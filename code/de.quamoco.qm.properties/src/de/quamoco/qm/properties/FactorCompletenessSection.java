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

package de.quamoco.qm.properties;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.conqat.ide.commons.ui.ui.TableViewerUtils;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.emf.commons.sections.ElementPropertySectionBase;

/**
 * Shows the accumulated completeness of a factor
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class FactorCompletenessSection extends
		ElementPropertySectionBase<Factor> {

	/** Table viewer to show the completeness for all quality models. */
	protected TableViewer completenessViewer;
	
	private final DecimalFormat numberFormat = new DecimalFormat("00.0");

	/** {@inheritDoc} */
	@Override
	protected void createLabel(Composite composite) {
		createLabel(composite, "Completeness");
	}

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		completenessViewer = new TableViewer(composite, SWT.FULL_SELECTION);

		completenessViewer.setContentProvider(new ArrayContentProvider());
		Table table = completenessViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalIndent = ITabbedPropertyConstants.HSPACE;
		data.verticalIndent = ITabbedPropertyConstants.VSPACE;
		table.setLayoutData(data);

		TableViewerUtils.createColumn(completenessViewer, "Model", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						CompletenessItem measurementResult = (CompletenessItem) element;
						return measurementResult.getModel().getName();
					}
				});

		TableViewerUtils.createColumn(completenessViewer, "Completeness", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						CompletenessItem measurementResult = (CompletenessItem) element;
						return "" + numberFormat.format(100.0*measurementResult.getCompleteness()) + "%";
					}
				});
	}

	/** {@inheritDoc} */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		Factor factor = getElement();
		
		HashMap<QualityModel, Double> completeness = factor.getAccumulatedCompleteness();
	
		List<CompletenessItem> items = new LinkedList<FactorCompletenessSection.CompletenessItem>();
		for(Map.Entry<QualityModel, Double> entry: completeness.entrySet()) {
			items.add(new CompletenessItem(entry.getKey(), entry.getValue()));			
		}
		
		completenessViewer.setInput(items);
	}

	/**
	 * Data data in one line of the table
	 * 
	 * @author lochmann
	 * @author $Author: hummelb $
	 * @version $Rev: 18709 $
	 * @levd.rating RED Rev:
	 */
	private static class CompletenessItem {
		/** The model for which the completeness is shown */
		private QualityModel model;
		
		/** The completeness */
		private Double completeness;

		/**
		 * Constructor
		 * @param model
		 * @param completeness
		 */
		public CompletenessItem(QualityModel model, Double completeness) {
			super();
			this.model = model;
			this.completeness = completeness;
		}

		/**
		 * Getter for model
		 * @return
		 */
		public QualityModel getModel() {
			return model;
		}

		/**
		 * Getter for completeness
		 * @return
		 */
		public Double getCompleteness() {
			return completeness;
		}

	}

}
