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

package de.quamoco.qm.editor.refactoring;

import java.util.Collection;
import java.util.List;

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChangeCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.handlers.HandlerUtil;

import de.quamoco.qm.presentation.QmEditor;

/**
 * Handler for the command to merge two elements.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 16CE53A4892D28355AAA6495035326E5
 */
public class MergeHandler extends AbstractHandler {

	/** {@inheritDoc} */
	public Object execute(ExecutionEvent event) {

		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindow(event)
				.getActivePage();
		List<EObject> elements = SelectionUtils.selectAll(activePage
				.getSelection(), EObject.class);
		if (elements.size() == 2) {
			EObject first = elements.get(0);
			EObject second = elements.get(1);

			merge(first, second, activePage);
		}

		return null;
	}

	/** Perform the merge of the two elements. */
	private void merge(EObject first, EObject second, IWorkbenchPage activePage) {
		final Merge merge = new Merge(first, second);
		Collection<Conflict> conflicts = merge.getConflicts();

		if (!conflicts.isEmpty()) {
			AdapterFactory adapterFactory = ((QmEditor) activePage
					.getActiveEditor()).getAdapterFactory();
			ConflictResolutionDialog dialog = new ConflictResolutionDialog(
					merge, adapterFactory);
			if (dialog.open() != IDialogConstants.OK_ID) {
				return;
			}
		}

		EditingDomain domain = AdapterFactoryEditingDomain
				.getEditingDomainFor(first);

		Command command = new ChangeCommand(domain.getResourceSet()) {
			@Override
			protected void doExecute() {
				merge.apply();
			}
		};

		domain.getCommandStack().execute(command);
	}
}
