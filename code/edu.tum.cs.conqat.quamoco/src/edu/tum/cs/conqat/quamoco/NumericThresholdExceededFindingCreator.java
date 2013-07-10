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

import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.FindingCategory;
import org.conqat.engine.commons.findings.FindingGroup;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.commons.node.NodeConstants;
import org.conqat.engine.commons.node.NodeUtils;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IElement;
import org.conqat.engine.resource.IResource;
import org.conqat.engine.resource.analysis.ElementAnalyzerBase;

@AConQATProcessor(description = "")
public class NumericThresholdExceededFindingCreator extends
		ElementAnalyzerBase<IResource, IElement> {

	@AConQATFieldParameter(parameter = "readKey", attribute = "name", optional = false, description = "")
	public String readKey;

	@AConQATFieldParameter(parameter = "writeKey", attribute = "name", optional = false, description = "")
	public String writeKey;

	@AConQATFieldParameter(parameter = "threshold", attribute = "value", optional = false, description = "")
	public double threshold;

	@AConQATFieldParameter(parameter = "findingCategory", attribute = "name", optional = false, description = "")
	public String findingCategoryName;

	@AConQATFieldParameter(parameter = "rule", attribute = "name", optional = false, description = "")
	public String ruleName;

	@AConQATFieldParameter(parameter = "message", attribute = "text", optional = false, description = "")
	public String message;

	private FindingGroup group;

	@Override
	protected void setUp(IResource root) throws ConQATException {
		super.setUp(root);
		FindingReport report = NodeUtils.getFindingReport(root);
		FindingCategory category = report
				.getOrCreateCategory(findingCategoryName);
		group = category.getOrCreateFindingGroup(ruleName);
		group.setValue(NodeConstants.RULE_IDENTIFIER_KEY, ruleName);
		NodeUtils.addToDisplayList(root, writeKey);
	}

	@Override
	protected void analyzeElement(IElement element) throws ConQATException {
		Double value = Double.valueOf(element.getValue(readKey).toString());
		if (value.doubleValue() > threshold) {
			
			
			Finding finding = group.createFinding(new ElementLocation(element.getLocation(), element.getUniformPath()));
			
			// message is not used anymore after restructuring to new findings framework of 21.12.2012

			NodeUtils.getOrCreateFindingsList(element, writeKey).add(finding);
		}

	}

	@Override
	protected String[] getKeys() {
		return new String[] { writeKey };
	}
}
