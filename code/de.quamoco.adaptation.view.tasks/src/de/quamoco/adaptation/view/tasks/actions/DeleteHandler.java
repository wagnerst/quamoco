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

import java.util.Collection;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.util.resource.ResourceUtil;

/**
 * Handles the context menu action "Delete".
 * Retrieves all selected {@link AdaptationTask}s and asks the user to confirm
 * the deletion before performing it.
 * @author Franz Becker
 */
public class DeleteHandler extends AbstractViewHandler {

	/**
	 * Ask for confirmation and then delegate to {@link #deleteAdaptationTasks(Collection)}.
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final List<AdaptationTask> selectedTasks = getSelectedTasks(event);		
		
		// confirm deletion by user
		if (selectedTasks.size() > 0) {
			if (MessageDialog.openConfirm(HandlerUtil.getActiveShell(event), "Delete", getConfirmMessage(selectedTasks.size()))) {				
				deleteAdaptationTasks(selectedTasks);
			}
		}		
		return null;
	}		
	
	/**
	 * @param n the number of tasks to delete
	 * @return the confirm message in singular or plural depending on n
	 */
	private String getConfirmMessage(int n) {
		if (n == 1) {
			return "Do you really want to delete the selected task?";
		} else {
			return "Do you really want to delete the " + n + " selected tasks?";
		}
	}
	
	/**
	 * Deletes the passed {@link AdaptationTask}s from their model by executing a {@link RemoveCommand}
	 * on their respective editing domain.
	 * @param tasksToDelete a collection of {@link AdaptationTask} that shall be deleted
	 * @see ResourceUtil#getEditingDomainFor(org.eclipse.emf.ecore.EObject)
	 * @see RemoveCommand
	 */
	public static void deleteAdaptationTasks(Collection<AdaptationTask> tasksToDelete) {
		for (AdaptationTask task : tasksToDelete) {	// tasks may belong to different editing domains
			EditingDomain editingDomain = ResourceUtil.getEditingDomainFor(task);
			if (editingDomain != null) {	
				Command removeCommand = new RemoveCommand(editingDomain, task.eContainer(), task.eContainingFeature(), task);
				editingDomain.getCommandStack().execute(removeCommand);
			}					
		}
	}

}
