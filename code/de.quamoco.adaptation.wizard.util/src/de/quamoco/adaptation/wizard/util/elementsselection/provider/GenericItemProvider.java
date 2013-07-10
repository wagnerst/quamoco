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

package de.quamoco.adaptation.wizard.util.elementsselection.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.model.FeatureType;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.AnnotatedElementItemProvider;

/**
 * Generic item provider that returns the children of an {@link AnnotatedElement}
 * based on a {@link ElementHierarchy} defined by the adaptation model.<br>
 * <br>
 * The {@link ElementHierarchy} describes the hierarchy by defining an {@link EReference}
 * and a type indicating whether it is a "children" or "parent" reference.<br>
 * If no such {@link ElementHierarchy} is passed the element simply does not have any children nor
 * does it have a parent.<br>
 * <br>
 * A children reference goes from a parent to its children, a parent reference goes from
 * a child to its parent (hence, more effort to obtain the children of an element).
 * 
 * @author Franz Becker
 * @levd.rating GREEN Hash: 3FD2E660439DE98424996E63C20D3EB3
 */
public class GenericItemProvider extends AnnotatedElementItemProvider {

	/** The {@link ElementHierarchy} that shall be used, may be null. */
	protected ElementHierarchy elementHierarchy;
	
	/** Used for obtaining children when the {@link ElementHierarchy} type is "parent hierarchy" in<br>
	 *  {@link #getParentFeatureChildren(AnnotatedElement)}. */
	protected EReference qualityModelEReference;

	/**
	 * Calls the super constructor and initializes instance variables.
	 * @param adapterFactory the {@link AdapterFactory} that creates this adapter
	 * @param elementHierarchy the {@link ElementHierarchy} that shall be used, may be null.
	 */
	public GenericItemProvider(AdapterFactory adapterFactory, ElementHierarchy elementHierarchy) {
		super(adapterFactory);
		this.elementHierarchy = elementHierarchy;		
		
		if (elementHierarchy != null) {
			// determine qualityModelEReference
			EClass elementClass = elementHierarchy.getAdaptationElement().getElementClass();
			EClass qualityModelClass = QmPackage.eINSTANCE.getQualityModel();		
			for (EReference eReference : qualityModelClass.getEReferences()) {
				if (eReference.getEType() == elementClass) {
					qualityModelEReference = eReference;
					break;
				}
			}
			if (qualityModelEReference == null) {
				throw new IllegalArgumentException("Something went wrong, check your adaptation model (EntitySelectionPage -> Hierarchy relation and attributes)!");
			}
		}
	}

	/**
	 * Calculates the children of an element based on the {@link ElementHierarchy}
	 * that is set. If the hierarchy is null the element simply doesn't have any children.
	 */
	@Override
	public Collection<?> getChildren(Object object) {
		if (elementHierarchy != null) {
			AnnotatedElement parent = (AnnotatedElement) object;	
			FeatureType featureType = elementHierarchy.getFeatureType();
			
			// Call utility method based on type
			if (featureType.equals(FeatureType.PARENT_FEATURE)) {
				return getParentFeatureChildren(parent);
			} else if (featureType.equals(FeatureType.CHILDREN_FEATURE)) {
				return getChildrenFeatureChildren(parent);
			} 
		}		
		return Collections.EMPTY_LIST;
	}

	/**
	 * This case is pretty straight forward. Take the reference that describes the
	 * children feature and use the eGet method to get all associated elements 
	 * (which are children). If the result is already a list return it, if not
	 * pack the (single) element into a list.
	 * @param parent
	 * @return
	 */
	protected Collection<?> getChildrenFeatureChildren(AnnotatedElement parent) {
		EReference childrenReference = elementHierarchy.getReference();
		
		Object childrenReferenceResult = parent.eGet(childrenReference);
		if (childrenReferenceResult instanceof Collection<?>) {
			return (Collection<?>) childrenReferenceResult;
		}

		return Collections.singleton(childrenReferenceResult);
	}

