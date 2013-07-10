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

package edu.tum.cs.conqat.quamoco.qiesl;

import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.JexlException;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.Script;
import org.apache.commons.jexl2.introspection.UberspectImpl;
import org.apache.commons.jexl2.parser.ASTAssignment;
import org.apache.commons.jexl2.parser.ASTIdentifier;
import org.apache.commons.jexl2.parser.ASTJexlScript;
import org.apache.commons.jexl2.parser.ASTMethodNode;
import org.apache.commons.jexl2.parser.ASTReference;
import org.apache.commons.jexl2.parser.ASTSizeFunction;
import org.apache.commons.jexl2.parser.Node;
import org.apache.commons.jexl2.parser.ParseException;
import org.apache.commons.jexl2.parser.Parser;
import org.apache.commons.jexl2.parser.TokenMgrError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.assertion.CCSMPre;
import org.conqat.lib.commons.io.NullOutputStream;
import org.conqat.lib.commons.logging.ILogger;
import org.conqat.lib.commons.logging.SimpleLogger;
import org.conqat.lib.commons.string.StringUtils;

import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.FindingCollection;
import edu.tum.cs.conqat.quamoco.IFileRangeResolver;
import edu.tum.cs.conqat.quamoco.IFunctionRangeResolver;
import edu.tum.cs.conqat.quamoco.NullFileRangeResolver;
import edu.tum.cs.conqat.quamoco.NullFunctionRangeResolver;

/**
 * Engine for interpreting QIESL expression.
 * 
 * It must be used in the following way: 1) initialize(String expression, Type
 * expectedReturnType) 2) parse() 3) Set all variables, that were returned by
 * parse() 4) evaluate()
 * 
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 89E50A2CB0F7657BC91D2AF857229FC4
 */
public class QIESLEngine {

	/** Literal for all impacts and refinements */
	public static final String ALL_IMPACTS_AND_REFINEMENTS_LITERAL = "allImpactsAndRefinements";
	/** The predefined result variable */
	private static final String RESULT_VARIABLE_NAME = "result";

	/** Delimiters for measure names in QIESL formulas. */
	public static final String MEASURE_NAME_DELIMITER = "%%";

	/** Pattern to substitute measure names */
	private static final Pattern PATTERN_FOR_MEASURE_NAMES = Pattern
			.compile("%%([^%]*)%%");

	/** Literal for veto QPoint value */
	private static final String VETO_LITERAL = "veto";
	/** Literal for unknown findings value */
	private static final String UNKNOWN_FINDINGS_LITERAL = "unknownFindings";
	/** Literal for unknown points */
	private static final String UNKNOWN_POINTS_LITERAL = "unknownPoints";
	/** Literal for unknown number */
	private static final String UNKNOWN_NUMBER_LITERAL = "unknownNumber";

	/** The JexlEngine */
	private final JexlEngine jexlEngine;
	/** The JexlContext */
	private JexlContext jexlContext;

	/** Constructs a new engine with the given resolvers. */
	public QIESLEngine(IFunctionRangeResolver functionRangeResolver,
			IFileRangeResolver fileRangeResolver,
			IFileRangeResolver classRangeResolver, ILogger logger) {
		jexlEngine = createJexlEngine(new QIESLFunctions(functionRangeResolver,
				fileRangeResolver, classRangeResolver, logger));
	}

	/**
	 * Constructs a new engine with Null resolvers, i.e. <b>file range resolving
	 * and function range resolving will not produce correct results</b>. The
	 * logger used is a NullLogger.
	 */
	public QIESLEngine() {
		jexlEngine = createJexlEngine(new QIESLFunctions(
				new NullFunctionRangeResolver(), new NullFileRangeResolver(), new NullFileRangeResolver(),
				new SimpleLogger(new NullOutputStream())));
	}

	/** Creates a new {@link JexlEngine} using the given function object */
	public static JexlEngine createJexlEngine(Object functions) {
		Log logger = LogFactory.getLog(QIESLEngine.class);
		Map<String, Object> allFunctions = new HashMap<String, Object>();
		allFunctions.put(null, functions);
		JexlEngine jexlEngine = new JexlEngine(new UberspectImpl(logger),
				new QIESLArithmetic(), allFunctions, logger);
		jexlEngine.setSilent(false);
		jexlEngine.setLenient(false);
		return jexlEngine;
	}

