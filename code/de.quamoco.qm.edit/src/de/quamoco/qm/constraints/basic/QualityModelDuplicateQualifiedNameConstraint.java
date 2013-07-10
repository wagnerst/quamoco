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
import java.util.Iterator;
import java.util.List;

import org.conqat.lib.commons.collections.ListMap;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;

/**
 * Constraint to identify quality model elements that have duplicate qualified
 * names.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 85CB273F41F573039E91D517CBC45533
 */
public class QualityModelDuplicateQualifiedNameConstraint extends
		AbstractModelConstraint {

	/** {@inheritDoc} */
	@Override
	public IStatus validate(IValidationContext context) {
		QualityModel qualityModel = (QualityModel) context.getTarget();

		ListMap<String, QualityModelElement> elementsByKey = new ListMap<String, QualityModelElement>();
		for (Iterator<EObject> i = qualityModel.eAllContents(); i.hasNext();) {
			EObject e = i.next();
			if (e instanceof QualityModelElement) {
				QualityModelElement element = (QualityModelElement) e;
				String name = element.getQualifiedName();
				elementsByKey.add(name, element);
			}
		}

		List<IStatus> problems = new ArrayList<IStatus>();
		context
				.addResult(QmPackage.Literals.QUALITY_MODEL_ELEMENT__QUALIFIED_NAME);
		for (String key : elementsByKey.getKeys()) {
			List<QualityModelElement> elements = elementsByKey
					.getCollection(key);
			if (elements.size() > 1) {
				for (QualityModelElement element : elements) {
					IStatus status = ConstraintStatus.createStatus(context,
							element, context.getResultLocus(),
							"Duplicate qualified name.");
					problems.add(status);
				}
			} else {
				IStatus status = ConstraintStatus.createSuccessStatus(context,
						elements.get(0), context.getResultLocus());
				problems.add(status);
			}
		}

		if (problems.isEmpty()) {
			return context.createSuccessStatus();
		}
		return ConstraintStatus.createMultiStatus(context, problems);
	}
}
