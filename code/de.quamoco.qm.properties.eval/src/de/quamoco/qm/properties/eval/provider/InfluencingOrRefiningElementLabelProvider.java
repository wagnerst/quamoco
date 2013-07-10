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

import java.text.DecimalFormat;
import java.util.Locale;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.properties.eval.Activator;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;

public class InfluencingOrRefiningElementLabelProvider extends
		ColumnLabelProvider {

	protected static final DecimalFormat formatter;
	static {
		formatter = (DecimalFormat) DecimalFormat.getInstance(Locale.US);
		formatter.setMaximumFractionDigits(2);
	}
	
	protected final int columnIndex;

	protected final Color lightGray;
	protected final Color darkGrey;

	public static final int COLUMN_RANKING = 0;
	public static final int COLUMN_CONTRIBUTION_POINTS = 1;
	public static final int COLUMN_NAME = 2;
	public static final int COLUMN_TYPE = 3;
	public static final int COLUMN_MODULE = 4;

	protected QualityModel currentQualityModel;

	public void setCurrentQualityModel(QualityModel m) {
		this.currentQualityModel = m;
	}

	public InfluencingOrRefiningElementLabelProvider(int columnIndex) {
		this.columnIndex = columnIndex;

		Display display = Activator.getDefault().getWorkbench().getDisplay();
		lightGray = new Color(display, 220, 220, 220);
		darkGrey = new Color(display, 180, 180, 180);
	}

	@Override
	public String getText(Object object) {
		if (object instanceof InfluencingOrRefiningElement<?>) {
			InfluencingOrRefiningElement<?> element = (InfluencingOrRefiningElement<?>) object;
			switch (columnIndex) {
			case COLUMN_RANKING:
				return Integer.toString(element.getRanking());
			case COLUMN_CONTRIBUTION_POINTS:
				return formatter.format(element.getContPoints());
			case COLUMN_NAME:
				return element.getName();
			case COLUMN_TYPE:
				return element.getType().toString();
			case COLUMN_MODULE:
				return element.getModule();
			default:
				return "";
			}
		}
		return "";
	}

	@Override
	public String getToolTipText(Object object) {
		if (object instanceof InfluencingOrRefiningElement<?>) {
			InfluencingOrRefiningElement<?> element = (InfluencingOrRefiningElement<?>) object;
			if (element.getElement() != null) {
				return element.getElement().getDescription();
			}
		}
		return "";
	}

	@Override
	public Color getBackground(Object object) {
		if (object instanceof InfluencingOrRefiningElement<?>) {
			InfluencingOrRefiningElement<?> element = (InfluencingOrRefiningElement<?>) object;
			if (element.getContPoints() == 0.0d) {
				return lightGray;
			}
		}
		return null;
	}

	@Override
	public Color getForeground(Object object) {
		if (object instanceof InfluencingOrRefiningElement<?>) {
			InfluencingOrRefiningElement<?> element = (InfluencingOrRefiningElement<?>) object;
			Object refElement = element.getElement();
			if (refElement instanceof Factor) {
				Factor f = (Factor) refElement;
				if (this.currentQualityModel == null) {
					if(f.getEvaluatedBy().isEmpty()) {
						return darkGrey;
					}
				} else {
					if (f.getActualEvaluation(this.currentQualityModel) == null) {
						return darkGrey;
					}
				}
			}

		}
		return null;
	}

}
