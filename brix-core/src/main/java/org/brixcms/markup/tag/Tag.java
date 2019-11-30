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

package org.brixcms.markup.tag;

import java.util.Map;

/**
 * Represents an HTML tag.
 *
 * @author Matej Knopp
 * @see ComponentTag
 */
public interface Tag extends Item {
// -------------------------- OTHER METHODS --------------------------

    /**
     * Returns map of tag attributes. The result map does not have to be modifiable.
     */
    public Map<String, String> getAttributeMap();

    /**
     * Returns the name of this tag ("a", "p", "br", "input", etc.).
     *
     * @return
     */
    public String getName();

    ;

    /**
     * Returns the type of this tag.
     *
     * @return
     */
    public Type getType();

// -------------------------- ENUMERATIONS --------------------------

    /**
     * Tag type
     *
     * @author Matej Knopp
     */
    public enum Type {
        OPEN, CLOSE, OPEN_CLOSE
    }
}
