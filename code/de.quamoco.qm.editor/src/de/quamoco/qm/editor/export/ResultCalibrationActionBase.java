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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * Base class for actions to export and import calibration data from Excel
 * files.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 987A395853EFACF6977D52185905F49F
 */
public abstract class ResultCalibrationActionBase extends ImportExportActionBase {

	/** Choose an Excel file to open or save. */
	protected String chooseExcelFile(boolean save) {
		FileDialog dialog = new FileDialog(Display.getDefault()
				.getActiveShell(), save ? SWT.SAVE : SWT.OPEN);
		if (save) {
			dialog.setOverwrite(true);
		}
		dialog.setFilterExtensions(new String[] {
				"*." + EExcelVersion.EXCEL_2007.getExtension(),
				"*." + EExcelVersion.EXCEL_2003.getExtension() });
		dialog.setFilterNames(new String[] {
				EExcelVersion.EXCEL_2007.getText(),
				EExcelVersion.EXCEL_2003.getText() });
		return dialog.open();
	}
}
