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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.Model;

public class InstructionMeasureMigration extends CustomMigration {

	private EClass instructionClass;
	private EReference instructionReference;
	private EAttribute languageAttribute;
	private EAttribute valueAttribute;

	@Override
	public void migrateBefore(Model model, Metamodel metamodel)
			throws MigrationException {
		instructionClass = metamodel.getEClass("qm.Instruction");
		instructionReference = metamodel.getEReference("qm.Fact.instruction");
		languageAttribute = metamodel.getEAttribute("qm.Instruction.language");
		valueAttribute = metamodel.getEAttribute("qm.Instruction.value");
	}

	@Override
	public void migrateAfter(Model model, Metamodel metamodel)
			throws MigrationException {
		for (Instance fact : model.getAllInstances("qm.Fact")) {
			List<Instance> instructions = fact.unset(instructionReference);
			for (Instance instruction : instructions) {
				instruction.migrate("qm.Measure");
				String language = instruction.unset(languageAttribute);
				String value = instruction.unset(valueAttribute);
				instruction.set("name", fact.getLink("attribute").get("name")
						+ " @" + fact.getContainer().get("name"));

				Instance comment = model.newInstance("qm.Comment");
				comment.set("description", value);
				comment.set("language", language);
				instruction.add("comment", comment);
			}
			fact.set("measure", instructions);
		}
	}
}
