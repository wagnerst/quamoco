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

package de.quamoco.qm.editor.excelexport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Function;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Instrument;
import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.editor.export.EExcelVersion;
import de.quamoco.qm.util.QmModelUtils;

public class HumanReadableExcelExporter {

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

	/** The viewpoint of this qualitymodel is taken */
	private QualityModel context;

	/**
	 * Constructor
	 */
	public HumanReadableExcelExporter(QualityModel context,
			ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		this.context = context;
	}

	public void export(String fileName, IProgressMonitor monitor)
			throws IOException {
		init();

		monitor.beginTask("Export " + fileName,
				measureEvaluations.size() + 3 * factorAggregations.size() + 3
						* multiMeasureEvaluations.size());

		Workbook workbook = fileName.endsWith("."
				+ EExcelVersion.EXCEL_2007.getExtension()) ? new XSSFWorkbook()
				: new HSSFWorkbook();

		createAllInOneSheet(workbook);
		createMeasureSheet(workbook);
		createFactorSheet(workbook);
		createQASheet(workbook);

		// createMeasureEvaluationSheet(workbook, monitor);
		//
		// createFactorAggregationSheet(workbook,
		// QmPackage.eINSTANCE.getRanking_Weight(), "AggregationWeight",
		// monitor);
		//
		// createFactorAggregationSheet(workbook,
		// QmPackage.eINSTANCE.getRanking_Rank(), "AggregationRank",
		// monitor);
		//
		// createFactorImpactSheet(workbook, monitor);

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

	private void createAllInOneSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet("All-In-One");
		sheet.setDefaultColumnWidth(30);

		String[] names = new String[] { "Measure Name", "Tool",
				"Normalization", "Measure Description", "Measure Weight",
				"Influenced Factor", "Factor weight",
				"Impacted Quality Aspect", "Effect of the Impact",
				"Rationale for the Impact", "Description of the Quality Aspect" };

		Row header = sheet.createRow(0);
		for (int col = 0; col < names.length; col++) {
			header.createCell(col).setCellValue(names[col]);
		}

		int row = 1;
		for (Measure measure : measures) {
			for (Factor factor : measure.getMeasuresDirect()) {

				for (Impact imp : factor.getInfluences()) {
					Factor qa = imp.getTarget();
					Evaluation qaeval = qa.getActualEvaluation(context);
					
					WeightedSumFactorAggregation wsf = null;
					if (!(qaeval instanceof WeightedSumFactorAggregation)) {
						System.err.println("WARNING: factor '" + factor + "' qa '"
								+ qa
								+ "' does not have a WeightedSumFactorAggregation");
					} else {
						wsf = (WeightedSumFactorAggregation) qaeval;
					}

					double qaweight = Double.NaN;
					if (wsf != null) {
						for (Ranking ranking : wsf.getRankings()) {
							if (ranking.getRankedElement() == factor) {
								qaweight = ranking.getWeight();
							}
						}
					}

					Evaluation factoreval = factor.getActualEvaluation(context);
					WeightedSumMultiMeasureEvaluation mme = null;
					if (factoreval == null) {
						System.err.println("WARNING: measure '"
								+ measure.getQualifiedName() + "' factor '"
								+ factor.getQualifiedName()
								+ "' does not have an evaluation at all.");
					} else if (!(factoreval instanceof WeightedSumMultiMeasureEvaluation)) {
						System.err
								.println("WARNING: measure '"
										+ measure.getQualifiedName()
										+ "' factor '"
										+ factor.getQualifiedName()
										+ "' does not have a multiMeasureEvaluation, but a "
										+ factoreval.getClass());
					} else {
						mme = (WeightedSumMultiMeasureEvaluation) factoreval;
					}

					String norm = "NO NORMALIZATION";
					double weight = Double.NaN;
					if (mme != null) {
						for (Ranking ranking : mme.getRankings()) {
							if (ranking.getRankedElement() == measure) {
								weight = ranking.getWeight();
								if (ranking instanceof MeasureRanking) {
									MeasureRanking mr = (MeasureRanking) ranking;
									Function f = mr.getFunction();

									norm = mr.getRange()
											+ printFunction(f)
											+ (mr.getNormlizationMeasure() != null ? mr
													.getNormlizationMeasure()
													.getQualifiedName() : "");
								}
							}
						}
					}

					Row r = sheet.createRow(row++);

//					"Measure Name", 
//					"Tool",
//					"Normalization", 
//					"Measure Description", 
//					"Measure Weight",
//					"Influenced Factor", 
//					"Factor weight",
//					"Impacted Quality Aspect", 
//					"Effect of the Impact",
//					"Rationale for the Impact", 
//					"Description of the Quality Aspect"
					
					int cell = 0;
					r.createCell(cell++).setCellValue(
							measure.getQualifiedName());
					r.createCell(cell++).setCellValue(getToolName(measure));
					r.createCell(cell++).setCellValue(norm);
					r.createCell(cell++).setCellValue(measure.getDescription());
					if (!Double.isNaN(weight)) {
						r.createCell(cell++).setCellValue(weight);
					} else {
						r.createCell(cell++).setCellValue("NO WEIGHT GIVEN.");
					}
					r.createCell(cell++)
							.setCellValue(factor.getQualifiedName());
					if (!Double.isNaN(qaweight)) {
						r.createCell(cell++).setCellValue(qaweight);
					} else {
						r.createCell(cell++).setCellValue("NO WEIGHT GIVEN.");
					}
					r.createCell(cell++).setCellValue(qa.getQualifiedName());
					r.createCell(cell++).setCellValue(imp.getEffect().getLiteral());
					r.createCell(cell++).setCellValue(imp.getJustification());
					r.createCell(cell++).setCellValue(qa.getDescription());
				}
			}
		}
	}

