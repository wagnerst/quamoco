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

package de.quamoco.qm.properties;

import java.text.NumberFormat;
import java.util.List;

import org.conqat.ide.commons.ui.ui.TableViewerUtils;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Property section to display the result of an evaluation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class EvaluationResultPropertySection extends
		ResultPropertySectionBase<Evaluation> {

	/** {@inheritDoc} */
	@Override
	protected void createValue(Composite composite) {
		super.createValue(composite);
		TableViewerUtils.createColumn(resultViewer, "Value", 100, SWT.None,
				new ColumnLabelProvider() {
					@Override
					public String getText(Object element) {
						EvaluationResult evaluationResult = (EvaluationResult) element;
						DoubleInterval interval = evaluationResult.getValue();
						NumberFormat format = NumberFormat.getNumberInstance();
						Evaluation evaluation = evaluationResult
								.getResultsFrom();
						double lower = evaluation.getMaximumPoints()
								* interval.getLower();
						double upper = evaluation.getMaximumPoints()
								* interval.getUpper();
						if (lower == upper) {
							return "" + lower;
						}
						return "[" + format.format(lower) + ";"
								+ format.format(upper) + "]";
					}
				});
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		Evaluation evaluation = getElement();
		List<EvaluationResult> evaluationResults = QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getEvaluationResult_ResultsFrom(),
				evaluation, EvaluationResult.class);
		resultViewer.setInput(evaluationResults);
	}
}
