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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class ImplementingFactorMigration extends ImplementingMigrationBase {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {

		Map<String, String> replacement = new HashMap<String, String>();

		EClass implementingFactorClass = metamodel
				.getEClass("qm.ImplementingFactor");

		for (Instance implementingFactor : model
				.getAllInstances(implementingFactorClass)) {
			Instance implementedFactor = getImplementedFactor(implementingFactor);
			replacement.put("%%" + getFactorQualifiedName(implementingFactor)
					+ "%%", "%%" + getFactorQualifiedName(implementedFactor)
					+ "%%");
		}

		for (Instance eval : model.getAllInstances("qm.Evaluation")) {
			for (Entry<String, String> r : replacement.entrySet()) {
				String specification = eval.get("specification");
				eval.set("specification", specification.replaceAll(
						Pattern.quote(r.getKey()),
						Matcher.quoteReplacement(r.getValue())));
			}
		}

		for (Instance implementingFactor : model
				.getAllInstances(implementingFactorClass)) {
			Instance implementedFactor = implementingFactor.get("implements");
			for (EReference reference : implementingFactorClass
					.getEAllReferences()) {
				if (reference.isMany()) {
					implementedFactor.getLinks(reference).addAll(
							implementingFactor.getLinks(reference));
				}
			}
			for (EReference reference : getEAllIncomingReferences(metamodel,
					implementingFactorClass)) {
				List<Instance> values = implementingFactor
						.getInverse(reference);
				for (Instance value : values) {
					if (reference.isMany()) {
						value.remove(reference, implementingFactor);
						value.add(reference, implementedFactor);
					} else {
						value.set(reference, implementedFactor);
					}
				}
			}
			model.delete(implementingFactor);
		}
	}

	private Instance getFactorEntity(Instance factor) {
		if (factor.get("characterizes") != null) {
			return factor.get("characterizes");
		}
		if (factor.instanceOf("qm.ImplementingFactor")) {
			return getFactorEntity(factor.getLink("implements"));
		}
		return null;
	}

	private Instance getImplementedFactor(Instance factor) {
		while (factor.instanceOf("qm.ImplementingFactor")) {
			factor = factor.get("implements");
		}
		return factor;
	}

	private String getFactorQualifiedName(Instance factor) {
		String qualifiedName = "";
		if (factor.instanceOf("qm.ImplementingFactor")) {
			qualifiedName += factor.getLink("qualityModel").get("name") + "/";
		}

		String name = getImplementedFactor(factor).get("name");
		if (name != null) {
			qualifiedName += name;
		}

		Instance entity = getFactorEntity(factor);
		if (entity != null) {
			String entityQualifiedName = entity.get("name");
			if (!"System".equals(entityQualifiedName)
					&& !"".equals(entityQualifiedName)) {
				qualifiedName += " @" + entityQualifiedName;
			}
		}

		return qualifiedName;
	}
}
