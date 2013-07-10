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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration to establish a specialization link between two entities based on an
 * attribute that denotes whether an entity is a specialization.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class EntitySpecializationMigration extends CustomMigration {

	/**
	 * Attribute that denotes whether an entity is a specialization of another
	 * entity.
	 */
	private EAttribute specializationAttribute;

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		specializationAttribute = metamodel
				.getEAttribute("qmm.Entity.specialization");
	}

	/** {@inheritDoc} */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) {
		for (Instance entity : model.getAllInstances("qmm.Entity")) {
			boolean specialization = entity.unset(specializationAttribute);
			if (specialization) {
				Instance composite = entity.getLink("composite");
				if (composite != null) {
					entity.set("superordinate", composite);
				}
			}
		}
	}
}
