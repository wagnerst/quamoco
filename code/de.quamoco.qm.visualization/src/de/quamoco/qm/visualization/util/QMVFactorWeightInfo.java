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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Copyright (c) 15.09.2011 Fraunhofer IESE. All rights reserved.<br>
 * <br>
 * A cache for factor weighting data to be used by the visualization. <br>
 * <br>
 * created: 15.09.2011 - 17:26:54<br>
 * author: Dr. Henning Barthel.<br>
 * project: QuamocoVis<br>
 * <br>
 * 
 */
public class QMVFactorWeightInfo {
	private QMVQualityModelInfo modelInfo = null;
	private Map<QualityModelElement, Map<QualityModelElement, Double>> factorWeightMap = new HashMap<QualityModelElement, Map<QualityModelElement, Double>>();

	/**
	 * Caches the weights of factors.
	 * 
	 * @param mi
	 */
	public void collectWeightInfo(QMVQualityModelInfo mi) {
		this.modelInfo = mi;

		for (QualityModel qmModel : modelInfo.getModelList()) {
			List<Factor> qmFactors = qmModel.getFactors();
			IESELogger.info("collectWeightInfo for qmModel:"
					+ qmModel.getQualifiedName());
			IESELogger.incPrefix();
			IESELogger.info("#factors:" + qmFactors.size());
			IESELogger.info("factors:" + QMVUtils.toString(qmFactors));

			IESELogger.incPrefix();
			for (Factor factor : qmFactors) {
				IESELogger.info("collect child factor weights for factor "
						+ factor.getQualifiedName() + ":");
				IESELogger.info("deepestModel("
						+ factor.getQualifiedName()
						+ "):"
						+ QmModelUtils
								.getDeepestModelInRequiresRelation(factor)
								.getQualifiedName());

				collectWeightInfo(factor);
			}
			IESELogger.decPrefix();

			IESELogger.decPrefix();
		}

		// toplevel weights! do not have an evaluation?!
		{
			Factor root = modelInfo.getProductRootFactor();
			Map<QualityModelElement, Double> weightMap = new HashMap<QualityModelElement, Double>();
			factorWeightMap.put(root, weightMap);
			for (Factor childFactor : root.getRefinedByDirect()) {
				weightMap.put(childFactor, 1.0);
				IESELogger.info("weight(" + root.getQualifiedName() + ","
						+ childFactor.getQualifiedName() + ") added");
				Map<QualityModelElement, Double> childWeightMap = new HashMap<QualityModelElement, Double>();
				factorWeightMap.put(childFactor, childWeightMap);

				for (Factor childchildFactor : childFactor.getRefinedByDirect()) {
					childWeightMap.put(childchildFactor, 1.0);
				}
			}

		}
	}

	/**
	 * Collects the weighting info for the given factor.
	 * 
	 * @param factor
	 */
	private void collectWeightInfo(Factor factor) {
		Map<QualityModelElement, Double> weightMap = null;
		Evaluation evaluation = QmModelUtils
				.getActualEvaluationInDeepestModel(factor);
		if (evaluation == null) {
			// System.err.println("\t\tWARNING:NO EVALUATION FOR " +
			// factor.getQualifiedName());
			// System.err.flush();
		}
		if (evaluation instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation factorAggregation = (WeightedSumFactorAggregation) evaluation;
			for (FactorRanking ranking : factorAggregation.getRankings()) {
				Factor rankedFactor = ranking.getFactor();
				double weight = ranking.getWeight();

				if (weightMap == null) {
					weightMap = new HashMap<QualityModelElement, Double>();
					factorWeightMap.put(factor, weightMap);
				}
				weightMap.put(rankedFactor, weight);

				IESELogger.incPrefix();
				IESELogger.info("weight(" + rankedFactor.getQualifiedName()
						+ "):" + weight);
				IESELogger.decPrefix();
			}
		}
		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			WeightedSumMultiMeasureEvaluation measureEvaluation = (WeightedSumMultiMeasureEvaluation) evaluation;

			for (MeasureRanking ranking : measureEvaluation.getRankings()) {
				Measure measure = ranking.getMeasure();
				double weight = ranking.getWeight();
				if (weightMap == null) {
					weightMap = new HashMap<QualityModelElement, Double>();
					factorWeightMap.put(factor, weightMap);
				}
				weightMap.put(measure, weight);

				IESELogger.incPrefix();
				IESELogger.info("measure weight(" + measure.getQualifiedName()
						+ "):" + weight);
				IESELogger.decPrefix();
			}
		}
	}

	/**
	 * Return the weight of a child factor with respect to its parent factor.
	 * 
	 * @param parentFactor
	 *            the parent
	 * @param childFactor
	 *            the child
	 * @return the weight
	 */
	public Double getWeight(QualityModelElement parentFactor,
			QualityModelElement childFactor) {
		Map<QualityModelElement, Double> weightMap = factorWeightMap
				.get(parentFactor);
		if (weightMap == null)
			return null;
		return weightMap.get(childFactor);
	}

	/**
	 * Prints out the cached information.
	 * 
	 * @param comment
	 */
	public void print(String comment) {
		IESELogger.info(comment);
		IESELogger.incPrefix();
		for (QualityModelElement factor : factorWeightMap.keySet()) {
			IESELogger.info("Factor "
					+ factor.getQualifiedName()
					+ ":("
					+ QmModelUtils.getDeepestModelInRequiresRelation(factor)
							.getQualifiedName() + ")");
			Map<QualityModelElement, Double> weightMap = factorWeightMap
					.get(factor);
			if (weightMap == null || weightMap.size() == 0)
				continue;
			double totalWeight = 0.0;
			for (QualityModelElement childFactor : weightMap.keySet()) {
				double weight = weightMap.get(childFactor);
				totalWeight += weight;
				IESELogger.incPrefix();
				IESELogger.info(childFactor.getQualifiedName() + ":weight="
						+ weight);
				IESELogger.decPrefix();
			}
			IESELogger.incPrefix();
			IESELogger.info("totalWeight(" + factor.getQualifiedName() + "):"
					+ totalWeight);
			IESELogger.decPrefix();
		}
		IESELogger.decPrefix();
		IESELogger.info("#result entries:" + factorWeightMap.size());
	}

}
