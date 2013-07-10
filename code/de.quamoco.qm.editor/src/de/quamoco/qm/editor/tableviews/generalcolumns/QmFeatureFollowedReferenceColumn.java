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

import java.util.List;

import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.editor.tableviews.QmTableViewer;

/**
 * This class realized a table column, that follows two references. This class
 * is needed, because all relations in the model were modeled as classes.
 * Example: If in the table "Factors" are shown, than the impacted Factors have
 * to be determined using two references: Factor->Impact->Factor.
 * 
 * Currently this class is not used, but it may be used in the future.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 1BBF42AD9AA48858D0EC35289FE6421A
 */
public class QmFeatureFollowedReferenceColumn extends QmFeatureColumn {

	/** Constant for a semicolon followed by a space */
	private static final String LIST_LABEL_SEPARATOR = "; ";

	/** Constant for an opening bracket */
	private static final String LIST_LABEL_START = "[";

	/** Constant for a closing bracket */
	private static final String LIST_LABEL_END = "]";

	/**
	 * The second feature to follow
	 */
	protected EStructuralFeature featureToFollow;

	/**
	 * The feature that will be shown as the text within the column. If this is
	 * null, than the standard-text using the LabelProvider will be shown.
	 */
	protected EStructuralFeature featureToShow;

	/**
	 * The @param feature will be the first feature that if followed, @param
	 * featureToFollow will be the second one. If @param featureToShow is set,
	 * than it will be used as the text for the column; if it is null, the
	 * standard text will be used, using the labelProvider.
	 */
	public QmFeatureFollowedReferenceColumn(QmTableViewer parent,
			EStructuralFeature feature, String caption,
			EFeatureColumnImage imageType, EStructuralFeature featureToFollow,
			EStructuralFeature featureToShow) {
		super(parent, feature, caption, imageType);
		this.featureToFollow = featureToFollow;
		this.featureToShow = featureToShow;
	}

	/** {@inheritDoc} */
	@Override
	public Image getColumnImage(Object element) {
		if (imageType == EFeatureColumnImage.NONE) {
			return null;
		} else if (imageType == EFeatureColumnImage.FEATURE) {
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
		Object value = ((EObject) element).eGet(feature);

		List<?> values = (List<?>) value;
		if (values.isEmpty()) {
			return StringUtils.EMPTY_STRING;
		}
		String label = LIST_LABEL_START;
		for (Object object : values) {
			Object followedObject = ((EObject) object).eGet(featureToFollow);
			String toShow = null;
			if (featureToShow == null) {
				toShow = labelProvider.getText(followedObject);
			} else {
				toShow = "" + ((EObject) followedObject).eGet(featureToShow);
			}

			// check whether the object is the last element
			int lastIndex = values.size() - 1;
			if (!(values.get(lastIndex).equals(object))) {
				label += labelProvider.getText(toShow) + LIST_LABEL_SEPARATOR;
			} else {
				label += labelProvider.getText(toShow) + LIST_LABEL_END;
			}
		}
		return label;

	}

	/** {@inheritDoc} */
	@Override
	public String getSortObject(Object element) {
		return getColumnText(element);
	}

}
