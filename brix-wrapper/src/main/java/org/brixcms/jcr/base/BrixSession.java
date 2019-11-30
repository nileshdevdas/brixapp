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

package org.brixcms.jcr.base;

import org.brixcms.jcr.base.action.AbstractActionHandler;
import org.brixcms.jcr.base.event.EventsListener;
import org.brixcms.jcr.base.filter.ValueFilter;

import javax.jcr.Session;
import java.util.Map;

public interface BrixSession extends Session {
// -------------------------- OTHER METHODS --------------------------

    public void addActionHandler(AbstractActionHandler handler);

    public void addEventsListener(EventsListener listener);

    public Map<String, Object> getAttributesMap();

    public ValueFilter getValueFilter();

    public void setValueFilter(ValueFilter valueFilter);
}
