/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 */
package org.brixcms.plugin.site.resource;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.util.time.Time;
import org.brixcms.Brix;
import org.brixcms.auth.Action;
import org.brixcms.jcr.wrapper.BrixFileNode;
import org.brixcms.jcr.wrapper.BrixNode;
import org.brixcms.plugin.site.SitePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;

public class ResourceRequestTarget implements IRequestTarget {
// ------------------------------ FIELDS ------------------------------

    public static final String SAVE_PARAMETER = Brix.NS_PREFIX + "save";

    private static final Logger log = LoggerFactory.getLogger(ResourceRequestTarget.class);
    private final IModel<BrixNode> node;
    private final Boolean save;

// --------------------------- CONSTRUCTORS ---------------------------

    public ResourceRequestTarget(IModel<BrixNode> node) {
        super();
        this.node = node;
        this.save = null;
    }

    public ResourceRequestTarget(IModel<BrixNode> node, boolean save) {
        super();
        this.node = node;
        this.save = save;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface IRequestTarget ---------------------


    public void respond(RequestCycle requestCycle) {
        boolean save = (this.save != null) ? this.save : Strings.isTrue(requestCycle.getRequest()
                .getParameter(SAVE_PARAMETER));

        BrixFileNode node = (BrixFileNode) this.node.getObject();

        if (!SitePlugin.get().canViewNode(node, Action.Context.PRESENTATION)) {
            throw Brix.get().getForbiddenException();
        }

        WebResponse response = (WebResponse) requestCycle.getResponse();

        response.setContentType(node.getMimeType());

        Date lastModified = node.getLastModified();
        response.setLastModifiedTime(Time.valueOf(lastModified));

        try {
            HttpServletRequest r = ((WebRequest) requestCycle.getRequest()).getHttpServletRequest();
            String since = r.getHeader("If-Modified-Since");
            if (!save && since != null) {
                Date d = new Date(r.getDateHeader("If-Modified-Since"));

                // the weird toString comparison is to prevent comparing milliseconds
                if (d.after(lastModified) || d.toString().equals(lastModified.toString())) {
                    response.setContentLength(node.getContentLength());
                    response.getHttpServletResponse().setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                    return;
                }
            }
            String fileName = node.getName();
            long length = node.getContentLength();
            HttpServletRequest httpServletRequest = ((WebRequest) requestCycle.getRequest()).getHttpServletRequest();
            HttpServletResponse httpServletResponse = response.getHttpServletResponse();
            InputStream stream = node.getDataAsStream();

            new Streamer(length, stream, fileName, save, httpServletRequest, httpServletResponse).stream();
        } catch (Exception e) {
            log.error("Error writing resource data to content", e);
        }
    }

    public void detach(RequestCycle requestCycle) {
        node.detach();
    }
}
