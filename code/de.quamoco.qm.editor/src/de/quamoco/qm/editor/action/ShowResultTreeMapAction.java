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

package de.quamoco.qm.editor.action;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.result.ResultTreeMapView;
import de.quamoco.qm.presentation.QmEditorPlugin;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Action to show a result in a tree map.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ShowResultTreeMapAction implements IObjectActionDelegate {

	/** The evaluation result. */
	private EObject result;

	/** The quality model editor. */
	private CustomQmEditor editor;

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		if (result instanceof EvaluationResult) {
			EvaluationResult evaluationResult = (EvaluationResult) result;
			showResultTreeMap(evaluationResult, editor);
		} else {
			QualityModelResult modelResult = (QualityModelResult) result;
			EvaluationResult evaluationResult = modelResult
					.getEvaluationResults().get(
							modelResult.getEvaluationResults().size() - 1);
			showResultTreeMap(evaluationResult, editor);
		}
	}

	/** Show the result in a tree map. */
	public static void showResultTreeMap(EvaluationResult result,
			CustomQmEditor editor) {
		try {
			ResultTreeMapView view = (ResultTreeMapView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(ResultTreeMapView.ID, UUIDUtils.getId(result),
							IWorkbenchPage.VIEW_ACTIVATE);
			view.setResult(result, editor);
		} catch (PartInitException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Result view could not be opened", e);
		}
	}

	/** Show the result in a tree map. */
	public static void showResultTreeMap(QualityModelResult result,
			CustomQmEditor editor) {
		EvaluationResult evaluationResult = result.getEvaluationResults().get(
				result.getEvaluationResults().size() - 1);
		ShowResultTreeMapAction.showResultTreeMap(evaluationResult, editor);
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		result = SelectionUtils.checkAndPickFirst(selection, EObject.class);
	}

	/** {@inheritDoc} */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		editor = (CustomQmEditor) targetPart;
	}
}
