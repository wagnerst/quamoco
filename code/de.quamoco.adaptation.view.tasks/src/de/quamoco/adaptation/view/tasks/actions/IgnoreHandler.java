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

import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.model.status.StatusPackage;
import de.quamoco.adaptation.model.status.UserMarkedCompleted;
import de.quamoco.adaptation.util.resource.ResourceUtil;

/**
 * Handles the context menu action "Mark Completed".
 * Retrieves the selected {@link AdaptationTask}s and calls sets 
 * their {@link UserMarkedCompleted} criteria to completed.
 * @author Franz Becker
 */
public class IgnoreHandler extends AbstractViewHandler {

	/**
	 * Delegates the call to {@link #setIgnoredByUser(AdaptationTask, boolean)}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final List<AdaptationTask> selectedTasks = getSelectedTasks(event);
		for (AdaptationTask task : selectedTasks) {
			setIgnoredByUser(task, true);
		}
		return null;
	}
	
	public static void setIgnoredByUser(AdaptationTask task, boolean value) {
		EditingDomain editingDomain = ResourceUtil.getEditingDomainFor(task);
		if (editingDomain != null) {
			editingDomain.getCommandStack().execute(new SetCommand(editingDomain, task, StatusPackage.Literals.ADAPTATION_TASK__IGNORED_BY_USER, value));
		}	
	}

}
