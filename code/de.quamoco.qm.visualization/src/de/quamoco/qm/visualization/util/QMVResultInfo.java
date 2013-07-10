/*-------------------------------------------------------------------------+
|                                                                          |
| Fraunhofer-Institut fuer Experimentelles Software Engineering (IESE)     |
|                                                                          |
|                                                                          |
|   NOT TO BE PUBLISHED UNDER QUAMOCO LICENSE                              |
|                                                                          |
+-------------------------------------------------------------------------*/

package de.quamoco.qm.visualization.util;

import iese.inseviz.util.log.IESELogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.MultiMeasureEvaluationResult;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Copyright (c) 12.09.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * A cache for evaluation result data to be used by the visualization. <br>
 * <br>
 * created: 12.09.2011 - 14:32:50<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * 
 */
public class QMVResultInfo
{
	private QMVQualityModelInfo modelInfo = null;
	private Map<QualityModelResult, Map<Factor, double[]>> factorResultMap = new HashMap<QualityModelResult, Map<Factor, double[]>>();
	private Map<QualityModelResult, Map<Measure, double[]>> measureResultMap = new HashMap<QualityModelResult, Map<Measure, double[]>>();
	private Map<QualityModelResult, Map<Measure, List<FindingMessage>>> measureFindingsMessagesMap = new HashMap<QualityModelResult, Map<Measure, List<FindingMessage>>>();

	/**
	 * Caches the evaluation value of factors regarding the given result.
	 * 
	 * @param mi
	 */
	public void collectResultInfo(QMVQualityModelInfo mi)
	{
		this.modelInfo = mi;

		for (QualityModel model : modelInfo.getModelList())
		{

			for (Factor factor : model.getFactors())
			{
				for (QualityModelResult result : modelInfo.getResultList())
				{
					{
						// try to get result for the factor
						EvaluationResult evalResult = QmModelUtils.getResult(result, factor);
						if (evalResult == null)
							continue;
						DoubleInterval value = evalResult.getValue();
						this.setValue(result, factor, value);

						if (evalResult instanceof MultiMeasureEvaluationResult)
						{
							MultiMeasureEvaluationResult mmEvalResult = (MultiMeasureEvaluationResult) evalResult;
							for (MeasureRankingEvaluationResult mrer : mmEvalResult.getEvaluationResults())
							{
								value = mrer.getValue();
								Measure measure = mrer.getResultsFrom().getMeasure();
								this.setValue(result, measure, value);

								// System.out.println(factor.getQualifiedName()
								// + ":");
								// System.out.println("\tmeasure:"
								// + measure.getQualifiedName());
								// System.out.println("\tvalue:" + value);

							}
						}
					}
					{
						// try to get result for the measure
						List<Measure> measuredByList = factor.getMeasuredByDirect();
						for (Measure measure : measuredByList)
						{
							MeasurementResult measurementResult = QmModelUtils.getResult(result, measure);
							if (measurementResult == null)
								continue;

							if (measurementResult instanceof NumberMeasurementResult)
							{
								NumberMeasurementResult nmr = (NumberMeasurementResult) measurementResult;
								DoubleInterval value = nmr.getValue();
								this.setValue(result, measure, value);
								// System.out.println("MEASURE VALUE found:");
								// System.out.println("\tmeasure:"
								// + measure.getQualifiedName());
								// System.out.println("\tfactor:"
								// + factor.getQualifiedName());
								// System.out.println("\tresult:"
								// + result.getSystem());
								// System.out.println("\tmodel:"
								// + model.getQualifiedName());
							}
							if (measurementResult instanceof FindingsMeasurementResult)
							{
								FindingsMeasurementResult fmr = (FindingsMeasurementResult) measurementResult;

								setFindingMessages(result, measure, fmr.getFindingMessages());

							}
						}
					}
				}
			}
		}
	}

