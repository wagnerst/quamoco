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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;

import edu.tum.cs.emf.commons.validation.FeatureModelConstraintBase;

/**
 * Constraint to identify elements which use "self-descriptive" in the value of
 * a String-typed attribute.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class FeatureValueSelfDescriptiveConstraintBase extends
		FeatureModelConstraintBase {

	/** Constructor. */
	public FeatureValueSelfDescriptiveConstraintBase(EAttribute feature) {
		super(feature);
	}

	/** {@inheritDoc} */
	@Override
	protected IStatus validate(EObject target) {
		String description = (String) target.eGet(getFeature());
		if (description != null && description.contains("self-descriptive")) {
			return createFailureStatus();
		}
		return createSuccessStatus();
	}
}
