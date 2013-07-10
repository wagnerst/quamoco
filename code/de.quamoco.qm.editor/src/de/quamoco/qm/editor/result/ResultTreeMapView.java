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

/*--------------------------------------------------------------------------+
$Id: ResultTreeMapView.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 Technische Universitaet Muenchen                     |
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
+--------------------------------------------------------------------------*/
package de.quamoco.qm.editor.result;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import org.conqat.ide.commons.ui.color.IColorProvider;
import org.conqat.ide.commons.ui.treemap.ITreeMapContentProvider;
import org.conqat.ide.commons.ui.treemap.ITreeMapLabelProvider;
import org.conqat.ide.commons.ui.treemap.ITreeMapListener;
import org.conqat.ide.commons.ui.treemap.TreeMapLabelProviderBase;
import org.conqat.ide.commons.ui.treemap.TreeMapWidget;
import org.conqat.lib.commons.treemap.CushionTreeMapRenderer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import de.quamoco.qm.DoubleInterval;
import de.quamoco.qm.Effect;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.EvaluationResult;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorAggregation;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.Impact;
import de.quamoco.qm.MeasureRanking;
import de.quamoco.qm.MeasureRankingEvaluationResult;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.MultiMeasureEvaluation;
import de.quamoco.qm.MultiMeasureEvaluationResult;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.Result;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.WeightedSumMultiMeasureEvaluation;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.emf.commons.resources.ColorCache;
import edu.tum.cs.emf.commons.views.EditorSpecificViewBase;

