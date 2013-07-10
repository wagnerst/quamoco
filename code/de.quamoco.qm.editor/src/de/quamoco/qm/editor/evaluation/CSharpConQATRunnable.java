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

import java.io.File;
import java.io.IOException;

import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.eclipse.core.runtime.IProgressMonitor;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;

/**
 * Runnable to perform the ConQAT evaluation on a C# system.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class CSharpConQATRunnable extends ConQATRunnableBase {

	/** Path to the Gendarme file. */
	private final String gendarmeFile;

	/** Path which was used when Gendarme was executed. */
	private final String gendarmeDir;
	
	/** File where the understand ranges are saved */
	private final String understandRangesFile;

	/** Constructor. */
	public CSharpConQATRunnable(QualityModel model, String projectName,
			String sourceDir, String outputDir, String manualMeasureFile,
			String gendarmeFile, String gendarmeDir, String understandRangesFile, CustomQmEditor editor) {
		super("Editor-csharp", model, projectName, sourceDir, outputDir,
				manualMeasureFile, editor);
		this.gendarmeFile = gendarmeFile;
		this.gendarmeDir = gendarmeDir;
		this.understandRangesFile=understandRangesFile;
	}

	/** {@inheritDoc} */
	@Override
	public void run(IProgressMonitor monitor) throws InterruptedException {

		// create map.properties file
		File propertiesMapFile = new File(sourceDir, "map.properties");
		try {
			FileSystemUtils.writeFile(propertiesMapFile, "gendarme-root="
					+ getGendarmeDir() + "\n");
		} catch (IOException e) {
			throw new InterruptedException(
					"Properties file could not be written.");
		}

		// copy gendarmeReport.xml to the right location
		try {
			File sourceFile = new File(gendarmeFile);
			File targetFile = new File(sourceDir, "gendarmeReport.xml");
			if (!sourceFile.equals(targetFile)) {
				FileSystemUtils.copyFile(sourceFile, targetFile);
			}
		} catch (IOException e) {
			throw new InterruptedException("Gendarme file could not be copied.");
		}

		// copy understand-ranges.xml to the right location
		try {
			File sourceFile = new File(understandRangesFile);
			File targetFile = new File(sourceDir, "understand-ranges.xml");
			if (!sourceFile.equals(targetFile)) {
				FileSystemUtils.copyFile(sourceFile, targetFile);
			}
		} catch (IOException e) {
			throw new InterruptedException("Understand-Ranges file could not be copied.");
		}

		super.run(monitor);
	}

	/**
	 * Transform the path so that it can be understood by the
	 * GendarmeReportReader.
	 */
	private String getGendarmeDir() {
		return gendarmeDir.replace("\\\\", "\\").replace("\\", "\\\\");
	}
}
