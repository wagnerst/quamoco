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
import java.util.List;
import java.util.Set;

import org.conqat.lib.commons.collections.CollectionUtils;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.ConstraintStatus;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

/**
 * Checks the following constraints of factor-refinement: 1) [a1 @e] <- [a2@e]
 * 2) [a @e1] <- [a @e2], if is-a(e1, e2)
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ConsistentFactorEntityRefinementConstraint extends
		ElementModelConstraintBase {

	private static final String REFINEMENT_MESSAGE = "The factor is refining \"{0}\", but its entity \"{1}\" is not in a is-a relation with \"{2}\"";

	private static final String DECOMPOSITION_MESSAGE = "The factor is decomposing \"{0}\", but its entity \"{1}\" is not the same as \"{2}\".";

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		Factor factor = (Factor) target;

		List<IStatus> problems = new ArrayList<IStatus>();

		for (Factor superFactor : factor.getRefinesDirect()) {
			problems.add(validateRefinement(factor, superFactor));
			problems.add(validateDecomposition(factor, superFactor));
		}
		return createMultiStatus(problems);
	}

	private IStatus validateRefinement(Factor factor, Factor superFactor) {
		Set<EObject> resultLocus = CollectionUtils.asHashSet(
				QmPackage.Literals.FACTOR__REFINES_DIRECT, superFactor);
		if (isRefinement(factor, superFactor)) {
			Set<Entity> allowedEntities = QmModelUtils
					.getAllSpecializedByEntities(superFactor.getCharacterizes());
			if (superFactor.getCharacterizes().isUseCase()) {
				// for UseCases also entities if a part-of relation are allowed
				allowedEntities
						.addAll(QmModelUtils.getAllConsistsOfEntity(superFactor
								.getCharacterizes()));
			}

			if (factor.getCharacterizes() != null
					&& superFactor.getCharacterizes() != null
					&& !allowedEntities.contains(factor.getCharacterizes())) {

				return ConstraintStatus.createStatus(getContext(), factor,
						resultLocus, REFINEMENT_MESSAGE, superFactor
								.getQualifiedName(), factor.getCharacterizes()
								.getQualifiedName(), superFactor
								.getCharacterizes().getQualifiedName());
			}
		}
		return createSuccessStatus(resultLocus);
	}

	private boolean isRefinement(Factor factor, Factor superFactor) {
		return factor.getName() != null
				&& factor.getName().equals(superFactor.getName());
	}

	private IStatus validateDecomposition(Factor factor, Factor superFactor) {
		Set<EObject> resultLocus = CollectionUtils.asHashSet(
				QmPackage.Literals.FACTOR__REFINES_DIRECT, superFactor);
		if (isDecomposition(factor, superFactor)) {
			if (factor.getCharacterizes() != null
					&& superFactor.getCharacterizes() != null
					&& !factor.getCharacterizes().equals(
							superFactor.getCharacterizes())) {
				return ConstraintStatus.createStatus(getContext(), factor,
						resultLocus, DECOMPOSITION_MESSAGE, superFactor
								.getQualifiedName(), factor.getCharacterizes()
								.getQualifiedName(), superFactor
								.getCharacterizes().getQualifiedName());
			}
		}
		return createSuccessStatus(resultLocus);
	}

	private boolean isDecomposition(Factor factor, Factor superFactor) {
		return factor.getName() != null
				&& !factor.getName().equals(superFactor.getName());
	}

}
