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
 $Id: ResultCalibrationImporter.java 5039 2012-10-15 06:18:20Z lochmann $
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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.Function;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Helper class to import calibration data from an Excel file.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 5039 $
 * @levd.rating GREEN Hash: A5E77855226734FC56511CD1312D0888
 */
public class ResultCalibrationImporter {

	/**
	 * {@link ResourceSet} with the {@link QualityModel}s which need to be
	 * calibrated.
	 */
	private final ResourceSet resourceSet;

	/** Constructor. */
	public ResultCalibrationImporter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/** Import an Excel file with a certain name. */
	public void importFile(String filename, IProgressMonitor monitor)
			throws IOException {

		FileInputStream in = new FileInputStream(filename);
		Workbook workbook = filename.endsWith("."
				+ EExcelVersion.EXCEL_2007.getExtension()) ? new XSSFWorkbook(
				in) : new HSSFWorkbook(in);

		Sheet measuresSheet = workbook.getSheet("Measures");
		Sheet aggregationWeightSheet = workbook.getSheet("AggregationWeight");
		Sheet aggregationRankSheet = workbook.getSheet("AggregationRank");

		monitor.beginTask("Import " + filename, measuresSheet.getRow(0)
				.getLastCellNum()
				- 1
				+ aggregationWeightSheet.getRow(0).getLastCellNum()
				- 1
				+ aggregationRankSheet.getRow(0).getLastCellNum() - 1);

		importMeasureEvaluationCalibration(measuresSheet, monitor);
		importFactorAggregationCalibration(aggregationWeightSheet,
				QmPackage.eINSTANCE.getRanking_Weight(), monitor);
		importFactorAggregationCalibration(aggregationRankSheet,
				QmPackage.eINSTANCE.getRanking_Rank(), monitor);

		monitor.done();
	}

	/** Calibrate the {@link MeasureEvaluation}s. */
	private void importMeasureEvaluationCalibration(Sheet sheet,
			IProgressMonitor monitor) {
		Row row = sheet.getRow(5);
		for (Iterator<Cell> i = row.cellIterator(); i.hasNext();) {
			Cell cell = i.next();
			int column = cell.getColumnIndex();
			if (column < 1) {
				continue;
			}
			String id = cell.getStringCellValue();
			EObject evaluation = getEvaluation(id);
			if (evaluation instanceof SingleMeasureEvaluation) {
				SingleMeasureEvaluation measureEvaluation = (SingleMeasureEvaluation) evaluation;
				importFunction(sheet, column, measureEvaluation);
			} else {
				WeightedSumMultiMeasureEvaluation measureEvaluation = (WeightedSumMultiMeasureEvaluation) evaluation;
				if (measureEvaluation == null) {
					System.err
							.println("Evaluation not found. Model and Excel file are inconsistent.");
				} else {
					String measureName = sheet.getRow(1).getCell(column)
							.getStringCellValue();
					MeasureRanking measureRanking = getMeasureRanking(
							measureEvaluation, measureName);

					importFunction(sheet, column, measureRanking);
				}
			}
			monitor.worked(1);
		}
	}

	/** Calibrate the {@link Function}s. */
	private void importFunction(Sheet sheet, int column,
			MeasureEvaluation measureEvaluation) {
		LinearFunction function = (LinearFunction) measureEvaluation
				.getFunction();
		String functionName = sheet.getRow(2).getCell(column)
				.getStringCellValue();
		if (!function.eClass().getName().equals(functionName)) {
			function = (LinearFunction) EcoreUtil
					.create((EClass) QmPackage.eINSTANCE
							.getEClassifier(functionName));
			measureEvaluation.setFunction(function);
		}
		double lowerBound = sheet.getRow(3).getCell(column)
				.getNumericCellValue();
		function.setLowerBound(lowerBound);
		double upperBound = sheet.getRow(4).getCell(column)
				.getNumericCellValue();
		function.setUpperBound(upperBound);
	}

