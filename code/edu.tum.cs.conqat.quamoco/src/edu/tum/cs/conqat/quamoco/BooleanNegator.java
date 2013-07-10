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
@AConQATProcessor(description = "Negates a boolean value.")
public class BooleanNegator extends ConQATProcessorBase {

	/** directory where to store it. */
	private Boolean value;

	/** Set input filename. */
	@AConQATParameter(name = "value", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "boolean value.")
	public void addProjectName(
			@AConQATAttribute(name = "attr", description = "value") Boolean value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean process() {
		return !this.value.booleanValue();
	}

}
