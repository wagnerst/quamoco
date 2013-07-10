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

package de.quamoco.adaptation.util.qm.resource;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.presentation.QmModelWizard;

/**
 * A utility class that abstracts from the handling of quality model resources
 * @author Franz Becker
 */
public class QualityModelResourceUtil {
	
	/**
	 * Gets the {@link QualityModel} of a given file within the passed editing domain.
	 * @param file the file
	 * @param editingDomain the editing domain the {@link QualityModel} shall be loaded into.
	 * @return the {@link QualityModel} to a given file.
	 */
	public static QualityModel getQualityModel(IFile file, EditingDomain editingDomain) {
		if (isQualityModelResource(file)) {
			URI uri = URI.createFileURI(file.getFullPath().toString());
			Resource qualityModelResource = editingDomain.getResourceSet().getResource(uri, true);			
			EList<EObject> contents = qualityModelResource.getContents();
			if (contents.size() > 0) {
				if (contents.get(0) instanceof QualityModel) {
					return (QualityModel) contents.get(0);
				}				
			}
		}		
		return null;
	}
	
	/**
	 * Instantiates a new {@link QualityModelResourceVisitor} that searches for
	 * quality models according to their file extension within the workspace root.
	 * @param editingDomain the editing domain in which the quality models shall be loaded
	 * @return all quality models contained within the passed resource
	 * @see IResourceVisitor
	 */
	public static List<QualityModel> getQualityModels(EditingDomain editingDomain) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		QualityModelResourceVisitor qualityModelVisitor = new QualityModelResourceVisitor(editingDomain);
		try {
			workspaceRoot.accept(qualityModelVisitor);
		} catch (CoreException e) {
			e.printStackTrace();
		} 
		// getQualityModels is never null
		return qualityModelVisitor.getElements();
	}
	
	/**
	 * Checks if the passed resource has the right file extension
	 * @param resource the resource
	 * @return true if resource is a file (has file extension) and it's lower cased file extension is 
	 * 			in {@link QmModelWizard#FILE_EXTENSIONS}
	 */
	public static boolean isQualityModelResource(IResource resource) {
		String fileExtension = resource.getFileExtension();
		// fileExtension.toLowerCase() is only included if someone types e.g. My.QM
		return fileExtension != null && QmModelWizard.FILE_EXTENSIONS.contains(fileExtension.toLowerCase()) && resource.exists();			   
	}
	
	/**
	 * Tries to retrieve the UUID of the passed {@link QualityModel}.
	 * @param qualityModel the {@link QualityModel} whose UUID shall be retrieved.
	 * @return the UUID of the passed {@link QualityModel} if it's stored in an {@link XMIResource}, null otherwise.
	 */
	public static String getQualityModelUUID(QualityModel qualityModel) {
		if (qualityModel.eResource() instanceof XMIResource) {
			return ((XMIResource) qualityModel.eResource()).getID(qualityModel);
		}
		return null;
	}	
	
}
