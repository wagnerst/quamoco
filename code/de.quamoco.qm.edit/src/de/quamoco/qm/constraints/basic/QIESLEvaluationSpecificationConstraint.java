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

package de.quamoco.qm.constraints.basic;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.util.qiesl.QIESLVerificator;
import edu.tum.cs.emf.commons.validation.FeatureModelConstraintBase;

/**
 * Constraint to identify evaluations whose specification has errors.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 5445AB89C7DB2B8AFD8D18DB083B5E9F
 */
public class QIESLEvaluationSpecificationConstraint extends
		FeatureModelConstraintBase {

	/** Constructor. */
	public QIESLEvaluationSpecificationConstraint() {
		super(QmPackage.Literals.TEXT_EVALUATION__SPECIFICATION);
	}

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		QIESLEvaluation evaluation = (QIESLEvaluation) target;
		String message = QIESLVerificator.verifyQIESL(evaluation);
		if (message != null) {
			return createFailureStatus(message);
		}
		return createSuccessStatus();
	}

}
