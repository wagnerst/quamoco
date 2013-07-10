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

package de.quamoco.qm.properties.eval.provider;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.util.QmModelUtils;

public class NormalizationMeasureProvider extends LabelProvider implements
		IStructuredContentProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof QualityModelElement) {
			return ((QualityModelElement) element).getQualifiedName();
		}
		return "";
	}

	@Override
	public Object[] getElements(Object inputElement) {
		QualityModelElement element = (QualityModelElement) inputElement;
		return QmModelUtils.getNormalizationMeasures(element).toArray();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
