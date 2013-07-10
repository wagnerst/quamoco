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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.properties.eval.ParseQIESLException;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;
import de.quamoco.qm.properties.eval.model.InfluencingOrRefiningElement;
import de.quamoco.qm.properties.eval.provider.InfluencingOrRefiningElementComparator;
import de.quamoco.qm.properties.eval.provider.InfluencingOrRefiningElementLabelProvider;
import de.quamoco.qm.properties.eval.provider.edit.ContributionPointsEditingSupport;
import de.quamoco.qm.properties.eval.provider.edit.IValueUpdatedListener;
import de.quamoco.qm.properties.eval.provider.edit.RankingEditingSupport;
import de.quamoco.qm.properties.eval.util.Util;
import de.quamoco.qm.util.QmModelUtils;

public abstract class AbstractAggregationSection extends
		AbstractGenerateEvaluationSection {

	/**
	 * Never use setSelection on this {@link Spinner} to update the value! Use
	 * the utility method {@link #setMaxPointsFieldValue(int)}, since the
	 * listener must be removed first!
	 */
	protected Spinner maxPoints;
	protected MaxPointsModifiedListener maxPointsModifyListener;
	protected TableViewer tableViewer;
	protected Object lastSelectedTableElement;

	protected QualityModel currentQM;

	protected void setCurrentQualityModel(QualityModel qm) {
		currentQM = qm;
	}

	protected List<InfluencingOrRefiningElementLabelProvider> labelProviders = new LinkedList<InfluencingOrRefiningElementLabelProvider>();

	@Override
	protected void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		// Maximum Points
		Composite maxPointsComposite = widgetFactory.createComposite(parent);
		GridLayout maxPointsLayout = new GridLayout(3, false);
		maxPointsLayout.marginWidth = 0;
		maxPointsComposite.setLayout(maxPointsLayout);
		maxPointsComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				false));
		widgetFactory.createLabel(maxPointsComposite, "Maximum points:");
		maxPoints = new Spinner(maxPointsComposite, SWT.BORDER);
		maxPoints.setMinimum(0);
		maxPoints.setMaximum(Integer.MAX_VALUE);
		maxPointsModifyListener = new MaxPointsModifiedListener();
		maxPoints.addModifyListener(maxPointsModifyListener);

		{// button for setting all elements to 1
			String elementPluralName = getElementName();
			Button setElementsEqualButton = widgetFactory.createButton(
					maxPointsComposite, "All " + elementPluralName
							+ " are equally important", SWT.PUSH);
			setElementsEqualButton.setLayoutData(new GridData(SWT.END,
					SWT.FILL, true, false));
			setElementsEqualButton.setToolTipText("Sets the ranking of all "
					+ elementPluralName + " to 1");
			setElementsEqualButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					setAllElementRankingsTo(1);
				}
			});
		}

		{// button for setting all elements to the parent's value

			// if (evaluation instanceof WeightedSumFactorAggregation) {
			// copy button is only relevant for WeithedSumFactorAggregations
			String elementPluralName = getElementName();
			Button setElementsEqualButton = widgetFactory.createButton(
					maxPointsComposite, "copy rankings of parent", SWT.PUSH);
			setElementsEqualButton.setLayoutData(new GridData(SWT.END,
					SWT.FILL, true, false));
			setElementsEqualButton.setToolTipText("Sets the ranking of all "
					+ elementPluralName + " to the parents' value");
			setElementsEqualButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					setAllElementRankingsToParentValue();
				}
			});
			// }
		}

		// Table
		tableViewer = new TableViewer(parent, SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);
		IStructuredContentProvider contentProvider = getContentProvider();
		tableViewer.setContentProvider(contentProvider);
		createColumns(contentProvider);
		tableViewer.setComparator(new InfluencingOrRefiningElementComparator());
		final Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		ColumnViewerToolTipSupport.enableFor(tableViewer);
		tableViewer
				.addPostSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) event
								.getSelection();
						if (selection != null) {
							lastSelectedTableElement = selection.isEmpty() ? null
									: selection.getFirstElement();
						}
					}
				});

	}

	protected abstract IStructuredContentProvider getContentProvider();

	protected abstract String getElementName();

	protected abstract String getElementPluralName();

	@Override
	protected final void setInput(Evaluation evaluation) {
		setMaxPointsFieldValue(evaluation.getMaximumPoints());

		setInputSubclass(evaluation);

		/*
		 * Try to restore table selection, if not possible select the first
		 * element.
		 */
		tableViewer.setSelection(lastSelectedTableElement == null ? null
				: new StructuredSelection(lastSelectedTableElement), true);
		if (((IStructuredSelection) tableViewer.getSelection()).isEmpty()) {
			Object firstElement = tableViewer.getElementAt(0);
			if (firstElement != null) {
				tableViewer.setSelection(new StructuredSelection(firstElement),
						true);
			}
		}
	}

	/**
	 * Set input of the table viewer in this method
	 * 
	 * @param evaluation
	 */
	protected abstract void setInputSubclass(Evaluation evaluation);

	protected void setMaxPointsFieldValue(int value) {
		maxPoints.removeModifyListener(maxPointsModifyListener);
		maxPoints.setSelection(value);
		maxPoints.addModifyListener(maxPointsModifyListener);
	}

	/**
	 * Creates the columns used in the {@link TableViewer}
	 * 
	 * @param parent
	 * @param viewer
	 */
	private void createColumns(IStructuredContentProvider contentProvider) {
		TableViewerColumn ranking = createTableViewerColumn("Ranking",
				"Ranking", 60,
				InfluencingOrRefiningElementLabelProvider.COLUMN_RANKING,
				contentProvider);
		RankingEditingSupport rankingEditingSupport = new RankingEditingSupport(
				tableViewer);
		rankingEditingSupport
				.addValueUpdatedListener(new RankingUpdatedListener());
		ranking.setEditingSupport(rankingEditingSupport);
		TableViewerColumn contPoints = createTableViewerColumn(
				"CP",
				"Contribution Points",
				50,
				InfluencingOrRefiningElementLabelProvider.COLUMN_CONTRIBUTION_POINTS,
				contentProvider);
		ContributionPointsEditingSupport contributionPointsEditingSupport = new ContributionPointsEditingSupport(
				tableViewer);
		contributionPointsEditingSupport
				.addValueUpdatedListener(new ContributionPointsUpdatedListener());
		contPoints.setEditingSupport(contributionPointsEditingSupport);
		createTableViewerColumn("Name", "Name", 150,
				InfluencingOrRefiningElementLabelProvider.COLUMN_NAME,
				contentProvider);
		createTableViewerColumn("Type", "Type", 80,
				InfluencingOrRefiningElementLabelProvider.COLUMN_TYPE,
				contentProvider);
		createTableViewerColumn("Module", "Module", 80,
				InfluencingOrRefiningElementLabelProvider.COLUMN_MODULE,
				contentProvider);
	}

	/**
	 * Creates a single table column
	 * 
	 * @param title
	 *            the title of the column
	 * @param bound
	 *            the width
	 * @param colIndex
	 *            the column number
	 * @return the created column
	 */
	private TableViewerColumn createTableViewerColumn(String title,
			String toolTip, int bound, final int colIndex,
			final IStructuredContentProvider contentProvider) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setToolTipText(toolTip);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(false);

		InfluencingOrRefiningElementLabelProvider labelProvider = new InfluencingOrRefiningElementLabelProvider(
				colIndex);
		labelProviders.add(labelProvider);
		viewerColumn.setLabelProvider(labelProvider);
		return viewerColumn;
	}

	/**
	 * Calculated the new contribution points of all elements based on their
	 * ranking.
	 */
	protected void calculateContributionPointsRankingBasedAndUpdate() {
		EditingDomain editingDomain = AdapterFactoryEditingDomain
				.getEditingDomainFor(evaluation);

		Vector<InfluencingOrRefiningElement<?>> elements = Util
				.getTableData(tableViewer);
		int maxPointsValue = Integer.parseInt(maxPoints.getText());

		/*
		 * Setting all negative rankings to zero and check if there is at least
		 * one ranking > 0.
		 */
		boolean atLeastOneRankingGreaterZero = false;
		for (InfluencingOrRefiningElement<?> element : elements) {
			if (element.getRanking() <= 0) {
				element.setRanking(0);
			} else {
				atLeastOneRankingGreaterZero = true;
			}
		}
		if (!atLeastOneRankingGreaterZero) {
			// String elementName = getElementPluralName();
			// Util.showErrorDialog("Error", "There is no " + elementName +
			// " with a ranking > 0! Contribution points cannot be calculated!");
			return;
		}

		// Set new max points value to evaluation
		SetCommand setEvaluationMaxPoints = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.EVALUATION__MAXIMUM_POINTS,
				maxPointsValue);

		/*
		 * Generate QIESL -> method changes the passed vector
		 */
		EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
				.createEvaluationGenerator(evaluation);
		CompoundCommand command = evaluationGenerator
				.generateRankingBasedAggregation(editingDomain, evaluation,
						maxPointsValue, elements);
		command.append(setEvaluationMaxPoints);

		// Since vector is changed, update table
		for (InfluencingOrRefiningElement<?> influencingOrRefiningElement : elements) {
			tableViewer.refresh(influencingOrRefiningElement);
		}

		// Execute command
		editingDomain.getCommandStack().execute(command);

		// Update status code
		updateStatusText(evaluation);
	}

	/**
	 * Calculates the maximum points of the evaluation based on the (rounded)
	 * contribution points.
	 */
	protected void calculateMaxPointsAndUpdate() {
		EditingDomain editingDomain = AdapterFactoryEditingDomain
				.getEditingDomainFor(evaluation);

		Vector<InfluencingOrRefiningElement<?>> elements = Util
				.getTableData(tableViewer);

		/*
		 * Set all rankings to -1
		 */
		for (InfluencingOrRefiningElement<?> element : elements) {
			element.setRanking(-1);
			tableViewer.refresh(element);
		}

		/*
		 * Check if all contribution points are int, if not abort
		 */
		int maxPointsValue = 0;
		for (InfluencingOrRefiningElement<?> element : elements) {
			double cp = element.getContPoints();
			if (Math.floor(cp) == cp) {
				maxPointsValue += cp;
			} else {
				long roundedCp = Math.round(cp);
				element.setContPoints(roundedCp);
				maxPointsValue += roundedCp;
				tableViewer.refresh(element);
			}
		}

		// Set new max points value to evaluation
		SetCommand setEvaluationMaxPoints = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.EVALUATION__MAXIMUM_POINTS,
				maxPointsValue);
		editingDomain.getCommandStack().execute(setEvaluationMaxPoints);

		// Update maxPoints field, necessary since maxPointsValue is a
		// calculated one and not
		// necessarily the one in the field.
		setMaxPointsFieldValue(maxPointsValue);

		EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
				.createEvaluationGenerator(evaluation);
		Command command = evaluationGenerator.generatePointBasedAggregation(
				editingDomain, evaluation, maxPointsValue, elements);
		editingDomain.getCommandStack().execute(command);

		// Update status code
		updateStatusText(evaluation);
	}

	/**
	 * Calculated the new contribution points of all elements based on their
	 * weight (ranking is -1).
	 */
	protected void calculateContributionPointsWeightBasedAndUpdate() {
		int newMaxPointsValue;
		try {
			newMaxPointsValue = Integer.parseInt(maxPoints.getText());
		} catch (Exception e) {
			return; // do nothing if the new value is not an integer
		}
		EditingDomain editingDomain = AdapterFactoryEditingDomain
				.getEditingDomainFor(evaluation);
		Vector<InfluencingOrRefiningElement<?>> elements = Util
				.getTableData(tableViewer);
		double oldMaxPointsValue = evaluation.getMaximumPoints(); // double for
		// calculation
		// purposes

		double maxPointsFactor = newMaxPointsValue / oldMaxPointsValue;
		/*
		 * Update all factors, multiply contribution points with factor
		 */
		for (InfluencingOrRefiningElement<?> element : elements) {
			element.setContPoints(element.getContPoints() * maxPointsFactor);
			tableViewer.refresh(element);
		}

		// Set new max points value to evaluation
		SetCommand setEvaluationMaxPoints = new SetCommand(editingDomain,
				evaluation, QmPackage.Literals.EVALUATION__MAXIMUM_POINTS,
				newMaxPointsValue);
		editingDomain.getCommandStack().execute(setEvaluationMaxPoints);

		EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
				.createEvaluationGenerator(evaluation);
		Command command = evaluationGenerator.generatePointBasedAggregation(
				editingDomain, evaluation, newMaxPointsValue, elements);
		editingDomain.getCommandStack().execute(command);

		// Update status code
		updateStatusText(evaluation);
	}

	protected void setAllElementRankingsToParentValue() {

		if (!(evaluation instanceof WeightedSumFactorAggregation)) {
			showMessage("This function is only available for WeighedSumFactorAggregations");
			return;
		}

		Vector<InfluencingOrRefiningElement<?>> elements = Util
				.getTableData(tableViewer);

		CharacterizingElement charEle = evaluation.getEvaluates();// elements.get(0).getElement();

		List<Evaluation> evals = new ArrayList<Evaluation>();

		evals.addAll(QmModelUtils.getInverse(
				QmPackage.eINSTANCE.getEvaluation_Evaluates(), charEle,
				Evaluation.class));
		evals.remove(evaluation);

		// String modulename = elements.get(0).getModule();

		Evaluation selected = showMessage(evals);

		if (!((selected instanceof WeightedSumFactorAggregation) || (selected instanceof QIESLEvaluation))) {
			showMessage("Only WeightedSumFactorAggregations and genereated QIESLs can be copied.\n"
					+ "But this is a "
					+ (selected == null ? "null" : selected.getClass()
							.getName()));
			return;
		}

		boolean isWeightBased = false;
		int maxPointsValue = selected.getMaximumPoints();
		selected.setMaximumPoints(maxPointsValue);
		maxPoints.setSelection(maxPointsValue);

		for (InfluencingOrRefiningElement<?> element : elements) {
			int rankingOfParent = getRankingOfParent(element, selected);
			if (rankingOfParent == -1) {
				isWeightBased = true;
				element.setContPoints(getWeightOfParent(element, selected)
						* maxPointsValue);
			} else {
				element.setRanking(rankingOfParent);
			}
		}

		if (isWeightBased) {
			calculateContributionPointsWeightBasedAndUpdate();
		} else {
			calculateContributionPointsRankingBasedAndUpdate();
		}
	}

	private int getRankingOfParent(InfluencingOrRefiningElement<?> element,
			Evaluation selected) {

		if (selected instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation sumaggr = (WeightedSumFactorAggregation) selected;

			for (FactorRanking ranking : sumaggr.getRankings()) {
				if (ranking.getRankedElement().equals(element.getElement())) {
					return ranking.getRank();
				}
			}
			return 0;
		} else if (selected instanceof QIESLEvaluation) {
			EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
					.createEvaluationGenerator(selected);

			try {
				Vector<InfluencingOrRefiningElement<Factor>> factors = evaluationGenerator
						.getCurrentlyUsedFactors(selected);
				for (InfluencingOrRefiningElement<Factor> el : factors) {
					if (el.getElement().equals(element.getElement())) {
						return el.getRanking();
					}
				}

			} catch (ParseQIESLException e) {
				showMessage("QIESL-Error: " + e.getMessage());
			}

			return 0;
		} else {
			throw new RuntimeException("This should never happen.");
		}

	}

	private double getWeightOfParent(InfluencingOrRefiningElement<?> element,
			Evaluation selected) {

		if (selected instanceof WeightedSumFactorAggregation) {
			WeightedSumFactorAggregation sumaggr = (WeightedSumFactorAggregation) selected;

			for (FactorRanking ranking : sumaggr.getRankings()) {
				if (ranking.getRankedElement().equals(element.getElement())) {
					return ranking.getWeight();
				}
			}
			return 0;
		} else if (selected instanceof QIESLEvaluation) {
			EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
					.createEvaluationGenerator(selected);

			try {
				Vector<InfluencingOrRefiningElement<Factor>> factors = evaluationGenerator
						.getCurrentlyUsedFactors(selected);
				for (InfluencingOrRefiningElement<Factor> el : factors) {
					if (el.getElement().equals(element.getElement())) {
						return el.getContPoints();
					}
				}

			} catch (ParseQIESLException e) {
				showMessage("QIESL-Error: " + e.getMessage());
			}

			return 0;
		} else {
			throw new RuntimeException("This should never happen.");
		}

	}

	/** Show a message in a dialog. */
	private void showMessage(String message) {
		MessageDialog.openInformation(Display.getDefault().getActiveShell(),
				"Message", message);
	}

	private Evaluation showMessage(List<Evaluation> evals) {
		String[] evalsstring = new String[evals.size()];

		for (int i = 0; i < evalsstring.length; i++) {
			evalsstring[i] = evals.get(i).getQualifiedName();
		}

		MessageDialog dialog = new MessageDialog(Display.getDefault()
				.getActiveShell(), "Select the evaluation to copy from", null,
				"Which evaluation should be copied?", MessageDialog.QUESTION,
				evalsstring, 2);
		int answer = dialog.open();

		return evals.get(answer);
	}

	protected void setAllElementRankingsTo(int ranking) {
		Vector<InfluencingOrRefiningElement<?>> elements = Util
				.getTableData(tableViewer);
		for (InfluencingOrRefiningElement<?> element : elements) {
			element.setRanking(ranking);
		}
		calculateContributionPointsRankingBasedAndUpdate();
	}

	@Override
	public void refresh() {

		for (InfluencingOrRefiningElementLabelProvider lp : labelProviders) {
			lp.setCurrentQualityModel(currentQM);
		}

		/*
		 * Check measures
		 */
		Vector<InfluencingOrRefiningElement<?>> tableData = Util
				.getTableData(tableViewer);
		List<Ranking> evaluationData = new LinkedList<Ranking>(getRankings());

		if (tableData.size() == evaluationData.size()) {
			/*
			 * Check for each ranking if an according model element exists
			 */
			for (Iterator<Ranking> outerIter = evaluationData.iterator(); outerIter
					.hasNext();) {
				Ranking ranking = outerIter.next();
				for (Iterator<InfluencingOrRefiningElement<?>> innerIter = tableData
						.iterator(); innerIter.hasNext();) {
					InfluencingOrRefiningElement<?> element = innerIter.next();
					if (ranking.getRankedElement() == element.getElement()) { // match
						// compare the data
						if (!element.represents(ranking)) {
							// update required
							setInput(evaluation);
							return;
						}
						// jump to next interation
						innerIter.remove();
						outerIter.remove();
						break;
					}
				}
			}
			// if the model is in sync both lists should be empty
			if (!tableData.isEmpty() || !evaluationData.isEmpty()) {
				setInput(evaluation);
			}
		} else { // model data does not represent the actual data, refresh
			setInput(evaluation);
		}
	}

	protected abstract Collection<? extends Ranking> getRankings();

	protected class RankingUpdatedListener implements IValueUpdatedListener {
		@Override
		public void valueUpdated(InfluencingOrRefiningElement<?> element) {
			calculateContributionPointsRankingBasedAndUpdate();
		}
	}

	protected class ContributionPointsUpdatedListener implements
			IValueUpdatedListener {
		@Override
		public void valueUpdated(InfluencingOrRefiningElement<?> element) {
			calculateMaxPointsAndUpdate();
		}
	}

	protected class MaxPointsModifiedListener implements ModifyListener {
		@Override
		public void modifyText(ModifyEvent e) {
			Vector<InfluencingOrRefiningElement<?>> elements = Util
					.getTableData(tableViewer);

			boolean allElementsGreaterEqualsZero = true;
			boolean allElementsEqualsMinusOne = true;

			for (InfluencingOrRefiningElement<?> element : elements) {
				int ranking = element.getRanking();
				allElementsGreaterEqualsZero = allElementsGreaterEqualsZero
						&& (ranking >= 0);
				allElementsEqualsMinusOne = allElementsEqualsMinusOne
						&& (ranking == -1);
			}

			if (allElementsGreaterEqualsZero) {
				calculateContributionPointsRankingBasedAndUpdate();
			} else if (allElementsEqualsMinusOne) {
				calculateContributionPointsWeightBasedAndUpdate();
			} else {
				Util.showErrorDialog("Error",
						"Rankings are inconsistent, please check!");
			}
		}
	}

}
