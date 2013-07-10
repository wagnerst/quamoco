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

package de.quamoco.adaptation.view.tasks.actions;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.adaptation.model.status.AdaptationTask;

/**
 * Handles the context menu action "Delete Completed".
 * Retrieves all completed {@link AdaptationTask}s from the view's content provider
 * and asks the user to confirm the deletion - if he does all completed 
 * {@link AdaptationTask}s will be deleted.
 * @author Franz Becker
 */
public class DeleteCompletedHandler extends AbstractViewHandler {

	/** 
	 * Retrieves the {@link #getCompletedTasks(ExecutionEvent) completed} tasks,
	 * asks for a confirmation and then delegates to {@link DeleteHandler#deleteAdaptationTasks(java.util.Collection)}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Get completed tasks
		List<AdaptationTask> completedTasks = getCompletedTasks(event);

		// Ask for confirmation
		if (!completedTasks.isEmpty()) {			
			if (MessageDialog.openConfirm(HandlerUtil.getActiveShell(event), "Delete Completed", getConfirmMessage(completedTasks.size()))) {
				DeleteHandler.deleteAdaptationTasks(completedTasks);
			}
		} else {	// show message that there are no completed tasks to delete
			MessageDialog.openInformation(HandlerUtil.getActiveShell(event), "Delete Completed", "There are no completed tasks to delete.");
		}

		return null;
	}

	/**
	 * Retrieves the completed tasks from the {@link ITreeContentProvider} of the view.
	 * @param event needed to retrieve the view
	 * @return a list of completed {@link AdaptationTask}s.
	 */
	private List<AdaptationTask> getCompletedTasks(ExecutionEvent event) {
		List<AdaptationTask> completedTasks = new LinkedList<AdaptationTask>();
		
		/* Get the tasks from the content provider and the tree viewer's input */
		TreeViewer treeViewer = getView(event).getViewer();
		ITreeContentProvider contentProvider = (ITreeContentProvider) treeViewer.getContentProvider();
		Object[] elements = contentProvider.getElements(treeViewer.getInput());
		
		for (Object object : elements) {
			if (object instanceof AdaptationTask) {
				AdaptationTask task = (AdaptationTask) object;
				if (!task.isIgnoredByUser() && task.isCompleted()) {
					completedTasks.add(task);
				}
			}
		}
		
		return completedTasks;
	}

	
	/**
	 * @param n the number of completed tasks
	 * @return the confirm message in singular or plural depending on n
	 */
	private String getConfirmMessage(int n) {
		if (n == 1) {	// create message either singular or plural
			return "Do you really want to permanently delete the completed task?\n(Ignored tasks will not be removed)";
		} else {
			return "Do you really want to permanently delete the " + n + " completed tasks?\n(Ignored tasks will not be removed)";
		}
	}
	
}
