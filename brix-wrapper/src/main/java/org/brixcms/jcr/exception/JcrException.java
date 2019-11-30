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

package org.brixcms.jcr.exception;

import javax.jcr.RepositoryException;

/**
 * @author Matej Knopp
 */
public class JcrException extends RuntimeException {
// ------------------------------ FIELDS ------------------------------

    private static final long serialVersionUID = 1L;

// --------------------------- CONSTRUCTORS ---------------------------

    public JcrException(RepositoryException cause) {
        super(cause);
    }

    public JcrException(String message, RepositoryException cause) {
        super(message, cause);
    }

// -------------------------- OTHER METHODS --------------------------

    @Override
    public RepositoryException getCause() {
        return (RepositoryException) super.getCause();
    }
}
