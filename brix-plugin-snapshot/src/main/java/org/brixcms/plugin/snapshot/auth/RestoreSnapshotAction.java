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

package org.brixcms.plugin.snapshot.auth;

import org.brixcms.auth.AbstractWorkspaceAction;
import org.brixcms.workspace.Workspace;

public class RestoreSnapshotAction extends AbstractWorkspaceAction {
// ------------------------------ FIELDS ------------------------------

    private final Workspace snapshotWorkspace;

// --------------------------- CONSTRUCTORS ---------------------------

    public RestoreSnapshotAction(Context context, Workspace targetWorkspace) {
        super(context, targetWorkspace);
        this.snapshotWorkspace = null;
    }

    public RestoreSnapshotAction(Context context, Workspace targetWorkspace, Workspace snapshotWorkspace) {
        super(context, targetWorkspace);
        this.snapshotWorkspace = snapshotWorkspace;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public Workspace getSnapshotWorkspace() {
        return snapshotWorkspace;
    }

// -------------------------- OTHER METHODS --------------------------

    public Workspace getTargetWorkspace() {
        return getWorkspace();
    }

    public boolean isFromXML() {
        return snapshotWorkspace == null;
    }
}
