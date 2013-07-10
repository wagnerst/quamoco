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

package de.quamoco.adaptation.view.tasks.listener;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;

import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.view.tasks.actions.GotoHandler;

/**
 * Delegates to {@link GotoHandler#openAffectedElementInEditor(AdaptationTask)}
 * if an {@link AdaptationTask} is currently selected.
 * @author Franz Becker 
 */
public class ViewerDoubleClickListener implements IDoubleClickListener {
	
	/**
	 * Check if an {@link AdaptationTask} is selected, if yes call
	 * {@link GotoHandler#openAffectedElementInEditor(AdaptationTask)}.
	 */
	@Override
	public void doubleClick(DoubleClickEvent event) {
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		if (selection.getFirstElement() instanceof AdaptationTask) {
			GotoHandler.openAffectedElementInEditor((AdaptationTask) selection.getFirstElement());
		}
	}

}
