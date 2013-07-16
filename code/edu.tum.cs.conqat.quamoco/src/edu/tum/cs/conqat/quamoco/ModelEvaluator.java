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

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.collections.UnmodifiableList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import utils.Logger;
import utils.QuamocoEvaluateException;
import utils.Types;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Effect;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.FindingMessage;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.FindingsUnionMeasureAggregation;
import de.quamoco.qm.Function;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Instrument;
import de.quamoco.qm.LinearDecreasingFunction;
import de.quamoco.qm.LinearIncreasingFunction;
import de.quamoco.qm.ManualEvaluation;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureEvaluation;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.MultiMeasureEvaluationResult;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.NumberMeanMeasureAggregation;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QIESLAggregation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.SingleMeasureEvaluationResult;
import de.quamoco.qm.TextAggregation;
import de.quamoco.qm.TextEvaluation;
import de.quamoco.qm.Type;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.conqat.quamoco.qiesl.IQIESLEvalVariables;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEvalVariables;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLException;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLFunctions;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * This is the changed version of the Quamoco-Model-Evaluator originally
 * designed by Floiran Deissenbeock und Klaus Lochmann. 
 * 
 * @author Jan-Peter Ostberg
 *
 */


public class ModelEvaluator extends QMProcessorBase {

	/** Function range resolver */
	public IFunctionRangeResolver functionRangeResolver;

	/** File range resolver */
	public IFileRangeResolver fileRangeResolver = new FileRangeResolver();

	/** Class range resolver */
	public IFileRangeResolver classRangeResolver = new FileRangeResolver();

	/** The metric values. */
	private MetricCollection metricValues;

	/** Functions used by {@link Evaluation}s and {@link MeasurementMethod}s. */
	private QIESLFunctions functions;

	/** The QIESL engine */
	private QIESLEngine qieslEngine;

	/** Result of the evaluation. */
	private QualityModelResult result;
	
	private String projectName = "No Name set";

	public boolean writeFindings = true;
	
	public void setProjectName (String name){
		this.projectName = name;
	}

