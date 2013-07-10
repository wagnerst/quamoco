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

package de.quamoco.adaptation.util.resource;

import java.util.Collections;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * A utility class that abstracts from the handling of resources
 * @author Franz Becker
 */
public class ResourceUtil {

	/** Do not modify! */
	public static final Map<? extends Object, ? extends Object> SAVE_OPTIONS;
	
	/** The default {@link TransactionalEditingDomain}. */
	// TODO check this one or adapterfactoryeditingdomain
	public static final TransactionalEditingDomain defaultEditingDomainSingleton = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
	
	static {
		SAVE_OPTIONS = Collections.singletonMap(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
	}
	
	/**
	 * @param eObject the {@link EObject} of interest.
	 * @return the {@link TransactionalEditingDomain} of a passed {@link EObject}
	 */
	public static EditingDomain getEditingDomainFor(EObject eObject) {
		EditingDomain editingDomain;
		editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
		if (editingDomain != null) {
			return editingDomain;
		}
		editingDomain = TransactionUtil.getEditingDomain(eObject);
		if (editingDomain != null) {
			return editingDomain;
		}		
		// If this point is reached no editing domain found => return default one
		return defaultEditingDomainSingleton;
	}
	
	/**
	 * Creates a new resource for the passed root object.
	 * @param editingDomain the editing domain that shall be used
	 * @param newFile the file of the new resource
	 * @param rootObject the root object of the new resource
	 * @return false if any exceptions were thrown, true otherwise
	 * @see NewResourceCommand
	 */
	public static boolean createNewResource(TransactionalEditingDomain editingDomain, IFile newFile, EObject rootObject) {		
		URI fileURI = URI.createPlatformResourceURI(newFile.getFullPath().toString(), true);		
		NewResourceCommand newResourceCommand = new NewResourceCommand(editingDomain, rootObject, fileURI);
		if (newResourceCommand.executeOnEditingDomain()) {
			return newResourceCommand.getStatus();
		}
		return false;	
	}
		
	/**
	 * Tries to retrieve the workspace related {@link IFile} of an passed {@link EObject}.
	 * @param eObject the object of interest
	 * @return the {@link IFile} of the resource of an passed {@link EObject}
	 */
	public static IFile getIFile(EObject eObject) {
		if (eObject.eResource() != null) {
			URI uri = eObject.eResource().getURI();
			String fileString = uri.toPlatformString(true);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileString));	
		}
		return null;		
	}
}
