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
package org.brixcms.web;

import org.apache.wicket.IRequestTarget;
import org.apache.wicket.request.RequestParameters;
import org.apache.wicket.request.target.coding.IRequestTargetUrlCodingStrategy;
import org.brixcms.BrixNodeModel;
import org.brixcms.Path;
import org.brixcms.jcr.exception.JcrException;
import org.brixcms.jcr.wrapper.BrixNode;
import org.brixcms.plugin.site.SitePlugin;
import org.brixcms.web.nodepage.BrixNodePageUrlCodingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrixUrlCodingStrategy implements IRequestTargetUrlCodingStrategy {
// ------------------------------ FIELDS ------------------------------

    Logger logger = LoggerFactory.getLogger(BrixUrlCodingStrategy.class);

    /**
     * request cycle processor
     */
    private final BrixRequestCycleProcessor brixRequestCycleProcessor;

// --------------------------- CONSTRUCTORS ---------------------------

    /**
     * Constructor
     *
     * @param brixRequestCycleProcessor
     */
    public BrixUrlCodingStrategy(BrixRequestCycleProcessor brixRequestCycleProcessor) {
        this.brixRequestCycleProcessor = brixRequestCycleProcessor;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface IRequestTargetUrlCodingStrategy ---------------------


    public String getMountPath() {
        throw new UnsupportedOperationException();
    }

    public CharSequence encode(IRequestTarget requestTarget) {
        throw new UnsupportedOperationException();
    }

    public IRequestTarget decode(RequestParameters requestParameters) {
        String pathStr = requestParameters.getPath();

        IRequestTarget target = targetForPath(pathStr, requestParameters);

        if (target == null) {
            // 404 if node not found
            // return new
            // WebErrorCodeResponseTarget(HttpServletResponse.SC_NOT_FOUND,
            // "Resource
            // " + pathStr
            // + " not found");
            return null;
            // return new PageRequestTarget(new
            // ResourceNotFoundPage(pathStr));
        } else {
            return target;
        }
    }

    public boolean matches(IRequestTarget requestTarget) {
        throw new UnsupportedOperationException();
    }

    public boolean matches(String path, boolean caseSensitive) {
        throw new UnsupportedOperationException();
    }

// -------------------------- OTHER METHODS --------------------------

    public IRequestTarget targetForPath(String pathStr, RequestParameters requestParameters) {
        if (!pathStr.startsWith("/")) {
            pathStr = "/" + pathStr;
        }

        // TODO: This is just a quick fix
        if (pathStr.startsWith("/webdav") || pathStr.startsWith("/jcrwebdav")) {
            return null;
        }

        Path path = decode(new Path(pathStr, false));

        IRequestTarget target = null;
        try {
            while (target == null) {
                final BrixNode node = this.brixRequestCycleProcessor.getNodeForUriPath(path);
                if (node != null) {
                    target = getSwitchTarget(node);
                    if (target == null) {
                        target = SitePlugin.get().getNodePluginForNode(node).respond(
                                new BrixNodeModel(node), requestParameters);
                    }
                }
                if (path.isRoot() || path.toString().equals(".")) {
                    break;
                }
                path = path.parent();
            }
        } catch (JcrException e) {
            Throwable iter = e;
            while (iter.getCause() != null) {
                iter = iter.getCause();
            }

            //TODO: shall we really rely on Jackrabbit implementation for this??? - trouble is that SPI is not covered by JCR2 intentionally
            //it's the last bit of jackrabbit in core!
//            if (iter instanceof MalformedPathException) {
//                logger.info("JcrException caught due to incorrect url",e);
//            } else {
//                throw e;
//            }
        }

        return target;
    }

    private Path decode(Path path) {
        StringBuilder builder = new StringBuilder(path.toString().length());
        boolean first = true;
        for (String s : path) {
            if (first) {
                first = false;
            } else {
                builder.append("/");
            }
            builder.append(BrixNodePageUrlCodingStrategy.urlDecode(s));
        }
        if (builder.length() == 0) {
            builder.append("/");
        }
        return new Path(builder.toString(), false);
    }

    private IRequestTarget getSwitchTarget(BrixNode node) {
        if (node instanceof BrixNode) {
            return SwitchProtocolRequestTarget.requireProtocol((node).getRequiredProtocol());
        } else {
            return null;
        }
    }
}
