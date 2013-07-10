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

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.model.FeatureType;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QualityModel;

/**
 * Implements an item provider for {@link TransientRoot}.
 * @author Franz Becker
 * @levd.rating GREEN Hash: FF15170FA709B26B4C51A9E3BC813837
 */
public class TransientRootItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemColorProvider {

	/** The {@link ElementHierarchy} that shall be used, may be null. */
	protected ElementHierarchy elementHierarchy;
	
	/**
	 * Calls the super constructor and initializes variables.
	 * @param adapterFactory the {@link AdapterFactory} that created this adapter.
	 * @param elementHierarchy the {@link ElementHierarchy} that shall be used, may be null for flat view.
	 */
	public TransientRootItemProvider(AdapterFactory adapterFactory, ElementHierarchy elementHierarchy) {
		super(adapterFactory);
		this.elementHierarchy = elementHierarchy;
	}
	
	/**
	 * Gets all children of the {@link TransientRoot} and, if {@link ElementHierarchy} is not null,
	 * removes all those who have a parent.
	 */
	@Override
	public Collection<?> getChildren(Object object) {
		TransientRoot transientRoot = (TransientRoot) object;
		Collection<AnnotatedElement> children = transientRoot.getReferencedElements();
		
		if (elementHierarchy != null) {
			// remove those children which don't belong to the hierarchy's class
			EClass elementClass = elementHierarchy.getAdaptationElement().getElementClass();
			for (Iterator<AnnotatedElement> iterator = children.iterator(); iterator.hasNext();) {
				AnnotatedElement child = iterator.next();
				if (!(child.eClass() == elementClass)) {
					iterator.remove();
				}				
			}
			
			// remove those children which are not root elements
			FeatureType featureType = elementHierarchy.getFeatureType();
			EReference reference = elementHierarchy.getReference();
			// Call utility methods based on type
			if (featureType.equals(FeatureType.CHILDREN_FEATURE)) {
				removeChildrenFeatureChildren(children, reference);
			} else if (featureType.equals(FeatureType.PARENT_FEATURE)) {
				removeParentFeatureChildren(children, reference);
			}
		}		
	
		return children;
	}

	/**
	 * Iterate over all children of the {@link QualityModel}, read their parent feature and remove
	 * those whose result is not empty.
	 * @param children
	 * @param reference
	 */
	protected void removeParentFeatureChildren(Collection<AnnotatedElement> children, EReference reference) {
		for (Iterator<AnnotatedElement> iterator = children.iterator(); iterator.hasNext();) {
			EObject child = iterator.next();
			Object referenceResult = child.eGet(reference);
			if (referenceResult instanceof List<?>) {
				if (!((List<?>) referenceResult).isEmpty()) {
					iterator.remove();
				}
			} else if (referenceResult != null) {
				iterator.remove();
			}
		}
	}

	/**
	 * Iterate over all children of the {@link QualityModel}, read their children
	 * feature and remove all which occur there. Method is not really optimized for performance.
	 * @param children
	 */
	@SuppressWarnings("unchecked") // list type is EObject for sure
	protected void removeChildrenFeatureChildren(Collection<AnnotatedElement> children, EReference reference) {
		List<AnnotatedElement> childrenToRemove = new LinkedList<AnnotatedElement>();
		for (EObject child : children) {
			Object referenceResult = child.eGet(reference);
			if (referenceResult instanceof List<?>) {
				childrenToRemove.addAll((Collection<? extends AnnotatedElement>) referenceResult);
			} else if (referenceResult instanceof AnnotatedElement) {
				childrenToRemove.add((AnnotatedElement) referenceResult);
			}
		}
	}

	/**
	 * {@link TransientRoot} doesn't have a parent 
	 */
	@Override
	public Object getParent(Object object) {
		return null;
	}

}
