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

package de.quamoco.qm.util.qiesl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.jexl2.JexlException;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureAggregation;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QIESLAggregation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.FindingCollection;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEvalVariables;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLException;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * Provides static methods to verify QIESL specifications.
 */
public class QIESLVerificator {

	/**
	 * {@link Evaluation}-specific parts of QIESL validation.
	 * 
	 * @param evaluation
	 *            The evaluation to be verified
	 * @param newest
	 *            A new specification string (or null)
	 * @throws QIESLException
	 */
	private static void verifyQIESL(QIESLEvaluation evaluation, String newest)
			throws QIESLException {

		verifyQIESL(evaluation.getSpecification(), newest, evaluation
				.getUsableMeasures(), evaluation.getUsableFactors(), evaluation
				.getNormalizationMeasures(), Type.NUMBER);
	}

	/**
	 * {@link MeasureAggregation}-specific parts of QIESL validation.
	 * 
	 * @param aggregation
	 *            The aggregation to be verified
	 * @param newest
	 *            A new specification string (or null)
	 * @throws QIESLException
	 */
	private static void verifyQIESL(QIESLAggregation aggregation, String newest)
			throws QIESLException {

		if (aggregation.getDetermines() != null) {
			verifyQIESL(aggregation.getSpecification(), newest, aggregation
					.getUsableMeasures(), Collections.<Factor> emptyList(),
					Collections.<NormalizationMeasure> emptyList(), aggregation
							.getDetermines().getType());
		}
	}

	/** Verifies the qiesl expression */
	private static void verifyQIESL(String spec, String newSpec,
			List<Measure> measures, List<Factor> factors,
			List<NormalizationMeasure> normalizationMeasures,
			Type expectedReturnType) throws QIESLException {
		String expr = newSpec != null ? newSpec : spec;
		if (StringUtils.isEmpty(expr)) {
			throw QIESLException.EMPTY_EXPRESSION;
		}

		Map<String, Type> optionalMeasures = new HashMap<String, Type>();
		Map<String, Type> mandatoryMeasures = new HashMap<String, Type>();

		// Add measures
		for (Measure m : measures) {
			mandatoryMeasures.put(m.getQualifiedName(), m.getType());
		}

		// Add factors
		for (Factor factor : factors) {
			// Due to overwriting it is no longer required, that all factors
			// have
			// evaluations.
			// checkHasEvaluation(factor);
			mandatoryMeasures.put(factor.getQualifiedName(), Type.NUMBER);
		}

		Set<String> namesOfNormalizations = new HashSet<String>();
		// Add normalization measures
		for (Measure measure : normalizationMeasures) {
			if (StringUtils.isEmpty(measure.getQualifiedName())) {
				throw new QIESLException("Qualified name of measure is empty: "
						+ measure);
			}
			namesOfNormalizations.add(measure.getQualifiedName());
			optionalMeasures.put(measure.getQualifiedName(), measure.getType());
		}

		verify(expr, optionalMeasures, mandatoryMeasures, expectedReturnType);
	}

	/** Verifies the qiesl expression */
	private static void verify(String expr, Map<String, Type> optionalMeasures,
			Map<String, Type> mandatoryMeasures, Type expectedReturnType)
			throws QIESLException {

		QIESLEngine engine = new QIESLEngine();

		engine.evaluate(expr, new QIESLEvalVariables(new QPoints[0],
				obtainVariables(optionalMeasures),
				obtainVariables(mandatoryMeasures)), expectedReturnType);
	}

	/** Produces a variable map with dummy values */
	private static Map<String, Object> obtainVariables(
			Map<String, Type> measures) throws QIESLException {
		Map<String, Object> result = new HashMap<String, Object>();

		for (String name : measures.keySet()) {
			if (measures.get(name) == Type.NUMBER) {
				result.put(name, QPoints.valueOf(1));
			} else if (measures.get(name) == Type.FINDINGS) {
				FindingCollection dummyFindingCollection = new FindingCollection();
				dummyFindingCollection.add(new FindingReport()
						.getOrCreateCategory("").createFindingGroup("")
						.createFinding(""));
				result.put(name, dummyFindingCollection);

			} else {
				throw new QIESLException("unknown measure type: "
						+ measures.get(name));
			}

		}
		return result;
	}

	/**
	 * Verify the QIESL specification of a given {@link QualityModelElement}.
	 * 
	 * @param obj
	 *            the element whose specification is to verify
	 * @return error message or null on success
	 */
	public static String verifyQIESL(EObject obj) {
		return verifyQIESL(obj, null);
	}

	/**
	 * Verify the QIESL specification of a given {@link QualityModelElement}.
	 * 
	 * @param obj
	 *            the element to operate on
	 * @param s
	 *            the (new) specification to be verified
	 * @return error message or null on success
	 */
	public static String verifyQIESL(EObject obj, String s) {
		if (obj == null) {
			return "No expression given.";
		}

		try {
			if (obj instanceof QIESLEvaluation) {
				verifyQIESL((QIESLEvaluation) obj, s);
				return null;
			}
			if (obj instanceof QIESLAggregation) {
				verifyQIESL((QIESLAggregation) obj, s);
				return null;
			}
			return "Unsupported element type";
		} catch (QIESLException e) {
			if (e.getCause() != null && (e.getCause() instanceof JexlException)) {
				String msg = ((JexlException) e.getCause()).getMessage();
				int index = msg.indexOf(":");
				if (index != -1) {
					msg = msg.substring(index + 1).trim();
				}
				return msg;
			}
			return e.getMessage();
		}
	}
}
