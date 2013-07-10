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

package de.quamoco.qm.conqat.action;

import java.util.List;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.conqat.ide.commons.ui.wizard.SinglePageWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.conqat.wizard.GeneratorPage;

/**
 * This action opens a wizard for the ConQAT block generation.
 * 
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 61FB3E47A6FE57581A1FC095456D26F1
 */
public class GenerateConQATBlockAction implements IObjectActionDelegate {

	/** List of selected models. */
	private List<QualityModel> models;

	/** Open wizard. */
	public void run(IAction action) {
		WizardDialog dialog = new WizardDialog(Display.getDefault()
				.getActiveShell(), new SinglePageWizard(new GeneratorPage(
				models)));
		dialog.open();
	}

	/** Update {@link #models} based on selection. */
	public void selectionChanged(IAction action, ISelection selection) {
		models = SelectionUtils
				.checkAndSelectAll(selection, QualityModel.class);
	}

	/** Nothing to do. */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// not required
	}
}