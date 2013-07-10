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
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.CustomLabelProvider;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.util.LabelProviderViewerSorter;
import edu.tum.cs.emf.commons.validation.view.ProblemLabelDecorator;

/**
 * Pane that displays the selection hierarchy.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 28BE67AF2F4769F3D9D1846A06735830
 */
public class SelectionViewerPane extends ViewerPane {

	/** The parent editor. */
	private final CustomQmEditor editor;

	/** The {@link TreeViewer} displaying the selection viewer. */
	private TreeViewer treeViewer;

	/** Constructor. */
	public SelectionViewerPane(CustomQmEditor editor) {
		super(editor.getSite().getPage(), editor);
		this.editor = editor;
		setTitle(editor.getEditingDomain().getResourceSet());
	}

	/** {@inheritDoc} */
	@Override
	public Viewer createViewer(Composite composite) {
		TreeViewer treeViewer = new TreeViewer(composite, SWT.MULTI);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(treeViewer.getTree(),
				"de.quamoco.qm.help.Selection");
		return treeViewer;
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

		createTreeViewer();
	}

	/** Create the {@link TreeViewer} of this pane. */
	private void createTreeViewer() {
		AdapterFactory adapterFactory = editor.getAdapterFactory();
		EditingDomain editingDomain = editor.getEditingDomain();

		treeViewer = getViewer();
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(
				adapterFactory));

		ILabelProvider lp = new AdapterFactoryLabelProvider.ColorProvider(
				adapterFactory, treeViewer);
		lp = new DecoratingLabelProvider(lp, new ProblemLabelDecorator(true));
		CustomLabelProvider clp = new CustomLabelProvider(lp);

		treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(
				clp));
		treeViewer.setInput(editingDomain.getResourceSet());
		treeViewer.setSelection(new StructuredSelection(editingDomain
				.getResourceSet().getResources().get(0)), true);
		treeViewer.setSorter(new LabelProviderViewerSorter(clp, false,
				QmPackage.eINSTANCE.getEntity(), QmPackage.eINSTANCE
						.getFactor(), QmPackage.eINSTANCE.getMeasure(),
				QmPackage.eINSTANCE.getSource(), QmPackage.eINSTANCE.getTag(),
				QmPackage.eINSTANCE.getTool(), QmPackage.eINSTANCE
						.getEvaluation(), QmPackage.eINSTANCE
						.getMeasurementMethod()));

		new AdapterFactoryTreeEditor(treeViewer.getTree(), adapterFactory);
	}

	/** {@inheritDoc} */
	@Override
	public TreeViewer getViewer() {
		return (TreeViewer) super.getViewer();
	}
}