/**
 * View that displays the evaluation result as a tree map. The tree map only
 * shows one level of the evaluation result, i.e. the result of one
 * {@link FactorAggregation} or one {@link MultiMeasureEvaluation}. The area
 * denotes the weight of the sub factors or measures and the color their value.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class ResultTreeMapView extends EditorSpecificViewBase<CustomQmEditor> {

	/** different ways to color the tree map. **/
	enum EColoring {
		/** Automatically determine coloring. */
		Auto,
		/** Use result value to choose color. */
		Value,
		/** Use result grade to choose color. */
		Grade
	}

	/** The identifier of the view as in the plugin.xml. */
	public static final String ID = ResultTreeMapView.class.getName();

	/** {@link TreeMapWidget} to display the evaluation result and its children. */
	private TreeMapWidget<Result> widget;

	/**
	 * The evaluation result whose children are currently displayed in the tree
	 * map.
	 */
	private EvaluationResult context;

	/**
	 * Label to display the qualified name of the evaluation whose result is the
	 * context of tree map.
	 */
	private Label contextLabel;

	/**
	 * Label to display the qualified name of the child which is currently
	 * selected.
	 */
	private Label childLabel;

	/** Whether the color needs to be inverted due to negative impacts. */
	private boolean invert = false;

	/** Navigation path to the current context element. */
	private final Stack<EvaluationResult> navigation = new Stack<EvaluationResult>();

	/** The current coloring. */
	private EColoring coloring = EColoring.Auto;

	/** Map of colorings to actions which allow to select the coloring. */
	private IdentityHashMap<EColoring, Action> actions;

	/** {@inheritDoc} */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		Composite composite = new Composite(parent, SWT.None);
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.horizontalSpacing = layout.verticalSpacing = 2;
		layout.marginLeft = layout.marginTop = 2;
		composite.setLayout(layout);

		contextLabel = new Label(composite, SWT.None);
		contextLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		widget = new TreeMapWidget<Result>(composite);
		widget.setLayoutData(new GridData(GridData.FILL_BOTH));
		widget.setContentProvider(new TreeMapContentProvider());
		widget.setColorProvider(new TreeMapColorProvider());
		widget.addTreeMapListener(new TreeMapListener());
		widget.setTextColor(Display.getDefault()
				.getSystemColor(SWT.COLOR_BLACK));
		widget.setLabelProvider(new TreeMapLabelProvider());
		widget.setRenderer(new CushionTreeMapRenderer(0.7, 0.7));

		childLabel = new Label(composite, SWT.None);
		childLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		IToolBarManager toolBar = getViewSite().getActionBars()
				.getToolBarManager();
		actions = new IdentityHashMap<ResultTreeMapView.EColoring, Action>();
		for (final EColoring coloring : EColoring.values()) {
			Action action = new Action(coloring.name(), IAction.AS_RADIO_BUTTON) {

				/** {@inheritDoc} */
				@Override
				public void run() {
					if (isChecked()) {
						setColoring(coloring);
					}
				}
			};
			if (coloring.ordinal() == 0) {
				action.setChecked(true);
			}
			toolBar.add(action);
			actions.put(coloring, action);
		}
	}

	/** Set the current coloring. */
	protected void setColoring(EColoring coloring) {
		this.coloring = coloring;
		getDialogSettings().put(EColoring.class.getSimpleName(),
				coloring.name());
		widget.refresh();
		contextLabel.setText(getLabel(context));
	}

	/** Set the result whose children should be displayed. */
	public void setResult(EvaluationResult result, CustomQmEditor editor) {
		setEditor(editor);
		navigation.clear();
		doSetResult(result);
		invert = false;

	}

	/** Navigate forward to a child as context element. */
	private void navigateForward(EvaluationResult result) {
		navigation.push(context);
		if (isNegativeImpact(result, context)) {
			invert = !invert;
		}
		doSetResult(result);
	}

	/** Navigate backward to the previous context element. */
	private void navigateBackward() {
		EvaluationResult result = navigation.pop();
		if (isNegativeImpact(context, result)) {
			invert = !invert;
		}
		doSetResult(result);
	}

	/** Actually set the result. */
	private void doSetResult(EvaluationResult result) {
		this.context = result;
		String coloringName = getDialogSettings().get(
				EColoring.class.getSimpleName());
		if (coloringName != null) {
			EColoring coloring = EColoring.valueOf(coloringName);
			for (Entry<EColoring, Action> entry : actions.entrySet()) {
				entry.getValue().setChecked(entry.getKey() == coloring);
			}
			this.coloring = coloring;
		}
		widget.setInput(result);
		contextLabel.setText(getLabel(result));
	}

	/** Get the dialog settings for this view. */
	protected IDialogSettings getDialogSettings() {
		return QmEditorPlugin.getPlugin().getDialogSettings(
				getClass().getSimpleName());
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus() {
		widget.setFocus();
	}

	/** {@link ITreeMapContentProvider} for the tree map. */
	private class TreeMapContentProvider implements
			ITreeMapContentProvider<Result> {

		/** {@inheritDoc} */
		@Override
		public Result[] getChildren(Result element) {
			if (element == context) {
				if (element instanceof EvaluationResult) {
					EvaluationResult evaluationResult = (EvaluationResult) element;
					Evaluation evaluation = evaluationResult.getResultsFrom();
					if (evaluation instanceof WeightedSumFactorAggregation) {
						WeightedSumFactorAggregation factorEvaluation = (WeightedSumFactorAggregation) evaluation;
						return getChildren(element, factorEvaluation);
					} else if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
						WeightedSumMultiMeasureEvaluation measureEvaluation = (WeightedSumMultiMeasureEvaluation) evaluation;
						return getChildren(element, measureEvaluation);
					}
				}
			}
			return new Result[0];
		}

		/** Get children of a result for a {@link WeightedSumFactorAggregation}. */
		private Result[] getChildren(Result element,
				WeightedSumFactorAggregation factorEvaluation) {
			List<EvaluationResult> results = new ArrayList<EvaluationResult>();
			for (FactorRanking ranking : factorEvaluation.getRankings()) {
				if (ranking.getRank() == 0) {
					continue;
				}
				EvaluationResult result = QmModelUtils.getResult(
						(QualityModelResult) element.eContainer(),
						ranking.getFactor());
				if (result != null) {
					results.add(result);
				}
			}
			return results.toArray(new Result[0]);
		}

		/**
		 * Get children of a result for a
		 * {@link WeightedSumMultiMeasureEvaluation}.
		 */
		private Result[] getChildren(Result element,
				WeightedSumMultiMeasureEvaluation measureEvaluation) {
			List<MeasurementResult> results = new ArrayList<MeasurementResult>();
			for (MeasureRanking ranking : measureEvaluation.getRankings()) {
				if (ranking.getRank() == 0) {
					continue;
				}
				MeasurementResult result = QmModelUtils.getResult(
						(QualityModelResult) element.eContainer(),
						ranking.getMeasure());
				if (result != null) {
					results.add(result);
				}
			}
			return results.toArray(new Result[0]);
		}
	}

	/** The {@link IColorProvider} for the tree map. */
	private class TreeMapColorProvider implements IColorProvider<Result> {

		/** {@inheritDoc} */
		@Override
		public Color getColor(Result result) {
			if (result instanceof EvaluationResult) {
				EvaluationResult evaluationResult = (EvaluationResult) result;
				return getColor(evaluationResult);
			} else if (result instanceof MeasurementResult) {
				MeasurementResult measurementResult = (MeasurementResult) result;
				return getColor(measurementResult);
			}
			return Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		}

		/** Get the color for an {@link EvaluationResult}. */
		protected Color getColor(EvaluationResult evaluationResult) {
			switch (coloring) {
			case Grade:
				return getSchoolGradeColor(evaluationResult);
			case Value:
				return getValueColor(evaluationResult);
			default:
				boolean qualityAspect = evaluationResult.getResultsFrom()
						.getEvaluates().isQualityAspect();
				return qualityAspect ? getSchoolGradeColor(evaluationResult)
						: getValueColor(evaluationResult);
			}
		}

		/** Get the result value-based color for an {@link EvaluationResult}. */
		protected Color getValueColor(EvaluationResult evaluationResult) {
			// for product-factors
			double value = evaluationResult.getValue().getLower();
			boolean invert = ResultTreeMapView.this.invert;
			if (isNegativeImpact(evaluationResult, context)) {
				invert = !invert;
			}
			if (invert) {
				value = 1 - evaluationResult.getValue().getUpper();
			}
			return getValueColor(value);
		}

		/** Get the school grade-based color for an {@link EvaluationResult}. */
		protected Color getSchoolGradeColor(EvaluationResult evaluationResult) {
			// for quality aspects
			boolean invert = ResultTreeMapView.this.invert;
			if (isNegativeImpact(evaluationResult, context)) {
				invert = !invert;
			}
			double value = evaluationResult.getSchoolGrade().getUpper();
			if (invert) {
				value = evaluationResult.getInvertedSchoolGrade().getLower();
			}
			return getSchoolGradeColor(value);
		}

		/** Get the color for a {@link MeasurementResult}. */
		protected Color getColor(MeasurementResult measurementResult) {
			MeasureRankingEvaluationResult rankingResult = getRankingResult(measurementResult);
			double value = rankingResult.getValue().getLower();
			if (invert) {
				value = 1 - rankingResult.getValue().getUpper();
			}
			return getValueColor(value);
		}

		/** Get the color for a certain value. value==0 is red, value==1 green */
		private Color getValueColor(double norm) {
			if (norm < 0.5) {
				// generate a color between red and yellow

				norm = norm * 2;

				int factor = (int) Math.floor(norm * 205) + 50;
				factor = Math.max(0, factor);
				factor = Math.min(255, factor);
				return ColorCache.INSTANCE.getColor(255, factor, 50);
			}

			norm = norm - 0.5;
			norm = 0.5 - norm;

			norm = norm * 2;

			// generate a color between green and yellow
			int factor = (int) Math.floor(norm * (205)) + 50;
			factor = Math.max(0, factor);
			factor = Math.min(255, factor);
			return ColorCache.INSTANCE.getColor(factor, 255, 50);
		}

		/** Get the color for a certain value. */
		private Color getSchoolGradeColor(double value) {
			// green= 70, 255, 50
			// yellow= 255,255,50
			// red = 255,50,50

			double norm = value;

			// school grades between 4 and 6 are mapped to 0.0 to 0.2
			// school grades between 1 and 4 are mapped to 0.2 to 1.0

			if (value >= 4) {
				norm = norm - 4;
				norm = norm / 2;
				norm = 1 - norm;
				norm = norm * 0.2;
			} else {
				norm = norm - 1;
				norm = norm / 3;
				norm = 1 - norm;
				norm = norm * 0.8;
				norm = norm + 0.2;
			}

			return getValueColor(norm);
		}
	}

	/**
	 * Check whether the impact is of a child result is negative and thus needs
	 * to be inverted.
	 */
	protected boolean isNegativeImpact(EvaluationResult childResult,
			EvaluationResult parentResult) {
		Factor child = childResult.getResultsFrom().getEvaluates();
		Factor parent = parentResult.getResultsFrom().getEvaluates();
		Impact impact = QmModelUtils.getImpact(child, parent);
		return impact != null && impact.getEffect() == Effect.NEGATIVE;
	}

	/** Get the ranking result for a certain child measurement result. */
	private MeasureRankingEvaluationResult getRankingResult(
			MeasurementResult measurementResult) {
		MeasureRanking ranking = getRanking(measurementResult);
		MeasureRankingEvaluationResult rankingResult = getRankingResult(ranking);
		return rankingResult;
	}

	/** Get the ranking result for a certain measure ranking. */
	private MeasureRankingEvaluationResult getRankingResult(
			MeasureRanking ranking) {
		if (context instanceof MultiMeasureEvaluationResult) {
			MultiMeasureEvaluationResult evaluationResult = (MultiMeasureEvaluationResult) context;
			for (MeasureRankingEvaluationResult result : evaluationResult
					.getEvaluationResults()) {
				if (result.getResultsFrom() == ranking) {
					return result;
				}
			}
		}
		return null;
	}

	/** Get the label for an interval. */
	private String getLabel(DoubleInterval interval) {
		NumberFormat format = NumberFormat.getNumberInstance();
		double lower = interval.getLower();
		double upper = interval.getUpper();
		if (lower == upper) {
			return "" + lower;
		}
		return "[" + format.format(lower) + ";" + format.format(upper) + "]";

	}

	/** The {@link ITreeMapLabelProvider} for the tree map. */
	private class TreeMapLabelProvider extends TreeMapLabelProviderBase<Result> {

		/** {@inheritDoc} */
		@Override
		public String getLabel(Result result) {
			if (result instanceof EvaluationResult) {
				EvaluationResult evaluationResult = (EvaluationResult) result;
				return evaluationResult.getResultsFrom().getEvaluates()
						.getQualifiedName();
			} else if (result instanceof MeasurementResult) {
				MeasurementResult measurementResult = (MeasurementResult) result;
				return measurementResult.getResultsFrom().getDetermines()
						.getQualifiedName();
			}
			return "";
		}

		/** {@inheritDoc} */
		@Override
		public double getArea(Result element) {
			if (element instanceof EvaluationResult) {
				EvaluationResult evaluationResult = (EvaluationResult) element;
				FactorRanking ranking = getRanking(evaluationResult);
				if (ranking != null) {
					return 100000.0 * ranking.getWeight();
				}
			} else if (element instanceof MeasurementResult) {
				MeasurementResult measurementResult = (MeasurementResult) element;
				MeasureRanking ranking = ResultTreeMapView.this
						.getRanking(measurementResult);
				if (ranking != null) {
					return 100000.0 * ranking.getWeight();
				}
			}
			return 0.0;
		}
	}

	/** Get the ranking for an evaluation result. */
	protected FactorRanking getRanking(EvaluationResult element) {
		Evaluation evaluation = context.getResultsFrom();
		if (evaluation instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation aggregation = (WeightedSumFactorAggregation) evaluation;
			for (FactorRanking ranking : aggregation.getRankings()) {
				if (ranking.getFactor() == element.getResultsFrom()
						.getEvaluates()) {
					return ranking;
				}
			}
		}
		return null;
	}

	/** Get the ranking for a measurement result. */
	protected MeasureRanking getRanking(MeasurementResult element) {
		Evaluation evaluation = context.getResultsFrom();
		if (evaluation instanceof WeightedSumMultiMeasureEvaluation) {
			WeightedSumMultiMeasureEvaluation aggregation = (WeightedSumMultiMeasureEvaluation) evaluation;
			for (MeasureRanking ranking : aggregation.getRankings()) {
				if (ranking.getMeasure() == element.getResultsFrom()
						.getDetermines()) {
					return ranking;
				}
			}
		}
		return null;
	}

	/** Get the label for an {@link EvaluationResult}. */
	protected String getLabel(EvaluationResult evaluationResult) {
		Evaluation evaluation = evaluationResult.getResultsFrom();
		switch (coloring) {
		case Grade:
			return getSchoolGradeLabel(evaluationResult);
		case Value:
			return getValueLabel(evaluationResult);
		default:
			boolean qualityAspect = evaluation.getEvaluates().isQualityAspect();
			return qualityAspect ? getSchoolGradeLabel(evaluationResult)
					: getValueLabel(evaluationResult);
		}
	}

	/** Get the school grade-based label of an {@link EvaluationResult}. */
	protected String getSchoolGradeLabel(EvaluationResult evaluationResult) {
		Evaluation evaluation = evaluationResult.getResultsFrom();
		return evaluation.getQualifiedName() + " = "
				+ getLabel(evaluationResult.getSchoolGrade()) + " (Grade)";
	}

	/** Get the value-based label of an {@link EvaluationResult}. */
	protected String getValueLabel(EvaluationResult evaluationResult) {
		Evaluation evaluation = evaluationResult.getResultsFrom();
		return evaluation.getQualifiedName() + " = "
				+ getLabel(evaluationResult.getValue()) + " (Value)";
	}

	/** The {@link ITreeMapListener} for the tree map. */
	private class TreeMapListener implements ITreeMapListener<Result> {

		/** {@inheritDoc} */
		@Override
		public void mouseExitElement() {
			childLabel.setText("");
		}

		/** {@inheritDoc} */
		@Override
		public void mouseEnterElement(Result result) {
			if (result instanceof EvaluationResult) {
				EvaluationResult evaluationResult = (EvaluationResult) result;
				childLabel.setText(getLabel(evaluationResult));
			} else if (result instanceof MeasurementResult) {
				MeasurementResult measurementResult = (MeasurementResult) result;
				MeasureRankingEvaluationResult rankingResult = getRankingResult(measurementResult);
				childLabel.setText(measurementResult.getResultsFrom()
						.getQualifiedName()
						+ " = "
						+ getLabel(rankingResult.getValue()));
			}
		}

		/** {@inheritDoc} */
		@Override
		public void doubleClickedElement(Result result, MouseEvent e) {
			if (result instanceof EvaluationResult) {
				EvaluationResult evaluationResult = (EvaluationResult) result;
				navigateForward(evaluationResult);
			}
		}

		/** {@inheritDoc} */
		@Override
		public void clickedElement(Result result, MouseEvent e) {
			if (e.button == 3) {
				navigateBackward();
			}
		}
	}
}
