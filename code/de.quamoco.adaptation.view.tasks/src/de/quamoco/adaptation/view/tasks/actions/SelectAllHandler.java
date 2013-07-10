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

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.adaptation.view.tasks.AdaptationTasksView;

/**
 * Handles the context menu action "Select All".
 * Retrieves the {@link AdaptationTasksView}, its tree and calls selectAll() on it.
 * Afterwards triggers a context menu update (important!).
 * @author Franz Becker
 */
public class SelectAllHandler extends AbstractViewHandler {

	/**
	 * Performs the action:
	 * <ul>
	 * <li>Retrieve the tree and call selectAll()</li>
	 * <li>Get the active site's selection provider and set its 
	 *     selection to the current selection to trigger an context menu update</li>
	 * </ul>
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		AdaptationTasksView view = getView(event);
		if (view != null) {
			view.getViewer().getTree().selectAll();
			
			// workaround to trigger context menu update
			ISelectionProvider selectionProvider = HandlerUtil.getActiveSite(event).getSelectionProvider();
			selectionProvider.setSelection(selectionProvider.getSelection());
		}	
		
		return null;
	}

}
