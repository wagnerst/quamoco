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

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Constraint to identify factors that have measures but do not have an
 * evaluation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: D8FC00CE313083923AB84D56929B7A44
 */
public class MeasuredFactorNotEvaluatedConstraint extends
		ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Factor factor = (Factor) target;

		List<Measure> measures = factor.getMeasuredByDirect();
		if (measures.isEmpty()) {
			return createSuccessStatus();
		}

		List<Evaluation> evaluations = factor.getEvaluatedBy();
		if (evaluations.isEmpty()) {
			return createFailureStatus();
		}

		return createSuccessStatus();
	}
}
