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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.presentation.QmEditor;

/**
 * Action to locate an element for an identifier.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: AF8C703FB8F466CF797B85B0CD2D3784
 */
public class LocateIdentifierAction implements IEditorActionDelegate {

	/** The editor. */
	private QmEditor editor;

	/** {@inheritDoc} */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		editor = (QmEditor) targetEditor;
	}

	/** {@inheritDoc} */
	public void run(IAction action) {

		InputDialog dialog = new InputDialog(Display.getDefault()
				.getActiveShell(), "Locate Identifier",
				"Enter the identifier of the element that should be located:",
				"", null);

		if (dialog.open() == IDialogConstants.OK_ID) {
			String identifier = dialog.getValue();
			EObject element = resolveIdentifier(identifier);
			if (element != null) {
				editor.getViewer().setSelection(
						new StructuredSelection(element), true);
				editor.setFocus();
			} else {
				MessageDialog.openInformation(Display.getDefault()
						.getActiveShell(), "Not found",
						"Element with identifier \"" + identifier
								+ "\" not found.");
			}
		}
	}

	/** Resolve an element based on the identifier. */
	private EObject resolveIdentifier(String identifier) {
		ResourceSet resourceSet = editor.getEditingDomain().getResourceSet();
		for (Resource resource : resourceSet.getResources()) {
			EObject element = resource.getEObject(identifier);
			if (element != null) {
				return element;
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) {
		// not required
	}
}
