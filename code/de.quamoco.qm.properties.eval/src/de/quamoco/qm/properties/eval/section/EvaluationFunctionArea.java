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

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.properties.eval.Activator;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.MappingFunction;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Range;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Scale;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;
import de.quamoco.qm.properties.eval.model.RefiningMeasure;
import de.quamoco.qm.properties.eval.provider.EvaluationMeasureProvider;
import de.quamoco.qm.properties.eval.provider.NormalizationMeasureProvider;
import de.quamoco.qm.properties.eval.provider.RangeProvider;
import de.quamoco.qm.properties.eval.util.Util;

public class EvaluationFunctionArea {

	protected TabbedPropertySheetWidgetFactory widgetFactory;
	protected Listener validateAndUpdateListener;

	/*
	 * GUI controls
	 */
	private Text txtLowerBound;
	private Text txtUpperBound;
	private Button incPoints;
	private Button decPoints;
	private Spinner maxPoints;
	private ComboViewer measureForEvaluation;
	private ComboViewer measureForNormalization;
	private Label rangeLabel;
	private ComboViewer range;
	private Composite rangeComposite; // required since range has redraw
										// problems
	private final Color red;
	private Color defaultBg;
	private Image incImage;
	private Image decImage;
	private Image notDecidedImage;
	private Label lblImage;
	private Label xAxisLabel;

