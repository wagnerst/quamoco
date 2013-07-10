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

package edu.tum.cs.conqat.quamoco;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.node.ListNode;
import org.conqat.engine.commons.node.NodeConstants;
import org.conqat.engine.commons.node.NodeUtils;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.lib.commons.collections.Pair;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.Function;
import de.quamoco.qm.LinearFunction;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/** {@ConQAT.Doc} **/
@AConQATProcessor(description = "This processor displays the result of an evaluation as a tree.")
public class EvaluationResultFormatter extends ConQATProcessorBase {

	/** Key for the ConQAT Node there the result (plain) is stored */
	public static final String EVAL_RESULT_VALUE_KEY = "Evaluation Result Value";

	/** Key for the ConQAT Node there the result in text form is stored */
	public static final String EVAL_RESULT_KEY = "Evaluation Result";

	/** Key for the ConQAT Node there the QIESL message is stored */
	public static final String EVAL_MSG_KEY = "QIESL Message";

	/** Output maximum points */
	public static final String EVAL_MAXPOINTS = "Max Points";

	/** Key to store completeness */
	public static final String COMPLETENESS_KEY = "Completeness";

	/** The quality model that is evaluated. */
	protected QualityModel[] qualityModels;

	/** Result that is returned */
	protected ListNode result;

	/** Result of the evaluation. */
	private QualityModelResult qualityModelResult;

	/** Set the quality model. */
	@AConQATParameter(name = "quality-models", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The quality model.")
	public void setQualityModel(
			@AConQATAttribute(name = "models", description = "quality models") QualityModel[] qm) {
		this.qualityModels = qm;

		for (Resource resource : qualityModels[0].eResource().getResourceSet()
				.getResources()) {
			EObject root = resource.getContents().get(0);
			if (root instanceof QualityModelResult) {
				qualityModelResult = (QualityModelResult) root;
			}
		}
	}

	/** Convert the QualityModel to a ListNode-Tree */
	@Override
	public ListNode process() {
		this.result = new ListNode();
		result.setValue(NodeConstants.HIDE_ROOT, true);
		NodeUtils.addToDisplayList(result, EVAL_RESULT_KEY);
		NodeUtils.addToDisplayList(result, EVAL_MAXPOINTS);
		NodeUtils.addToDisplayList(result, EVAL_MSG_KEY);

		for (QualityModel qualityModel : qualityModels) {

			TreeIterator<EObject> i = qualityModel.eAllContents();
			while (i.hasNext()) {
				EObject eObject = i.next();
				if (eObject instanceof Factor) {
					Factor factor = (Factor) eObject;
					if (isRoot(factor)) {
						export(factor, result, "");
					}
				}
			}
		}

		return this.result;
	}

	/** Exports a Factor */
	protected void export(Factor factor, ListNode parent, String marker) {
		String name = factor.getQualifiedName() + " [" + marker + "]";
		ListNode nodeOfProperty = new ListNode(name, name);
		parent.addChild(nodeOfProperty);

		EvaluationResult evaluationResult = QmModelUtils.getResult(
				qualityModelResult, factor);
		if (evaluationResult != null) {

			QPoints value = QPoints.valueOf(evaluationResult.getValue());

			// text value for the user
			{
				if (factor.isQualityAspect()) {
					nodeOfProperty.setValue(EVAL_RESULT_KEY, "school degree: "
							+ format(evaluationResult.getSchoolGrade())
							+ " value=" + format(value));
				} else {
					nodeOfProperty.setValue(EVAL_RESULT_KEY, format(value));
				}
			}

			// plain value, used by the radar plot
			{
				nodeOfProperty.setValue(EVAL_RESULT_VALUE_KEY, format(value));
			}

			if (evaluationResult.getMessage() != null) {
				nodeOfProperty.setValue(EVAL_MSG_KEY,
						evaluationResult.getMessage());
			}

			Evaluation evaluation = evaluationResult.getResultsFrom();
			if (evaluation != null) {
				nodeOfProperty.setValue(EVAL_MAXPOINTS,
						evaluation.getMaximumPoints());
				nodeOfProperty.setValue(COMPLETENESS_KEY, evaluation
						.getAccumulatedCompleteness(evaluation
								.getQualityModel()));
			}
		} else {
			nodeOfProperty.setValue(EVAL_MSG_KEY,
					"This factor has not been evaluated.");
		}

		for (Factor subFactor : factor.getRefinedByDirect()) {
			export(subFactor, nodeOfProperty, "refined");
		}
		for (Factor subFactor : factor.getInfluencedByDirect()) {
			export(subFactor, nodeOfProperty, "impacted");
		}

		if (evaluationResult != null) {
			Evaluation evaluation = evaluationResult.getResultsFrom();
			if (!factor.getMeasuredByDirect().isEmpty()) {
				// the factor has measures

				if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
					export(factor,
							(WeightedSumMultiMeasureEvaluation) evaluation,
							nodeOfProperty);
				}
			}
		}
	}

