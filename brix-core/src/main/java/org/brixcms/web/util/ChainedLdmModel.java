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

package org.brixcms.web.util;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class ChainedLdmModel extends LoadableDetachableModel {
// ------------------------------ FIELDS ------------------------------

    private final IModel chained;

// --------------------------- CONSTRUCTORS ---------------------------

    public ChainedLdmModel(IModel model) {
        super();
        this.chained = model;
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    protected Object load() {
        return load(chained);
    }

    protected abstract Object load(IModel chained);

    @Override
    protected void onDetach() {
        chained.detach();
        super.onDetach();
    }
}
