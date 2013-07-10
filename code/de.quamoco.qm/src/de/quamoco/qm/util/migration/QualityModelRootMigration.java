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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;
import org.eclipse.emf.edapt.migration.ModelResource;

public class QualityModelRootMigration extends CustomMigration {

	private EClass qualityModelClass;

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		qualityModelClass = metamodel.getEClass("qm.QualityModel");
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {

		for (Instance qualityModel : model.getAllInstances(qualityModelClass)) {
			ModelResource resource = qualityModel.getResource();
			resource.getRootInstances()
					.addAll(qualityModel.getLinks("modules"));
			qualityModel.getLinks("modules").removeAll(
					qualityModel.getLinks("modules"));
			resource.getRootInstances().remove(qualityModel);
			model.delete(qualityModel);
		}
	}
}
