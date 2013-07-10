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

package de.quamoco.adaptation.wizard.util.modelattributes.filter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import de.quamoco.adaptation.model.Purpose;
import de.quamoco.adaptation.model.status.QmAdaptationAttributes;
import de.quamoco.adaptation.wizard.util.modelattributes.ModelAttributesComposite;
import de.quamoco.qm.AnnotationBase;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Tag;

/**
 * A {@link ViewerFilter} that filters a {@link TreeViewer} displaying {@link QualityModel}s
 * depending on the attributes set in a {@link ModelAttributesComposite}.
 * @author Franz Becker
 * @levd.rating YELLOW Hash: ACF020B168B693864DE43226164FA2CF
 */
public class AttributesViewerFilter extends ViewerFilter {

	/** The {@link ModelAttributesComposite} that determines the filtering criteria. */
	protected ModelAttributesComposite attributesComposite;

	/**
	 * Calls the super constructor and sets {@link #attributesComposite}.
	 * @param attributesComposite inits {@link #attributesComposite}.
	 */
	public AttributesViewerFilter(ModelAttributesComposite attributesComposite) {
		super();
		this.attributesComposite = attributesComposite;
	}
	
	/**
	 * Returns true for a given {@link QualityModel} iff it fulfills either {@link #isLeafMatch(QualityModel)}
	 * or any of its (subsequent) children.
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof QualityModel) {
			QualityModel qualityModel = (QualityModel) element;
			
			if (isLeafMatch(qualityModel)) {
				return true;
			} else { // check paths below
				TreeViewer treeViewer = (TreeViewer) viewer;
				ITreeContentProvider contentProvider = (ITreeContentProvider) treeViewer.getContentProvider();
				
				// Check if an element in the children tree fulfills the criteria.
				// If yes return true because then element is on the path to this
				// element and the hierarchy shall be maintained
				if (contentProvider.hasChildren(element)) {
					for (Object child : contentProvider.getChildren(element)) {
						if (select(viewer, element, child)) {
							return true;
						}	
					}
				}	
			}
			
		}		
		return false;
	}

	/**
	 * Checks the filter criteria based on the {@link #attributesComposite}. Criteria that are checked:
	 * <ul>
	 * <li>Description: False iff the lower-cased description of the {@link QualityModel} does not contain
	 * the lower-cased description entered by the user.</li>
	 * <li>Object: False iff the {@link QualityModel} does not contain the {@link Entity} selected the user.</li>
	 * <li>Viewpoint: False iff the {@link QualityModel} does not contain the {@link Factor} selected by the user.</li>
	 * <li>Quality Focus: False iff the {@link QualityModel} does not contain the {@link Factor} selected by the user.</li>
	 * <li>Purpose: False iff the {@link QualityModel}'s purpose is not equals the purpose 
	 * selected by the user.</li>
	 * <li>Context: False iff the {@link QualityModel} does not contain all the selected tags.</li>
	 * </ul>
	 * @param qualityModel the {@link QualityModel} of interest.
	 * @return false if any filter criteria is not matched, true otherwise.
	 */
	public boolean isLeafMatch(QualityModel qualityModel) {		
		String name = attributesComposite.getAttributeName();
		String description = attributesComposite.getAttributeDescription();
		String object = attributesComposite.getAttributeObject();
		String viewpoint = attributesComposite.getAttributeViewpoint();
		String qualityFocus = attributesComposite.getAttributeQualityFocus();
		Purpose purpose = attributesComposite.getAttributePurpose();
		Set<String> context = new HashSet<String>(); // copy necessary to work with
		context.addAll(attributesComposite.getAttributeContext());
		boolean filterByName = attributesComposite.isFilterByName();
		boolean filterByDescription = attributesComposite.isFilterByDescription();
		boolean filterByObject = attributesComposite.isFilterByObject();
		boolean filterByViewpoint = attributesComposite.isFilterByViewpoint();
		boolean filterByQualityFocus = attributesComposite.isFilterByQualityFocus();
		boolean filterByPurpose = attributesComposite.isFilterByPurpose();
		boolean filterByContext = attributesComposite.isFilterByContext();
			
		// Check name if set, transform both to lower case
		if (filterByName && name != null && !name.isEmpty()) {
			if (!name.equalsIgnoreCase(qualityModel.getName())) {
				return false;
			}
		}
		
		// Check description if set, transform both to lower case
		if (filterByDescription && description != null && !description.isEmpty()) {
			description = description.toLowerCase();			
			String qmDescription = qualityModel.getDescription();			
			if ((qmDescription == null) || (qmDescription != null) && (!qmDescription.toLowerCase().contains(description))) {
				return false;
			}
		}
		
		// Check object if set
		if (filterByObject && object != null && !object.isEmpty()) {
			boolean entityIncluded = false;
			for (Entity entity : qualityModel.getEntities()) {
				if (object.equals(entity.getName())) {
					entityIncluded = true;
					break;
				}
			}
			if (!entityIncluded) {
				return false;
			}
		}
		
		// Check viewpoint if set
		if (filterByViewpoint && viewpoint != null && !viewpoint.isEmpty()) {
			boolean viewpointIncluded = false;
			for (Factor factor : qualityModel.getFactors()) {
				if (viewpoint.equals(factor.getName())) {
					viewpointIncluded = true;
					break;
				}
			}
			if (!viewpointIncluded) {
				return false;
			}
		}
		
		// Check quality focus if set
		if (filterByQualityFocus && qualityFocus != null && !qualityFocus.isEmpty()) {
			boolean qualityFocusIncluded = false;
			for (Factor factor : qualityModel.getFactors()) {
				if (qualityFocus.equals(factor.getName())) {
					qualityFocusIncluded = true;
					break;
				}
			}
			if (!qualityFocusIncluded) {
				return false;
			}
		}
		
		// Check purpose if set
		if (filterByPurpose && purpose != null) {
			for (AnnotationBase annotationBase : qualityModel.getAdvancedAnnotations()) {
				if (annotationBase instanceof QmAdaptationAttributes) {
//					QmAdaptationAttributes attributes = (QmAdaptationAttributes) annotationBase;
					// TODO what to do here?
				}
			}
//			String qmPurpose = AttributeHandler.getPurpose(qualityModel);
//			if (!purpose.getName().equals(qmPurpose)) {
//				return false;
//			}
		}
		
		// Check context if set
		if (filterByContext && context != null && !context.isEmpty()) {
			for (Tag tag : qualityModel.getTags()) {
				context.remove(tag.getName());
			}
			// If there are still elements in context, the QualityModel does not match
			if (!context.isEmpty()) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * @return true if at least one criteria is set and used for filtering, 
	 * false if the filter is not active (<=> no attribute set)
	 */
	public boolean isActive() {
		String name = attributesComposite.getAttributeName();
		String description = attributesComposite.getAttributeDescription();
		String object = attributesComposite.getAttributeObject();
		String viewpoint = attributesComposite.getAttributeViewpoint();
		String qualityFocus = attributesComposite.getAttributeQualityFocus();
		Purpose purpose = attributesComposite.getAttributePurpose();
		Set<String> context = attributesComposite.getAttributeContext();
		boolean filterByName = attributesComposite.isFilterByName();
		boolean filterByDescription = attributesComposite.isFilterByDescription();
		boolean filterByObject = attributesComposite.isFilterByObject();
		boolean filterByViewpoint = attributesComposite.isFilterByViewpoint();
		boolean filterByQualityFocus = attributesComposite.isFilterByQualityFocus();
		boolean filterByPurpose = attributesComposite.isFilterByPurpose();
		boolean filterByContext = attributesComposite.isFilterByContext();
		
		return filterByName && name != null && !name.isEmpty()
			|| filterByDescription && description != null && !description.isEmpty()
			|| filterByObject && object != null && !object.isEmpty()
			|| filterByViewpoint && viewpoint != null && !viewpoint.isEmpty()
			|| filterByQualityFocus && qualityFocus != null && !qualityFocus.isEmpty()
			|| filterByPurpose && purpose != null 
			|| filterByContext && context != null && !context.isEmpty();
	}
	
}
