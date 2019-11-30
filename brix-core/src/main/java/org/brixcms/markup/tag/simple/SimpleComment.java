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

package org.brixcms.markup.tag.simple;

import org.brixcms.markup.tag.Comment;

/**
 * Simple implementation of the {@link Comment} interface.
 *
 * @author Matej Knopp
 */
public class SimpleComment implements Comment {
// ------------------------------ FIELDS ------------------------------

    private final String text;

// --------------------------- CONSTRUCTORS ---------------------------

    public SimpleComment(String text) {
        this.text = text;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getText() {
        return text;
    }
}
