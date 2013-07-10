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

import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.NormalizationMeasure;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Constraint to identify measures that are not used.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 9004C6E5CF3A0CA9C4BD3406A9274F3E
 */
public class MeasureNotUsedConstraint extends ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Measure measure = (Measure) target;

		if (measure instanceof NormalizationMeasure) {
			return createSuccessStatus();
		}
		List<Factor> factors = measure.getMeasuresDirect();
		List<Measure> refines = measure.getRefinesDirect();
		if (factors.isEmpty() && refines.isEmpty()) {
			return createFailureStatus();
		}
		return createSuccessStatus();
	}
}
