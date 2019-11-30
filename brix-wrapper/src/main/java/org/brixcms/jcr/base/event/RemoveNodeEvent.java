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

package org.brixcms.jcr.base.event;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

/**
 * Event indicating that a node has been removed.
 *
 * @author Matej
 */
public class RemoveNodeEvent extends NodeEvent {
// ------------------------------ FIELDS ------------------------------

    private final String nodeName;
    private final String nodeUUID;

// --------------------------- CONSTRUCTORS ---------------------------

    RemoveNodeEvent(Node node) throws RepositoryException {
        super(node.getParent());
        this.nodeName = node.getName();
        this.nodeUUID = node.isNodeType("mix:referenceable") ? node.getIdentifier() : null;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getNodeName() {
        return nodeName;
    }

    public String getNodeUUID() {
        return nodeUUID;
    }

// -------------------------- OTHER METHODS --------------------------

    public Node getParentNode() {
        return super.getNode();
    }
}