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

package de.quamoco.qm.constraints.extended;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Impact;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Create a warning for impacts that target a factor that has sub-factors.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ImpactTargetsLeafConstraint extends ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Impact impact = (Impact) target;
		if (impact.getTarget() != null) {
			if (!impact.getTarget().getRefinedBy().isEmpty()) {
				return createFailureStatus();
			}
		}
		return createSuccessStatus();
	}

}
