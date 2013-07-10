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

package de.quamoco.adaptation.view.history.action;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;

import de.quamoco.adaptation.view.history.AdaptationHistoryView;
import de.quamoco.qm.QualityModel;

public class DeleteAllAction extends Action {

	private AdaptationHistoryView view;

	public DeleteAllAction(AdaptationHistoryView view) {
		super();
		this.view = view;
	}

	@Override
	public void run() {
		QualityModel qualityModel = (QualityModel) view.getViewer().getInput();
		
		if (qualityModel != null && MessageDialog.openQuestion(view.getSite().getShell(), "Delete history", "Do you really want to delete the complete history?")) {
			IStructuredContentProvider contentProvider = (IStructuredContentProvider) view.getViewer().getContentProvider();
			Object[] elementsToDelete = contentProvider.getElements(qualityModel);
			
			EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(qualityModel);			
			CompoundCommand command = new CompoundCommand();
			
			for (Object object : elementsToDelete) {
				EObject eObject = (EObject) object;
				RemoveCommand removeCommand = new RemoveCommand(editingDomain, eObject.eContainer(), eObject.eContainingFeature(), eObject);
				if (removeCommand.canExecute()) {
					command.append(removeCommand);
				}			
			}
			
			command.setLabel("Delete history");
			editingDomain.getCommandStack().execute(command);
		}
	}

	@Override
	public String getText() {
		return "Delete history";
	}

	@Override
	public String getToolTipText() {
		return "Deletes all items in the history";
	}	
	
}
