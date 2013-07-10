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

import org.conqat.ide.commons.ui.ui.WorkbenchUtils;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;

import de.quamoco.adaptation.model.status.AdaptationHistoryItem;
import de.quamoco.adaptation.model.status.AdaptationTask;
import de.quamoco.adaptation.util.resource.ResourceUtil;
import de.quamoco.adaptation.view.history.AdaptationHistoryView;
import de.quamoco.adaptation.view.tasks.listener.ViewerDoubleClickListener;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.presentation.QmEditor;

/**
 * Handles the context menu action "Go to".
 * Retrieves the selected {@link AdaptationTask} and then tries to open the 
 * {@link AdaptationTask#getAffectedElement() affected element} in an editor or view.
 * @author Franz Becker
 * @see ViewerDoubleClickListener
 * @see #openAffectedElementInEditor(AdaptationTask)
 */
public class GotoHandler extends AbstractViewHandler {

	/**
	 * Delegates the call to {@link #openAffectedElementInEditor(AdaptationTask)}
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		AdaptationTask selectedTask = getSelectedTask(event);
		if (selectedTask != null) {						
			openAffectedElementInEditor(selectedTask);
		}
		return null;
	}
	
	/**
	 * Retrieves the affected element and opens it either in an {@link QmEditor}
	 * or in an {@link AdaptationHistoryView}, depending on its type.
	 * @param task the task of interest
	 */
	public static void openAffectedElementInEditor(AdaptationTask task) {
		try {
			EObject affectedElement = task.getAffectedElement();
			/*
			 * Case distinction: Either show in QmEditor or show in HistoryView! 
			 */
			if (affectedElement instanceof QualityModelElement) {
				IFile file = ResourceUtil.getIFile(affectedElement);
				if (file != null) {
					IEditorPart editor = IDE.openEditor(WorkbenchUtils.getActiveWorkbenchPage(), file, true);
					if (editor instanceof QmEditor) {
						QmEditor qmEditor = (QmEditor) editor;
						StructuredSelection newSelection = new StructuredSelection(affectedElement);
						qmEditor.getViewer().setSelection(newSelection, true);
					}
				}
			} else if (affectedElement instanceof AdaptationHistoryItem) {				
				IWorkbenchPage page = WorkbenchUtils.getActiveWorkbenchPage();
				if (page != null) {
					// TODO remove hard coding
					AdaptationHistoryView historyView = (AdaptationHistoryView) page.showView("de.quamoco.adaptation.view.AdaptationHistoryView");
					historyView.selectItem((AdaptationHistoryItem) affectedElement);
				}
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

}
