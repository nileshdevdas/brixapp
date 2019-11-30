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

package org.brixcms.util;

import java.util.Iterator;

public class ArrayIterator<T> implements Iterator<T> {
// ------------------------------ FIELDS ------------------------------

    private int index = 0;
    private final T[] array;

// --------------------------- CONSTRUCTORS ---------------------------

    public ArrayIterator(T[] array) {
        super();
        this.array = array;
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface Iterator ---------------------

    public boolean hasNext() {
        return index < array.length;
    }

    public T next() {
        return array[index++];
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
