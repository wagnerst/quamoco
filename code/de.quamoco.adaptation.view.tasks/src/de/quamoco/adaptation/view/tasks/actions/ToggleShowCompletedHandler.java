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

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.adaptation.view.tasks.AdaptationTasksView;
import de.quamoco.adaptation.view.tasks.filter.CompletedFilter;

/**
 * Handles the toolbar action "Filter Completed" (toggle button).
 * @author Franz Becker
 */
public class ToggleShowCompletedHandler extends AbstractViewHandler {

	/**
	 * Toggles the command state, retrieves the {@link AdaptationTasksView} and
	 * calls on its {@link CompletedFilter} the {@link CompletedFilter#setEnabled(boolean)}
	 * method.
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
        boolean enabled = HandlerUtil.toggleCommandState(command);

        AdaptationTasksView view = getView(event);
		if (view != null) {			
			view.getCompletedFilter().setEnabled(enabled);
		}
		return null;
	}

}
