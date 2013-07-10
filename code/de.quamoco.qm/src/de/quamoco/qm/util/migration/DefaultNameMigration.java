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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration to set the name to a default based on the type of a model element.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class DefaultNameMigration extends CustomMigration {

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		setDefaultName(model, "qm.Evaluation");
		setDefaultName(model, "qm.Aggregation");
		setDefaultName(model, "qm.ManualInstrument");
	}

	/**
	 * Set the default name of elements of a certain class by using the class
	 * name.
	 */
	private void setDefaultName(Model model, String className) {
		EClass eClass = model.getMetamodel().getEClass(className);
		for (Instance element : model.getAllInstances(eClass)) {
			if (element.get("name") == null) {
				element.set("name", eClass.getName());
			}
		}
	}
}