	public QualityModel[] getQualityModelFromPath(HashSet<String> filenames){
		
		ResourceSet resourceSet = new ResourceSetImpl();

		for (String filename : filenames) {
			resourceSet.getResource(
					URI.createFileURI(new File(filename).getAbsolutePath()),
					true);
		}

		// TODO (FD): We had this here first: EcoreUtil.resolveAll(resourceSet);
		// However, this causes problems when run on a submodel like C#. If
		// everything was resolved,getFactors() for this model also returns
		// factors defined in an "upper" model.

		// Klaus: I think this is no longer relevant.

		EcoreUtil.resolveAll(resourceSet);

		List<QualityModel> qmList = new LinkedList<QualityModel>();

		for (Resource res : resourceSet.getResources()) {
			TreeIterator<EObject> it = res.getAllContents();
			while (it.hasNext()) {
				EObject eObject = it.next();
				if (eObject instanceof QualityModel) {
					qmList.add((QualityModel) eObject);
				}
			}
		}

		Logger.getLogger("Returning " + qmList + " quality models.",Types.MessageType.DEBUG);

		//TODO: Write new Util
		return CollectionUtils.toArray(qmList, QualityModel.class);

	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws ConQATException
	 */
	//@Override
	public QualityModel[] process() throws QuamocoEvaluateException { 

		createResultModelResource();

		functions = new QIESLFunctions(functionRangeResolver,
				fileRangeResolver, classRangeResolver, getLogger());

		qieslEngine = new QIESLEngine(functionRangeResolver, fileRangeResolver,
				classRangeResolver, getLogger());

		List<CharacterizingElement> elements = new ArrayList<CharacterizingElement>();
		for (QualityModel model : models) {
			elements.addAll(model.getMeasures());
			elements.addAll(model.getFactors());
		}

		for (CharacterizingElement element : elements) {
			process(element);
		}

		Logger.getLogger(
				"FunctionRangeResolver processed "
						+ functionRangeResolver.getNumLocationsWithoutRegion()
						+ " locations without region of totally "
						+ functionRangeResolver.getNumLocations()
						+ " locations.", Types.MessageType.INFO);

		return models;

	}

	/** Create the model that stores the result. */
	private void createResultModelResource() {
		Resource modelResource = models[0].eResource();
		URI resultURI = modelResource.getURI().trimSegments(1)
				.appendSegment("result_" + projectName + ".qmr");
		Resource resultResource = modelResource.getResourceSet()
				.createResource(resultURI);
		result = QmFactory.eINSTANCE.createQualityModelResult();
		resultResource.getContents().add(result);
		result.setDate(new Date());
		result.setSystem(projectName);
	}

	/**
	 * Evaluates both factors and measures of a quality model.
	 * 
	 * @throws ConQATException
	 */
	private void process(CharacterizingElement element) throws QuamocoEvaluateException { //TODO: Write Exception or use fitting existend exception

		if (element instanceof Measure) {
			determine((Measure) element);
		} else if (element instanceof Factor) {
			evaluate((Factor) element);
		} else {
			//TODO: Write new Util
			CCSMAssert.fail("Unknown element type: " + element.getClass());
		}
	}

	/** Evaluates a measure of the quality model. */
	private Object determine(Measure measure) {

		Object value = getValue(measure);
		if (value != null) {
			return value;
		}

		value = getUnknownValue(measure);

		MeasurementMethod method = QmModelUtils
				.getActualMeasurementMethodInDeepestModel(measure);
		if (method != null) {
			if (method instanceof QIESLAggregation) {
				value = determine((QIESLAggregation) method);
			} else if (method instanceof Instrument) {
				value = determine((Instrument) method);
			} else if (method instanceof TextAggregation) {
				value = determine((TextAggregation) method);
			} else if (method instanceof FindingsUnionMeasureAggregation) {
				value = determine((FindingsUnionMeasureAggregation) method);
			} else if (method instanceof NumberMeanMeasureAggregation) {
				value = determine((NumberMeanMeasureAggregation) method);
			}
		}
		return value;
	}

	/** Get the value of the determination of a measure from the result. */
	private Object getValue(Measure measure) {
		MeasurementResult measurementResult = QmModelUtils.getResult(result,
				measure);
		if (measurementResult == null) {
			return null;
		}
		return QuamocoUtils.getValue(measurementResult);
	}

	/** Determine the value of a {@link NumberMeanMeasureAggregation}. */
	private Object determine(NumberMeanMeasureAggregation method) {
		Measure measure = method.getDetermines();

		List<Number> numbers = new ArrayList<Number>();
		for (Measure subMeasure : measure.getRefinedByDirect()) {
			if (subMeasure.getType() == Type.NUMBER) {
				numbers.add((Number) determine(subMeasure));
			}
		}
		Number value = functions.mean(numbers.toArray(new Number[0]));
		storeMeasurementResult(method, QPoints.asQPoints(value), null);
		return value;
	}

	/** Determine the value of a {@link FindingsUnionMeasureAggregation}. */
	private Object determine(FindingsUnionMeasureAggregation method) {
		Measure measure = method.getDetermines();

		List<FindingCollection> collections = new ArrayList<FindingCollection>();
		for (Measure subMeasure : measure.getRefinedByDirect()) {
			if (subMeasure.getType() == Type.FINDINGS) {
				FindingCollection subMeasureResult = (FindingCollection) determine(subMeasure);
				collections.add(subMeasureResult);
			}
		}

		FindingCollection value = functions.union(collections
				.toArray(new FindingCollection[0]));
		storeMeasurementResult(method, value, null);
		return value;
	}

	/** Determine the value of a {@link TextAggregation}. */
	private Object determine(TextAggregation method) {
		Object value = getUnknownValue(method.getDetermines());
		storeMeasurementResult(method, value, null);
		return value;
	}

	/** Determine the value of an {@link Instrument}. */
	private Object determine(Instrument instrument) {
		Object value = metricValues.get(instrument.getQualifiedName());
		String message = null;
		if (value == null) {
			message = "No value for instrument "
					+ instrument.getQualifiedName();
			Logger.getLogger(message,Types.MessageType.WARN);
			Measure measure = instrument.getDetermines();
			value = getUnknownValue(measure);
		}
		if (instrument.getDetermines().getType() == Type.NUMBER) {
			value = QPoints.asQPoints(value);
		}
		storeMeasurementResult(instrument, value, message);
		return value;
	}

	/** Determine the value of a {@link QIESLAggregation}. */
	private Object determine(QIESLAggregation aggregation) {

		Measure measure = aggregation.getDetermines();
		Object value = null;
		String message = null;
		try {
			value = qieslEngine.evaluate(aggregation.getSpecification(),
					prepareVariables(aggregation), measure.getType());
			value = QPoints.asQPoints(value);
		} catch (QIESLException e) {
			// if the QIESL fails a warning is logged, and the result of the
			// measure is set to unknown
			Logger.getLogger(e.getMessage(), Types.MessageType.WARN);
			value = getUnknownValue(measure);
		}
		storeMeasurementResult(aggregation, value, message);
		return value;
	}

	/**
	 * Generates the data structure for all variables a measure may use in its
	 * evaluation.
	 */
	private IQIESLEvalVariables prepareVariables(QIESLAggregation aggregation) {

		Measure measure = aggregation.getDetermines();
		List<Measure> measures = new ArrayList<Measure>();
		measures.addAll(aggregation.getUsableMeasures());

		for (Measure subMeasure : measures) {
			determine(subMeasure);
		}

		final HashMap<String, Object> optionalVariables = new HashMap<String, Object>();
		final HashMap<String, Object> mandatoryVariables = new HashMap<String, Object>();

		for (Measure subMeasure : measures) {
			mandatoryVariables.put(subMeasure.getQualifiedName(),
					getValue(subMeasure));
		}

		for (Measure subMeasure : getAndEvaluateAllNormalizationMeasures(measure
				.getQualityModel())) {
			optionalVariables.put(subMeasure.getQualifiedName(),
					getValue(subMeasure));
		}
//TODO: Write new Util
		return new QIESLEvalVariables(null,
				CollectionUtils.asUnmodifiable(optionalVariables),
				CollectionUtils.asUnmodifiable(mandatoryVariables));
	}

	/**
	 * Returns all normalization measures of a quality model and evaluates them.
	 */
	private List<NormalizationMeasure> getAndEvaluateAllNormalizationMeasures(
			QualityModel qualityModel) {

		List<NormalizationMeasure> normalizationMeasures = QmModelUtils
				.getNormalizationMeasures(qualityModel);
		for (Measure subMeasure : normalizationMeasures) {
			determine(subMeasure);
		}

		return normalizationMeasures;
	}

	/** Get the unknown value for a measure. */
	private Object getUnknownValue(Measure measure) {
		Type type = measure.getType();
		switch (type) {
		case FINDINGS:
			return QIESLFunctions.UNKNOWN_FINDING_COLLECTION;
		case NUMBER:
			return QPoints.UNKNOWN;
		}
		//TODO: Write new Util
		CCSMAssert.fail("Unknown type: " + type);
		return null;
	}

	/** Evaluates the factor. */
	private QPoints evaluate(Factor factor) throws QuamocoEvaluateException { 

		QPoints value = getValue(factor);
		if (value != null) {
			return value;
		}

		value = QPoints.UNKNOWN;
		Evaluation evaluation = QmModelUtils
				.getActualEvaluationInDeepestModel(factor);
		if (evaluation != null) {
			if (evaluation instanceof QIESLEvaluation) {
				value = evaluate((QIESLEvaluation) evaluation);
			} else if (evaluation instanceof SingleMeasureEvaluation) {
				value = evaluate((SingleMeasureEvaluation) evaluation);
			} else if (evaluation instanceof WeightedSumFactorAggregation) {
				value = evaluate((WeightedSumFactorAggregation) evaluation);
			} else if (evaluation instanceof TextEvaluation) {
				value = evaluate((TextEvaluation) evaluation);
			} else if (evaluation instanceof ManualEvaluation) {
				value = evaluate((ManualEvaluation) evaluation);
			} else if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
				value = evaluate((WeightedSumMultiMeasureEvaluation) evaluation);
			} else {
				Logger.getLogger("Unknown evaluation type: " + evaluation.getClass(),Types.MessageType.ERROR);
			}
		}
		return value;
	}

