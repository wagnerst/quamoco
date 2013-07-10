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
import org.conqat.engine.core.core.ConQATException;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Instrument;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.Type;

/**
 * {@ConQAT.Doc}
 * 
 * TODO (FD): There still is some kind of mismatch between tools/rules and
 * categories/groups.
 * 
 * @author Florian Deissenboeck
 * @author $Author: deissenb $
 * @version $Rev: 3418 $
 * @levd.rating YELLOW Hash: B9475193C0C241643F138BA50954C68F
 */
@AConQATProcessor(description = "This processor collects all metrics and provides them"
		+ "as a MetricCollection")
public class AutomaticMetricCollector extends ConQATProcessorBase {

	/** The scope of the evaluated system. */
	private IConQATNode scope;

	/** The quality model whose instrument are read */
	private QualityModel[] qualityModels;

	/** The result. */
	private final MetricCollection measureValues = new MetricCollection();

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, description = ConQATParamDoc.INPUT_DESC, minOccurrences = 1, maxOccurrences = 1)
	public void setScope(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) IConQATNode scope) {
		this.scope = scope;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "models", description = "Add the quality model whose instrument should be read.", minOccurrences = 1, maxOccurrences = 1)
	public void addDirectMeasure(
			@AConQATAttribute(name = "ref", description = "Reference to generating processor") QualityModel[] qualityModels) {
		this.qualityModels = qualityModels;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "metric-collection", description = "Add other metric collection.")
	public void addOptionalMetricCollection(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) MetricCollection collection) {
		measureValues.putAll(collection);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ConQATException
	 */
	@Override
	public MetricCollection process() throws ConQATException {
		for (QualityModel qualityModel : this.qualityModels) {
			TreeIterator<EObject> i = qualityModel.eAllContents();
			while (i.hasNext()) {
				EObject child = i.next();

				if (child instanceof Instrument) {

					if (child instanceof ToolBasedInstrument) {
						ToolBasedInstrument tbi = (ToolBasedInstrument) child;
						if (tbi.getDetermines().getType() == Type.FINDINGS) {
							addFindingMeasures(tbi);
						} else if (tbi.getDetermines().getType() == Type.NUMBER) {
							addPlainMeasures(tbi);
						} else {
							throw new IllegalStateException("Unknown type: "
									+ tbi.getDetermines().getType());
						}
					} else {
						getLogger().warn(
								"Instrument type '"
										+ child.getClass().getName()
										+ "' is not supported.");
					}
				}
			}
		}

		return measureValues;

	}

	/** Add plain measures. */
	private void addPlainMeasures(ToolBasedInstrument tbi) {
		Object value = scope.getValue(tbi.getMetric());
		if (value == null) {
			getLogger().warn(
					"Instrument '" + tbi.getQualifiedName()
							+ "' was not evaluated.");
		} else {
			CCSMAssert.isInstanceOf(value, Number.class);

			double nvalue = ((Number) value).doubleValue();

			measureValues.put(tbi.getQualifiedName(), nvalue);
		}
	}

	/** Add finding measures. */
	private void addFindingMeasures(ToolBasedInstrument tbi) {

		String tool = tbi.getTool().getName();
		String ruleIdentifier = tbi.getMetric();

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

		measureValues.put(tbi.getQualifiedName(), result);
	}

}
