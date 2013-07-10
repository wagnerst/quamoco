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

package de.quamoco.qm.editor.evaluation;

import org.conqat.ide.core.preferences.EConQATPreferences;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;

/**
 * Runnable to perform the ConQAT evaluation on a Java system.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class JavaConQATRunnable extends ConQATRunnableBase {

	/** The directory with the binaries. */
	private final String binaryDir;

	/** Constructor. */
	public JavaConQATRunnable(QualityModel model, String projectName,
			String sourceDir, String binaryDir, String outputDir,
			String manualMeasureFile, CustomQmEditor editor) {
		super("Editor-java", model, projectName, sourceDir, outputDir,
				manualMeasureFile, editor);

		this.binaryDir = binaryDir;
	}

	/** {@inheritDoc} */
	@Override
	protected void addProperties(StringBuffer properties) {
		properties.append("byte-code.dir=" + binaryDir + "\n");
		for (String s : EConQATPreferences.CONQAT_VMARGS.getStringValue()
				.split(" ")) {
			properties.append("vm-arg.value=" + s + "\n");
		}
	}
}
