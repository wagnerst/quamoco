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
$Id: ResultCalibrationExporter.java 4974 2012-02-17 09:34:10Z lochmann $
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.quamoco.qm.Effect;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Function;
import de.quamoco.qm.Impact;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.SingleMeasureEvaluationResult;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.resources.UUIDUtils;

/**
 * Helper class to export calibration data to an Excel file.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @ConQAT.Rating YELLOW Hash: 44F3D2989284192F41CBC16210A73C00
 */
public class ResultCalibrationExporter {

	/** The resource set with the quality model and the results. */
	private final ResourceSet resourceSet;

	/** The results that should be exported. */
	private List<QualityModelResult> results;

	/** The quality models that are currently loaded. */
	private List<QualityModel> qualityModels;

	/** The list of all loaded {@link MeasureEvaluation}s. */
	private List<MeasureEvaluation> measureEvaluations;

	/** The list of all loaded {@link WeightedSumFactorAggregation}s. */
	private List<WeightedSumFactorAggregation> factorAggregations;

	/** The list of all loaded {@link WeightedSumMultiMeasureEvaluation}. */
	private List<WeightedSumMultiMeasureEvaluation> multiMeasureEvaluations;

	/** The list of all loaded {@link Factor}s. */
	private List<Factor> factors;

	/** The list of all loaded {@link Measure}. */
	private List<Measure> measures;

