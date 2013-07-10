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

package de.quamoco.qm.properties.eval;

import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;

/**
 * This class is a super class for all enhanced mapping functions that map a
 * measurement results of one measure to a result on the evaluation scale. The
 * implementations provide the type of function form by their class name and the
 * nodes used to implement the concrete function.
 * 
 * @author klaes
 * 
 */
public abstract class EnhancedMappingFunction {

	private EvaluationGenerator.MappingFunction type = null;

	private double[] nodes = null;

	public EnhancedMappingFunction(double[] nodes) {

		this.nodes = nodes;
	}

	public EvaluationGenerator.MappingFunction getType() {

		return this.type;
	}

	public double[] getNodes() {
		return nodes;
	}

}
