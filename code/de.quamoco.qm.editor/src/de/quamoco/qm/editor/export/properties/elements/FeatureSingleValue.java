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

import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

public class FeatureSingleValue extends SingleValue {

	private EStructuralFeature attribute;

	public FeatureSingleValue(EStructuralFeature attribute) {
		super();
		this.attribute = attribute;
	}
	


	@Override
	public String getValue(EObject eobject) {
		Object value = eobject.eGet(attribute);
		if (value == null) {
			return "";
		}

		if (value instanceof String) {
			value = StringUtils.replaceLineBreaks((String) value, "<br/>");
		} else if(value instanceof Double) {
			Double d = (Double) value;
			value = nf.format(d);
		}
		return String.valueOf(value);

	}

	@Override
	public String getName() {
		return ResourceLocatorUtils.getString(attribute);
	}

}
