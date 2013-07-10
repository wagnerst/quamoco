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

package de.quamoco.qm.properties;

import java.util.ArrayList;
import java.util.List;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.QmPackage;

/**
 * The section for the feature "specification" of class "Evaluation".
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 27CDDD2FD077674090E17DB491CA61B7
 */
public class QIESLEvaluationSpecificationSection extends
		QIESLSpecificationPropertySectionBase {

	/** Constructor. */
	public QIESLEvaluationSpecificationSection() {
		super(QmPackage.eINSTANCE.getTextEvaluation_Specification());
	}

	/** {@inheritDoc} */
	@Override
	protected List<CharacterizingElement> getMeasuresAndFactors() {
		Evaluation evaluation = (Evaluation) getElement();
		List<CharacterizingElement> elements = new ArrayList<CharacterizingElement>();
		elements.addAll(evaluation.getUsableFactors());
		elements.addAll(evaluation.getUsableMeasures());
		return elements;
	}
}