	/** Evaluate a QIESL specification. */
	public Object evaluate(String expression, IQIESLEvalVariables variables,
			Type expectedReturnType) throws QIESLException {

		Map<String, Object> optionalVariables = variables
				.getOptionalVariables();
		Map<String, Object> mandatoryVariables = variables
				.getMandatoryVariables();

		checkOverlappingVariables(optionalVariables.keySet(),
				mandatoryVariables.keySet());

		initialize();

		Map<String, Object> modelVariables = new HashMap<String, Object>();
		modelVariables.putAll(mandatoryVariables);
		modelVariables.putAll(optionalVariables);
		modelVariables.put(ALL_IMPACTS_AND_REFINEMENTS_LITERAL,
				variables.getAllImpactsAndRefinements());
		modelVariables.put(VETO_LITERAL, QPoints.VETO);
		modelVariables.put(UNKNOWN_FINDINGS_LITERAL,
				QIESLFunctions.UNKNOWN_FINDING_COLLECTION);
		modelVariables.put(UNKNOWN_POINTS_LITERAL, QPoints.valueOf(0, 1));
		modelVariables.put(UNKNOWN_NUMBER_LITERAL,
				QIESLFunctions.UNKNOWN_DOUBLE);

		Map<String, String> nameMapping = createNameMapping(modelVariables);
		String technicalExpression = toTechnicalExpression(expression,
				nameMapping);

		HashSet<String> usedTechnicalNames = validate(technicalExpression,
				nameMapping.keySet());

		checkMandatoryVariables(expression, mandatoryVariables, nameMapping,
				usedTechnicalNames);

		prepareVariables(usedTechnicalNames, nameMapping, modelVariables);
		Object result = evaluate(technicalExpression, expectedReturnType);

		return result;

	}

	/** Checks if the sets contain no overlapping variables */
	private void checkOverlappingVariables(Set<String> set1, Set<String> set2)
			throws QIESLException {
		Set<String> intersection = new HashSet<String>(set1);
		intersection.retainAll(set2);
		if (!intersection.isEmpty()) {
			throw new QIESLException("Overlapping variables: "
					+ StringUtils.concat(intersection, ", "));
		}
	}

	/**
	 * @param expression
	 * @param mandatoryVariables
	 * @param nameMapping
	 * @param usedTechnicalNames
	 * @throws QIESLException
	 */
	private void checkMandatoryVariables(String expression,
			Map<String, Object> mandatoryVariables,
			Map<String, String> nameMapping, HashSet<String> usedTechnicalNames)
			throws QIESLException {
		HashSet<String> unusedModelVariables = new HashSet<String>(
				mandatoryVariables.keySet());
		for (String technicalName : usedTechnicalNames) {
			unusedModelVariables.remove(nameMapping.get(technicalName));
		}

		if (!unusedModelVariables.isEmpty()
				&& !usedTechnicalNames
						.contains(ALL_IMPACTS_AND_REFINEMENTS_LITERAL)) {
			throw new QIESLException("Expression " + expression
					+ " does not use mandatory variables: "
					+ StringUtils.concat(unusedModelVariables, ", "));
		}
	}

	/** Checks if the variable is the predefined result variable */
	private boolean isResultVariable(String name) {
		return name.equals("result");
	}

	/** Prepares the variables */
	private void prepareVariables(Set<String> usedTechnicalNames,
			Map<String, String> nameMapping, Map<String, Object> modelVariables)
			throws QIESLException {

		for (String technicalName : usedTechnicalNames) {
			if (isResultVariable(technicalName)) {
				continue;
			}
			String modelName = nameMapping.get(technicalName);
			CCSMAssert
					.isNotNull(modelName,
							"Null-reference should be sorted out in toTechnicalExpression");

			Object value = modelVariables.get(modelName);

			CCSMAssert.isNotNull(value, "Value of model variable " + modelName
					+ " was null");

			if (value instanceof Exception) {
				throw new QIESLException(
						"not evaluated because sub-value is missing.");
			}

			jexlContext.set(technicalName, value);

		}
	}

	/** Validates the expression */
	private HashSet<String> validate(String expressionString,
			Set<String> knownTechnicalNames) throws QIESLException {
		Parser parser = new Parser(new StringReader(";"));
		ASTJexlScript result;
		try {
			result = parser.parse(new StringReader(expressionString), null);
		} catch (ParseException e) {
			throw new QIESLException("Syntax error: " + e.getMessage(), e);
		} catch (TokenMgrError e) {
			throw new QIESLException("Error: " + e.getMessage(), e);
		} catch (Error e) {
			throw new QIESLException("Error: " + e.getMessage(), e);
		}

		HashSet<String> identifiers = getReferencedVariables(result);
		HashSet<String> declaredVariables = getDeclaredVariables(result);
		identifiers.removeAll(declaredVariables);

		Set<String> unknownTechnicalNames = difference(identifiers,
				knownTechnicalNames);

		for (String unknownName : unknownTechnicalNames) {
			if (!isResultVariable(unknownName)) {
				throw new QIESLException("Unknown variable: " + unknownName);
			}
		}

		return identifiers;
	}

	// TODO (FD): Move to commons
	/** Computes the set difference */
	public static <T> Set<T> difference(Set<T> set1, Set<T> set2) {
		HashSet<T> result = new HashSet<T>(set1);
		result.removeAll(set2);
		return result;
	}

