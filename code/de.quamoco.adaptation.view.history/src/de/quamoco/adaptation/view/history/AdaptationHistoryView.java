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

package de.quamoco.adaptation.view.history;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.view.AdaptationFilteredTree;
import de.quamoco.adaptation.view.AdaptationView;
import de.quamoco.adaptation.view.history.action.DeleteAllAction;
import de.quamoco.adaptation.view.history.listener.EditorSelectionChangedListener;
import de.quamoco.adaptation.view.history.provider.HistoryAdapterFactory;

public class AdaptationHistoryView extends AdaptationView {

	/** Selects {@link AdaptationHistoryItem}s in the tree viewer according to the editor's selection. */
	private EditorSelectionChangedListener editorSelectionChangedListener;
	
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
		menuManager.add(new DeleteAllAction(this));
	}
	
	@Override
	protected AdaptationFilteredTree createAdaptationFilteredTree(Composite parent) {
		return new AdaptationHistoryFilteredTree(parent);
	}

	@Override
	protected void initTreeViewer(TreeViewer treeViewer) {
		super.initTreeViewer(treeViewer);
		
		/*
		 * Add content and label provider
		 */
		HistoryAdapterFactory adapterFactory = new HistoryAdapterFactory();
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
	}
	
	@Override
	protected void addListener(TreeViewer treeViewer) {
		super.addListener(treeViewer);
		
//		// Add double click listener that allows to open the affected element in an editor
//		getViewer().addDoubleClickListener(new ViewerDoubleClickListener());
				
		// Add listener for selecting entries in the view if the affected element has been
		// selected in the QmEditor
		editorSelectionChangedListener = new EditorSelectionChangedListener(getViewer(), true);
		getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(editorSelectionChangedListener);
	}

	public void selectItem(AdaptationHistoryItem elementToSelect) {
		StructuredSelection newSelection = new StructuredSelection(elementToSelect);
		getViewer().setSelection(newSelection, true);
	}

}
