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
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration to parse the completeness of an evaluation from the description of
 * the evaluation. The description has to be of the form
 * "100;all relevant measures seem to be included in this evaluation" to be able
 * to parse the value 100 from it. In the end, value is removed from the
 * description.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class EvaluationCompletenessMigration extends CustomMigration {

	/** {@inheritDoc} */
	@Override
	public void migrateAfter(Model model, Metamodel metamodel) {
		for (Instance evaluation : model.getAllInstances("qm.Evaluation")) {
			String description = evaluation.get("description");
			if (description != null) {
				try {
					int index = description.indexOf(';');
					String complText = description.substring(0, index);
					int completeness = Integer.parseInt(complText);
					evaluation.set("completeness", completeness);
					evaluation.set("description",
							description.substring(index + 1));
				} catch (RuntimeException e) {
					// ignore
				}
			}
		}
	}
}
