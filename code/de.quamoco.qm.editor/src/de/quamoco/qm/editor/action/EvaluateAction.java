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

import java.lang.reflect.InvocationTargetException;

import org.conqat.ide.commons.ui.dialog.MessageUtils;
import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.conqat.ide.commons.ui.wizard.SinglePageWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.evaluation.CSharpEvaluationWizardPage;
import de.quamoco.qm.editor.evaluation.EvaluateWizardPageBase;
import de.quamoco.qm.editor.evaluation.JavaEvaluateWizardPage;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Action to perform the evaluation with ConQAT.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class EvaluateAction implements IObjectActionDelegate {

	/** Quality model that is evaluated. */
	private QualityModel model;

	/** The quality model editor. */
	private CustomQmEditor editor;

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		EvaluateWizardPageBase page = "Java".equals(model.getName()) ? new JavaEvaluateWizardPage(
				model, editor) : new CSharpEvaluationWizardPage(model, editor);
		Wizard wizard = new SinglePageWizard(page);
		WizardDialog dialog = new WizardDialog(Display.getDefault()
				.getActiveShell(), wizard);
		if (dialog.open() == IDialogConstants.OK_ID) {
			ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(
					Display.getDefault().getActiveShell());
			try {
				progressDialog.run(true, true, page.getRunnable());
			} catch (InvocationTargetException e) {
				reportError(e, "Quality evaluation could not be started");
			} catch (InterruptedException e) {
				reportError(e, "Quality evaluation has been interrupted");
			}
		}
	}

	/** Report an error in a dialog and to the error log. */
	private void reportError(Throwable e, String message) {
		MessageUtils.showError("Evaluation error", message);
		LoggingUtils.error(QmEditorPlugin.getPlugin(), message, e);
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		model = SelectionUtils.checkAndPickFirst(selection, QualityModel.class);
		if (model != null
				&& ("Java".equals(model.getName()) || "C#".equals(model
						.getName()))) {
			action.setEnabled(true);
		} else {
			action.setEnabled(false);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		editor = (CustomQmEditor) targetPart;
	}
}
