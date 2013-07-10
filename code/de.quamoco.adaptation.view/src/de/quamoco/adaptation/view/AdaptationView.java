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

package de.quamoco.adaptation.view;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.ViewPart;

import de.quamoco.adaptation.view.listener.PartListener;
import de.quamoco.qm.presentation.QmEditor;

/**
 * Provides an abstract view for adaptation views.
 * @author Franz Becker
 */
public abstract class AdaptationView extends ViewPart {
	
	/** The {@link SelfRefreshingTreeViewer} of this view. */
	private SelfRefreshingTreeViewer treeViewer;	
	
	/*
	 * Listener which must be removed upon dispose()
	 */

	/** Implements the link to editor functionality. */
	private PartListener partListener;			

	/**
	 * Creates the {@link AdaptationTasksFilteredTree}, initializes it and adds
	 * all required listeners.
	 */
	@Override
	public void createPartControl(Composite parent) {
		AdaptationFilteredTree filteredTree = createAdaptationFilteredTree(parent);
		treeViewer = (SelfRefreshingTreeViewer) filteredTree.getViewer();
		
		initTreeViewer(treeViewer);
		addListener(treeViewer);	
		
		if (treeViewer.getContentProvider() != null) {
			IEditorPart editor = getSite().getPage().getActiveEditor();
			if (editor instanceof QmEditor) {
				partListener.setCurrentEditor((QmEditor) editor);
			}
		}
	}

	/**
	 * Has to be implemented by subclasses, creates and returns an instance of
	 * {@link AdaptationFilteredTree} that shall be used for this view.
	 * @param parent the parent composite
	 * @return the instance of {@link AdaptationFilteredTree}.
	 */
	protected abstract AdaptationFilteredTree createAdaptationFilteredTree(Composite parent);

	/**
	 * Sets the selection provider of the view's page and registers
	 * the context menu.<br>
	 * Subclasses are supposed to extend this method. 
	 */
	protected void initTreeViewer(TreeViewer treeViewer) {
		getSite().setSelectionProvider(treeViewer);
		registerContextMenu();
	}
	
	/**
	 * Adds a {@link PartListener} for the "link to editor" functionality.<br>
	 * Subclasses are supposed to extend this method.
	 */
	protected void addListener(TreeViewer treeViewer) {		
		// Add part listener for linkage to currently active editor
		partListener = new PartListener(treeViewer);
		getSite().getPage().addPartListener(partListener);		
	}

	/**
	 * Registers the view's context menu to the viewer, otherwise it wouldn't be shown.
	 */
	protected void registerContextMenu() {
		MenuManager contextMenu = new MenuManager();
		contextMenu.setRemoveAllWhenShown(true);
		getSite().registerContextMenu(contextMenu, treeViewer);		
		Control control = treeViewer.getControl();
		Menu menu = contextMenu.createContextMenu(control);
		control.setMenu(menu);
	}
	
	/**
	 * Method required for action handler.
	 * @return the {@link TreeViewer} of this view.
	 */
	public TreeViewer getViewer() {
		return treeViewer;
	}
	
	/**
	 * Called when the view gets the focus 
	 * => pass it to viewer.
	 */
	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}
	
	/**
	 * Performs cleanup.<br>
	 * Subclasses are supposed to extend this method and remove
	 * their listeners.
	 */
	@Override
	public void dispose() {
		/** Releases the registered listener */
		getSite().getPage().removePartListener(partListener);
		
		treeViewer.dispose();
		super.dispose();
	}

}