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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class PropertyToFactorMigration extends TagMigrationBase {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {

		for (Instance f : model.getAllInstances("qm.Factor")) {
			f.add("tags", getOrCreateTag(model, "Factor"));
		}

		for (Instance p : model.getAllInstances("qm.Property")) {
			Instance pf = createFactorFromProperty(model, p);
			for (Instance f : p.getLinks("factors")) {
				addRefinement(model, pf, f, "implements");
			}
		}

		Instance obj = getOrCreateEntity(model, "Object");
		if (obj != null) {
			for (Instance e : model.getAllInstances("qm.Entity")) {
				if (e.get("isA") == null && e != obj) {
					e.set("isA", obj);
				}
			}
		}

		for (Instance f : model.getAllInstances("qm.Factor")) {
			Instance p = f.get("property");
			Instance e = f.get("characterizes");
			if (e.get("isA") != null) {
				List<Instance> rfs = getFactorsWithProperty(e, p);
				for (Instance rf : rfs) {
					addRefinement(model, rf, f, "implements");
				}
			}
		}
	}

	private List<Instance> getFactorsWithProperty(Instance entity,
			Instance property) {
		List<Instance> factors = new ArrayList<Instance>();
		for (Instance factor : entity.getLink("isA")
				.getLinks("characterizedBy")) {
			if (factor.get("property") == property) {
				factors.add(factor);
			}
		}
		return factors;
	}

	private Instance createFactorFromProperty(Model model, Instance p) {
		Instance f = model.newInstance("qm.Factor");
		f.set("name", p.get("name"));
		f.set("description", p.get("description"));
		f.getLinks("annotations").addAll(p.getLinks("annotations"));
		f.set("characterizes", getOrCreateEntity(model, "Object"));
		f.add("tags", getOrCreateTag(model, "Property"));
		p.getLinks("annotations").removeAll(p.getLinks("annotations"));
		p.getContainer().add("factors", f);
		return f;
	}
}
