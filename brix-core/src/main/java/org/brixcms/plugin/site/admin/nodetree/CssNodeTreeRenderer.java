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

package org.brixcms.plugin.site.admin.nodetree;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.tree.BaseTree;

/**
 * Renderer for css nodes
 *
 * @author Jeremy Thomerson
 */
public class CssNodeTreeRenderer extends AbstractMimeTypeTreeRenderer {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 1L;

    private static final ResourceReference RESOURCE = new ResourceReference(
            PageNodeTreeRenderer.class, "resources/gnome-mime-text-css.png");

// --------------------------- CONSTRUCTORS ---------------------------

    public CssNodeTreeRenderer() {
        super(new String[]{"text/css"});
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    protected ResourceReference getImageResourceReference(BaseTree tree, Object node) {
        return RESOURCE;
    }
}
