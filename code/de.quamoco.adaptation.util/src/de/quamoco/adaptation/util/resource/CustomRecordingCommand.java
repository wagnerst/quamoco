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

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * A customizes {@link RecordingCommand} that adds the method
 * {@link #executeOnEditingDomain()} which is quite convenient.
 * @author Franz Becker
 */
public abstract class CustomRecordingCommand extends RecordingCommand {

	/** The editing domain the command shall be executed on. */
	protected TransactionalEditingDomain editingDomain;
	
	/**
	 * Calls the super constructor and initializes {@link #editingDomain}
	 * @param editingDomain the value for {@link #editingDomain}
	 * @param label the label of the command
	 * @param description the description of the command
	 */
	public CustomRecordingCommand(TransactionalEditingDomain editingDomain, String label, String description) {
		super(editingDomain, label, description);
		this.editingDomain = editingDomain;
	}
	
	/**
	 * Executes this command on the command stack of the editing domain. 
	 * @return false if exception was caught, true otherwise
	 * @see TransactionalEditingDomain#getCommandStack()
	 * @see CommandStack#execute(Command)
	 */
	public boolean executeOnEditingDomain() {
		try {			
			editingDomain.getCommandStack().execute(this);
		} catch (Exception e) {
			return false;
		}
		return true;		
	}

}
