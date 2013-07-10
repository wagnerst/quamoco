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

import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.tableviews.FactorViewer;

/**
 * Pane that displays the factor list.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 867E983CC448422B0ADDF7ECB570B8F3
 */
public class FactorTableViewerPane extends ViewerPane {

	/** The parent editor. */
	private final CustomQmEditor editor;

	/** Constructor. */
	public FactorTableViewerPane(CustomQmEditor editor) {
		super(editor.getSite().getPage(), editor);
		this.editor = editor;
	}

	/** {@inheritDoc} */
	@Override
	public Viewer createViewer(Composite composite) {
		// set the help system
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite,
				"de.quamoco.qm.help.FactorList");
		
		// create a FactorViewer
		return new FactorViewer(composite, SWT.FULL_SELECTION);
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

		// initialize the viewer
		FactorViewer tableViewer = getViewer();
		tableViewer.initialize(editingDomain, adapterFactory);
		tableViewer.setInput(editingDomain.getResourceSet());
	}

	/** {@inheritDoc} */
	@Override
	public FactorViewer getViewer() {
		return (FactorViewer) super.getViewer();
	}
}