	/** Get the value of the evaluation of a factor from the result. */
	private QPoints getValue(Factor factor) {
		EvaluationResult evaluationResult = QmModelUtils.getResult(result,
				factor);
		if (evaluationResult == null) {
			return null;
		}
		return QPoints.valueOf(evaluationResult.getValue());
	}

	/** Evaluates a {@link WeightedSumMultiMeasureEvaluation}. */
	private QPoints evaluate(WeightedSumMultiMeasureEvaluation evaluation)
			throws QuamocoEvaluateException {
		QPoints value = QPoints.valueOf(0);
		EList<MeasureRanking> rankings = evaluation.getRankings();
		MultiMeasureEvaluationResult evaluationResult = QmFactory.eINSTANCE
				.createMultiMeasureEvaluationResult();

		// empty rankings return unknown
		if (evaluation.getRankings().isEmpty()) {
			storeEvaluationResult(evaluation, evaluationResult,
					QPoints.UNKNOWN, null);
			return QPoints.UNKNOWN;
		}

		for (MeasureRanking ranking : rankings) {
			MeasureRankingEvaluationResult measureEvaluationResult = QmFactory.eINSTANCE
					.createMeasureRankingEvaluationResult();

			if (ranking.getMeasure() == null) {
				throw new QuamocoEvaluateException(
						"WeightedSumMultiMeasureEvaluation + '"
								+ evaluation.getQualifiedName()
								+ "' of factor '"
								+ evaluation.getEvaluates().getQualifiedName()
								+ " has measure==null");
			}

			QPoints measureEvaluation = evaluate(ranking,
					measureEvaluationResult);

			measureEvaluationResult.setResultsFrom(ranking);
			measureEvaluationResult.setValue(measureEvaluation
					.doubleIntervalValue());
			evaluationResult.getEvaluationResults()
					.add(measureEvaluationResult);

			QPoints weight = QPoints.valueOf(ranking.getWeight());
			QPoints rankingResult = QPoints.multiply(weight, measureEvaluation);
			value = QPoints.add(value, rankingResult);
		}
		storeEvaluationResult(evaluation, evaluationResult, value, null);
		return value;
	}

