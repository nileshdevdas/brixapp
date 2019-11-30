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

package org.brixcms.workspace;

/**
 * Workspace manager that can be running in clustered JCR environment.
 *
 * @author Matej Knopp
 */
public interface ClusteredWorkspaceManager extends WorkspaceManager {
// -------------------------- OTHER METHODS --------------------------

    /**
     * Notification that a workspace has been created externally (e.g. on different node).
     *
     * @param workspaceId
     */
    public void workspaceCreated(String workspaceId);
}