	public EvaluationFunctionArea(Composite parent,
			TabbedPropertySheetWidgetFactory widgetFactory,
			boolean isMultiMeasure) {
		Display display = Activator.getDefault().getWorkbench().getDisplay();
		red = new Color(display, 255, 225, 225);

		// Maximum Points
		Label maxPointsLabel = widgetFactory.createLabel(parent,
				"Maximum points:");
		maxPoints = new Spinner(parent, SWT.BORDER);
		maxPoints.setMinimum(0);
		maxPoints.setMaximum(Integer.MAX_VALUE);

		// set default background color
		defaultBg = maxPoints.getBackground();

		// Measure used for evaluation
		Label measureForEvaluationLabel = widgetFactory.createLabel(parent,
				"Measure used for \nEvaluation:", SWT.NONE);
		measureForEvaluation = new ComboViewer(widgetFactory
				.createCCombo(parent));
		EvaluationMeasureProvider evaluationMeasureProvider = new EvaluationMeasureProvider();
		measureForEvaluation.setContentProvider(evaluationMeasureProvider);
		measureForEvaluation.setLabelProvider(evaluationMeasureProvider);

		/*
		 * Measure used for normalization
		 */
		widgetFactory.createLabel(parent, "Measure used for \nNormalization:",
				SWT.NONE);
		Composite normMeasureComposite = widgetFactory.createComposite(parent);
		GridLayout normMeasureLayout = new GridLayout(2, false);
		normMeasureLayout.marginWidth = 1;
		normMeasureComposite.setLayout(normMeasureLayout);
		measureForNormalization = new ComboViewer(widgetFactory
				.createCCombo(normMeasureComposite));
		NormalizationMeasureProvider normalizationMeasureProvider = new NormalizationMeasureProvider();
		measureForNormalization
				.setContentProvider(normalizationMeasureProvider);
		measureForNormalization.setLabelProvider(normalizationMeasureProvider);
		// Clear button
		final Button clearMeasureForNormalization = widgetFactory.createButton(
				normMeasureComposite, "", SWT.PUSH);
		Image clearImage = Activator.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "icons/clear.gif").createImage();
		clearMeasureForNormalization.setImage(clearImage);
		clearMeasureForNormalization
				.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						measureForNormalization.setSelection(null);
						// manual call necessary, otherwise no notification
						validateAndUpdateListener.handleEvent(null);
						measureForNormalization.getCCombo().setToolTipText("");
					}
				});
		measureForNormalization
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						boolean emptySelection = event.getSelection() == null
								|| event.getSelection().isEmpty();
						clearMeasureForNormalization
								.setVisible(!emptySelection);
					}
				});
		clearMeasureForNormalization.setVisible(false);

		// Listener creating tooltips for measureForEvaluation and
		// measureForNormalization
		ISelectionChangedListener comboToolTipListener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ComboViewer comboViewer = (ComboViewer) event.getSource();
				Measure selectedMeasure = (Measure) ((IStructuredSelection) event
						.getSelection()).getFirstElement();
				if (selectedMeasure != null) {
					comboViewer.getCCombo().setToolTipText(
							selectedMeasure.getDescription());
				} else {
					comboViewer.getCCombo().setToolTipText("");
				}
			}
		};
		measureForEvaluation
				.addPostSelectionChangedListener(comboToolTipListener);
		measureForNormalization
				.addSelectionChangedListener(comboToolTipListener);

		// Range
		rangeLabel = widgetFactory.createLabel(parent, "Range:");
		rangeComposite = widgetFactory.createComposite(parent);
		{
			GridLayout gridLayout = new GridLayout(1, true);
			gridLayout.marginWidth = 1;
			gridLayout.marginHeight = 1;
			rangeComposite.setLayout(gridLayout);
		}
		range = new ComboViewer(widgetFactory.createCCombo(rangeComposite));
		RangeProvider rangeProvider = new RangeProvider();
		range.setContentProvider(rangeProvider);
		range.setLabelProvider(rangeProvider);
		measureForEvaluation
				.addPostSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						Measure selectedMeasure = (Measure) ((IStructuredSelection) measureForEvaluation
								.getSelection()).getFirstElement();
						if (selectedMeasure != null) {
							Scale scale = Util
									.getScaleFromMeasure(selectedMeasure);
							if (scale != null) {
								boolean rangeVisible = scale
										.equals(Scale.FINDINGS);
								setRangeVisibility(rangeVisible);
							}
						}
					}
				});
		setRangeVisibility(false);

		// function label
		Label lblFunction = widgetFactory.createLabel(parent, "Function: ");
		lblFunction.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING,
				false, false));

		/*
		 * New composite that included the following elements
		 */
		Composite functionComposite = widgetFactory.createComposite(parent);
		functionComposite.setLayout(new GridLayout(1, true));
		functionComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL,
				true, true));

		// Radio buttons for inc and dec function
		incPoints = widgetFactory.createButton(functionComposite,
				"Linear Increasing Points", SWT.RADIO);
		decPoints = widgetFactory.createButton(functionComposite,
				"Linear Decreasing Points", SWT.RADIO);

		// Images
		notDecidedImage = Activator.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "img/EvalFuncNotDecided.png")
				.createImage();
		incImage = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				"img/EvalFuncLinearInc.png").createImage();
		decImage = Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
				"img/EvalFuncLinearDec.png").createImage();
		lblImage = widgetFactory.createLabel(functionComposite, "");
		lblImage.setImage(notDecidedImage);
		incPoints.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (incPoints.getSelection()) {
					lblImage.setImage(incImage);
				}
			}
		});
		decPoints.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (decPoints.getSelection()) {
					lblImage.setImage(decImage);
				}
			}
		});

		/*
		 * Edit fields: lowerBound / upperBound decUpperBound / decLowerBound
		 */
		Composite editComposite = widgetFactory
				.createComposite(functionComposite);
		editComposite.setLayout(new GridLayout(4, false));
		editComposite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING,
				false, false));
		Label placeHolder1 = widgetFactory.createLabel(editComposite, "");
		txtLowerBound = widgetFactory.createText(editComposite, "");
		Label placeHolder2 = widgetFactory.createLabel(editComposite, "");
		txtUpperBound = widgetFactory.createText(editComposite, "");
		GridData placeHolder1Width = new GridData(SWT.BEGINNING, SWT.BEGINNING,
				false, false);
		placeHolder1Width.widthHint = 57;
		GridData placeHolder2Width = new GridData(SWT.BEGINNING, SWT.BEGINNING,
				false, false);
		placeHolder2Width.widthHint = 20;
		placeHolder1.setLayoutData(placeHolder1Width);
		placeHolder2.setLayoutData(placeHolder2Width);
		final int textFieldsWidth = 40;
		GridData leftText = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false);
		leftText.widthHint = textFieldsWidth;
		GridData rightText = new GridData(SWT.BEGINNING, SWT.BEGINNING, false,
				false);
		rightText.widthHint = textFieldsWidth;
		txtLowerBound.setLayoutData(leftText);
		txtUpperBound.setLayoutData(rightText);

		// Text for X-Axis
		Composite xAxisTextComposite = widgetFactory
				.createComposite(functionComposite);
		xAxisTextComposite.setLayout(new GridLayout(2, false));
		xAxisTextComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));
		Label placeHolder3 = widgetFactory.createLabel(xAxisTextComposite, "");
		GridData placeHolder3Width = new GridData();
		placeHolder3Width.widthHint = 25;
		placeHolder3Width.verticalSpan = 3;
		placeHolder3.setLayoutData(placeHolder3Width);
		xAxisLabel = widgetFactory.createLabel(xAxisTextComposite, "",
				SWT.CENTER | SWT.WRAP);
		xAxisLabel.setBackground(defaultBg);
		{
			// Set layout data for xAxisLabel
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
			gridData.verticalSpan = 3;
			gridData.widthHint = 200;
			xAxisLabel.setLayoutData(gridData);
		}

		/*
		 * Set function label according to the values specified for the
		 * measures.
		 */
		ISelectionChangedListener measureSelectionChangedListener = new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				refreshXAxisLabel();
			}
		};
		measureForEvaluation
				.addSelectionChangedListener(measureSelectionChangedListener);
		measureForNormalization
				.addSelectionChangedListener(measureSelectionChangedListener);

		/*
		 * Hide maxPoints and measureUsedForEvaluation controls if necessary
		 */
		if (isMultiMeasure) {
			GridData data = new GridData();
			data.exclude = true;
			maxPointsLabel.setLayoutData(data);
			maxPoints.setLayoutData(data);
			measureForEvaluationLabel.setLayoutData(data);
			measureForEvaluation.getControl().setLayoutData(data);
			maxPointsLabel.setVisible(false);
			maxPoints.setVisible(false);
			measureForEvaluationLabel.setVisible(false);
			measureForEvaluation.getControl().setVisible(false);
		}

	}

	public void setValidateAndUpdateListener(
			ValidateAndUpdateListener validateAndUpdateListener) {
		if (this.validateAndUpdateListener != null) {
			// remove old one
			deactivateValidateAndUpdateListener();
		}
		this.validateAndUpdateListener = validateAndUpdateListener;
		activateValidateAndUpdateListener();
	}

	protected void activateValidateAndUpdateListener() {
		// Add passed listener to the controls
		maxPoints.addListener(SWT.Modify, validateAndUpdateListener);
		measureForEvaluation.getCCombo().addListener(SWT.Selection,
				validateAndUpdateListener);
		measureForNormalization.getCCombo().addListener(SWT.Selection,
				validateAndUpdateListener);
		range.getCCombo().addListener(SWT.Selection, validateAndUpdateListener);
		incPoints.addListener(SWT.Selection, validateAndUpdateListener);
		decPoints.addListener(SWT.Selection, validateAndUpdateListener);
		txtLowerBound.addListener(SWT.Modify, validateAndUpdateListener);
		txtUpperBound.addListener(SWT.Modify, validateAndUpdateListener);
	}

	protected void deactivateValidateAndUpdateListener() {
		// Add passed listener to the controls
		maxPoints.removeListener(SWT.Modify, validateAndUpdateListener);
		measureForEvaluation.getCCombo().removeListener(SWT.Selection,
				validateAndUpdateListener);
		measureForNormalization.getCCombo().removeListener(SWT.Selection,
				validateAndUpdateListener);
		range.getCCombo().removeListener(SWT.Selection,
				validateAndUpdateListener);
		incPoints.removeListener(SWT.Selection, validateAndUpdateListener);
		decPoints.removeListener(SWT.Selection, validateAndUpdateListener);
		txtLowerBound.removeListener(SWT.Modify, validateAndUpdateListener);
		txtUpperBound.removeListener(SWT.Modify, validateAndUpdateListener);
	}

	/**
	 * Evaluates this composite and returns whether it is valid or not. It is
	 * valid when all values are set and the lowerBound is less than the upper
	 * bound.<br>
	 * Additionally sets the background color to indicate which fields are valid
	 * and which are not.
	 */
	public boolean isValid() {
		boolean maxPointsSet = !maxPoints.getText().isEmpty();
		boolean measureForEvaulationSet = !measureForEvaluation.getSelection()
				.isEmpty();
		boolean functionSelected = incPoints.getSelection()
				|| decPoints.getSelection();
		boolean boundsCorrect;
		try {
			String lowerBound = txtLowerBound.getText();
			String upperBound = txtUpperBound.getText();
			double l = Double.parseDouble(lowerBound);
			double u = Double.parseDouble(upperBound);
			boundsCorrect = l < u;
		} catch (Exception e) {
			boundsCorrect = false;
		}

		maxPoints.setBackground(maxPointsSet ? defaultBg : red);
		measureForEvaluation.getCCombo().setBackground(
				measureForEvaulationSet ? defaultBg : red);
		incPoints.setBackground(functionSelected ? defaultBg : red);
		decPoints.setBackground(functionSelected ? defaultBg : red);
		txtLowerBound.setBackground(boundsCorrect ? defaultBg : red);
		txtUpperBound.setBackground(boundsCorrect ? defaultBg : red);

		boolean result = maxPointsSet && measureForEvaulationSet
				&& functionSelected && boundsCorrect;
		return result;
	}

	/**
	 * Set the fields of the evaluation function area according to the passed
	 * {@link RefiningMeasure}.
	 * 
	 * @param refiningMeasure
	 */
	public void setInput(RefiningMeasure refiningMeasure) {
		deactivateValidateAndUpdateListener();

		Measure evaluationMeasure = refiningMeasure.getElement();
		NormalizationMeasure normalizationMeasure = refiningMeasure
				.getNormalizationMeasure();
		Range definedRange = refiningMeasure.getRange();
		MappingFunction function = refiningMeasure.getMappingFunction();
		double lowerBound = refiningMeasure.getLowerBound();
		double upperBound = refiningMeasure.getUpperBound();

		// measure for evaluation
		measureForEvaluation.setSelection(evaluationMeasure == null ? null
				: new StructuredSelection(evaluationMeasure), true);

		// measure for normalization
		measureForNormalization.setSelection(
				normalizationMeasure == null ? null : new StructuredSelection(
						normalizationMeasure), true);

		// range
		range.setSelection(definedRange == null ? null
				: new StructuredSelection(definedRange), true);

		// increasing or decreasing function
		boolean inc = MappingFunction.LinearIncreasing.equals(function);
		boolean dec = MappingFunction.LinearDecreasing.equals(function);
		incPoints.setSelection(inc);
		decPoints.setSelection(dec);
		lblImage.setImage(inc ? incImage : (dec ? decImage : notDecidedImage));

		// lower and upper bound
		txtLowerBound.setText(Double.toString(lowerBound));
		txtUpperBound.setText(Double.toString(upperBound));

		refreshXAxisLabel();

		activateValidateAndUpdateListener();
	}

	/**
	 * Sets the values for this composite according to the {@link Evaluation}
	 * passed. If it's specification was
	 * {@link EvaluationGenerator#isGenerated(Evaluation) generated}, then the
	 * values will be set according to the already generated specification.
	 * Otherwise default values will be used.
	 * 
	 * @param evaluation
	 *            the {@link Evaluation} of interest.
	 */
	protected void setInput(Evaluation evaluation) {
		deactivateValidateAndUpdateListener();

		measureForEvaluation.setInput(evaluation);
		measureForNormalization.setInput(evaluation);

		range.setInput(evaluation); // no parameter necessary, but null
									// forbidden

		EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
				.createEvaluationGenerator(evaluation);

		/*
		 * Max points
		 */
		Factor factor = evaluation.getEvaluates();
		maxPoints.setSelection(evaluation.getMaximumPoints());

		if (evaluationGenerator.isGenerated(evaluation)
				&& evaluationGenerator
						.isGeneratedEvaluationFunction(evaluation)) {
			try {
				// Set value for measureForEvaluation
				Measure evaluationMeasure = evaluationGenerator
						.getEvaluationMeasure(evaluation);
				measureForEvaluation.setSelection(
						evaluationMeasure == null ? null
								: new StructuredSelection(evaluationMeasure),
						true);

				// Set value for measureForNormalization
				Measure normalizationMeasure = evaluationGenerator
						.getNormalizationMeasure(evaluation);
				measureForNormalization
						.setSelection(
								normalizationMeasure == null ? null
										: new StructuredSelection(
												normalizationMeasure), true);

				range.setSelection(new StructuredSelection(evaluationGenerator
						.getRange(evaluation)));

				/*
				 * Set function values
				 */
				MappingFunction function = evaluationGenerator
						.getMappingFunction(evaluation);
				boolean inc = MappingFunction.LinearIncreasing.equals(function);
				boolean dec = MappingFunction.LinearDecreasing.equals(function);
				incPoints.setSelection(inc);
				decPoints.setSelection(dec);
				lblImage.setImage(inc ? incImage : (dec ? decImage
						: notDecidedImage));
				txtLowerBound.setText(Double.toString(evaluationGenerator
						.getLowerBound(evaluation)));
				txtUpperBound.setText(Double.toString(evaluationGenerator
						.getUpperBound(evaluation)));
				refreshXAxisLabel();
			} catch (ParseQIESLException e) {
				e.printStackTrace();
				// TODO2 show error message
			}
		} else {
			// If the Factor of an Evaluation has only one measure, preselect
			// this one
			if (factor != null && factor.getMeasuredByDirect().size() == 1) {
				measureForEvaluation.setSelection(new StructuredSelection(
						factor.getMeasuredByDirect().get(0)));
			}

			range.setSelection(new StructuredSelection(Range.NA));

			incPoints.setSelection(false);
			decPoints.setSelection(false);
			txtLowerBound.setText("");
			txtUpperBound.setText("");
			refreshXAxisLabel();
		}

		activateValidateAndUpdateListener();
	}

	protected void refreshXAxisLabel() {
		String mEval = measureForEvaluation.getCCombo().getText();
		String mNorm = measureForNormalization.getCCombo().getText();
		if ((mEval != null) && !mEval.isEmpty()) {
			if ((mNorm != null) && !mNorm.isEmpty()) {
				xAxisLabel.setText(mEval + " / " + mNorm);
			} else {
				xAxisLabel.setText(mEval);
			}
		} else {
			xAxisLabel.setText("");
		}

		xAxisLabel.getShell().layout(true, true);
	}

	protected void setRangeVisibility(boolean visible) {
		GridData data = new GridData();
		data.exclude = !visible;
		rangeLabel.setLayoutData(data);
		rangeLabel.setVisible(visible);
		rangeComposite.setLayoutData(data);
		rangeComposite.setVisible(visible);
		rangeLabel.getParent().layout();
	}

	protected boolean isRangeVisible() {
		return rangeLabel.getVisible();
	}

	/*
	 * Getter section
	 */

	public int getMaxPoints() {
		return Integer.parseInt(maxPoints.getText());
	}

	public boolean isIncreasingFunction() {
		return incPoints.getSelection();
	}

	public boolean isDecreasingFunction() {
		return decPoints.getSelection();
	}

	public double getLowerBound() {
		return Double.valueOf(txtLowerBound.getText());
	}

	public double getUpperBound() {
		return Double.valueOf(txtUpperBound.getText());
	}

	public Measure getEvaluationMeasure() {
		return (Measure) ((IStructuredSelection) measureForEvaluation
				.getSelection()).getFirstElement();
	}

	public NormalizationMeasure getNormalizationMeasure() {
		if (measureForNormalization.getSelection() != null) {
			return (NormalizationMeasure) ((IStructuredSelection) measureForNormalization
					.getSelection()).getFirstElement();
		}
		return null;
	}

	public Range getRange() {
		if (isRangeVisible()) {
			return (Range) ((IStructuredSelection) range.getSelection())
					.getFirstElement();
		} else {
			return Range.NA;
		}
	}

	/**
	 * Abstract super class to implement some common behavior.
	 * 
	 * @author Franz Becker
	 */
	public abstract class ValidateAndUpdateListener implements Listener {

		@Override
		public final void handleEvent(Event event) {
			/*
			 * Do not handle events from radio button that gets unchecked,
			 * because the other one will get checked and throw another event
			 */
			if (event != null
					&& (event.widget == incPoints || event.widget == decPoints)) {
				Button eventRadioButton = (Button) event.widget;
				if (!eventRadioButton.getSelection()) {
					return;
				}
			}
			if (isValid()) {
				handleValidUpdate(event);
			}
		}

		public abstract void handleValidUpdate(Event event);

	}

}