	private void createMeasureSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet("Measures");
		sheet.setDefaultColumnWidth(30);

		String[] names = new String[] { "Measure Name", "Tool",
				"Normalization", "Measure Description", "Measure Weight",
				"Influenced Factor" };

		Row header = sheet.createRow(0);
		for (int col = 0; col < names.length; col++) {
			header.createCell(col).setCellValue(names[col]);
		}

		int row = 1;
		for (Measure measure : measures) {
			for (Factor factor : measure.getMeasuresDirect()) {
				Evaluation eval = factor.getActualEvaluation(context);
				WeightedSumMultiMeasureEvaluation mme = null;
				if (eval == null) {
					System.err.println("WARNING: measure '"
							+ measure.getQualifiedName() + "' factor '"
							+ factor.getQualifiedName()
							+ "' does not have an evaluation at all.");
				} else if (!(eval instanceof WeightedSumMultiMeasureEvaluation)) {
					System.err
							.println("WARNING: measure '"
									+ measure.getQualifiedName()
									+ "' factor '"
									+ factor.getQualifiedName()
									+ "' does not have a multiMeasureEvaluation, but a "
									+ eval.getClass());
				} else {
					mme = (WeightedSumMultiMeasureEvaluation) eval;
				}

				String norm = "NO NORMALIZATION";
				double weight = Double.NaN;
				if (mme != null) {
					for (Ranking ranking : mme.getRankings()) {
						if (ranking.getRankedElement() == measure) {
							weight = ranking.getWeight();
							if (ranking instanceof MeasureRanking) {
								MeasureRanking mr = (MeasureRanking) ranking;
								Function f = mr.getFunction();

								norm = mr.getRange()
										+ printFunction(f)
										+ (mr.getNormlizationMeasure() != null ? mr
												.getNormlizationMeasure()
												.getQualifiedName() : "");
							}
						}
					}
				}

				Row r = sheet.createRow(row++);

				int cell = 0;
				r.createCell(cell++).setCellValue(measure.getQualifiedName());
				r.createCell(cell++).setCellValue(getToolName(measure));
				r.createCell(cell++).setCellValue(norm);
				r.createCell(cell++).setCellValue(measure.getDescription());
				if (!Double.isNaN(weight)) {
					r.createCell(cell++).setCellValue(weight);
				} else {
					r.createCell(cell++).setCellValue("NO WEIGHT GIVEN.");
				}
				r.createCell(cell++).setCellValue(factor.getQualifiedName());
			}
		}
	}

	private String printFunction(Function f) {
		if (f instanceof LinearIncreasingFunction) {
			return " linearIncreasing("
					+ ((LinearIncreasingFunction) f).getLowerBound() + ","
					+ ((LinearIncreasingFunction) f).getUpperBound() + ") ";
		}

		if (f instanceof LinearDecreasingFunction) {
			return " linearDecreasing("
					+ ((LinearDecreasingFunction) f).getLowerBound() + ","
					+ ((LinearDecreasingFunction) f).getUpperBound() + ") ";
		}

		return " UNKNOWN ";

	}

	private void createFactorSheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet("Factors");
		sheet.setDefaultColumnWidth(30);

		String[] names = new String[] { "Factor Name", "Factor Description",
				"Factor Weight", "Impacted Quality Aspect",
				"Effect of the Impact", "Rationale for the Impact" };

		Row header = sheet.createRow(0);
		for (int col = 0; col < names.length; col++) {
			header.createCell(col).setCellValue(names[col]);
		}

		int row = 1;
		for (Factor factor : factors) {

			if (factor.getCharacterizes() == null) {
				continue;
			}

			for (Impact imp : factor.getInfluences()) {
				Factor qa = imp.getTarget();
				Evaluation eval = qa.getActualEvaluation(context);

				WeightedSumFactorAggregation wsf = null;
				if (!(eval instanceof WeightedSumFactorAggregation)) {
					System.err.println("WARNING: factor '" + factor + "' qa '"
							+ qa
							+ "' does not have a WeightedSumFactorAggregation");
				} else {
					wsf = (WeightedSumFactorAggregation) eval;
				}

				double weight = Double.NaN;
				if (wsf != null) {
					for (Ranking ranking : wsf.getRankings()) {
						if (ranking.getRankedElement() == factor) {
							weight = ranking.getWeight();
						}
					}
				}

				Row r = sheet.createRow(row++);

				int cell = 0;
				r.createCell(cell++).setCellValue(factor.getQualifiedName());
				r.createCell(cell++).setCellValue(factor.getDescription());
				r.createCell(cell++).setCellValue(weight);
				r.createCell(cell++).setCellValue(qa.getQualifiedName());
				r.createCell(cell++).setCellValue(imp.getEffect().getLiteral());
				r.createCell(cell++).setCellValue(imp.getJustification());
			}
		}
	}

	private void createQASheet(Workbook workbook) {
		Sheet sheet = workbook.createSheet("QualityAspects");
		sheet.setDefaultColumnWidth(30);

		String[] names = new String[] { "QualityAspect Name",
				"Quality Aspect Description" };

		Row header = sheet.createRow(0);
		for (int col = 0; col < names.length; col++) {
			header.createCell(col).setCellValue(names[col]);
		}

		int row = 1;
		for (Factor factor : factors) {

			if (factor.getCharacterizes() != null) {
				continue;
			}

			Row r = sheet.createRow(row++);

			int cell = 0;
			r.createCell(cell++).setCellValue(factor.getQualifiedName());
			r.createCell(cell++).setCellValue(factor.getDescription());
		}
	}

	private String getToolName(Measure measure) {
		MeasurementMethod mm = measure.getActualMeasurementMethod(context);
		String s;
		if (mm instanceof Instrument) {
			Instrument i = (Instrument) mm;
			if (i instanceof ManualInstrument) {
				ManualInstrument mi = (ManualInstrument) i;
				s = "Manual Inspection: \"" + mi.getName() + "\": "
						+ mi.getDescription();
			} else if (i instanceof ToolBasedInstrument) {
				ToolBasedInstrument tbi = (ToolBasedInstrument) i;
				s = tbi.getTool().getName() + ": " + tbi.getMetric();
			} else {
				System.err.println("WARNING: Measure "
						+ measure.getQualifiedName()
						+ " has a strange instrument.");
				s = "WARNING";
			}
		} else {
			System.err.println("WARNING: Measure " + measure.getQualifiedName()
					+ " has a MM that is not an Instrument.");
			s = "WARNING";
		}
		return s;
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

}
