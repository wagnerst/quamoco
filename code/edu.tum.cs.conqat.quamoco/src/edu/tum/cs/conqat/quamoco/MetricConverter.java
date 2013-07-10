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

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.engine.commons.findings.FindingsList;
import org.conqat.engine.commons.findings.util.FindingUtils;
import org.conqat.engine.commons.node.ListNode;
import org.conqat.engine.commons.node.NodeUtils;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATKey;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 4E38D0905CA74B9452DE21CCA714CB08
 */
@AConQATProcessor(description = "This processor converts a MetricCollection to"
		+ " IConQAT structure in order to visualize it, e.g. as table.")
public class MetricConverter extends ConQATProcessorBase {

	/** {@ConQAT.Doc} */
	@AConQATKey(description = "Key for measure value", type = "java.lang.Object")
	public static final String KEY = "Measure Value";

	/** The collection to convert. */
	private MetricCollection metricCollection;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, description = ConQATParamDoc.INPUT_DESC, minOccurrences = 1, maxOccurrences = 1)
	public void setMetricCollection(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) MetricCollection metricCollection) {
		this.metricCollection = metricCollection;
	}

	/** {@inheritDoc} 
	 * @throws ConQATException */
	@Override
	public ListNode process() throws ConQATException {
		ListNode result = new ListNode();
		FindingReport targetReport = NodeUtils.getFindingReport(result);

		for (String name : metricCollection.keySet()) {
			ListNode measureNode = new ListNode(name);
			result.addChild(measureNode);
			Object value = metricCollection.get(name);

			if (value instanceof FindingCollection) {
				FindingCollection findings = (FindingCollection) value;
				FindingsList list = new FindingsList(measureNode);
				list.addAll(FindingUtils.adoptFindings(targetReport, findings));
				value = list;

				value = "#findings=" + findings.size();
			}

			measureNode.setValue(KEY, value);

		}

		NodeUtils.addToDisplayList(result, KEY);

		return result;
	}

}
