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

import java.io.File;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;

/**
 * {@ConQAT.Doc}
 * 
 * @author Klaus Lochmann
 * @author $Author: heineman $
 * @version $Rev: 3646 $
 * @levd.rating YELLOW Hash: FB032160031FFA5756800A57F4D25DC5
 */
@AConQATProcessor(description = "Returns true if a file exists, and false, if the file not exists.")
public class FileExistenceCondition extends ConQATProcessorBase {

	/** directory where to store it. */
	private String filename;

	/** Set input filename. */
	@AConQATParameter(name = "filename", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "Local filename.")
	public void addProjectName(
			@AConQATAttribute(name = "attr", description = "filename") String filename) {
		this.filename = filename;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean process() {
		if (this.filename == null) {
			return false;
		}
		File file = new File(filename);
		return file.exists();
	}

}
