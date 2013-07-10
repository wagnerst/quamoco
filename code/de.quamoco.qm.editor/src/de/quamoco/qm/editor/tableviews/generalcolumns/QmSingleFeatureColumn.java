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

package de.quamoco.qm.editor.tableviews.generalcolumns;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.editor.tableviews.ListTableViewer;

/**
 * This class realized a table column, that shows an EStructuralElement that has
 * a multiplicity of 1.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 3692E7FC7E689C8191FFB3108F87289B
 */
public class QmSingleFeatureColumn extends QmFeatureColumn {

	/**
	 * The feature that will be shown as the text within the column. If this is
	 * null, than the standard-text using the LabelProvider will be shown.
	 */
	protected EStructuralFeature featureToShow;

	/**
	 * The @param feature will be the feature that is shown in the column. If @param
	 * featureToShow is set, than it will be used as the text for the column; if
	 * it is null, the standard text will be used, using the labelProvider.
	 */
	public QmSingleFeatureColumn(ListTableViewer parent,
			EStructuralFeature feature, String caption,
			EFeatureColumnImage imageType, EStructuralFeature featureToShow) {
		super(parent, feature, caption, imageType);
		this.featureToShow = featureToShow;
	}

	/** {@inheritDoc} */
	@Override
	public Image getColumnImage(Object element) {

		if (imageType == EFeatureColumnImage.NONE) {
			return null;
		} else if (imageType == EFeatureColumnImage.FEATURE) {

			if (this.feature == null) {
				return labelProvider.getImage(element);
			}

			Object o = ((EObject) element).eGet(feature);
			if (o == null) {
				return null;
			}
			return labelProvider.getImage(o);
		} else {
			return labelProvider.getImage(element);
		}
	}

	/** {@inheritDoc} */
	@Override
	public String getColumnText(Object element) {

		if (this.feature == null) {
			return labelProvider.getText(element);
		}

		Object value = ((EObject) element).eGet(feature);

		if (value == null) {
			return null;
		}

		if (featureToShow == null) {
			return labelProvider.getText(value);
		}

		return ((EObject) value).eGet(featureToShow).toString();

	}

	/** {@inheritDoc} */
	@Override
	public String getSortObject(Object element) {
		return getColumnText(element);
	}
}
