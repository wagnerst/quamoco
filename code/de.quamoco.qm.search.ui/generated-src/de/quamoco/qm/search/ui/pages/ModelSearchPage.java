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

package de.quamoco.qm.search.ui.pages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.search.core.internal.replace.provisional.ITransformation;
import org.eclipse.emf.search.core.internal.replace.provisional.NullModelSearchTransformation;
import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.core.results.IModelResultEntry;
import org.eclipse.emf.search.ui.pages.AbstractModelSearchPage;

import de.quamoco.qm.search.replace.TextualReplaceTransformation;
import de.quamoco.qm.search.util.TextualFeaturesUtils;


public final class ModelSearchPage extends AbstractModelSearchPage {

	@Override
	protected String getModelSearchPageID() {
		return "search.ui.pages.QmModelSearchPageID"; //$NON-NLS-1$
	}

	public String getOccurenceLabel(IModelResultEntry entry) {
		return entry.getSource() instanceof EObject ? TextualFeaturesUtils
				.instance().getTextFromEStructuralFeatureIfAny(
						(EObject) entry.getSource()) : "ERROR"; //$NON-NLS-1$
	}

	@Override
	public ITransformation<EObject, IModelSearchQuery, String, String> getTransformation(
			EObject element, IModelSearchQuery query, String value) {
		return TextualFeaturesUtils.instance()
				.getTextFromEStructuralFeatureIfAny(element) != null ? new TextualReplaceTransformation(
				(EObject) element, query, value)
				: new NullModelSearchTransformation();
	}
}
