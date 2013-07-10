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

public class ImplementingMeasureMigration extends ImplementingMigrationBase {

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {

		EClass implementingMeasureClass = metamodel
				.getEClass("qm.ImplementingMeasure");

		Map<String, String> replacement = new HashMap<String, String>();

		for (Instance implementingMeasure : model
				.getAllInstances(implementingMeasureClass)) {
			Instance implementedMeasure = getImplementedMeasure(implementingMeasure);
			replacement.put("%%" + getMeasureQualifiedName(implementingMeasure)
					+ "%%", "%%" + getMeasureQualifiedName(implementedMeasure)
					+ "%%");
		}

		for (Instance eval : model.getAllInstances("qm.Evaluation")) {
			for (Entry<String, String> r : replacement.entrySet()) {
				String specification = eval.get("specification");
				specification = specification.replaceAll(
						Pattern.quote(r.getKey()),
						Matcher.quoteReplacement(r.getValue()));
				eval.set("specification", specification);
			}
		}

		for (Instance aggr : model.getAllInstances("qm.Aggregation")) {
			for (Entry<String, String> r : replacement.entrySet()) {
				String specification = aggr.get("specification");
				specification = specification.replaceAll(
						Pattern.quote(r.getKey()),
						Matcher.quoteReplacement(r.getValue()));
				aggr.set("specification", specification);
			}
		}

		for (Instance implementingMeasure : model
				.getAllInstances(implementingMeasureClass)) {
			Instance implementedMeasure = implementingMeasure.get("implements");
			inline(model, implementingMeasure, implementedMeasure, metamodel);
		}
	}

	private void inline(Model model, Instance implementingMeasure,
			Instance implementedMeasure, Metamodel metamodel) {
		for (EReference reference : implementingMeasure.getEClass()
				.getEAllReferences()) {
			if ("measures".equals(reference.getName())) {
				for (Instance measurement : new ArrayList<Instance>(
						implementingMeasure.getLinks("measures"))) {
					Instance existingMeasurement = getMeasurement(
							implementedMeasure, measurement);
					if (existingMeasurement != null) {
						inline(model, measurement, existingMeasurement,
								metamodel);
					}
				}
			}
			if (reference.isMany()) {
				implementedMeasure.getLinks(reference).addAll(
						implementingMeasure.getLinks(reference));
			}
		}
		for (EReference reference : getEAllIncomingReferences(metamodel,
				metamodel.getEClass("qm.ImplementingMeasure"))) {
			List<Instance> values = implementingMeasure.getInverse(reference);
			for (Instance value : values) {
				if (reference.isMany()) {
					value.remove(reference, implementingMeasure);
					value.add(reference, implementedMeasure);
				} else {
					value.set(reference, implementedMeasure);
				}
			}
		}
		model.delete(implementingMeasure);
	}

	private Instance getMeasurement(Instance implementedMeasure,
			Instance measurement) {
		for (Instance m : implementedMeasure.getLinks("measures")) {
			if (m.get("parent") == measurement.get("parent")) {
				return m;
			}
		}
		return null;
	}

	private Instance getMeasureEntity(Instance measure) {
		if (measure.get("characterizes") != null) {
			return measure.get("characterizes");
		}
		if (measure.instanceOf("qm.ImplementingMeasure")) {
			return getMeasureEntity(measure.getLink("implements"));
		}
		return null;
	}

	private Instance getImplementedMeasure(Instance measure) {
		while (measure.instanceOf("qm.ImplementingMeasure")) {
			measure = measure.get("implements");
		}
		return measure;
	}

	private String getMeasureQualifiedName(Instance measure) {
		String qualifiedName = "";

		if (measure.instanceOf("qm.ImplementingMeasure")) {
			qualifiedName += measure.getLink("qualityModel").get("name") + "/";
		}

		String name = getImplementedMeasure(measure).get("name");
		if (name != null) {
			qualifiedName += name;
		}

		Instance entity = getMeasureEntity(measure);
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
