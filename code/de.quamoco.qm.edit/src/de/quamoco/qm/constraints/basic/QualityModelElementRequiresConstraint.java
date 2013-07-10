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
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Constraint to identify quality model elements that refer to other quality
 * model elements which are in modules not available through the requires
 * relation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 04625AC91729573FEB7957162EBCD9CB
 */
public class QualityModelElementRequiresConstraint extends
		AbstractModelConstraint {

	/** {@inheritDoc} */
	@Override
	public IStatus validate(IValidationContext context) {
		QualityModelElement element = (QualityModelElement) context.getTarget();

		List<IStatus> problems = new ArrayList<IStatus>();
		for (EReference reference : element.eClass().getEAllReferences()) {
			if (reference.isContainer() || reference.isContainment()) {
				continue;
			}
			problems.add(validate(context, element, reference));
		}

		return ConstraintStatus.createMultiStatus(context, problems);
	}

	/**
	 * Check whether a reference from a certain element violates the requires
	 * relation.
	 */
	@SuppressWarnings("unchecked")
	private IStatus validate(IValidationContext context,
			QualityModelElement element, EReference reference) {

		if (reference.isMany()) {
			List<QualityModelElement> values = (List<QualityModelElement>) element
					.eGet(reference);
			for (QualityModelElement value : values) {
				if (!QmModelUtils.requires(element, value)) {
					return createFailureStatus(context, reference, value);
				}
			}
		} else {
			QualityModelElement value = (QualityModelElement) element
					.eGet(reference);
			if (value != null && !QmModelUtils.requires(element, value)) {
				return createFailureStatus(context, reference, value);
			}
		}

		List<EReference> resultLocus = Collections.singletonList(reference);
		return ConstraintStatus.createSuccessStatus(context, element,
				resultLocus);
	}

	/** Create a failure status that is specific for this constraint. */
	private IStatus createFailureStatus(IValidationContext context,
			EReference reference, QualityModelElement value) {
		List<EReference> resultLocus = Collections.singletonList(reference);
		String message = context.createFailureStatus(reference.getName(),
				value.getQualifiedName()).getMessage();
		return ConstraintStatus.createStatus(context, resultLocus, message);
	}
}
