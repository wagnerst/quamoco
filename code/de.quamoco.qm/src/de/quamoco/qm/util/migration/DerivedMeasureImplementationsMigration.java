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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Migration to automatically set the specification of a derived measure based
 * on the measures from which it is derived. The reference to these measures is
 * deleted by the metamodel adaptation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class DerivedMeasureImplementationsMigration extends CustomMigration {

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		for (Instance dm : model.getAllInstances("qm.DerivedMeasure")) {
			EList<Instance> impls = dm.getLinks("implementations");
			if (impls.size() == 1) {
				dm.set("specification", "return %%" + impls.get(0).get("name")
						+ "%%;");
			} else if (impls.size() > 1) {
				String spec = "return union(";
				int i = 0;
				for (Instance m : impls) {
					if (i > 0) {
						spec += ", ";
					}
					spec += "%%" + m.get("name") + "%%";
					i++;
				}
				spec += dm.get("specification") + ");";
				dm.set("specification", spec);
			}
		}
	}
}
