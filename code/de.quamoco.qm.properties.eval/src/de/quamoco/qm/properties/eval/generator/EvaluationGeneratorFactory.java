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

package de.quamoco.qm.properties.eval.generator;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.FormBasedEvaluation;
import de.quamoco.qm.QIESLEvaluation;

/**
 * Abstracts from the creation of {@link EvaluationGenerator}s.
 * 
 * @author Franz Becker
 */
public class EvaluationGeneratorFactory {

	/** Prevent instantiation */
	private EvaluationGeneratorFactory() {
	}

	/**
	 * Returns a new {@link EvaluationGenerator} depending on the type of the
	 * passed {@link Evaluation}.
	 * 
	 * @param evaluation
	 *            the {@link Evaluation} of interest
	 * @return a suitable {@link EvaluationGenerator}
	 */
	public static EvaluationGenerator createEvaluationGenerator(
			Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			return new QIESLGeneratorImp();
		} else if (evaluation instanceof FormBasedEvaluation) {
			return new FormBasedEvalGenImp();
		} else {
			throw new IllegalArgumentException(
					"No generator available for this evaluation type!");
		}
	}
}
