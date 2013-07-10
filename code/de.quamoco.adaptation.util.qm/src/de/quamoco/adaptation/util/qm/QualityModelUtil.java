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

package de.quamoco.adaptation.util.qm;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.util.TransientItemProvider;

/**
 * A utility class for {@link QualityModel} specific tasks.
 * @author Franz Becker
 */
public class QualityModelUtil {

	/** The key for the adaptation ID annotation */
	private static final String adaptationID = "adaptedElementUUID";
	
	/**
	 * Creates and sets an adaptation ID for a newly generated element.
	 * @param element the element to annotate
	 */
	public static void createNewAdaptationID(AnnotatedElement element) {
		setAdaptationID(element, EcoreUtil.generateUUID());
	}
	
	/**
	 * Puts the passed ID into the element's annotations.
	 * @param element the element to annotate
	 * @param id the id to annotate
	 */
	public static void setAdaptationID(AnnotatedElement element, String id) {
		element.getAnnotations().put(adaptationID, id);
	}
	
	/**
	 * If the passed element is an {@link AnnotatedElement} this method
	 * returns {@link #getAdaptationID(AnnotatedElement)}, null otherwise.
	 * @param element the element to retrieve the id from
	 * @return null or {@link #getAdaptationID(AnnotatedElement)}
	 */
	public static String getAdaptationID(Object element) {
		if (element instanceof AnnotatedElement) {
			return getAdaptationID((AnnotatedElement) element);
		}
		return null;
	}
	
	/**
	 * Utility method for retrieving IDs from annotations.
	 * @param element the element of interest
	 * @return the ID from the annotations, null if there is none
	 */
	public static String getAdaptationID(AnnotatedElement element) {
		if (element.getAnnotations().containsKey(adaptationID)) {
			return element.getAnnotations().get(adaptationID);
		}
		return null;		
	}
	
	/**
	 * Utility method for retrieving a collection of adaptation IDs out
	 * of a collection of {@link AnnotatedElement}s.
	 * @param elements a collection of {@link AnnotatedElement}
	 * @return a collection of adaptation IDs
	 */
	public static Collection<String> getAdaptationIDs(Collection<? extends AnnotatedElement> elements) {
		Set<String> adaptationIDs = new HashSet<String>(elements.size());
		for (AnnotatedElement annotatedElement : elements) {
			String adaptationID = getAdaptationID(annotatedElement);
			if (adaptationID != null) {
				adaptationIDs.add(adaptationID);
			}
		}
		return adaptationIDs;
	}
	
	// TODO comment
	public static List<EReference> getSuitableQualityModelReferences(EClass supertype) {
		List<EReference> references = new LinkedList<EReference>();
		for (EReference reference : QmPackage.eINSTANCE.getQualityModel().getEReferences()) {
			if (reference.getEType() instanceof EClass) {
				EClass referenceType = (EClass) reference.getEType();
				if (supertype.isSuperTypeOf(referenceType)) {
					references.add(reference);
				}
			}								
		}
		return references;
	}

	/**
	 * If the passed element is a ({@link TransientItemProvider}) it will return 
	 * its target, if not it will return the element itself (if {@link EObject}).
	 * @param element the element that shall be unwrapped
	 * @return an {@link EObject}
	 */
	public static EObject unwrapTransientTarget(Object element) {
		if (element instanceof TransientItemProvider) {
			element = ((TransientItemProvider) element).getTarget();			
		}
		if (element instanceof EObject) {
			return (EObject) element;
		}
		return null;
	}

	/**
	 * Use this utility method whenever a {@link TransientItemProvider} can
	 * occur. If the passed element is one ({@link TransientItemProvider}) it will return the wrapped
	 * children, if not it will return the element included in a collection.
	 * @param element the element that shall be unwrapped
	 * @return an {@link EObject}
	 */
	public static Collection<?> unwrapTransientChildren(Object element) {
		if (element instanceof TransientItemProvider) {
			TransientItemProvider provider = (TransientItemProvider) element;
			return provider.getChildren(provider.getTarget());
		}
		if (element instanceof EObject) {
			return Collections.singleton(element);
		}
		return null;
	}
	
	/**
	 * Recursively collects all {@link AnnotatedElement} starting at a passed object.
	 * @param object a parent object (for each call)
	 * @return a collection of {@link AnnotatedElement} under a passed root.
	 */	
	public static List<AnnotatedElement> getAnnotatedElements(Object object) {
		List<AnnotatedElement> resultList = new LinkedList<AnnotatedElement>();

		if (object instanceof AnnotatedElement) {
			AnnotatedElement element = (AnnotatedElement) object;
			resultList.add(element);
			
			for (EObject eObject : element.eContents()) {				
				resultList.addAll(getAnnotatedElements(eObject));				
			}
		} 	
		
		return resultList;
	}

}