	/** Evaluate a {@link ManualEvaluation}. */
	private QPoints evaluate(ManualEvaluation evaluation) {
		QPoints value = QPoints.asQPoints(metricValues.get(evaluation
				.getQualifiedName()));
		String message = null;
		if (value == null) {
			message = "No value for manual evaluation "
					+ evaluation.getQualifiedName();
			Logger.getLogger(message,Types.MessageType.WARN);
			value = QPoints.UNKNOWN;
		}
		storeEvaluationResult(evaluation, value, message);

		return value;
	}

	/** Evaluate a {@link TextEvaluation}. */
	private QPoints evaluate(TextEvaluation evaluation) {
		QPoints value = QPoints.UNKNOWN;
		storeEvaluationResult(evaluation, value, null);
		return value;
	}

	/**
	 * Evaluate a {@link WeightedSumFactorAggregation}.
	 * 
	 * @throws ConQATException
	 */
	private QPoints evaluate(WeightedSumFactorAggregation evaluation)
			throws QuamocoEvaluateException {

		// empty rankings return unknown
		if (evaluation.getRankings().isEmpty()) {
			storeEvaluationResult(evaluation, QPoints.UNKNOWN, null);
			return QPoints.UNKNOWN;
		}

		QPoints value = QPoints.valueOf(0);

		for (FactorRanking ranking : evaluation.getRankings()) {
			QPoints weight = QPoints.valueOf(ranking.getWeight());
			if (ranking.getFactor() == null) {
				throw new QuamocoEvaluateException("The WeightedSumFactorAggregation '"
						+ evaluation.getQualifiedName() + "' of the factor '"
						+ evaluation.getEvaluates().getQualifiedName()
						+ "' has a factor==null.");
			}
			QPoints factorResult = evaluate(ranking.getFactor());

			// see if we have to negate due to a negative impact
			EList<Impact> impacts = ranking.getFactor().getInfluences();
			for (Impact impact : impacts) {
				if (impact.getTarget().equals(evaluation.getEvaluates())) {
					if (impact.getEffect() == Effect.NEGATIVE) {
						factorResult = QPoints.subtract(QPoints.valueOf(1),
								factorResult);
					}
				}
			}

			QPoints rankingResult = QPoints.multiply(weight, factorResult);
			value = QPoints.add(value, rankingResult);
		}
		storeEvaluationResult(evaluation, value, null);
		return value;
	}

