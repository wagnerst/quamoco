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

public class ToolBasedInstrumentMigration extends CustomMigration {

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {

		for (Instance i : model.getInstances("qm.ToolBasedInstrument")) {
			Instance a = getAnnotation(i, "Cat-Id");
			if (a != null) {
				i.set("tool", a.get("value"));
				model.delete(a);
			}
			a = getAnnotation(i, "Rule-Id");
			if (a != null) {
				i.migrate("qm.RuleBasedInstrument");
				i.set("rule", a.get("value"));
				model.delete(a);
				continue;
			}
			a = getAnnotation(i, "Key");
			if (a != null) {
				i.migrate("qm.MetricBasedInstrument");
				i.set("metric", a.get("value"));
				model.delete(a);
			}
		}
	}

	private Instance getAnnotation(Instance e, String key) {
		for (Instance a : e.getLinks("annotations")) {
			if (a.get("key") == key) {
				return a;
			}
		}
		return null;
	}
}
