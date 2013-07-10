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

/*--------------------------------------------------------------------------+
$Id: ExportResultCalibrationAction.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 Technische Universitaet Muenchen                     |
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
+--------------------------------------------------------------------------*/
package de.quamoco.qm.editor.export;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.editor.CustomQmEditor;

/**
 * Action to export calibration data from evaluation results.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 83A9B980E6A3A04767BBCF6D74D4598B
 */
public class ExportResultCalibrationAction extends ResultCalibrationActionBase {

	/** The quality model editor. */
	private CustomQmEditor editor;

	/** {@inheritDoc} */
	@Override
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		editor = (CustomQmEditor) targetEditor;
	}

	/** {@inheritDoc} */
	@Override
	protected String chooseFile() {
		return chooseExcelFile(true);
	}

	/** Perform the export of the calibration data. */
	@Override
	protected void doProcess(final String fileName, IProgressMonitor monitor)
			throws InvocationTargetException {
		ResultCalibrationExporter exporter = new ResultCalibrationExporter(
				editor.getEditingDomain().getResourceSet());
		try {
			exporter.export(fileName, monitor);
		} catch (IOException e) {
			throw new InvocationTargetException(e);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void show(String fileName) {
		openFile(fileName);
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// not required
	}
}