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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.view.tasks.AdaptationTasksView;

/**
 * An abstract super class for handlers dealing with the {@link AdaptationTasksView}
 * @author Franz Becker
 */
public abstract class AbstractViewHandler extends AbstractHandler {

	/**
	 * Tries to return the {@link AdaptationTasksView} instance.
	 * @param event the execution event
	 * @return the instance of the {@link AdaptationTasksView}
	 */
	public AdaptationTasksView getView(ExecutionEvent event) {
		IWorkbenchPart part = HandlerUtil.getActivePart(event);
		if (part instanceof AdaptationTasksView) {
			return (AdaptationTasksView) part;
		}
		return null;
	}
	
	/**
	 * Returns all selected {@link AdaptationTask}s.
	 * @param event the execution event.
	 * @return the selected {@link AdaptationTask}s in the {@link AdaptationTasksView}
	 */
	public List<AdaptationTask> getSelectedTasks(ExecutionEvent event) {
		final IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		
		// iterate over selection and add adaptation tasks.
		LinkedList<AdaptationTask> taskList = new LinkedList<AdaptationTask>();
		for (Object selectedObject : selection.toArray()) {
			if (selectedObject instanceof AdaptationTask) {
				taskList.add((AdaptationTask) selectedObject);
			}
		}
		
		return taskList;
	}
	
	/**
	 * Returns the selected {@link AdaptationTask}.
	 * @param event the execution event
	 * @return the first element in the current selection if it's an {@link AdaptationTask}
	 */
	public AdaptationTask getSelectedTask(ExecutionEvent event) {
		final IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		if (selection.getFirstElement() instanceof AdaptationTask) {
			return (AdaptationTask) selection.getFirstElement();
		}
		return null;
	}

}
