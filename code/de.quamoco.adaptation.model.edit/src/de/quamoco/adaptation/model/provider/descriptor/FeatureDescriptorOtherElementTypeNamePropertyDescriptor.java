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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import de.quamoco.adaptation.model.PreselectionDescriptor;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;

/**
 * 
 * @author Franz Becker
 * @generated NOT
 */
// TODO comment
public class FeatureDescriptorOtherElementTypeNamePropertyDescriptor extends ItemPropertyDescriptor {

	final static boolean isSettable = true;
	final static boolean multiLine = false;
	final static boolean sortChoices = true;
	
	public FeatureDescriptorOtherElementTypeNamePropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName,
										String description, EStructuralFeature feature) {		
		super(adapterFactory, resourceLocator, displayName, description, feature,
				isSettable, multiLine, sortChoices, 
				null, null, null);
	}
	
	/**
	 * Returns a list of all {@link EClass}es in the model that are a subtype
	 * of {@link AnnotatedElement}.
	 * 
	 * Used for {@link PreselectionDescriptor}.
	 */
	@Override
	protected Collection<?> getComboBoxObjects(Object object) {
		Collection<String> result = new LinkedList<String>();	
		
		EClass annotatedElement = QmPackage.Literals.ANNOTATED_ELEMENT;
		for (EClassifier eClassifier : QmPackage.eINSTANCE.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;
				if (annotatedElement.isSuperTypeOf(eClass)) {
					result.add(eClass.getName());
				}
			}			
		}
		return result;
	}
	
}