	/**
	 * This case is a bit more complicated. The reference determining the hierarchy
	 * determines the parent and not the children directly. Hence, all elements under
	 * the {@link QualityModel} need to be searched. An element is a 
	 * children when the "parent reference" references the possibleParent (the element
	 * for which currently the children shall be determined).
	 * @param possibleParent
	 * @return
	 */
	@SuppressWarnings("unchecked") // list type is EObject for sure
	protected Collection<?> getParentFeatureChildren(AnnotatedElement possibleParent) {
		Collection<Object> children = new ArrayList<Object>();
		
		QualityModel qualityModel = possibleParent.getQualityModel();
		EReference parentReference = elementHierarchy.getReference();
		
		// Get all possible elements by asking the QualityModel for it
		Object qualityModelEGetResult = qualityModel.eGet(qualityModelEReference);
		if (qualityModelEGetResult instanceof List<?>) {
			for (EObject possibleChildren : ((List<EObject>) qualityModelEGetResult)) {
				Object parentReferenceResult = possibleChildren.eGet(parentReference);
				/*
				 * Now the result can either be a list (then it is not a strict hierarchy
				 * but has multiple parents) or the result is a single element (strict hierarchy).
				 */
				if (parentReferenceResult == possibleParent) {
					children.add(possibleChildren);
				} else  if (parentReferenceResult instanceof List<?>) {
					if (((List<?>) parentReferenceResult).contains(possibleParent)) {
						children.add(possibleChildren);
					}
				}
			}
		} // else would mean that the QualityModel has only 1 element of this type
		  // => no hierarchy anyway		
	
		return children;
	}

	/**
	 * Calculates the parent of an element based on the {@link ElementHierarchy}
	 * that is set. If the hierarchy is null the element simply doesn't have a parent.
	 */
	@Override
	public Object getParent(Object object) {
		AnnotatedElement child = (AnnotatedElement) object;	
		if (elementHierarchy != null) {
			FeatureType featureType = elementHierarchy.getFeatureType();
			
			// Call utility method based on type
			if (featureType.equals(FeatureType.PARENT_FEATURE)) {
				return getParentFeatureParent(child);
			} else if (featureType.equals(FeatureType.CHILDREN_FEATURE)) {
				return getChildrenFeatureParent(child);
			} 
		}		
		return null; // no hierarchy, no parent
	}

	/**
	 * The type of the {@link ElementHierarchy} is children feature, i.e.
	 * all elements need to be searched for the element that references the
	 * child element.
	 * @param child the element whose parent shall be returned
	 * @return the parent of the element, may be null if none
	 */
	@SuppressWarnings("unchecked")
	protected AnnotatedElement getChildrenFeatureParent(AnnotatedElement child) {
		QualityModel qualityModel = child.getQualityModel();
		EReference childrenReference = elementHierarchy.getReference();
		
		// Get all possible elements by asking the QualityModel for it
		Object qualityModelEGetResult = qualityModel.eGet(qualityModelEReference);
		if (qualityModelEGetResult instanceof List<?>) {
			List<AnnotatedElement> qualityModelElements = (List<AnnotatedElement>) qualityModelEGetResult;

			for (AnnotatedElement possibleParent : qualityModelElements) {
				if (possibleParent != child) {
					Object childReferenceResult = possibleParent.eGet(childrenReference);
					/*
					 * Now the result can either be a list (child has siblings) or the result is a 
					 * single element).
					 */
					if (childReferenceResult == child) {
						return possibleParent;
					} else  if (childReferenceResult instanceof List<?>) {
						if (((List<?>) childReferenceResult).contains(child)) {
							return possibleParent;
						}
					}
				}				
			}
		} // else would mean that the QualityModel has only 1 element of this type
		  // => no hierarchy anyway
		return null; // no parent found
	}

	/**
	 * The type of the {@link ElementHierarchy} is parent feature, i.e.
	 * the element referenced by the {@link ElementHierarchy}'s reference is the parent.
	 * @param child the element whose parent shall be returned
	 * @return the parent of the element, may be null if none
	 */
	protected AnnotatedElement getParentFeatureParent(AnnotatedElement child) {
		EReference parentReference = elementHierarchy.getReference();		
		Object eGetResult = child.eGet(parentReference, true);
		if (eGetResult instanceof AnnotatedElement) {
			return (AnnotatedElement) eGetResult;
		} else  if (eGetResult instanceof List<?>) {
			List<?> resultList = (List<?>) eGetResult;
			if (resultList.size() > 0) {
				return (AnnotatedElement) resultList.get(0);
			}
		}
		return null;
	}	
	
}
