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

package de.quamoco.adaptation.wizard.util.preselection;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.PreselectionDescriptor;
import de.quamoco.adaptation.model.ReferenceType;
import de.quamoco.qm.AnnotatedElement;

/**
 * A class for efficiently retrieving elements that shall be preselected,
 * according to several {@link PreselectionDescriptor}s.
 * @author Franz Becker
 * @ConQAT.Rating YELLOW Hash: DC6827EC89DCBE0E176F6126DFD24263
 */
// TODO comment
public class PreselectionRetriever {

	private Set<AnnotatedElement> allElements;
	private boolean initialized = false;
	private Map<PreselectionDescriptor, Set<AnnotatedElement>> elementsOfOtherType;
	
	public PreselectionRetriever(AdaptationElement adaptationElement) {
		elementsOfOtherType = new HashMap<PreselectionDescriptor, Set<AnnotatedElement>>();
		for (PreselectionDescriptor preselectionDescriptor : adaptationElement.getPreselectionDescriptors()) {
			elementsOfOtherType.put(preselectionDescriptor, new HashSet<AnnotatedElement>());
		}
	}
	
	private void initializeState() {
		for (PreselectionDescriptor descriptor : elementsOfOtherType.keySet()) {
			if (ReferenceType.INCOMING_REFERENCE.equals(descriptor.getFeatureDescriptor().getReferenceType())) {
				Set<AnnotatedElement> descriptorSet = elementsOfOtherType.get(descriptor);
				EClass otherElementType = descriptor.getFeatureDescriptor().getOtherElementType();
				for (AnnotatedElement element : allElements) {
					if (otherElementType.isSuperTypeOf(element.eClass())) {
						descriptorSet.add(element);
					}
				}
			}			
		}
		initialized = true;
	}

	public void modelChanged(Map<AnnotatedElement, AnnotatedElement> originalToCopyMap) {
		allElements = originalToCopyMap.keySet();
		initialized = false;
		for (Set<AnnotatedElement> set : elementsOfOtherType.values()) {
			set.clear();
		}
	}

	public Set<AnnotatedElement> getPreselectionFor(AnnotatedElement element) {
		return getPreselectionFor(Collections.singleton(element));
	}
	
	public Set<AnnotatedElement> getPreselectionFor(Collection<AnnotatedElement> elements) {
		if (!initialized) { initializeState(); } 
		
		Set<AnnotatedElement> result = new HashSet<AnnotatedElement>();
		for (PreselectionDescriptor descriptor : elementsOfOtherType.keySet()) {		
			EStructuralFeature descriptorFeature = descriptor.getFeatureDescriptor().getFeature();
			Set<AnnotatedElement> descriptorSet = elementsOfOtherType.get(descriptor);
			if (ReferenceType.OUTGOING_REFERENCE.equals(descriptor.getFeatureDescriptor().getReferenceType())) {
				// outgoing reference, straightforward
				for (AnnotatedElement element : elements) {
					result.addAll(getFeatureValue(element, descriptorFeature));
				}				
			} else {
				// incoming reference, use set that includes all elements of the other type
				for (AnnotatedElement elementOfOtherType : descriptorSet) {
					Set<AnnotatedElement> referenceValue = getFeatureValue(elementOfOtherType, descriptorFeature);
					referenceValue.retainAll(elements); // intersection
					if (!referenceValue.isEmpty()) {
						result.add(elementOfOtherType);
					}
				}
			}
		}
		return result;
	}
	
	private Set<AnnotatedElement> getFeatureValue(AnnotatedElement referenceOwner, EStructuralFeature feature) {
		Set<AnnotatedElement> result = new HashSet<AnnotatedElement>();
		Object eGetResult = referenceOwner.eGet(feature);
		if (eGetResult instanceof Collection<?>) {			
			for (Object object : (Collection<?>) eGetResult) {
				if (object instanceof AnnotatedElement) {
					result.add((AnnotatedElement) object);
				}
			}
			return result;
		} else if (eGetResult instanceof AnnotatedElement) {
			result.add((AnnotatedElement) eGetResult);
		}
		return result;
	}

}
