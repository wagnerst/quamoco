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

import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.AttributesSettingPage;
import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.GenericItemProvider;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRoot;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRootItemProvider;
import de.quamoco.qm.Entity;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.util.TransientItemProvider;

/**
 * Provides the possible objects a list of {@link QualityModel}s.
 * It collects entities starting from the root elements (defined by the hierarchies
 * in the adaptation model) until a given hierarchy level is reached (also defined
 * by the adaptation model).
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 89F65D29C11B6446DD6B10C7F811EF63
 */
public class ObjectContentProvider extends AbstractContentProvider {

	/** Contains the required information of the {@link AdaptationModel}. */
	private final AttributesSettingPage attributesSettingPage;

	/**
	 * Initializes {@link #attributesSettingPage}
	 * @param attributesSettingPage
	 */
	public ObjectContentProvider(AttributesSettingPage attributesSettingPage) {
		this.attributesSettingPage = attributesSettingPage;
	}

	/**
	 * Calls {@link #collectElements(List, QualityModel)} for each {@link QualityModel}.
	 * A final result list is created and passed (for efficiency reasons).
	 */
	@Override
	public Object[] getElements(Object inputElement) {
		List<Entity> entities = new LinkedList<Entity>();	
		
		// Iterate over quality models and call helper method
		if (inputElement instanceof Collection<?>) {
			for (Object element : (Collection<?>) inputElement) {
				if (element instanceof QualityModel) {
					QualityModel qualityModel = (QualityModel) element;
					Set<Entity> qmResult = new HashSet<Entity>();
					collectElements(qmResult, qualityModel);	
					entities.addAll(qmResult);
				}
			}
		} 
		
		/*
		 * Create final output. String is required since multiple elements
		 * with the same name shall only appear once!
		 */
		Set<String> result = new HashSet<String>();
		for (Entity entity : entities) {
			result.add(entity.getName());
		}
		return result.toArray();
	}
	
	/**
	 * For each hierarchy that is specified the following steps are performed:
	 * <ul>
	 * <li>Find the {@link TransientItemProvider} that describes the "entities" reference (i.e. its type is equals ENTITY}.</li>
	 * <li>Since this {@link TransientItemProvider} is a generic and hierarchical one it can be directly asked for its children
	 * and the result passed to the recursive method {@link #collectElementsRecursive(List, GenericItemProvider, Collection, int, int)}.</li>
	 * </ul>
	 * @param result the result set
	 * @param qualityModel the current {@link QualityModel} of interest
	 */
	protected void collectElements(Set<Entity> result, QualityModel qualityModel) {
		for (ElementHierarchy elementHierarchy : attributesSettingPage.getObjectHierarchies()) {
			TransientRoot transientRoot = new TransientRoot(qualityModel, QmPackage.Literals.QUALITY_MODEL__ENTITIES);
			TransientRootItemProvider transientRootItemProvider = new TransientRootItemProvider(null, elementHierarchy);
			GenericItemProvider genericItemProvider = new GenericItemProvider(null, elementHierarchy);
			Collection<?> rootEntities = transientRootItemProvider.getChildren(transientRoot);
			List<Entity> hierarchyResult = new LinkedList<Entity>();
			collectElementsRecursive(hierarchyResult, genericItemProvider, rootEntities, 1, attributesSettingPage.getObjectHierarchyLevel());
			if (result.isEmpty()) { // add all
				result.addAll(hierarchyResult);
			} else { // perform intersection
				result.retainAll(hierarchyResult);
			}
		}
	}
	
	/**
	 * This method checks the passed list of "parents" if they are {@link Entity}s, if yes they are added to the final result list.
	 * Then it is checked if maxLevel is reached, if not the genericItemProvider is queried for the children of the "parent" element
	 * and another recursive call is performed.
	 * @param result the final result list
	 * @param genericItemProvider used to query for more children according to the current hierarchy
	 * @param parents the current parents 
	 * @param currentLevel the hierarchy level on which the current "parents" reside
	 * @param maxLevel the maximum hierarchy level specified by the adaptation model
	 */
	protected void collectElementsRecursive(List<Entity> result, GenericItemProvider genericItemProvider, Collection<?> parents, int currentLevel, int maxLevel) {		
		for (Object parentElement : parents) {
			if (parentElement instanceof Entity) {
				result.add((Entity) parentElement);				
				if (currentLevel < maxLevel) {
					Collection<?> children = genericItemProvider.getChildren(parentElement);
					collectElementsRecursive(result, genericItemProvider, children, currentLevel+1, maxLevel);
				}				
			}
		}
	}

}
