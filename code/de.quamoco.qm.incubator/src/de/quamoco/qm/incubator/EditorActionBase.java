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

package de.quamoco.qm.incubator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public abstract class EditorActionBase implements IObjectActionDelegate {

	/**
	 * The element that is selected
	 */
	protected EObject eObject;
	
	/**
	 * The edit domain of the editor
	 */
	private EditingDomain editDomain;
	
	/**
	 * The viewer of the editor
	 */
	private Viewer viewer;

	/**
	 * {@inheritDoc}
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		viewer = null;
		if(targetPart != null && targetPart instanceof IViewerProvider) {
			viewer = ((IViewerProvider) targetPart).getViewer();
		}
		editDomain = null;
		if(targetPart != null && targetPart instanceof IEditingDomainProvider) {
			editDomain = ((IEditingDomainProvider) targetPart).getEditingDomain();
		}
	}

	/**
	 * Set a selection to the editor
	 */
	protected void select(StructuredSelection selection) {
		if(viewer != null) {
			viewer.setSelection(selection);
		}
	}
	
	/**
	 * Execute a command in the editor
	 */
	protected void execute(Command command) {
		if(editDomain != null) {
			editDomain.getCommandStack().execute(command);
		}
		else {
			command.execute();
			command.dispose();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;
		eObject = (EObject) structuredSelection.getFirstElement();
	}

}
