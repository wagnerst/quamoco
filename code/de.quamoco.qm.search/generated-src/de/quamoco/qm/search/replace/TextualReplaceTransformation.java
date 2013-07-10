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

package de.quamoco.qm.search.replace;

import java.util.regex.Pattern;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.core.internal.replace.provisional.AbstractModelSearchTransformation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;

import de.quamoco.qm.search.engine.ModelSearchQuery;
import de.quamoco.qm.search.util.TextualFeaturesUtils;


public class TextualReplaceTransformation
		extends
		AbstractModelSearchTransformation<EObject, IModelSearchQuery, String, String> {

	public TextualReplaceTransformation(EObject element,
			IModelSearchQuery query, String valuation) {
		super(element, query, valuation);
	}

	public boolean isValid() {
		return getModifiedElement() instanceof EObject;
	}

	public IStatus perform() {
		try {
			if (getQuery() instanceof ModelSearchQuery) {
				ModelSearchQuery query = (ModelSearchQuery) getQuery();

				String input = TextualFeaturesUtils.instance()
						.getTextFromEStructuralFeatureIfAny(
								getModifiedElement());
				String expr = getQuery().getQueryExpression();

				if (input != null) {
					EObject eObj = getModifiedElement();
					if (TextualFeaturesUtils.instance()
							.getTextFromEStructuralFeatureIfAny(eObj) != null) {
						for (ETypedElement elem : TextualFeaturesUtils
								.instance().getOwnedETypedElementsFromEObject(
										eObj)) {
							String curValue = TextualFeaturesUtils.instance()
									.getTextFromETypedElement(eObj, elem);

							switch (query.getKind()) {
							case REGULAR_EXPRESSION:
								Pattern pattern = Pattern.compile(expr);

								String newValue = pattern.matcher(curValue)
										.replaceFirst(getValuation());
								TextualFeaturesUtils.instance()
										.setTextForETypedElement(eObj, elem,
												newValue);

								break;
							case CASE_SENSITIVE:
							case NORMAL_TEXT:
								TextualFeaturesUtils.instance()
										.setTextForETypedElement(eObj, elem,
												getValuation());
								break;
							}
						}
					}
				}
			}
		} catch (Throwable t) {
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}

	@Override
	public String getResult() {
		return TextualFeaturesUtils.instance()
				.getTextFromEStructuralFeatureIfAny(
						(EObject) getModifiedElement());
	}
}
