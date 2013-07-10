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

package de.quamoco.adaptation.model.provider.descriptor;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;

/**
 * 
 * @author Franz Becker
 * @generated NOT
 */
// TODO comment
public class AdaptationElementReferenceNamePropertyDescriptor extends ItemPropertyDescriptor {

	final static boolean isSettable = true;
	final static boolean multiLine = false;
	final static boolean sortChoices = true;
	
	public AdaptationElementReferenceNamePropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName,
										String description, EStructuralFeature feature) {		
		super(adapterFactory, resourceLocator, displayName, description, feature,
				isSettable, multiLine, sortChoices, 
				null, null, null);
	}
	
	/**
	 * Returns a list of all referenced of {@link QualityModel} that are a subtype of {@link AnnotatedElement}.
	 * 
	 * Used for {@link ElementsSelectionPage}.
	 */
	@Override
	protected Collection<?> getComboBoxObjects(Object object) {
		Collection<String> result = new LinkedList<String>();
		
		EClass qualityModelClass = QmPackage.eINSTANCE.getQualityModel();
		EClass annotatedElementClass = QmPackage.eINSTANCE.getAnnotatedElement();

		/*
		 * Iterate over the reference of the Quality Model EClass
		 * and add all whose type is a subtype of AnnotatedElement
		 */
		for (EReference eReference : qualityModelClass.getEReferences()) {
			EClassifier eClassifier = eReference.getEType();
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				if (annotatedElementClass.isSuperTypeOf(eClass)) {
					result.add(eReference.getName());
				}
			}
		}

		return result;
	}
	
}
