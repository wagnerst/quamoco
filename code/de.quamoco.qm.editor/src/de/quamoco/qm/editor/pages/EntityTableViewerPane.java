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
 * Pane that displays the entity list.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 999071F8F78606F4A57BB59FAD2D640E
 */
public class EntityTableViewerPane extends ViewerPane {

	/** The parent editor. */
	private final CustomQmEditor editor;

	/** Constructor. */
	public EntityTableViewerPane(CustomQmEditor editor) {
		super(editor.getSite().getPage(), editor);
		this.editor = editor;
	}

	/** {@inheritDoc} */
	@Override
	public Viewer createViewer(Composite composite) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
				"de.quamoco.qm.help.EntityList");
		return new FilteredQmTableViewer(composite, SWT.FULL_SELECTION,
				QmPackage.eINSTANCE.getEntity());
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

		// Create a filtered table viewer
		FilteredQmTableViewer qmTableViewer = getViewer();

		// initialize it
		qmTableViewer.initialize(editingDomain, adapterFactory);

		createColumns(qmTableViewer);

		// set the input
		qmTableViewer.setInput(editingDomain.getResourceSet());
	}

	/** Create the columns of the table viewer. */
	private void createColumns(FilteredQmTableViewer qmTableViewer) {
		// Name of the Entity
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getNamedElement_Name(), null,
				EFeatureColumnImage.PARENT, null);

		// Name of the resource
		new QmResourceColumn(qmTableViewer.getQmTableViewer());

		// Show Tags
		new QmListFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getTaggedElement_TaggedBy(), "Tags",
				EFeatureColumnImage.NONE, null);

		// Super Types
		new QmListFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getEntity_IsADirect(), null,
				EFeatureColumnImage.NONE, QmPackage.eINSTANCE
						.getNamedElement_Name());

		// Part-of
		new QmSingleFeatureColumn(qmTableViewer.getQmTableViewer(),
				QmPackage.eINSTANCE.getEntity_PartOfDirect(), null,
				EFeatureColumnImage.NONE, QmPackage.eINSTANCE
						.getNamedElement_Name());
	}

	/** {@inheritDoc} */
	@Override
	public FilteredQmTableViewer getViewer() {
		return (FilteredQmTableViewer) super.getViewer();
	}
}