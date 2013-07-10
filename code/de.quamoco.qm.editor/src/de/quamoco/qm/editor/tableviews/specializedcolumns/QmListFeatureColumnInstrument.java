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

package de.quamoco.qm.editor.tableviews.specializedcolumns;

import java.util.List;

import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.MeasureAggregation;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.editor.tableviews.ListTableViewer;
import de.quamoco.qm.editor.tableviews.generalcolumns.EFeatureColumnImage;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmListFeatureColumn;

// TODO (SW): Please document this class.
public class QmListFeatureColumnInstrument extends QmListFeatureColumn {

	/** Constructor. */
	public QmListFeatureColumnInstrument(ListTableViewer parent,
			EStructuralFeature feature, String caption,
			EFeatureColumnImage imageType, EStructuralFeature featureToShow) {
		super(parent, feature, caption, imageType, featureToShow);
	}

	/** {@inheritDoc} */
	@Override
	public String getColumnText(Object element) {
		Object value = ((EObject) element).eGet(feature);

		List<?> values = (List<?>) value;
		if (values.isEmpty()) {
			return StringUtils.EMPTY_STRING;
		}
		Object object = values.get(0);
		if (object instanceof ManualInstrument) {
			return "Manual";
		} else if (object instanceof MeasureAggregation) {
			return "Aggregation";
		} else if (object instanceof ToolBasedInstrument) {
			return "Tool";
		}
		return "-";
	}

}