	/** Returns the referenced variables in the AST */
	private HashSet<String> getReferencedVariables(Node node)
			throws QIESLException {
		HashSet<String> identifiers = new HashSet<String>();
		if (node instanceof ASTSizeFunction) {
			throw new QIESLException(
					"Function size does not exist. Use extent instead");
		}
		if (node instanceof ASTIdentifier) {
			if (node.jjtGetParent() instanceof ASTMethodNode) {
				// TODO(FD): Validate functions
			} else if (!isLeftHandSideOfAssignment((ASTIdentifier) node)) {
				identifiers.add(((ASTIdentifier) node).image);
			}
		}
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			identifiers.addAll(getReferencedVariables(node.jjtGetChild(i)));
		}
		return identifiers;
	}

	/** Returns the declared variables in the AST */
	private HashSet<String> getDeclaredVariables(Node node)
			throws QIESLException {
		HashSet<String> identifiers = new HashSet<String>();
		if (node instanceof ASTIdentifier) {
			if (node.jjtGetParent() instanceof ASTMethodNode) {
				// ignore
			} else if (isLeftHandSideOfAssignment((ASTIdentifier) node)) {
				identifiers.add(((ASTIdentifier) node).image);
			}
		}
		for (int i = 0; i < node.jjtGetNumChildren(); i++) {
			identifiers.addAll(getDeclaredVariables(node.jjtGetChild(i)));
		}
		return identifiers;
	}

	/** Checks if the identifier is the left hand side of an assignment */
	private static boolean isLeftHandSideOfAssignment(ASTIdentifier identifier) {
		if (identifier.jjtGetParent() instanceof ASTReference) {
			ASTReference reference = (ASTReference) identifier.jjtGetParent();
			return (reference.jjtGetParent() instanceof ASTAssignment)
					&& ((ASTAssignment) reference.jjtGetParent())
							.jjtGetChild(0) == identifier.jjtGetParent();
		}
		return false;
	}

	/**
	 * @param variables
	 * @throws QIESLException
	 */
	private Map<String, String> createNameMapping(Map<String, Object> variables)
			throws QIESLException {
		HashMap<String, String> mapping = new HashMap<String, String>();
		for (String modelName : variables.keySet()) {
			String technicalName = toTechnicalName(modelName);
			if (mapping.containsKey(technicalName)) {
				throw new QIESLException("Model names " + modelName + " and "
						+ mapping.get(technicalName)
						+ " map to same to technial name: " + technicalName);
			}
			mapping.put(technicalName, modelName);
		}
		return mapping;

	}

	/**
	 * Initializes the QIESL-Engine.
	 */
	private void initialize() {
		this.jexlContext = new MapContext();
	}

	/**
	 * Converts expression with model names to expression with technical names.
	 * If a model name cannot be resolved a {@link QIESLException} is thrown.
	 */
	protected static String toTechnicalExpression(String expression,
			Map<String, String> nameMapping) throws QIESLException {
		Matcher matcher = PATTERN_FOR_MEASURE_NAMES.matcher(expression);
		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			String match = matcher.group(1);
			if (!nameMapping.containsValue(match)) {
				throw new QIESLException("Unknown variable '" + match
						+ "' in spec;" + " available names are '"
						+ nameMapping.values() + "'");
			}
			String replacement = toTechnicalName(match);
			matcher.appendReplacement(result,
					StringUtils.escapeRegexReplacementString(replacement));
		}
		matcher.appendTail(result);
		return result.toString();
	}

	/**
	 * Evaluates the given QUISL expression
	 * 
	 * @param type
	 */
	private Object evaluate(String qiesl, Type type) throws QIESLException {

		try {
			Script script = jexlEngine.createScript(qiesl);
			Object directResult = script.execute(jexlContext);
			Object result = jexlContext.get(RESULT_VARIABLE_NAME);

			// if result was not set, then use the value of the expression
			if (result == null) {
				result = directResult;
			}
			checkType(result, type);

			return result;
		} catch (JexlException ex) {
			if (ex.getCause() != null) {
				throw new QIESLException(ex.getCause().getMessage(),
						ex.getCause());
			}
			throw new QIESLException(ex.getMessage(), ex);
		}
	}

	/**
	 * Checks whether the type of o conforms to expectedReturnType
	 * 
	 * @param o
	 * @param expectedReturnType
	 */
	private void checkType(Object o, Type expectedReturnType)
			throws QIESLException {
		if (o == null) {
			throw QIESLException.NULL_RETURNED;
		}
		if (expectedReturnType == null) {
			return;
		}
		boolean ok = false;
		switch (expectedReturnType.getValue()) {
		case Type.NONE_VALUE:
			ok = false;
			break;
		case Type.FINDINGS_VALUE:
			ok = o instanceof FindingCollection;
			break;
		case Type.NUMBER_VALUE:
			ok = o instanceof Number;
			break;
		default:
			ok = false;
		}
		if (!ok) {
			throw new QIESLException("QIESL should return "
					+ expectedReturnType.getLiteral() + " but it returns "
					+ o.getClass().getName());
		}
	}

	/**
	 * Escapes a measure name, so that a valid java identifier results
	 */
	protected static String toTechnicalName(String name) {
		CCSMPre.isFalse(StringUtils.isEmpty(name), "Name cannot be empty");
		if (!Character.isJavaIdentifierStart(name.charAt(0))) {
			name = "_" + name;
		}
		return name.replaceAll("[^\\p{javaJavaIdentifierPart}]", "_");
	}

}
