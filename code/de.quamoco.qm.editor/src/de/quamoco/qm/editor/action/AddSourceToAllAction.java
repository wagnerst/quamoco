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

import de.quamoco.qm.Source;
import de.quamoco.qm.editor.commands.AddSourceToAllCommand;

/**
 * Action to add a source to all elements in the same resource.
 * 
 * @author herrmama
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: C8A4BFDD70FF09291A373B3630029010
 */
public class AddSourceToAllAction implements IObjectActionDelegate {

	/** Source. */
	private Source source;

	/** Editing domain. */
	private EditingDomain domain;

	/** {@inheritDoc} */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		domain = ((IEditingDomainProvider) targetPart).getEditingDomain();
	}

	/** {@inheritDoc} */
	public void run(IAction action) {
		Command command = new AddSourceToAllCommand(source);
		domain.getCommandStack().execute(command);
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		source = SelectionUtils.checkAndPickFirstSafe(selection,
				Source.class);
	}
}
