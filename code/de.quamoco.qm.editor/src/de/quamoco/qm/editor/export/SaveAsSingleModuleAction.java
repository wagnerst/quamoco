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

package de.quamoco.qm.editor.export;

import java.io.IOException;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Action to merge a number of quality models into a single one.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 75243947192B169C81102FDA4D8B8024
 */
public class SaveAsSingleModuleAction implements IEditorActionDelegate {

	/** The quality model editor. */
	private CustomQmEditor editor;

	/** {@inheritDoc} */
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		editor = (CustomQmEditor) targetEditor;
	}

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		ResourceSet resourceSet = editor.getEditingDomain().getResourceSet();
		ResourceDialog dialog = new ResourceDialog(Display.getDefault()
				.getActiveShell(), "Target URI", SWT.SINGLE | SWT.SAVE);
		if (dialog.open() == IDialogConstants.OK_ID) {
			URI targetURI = dialog.getURIs().get(0);

			Resource resource = new QualityModelMerger().merge(resourceSet);
			resource.setURI(targetURI);
			try {
				resource.save(null);
			} catch (IOException e) {
				LoggingUtils.error(QmEditorPlugin.getPlugin(),
						"Result could not be saved", e);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// not required
	}
}
