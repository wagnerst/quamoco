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

import org.conqat.engine.commons.node.IRemovableConQATNode;
import org.conqat.engine.commons.util.ConQATInputProcessorBase;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;

@AConQATProcessor(description = "")
public class RadarPlotPreparator extends
		ConQATInputProcessorBase<IRemovableConQATNode> {

	@Override
	public IRemovableConQATNode process() throws ConQATException {
		IRemovableConQATNode[] children = input.getChildren();
		for (IRemovableConQATNode child : children) {
			if (child.getName().equals("Quality @Product []")) {
				children = child.getChildren();
				for (IRemovableConQATNode child2 : children) {
					Double completeness = (Double) child2
							.getValue(EvaluationResultFormatter.COMPLETENESS_KEY);
					if (completeness != null && completeness > 0) {
						String points = (String) child2
								.getValue(EvaluationResultFormatter.EVAL_RESULT_VALUE_KEY);
						if (points.startsWith("[")) {
							points = points.substring(1, points.length() - 1);
						}
						if (points.contains(";")) {
							double lower = Double.valueOf(points.split(";")[0]
									.trim().replace(',', '.'));
							double upper = Double.valueOf(points.split(";")[1]
									.trim().replace(',', '.'));
							child2.setValue("quality",
									Double.valueOf((lower + upper) / 2));
						} else {
							child2.setValue("quality",
									Double.valueOf(points.replace(',', '.')));
						}
					} else {
						child2.remove();
					}

				}
				return child;
			}
		}
		return null;
	}
}