	/** Constructor. */
	public ResultCalibrationExporter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	/** Export the calibration data to an Excel file with a certain name. */
	public void export(String fileName, IProgressMonitor monitor)
			throws IOException {

		init();

		monitor.beginTask("Export " + fileName,
				measureEvaluations.size() + 3 * factorAggregations.size() + 3
						* multiMeasureEvaluations.size());

		Workbook workbook = fileName.endsWith("."
				+ EExcelVersion.EXCEL_2007.getExtension()) ? new XSSFWorkbook()
				: new HSSFWorkbook();

		createMeasureEvaluationSheet(workbook, monitor);
		createFactorAggregationSheet(workbook,
				QmPackage.eINSTANCE.getRanking_Weight(), "AggregationWeight",
				monitor);
		createFactorAggregationSheet(workbook,
				QmPackage.eINSTANCE.getRanking_Rank(), "AggregationRank",
				monitor);
		createFactorImpactSheet(workbook, monitor);

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(fileName);
			workbook.write(fileOut);
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
			monitor.done();
		}
	}

	/** Initialize the data that is needed for the export. */
	private void init() {
		results = QmModelUtils.getRootElementsOfType(resourceSet,
				QualityModelResult.class);
		qualityModels = QmModelUtils.getQualityModels(resourceSet);
		measureEvaluations = QmModelUtils.getContainedElementsOfType(
				qualityModels, MeasureEvaluation.class);
		factorAggregations = QmModelUtils.getContainedElementsOfType(
				qualityModels, WeightedSumFactorAggregation.class);
		multiMeasureEvaluations = QmModelUtils.getContainedElementsOfType(
				qualityModels, WeightedSumMultiMeasureEvaluation.class);
		factors = QmModelUtils.getContainedElementsOfType(qualityModels,
				Factor.class);
		measures = QmModelUtils.getContainedElementsOfType(qualityModels,
				Measure.class);
	}

	/** Create the sheet to display the {@link MeasureEvaluation}s. */
	private void createMeasureEvaluationSheet(Workbook workbook,
			IProgressMonitor monitor) throws IOException {
		checkNumberOfColumns(workbook, measureEvaluations.size() + 1);

		Sheet sheet = workbook.createSheet("Measures");
		sheet.setDefaultColumnWidth(30);

		int header = createMeasureEvaluationSheetHeader(sheet);
		createMeasureEvaluationSheetBody(sheet, header, monitor);

		autoSizeColumns(sheet);
	}

	/**
	 * Create the header of the sheet to display the {@link MeasureEvaluation}s.
	 * The header is shown in the first column.
	 */
	private int createMeasureEvaluationSheetHeader(Sheet sheet) {
		String[] names = new String[] { "Factor Name", "Measure Name",
				"Evaluation Function", "Lower Bound", "Upper Bound",
				"Evaluation ID" };

		for (int row = 0; row < names.length; row++) {
			sheet.createRow(row).createCell(0).setCellValue(names[row]);
		}
		int row = names.length;
		for (QualityModelResult result : results) {
			sheet.createRow(row).createCell(0).setCellValue(result.getSystem());
			row++;
		}
		return names.length;
	}

	/**
	 * Create the body of the sheet to display the {@link MeasureEvaluation}.
	 * There is a column for each {@link MeasureEvaluation}.
	 */
	private void createMeasureEvaluationSheetBody(Sheet sheet, int header,
			IProgressMonitor monitor) {
		int column = 1;
		for (MeasureEvaluation measureEvaluation : measureEvaluations) {
			Evaluation evaluation = null;
			if (measureEvaluation instanceof SingleMeasureEvaluation) {
				evaluation = (SingleMeasureEvaluation) measureEvaluation;
			} else {
				evaluation = (MultiMeasureEvaluation) measureEvaluation
						.eContainer();
			}
			sheet.getRow(0).createCell(column)
					.setCellValue(evaluation.getEvaluates().getQualifiedName());
			sheet.getRow(1)
					.createCell(column)
					.setCellValue(
							measureEvaluation.getMeasure().getQualifiedName());
			Function function = measureEvaluation.getFunction();
			sheet.getRow(2).createCell(column)
					.setCellValue(function.eClass().getName());
			LinearFunction linearFunction = (LinearFunction) function;
			sheet.getRow(3).createCell(column)
					.setCellValue(linearFunction.getLowerBound());
			sheet.getRow(4).createCell(column)
					.setCellValue(linearFunction.getUpperBound());
			sheet.getRow(5).createCell(column)
					.setCellValue(UUIDUtils.getId(evaluation));

			if (measureEvaluation instanceof SingleMeasureEvaluation) {
				SingleMeasureEvaluation singleMeasureEvaluation = (SingleMeasureEvaluation) measureEvaluation;
				exportSingleMeasureEvaluation(sheet, singleMeasureEvaluation,
						header, column);
			} else {
				MeasureRanking measureRanking = (MeasureRanking) measureEvaluation;
				exportMeasureRanking(sheet, measureRanking, header, column);
			}

			monitor.worked(1);
			column++;
		}
	}

	/** Export a {@link SingleMeasureEvaluation}. */
	private void exportSingleMeasureEvaluation(Sheet sheet,
			SingleMeasureEvaluation singleMeasureEvaluation, int header,
			int column) {
		int row = header;
		List<SingleMeasureEvaluationResult> evaluationResults = QmModelUtils
				.getInverse(
						QmPackage.eINSTANCE.getEvaluationResult_ResultsFrom(),
						singleMeasureEvaluation,
						SingleMeasureEvaluationResult.class);
		for (QualityModelResult result : results) {
			SingleMeasureEvaluationResult evaluationResult = getSingleMeasureEvaluationResult(
					evaluationResults, result);
			sheet.getRow(row).createCell(column)
					.setCellValue(evaluationResult.getRatioAffected());
			row++;
		}
	}

	/**
	 * Get the {@link SingleMeasureEvaluationResult} that is in a certain
	 * {@link QualityModelResult}.
	 */
	private SingleMeasureEvaluationResult getSingleMeasureEvaluationResult(
			List<SingleMeasureEvaluationResult> evaluationResults,
			QualityModelResult result) {
		for (SingleMeasureEvaluationResult evaluationResult : evaluationResults) {
			if (QmModelUtils.isContainedBy(evaluationResult, result)) {
				return evaluationResult;
			}
		}
		return null;
	}

	/** Export a {@link MeasureRanking}. */
	private void exportMeasureRanking(Sheet sheet,
			MeasureRanking measureRanking, int header, int column) {
		int row = header;
		List<MeasureRankingEvaluationResult> evaluationResults = QmModelUtils
				.getInverse(QmPackage.eINSTANCE
						.getMeasureRankingEvaluationResult_ResultsFrom(),
						measureRanking, MeasureRankingEvaluationResult.class);
		for (QualityModelResult result : results) {
			MeasureRankingEvaluationResult evaluationResult = getMeasureRankingEvaluationResult(
					evaluationResults, result);
			if (evaluationResult != null) {
				sheet.getRow(row).createCell(column)
						.setCellValue(evaluationResult.getRatioAffected());
			}
			row++;
		}
	}

	/**
	 * Get the {@link MeasureRankingEvaluationResult} that is in a certain
	 * {@link QualityModelResult}.
	 */
	private MeasureRankingEvaluationResult getMeasureRankingEvaluationResult(
			List<MeasureRankingEvaluationResult> evaluationResults,
			QualityModelResult result) {
		for (MeasureRankingEvaluationResult evaluationResult : evaluationResults) {
			if (QmModelUtils.isContainedBy(evaluationResult, result)) {
				return evaluationResult;
			}
		}
		return null;
	}

	/** Create the sheet to display the {@link FactorAggregation}s. */
	private void createFactorAggregationSheet(Workbook workbook,
			EAttribute attribute, String title, IProgressMonitor monitor)
			throws IOException {
		Sheet sheet = workbook.createSheet(title);

		int header = createFactorAggregationSheetHeader(sheet);
		createFactorAggregationSheetBody(sheet, attribute, header, monitor);

		autoSizeColumns(sheet);
	}

	/** Helper method to automatically set the width of all columns. */
	private void autoSizeColumns(Sheet sheet) {
		for (Iterator<Cell> i = sheet.getRow(0).cellIterator(); i.hasNext();) {
			Cell cell = i.next();
			sheet.autoSizeColumn(cell.getColumnIndex());
		}
	}

	/**
	 * Create the header of the sheet to display the {@link FactorAggregation}s.
	 * The header is shown in the first column.
	 */
	private int createFactorAggregationSheetHeader(Sheet sheet) {
		String[] names = new String[] { "Evaluation ID", "Factor Name" };

		int header = names.length;
		for (int row = 0; row < header; row++) {
			sheet.createRow(row).createCell(0).setCellValue(names[row]);
		}

		int row = header;
		for (Factor factor : factors) {
			sheet.createRow(row).createCell(0)
					.setCellValue(factor.getQualifiedName());
			row++;
		}

		for (Measure measure : measures) {
			sheet.createRow(row).createCell(0)
					.setCellValue(measure.getQualifiedName());
			row++;
		}
		return header;
	}

	/**
	 * Create the body of the sheet to display the {@link FactorAggregation}s.
	 * There is a column for each {@link FactorAggregation}.
	 */
	private void createFactorAggregationSheetBody(Sheet sheet,
			EAttribute attribute, int header, IProgressMonitor monitor)
			throws IOException {
		checkNumberOfColumns(sheet.getWorkbook(),
				multiMeasureEvaluations.size() + factorAggregations.size() + 1);
		int column = 1;
		for (WeightedSumFactorAggregation aggregation : factorAggregations) {
			sheet.getRow(0).createCell(column)
					.setCellValue(UUIDUtils.getId(aggregation));
			sheet.getRow(1)
					.createCell(column)
					.setCellValue(aggregation.getEvaluates().getQualifiedName());
			for (FactorRanking ranking : aggregation.getRankings()) {
				int row = factors.indexOf(ranking.getFactor()) + header;
				Object value = ranking.eGet(attribute);
				double doubleValue = 0.0;
				if (value instanceof Integer) {
					doubleValue = (Integer) value;
				} else {
					doubleValue = (Double) value;
				}
				sheet.getRow(row).createCell(column).setCellValue(doubleValue);
			}
			column++;
			monitor.worked(1);
		}

		for (WeightedSumMultiMeasureEvaluation aggregation : multiMeasureEvaluations) {
			sheet.getRow(0).createCell(column)
					.setCellValue(UUIDUtils.getId(aggregation));
			sheet.getRow(1)
					.createCell(column)
					.setCellValue(aggregation.getEvaluates().getQualifiedName());
			for (MeasureRanking ranking : aggregation.getRankings()) {
				int row = factors.size()
						+ measures.indexOf(ranking.getMeasure()) + header;
				Object value = ranking.eGet(attribute);
				double doubleValue = 0.0;
				if (value instanceof Integer) {
					doubleValue = (Integer) value;
				} else {
					doubleValue = (Double) value;
				}
				sheet.getRow(row).createCell(column).setCellValue(doubleValue);
			}
			column++;
			monitor.worked(1);
		}
	}

	/**
	 * Check whether the number of columns is supported by the chosen Excel
	 * version.
	 */
	private void checkNumberOfColumns(Workbook workbook, int number)
			throws IOException {
		if (workbook instanceof HSSFWorkbook && number > 256) {
			throw new IOException(EExcelVersion.EXCEL_2003.getText()
					+ " only supports 256 columns. Please export to "
					+ EExcelVersion.EXCEL_2007.getText() + " (."
					+ EExcelVersion.EXCEL_2007.getExtension() + ").");
		}
	}

	/**
	 * Create the sheet to display {@link Impact}s and {@link FactorRefinement}s
	 * to {@link FactorAggregation}s.
	 */
	private void createFactorImpactSheet(Workbook workbook,
			IProgressMonitor monitor) throws IOException {
		Sheet sheet = workbook.createSheet("Impact");

		int header = createFactorAggregationSheetHeader(sheet);
		createFactorImpactSheetBody(sheet, header, monitor);

		autoSizeColumns(sheet);
	}

	/**
	 * Create the body of the sheet to display {@link Impact}s and
	 * {@link FactorRefinement}s to {@link FactorAggregation}s. There is a
	 * column for each {@link FactorAggregation}.
	 */
	private void createFactorImpactSheetBody(Sheet sheet, int header,
			IProgressMonitor monitor) throws IOException {
		checkNumberOfColumns(sheet.getWorkbook(), factorAggregations.size() + 1);
		int column = 1;
		for (WeightedSumFactorAggregation aggregation : factorAggregations) {
			createFactorImpactColumn(sheet, aggregation, monitor, column,
					header);
			column++;
		}
		for (WeightedSumMultiMeasureEvaluation evaluation : multiMeasureEvaluations) {
			createFactorImpactColumn(sheet, evaluation, monitor, column, header);
			column++;
		}
	}

	/**
	 * Create the column to display {@link Impact}s and {@link FactorRefinement}
	 * s to {@link FactorAggregation}s.
	 */
	private void createFactorImpactColumn(Sheet sheet, Evaluation evaluation,
			IProgressMonitor monitor, int column, int header) {
		sheet.getRow(0).createCell(column)
				.setCellValue(UUIDUtils.getId(evaluation));
		sheet.getRow(1).createCell(column)
				.setCellValue(evaluation.getEvaluates().getQualifiedName());
		for (Factor factor : factors) {
			Impact impact = QmModelUtils.getImpact(factor,
					evaluation.getEvaluates());
			int row = factors.indexOf(factor) + header;
			if (impact != null) {
				if (impact.getEffect() == Effect.POSITIVE) {
					sheet.getRow(row).createCell(column).setCellValue("+");
				} else {
					sheet.getRow(row).createCell(column).setCellValue("-");
				}
			}
			FactorRefinement refinement = QmModelUtils.getRefinement(
					evaluation.getEvaluates(), factor);
			if (refinement != null) {
				sheet.getRow(row).createCell(column).setCellValue("r");
			}
		}
		monitor.worked(1);
	}
}