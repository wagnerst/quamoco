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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import de.quamoco.adaptation.model.FeatureDescriptor;
import de.quamoco.adaptation.model.ReferenceType;
import de.quamoco.qm.QualityModel;

/**
 * 
 * @author Franz Becker
 * @generated NOT
 */
// TODO comment
public class FeatureDescriptorFeatureNamePropertyDescriptor extends ItemPropertyDescriptor {

	final static boolean isSettable = true;
	final static boolean multiLine = false;
	final static boolean sortChoices = true;
	
	public FeatureDescriptorFeatureNamePropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName,
										String description, EStructuralFeature feature) {		
		super(adapterFactory, resourceLocator, displayName, description, feature,
				isSettable, multiLine, sortChoices, 
				null, null, null);
	}
	
	/**
	 * Returns a list of names of all {@link EReference} {@link QualityModel} has.
	 * 
	 * Used for {@link FeatureDescriptor}.
	 */
	@Override
	protected Collection<?> getComboBoxObjects(Object object) {	
		List<String> result = new LinkedList<String>();
		if (object instanceof FeatureDescriptor) {
			FeatureDescriptor descriptor = (FeatureDescriptor) object;
			/*
			 * Case distinction by reference type
			 */
			EClass elementClass = descriptor.getElementType();
			if (elementClass != null && descriptor.getOtherElementType() != null) {
				if (ReferenceType.OUTGOING_REFERENCE.equals(descriptor.getReferenceType())) {
					// outgoing
					for (EStructuralFeature feature : elementClass.getEAllStructuralFeatures()) {
						if (feature.getEType() instanceof EClass) {
							EClass featureType = (EClass) feature.getEType();
							if (featureType == descriptor.getOtherElementType()) {
								result.add(feature.getName());
							}
						}
					}
				} else {
					// incoming				
					for (EStructuralFeature feature : descriptor.getOtherElementType().getEAllStructuralFeatures()) {
						if (feature.getEType() instanceof EClass) {
							EClass featureType = (EClass) feature.getEType();
							if (featureType.isSuperTypeOf(elementClass)) {
								result.add(feature.getName());
							}
						}				
					}
				}
			}				
		}
		return result;
	}
	
}
