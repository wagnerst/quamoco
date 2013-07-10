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
import java.lang.reflect.InvocationTargetException;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Base class for actions that perform import or export of larger amout of data.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public abstract class ImportExportActionBase implements IEditorActionDelegate {

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		String fileName = chooseFile();
		if (fileName != null) {
			if (process(fileName)) {
				show(fileName);
			}
		}
	}

	/** Perform a long-running task on a file. */
	private boolean process(final String fileName) {
		ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display
				.getDefault().getActiveShell());
		try {
			dialog.run(true, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException {
					doProcess(fileName, monitor);
				}
			});
			return true;
		} catch (InvocationTargetException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Error during export", e);
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					"Error during export", e.getCause().getMessage());
		} catch (InterruptedException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Interruption during export", e);
			MessageDialog.openError(Display.getDefault().getActiveShell(),
					"Interruption during export", e.getMessage());
		}
		return false;
	}

	/** Perform a long-running task on a file (to be implemented by subclasses). */
	protected abstract void doProcess(String fileName, IProgressMonitor monitor)
			throws InvocationTargetException;

	/** Choose a file. */
	protected abstract String chooseFile();

	/** Show the result of long-running task (can be overwritten by subclasses. */
	protected void show(@SuppressWarnings("unused") String fileName) {
		// to be overwritten by subclasses
	}

	/** Open an Excel file. */
	protected void openFile(final String fileName) {
		File file = new File(fileName);
		IFileStore fileStore = EFS.getLocalFileSystem().getStore(
				new Path(file.getAbsolutePath()));
		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditorOnFileStore(page, fileStore);
		} catch (PartInitException e) {
			LoggingUtils.error(QmEditorPlugin.getPlugin(),
					"Report could not be opened", e);
		}
	}
}
