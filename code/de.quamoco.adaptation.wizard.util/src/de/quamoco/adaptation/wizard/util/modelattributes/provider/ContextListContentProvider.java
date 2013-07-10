/*-------------------------------------------------------------------------+
|                                                                          |
| Copyright 2012 Technische Universitaet Muenchen and                      |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
| Licensed under the Apache License, Version 2.0 (the "License");          |
| you may not use this file except in compliance with the License.         |
| You may obtain a copy of the License at                                  |
|                                                                          |
|    http://www.apache.org/licenses/LICENSE-2.0                            |
|                                                                          |
| Unless required by applicable law or agreed to in writing, software      |
| distributed under the License is distributed on an "AS IS" BASIS,        |
| WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. |
| See the License for the specific language governing permissions and      |
| limitations under the License.                                           |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.adaptation.wizard.util.modelattributes.provider;

import java.util.Collection;

/**
 * 
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 22CD7DDF524206F05E79C33E1687F76C
 */
public class ContextListContentProvider extends AbstractContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		Collection<?> input = (Collection<?>) inputElement;
		return input.toArray();
	}

}
