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

package edu.tum.cs.conqat.quamoco;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: heineman $
 * @version $Rev: 3646 $
 * @levd.rating YELLOW Hash: FB032160031FFA5756800A57F4D25DC5
 */
@AConQATProcessor(description = "This processor get a directory and determines the project name.")
public class ProjectNameDeterminer extends ConQATProcessorBase {

	/** directory where to store it. */
	private String dir;


	/** Set input filename. */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the output dir.")
	public void addOutputDir(
			@AConQATAttribute(name = "dir", description = "dir") String dir) {
		this.dir = dir;
	}

	/**
	 * Saves the model.
	 * 
	 * @throws ConQATException
	 */
	@Override
	public String process() throws ConQATException {

		String project = dir;
		if (project.endsWith("/")) {
			project = project.substring(0, project.length() - 1);
		}
		if (project.endsWith("\\")) {
			project = project.substring(0, project.length() - 1);
		}
		
		int i = Math.max(project.lastIndexOf("/"),project.lastIndexOf("\\"));
		if (i != -1) {
			project = project.substring(i + 1);
		}
		return project;
	}
}
