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

package de.quamoco.qm.search.evaluators;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.ecore.engine.EcoreModelSearchQuery;
import org.eclipse.emf.search.ecore.evaluators.EcoreTextualModelSearchQueryEvaluator;
import org.eclipse.emf.search.ecore.regex.ModelSearchQueryTextualExpressionEnum;
import org.eclipse.emf.search.ecore.regex.ModelSearchQueryTextualExpressionMatchingHelper;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.ETypedElement;

import de.quamoco.qm.search.l10n.Messages;
import de.quamoco.qm.search.util.TextualFeaturesUtils;



public final class ModelTextualModelSearchQueryEvaluator<Q extends IModelSearchQuery, T>
		extends EcoreTextualModelSearchQueryEvaluator<Q, T> {

	@Override
	public List<?> eval(Q query, T target, boolean notification) {
		List<Object> results = new ArrayList<Object>();
		if (query instanceof EcoreModelSearchQuery) {
			ModelSearchQueryTextualExpressionEnum kind = ((EcoreModelSearchQuery) query)
					.getKind();
			String text = query.getQueryExpression();
			text = (text == "" && kind == ModelSearchQueryTextualExpressionEnum.NORMAL_TEXT) ? "*" : text; //$NON-NLS-1$ //$NON-NLS-2$

			// discriminating according to participant meta elements selection
			for (Object o : query.getValidParticipantMetaElements()) {
				// In order to avoid duplicate results, the current element should be contained 
				// by the current resource
				if (target instanceof Resource) {
					if (o instanceof EObject) {
						Resource r = ((EObject) o).eResource();
						if (r instanceof Resource
								&& r.getURI().equals(
										((Resource) target).getURI())) {
							EObject eObj = (EObject) o;
							if (TextualFeaturesUtils.instance()
									.getTextFromEStructuralFeatureIfAny(eObj) != null) {
								for (ETypedElement elem : TextualFeaturesUtils
										.instance()
										.getOwnedETypedElementsFromEObject(eObj)) {
									String elementName = TextualFeaturesUtils
											.instance()
											.getTextFromETypedElement(eObj,
													elem);
									if (elementName != null
											&& ModelSearchQueryTextualExpressionMatchingHelper
													.getInstance().lookAt(
															elementName, text,
															kind)) {
										results.add(query
												.processSearchResultMatching(
														target, eObj,
														notification));
									}
								}
							}
						}
					}
				}
			}
		}
		return results;
	}

	@Override
	public String getLabel() {
		return Messages
				.getString("ModelTextualModelSearchQueryEvaluator.Label"); //$NON-NLS-1$
	}
}