	/**
	 * Caches the evaluation value of a factor regarding the given result.
	 * 
	 * @param result the evaluation result
	 * @param factor the factor
	 * @param value the value to store
	 */
	private void setValue(QualityModelResult result, Factor factor, DoubleInterval value)
	{
		Map<Factor, double[]> map = factorResultMap.get(result);
		if (map == null)
		{
			map = new HashMap<Factor, double[]>();
			factorResultMap.put(result, map);
		}
		double min = Math.max(0.0, value.getLower());
		double max = Math.min(1.0, value.getUpper());
		map.put(factor, new double[] {
		        min, max
		});
	}

	private void setValue(QualityModelResult result, Measure measure, DoubleInterval value)
	{
		Map<Measure, double[]> map = measureResultMap.get(result);
		if (map == null)
		{
			map = new HashMap<Measure, double[]>();
			measureResultMap.put(result, map);
		}
		double min = Math.max(0.0, value.getLower());
		double max = Math.min(1.0, value.getUpper());
		map.put(measure, new double[] {
		        min, max
		});
	}

	private void setFindingMessages(QualityModelResult result, Measure measure, List<FindingMessage> messages)
	{

		Map<Measure, List<FindingMessage>> map = measureFindingsMessagesMap.get(result);
		if (map == null)
		{
			map = new HashMap<Measure, List<FindingMessage>>();
			measureFindingsMessagesMap.put(result, map);
		}
		map.put(measure, messages);
	}

	/**
	 * Print out the stored information for factors.
	 * 
	 * @param comment
	 */
	public void print(String comment)
	{
		IESELogger.info(comment);
		IESELogger.incPrefix();

		List<Factor> noValueFactors = new ArrayList<Factor>();

		for (QualityModel qmModel : modelInfo.getModelList())
		{
			IESELogger.info("Quality Model:" + qmModel.getQualifiedName());
			List<Factor> qmFactors = qmModel.getFactors();
			IESELogger.info("#factors:" + qmFactors.size());
			for (Factor factor : qmFactors)
			{
				IESELogger.incPrefix();
				IESELogger.info("factor:" + factor.getQualifiedName());
				for (QualityModelResult qmResult : modelInfo.getResultList())
				{
					IESELogger.incPrefix();
					IESELogger.info("Quality Model Result " + qmResult.getSystem() + ":");
					Map<Factor, double[]> factorValueMap = factorResultMap.get(qmResult);
					double value[] = factorValueMap.get(factor);

					IESELogger.incPrefix();
					if (value == null)
					{
						IESELogger.info("found no value");
						if (noValueFactors.contains(factor) == false)
							noValueFactors.add(factor);
					}
					else
						IESELogger.info("value=[" + value[0] + "," + value[1] + "]");
					IESELogger.decPrefix();
					IESELogger.decPrefix();
				}
				IESELogger.decPrefix();
			}
		}
		IESELogger.info("#factors with no value found:" + noValueFactors.size());
		IESELogger.info(QMVUtils.toString(noValueFactors));
		IESELogger.decPrefix();
	}

	/**
	 * Returns the result value (min,max) for a factor in the given evaluation result. The values are clamped to be in
	 * [0,1].
	 * 
	 * @param qmResult
	 * @param factor
	 * @return the clamped weight in [0,1]
	 */
	public double[] getValue(QualityModelResult qmResult, Factor factor)
	{
		Map<Factor, double[]> factorValueMap = factorResultMap.get(qmResult);
		return factorValueMap.get(factor);
	}

	public double[] getValue(QualityModelResult qmResult, Measure measure)
	{
		Map<Measure, double[]> measureValueMap = measureResultMap.get(qmResult);
		return measureValueMap.get(measure);
	}

	public List<FindingMessage> getFindingMessages(QualityModelResult qmResult, Measure measure)
	{
		Map<Measure, List<FindingMessage>> map = measureFindingsMessagesMap.get(qmResult);
		return map.get(measure);
	}

	public QMVQualityModelInfo getModelInfo()
	{
		return modelInfo;
	}
}