	/**
	 * Evaluates a {@link SingleMeasureEvaluation}.
	 * 
	 * @throws ConQATException
	 */
	private QPoints evaluate(SingleMeasureEvaluation evaluation)
			throws QuamocoEvaluateException {
		SingleMeasureEvaluationResult evaluationResult = QmFactory.eINSTANCE
				.createSingleMeasureEvaluationResult();
		QPoints value = evaluate(evaluation, evaluationResult);
		storeEvaluationResult(evaluation, evaluationResult, value, null);
		return value;
	}

	/**
	 * Evaluate a {@link MeasureEvaluation}.
	 * 
	 * @throws ConQATException
	 */
	private QPoints evaluate(MeasureEvaluation evaluation,
			EObject evaluationResult) throws QuamocoEvaluateException {
		Function function = evaluation.getFunction();

		Measure measure = evaluation.getMeasure();
		double ratioAffected = QIESLFunctions.UNKNOWN_DOUBLE;

		Logger.getLogger("Range=" + evaluation.getRange(),Types.MessageType.WARN);

		if (measure.getType() == Type.NUMBER) {
			final QPoints measureValue = (QPoints) determine(measure);
			if (measureValue.isSingleValue()) {
				ratioAffected = measureValue.doubleValue();
			} else {
				ratioAffected = QIESLFunctions.UNKNOWN_DOUBLE;
			}

			double normalization = 1.0;
			if (evaluation.getNormlizationMeasure() != null) {
				QPoints value = (QPoints) determine(evaluation
						.getNormlizationMeasure());
				if (!value.isSingleValue()) {
					throw new QuamocoEvaluateException(
							"Value of normalization measure is an interval '"
									+ value.toString()
									+ "'. That is not allwoed.");
				}
				normalization = value.doubleValue();

			}

			ratioAffected = ratioAffected / normalization;

		} else if (evaluation.getRange() == null
				|| evaluation.getRange().equalsIgnoreCase("na")) {

			double normalization = 1.0;
			if (evaluation.getNormlizationMeasure() != null) {
				QPoints value = (QPoints) determine(evaluation
						.getNormlizationMeasure());
				if (!value.isSingleValue()) {
					throw new QuamocoEvaluateException(
							"Value of normalization measure is an interval '"
									+ value.toString()
									+ "'. That is not allwoed.");
				}
				normalization = value.doubleValue();

			}
			FindingCollection findings = (FindingCollection) determine(measure);
			ratioAffected = findings.size() / normalization;
		} else {
			FindingCollection findings = (FindingCollection) determine(measure);
			String range = evaluation.getRange().toLowerCase();
			try {
				RegionSetDictionary dictionary;

				if (range.equals("class")) {
					dictionary = functions.classRange(findings);
				} else if (range.equals("method")) {
					dictionary = functions.methodRange(findings);
				} else if (range.equals("file")) {
					dictionary = functions.fileRange(findings);
				} else {
					throw new IllegalStateException("unkown range: " + range);
				}

				double extent = functions.extent(dictionary);

				double locs = ((QPoints) getValue(evaluation
						.getNormlizationMeasure())).doubleValue();
				ratioAffected = extent / locs;

			} catch (QuamocoEvaluateException e) {
				getLogger()
						.error("Error when determining the extent: '"
								+ e.getMessage()
								+ "' Stack-Trace: "
								+ org.conqat.lib.commons.string.StringUtils
										.obtainStackTrace(e),
								e);
				return QPoints.UNKNOWN;
			}
		}

		if (evaluationResult instanceof SingleMeasureEvaluationResult) {
			((SingleMeasureEvaluationResult) evaluationResult)
					.setRatioAffected(ratioAffected);
		} else if (evaluationResult instanceof MeasureRankingEvaluationResult) {
			((MeasureRankingEvaluationResult) evaluationResult)
					.setRatioAffected(ratioAffected);
		}

		QPoints value = QPoints.UNKNOWN;
		if (function instanceof LinearIncreasingFunction) {
			LinearIncreasingFunction incFunc = (LinearIncreasingFunction) function;
			value = functions.linearDistribution(ratioAffected,
					incFunc.getLowerBound(), 0, incFunc.getUpperBound(), 1);
		} else if (function instanceof LinearDecreasingFunction) {
			LinearDecreasingFunction incFunc = (LinearDecreasingFunction) function;
			value = functions.linearDistribution(ratioAffected,
					incFunc.getLowerBound(), 1, incFunc.getUpperBound(), 0);
		}
		return value;
	}

