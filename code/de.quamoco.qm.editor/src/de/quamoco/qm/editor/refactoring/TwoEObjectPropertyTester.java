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

package de.quamoco.qm.editor.refactoring;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;

/**
 * Tester for properties concerning two elements.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 11C15BA9C088280FABF803961035A3CA
 */
public class TwoEObjectPropertyTester extends PropertyTester {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {

		List<EObject> elements = (List<EObject>) receiver;
		EObject first = elements.get(0);
		EObject second = elements.get(1);

		if ("sameEClass".equals(property)) {
			boolean value = first.eClass() == second.eClass();
			boolean result = value == (Boolean) expectedValue;
			return result;
		} else if ("sameEContainer".equals(property)) {
			boolean value = first.eContainer() == second.eContainer();
			boolean result = value == (Boolean) expectedValue;
			return result;
		}

		return false;
	}

}
