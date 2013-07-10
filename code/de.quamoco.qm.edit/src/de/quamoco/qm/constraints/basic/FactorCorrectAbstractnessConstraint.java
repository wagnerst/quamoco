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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Factor;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Validates, that concrete factors must not be refined by abstract ones.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class FactorCorrectAbstractnessConstraint extends
		ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Factor factor = (Factor) target;

		if (factor.getEvaluatedBy().isEmpty()) {
			return createSuccessStatus();
		}
		List<IStatus> problems = new ArrayList<IStatus>();
		for (Factor child : factor.getRefinedByDirect()) {
			if (child.getEvaluatedBy().isEmpty()) {
				problems.add(createFailureStatus(child.getQualifiedName(),
						factor.getQualifiedName()));
			}
		}
		return createMultiStatus(problems);
	}
}
