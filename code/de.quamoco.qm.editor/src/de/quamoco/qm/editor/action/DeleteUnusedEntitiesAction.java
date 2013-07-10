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

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.commands.DeleteUnusedEntitiesCommand;

/**
 * Deletes all entities that are not used, i.g. an entity is unused if it
 * characterizes not entities AND if it is not specialized AND if it is not in a
 * part-of relation.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 632568FEDBA0212F49CD131264E78B9C
 */
public class DeleteUnusedEntitiesAction implements IObjectActionDelegate {

	/** The editing domain */
	private EditingDomain domain;

	/** Quality Model, whose entities will be deleted */
	private QualityModel selectedQualityModel;

	/** {@inheritDoc} */
	public void run(IAction action) {
		Command command = new DeleteUnusedEntitiesCommand(selectedQualityModel);
		domain.getCommandStack().execute(command);
	}

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		QualityModel qm = SelectionUtils.checkAndPickFirstSafe(selection,
				QualityModel.class);
		if (qm == null) {
			return;
		}

		this.selectedQualityModel = qm;
	}
}
