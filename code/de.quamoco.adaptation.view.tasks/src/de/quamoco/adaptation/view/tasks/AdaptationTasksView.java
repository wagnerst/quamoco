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

package de.quamoco.adaptation.view.tasks;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.State;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.RegistryToggleState;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.view.AdaptationFilteredTree;
import de.quamoco.adaptation.view.AdaptationView;
import de.quamoco.adaptation.view.tasks.filter.CompletedFilter;
import de.quamoco.adaptation.view.tasks.listener.EditorSelectionChangedListener;
import de.quamoco.adaptation.view.tasks.listener.ViewerDoubleClickListener;
import de.quamoco.adaptation.view.tasks.provider.TaskAdapterFactory;

/**
 * Provides a view for handling adaptation tasks.
 * @author Franz Becker
 */
public class AdaptationTasksView extends AdaptationView {
	
	/** The filter that can filter out completed {@link AdaptationTask}s if activated. */
	private CompletedFilter completedFilter;
	
	/*
	 * Listener which must be removed upon dispose()
	 */
	
	/** Selects {@link AdaptationTask}s in the tree viewer according to the editor's selection. */
	private EditorSelectionChangedListener editorSelectionChangedListener;

	/**
	 * Creates an {@link AdaptationTasksFilteredTree} and returns it.
	 */
	@Override
	protected AdaptationFilteredTree createAdaptationFilteredTree(Composite parent) {
		return new AdaptationTasksFilteredTree(parent);
	}

	/**
	 * Initializes the {@link #treeViewer}.
	 */
	@Override
	protected void initTreeViewer(TreeViewer treeViewer) {		
		super.initTreeViewer(treeViewer);
		
		/*
		 * Add content and label provider
		 */
		TaskAdapterFactory adapterFactory = new TaskAdapterFactory();
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		
		/*
		 * Filtering of completed tasks. Default is that completed ones are filtered out.
		 * => toggle button not active => toggle status = false && filter.enabled = true
		 * 
		 * Create new filter, add it to the view and restore toggle status that is saved
		 * between various sessions.
		 */
		completedFilter = new CompletedFilter(treeViewer);
		treeViewer.addFilter(completedFilter);
		boolean enableFilter = !getShowCompletedToggleStatus();
		completedFilter.setEnabled(enableFilter);		
	}	
	
	@Override
	protected void addListener(TreeViewer treeViewer) {
		super.addListener(treeViewer);
	
		// Add double click listener that allows to open the affected element in an editor
		getViewer().addDoubleClickListener(new ViewerDoubleClickListener());
				
		// Add listener for selecting entries in the view if the affected element has been
		// selected in the QmEditor
		editorSelectionChangedListener = new EditorSelectionChangedListener(getViewer(), true);
		getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(editorSelectionChangedListener);
	}
	
	/**
	 * Retrieves the toggle status from the show completed command.
	 * @return the toggle status of the show completed command.
	 */
	private boolean getShowCompletedToggleStatus() {
		ICommandService cmdService = (ICommandService) getSite().getService(ICommandService.class);
		Command command = cmdService.getCommand("de.quamoco.adaptation.view.AdaptationTaskView.toggleShowCompletedCommand");
		State toggleState = command.getState(RegistryToggleState.STATE_ID);
		return (Boolean) toggleState.getValue();
	}
	
	/**
	 * Method required for action handler.
	 * @return the {@link CompletedFilter} of this view.
	 */
	public CompletedFilter getCompletedFilter() {
		return completedFilter;
	}	
	
	/**
	 * Performs cleanup.
	 */
	@Override
	public void dispose() {
		/** Releases the registered listener */
		getSite().getWorkbenchWindow().getSelectionService().removePostSelectionListener(editorSelectionChangedListener);
		
		super.dispose();
	}
	
}
