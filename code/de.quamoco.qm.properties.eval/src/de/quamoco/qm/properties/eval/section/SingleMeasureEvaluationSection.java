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

package de.quamoco.qm.properties.eval.section;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.SingleMeasureEvaluation;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Range;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Scale;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;
import de.quamoco.qm.properties.eval.section.EvaluationFunctionArea.ValidateAndUpdateListener;
import de.quamoco.qm.properties.eval.util.Util;

/**
 * Defines a property section for {@link Evaluation} that allows the user to
 * define {@link SingleMeasureEvaluation}s.
 * 
 * @author Franz Becker
 */
public class SingleMeasureEvaluationSection extends
		AbstractGenerateEvaluationSection {

	/**
	 * Contains all the (reusable) controls shown in this section
	 */
	protected EvaluationFunctionArea evaluationFunctionArea;

	/**
	 * Hacks the parents layout a little, then creates a listener that calls
	 * {@link #executeUpdate()} if the {@link EvaluationFunctionArea} has
	 * changed (and is valid) and then creates the
	 * {@link EvaluationFunctionArea}.
	 */
	@Override
	protected void createControls(final Composite parent) {
		parent.setLayout(new GridLayout(2, false));

		try { // Hack but necessary for line break in xAxisLabel
			parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			parent.getParent().setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, true));
		} catch (Exception e) {
		}

		// EvaluationFunctionArea contains all the other controls
		evaluationFunctionArea = new EvaluationFunctionArea(parent,
				widgetFactory, false);

		ValidateAndUpdateListener validateAndUpdateListener = evaluationFunctionArea.new ValidateAndUpdateListener() {
			@Override
			public void handleValidUpdate(Event event) {
				executeUpdate();
			}
		};
		evaluationFunctionArea
				.setValidateAndUpdateListener(validateAndUpdateListener);
	}

	/**
	 * Forwards the task to {@link EvaluationFunctionArea}.
	 */
	@Override
	protected void setInput(Evaluation evaluation) {
		evaluationFunctionArea.setInput(evaluation);
	}

	/**
	 * Method is triggered by the listener when the
	 * {@link EvaluationFunctionArea} is valid and has changed => update the
	 * model
	 */
	protected void executeUpdate() {
		EditingDomain editingDomain = AdapterFactoryEditingDomain
				.getEditingDomainFor(evaluation);

		int maxPointsValue = evaluationFunctionArea.getMaxPoints();
		boolean incSelected = evaluationFunctionArea.isIncreasingFunction();
		double lowerBound = evaluationFunctionArea.getLowerBound();
		double upperBound = evaluationFunctionArea.getUpperBound();

		Measure evaluationMeasure = evaluationFunctionArea
				.getEvaluationMeasure();
		Scale scale = Util.getScaleFromMeasure(evaluationMeasure);
		Measure normalizationMeasure = evaluationFunctionArea
				.getNormalizationMeasure();

		Range definedRange = evaluationFunctionArea.getRange();

		EvaluationGenerator.MappingFunction mappingFunction = incSelected ? EvaluationGenerator.MappingFunction.LinearIncreasing
				: EvaluationGenerator.MappingFunction.LinearDecreasing;

		SetCommand setEvaluationMaxPoints = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.EVALUATION__MAXIMUM_POINTS,
				maxPointsValue);
		editingDomain.getCommandStack().execute(setEvaluationMaxPoints);

		EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
				.createEvaluationGenerator(evaluation);
		Command command = evaluationGenerator.generateEvalFunction(
				editingDomain, evaluation, maxPointsValue, evaluationMeasure,
				normalizationMeasure, scale, definedRange, mappingFunction,
				lowerBound, upperBound);
		editingDomain.getCommandStack().execute(command);

		// Update status code
		updateStatusText(evaluation);
	}

	@Override
	public void refresh() {
		if (evaluation instanceof SingleMeasureEvaluation) {
			SingleMeasureEvaluation smEval = (SingleMeasureEvaluation) evaluation;
			if (smEval.getMeasure() == null
					|| smEval.getMeasure().getQualityModel() == null
					|| (evaluationFunctionArea.getNormalizationMeasure() != null && evaluationFunctionArea
							.getNormalizationMeasure().getQualityModel() == null)) {
				setInput(smEval);
			}
		}
	}

}
