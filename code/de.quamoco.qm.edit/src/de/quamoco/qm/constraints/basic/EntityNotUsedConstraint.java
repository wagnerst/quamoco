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

import de.quamoco.qm.Entity;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Constraint to identify entities that are not used.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: D7F2A27BE21D33FA4EF05DF4F18ABC51
 */
public class EntityNotUsedConstraint extends ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Entity entity = (Entity) target;
		// non-leafs are always true
		if (!entity.getConsistsOfDirect().isEmpty()
				|| !entity.getSpecializedByDirect().isEmpty()) {
			return createSuccessStatus();
		}
		// check if there are characterizations
		if (entity.getCharacterizedBy().isEmpty()) {
			return createFailureStatus();
		}
		return createSuccessStatus();
	}
}
