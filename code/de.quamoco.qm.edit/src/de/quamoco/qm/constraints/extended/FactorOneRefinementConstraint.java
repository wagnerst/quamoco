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

import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import edu.tum.cs.emf.commons.validation.FeatureModelConstraintBase;

/**
 * Constraint to identify factors which refine more than one other factors.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: DD084CF5AEDE39584ECC61B69D6727B4
 */
public class FactorOneRefinementConstraint extends FeatureModelConstraintBase {

	/** Constructor. */
	public FactorOneRefinementConstraint() {
		super(QmPackage.Literals.FACTOR__REFINES_DIRECT);
	}

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Factor factor = (Factor) target;

		if (factor.getRefines().size() > 1) {
			return createFailureStatus();
		}
		return createSuccessStatus();
	}
}
