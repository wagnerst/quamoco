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
$Id: MeasureReader.java 5018 2012-08-02 15:38:11Z lochmann $
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
package edu.tum.cs.conqat.quamoco.quickeval;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.ExcelUtils;
import edu.tum.cs.conqat.quamoco.MetricCollection;

/**
 * {@ConQAT.Doc}
 * 
 * @author ladmin
 * @author $Author: lochmann $
 * @version $Rev: 5018 $
 * @levd.rating RED Hash: 51ADB0DDABF93500CA545E9B26949DD7
 */
@AConQATProcessor(description = "Reads measure values from an Excel file. "
		+ "Use the MeasureLister to create such a file. "
		+ "If the processor is in lenient mode, it does not check whether the instruments "
		+ "in the excel file are conform to the quality model.")
public class MeasureReader extends ConQATProcessorBase {

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "metric", attribute = "collection", description = "Collection of computed metric values", optional = true)
	public MetricCollection metricCollection;

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "excel", attribute = "file", description = "File containing Excel measures")
	public String excelFile;

	/** The quality model. */
	protected QualityModel[] models;

	/** lenient mode for this processor */
	protected boolean lenient = false;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "lenient", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "Lenient mode for this processor.")
	public void setLenient(
			@AConQATAttribute(name = "attr", description = "Lenient mode for this processor.") boolean lenient) {
		this.lenient = lenient;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "models", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "The quality models.")
	public void setQualityModels(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) QualityModel[] models) {
		this.models = models;
	}

	/** {@inheritDoc} */
	@Override
	public MetricCollection process() throws ConQATException {

		if (!lenient && this.models == null) {
			throw new ConQATException(
					"This processor is not in lenient mode. Thus it has to be provided with a quality model");
		}

		assertCollectionInitialized();

		File excel = new File(excelFile);

		// if there is no file, issue a warning and return an empty collection
		if (!excel.exists()) {
			getLogger()
					.warn(
							"Excel-file '"
									+ excelFile
									+ "' not found. Returning empty metric collection.");
			return metricCollection;
		}

		getLogger().info("Reading Excel file from " + excelFile);

		HSSFSheet sheet = ExcelUtils.readFirstSheet(excel);
		assertFormat(sheet);
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			processRow(sheet, rowNum);
		}

		return metricCollection;
	}

	/** Read row in sheet and process content */
	private void processRow(HSSFSheet sheet, int rowNum) throws ConQATException {
		HSSFRow row = sheet.getRow(rowNum);
		if(row == null) {
			getLogger().warn("row " + rowNum + " does not exist.");
			return;
		} 
		
		String measureName = ExcelUtils.getCellContent(row, 0);
		
		getLogger().warn("in row " + rowNum + " there is measure " + measureName);
		
		if(measureName.isEmpty()) {
			return;
		}
		
		Type type = Enum.valueOf(Type.class, ExcelUtils.getCellContent(row, 1));

		if (TypeUtils.isPrimitive(type)) {
			String valueString = ExcelUtils.getCellContent(row, 3);
			storeManualInstrument(measureName, type, valueString);
		} else {
			String message = "Cannot read value for measure " + type
					+ " since it is not primitive. Skipping measure!";
			getLogger().warn(message);
		}
	}

	/** Read measure and store in {@link #metricCollection} */
	private void storeManualInstrument(String manualInstrumentName, Type type,
			String valueString) throws ConQATException {
		assertTypeAsInModel(type, manualInstrumentName);

		Object value = TypeUtils.convert(valueString, type);
		metricCollection.put(manualInstrumentName, value);

		getLogger().info(
				"Storing '" + manualInstrumentName + "': '" + value + "'");
	}

	/**
	 * Make sure that type specified in excel is same as type specified in model
	 * 
	 * @throws ConQATException
	 *             if types are inconsistent
	 */
	private void assertTypeAsInModel(Type type, String measureName)
			throws ConQATException {

		// in lenient mode, the model must not be set
		// if it is not set, nothing is checked.
		if (lenient && models == null) {
			return;
		}

		for (QualityModel model : models) {
			TreeIterator<EObject> i = model.eAllContents();
			while (i.hasNext()) {
				EObject eObject = i.next();
				if (eObject instanceof ManualInstrument) {
					ManualInstrument instrument = (ManualInstrument) eObject;
					if (instrument.getQualifiedName().equals(measureName)) {
						if (instrument.getDetermines().getType().equals(type)) {
							// all ok
							return;
						}
						// different types: throw exception
						String message = "Inconsistent types for manual instrument '"
								+ measureName
								+ "': in model: "
								+ instrument.getDetermines().getType()
								+ " in file: " + type;
						if (!lenient) {
							throw new ConQATException(message);
						}
						getLogger().warn(message);
					}
				}
			}
		}

		// if we reach here, no measure of this name was found in the model
		String msg = "No measure with qualified name '" + measureName
				+ "' found in model!";

		if (!lenient) {
			throw new ConQATException(msg);
		}
		getLogger().warn(msg);
	}

	/** Initialize collection if necessary */
	private void assertCollectionInitialized() {
		if (metricCollection == null) {
			metricCollection = new MetricCollection();
		}
	}

	/** Make sure format is as expected */
	private void assertFormat(HSSFSheet sheet) throws ConQATException {
		ExcelUtils.checkName(sheet, 0, MeasureLister.MEASURE);
		ExcelUtils.checkName(sheet, 1, MeasureLister.TYPE);
		ExcelUtils.checkName(sheet, 2, MeasureLister.DESCRIPTION);
		ExcelUtils.checkName(sheet, 3, MeasureLister.VALUE);
	}

}
