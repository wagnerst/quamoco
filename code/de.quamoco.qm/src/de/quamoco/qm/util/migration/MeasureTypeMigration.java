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

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration that sets the type of a measure based on the instrument that is
 * associated to it.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class MeasureTypeMigration extends CustomMigration {

	/** {@inheritDoc} */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) {
		for (Instance m : model.getAllInstances("qm.Measure")) {
			List<Instance> dets = m.getLinks("determinedBy");
			if (!dets.isEmpty()) {
				Instance det = dets.get(0);
				if (det.instanceOf("qm.RuleBasedInstrument")) {
					m.set("type", metamodel.getEEnumLiteral("qm.Type.FINDINGS"));
				} else if (det.instanceOf("qm.MetricBasedInstrument")) {
					m.set("type", metamodel.getEEnumLiteral("qm.Type.NUMBER"));
				}
			}
		}
	}
}
