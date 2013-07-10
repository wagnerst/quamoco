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

import java.text.DateFormat;

import org.conqat.ide.commons.ui.ui.TableViewerUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.Result;
import edu.tum.cs.emf.commons.sections.ElementPropertySectionBase;

/**
 * Base class for property sections that display measurement and evaluation
 * results in a table.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public abstract class ResultPropertySectionBase<E extends EObject> extends
		ElementPropertySectionBase<E> {

	/** Table viewer to show the result for different systems. */
	protected TableViewer resultViewer;

	/** {@inheritDoc} */
	@Override
	protected void createLabel(Composite composite) {
		createLabel(composite, "Result");
	}

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		resultViewer = new TableViewer(composite, SWT.FULL_SELECTION);

		resultViewer.setContentProvider(new ArrayContentProvider());
		Table table = resultViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.horizontalIndent = ITabbedPropertyConstants.HSPACE;
		data.verticalIndent = ITabbedPropertyConstants.VSPACE;
		table.setLayoutData(data);

		TableViewerUtils.createColumn(resultViewer, "System", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {

						Result measurementResult = (Result) element;
						QualityModelResult result = (QualityModelResult) measurementResult
								.eContainer();
						return result.getSystem();
					}
				});

		TableViewerUtils.createColumn(resultViewer, "Date", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						Result measurementResult = (Result) element;
						QualityModelResult result = (QualityModelResult) measurementResult
								.eContainer();
						return DateFormat.getDateInstance().format(
								result.getDate());
					}
				});
	}

	/** {@inheritDoc} */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}
