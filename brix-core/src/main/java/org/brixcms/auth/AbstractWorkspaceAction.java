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

package org.brixcms.auth;

import org.brixcms.workspace.Workspace;


public abstract class AbstractWorkspaceAction extends AbstractAction {
// ------------------------------ FIELDS ------------------------------

    private final Workspace workspace;

// --------------------------- CONSTRUCTORS ---------------------------

    public AbstractWorkspaceAction(Context context, Workspace workspace) {
        super(context);
        this.workspace = workspace;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Workspace getWorkspace() {
        return workspace;
    }

// ------------------------ CANONICAL METHODS ------------------------

    @Override
    public String toString() {
        return "AbstractWorkspaceAction{" + "workspace=" + workspace + "} " + super.toString();
    }
}
