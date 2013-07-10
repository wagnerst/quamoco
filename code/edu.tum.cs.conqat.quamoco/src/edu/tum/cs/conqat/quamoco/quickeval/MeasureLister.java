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

package edu.tum.cs.conqat.quamoco.quickeval;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.ExcelUtils;
import edu.tum.cs.conqat.quamoco.QMProcessorBase;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: D9A8163E33D79A434E265E9237AB17CE
 */
@AConQATProcessor(description = "Writes an excel file with all Manually settable measures")
public class MeasureLister extends QMProcessorBase {

	/** Name of measure column */
	public static final String MEASURE = "Measure";

	/** Name of type column */
	public static final String TYPE = "Type";

	/** Name of description column */
	public static final String DESCRIPTION = "Description";

	/** Name of value column */
	public static final String VALUE = "Value";

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "excel", attribute = "file", description = "Target file")
	public String file;

	/** {@inheritDoc} */
	@Override
	public String process() throws ConQATException {
		HSSFSheet sheet = ExcelUtils.createSheet();

		printHeader(sheet);

		List<String[]> items = new LinkedList<String[]>();

		for (QualityModel model : models) {

			TreeIterator<EObject> i = model.eAllContents();
			while (i.hasNext()) {
				EObject eObject = i.next();
				if (eObject instanceof ManualInstrument) {
					ManualInstrument instrument = (ManualInstrument) eObject;
					Type type = instrument.getDetermines().getType();
					if (TypeUtils.isPrimitive(type)) {
						addItem(items, instrument, type);

					}
				}
			}
		}

		// sort by qualified names
		Collections.sort(items, new Comparator<String[]>() {

			@Override
			public int compare(String[] o1, String[] o2) {
				return o1[0].compareTo(o2[0]);
			}
		});

		// write excel file
		int rowNumber = 1;
		for (String[] item : items) {
			HSSFRow row = sheet.createRow(rowNumber);
			for (int i = 0; i < item.length; i++) {
				row.createCell(i).setCellValue(item[i]);
			}
			rowNumber++;
		}

		writeFile(sheet);

		return file;
	}

	/**
	 * Adds an item to the list.
	 */
	private void addItem(List<String[]> items, ManualInstrument instrument,
			Type type) throws AssertionError {
		items.add(new String[] { instrument.getQualifiedName(),
				type.toString(), instrument.getDescription(),
				TypeUtils.defaultValue(type).toString() });
	}

	/** Write sheet to file */
	private void writeFile(HSSFSheet sheet) throws ConQATException {
		try {
			getLogger().info("Writing measures to " + file);
			ExcelUtils.writeTo(sheet, new File(file));
		} catch (IOException e) {
			throw new ConQATException("Problems writing target file: "
					+ e.getMessage());
		}
	}

	/** Print header */
	private void printHeader(HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue(MEASURE);
		row.createCell(1).setCellValue(TYPE);
		row.createCell(2).setCellValue(DESCRIPTION);
		row.createCell(3).setCellValue(VALUE);
	}
}
