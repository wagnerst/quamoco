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
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.qm.Entity;
import de.quamoco.qm.QmPackage;

/**
 * 
 * @author Franz Becker
 * @generated NOT
 */
// TODO comment
public class AttributesSettingPageObjectHierarchyPropertyDescriptor extends ItemPropertyDescriptor {

	final static boolean isSettable = true;
	final static boolean multiLine = false;
	final static boolean sortChoices = true;
	
	public AttributesSettingPageObjectHierarchyPropertyDescriptor(AdapterFactory adapterFactory, ResourceLocator resourceLocator, String displayName,
										String description, EStructuralFeature feature) {		
		super(adapterFactory, resourceLocator, displayName, description, feature,
				isSettable, multiLine, sortChoices, 
				null, null, null);
	}
	
	/**
	 * Returns the result from the super class minus the {@link ElementHierarchy}
	 * that do not describe hierarchies of {@link Entity entities}
	 */
	@Override
	protected Collection<?> getComboBoxObjects(Object object) {
		Collection<?> result = super.getComboBoxObjects(object);
		
		for (Iterator<?> iterator = result.iterator(); iterator.hasNext();) {
			Object entry = iterator.next();
			if (entry instanceof ElementHierarchy) {
				ElementHierarchy hierarchy = (ElementHierarchy) entry;
				if (!(hierarchy.getReference().getEType() == QmPackage.Literals.ENTITY)) {
					iterator.remove();
				}
			}
		}		
		
		return result;
	}
	
}
