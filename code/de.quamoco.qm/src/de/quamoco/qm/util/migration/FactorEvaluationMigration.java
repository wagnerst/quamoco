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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

public class FactorEvaluationMigration extends TagMigrationBase {

	private EReference evaluatedByReference;

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		evaluatedByReference = metamodel.getEReference("qm.Impact.evaluatedBy");
	}

	/** {@inheritDoc} */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) {
		for (Instance f : model.getAllInstances("qm.Factor")) {
			Set<String> specs = getSpecs(f);
			if (specs.size() > 1) {
				for (String spec : specs) {
					List<Instance> impacts = findImpactsWithSpecs(f, spec);
					Instance cf = model.newInstance("qm.Factor");
					cf.add("tags", getOrCreateTag(model, "ImpactEvaluation"));
					cf.set("characterizes", f.get("characterizes"));
					f.getContainer().add("factors", cf);
					String name = assembleName(getInfluences(impacts));
					if (impacts.size() > 1) {
						cf.set("name", "(" + f.get("name") + ") on (" + name
								+ ")");
					} else {
						cf.set("name", "(" + f.get("name") + ") on " + name);
					}
					addRefinement(model, f, cf, "impact");
					for (Instance i : impacts) {
						migrateEvaluation(model, cf, i);
						f.remove("impacts", i);
						cf.add("impacts", i);
					}
				}
			} else {
				for (Instance i : f.getLinks("impacts")) {
					migrateEvaluation(model, f, i);
				}
			}
		}
	}

	private void migrateEvaluation(Model model, Instance cf, Instance i) {
		Instance eval = i.unset(evaluatedByReference);
		if (cf.get("evaluatedBy") == null) {
			cf.set("evaluatedBy", eval);
		} else if (eval != null) {
			model.delete(eval);
		}
	}

	private List<Instance> getInfluences(List<Instance> impacts) {
		List<Instance> influences = new ArrayList<Instance>();
		for (Instance impact : impacts) {
			Instance influence = impact.get("influences");
			if (influence != null) {
				influences.add(influence);
			}
		}
		return influences;
	}

	private List<Instance> findImpactsWithSpecs(Instance factor, String spec) {
		List<Instance> impacts = new ArrayList<Instance>();
		for (Instance i : factor.getLinks("impacts")) {
			Instance evaluatedBy = i.get(evaluatedByReference);
			if (evaluatedBy != null
					&& evaluatedBy.get("specification").equals(spec)) {
				impacts.add(i);
			}
		}
		return impacts;
	}

	private Set<String> getSpecs(Instance factor) {
		Set<String> specs = new HashSet<String>();
		for (Instance i : factor.getLinks("impacts")) {
			Instance evaluatedBy = i.get(evaluatedByReference);
			if (evaluatedBy != null) {
				String spec = evaluatedBy.get("specification");
				specs.add(spec);
			} else {
				specs.add(null);
			}
		}
		return specs;
	}
}