	/** Evaluate a {@link QIESLEvaluation}. */
	private QPoints evaluate(QIESLEvaluation evaluation) throws QuamocoEvaluateException {
		Factor factor = evaluation.getEvaluates();

		QPoints value = null;
		String message = null;
		try {
			IQIESLEvalVariables variables = prepareVariables(evaluation);
			Object qieslValue = qieslEngine.evaluate(
					evaluation.getSpecification(), variables, Type.NUMBER);
			value = QPoints.asQPoints(qieslValue);

			extensionPointForCalibration(factor, evaluation, variables);
		} catch (QIESLException e) {
			Logger.getLogger(
					"QIESL-Exception: " + QuamocoUtils.stackTraceToString(e),Types.MessageType.WARN);
			message = "QIESL-Exception: " + e.getMessage();

			value = QPoints.UNKNOWN;
		}
		storeEvaluationResult(evaluation, value, message);
		return value;
	}

	/**
	 * Create and store an {@link EvaluationResult} of a certain
	 * {@link Evaluation} with a certain value.
	 */
	private EvaluationResult storeEvaluationResult(Evaluation evaluation,
			QPoints value, String message) {
		EvaluationResult evaluationResult = QmFactory.eINSTANCE
				.createEvaluationResult();
		return storeEvaluationResult(evaluation, evaluationResult, value,
				message);
	}

	/**
	 * Create and store a {@link MeasurementResult} of a certain
	 * {@link MeasurementMethod} with a certain value.
	 */
	private MeasurementResult storeMeasurementResult(MeasurementMethod method,
			Object value, String message) {
		MeasurementResult measurementResult = null;
		switch (method.getDetermines().getType()) {
		case FINDINGS:
			FindingCollection findings = (FindingCollection) value;
			FindingsMeasurementResult findingsMeasurementResult = QmFactory.eINSTANCE
					.createFindingsMeasurementResult();
			findingsMeasurementResult.setCount(findings.size());
			findingsMeasurementResult.setFindings(findings);
			measurementResult = findingsMeasurementResult;
			if (writeFindings) {
				for (Finding finding : findings) {
					ElementLocation location = finding.getLocation();

					FindingMessage findingMessage = QmFactory.eINSTANCE
							.createFindingMessage();
					findingMessage.setMessage(finding.getMessage());
					findingMessage.setLocation(location.toLocationString());
					findingsMeasurementResult.getFindingMessages().add(
							findingMessage);

				}
			}
			break;
		case NUMBER:
			QPoints points = QPoints.asQPoints(value);
			NumberMeasurementResult numberMeasurementResult = QmFactory.eINSTANCE
					.createNumberMeasurementResult();
			numberMeasurementResult.setValue(points.doubleIntervalValue());
			measurementResult = numberMeasurementResult;
			break;
		case NONE:
			break;
		default:
			break;
		}
		if (measurementResult != null) {
			measurementResult.setResultsFrom(method);
			result.getMeasurementResults().add(measurementResult);
			measurementResult.setMessage(message);
		}
		return measurementResult;
	}

