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

package de.quamoco.adaptation.model.util;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.presentation.AdaptationEditorPlugin;
import de.quamoco.adaptation.model.presentation.AdaptationModelModelWizard;
import de.quamoco.adaptation.util.resource.AbstractResourceVisitor;
import de.quamoco.adaptation.util.swt.GenericSelectionDialog;
import de.quamoco.adaptation.util.swt.SWTUtil;

/**
 * Utility class for loading {@link AdaptationModel}s.
 * @author Franz Becker
 * @see #getDefaultAdaptationModel(EditingDomain)
 * @see #getAdaptationModels(EditingDomain)
 */
public class AdaptationModelLoader {

	/**
	 * If there is more than one {@link AdaptationModel} the user shall have the choice
	 * to pick the desired one. If there is no further model, the default one is returned.
	 * @return the {@link AdaptationModel} that shall be used by this wizard.
	 */
	public static AdaptationModel getAdaptationModel(EditingDomain editingDomain) {
		List<AdaptationModel> models = AdaptationModelLoader.getAdaptationModels(editingDomain);
		if (models.size() > 1) {
			GenericSelectionDialog selectionDialog = new GenericSelectionDialog(
					"Select an adaptation model",
					"Please select an adaptation model that shall be used",
					models.toArray());
			selectionDialog.open();
			return (AdaptationModel) selectionDialog.getFirstResult();		
		} else {
			if (!models.isEmpty()) {
				return models.get(0);
			}
			return null;
		}
	}
	
	/**
	 * Tries to retrieve the default {@link AdaptationModel}. It is located in the 
	 * wizard's plug-in folder with the name default.am.
	 * @param editingDomain the {@link EditingDomain} in which the default model shall be loaded.
	 * @return the default {@link AdaptationModel} (default.am in the wizard's plug-in).
	 */
	public static AdaptationModel getDefaultAdaptationModel(EditingDomain editingDomain) {
		try {
			IPath path = new Path("default.am");					
			java.net.URL url = FileLocator.resolve(FileLocator.find(AdaptationEditorPlugin.getPlugin().getBundle(), path, null));
			URI uri = URI.createFileURI(url.getFile());			
			Resource resource = editingDomain.getResourceSet().getResource(uri, true);
			return (AdaptationModel) resource.getContents().get(0);
		} catch (Exception e) {			
			SWTUtil.showErrorDialog(null, "Default adaptation model could not be loaded!", e);
		}
		return null;
	}
	
	/**
	 * <ul>
	 * <li>Case 1) There is only the default adaptation model return it.</li>
	 * <li>Case 2) If there is one AM with "overrideDefaultAM" = true return this one.</li>
	 * <li>Case 3) Otherwise return all in the workspace</li>
	 * </ul>
	 * @param editingDomain the editing domain in which the {@link AdaptationModel}s shall be loaded
	 * @return all {@link AdaptationModel}s in the workspace plus the default one.
	 */
	public static List<AdaptationModel> getAdaptationModels(EditingDomain editingDomain) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		AdaptationModelResourceVisitor adaptationModelVisitor = new AdaptationModelResourceVisitor(editingDomain);
		try {
			workspaceRoot.accept(adaptationModelVisitor);
		} catch (CoreException e) {
			e.printStackTrace();
		} 
		// getElements is never null
		List<AdaptationModel> workspaceModels = adaptationModelVisitor.getElements();
		AdaptationModel workspaceDefault = null;
		for (AdaptationModel adaptationModel : workspaceModels) {
			if (adaptationModel.isOverrideDefaultAdaptationModel()) {
				if (workspaceDefault == null) { // this might match case 2
					workspaceDefault = adaptationModel;
				} else { // Case 3 is now the case, there is more than one which wants to override
					// Add the default one and return the list
					workspaceModels.add(getDefaultAdaptationModel(editingDomain));
					return workspaceModels;
				}
			}
				
		}
		
		if (workspaceDefault != null) { // Case 2, return only this one
			return Collections.singletonList(workspaceDefault);
		}
		
		// Case 1 or 3 - add the default one.
		workspaceModels.add(getDefaultAdaptationModel(editingDomain));
		return workspaceModels;
	}
	
	/**
	 * Resource visitor that returns all {@link AdaptationModel} resources.
	 * @author Franz Becker
	 * @see AbstractResourceVisitor
	 */
	private static class AdaptationModelResourceVisitor extends AbstractResourceVisitor<AdaptationModel> {

		public AdaptationModelResourceVisitor(EditingDomain editingDomain) {
			super(editingDomain, AdaptationModelModelWizard.FILE_EXTENSIONS);
		}

		@Override
		protected boolean isTypeInstance(EObject eObject) {
			return eObject instanceof AdaptationModel;
		}
		
	}
}
