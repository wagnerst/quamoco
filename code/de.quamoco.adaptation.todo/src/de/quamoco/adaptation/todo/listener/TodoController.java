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

package de.quamoco.adaptation.todo.listener;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.FeatureRequiredAction;
import de.quamoco.adaptation.model.FinalizeAction;
import de.quamoco.adaptation.model.TodoActionsOnStartup;
import de.quamoco.adaptation.model.util.AdaptationModelLoader;
import de.quamoco.qm.QualityModel;

public class TodoController extends EContentAdapter {

	protected static final EditingDomain editingDomainForAdaptationModels = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
	protected static final String ANNOTATION_KEY = "automaticallyAddTodoActionsUponOpen";
	protected EditingDomain editingDomain;
	protected AdaptationModel adaptationModel;
	protected ContentListener contentListener;

	/**
	 * Sets instance variables and performs initialization steps.
	 * @param editingDomain the {@link EditingDomain} to which this controller is attached.
	 */
	public TodoController(EditingDomain editingDomain) {
		super();
		this.editingDomain = editingDomain;
		Map<EClass, List<FeatureRequiredAction>> eClassToFeatureRequiredActionMap = initializeAdaptationModel();
		if (!adaptationModel.getRunGlobalActionsOnEditorStartup().equals(TodoActionsOnStartup.NEVER)) {
			runActionsGlobally(eClassToFeatureRequiredActionMap);
		}		
		contentListener = new ContentListener(editingDomain, eClassToFeatureRequiredActionMap);
		
	}
	
	/*
	 * Methods for initialization
	 */
	
	/**
	 * <ul>
	 * <li>Sets the {@link #adaptationModel}, in case there is more than one available asks the user to choose one</li>
	 * <li>Initializes {@link #eClassToFeatureRequiredActionMap} by retrieving all {@link FeatureRequiredAction} for each
	 * 		class from the adaptation model.</li>
	 * </ul>
	 */
	protected Map<EClass, List<FeatureRequiredAction>> initializeAdaptationModel() {
		Map<EClass, List<FeatureRequiredAction>> eClassToFeatureRequiredActionMap = new HashMap<EClass, List<FeatureRequiredAction>>();
		
		// Get Adaptation Model for this editing domain
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run(){
				adaptationModel = AdaptationModelLoader.getAdaptationModel(editingDomainForAdaptationModels);
			}
		});
		
		/*
		 * For each adaptation element get those FeatureRequiredActions that are !deleteIfNotSet (those are considered for the wizard only).
		 */
		for (AdaptationElement adaptationElement : adaptationModel.getWizard().getAdaptationElements()) {
			List<FeatureRequiredAction> actionList = new LinkedList<FeatureRequiredAction>();
			
			for (FinalizeAction finalizeAction : adaptationElement.getFinalizeActions()) {
				if (finalizeAction instanceof FeatureRequiredAction) {
					FeatureRequiredAction action = (FeatureRequiredAction) finalizeAction;
					if (!action.isDeleteIfNotSet()) {
						actionList.add(action);
					}
				}
			}
			
			// Add element only when action list is not empty
			if (!actionList.isEmpty()) {
				eClassToFeatureRequiredActionMap.put(adaptationElement.getElementClass(), actionList);
			}
		}
		
		return eClassToFeatureRequiredActionMap;
	}
	
	/**
	 * Upon initialization all FeatureRequiredActions are executed against the {@link QualityModel}s
	 * that are contained in the editingDomain to provide a consistent state. 
	 */
	protected void runActionsGlobally(Map<EClass, List<FeatureRequiredAction>> eClassToFeatureRequiredActionMap) {
		// Get the QualityModels of the ResourceSet
		List<QualityModel> qualityModels = new LinkedList<QualityModel>();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		
		for (Resource resource : resourceSet.getResources()) {
			if (resource.getContents().size() > 0) {
				if (resource.getContents().get(0) instanceof QualityModel) {
					qualityModels.add((QualityModel) resource.getContents().get(0));
				}
			}
		}
		
		// Gather commands for each QualityModel and for each action
		final CompoundCommand commandToExecute = new CompoundCommand();
		for (QualityModel qualityModel : qualityModels) {
			if (runGlobalActionsForModel(qualityModel)) {
				for (List<FeatureRequiredAction> actionList : eClassToFeatureRequiredActionMap.values()) {
					for (FeatureRequiredAction action : actionList) {
						Command command = action.getCommand(qualityModel, editingDomain);
						if (command.canExecute()) {
							commandToExecute.append(command);
						}
					}
				}
			}			
		}
		
		// Execute the command on the editing domain
		// Execute in Runnable to avoid SWT actions (probably caused by update)
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run(){
				if (commandToExecute.canExecute()) {
					if (adaptationModel.getRunGlobalActionsOnEditorStartup().equals(TodoActionsOnStartup.ASK_USER)) {
						MessageDialog dialog = new MessageDialog( null, "Add Todo Tasks", null, "There are some todo tasks that should be added to your model. Do you want to add them now?", MessageDialog.QUESTION, new String[] {"Yes", "No"}, 0);
						int result = dialog.open();
						if (result == 0) {
							editingDomain.getCommandStack().execute(commandToExecute);
						}
					} else {
						editingDomain.getCommandStack().execute(commandToExecute);
					}
					
				}				
			}
		});
		
	}
	
	private boolean runGlobalActionsForModel(QualityModel qualityModel) {
		if (qualityModel.getAnnotations().containsKey(ANNOTATION_KEY)) {
			if ("false".equals(qualityModel.getAnnotations().get(ANNOTATION_KEY))) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Methods for adding / removing content listener
	 */	
	public void adapt() {
		editingDomain.getResourceSet().eAdapters().add(contentListener);
	}
	
	public void unadapt() {
		editingDomain.getResourceSet().eAdapters().remove(contentListener);
	}

}