	/** Get the {@link MeasureRanking} for a certain measure. */
	private MeasureRanking getMeasureRanking(
			WeightedSumMultiMeasureEvaluation measureEvaluation,
			String measureName) {
		for (MeasureRanking ranking : measureEvaluation.getRankings()) {
			if (measureName.equals(ranking.getMeasure().getQualifiedName())) {
				return ranking;
			}
		}
		return null;
	}

	/** Get the evaluation with a certain identifier. */
	private EObject getEvaluation(String id) {
		List<QualityModel> qualityModels = QmModelUtils
				.getQualityModels(resourceSet);
		for (QualityModel qualityModel : qualityModels) {
			EObject element = UUIDUtils
					.getElement(qualityModel.eResource(), id);
			if (element != null) {
				return element;
			}
		}
		return null;
	}

	/** Calibrate a {@link FactorAggregation}. */
	private void importFactorAggregationCalibration(Sheet sheet,
			EAttribute attribute, IProgressMonitor monitor) {
		Row mainRow = sheet.getRow(0);
		for (Iterator<Cell> i = mainRow.cellIterator(); i.hasNext();) {
			Cell cell = i.next();
			int column = cell.getColumnIndex();
			if (column < 1) {
				continue;
			}
			String id = cell.getStringCellValue();
			EObject evaluation = getEvaluation(id);
			if (evaluation instanceof WeightedSumFactorAggregation) {
				WeightedSumFactorAggregation factorAggregation = (WeightedSumFactorAggregation) evaluation;
				importWeightedSumFactorAggregation(sheet, factorAggregation,
						attribute, column);
			} else {
				WeightedSumMultiMeasureEvaluation multiMeasureEvaluation = (WeightedSumMultiMeasureEvaluation) evaluation;
				importWeightedSumMultiMeasureEvaluation(sheet,
						multiMeasureEvaluation, attribute, column);
			}
			monitor.worked(1);
		}
	}

	/** Calibrate a {@link WeightedSumFactorAggregation}. */
	private void importWeightedSumFactorAggregation(Sheet sheet,
			WeightedSumFactorAggregation factorAggregation,
			EAttribute attribute, int column) {
		for (Iterator<Row> j = sheet.rowIterator(); j.hasNext();) {
			Row row = j.next();
			if (row.getRowNum() < 2) {
				continue;
			}
			try {
				Object value = row.getCell(column).getNumericCellValue();
				String factorName = row.getCell(0).getStringCellValue();
				FactorRanking ranking = getFactorRanking(factorAggregation,
						factorName);
				if (attribute.getEAttributeType() == EcorePackage.eINSTANCE
						.getEInt()) {
					value = ((Double) value).intValue();
				}
				ranking.eSet(attribute, value);
			} catch (RuntimeException e) {
				// ignore
			}
		}
	}

	/** Calibrate a {@link WeightedSumMultiMeasureEvaluation}. */
	private void importWeightedSumMultiMeasureEvaluation(Sheet sheet,
			WeightedSumMultiMeasureEvaluation multiMeasureEvaluation,
			EAttribute attribute, int column) {
		for (Iterator<Row> j = sheet.rowIterator(); j.hasNext();) {
			Row row = j.next();
			if (row.getRowNum() < 2) {
				continue;
			}
			try {
				Object value = row.getCell(column).getNumericCellValue();
				String measureName = row.getCell(0).getStringCellValue();
				MeasureRanking ranking = getMeasureRanking(
						multiMeasureEvaluation, measureName);
				if (attribute.getEAttributeType() == EcorePackage.eINSTANCE
						.getEInt()) {
					value = ((Double) value).intValue();
				}
				ranking.eSet(attribute, value);
			} catch (RuntimeException e) {
				// ignore
			}
		}
	}

	/** Get the {@link FactorRanking} for a certain factor. */
	private FactorRanking getFactorRanking(
			WeightedSumFactorAggregation factorAggregation, String factorName) {
		for (FactorRanking ranking : factorAggregation.getRankings()) {
			if (factorName.equals(ranking.getFactor().getQualifiedName())) {
				return ranking;
			}
		}
		return null;
	}

}