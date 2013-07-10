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
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.FulfillmentCriterion;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.adaptation.util.resource.ResourceUtil;

/**
 * Handles the context menu action "Mark Completed".
 * Retrieves the selected {@link AdaptationTask}s and calls sets 
 * their {@link UserMarkedCompleted} criteria to completed.
 * @author Franz Becker
 */
public class MarkCompletedHandler extends AbstractViewHandler {

	/**
	 * Delegates the call to {@link #setUserMarkedCompleted(AdaptationTask, boolean)}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final List<AdaptationTask> selectedTasks = getSelectedTasks(event);
		for (AdaptationTask task : selectedTasks) {
			setUserMarkedCompleted(task, true);
		}
		return null;
	}
	
	public static void setUserMarkedCompleted(AdaptationTask task, boolean value) {
		EditingDomain editingDomain = ResourceUtil.getEditingDomainFor(task);
		if (editingDomain != null) {
			editingDomain.getCommandStack().execute(new SetCompletedCommand(task, value));
		}	
	}
	
	/**
	 * A {@link Command} that marks a passed {@link AdaptationTask} as
	 * completed or not (depending on the passed value).
	 * @author Franz Becker
	 */
	private static class SetCompletedCommand extends AbstractCommand {

		private AdaptationTask task;
		private boolean value;

		public SetCompletedCommand(AdaptationTask task, boolean value) {
			this.task = task;
			this.value = value;
		}
		
		@Override
		public boolean canExecute() {
			return true;
		}

		
		@Override
		public Collection<?> getAffectedObjects() {
			return Collections.singletonList(task);
		}

		@Override
		public void execute() {
			for (FulfillmentCriterion criterion : task.getFulfillmentCriteria()) {
				if (criterion instanceof UserMarkedCompleted) {
					UserMarkedCompleted userMarkedCompleted = (UserMarkedCompleted) criterion;
					userMarkedCompleted.setHasMarkedCompleted(value);
					userMarkedCompleted.setLastUpdate(new Date());
				}
			}
		}

		@Override
		public void redo() {
			execute();
		}

		@Override
		public void undo() {
			for (FulfillmentCriterion criterion : task.getFulfillmentCriteria()) {
				if (criterion instanceof UserMarkedCompleted) {
					UserMarkedCompleted userMarkedCompleted = (UserMarkedCompleted) criterion;
					userMarkedCompleted.setHasMarkedCompleted(!value);
					userMarkedCompleted.setLastUpdate(new Date());
				}
			}
		}
		
	}

}
