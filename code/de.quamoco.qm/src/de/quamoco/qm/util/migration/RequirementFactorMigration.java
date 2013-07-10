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
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class RequirementFactorMigration extends TagMigrationBase {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {

		for (Instance r : model.getAllInstances("qm.QualityRequirement")) {
			Instance f = model.newInstance("qm.Factor");
			f.set("name", r.get("name"));
			f.set("description", r.get("description"));
			f.add("tags", getOrCreateTag(model, "Requirement"));
			r.getContainer().add("factors", f);
			for (Instance i : r.getLinks("impacts")) {
				if (i.get("factors") != null) {
					addRefinement(model, f, i.getLink("factors"), "impacts");
				} else {
					i.set("factors", f);
				}
			}
			model.delete(r);
		}
	}
}
