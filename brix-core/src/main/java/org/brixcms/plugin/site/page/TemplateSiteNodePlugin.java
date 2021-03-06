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

package org.brixcms.plugin.site.page;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.brixcms.Brix;
import org.brixcms.jcr.wrapper.BrixNode;
import org.brixcms.plugin.site.NodeConverter;
import org.brixcms.plugin.site.SimpleCallback;
import org.brixcms.plugin.site.SitePlugin;
import org.brixcms.plugin.site.page.admin.CreatePageOrTemplatePanel;

public class TemplateSiteNodePlugin extends AbstractSitePagePlugin {
// ------------------------------ FIELDS ------------------------------

    public static final String TYPE = Brix.NS_PREFIX + "tileTemplate";

// --------------------------- CONSTRUCTORS ---------------------------

    public TemplateSiteNodePlugin(SitePlugin plugin) {
        super(plugin);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface SiteNodePlugin ---------------------


    @Override
    public String getNodeType() {
        return TYPE;
    }

    public String getName() {
        return (new ResourceModel("template", "Template")).getObject();
    }

    public IModel<String> newCreateNodeCaptionModel(IModel<BrixNode> parentNode) {
        return new ResourceModel("createTemplate", "Create New Template");
    }

    ;

    @Override
    public Panel newCreateNodePanel(String id, IModel<BrixNode> parentNode, SimpleCallback goBack) {
        return new CreatePageOrTemplatePanel(id, parentNode, getNodeType(), goBack);
    }

    @Override
    public NodeConverter getConverterForNode(BrixNode node) {
        if (PageSiteNodePlugin.TYPE.equals(((BrixNode) node).getNodeType()))
            return new FromPageConverter(getNodeType());
        else
            return super.getConverterForNode(node);
    }

// -------------------------- INNER CLASSES --------------------------

    private static class FromPageConverter extends SetTypeConverter {
        public FromPageConverter(String type) {
            super(type);
        }
    }
}
