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

package de.quamoco.qm.editor.pages;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.tableviews.FilteredQmTableViewer;
import de.quamoco.qm.editor.tableviews.generalcolumns.EFeatureColumnImage;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmListFeatureColumn;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmResourceColumn;
import de.quamoco.qm.editor.tableviews.generalcolumns.QmSingleFeatureColumn;

/**
 * Pane that displays the measure list.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 1E7783751E1C67ED13D7B99F6B0DE211
 */
public class MeasureTableViewerPane extends ViewerPane {

	/** The parent editor. */
	private final CustomQmEditor editor;

	/** Constructor. */
	public MeasureTableViewerPane(CustomQmEditor editor) {
		super(editor.getSite().getPage(), editor);
		this.editor = editor;
	}

	/** {@inheritDoc} */
	@Override
	public Viewer createViewer(Composite composite) {
		// set the help system
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
				"de.quamoco.qm.help.MeasureList");

		// create a filtered table viewer for the measures
		return new FilteredQmTableViewer(composite, SWT.FULL_SELECTION,
				QmPackage.eINSTANCE.getMeasure());
	}

	/** {@inheritDoc} */
	@Override
	public void requestActivation() {
		super.requestActivation();
		editor.setCurrentViewerPane(this);
	}

	/** {@inheritDoc} */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		EditingDomain editingDomain = editor.getEditingDomain();
		AdapterFactory adapterFactory = editor.getAdapterFactory();

		// get the filtered table viewer
		FilteredQmTableViewer qmTableViewer = getViewer();

		// initialize it
		qmTableViewer.initialize(editingDomain, adapterFactory);

		createColumns(qmTableViewer);

		// set the input
		qmTableViewer.setInput(editingDomain.getResourceSet());
	}

	/** Create the columns of the table viewer. */
	private void createColumns(FilteredQmTableViewer qmTableViewer) {
		// Name of the Measure
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getNamedElement_Name(), "Name",
				EFeatureColumnImage.PARENT, null);

		// Characterized Entity
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getCharacterizingElement_Characterizes(),
				null, EFeatureColumnImage.NONE, null);

		// Title of the Measure
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getNamedElement_Title(), "Title",
				EFeatureColumnImage.NONE, null);

		// Measures
		new QmListFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getMeasure_Measures(), null,
				EFeatureColumnImage.NONE, null);

		// Show Type
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getMeasure_Type(), "Type",
				EFeatureColumnImage.NONE, null);

		// Show Tags
		new QmListFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getTaggedElement_TaggedBy(), "Tags",
				EFeatureColumnImage.NONE, null);

		// Name of the resource
		new QmResourceColumn(qmTableViewer.getQmTableViewer());
	}

	/** {@inheritDoc} */
	@Override
	public FilteredQmTableViewer getViewer() {
		return (FilteredQmTableViewer) super.getViewer();
	}
}
