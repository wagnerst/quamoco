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

package de.quamoco.adaptation.util.qm.compare;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.ecore.xmi.XMIResource;

import de.quamoco.adaptation.util.qm.QualityModelUtil;
import de.quamoco.qm.AnnotatedElement;

/**
 * A copier that enables the comparison of {@link AnnotatedElement}s.
 * It copies the ID of an element into the annotations, that enables {@link DummyXMIResource}
 * to retrieve it from the annotations.
 * @author Franz Becker
 */
public class CompareCopier extends Copier {

	/** just ignore */
	private static final long serialVersionUID = 1L;
	
	/** Maps the original elements to its copies. */
	private Map<AnnotatedElement, AnnotatedElement> originalToCopyMap = new HashMap<AnnotatedElement, AnnotatedElement>();
	
	/**
	 * Calls the super method and checks if the copied element is an {@link AnnotatedElement}.
	 * If yes there are two options:
	 * <ul>
	 * <li>The original element is in a {@link XMIResource}: Get it's ID from the resource.</li>
	 * <li>It's not in a {@link XMIResource}: Try to get the ID from the annotations.</li>	 * 
	 * </ul>
	 * Then assign the ID of the old object to the new object via annotations.
	 */
	@Override
	public EObject copy(EObject eObject) {
		EObject copy = super.copy(eObject);		
		if (eObject instanceof AnnotatedElement) {
			AnnotatedElement copiedElement = (AnnotatedElement) copy;
			AnnotatedElement sourceElement = (AnnotatedElement) eObject;
			originalToCopyMap.put(sourceElement, copiedElement);
			/*
			 * Case distinction 
			 */
			if (sourceElement.eResource() instanceof XMIResource) {
				/*
				 * Get ID from resource (of old element) and put it in annotations (of new element)
				 */
				XMIResource resource = (XMIResource) sourceElement.eResource();
				String id = resource.getID(sourceElement);	
				QualityModelUtil.setAdaptationID(copiedElement, id);
			} else {
				/*
				 * Try to get ID from annotations of old element and put it in
				 * annotations of new element.
				 */
				String id = QualityModelUtil.getAdaptationID(sourceElement);
				if (id != null) {
					QualityModelUtil.setAdaptationID(copiedElement, id);
				}
			}
		} 
		return copy;
	}

	/**
	 * @return the map of original elements to its copies.
	 */
	public Map<AnnotatedElement, AnnotatedElement> getOriginalToCopyMap() {
		return originalToCopyMap;
	}
	
}
