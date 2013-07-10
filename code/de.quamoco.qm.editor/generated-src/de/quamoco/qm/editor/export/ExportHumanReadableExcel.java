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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.excelexport.HumanReadableExcelExporter;
import de.quamoco.qm.util.QmModelUtils;

public class ExportHumanReadableExcel extends ResultCalibrationActionBase {

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
		List<QualityModel> qms= QmModelUtils.getQualityModels(editor.getEditingDomain().getResourceSet());
		
		QualityModel leaf=null;
		for(QualityModel test: qms) {
			List<QualityModel> req =QmModelUtils.getInverse(QmPackage.eINSTANCE.getQualityModel_Requires(), test, QualityModel.class);
			if(req.isEmpty()) {
				leaf = test;
			}
		}
				
		HumanReadableExcelExporter exporter = new HumanReadableExcelExporter(leaf,
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
