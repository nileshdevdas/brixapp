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

package org.brixcms.web.reference;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.link.AbstractLink;

public class ReferenceLink extends AbstractLink {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 1L;

    private final Reference reference;

// --------------------------- CONSTRUCTORS ---------------------------

    public ReferenceLink(String id, Reference reference) {
        super(id);
        this.reference = reference;
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    protected void onComponentTag(ComponentTag tag) {
        super.onComponentTag(tag);
        if (!isLinkEnabled()) {
            disableLink(tag);
        }
        tag.put("href", reference.getUrl());
    }

    @Override
    protected void onDetach() {
        reference.detach();
        super.onDetach();
    }
}
