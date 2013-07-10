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

package de.quamoco.qm.util.migration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Metamodel;

public abstract class ImplementingMigrationBase extends CustomMigration {

	protected Set<EReference> getEAllIncomingReferences(Metamodel metamodel,
			EClass eClass) {
		Set<EReference> result = new HashSet<EReference>();
		List<EReference> inverse = metamodel.getInverse(eClass,
				EcorePackage.eINSTANCE.getETypedElement_EType());
		for (EReference reference : inverse) {
			if (reference.getEOpposite() == null && !reference.isContainment()) {
				result.add(reference);
			}
		}
		for (EClass superClass : eClass.getESuperTypes()) {
			result.addAll(getEAllIncomingReferences(metamodel, superClass));
		}
		return result;
	}

}