	/**
	 * Exports the information how the factor was evaluated by the given
	 * evaluation
	 * 
	 * @param factor
	 * @param evaluation
	 */
	private void export(Factor factor,
			WeightedSumMultiMeasureEvaluation evaluation, ListNode parent) {

		List<MeasureRanking> rankings = new ArrayList<MeasureRanking>();
		rankings.addAll(evaluation.getRankings());
		Collections.sort(rankings, new Comparator<MeasureRanking>() {

			@Override
			public int compare(MeasureRanking o1, MeasureRanking o2) {
				return Integer.valueOf(o2.getRank()).compareTo(o1.getRank());
			}

		});

		for (MeasureRanking ranking : rankings) {
			String name = ranking.getMeasure().getQualifiedName();

			ListNode nodeOfRanking = new ListNode(name, name);
			parent.addChild(nodeOfRanking);

			String value = "rank=" + ranking.getRank();
			Function f = ranking.getFunction();
			if (f instanceof LinearFunction) {
				LinearFunction lf = (LinearFunction) f;
				value += " threshold=[" + format(lf.getLowerBound()) + " ; "
						+ format(lf.getUpperBound()) + "]";
			}

			List<MeasureRankingEvaluationResult> result = QmModelUtils
					.getInverse(QmPackage.eINSTANCE
							.getMeasureRankingEvaluationResult_ResultsFrom(),
							ranking, MeasureRankingEvaluationResult.class);

			if (!result.isEmpty()) {
				value += " ratio=" + format(result.get(0).getRatioAffected());
				value += " value=" + format(result.get(0).getValue());
			}

			nodeOfRanking.setValue(EVAL_RESULT_KEY, value);

			CharacterizingElement subelement = ranking.getRankedElement();
			if (subelement instanceof Measure) {
				export((Measure) subelement, nodeOfRanking, "measures");
			}
		}
	}

	/** Formats an evaluation result. */
	public static String format(Object evaluationResult) {
		
		if(QIESLFunctions.isUnknown(evaluationResult)) {
			return "NaN";
		}

		if (evaluationResult == QIESLFunctions.UNKNOWN_DOUBLE) {
			return "unknown Double";
		}

		if (evaluationResult == QIESLFunctions.UNKNOWN_FINDING_COLLECTION) {
			return "unknown Findings";
		}

		if (evaluationResult == QIESLFunctions.UNKNOWN_REGION_SET_DICT) {
			return "unknown region set dict";
		}

		if (evaluationResult instanceof QPoints) {
			return ((QPoints) evaluationResult).toShortString();
		}

		if (evaluationResult instanceof DoubleInterval) {
			DoubleInterval di = (DoubleInterval) evaluationResult;
			String sLower = format.format(di.getLower());
			String sUpper = format.format(di.getUpper());

			if (sLower.equals(sUpper)) {
				return sLower;
			}

			return "[" + sLower + "; " + sUpper + "]";
		}
		if (evaluationResult instanceof Pair<?, ?>) {
			Pair<Double, Double> di = (Pair<Double, Double>) evaluationResult;
			String sLower = format.format(di.getFirst());
			String sUpper = format.format(di.getSecond());

			if (sLower.equals(sUpper)) {
				return sLower;
			}

			return "[" + sLower + "; " + sUpper + "]";
		}

		if (evaluationResult instanceof FindingsMeasurementResult) {
			FindingsMeasurementResult fm = (FindingsMeasurementResult) evaluationResult;
			return "#findings=" + fm.getCount();
		}

		if (evaluationResult instanceof FindingCollection) {
			FindingCollection fc = (FindingCollection) evaluationResult;
			return "#findings=" + fc.size();
		}

		if (evaluationResult instanceof Number) {
			return format2.format(((Number) evaluationResult).doubleValue());
		}

		return String.valueOf(evaluationResult);
	}

	private static final DecimalFormat format = new DecimalFormat("0.000");
	private static final DecimalFormat format2 = new DecimalFormat(
			"0.000000000000");

	/** Exports a measure. */
	protected void export(Measure measure, ListNode parent, String marker) {

		String qn = measure.getQualifiedName();

		MeasurementResult measurementResult = QmModelUtils.getResult(
				qualityModelResult, measure);
		if (measurementResult != null) {
			MeasurementMethod mm = measurementResult.getResultsFrom();
			if (mm instanceof ToolBasedInstrument) {
				ToolBasedInstrument tb = (ToolBasedInstrument) mm;
				qn = tb.getQualifiedName();
			}

			String name = qn + " [" + marker + "]";
			ListNode nodeOfProperty = new ListNode(name, name);
			parent.addChild(nodeOfProperty);

			nodeOfProperty.setValue(EVAL_RESULT_KEY,
					format(QuamocoUtils.getValue(measurementResult)));

			String msg = measurementResult.getMessage();
			if (msg != null) {
				nodeOfProperty.setValue(EVAL_MSG_KEY, msg);
			}

			for (Measure subMeasure : measure.getRefinedByDirect()) {
				export(subMeasure, nodeOfProperty, "refined");
			}
		}
	}

	/** Checks whether a factor is a root element of the result tree. */
	protected boolean isRoot(Factor factor) {
		return factor.getRefines().isEmpty();
	}

}
