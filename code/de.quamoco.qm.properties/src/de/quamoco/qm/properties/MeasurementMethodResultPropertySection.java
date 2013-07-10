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

import java.text.NumberFormat;
import java.util.List;

import org.conqat.ide.commons.ui.ui.TableViewerUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditor;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Property section to display the result of a {@link FindingsMeasurementResult}
 * .
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class MeasurementMethodResultPropertySection extends
		ResultPropertySectionBase<MeasurementMethod> {

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		super.createValue(composite);

		MenuManager menuManager = new MenuManager();
		menuManager.add(new Action("Details") {
			@Override
			public void run() {
				IWorkbenchPartSite site = getPart().getSite();
				if (site != null) {
					IWorkbenchPage page = site.getWorkbenchWindow()
							.getActivePage();
					if (page != null) {
						IEditorPart editor = page.getActiveEditor();
						if (editor instanceof CustomQmEditor) {
							ISelection selection = resultViewer.getSelection();
							CustomQmEditor qmEditor = (CustomQmEditor) editor;
							qmEditor.setFocus();
							qmEditor.activateSelectionViewerPane();
							qmEditor.getViewer().setSelection(
									selection, true);
							
						}
					}
				}
			}
		});

		Menu menu = menuManager.createContextMenu(resultViewer.getControl());
		resultViewer.getTable().setMenu(menu);

		TableViewerUtils.createColumn(resultViewer, "Count", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						NumberFormat format = NumberFormat.getNumberInstance();
						if (element instanceof FindingsMeasurementResult) {
							FindingsMeasurementResult result = (FindingsMeasurementResult) element;
							int count = result.getCount();
							return format.format(count);
						} else if (element instanceof NumberMeasurementResult) {
							NumberMeasurementResult result = (NumberMeasurementResult) element;
							DoubleInterval interval = result.getValue();
							double lower = interval.getLower();
							double upper = interval.getUpper();
							if (lower == upper) {
								return "" + lower;
							}
							return "[" + format.format(lower) + ";"
									+ format.format(upper) + "]";
						}
						return "";
					}
				});
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		MeasurementMethod method = getElement();
		List<MeasurementResult> measurementResults = QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getMeasurementResult_ResultsFrom(), method,
				MeasurementResult.class);
		resultViewer.setInput(measurementResults);
	}
}
