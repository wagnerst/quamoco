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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class ImpactAspectMigration extends CustomMigration {

	private EAttribute activityIdAttribute;

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		activityIdAttribute = metamodel.getEAttribute("qmm.Impact.activityId");
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance impact : model.getInstances("qmm.Impact")) {
			String activityId = impact.unset(activityIdAttribute);
			Instance qualityModel = impact.getResource().getRootInstances()
					.get(0);
			impact.set("aspect", findAspectInModel(qualityModel, activityId));
		}
	}

	public Instance findAspectInModel(Instance qualityModel, String activityId) {
		String[] path = activityId.substring(1).split("/");
		Instance aspect = qualityModel;
		for (String segment : path) {
			aspect = findAspect(aspect, segment);
		}
		return aspect;
	}

	private Instance findAspect(Instance aspect, String segment) {
		for (Instance subAspect : aspect.getLinks("aspect")) {
			if (segment.equals(subAspect.get("id"))) {
				return subAspect;
			}
		}
		return null;
	}
}
