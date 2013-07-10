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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.FindingCategory;
import org.conqat.engine.commons.findings.FindingGroup;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.engine.commons.node.IConQATNode;
import org.conqat.engine.commons.node.NodeConstants;
import org.conqat.engine.commons.node.NodeUtils;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.lib.commons.collections.Pair;

/**
 * {@ConQAT.Doc}
 * 
 * TODO (FD): There still is some kind of mismatch between tools/rules and
 * categories/groups.
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 0580B6DD5AC17BA0247E210FABECE99D
 */
@AConQATProcessor(description = "This processor collects all metrics and provides them"
		+ "as a MetricCollection")
public class MetricCollector extends ConQATProcessorBase {

	/** The scope of the evaluated system. */
	private IConQATNode scope;

	/** Maps from measure name to key measure is stored at (for plain measures). */
	private final HashMap<String, String> plainMeasures = new HashMap<String, String>();

	/**
	 * Maps from measure name to a pair &lt;tool-id, rule-id&gt; (for finding
	 * measures).
	 */
	private final HashMap<String, Pair<String, String>> findingMeasures = new HashMap<String, Pair<String, String>>();

	/** The result. */
	private final MetricCollection measureValues = new MetricCollection();

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, description = ConQATParamDoc.INPUT_DESC, minOccurrences = 1, maxOccurrences = 1)
	public void setScope(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) IConQATNode scope) {
		this.scope = scope;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "plain-measure", description = "Add plain measure stored at the root of the scope")
	public void addPlainMeasure(
			@AConQATAttribute(name = "name", description = "Measure name") String name,
			@AConQATAttribute(name = "key", description = "Key the measure is stored at") String key) {
		plainMeasures.put(name, key);
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "findings-measure", description = "Add findings measure")
	public void addFindingsMeasure(
			@AConQATAttribute(name = "name", description = "Measure name") String name,
			@AConQATAttribute(name = "tool", description = "The tool that generated the finding") String tool,
			@AConQATAttribute(name = "rule-identifier", description = "The identifier for the rules to include") String ruleIdentifier) {
		findingMeasures.put(name,
				new Pair<String, String>(tool, ruleIdentifier));
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "direct-measure", description = "Add direct numeric measure.")
	public void addDirectMeasure(
			@AConQATAttribute(name = "name", description = "Measure name") String name,
			@AConQATAttribute(name = "value", description = "Measure value") double value) {
		measureValues.put(name, value);
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "metric-collection", description = "Add other metric collection.")
	public void addMetricCollection(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) MetricCollection collection) {
		measureValues.putAll(collection);
	}

	/** {@inheritDoc} */
	@Override
	public MetricCollection process() {
		addPlainMeasures();
		addFindingMeasures();
		return measureValues;
	}

	/** Add plain measures. */
	private void addPlainMeasures() {
		for (String name : plainMeasures.keySet()) {
			String key = plainMeasures.get(name);
			measureValues.put(name, scope.getValue(key));
		}
	}

	/** Add finding measures. */
	private void addFindingMeasures() {
		for (String name : findingMeasures.keySet()) {
			Pair<String, String> specifier = findingMeasures.get(name);
			FindingCollection findings = obtainFindings(specifier.getFirst(),
					specifier.getSecond());
			measureValues.put(name, findings);
		}

	}

	/** Obtain findings for a specific tool and rule-id. */
	private FindingCollection obtainFindings(String tool, String ruleIdentifier) {
		FindingReport report = NodeUtils.getFindingReport(scope);

		FindingCollection result = new FindingCollection();

		for (FindingCategory category : report.getChildren()) {
			if (!category.getName().equals(tool)) {
				continue;
			}
			for (FindingGroup group : category.getChildren()) {
				if (ruleIdentifier.equals(group
						.getValue(NodeConstants.RULE_IDENTIFIER_KEY))) {
					List<Finding> findings = Arrays.asList(group.getChildren());
					result.addAll(findings);
				}
			}
		}

		return result;
	}

}
