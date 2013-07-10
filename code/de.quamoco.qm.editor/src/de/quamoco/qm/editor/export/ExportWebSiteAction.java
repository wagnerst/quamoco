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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.editor.CustomQmEditor;

/**
 * Action to export the quality model to a web site.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ExportWebSiteAction extends ImportExportActionBase {

	/** The quality model editor. */
	private CustomQmEditor editor;

	/** {@inheritDoc} */
	@Override
	protected String chooseFile() {
		DirectoryDialog dialog = new DirectoryDialog(Display.getDefault()
				.getActiveShell());
		return dialog.open();
	}

	/** {@inheritDoc} */
	@Override
	protected void doProcess(String file, IProgressMonitor monitor)
			throws InvocationTargetException {
		WebSiteExporter exporter = new WebSiteExporter(editor
				.getEditingDomain().getResourceSet());
		try {
			exporter.export(file, monitor);
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void show(String fileName) {
		File file = new File(fileName + "/explorer.html");
		openFile(file.getAbsolutePath());
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// not required
	}

	/** {@inheritDoc} */
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		editor = (CustomQmEditor) targetEditor;
	}

}
