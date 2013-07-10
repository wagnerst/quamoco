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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Checks if the entity of a measure is in an is-a relation to the entity of the
 * factor it measures.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class MeasureWrongEntityConstraint extends ElementModelConstraintBase {

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Measure measure = (Measure) target;
		List<IStatus> problems = new ArrayList<IStatus>();

		Entity measuresEntity = measure.getCharacterizes();
		for (Factor superFactorBase : measure.getMeasuresDirect()) {
			Entity superEntity = superFactorBase.getCharacterizes();

			if (superEntity != null) {

				// the superEntity itself, and all sub-entity of it are allowed
				Set<Entity> allAllowedEntities = QmModelUtils
						.getAllSpecializedByEntities(superEntity);
				allAllowedEntities.add(superEntity);

				if (measuresEntity != null
						&& !allAllowedEntities.contains(measuresEntity)) {
					problems.add(createFailureStatus(
							Arrays.asList(superFactorBase),
							superFactorBase.getQualifiedName()));
				}
			}

		}

		return createMultiStatus(problems);
	}

}
