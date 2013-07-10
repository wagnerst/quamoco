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

package de.quamoco.adaptation.wizard.util.modelattributes.provider;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.AttributesSettingPage;
import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRoot;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRootItemProvider;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.util.TransientItemProvider;

/**
 * Provides the possible viewpoints of one or more {@link QualityModel}(s).
 * It collects root factors (defined by the hierarchies in the adaptation model) except
 * for those with a cetain name (also specified in the adaptation model).
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 1E6A530A655EA955E410D8BCA845B067
 */
public class ViewpointContentProvider extends AbstractContentProvider {

	/** Contains the required information of the {@link AdaptationModel}. */
	private final AttributesSettingPage attributesSettingPage;

	/**
	 * Initializes {@link #attributesSettingPage}
	 * @param attributesSettingPage
	 */
	public ViewpointContentProvider(AttributesSettingPage attributesSettingPage) {
		this.attributesSettingPage = attributesSettingPage;
	}

	/**
	 * Calls {@link #collectElements(List, QualityModel)} for each {@link QualityModel}.
	 * A final result list is created and passed (for efficiency reasons).<br/><br/>
	 * 
	 * After the collection of elements is finished, the factors that shall be ignored are 
	 * removed from the result list.
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		List<Factor> factors = new LinkedList<Factor>();	

		// Iterate over quality models and call helper method
		if (inputElement instanceof Collection<?>) {
			for (Object element : (Collection<?>) inputElement) {
				if (element instanceof QualityModel) {
					QualityModel qualityModel = (QualityModel) element;
					Set<Factor> qmResult = new HashSet<Factor>();
					collectElements(qmResult, qualityModel);	
					factors.addAll(qmResult);
				}
			}
		} 

		/*
		 * Remove those factors whose name match elements in the viewpointExceptions
		 */
		for (Iterator<Factor> iterator = factors.iterator(); iterator.hasNext();) {
			Factor factor = iterator.next();
			String factorName = factor.getName();			
			if (attributesSettingPage.getViewpointExceptions().contains(factorName)) {
				iterator.remove();
			}
		}		

		/*
		 * Create final output. String is required since multiple elements
		 * with the same name shall only appear once!
		 */
		Set<String> result = new HashSet<String>();
		for (Factor factor : factors) {
			result.add(factor.getName());
		}
		return result.toArray();
	}

	/**
	 * For each hierarchy that is specified the following steps are performed:
	 * <ul>
	 * <li>Find the {@link TransientItemProvider} that describes the "factors" reference (i.e. its type is equals FACTOR}.</li>
	 * <li>Since this {@link TransientItemProvider} is a generic and hierarchical one it can be directly asked for its children.</li>
	 * </ul>
	 * @param result the result set
	 * @param qualityModel the current {@link QualityModel} of interest
	 */
	protected void collectElements(Set<Factor> result, QualityModel qualityModel) {
		for (ElementHierarchy elementHierarchy : attributesSettingPage.getViewpointHierarchies()) {
			TransientRoot transientRoot = new TransientRoot(qualityModel, QmPackage.Literals.QUALITY_MODEL__FACTORS);
			TransientRootItemProvider transientRootItemProvider = new TransientRootItemProvider(null, elementHierarchy);

			Collection<?> rootFactors = transientRootItemProvider.getChildren(transientRoot);
			if (result.isEmpty()) { // add all factors
				for (Object rootFactor : rootFactors) {
					if (rootFactor instanceof Factor) {
						result.add((Factor) rootFactor);
					}
				}		
			} else { // do intersection
				result.retainAll(rootFactors);
			}
		}
	}
	
}
