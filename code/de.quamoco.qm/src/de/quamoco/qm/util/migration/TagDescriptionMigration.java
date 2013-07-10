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

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class TagDescriptionMigration extends CustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance tag : model.getAllInstances("qm.Tag")) {
			if (tag.get("description") == null) {
				String name = tag.get("name");
				if ("Property".equals(name)) {
					tag.set("description",
							"The tagged factor has been a property in the metamodel of iteration 1.");
				} else if ("Factor".equals(name)) {
					tag.set("description",
							"The tagged factor has already been a factor in the metamodel of iteration 1.");
				} else if ("implements".equals(name)) {
					tag.set("description",
							"The tagged factor refinement has been a relation between factor and property or between two factors having the same property in the metamodel of iteration 1");
				} else if ("composite".equals(name)) {
					tag.set("description",
							"A new factor has been introduced as parent for impacts originating from several factors in the metamodel of iteration 1. The factor refinements between between these factors and the new parent factor have been tagged using this tag.");
				} else if ("Requirement".equals(name)) {
					tag.set("description",
							"The tagged factor has been a requirement in the metamodel of iteration 1.");
				} else if ("impacts".equals(name)) {
					tag.set("description",
							"The tagged factor refinement has been a relation between a requirement and the source factor of an impact of that requirement in the metamodel of iteration 1.");
				} else if ("ImpactEvaluation".equals(name)) {
					tag.set("description",
							"The tagged factor has been an impact evaluation in the metamodel of iteration 1.");
				} else if ("impact".equals(name)) {
					tag.set("description",
							"The tagged factor refinement has been a relation between an impact evaluation and the source factor of the impact of the evaluation in the metamodel of iteration 1.");
				} else if ("Aspect".equals(name)) {
					tag.set("description",
							"The tagged factor has been an aspect in the metamodel of iteration 1.");
				} else if ("partof".equals(name)) {
					tag.set("description",
							"The tagged factor refinement has been a part of relation between aspects in the metamodel of iteration 1.");
				}
			}
		}
	}
}
