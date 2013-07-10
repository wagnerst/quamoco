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
$Id: ExcelUtils.java 5018 2012-08-02 15:38:11Z lochmann $
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
package edu.tum.cs.conqat.quamoco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.conqat.engine.core.core.ConQATException;

/**
 * Utility methods for handling Excel sheets
 * 
 * @author ladmin
 * @author $Author: lochmann $
 * @version $Rev: 5018 $
 * @levd.rating YELLOW Hash: 4E53BFE1C7132C43C75657558AA59F46
 */
public class ExcelUtils {

	/** Creates a new Workbook with a sheet */
	public static HSSFSheet createSheet() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		return workbook.createSheet();
	}

	/** Write a sheet to a file */
	public static void writeTo(HSSFSheet sheet, File targetFile)
			throws IOException {
		OutputStream stream = new FileOutputStream(targetFile);
		sheet.getWorkbook().write(stream);
		stream.flush();
		stream.close();
	}

	/**
	 * Read Excel sheet. Returns first sheet in the file.
	 * 
	 * @throws ConQATException
	 *             if file cannot be read
	 */
	public static HSSFSheet readFirstSheet(File file) throws ConQATException {
		try {
			InputStream myxls = new FileInputStream(file);
			HSSFWorkbook workbook = new HSSFWorkbook(myxls);
			return workbook.getSheetAt(0);
		} catch (IOException e) {
			throw new ConQATException("Could not read Excel file: "
					+ e.getMessage(), e);
		}
	}

	/** Get text value of cell in specified column. */
	public static String getCellText(HSSFRow row, int colNo) {
		return cell(row, colNo).getRichStringCellValue().getString();
	}

	/** Resolve cell */
	public static HSSFCell cell(HSSFRow row, int colNo) {
		return row.getCell(colNo);
	}

	/** Get cell content independent of cell type */
	public static String getCellContent(HSSFRow row, int colNo) {
		HSSFCell cell = cell(row, colNo);
		if(cell == null) {
			return "";
		}
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return Double.toString(getCellNumber(row, colNo));
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return getCellText(row, colNo);
		}
		if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			String formula = cell(row,colNo).getCellFormula();
			return formula;			
		}
		if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return "";
		}
		
		throw new AssertionError("Not implemented for cell type: "
				+ cell.getCellType());
	}

	/** Get text value of cell in specified column. */
	public static Double getCellNumber(HSSFRow row, int colNo) {
		return cell(row, colNo).getNumericCellValue();
	}

	/**
	 * Check if column has correct headline.
	 */
	public static void checkName(HSSFSheet sheet, int colNo, String title)
			throws ConQATException {
		if (!title.equals(getCellText(sheet.getRow(0), colNo))) {
			throw new ConQATException("Invalid headline");
		}
	}

}
