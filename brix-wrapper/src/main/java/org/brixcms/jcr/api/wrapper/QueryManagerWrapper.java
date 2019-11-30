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

import org.brixcms.jcr.api.JcrQuery;
import org.brixcms.jcr.api.JcrQueryManager;
import org.brixcms.jcr.api.JcrSession;

import javax.jcr.Node;
import javax.jcr.query.QueryManager;
import javax.jcr.query.qom.QueryObjectModelFactory;

/**
 * @author Matej Knopp
 * @author igor.vaynberg
 */
class QueryManagerWrapper extends AbstractWrapper implements JcrQueryManager {
// -------------------------- STATIC METHODS --------------------------

    public static JcrQueryManager wrap(QueryManager delegate, JcrSession session) {
        if (delegate == null) {
            return null;
        } else {
            return new QueryManagerWrapper(delegate, session);
        }
    }

// --------------------------- CONSTRUCTORS ---------------------------

    protected QueryManagerWrapper(QueryManager delegate, JcrSession session) {
        super(delegate, session);
    }

// ------------------------ INTERFACE METHODS ------------------------


// --------------------- Interface JcrQueryManager ---------------------

    @Override
    public QueryManager getDelegate() {
        return (QueryManager) super.getDelegate();
    }

// --------------------- Interface QueryManager ---------------------


    public JcrQuery createQuery(final String statement, final String language) {
        return executeCallback(new Callback<JcrQuery>() {
            public JcrQuery execute() throws Exception {
                return JcrQuery.Wrapper.wrap(getDelegate().createQuery(statement, language),
                        getJcrSession());
            }
        });
    }

    public QueryObjectModelFactory getQOMFactory() {
        return executeCallback(new Callback<QueryObjectModelFactory>() {
            public QueryObjectModelFactory execute() throws Exception {
                return getDelegate().getQOMFactory();
            }
        });
    }

    public JcrQuery getQuery(final Node node) {
        return executeCallback(new Callback<JcrQuery>() {
            public JcrQuery execute() throws Exception {
                return JcrQuery.Wrapper.wrap(getDelegate().getQuery(unwrap(node)), getJcrSession());
            }
        });
    }

    public String[] getSupportedQueryLanguages() {
        return executeCallback(new Callback<String[]>() {
            public String[] execute() throws Exception {
                return getDelegate().getSupportedQueryLanguages();
            }
        });
    }
}
