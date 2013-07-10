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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * Implements the command to create a new resource with a
 * passed root object. <br><br>
 * <b>Usage:</b><br><br>
 * <code>NewResourceCommand command = new NewResourceCommand(editingDomain, rootObject, URI);<br><br>
 * if (command.executeOnEditingDomain) {<br>
 *    no exception was thrown<br>
 * } else {<br>
 *    an exception was thrown<br>
 * }</code><br>
 * @author Franz Becker
 */
// TODO replace this command with an AbstractCommand that doesn't need a TransactionalEditingDomain
public class NewResourceCommand extends CustomRecordingCommand {

	/** The label of the command. */
	protected static String commandLabel = "Create new resource";
	
	/** The description of the command. */
	protected static String commandDescription = "Creates a new resource";
	
	/** The root object of the new resource. */	
	protected EObject rootObject;
	
	/** The uri of the new resource. */	
	protected URI fileURI;
	
	/** The status of the operation, false if exception was thrown. */	
	protected boolean status;
	
	/**
	 * Calls super constructor and initializes instance variables
	 * @param rootObject the root object of the new resoure
	 * @param fileURI the {@link URI} of the new resource
	 */
	public NewResourceCommand(TransactionalEditingDomain editingDomain, EObject rootObject, URI fileURI) {
		super(editingDomain, commandLabel, commandDescription);
		this.rootObject = rootObject;
		this.fileURI = fileURI;
	}

	/**
	 * executed the command
	 */
	@Override
	protected void doExecute() {
		try {
			ResourceSet resourceSet = editingDomain.getResourceSet();
			Resource resource = resourceSet.createResource(fileURI);
			if (rootObject != null) {
				resource.getContents().add(rootObject);
			}
			resource.save(ResourceUtil.SAVE_OPTIONS);
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}	
		status = true;
	}
	
	/**
	 * @return false if an exception was caught, true otherwise
	 */
	public boolean getStatus() {
		return status;
	}

}
