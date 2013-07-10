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

import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration that tags all quality aspects as such and characterizes them with
 * System as entity. Moreover, all entities are made part of System. In between,
 * these two steps quality aspects are replaced by factors.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class AspectEntityMigration extends TagMigrationBase {

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		for (Instance factor : model.getAllInstances("qm.QualityAspect")) {
			factor.add("tags", getOrCreateTag(model, "Aspect"));
		}
	}

	/** {@inheritDoc} */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) {
		for (Instance qualityModel : model.getAllInstances("qm.QualityModel")) {
			for (Instance factor : qualityModel.getLinks("aspects")) {
				factor.set("characterizes", getOrCreateEntity(model, "System"));
			}
		}

		Instance s = getSystemEntity(model);
		if (s != null) {
			for (Instance e : model.getAllInstances("qm.Entity")) {
				if (e != s) {
					e.set("partOf", s);
				}
			}
		}
	}

	/** Get the entity that describes the software system. */
	private Instance getSystemEntity(Model model) {
		for (Instance entity : model.getAllInstances("qm.Entity")) {
			if ("System".equals(entity.get("name"))) {
				return entity;
			}
		}
		return null;
	}
}