	/**
	 * Store the {@link EvaluationResult} of a certain {@link Evaluation} with a
	 * certain value.
	 */
	private EvaluationResult storeEvaluationResult(Evaluation evaluation,
			EvaluationResult evaluationResult, QPoints value, String message) {
		evaluationResult.setResultsFrom(evaluation);
		result.getEvaluationResults().add(evaluationResult);
		evaluationResult.setValue(value.doubleIntervalValue());
		evaluationResult.setMessage(message);
		return evaluationResult;
	}

	/**
	 * Extension point for calibration. Will be overwritten to extend the model
	 * evaluator to generate the information necessary for calibration.
	 */
	@SuppressWarnings("unused")
	protected void extensionPointForCalibration(Factor factor,
			QIESLEvaluation evaluation, IQIESLEvalVariables variables) {
		// does nothing here.
	}

	/**
	 * Prepares all variables a factor may use in its evaluation.
	 * 
	 * @throws ConQATException
	 */
	private IQIESLEvalVariables prepareVariables(QIESLEvaluation evaluation)
			throws QuamocoEvaluateException {

		Factor factor = evaluation.getEvaluates();

		List<Impact> impactedBy = new ArrayList<Impact>();
		for (Impact influencedBy : factor.getInfluencedBy()) {
			if (QmModelUtils.getActualEvaluationInDeepestModel(influencedBy
					.getSource()) != null) {
				impactedBy.add(influencedBy);
			}
		}

		List<FactorRefinement> refinements = new ArrayList<FactorRefinement>();
		for (FactorRefinement refinedBy : factor.getRefinedBy()) {
			if (QmModelUtils.getActualEvaluationInDeepestModel(refinedBy
					.getChild()) != null) {
				refinements.add(refinedBy);
			}
		}

		for (FactorRefinement factorRefinement : refinements) {
			evaluate(factorRefinement.getChild());
		}

		for (Impact impact : impactedBy) {
			evaluate(impact.getSource());
		}
		for (Measure subMeasure : evaluation.getUsableMeasures()) {
			determine(subMeasure);
		}

		Map<String, Object> optionalVariables = new HashMap<String, Object>();
		Map<String, Object> mandatoryVariables = new HashMap<String, Object>();
		List<QPoints> allImpactsAndRefinements = new LinkedList<QPoints>();

		for (FactorRefinement refinement : refinements) {

			Factor subFactor = refinement.getChild();
			mandatoryVariables.put(subFactor.getQualifiedName(),
					getValue(subFactor));
			allImpactsAndRefinements.add(getValue(subFactor));
		}

		for (Impact impact : impactedBy) {
			Factor source = impact.getSource();
			QPoints value = getValue(source);

			if (impact.getEffect() == Effect.NEGATIVE) {
				value = QPoints.subtract(QPoints.valueOf(1), value);
			}
			mandatoryVariables.put(source.getQualifiedName(), value);
			allImpactsAndRefinements.add(value);
		}

		for (Measure subMeasure : evaluation.getUsableMeasures()) {
			mandatoryVariables.put(subMeasure.getQualifiedName(),
					getValue(subMeasure));
		}
		for (Measure subMeasure : getAndEvaluateAllNormalizationMeasures(evaluation
				.getQualityModel())) {
			optionalVariables.put(subMeasure.getQualifiedName(),
					getValue(subMeasure));
		}

		return new QIESLEvalVariables(
				allImpactsAndRefinements.toArray(new QPoints[0]),
				CollectionUtils.asUnmodifiable(optionalVariables),
				CollectionUtils.asUnmodifiable(mandatoryVariables));

	}
}
