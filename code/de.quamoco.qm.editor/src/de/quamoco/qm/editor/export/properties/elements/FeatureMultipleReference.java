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

package de.quamoco.qm.editor.export.properties.elements;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

public class FeatureMultipleReference extends MultipleReference {

	EStructuralFeature feature;

	public FeatureMultipleReference(EStructuralFeature feature) {
		super();
		this.feature = feature;
	}

	@Override
	public List<EObject> getReferencedObjects(EObject eobject) {
		return (List<EObject>) eobject.eGet(feature);
	}
	
	@Override
	public String getName() {
		return ResourceLocatorUtils.getString(feature);
	}
}
