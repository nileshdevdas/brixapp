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

package org.brixcms.jcr.api.wrapper;

import org.brixcms.jcr.api.JcrSession;
import org.brixcms.jcr.api.JcrValue;
import org.brixcms.jcr.api.JcrValueFactory;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.ValueFactory;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @author Matej Knopp
 * @author igor.vaynberg
 */
class ValueFactoryWrapper extends AbstractWrapper implements JcrValueFactory {
// -------------------------- STATIC METHODS --------------------------

    public static JcrValueFactory wrap(ValueFactory delegate, JcrSession session) {
        if (delegate == null) {
            return null;
        } else {
            return new ValueFactoryWrapper(delegate, session);
        }
    }

// --------------------------- CONSTRUCTORS ---------------------------

    protected ValueFactoryWrapper(ValueFactory delegate, JcrSession session) {
        super(delegate, session);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface JcrValueFactory ---------------------

    @Override
    public ValueFactory getDelegate() {
        return (ValueFactory) super.getDelegate();
    }

// --------------------- Interface ValueFactory ---------------------


    public JcrValue createValue(final Node value, final boolean weak) {
        return executeCallback(new Callback<JcrValue>() {
            public JcrValue execute() throws Exception {
                return JcrValue.Wrapper.wrap(getDelegate().createValue(unwrap(value), weak), getJcrSession());
            }
        });
    }

    public JcrValue createValue(String value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    public JcrValue createValue(final String value, final int type) {
        return executeCallback(new Callback<JcrValue>() {
            public JcrValue execute() throws Exception {
                return JcrValue.Wrapper.wrap(getDelegate().createValue(value, type),
                        getJcrSession());
            }
        });
    }

    public JcrValue createValue(long value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    public JcrValue createValue(double value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    public JcrValue createValue(final BigDecimal value) {
        return executeCallback(new Callback<JcrValue>() {
            public JcrValue execute() throws Exception {
                return JcrValue.Wrapper.wrap(getDelegate().createValue(unwrap(value)), getJcrSession());
            }
        });
    }

    public JcrValue createValue(boolean value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    public JcrValue createValue(Calendar value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    /**
     * @deprecated
     */
    @Deprecated
    public JcrValue createValue(InputStream value) {
        return JcrValue.Wrapper.wrap(getDelegate().createValue(value), getJcrSession());
    }

    public JcrValue createValue(final Binary binary) {
        return executeCallback(new Callback<JcrValue>() {
            public JcrValue execute() throws Exception {
                return JcrValue.Wrapper.wrap(getDelegate().createValue(unwrap(binary)), getJcrSession());
            }
        });
    }

    public JcrValue createValue(final Node value) {
        return executeCallback(new Callback<JcrValue>() {
            public JcrValue execute() throws Exception {
                return JcrValue.Wrapper.wrap(getDelegate().createValue(unwrap(value)),
                        getJcrSession());
            }
        });
    }

    public Binary createBinary(final InputStream stream) {
        return executeCallback(new Callback<Binary>() {
            public Binary execute() throws Exception {
                return getDelegate().createBinary(stream);
            }
        });
    }
}
