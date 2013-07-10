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

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.MappingFunction;
import de.quamoco.qm.properties.eval.model.RefiningMeasure;
import de.quamoco.qm.properties.eval.provider.MeasureAggregationContentProvider;
import de.quamoco.qm.properties.eval.section.EvaluationFunctionArea.ValidateAndUpdateListener;

/**
 * Defines a property section for {@link Evaluation} that allows the user to
 * specify {@link MultiMeasureEvaluation}s.
 * 
 * @author Franz Becker
 */
public class MultiMeasureEvaluationSection extends AbstractAggregationSection {

	/**
	 * Contains all the (reusable) controls shown in this section
	 */
	protected EvaluationFunctionArea evaluationFunctionArea;
	protected Composite evaluationFunctionComposite;

	@Override
	protected void createControls(final Composite parent) {
		super.createControls(parent);

		try { // Hack but necessary for line break in xAxisLabel
			parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			parent.getParent().setLayoutData(
					new GridData(SWT.FILL, SWT.FILL, true, true));
		} catch (Exception e) {
		}

		evaluationFunctionComposite = widgetFactory.createComposite(parent);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 0;
		evaluationFunctionComposite.setLayout(gridLayout);
		evaluationFunctionComposite.setLayoutData(new GridData(SWT.FILL,
				SWT.FILL, true, true));
		evaluationFunctionComposite.setVisible(false);
		evaluationFunctionArea = new EvaluationFunctionArea(
				evaluationFunctionComposite, widgetFactory, true);
		MyValidateAndUpdateListener validateAndUpdateListener = new MyValidateAndUpdateListener();
		evaluationFunctionArea
				.setValidateAndUpdateListener(validateAndUpdateListener);

		tableViewer
				.addPostSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						if (event.getSelection() instanceof IStructuredSelection) {
							IStructuredSelection selection = (IStructuredSelection) event
									.getSelection();
							if (selection.getFirstElement() instanceof RefiningMeasure) {
								evaluationFunctionArea
										.setInput((RefiningMeasure) selection
												.getFirstElement());
								evaluationFunctionArea.isValid(); // for
																	// coloring
								evaluationFunctionComposite.setVisible(true);
								parent.layout();
							} else {
								evaluationFunctionComposite.setVisible(false);
							}

						} else {
							evaluationFunctionComposite.setVisible(false);
						}
					}
				});
	}

	@Override
	protected IStructuredContentProvider getContentProvider() {
		return new MeasureAggregationContentProvider();
	}

	@Override
	protected String getElementName() {
		return "measure";
	}

	@Override
	protected String getElementPluralName() {
		return "measures";
	}

	@Override
	protected void setInputSubclass(Evaluation evaluation) {
		// Workaround, see javadoc for details
		tableViewer.setInput(MeasureAggregationContentProvider
				.getElementsStatic(evaluation));
		evaluationFunctionArea.setInput(evaluation);
		evaluationFunctionArea.isValid(); // for coloring

		// automatically set all rankings to 1 if evaluation has no rankings yet
		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			if (((WeightedSumMultiMeasureEvaluation) evaluation).getRankings()
					.isEmpty()) {
				setAllElementRankingsTo(1);
			}
		}
	}

	/**
	 * Method is triggered by the listener when the
	 * {@link EvaluationFunctionArea} is valid and has changed => update the
	 * model
	 */
	protected void updateRefiningMeasure(RefiningMeasure refiningMeasure) {
		refiningMeasure.setNormalizationMeasure(evaluationFunctionArea
				.getNormalizationMeasure());
		boolean incSelected = evaluationFunctionArea.isIncreasingFunction();
		boolean decSelected = evaluationFunctionArea.isDecreasingFunction();
		refiningMeasure
				.setMappingFunction(incSelected ? MappingFunction.LinearIncreasing
						: decSelected ? MappingFunction.LinearDecreasing : null);
		refiningMeasure.setLowerBound(evaluationFunctionArea.getLowerBound());
		refiningMeasure.setUpperBound(evaluationFunctionArea.getUpperBound());
		refiningMeasure.setRange(evaluationFunctionArea.getRange());

		maxPointsModifyListener.modifyText(null); // hacky but does the trick
													// (update)
	}

	@Override
	protected Collection<? extends Ranking> getRankings() {
		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			return ((WeightedSumMultiMeasureEvaluation) evaluation)
					.getRankings();
		}
		return Collections.emptyList();
	}

	/**
	 * Handles the events coming from the {@link EvaluationFunctionArea}.
	 * 
	 * @author Franz Becker
	 */
	protected class MyValidateAndUpdateListener extends
			ValidateAndUpdateListener {

		public MyValidateAndUpdateListener() {
			evaluationFunctionArea.super();
		}

		@Override
		public void handleValidUpdate(Event event) {
			RefiningMeasure refiningMeasure = (RefiningMeasure) (tableViewer
					.getSelection() != null ? ((IStructuredSelection) tableViewer
					.getSelection()).getFirstElement()
					: null);

			if (refiningMeasure != null) {
				if (evaluationFunctionArea.getEvaluationMeasure() == refiningMeasure
						.getElement()) {
					updateRefiningMeasure(refiningMeasure);
				} else {
					throw new IllegalStateException();
				}
			} // else do nothing
		}

	}

}
