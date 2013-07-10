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

import org.conqat.ide.commons.ui.ui.TableViewerUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.FindingsMeasurementResult;
import edu.tum.cs.emf.commons.sections.ElementPropertySectionBase;

/**
 * Property section for findings.
 * 
 * @author heineman
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class FindingsMeasurementResultPropertySection extends
		ElementPropertySectionBase<FindingsMeasurementResult> {

	/** Table viewer to show the result for different systems. */
	protected TableViewer resultViewer;

	/** {@inheritDoc} */
	@Override
	protected void createLabel(Composite composite) {
		//do nothing
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
		data.grabExcessHorizontalSpace = true;
		table.setLayoutData(data);

		TableViewerUtils.createColumn(resultViewer, "Message", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						return ((FindingMessage) element).getMessage();
					}
				});

		TableViewerUtils.createColumn(resultViewer, "Location", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						return ((FindingMessage) element).getLocation();
					}
				});
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();
		FindingsMeasurementResult result = getElement();
		EList<FindingMessage> findingMessages = result.getFindingMessages();
		resultViewer.setInput(findingMessages);
	}

}
