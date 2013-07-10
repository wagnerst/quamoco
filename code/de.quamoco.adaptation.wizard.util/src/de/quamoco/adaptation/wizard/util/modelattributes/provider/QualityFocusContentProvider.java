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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.AttributesSettingPage;
import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.GenericItemProvider;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;

/**
 * Provides the possible quality focuses of one or more {@link QualityModel}(s).
 * It collects factors below the selected viewpoint (below is defined by the hierarchies in the adaptation model)
 * until a certain hierarchy level is reached (also defined by the adaptation model).
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 14117A5BFC763992B9171DA8F4E84767
 */
public class QualityFocusContentProvider extends AbstractContentProvider {

	/** Contains the required information of the {@link AdaptationModel}. */
	private final AttributesSettingPage attributesSettingPage;

	/** The {@link ComboViewer} of the viewpoint selection. */
	private final ComboViewer cbViewpoint;

	/**
	 * Initializes {@link #attributesSettingPage}
	 * @param attributesSettingPage
	 */
	public QualityFocusContentProvider(AttributesSettingPage attributesSettingPage, ComboViewer cbViewpoint) {
		this.attributesSettingPage = attributesSettingPage;
		this.cbViewpoint = cbViewpoint;
	}

	/**
	 * Calls {@link #collectElements(List, QualityModel)} for each {@link QualityModel}.
	 * A final result list is created and passed (for efficiency reasons).
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		List<Factor> factors = new LinkedList<Factor>();

		IStructuredSelection selection = (IStructuredSelection) cbViewpoint.getSelection();
		if (selection.getFirstElement() instanceof String) {
			String viewpoint = (String) selection.getFirstElement();

			// Iterate over quality models and call helper method
			if (inputElement instanceof Collection<?>) {
				for (Object element : (Collection<?>) inputElement) {
					if (element instanceof QualityModel) {
						QualityModel qualityModel = (QualityModel) element;
						Set<Factor> qmResult = new HashSet<Factor>();
						collectElements(qmResult, qualityModel, viewpoint);	
						factors.addAll(qmResult);
					}
				}
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
	 * <li>Search for a factor whose name matches the passed viewpoint.</li>
	 * <li>If such a factor is found, call recursive method to add its children.</li>
	 * </ul>
	 * @param result the result list
	 * @param qualityModel the current {@link QualityModel} of interest
	 * @param viewpoint the viewpoint set by the user
	 */
	protected void collectElements(Set<Factor> result, QualityModel qualityModel, String viewpoint) {
		for (Factor factor : qualityModel.getFactors()) {
			if (viewpoint.equals(factor.getName())) {
				// Factor found => call helper method for each hierarchy
				for (ElementHierarchy elementHierarchy : attributesSettingPage.getQualityFocusHierarchies()) {
					GenericItemProvider genericItemProvider = new GenericItemProvider(null, elementHierarchy);
					Collection<?> factorChildren = genericItemProvider.getChildren(factor);
					collectElementsRecursive(result, genericItemProvider, factorChildren, 1, attributesSettingPage.getQualityFocusHierarchyLevel());
				}
			}
		}
	}
	
	/**
	 * This method checks the passed list of "parents" if they are {@link Factor}s, if yes they are added to the final result list.
	 * Then it is checked if maxLevel is reached, if not the genericItemProvider is queried for the children of the "parent" element
	 * and another recursive call is performed.
	 * @param result the final result list
	 * @param genericItemProvider used to query for more children according to the current hierarchy
	 * @param parents the current parents 
	 * @param currentLevel the hierarchy level on which the current "parents" reside
	 * @param maxLevel the maximum hierarchy level specified by the adaptation model
	 */
	protected void collectElementsRecursive(Set<Factor> result, GenericItemProvider genericItemProvider, Collection<?> parents, int currentLevel, int maxLevel) {		
		for (Object parentElement : parents) {
			if (parentElement instanceof Factor) {
				result.add((Factor) parentElement);				
				if (currentLevel < maxLevel) {
					Collection<?> children = genericItemProvider.getChildren(parentElement);
					collectElementsRecursive(result, genericItemProvider, children, currentLevel+1, maxLevel);
				}				
			}
		}
	}
	
}

