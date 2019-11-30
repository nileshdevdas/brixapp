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

package org.brixcms.demo.web.dav;

import org.apache.jackrabbit.webdav.jcr.JCRWebdavServerServlet;
import org.apache.wicket.Application;
import org.brixcms.demo.web.WicketApplication;

import javax.jcr.Repository;

public class JcrServlet extends JCRWebdavServerServlet {
// --------------------------- CONSTRUCTORS ---------------------------

    public JcrServlet() {

    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    protected Repository getRepository() {
        WicketApplication app = (WicketApplication) Application.get("wicket.brix-demo");
        return app.getRepository();
    }
}
