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

import java.util.List;

import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class ImpactFactorMigration extends TagMigrationBase {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance i : model.getAllInstances("qm.Impact")) {
			if (i.getLinks("factors").size() > 1) {
				Instance pf = model.newInstance("qm.Factor");
				i.getContainer().getContainer().add("factors", pf);
				List<Instance> factors = i.unset(metamodel
						.getEReference("qm.Impact.factors"));
				String name = assembleName(factors);
				pf.set("name", name);
				i.add("factors", pf);
			}
		}
	}
}
