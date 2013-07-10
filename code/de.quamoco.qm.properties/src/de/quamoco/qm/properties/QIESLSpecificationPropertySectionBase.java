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

import static edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine.MEASURE_NAME_DELIMITER;

import java.util.ArrayList;
import java.util.List;

import org.conqat.ide.commons.ui.logging.LoggingUtils;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;

import de.quamoco.qm.CharacterizingElement;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Measure;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QualityModelElement;
import de.quamoco.qm.Type;
import de.quamoco.qm.provider.util.QmEditUtils;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.qiesl.QIESLVerificator;
import edu.tum.cs.emf.commons.sections.TextPropertySectionBase;
import edu.tum.cs.emf.commons.validation.Severity;

/**
 * Base class for property sections to edit a QIESL specification.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating YELLOW Hash: 78B876A1F6A8999E8689924A99346D92
 */
public abstract class QIESLSpecificationPropertySectionBase extends
		TextPropertySectionBase {

	/** Table to display the usable measures and factors. */
	private TableViewer measuresAndFactorsTableViewer;

	/** Table to display the usable normalization measures. */
	private TableViewer normalizationMeasuresTableViewer;

	/**
	 * The constructor.
	 * 
	 * @param attribute
	 *            the {@link EAttribute} of the element.
	 */
	public QIESLSpecificationPropertySectionBase(EAttribute attribute) {
		super(attribute, true);
	}

	/** Creates the {@link Text}. */
	@Override
	protected void createValue(Composite composite) {

		Composite subComposite = getWidgetFactory().createComposite(composite);
		GridLayout layout = new GridLayout(1, false);
		layout.marginBottom = layout.marginLeft = layout.marginRight = layout.marginTop = 0;
		layout.marginWidth = layout.marginHeight = 0;
		subComposite.setLayout(layout);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalIndent = ITabbedPropertyConstants.HSPACE;
		data.grabExcessHorizontalSpace = true;
		subComposite.setLayoutData(data);

		super.createValue(subComposite);
		((GridData) text.getLayoutData()).horizontalIndent = 0;
		try {
			new ContentProposalAdapter(text, new TextContentAdapter(),
					new IContentProposalProvider() {

						@Override
						public IContentProposal[] getProposals(String contents,
								int position) {
							List<IContentProposal> proposals = new ArrayList<IContentProposal>();
							for (CharacterizingElement element : getNormalizationMeasures()) {
								proposals.add(new SpecificationContentProposal(
										element));
							}
							for (CharacterizingElement element : getMeasuresAndFactors()) {
								proposals.add(new SpecificationContentProposal(
										element));
							}
							return proposals.toArray(new IContentProposal[0]);
						}
					}, KeyStroke.getInstance("M1+Space"), null);
		} catch (ParseException e) {
			LoggingUtils.error(Activator.getDefault(),
					"Key combination could not be parsed", e);
		}

		createButtons(subComposite);
		createTables(subComposite);
	}

	/** Create the buttons to generate QIESL formulas. */
	@Override
	protected void createButtons(Composite subComposite) {
		Composite buttonComposite = getWidgetFactory().createComposite(
				subComposite);
		GridLayout layout = new GridLayout(2, true);
		layout.marginBottom = layout.marginLeft = layout.marginRight = layout.marginTop = 0;
		buttonComposite.setLayout(layout);
		buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createButton(buttonComposite, "Generate standard QIESL with Ranges",
				new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						generateStandardQIESLWithRanges();
					}
				});

		createButton(buttonComposite, "Generate standard QIESL without Ranges",
				new SelectionAdapter() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						generateStandardQIESLWithoutRanges();
					}
				});
	}

	/** Create a button to generate QIESL formulas. */
	private Button createButton(Composite composite, String text,
			SelectionListener listener) {
		Button button = new Button(composite, SWT.NONE);
		button.setText(text);
		button.addSelectionListener(listener);
		return button;
	}

	/** Create the tables of usage measures and factors. */
	private void createTables(Composite composite) {
		// Create the table of usable measures and factors
		Label measuresAndFactorsLabel = new Label(composite, SWT.NONE);
		measuresAndFactorsLabel.setText("Available Measures and Factors:");
		measuresAndFactorsLabel.setBackground(composite.getBackground());
		measuresAndFactorsTableViewer = createTable(composite);

		// Create the table of usable normalization measures
		Label normalizationMeasuresLabel = new Label(composite, SWT.NONE);
		normalizationMeasuresLabel.setText("Available Normalization Measures:");
		normalizationMeasuresLabel.setBackground(composite.getBackground());
		normalizationMeasuresTableViewer = createTable(composite);
	}

	/** Create a table to display usable measures and factors. */
	private TableViewer createTable(Composite composite) {
		TableViewer tableViewer = new TableViewer(composite, SWT.MULTI
				| SWT.BORDER | SWT.FULL_SELECTION);
		Table table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		table.setLayoutData(gridData);

		String[] titles = { "Name", "Type", "Module" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			column.setWidth(200);
		}
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());

		return tableViewer;
	}

	/** Generate a standard QIESL formula, considering ranges. */
	private void generateStandardQIESLWithRanges() {
		EObject element = getElement();
		if (element instanceof Evaluation) {
			QIESLEvaluation evaluation = (QIESLEvaluation) element;
			Factor factor = evaluation.getEvaluates();

			if (!factor.getRefinedByDirect().isEmpty()
					|| !factor.getInfluencedByDirect().isEmpty()) {
				String qiesl = "result=mean(allImpactsAndRefinements)";
				evaluation.setSpecification(qiesl.toString());
				this.text.setText(qiesl);
				verifyQIESL(qiesl);
			} else {
				List<Measure> measures = evaluation.getUsableMeasures();

				// The generation is only applicable, if all measures are of
				// type findings.
				for (Measure m : measures) {
					if (m.getType() != Type.FINDINGS) {
						showMessage("This function does only work if all measures have type Findings.\n"
								+ "But ["
								+ m.getQualifiedName()
								+ "] is of type " + m.getType());
					}
				}

				StringBuffer qiesl = generateQIESLForFactor(evaluation,
						measures);

				// if it succeeded without error, set it
				if (qiesl != null) {
					Command command = SetCommand.create(getEditingDomain(),
							getElement(), getFeature(), qiesl.toString());
					wrapAndExecute(command);
					verifyQIESL(qiesl.toString());
				}
			}
		} else {
			showMessage("This function is only available for factors.");
		}
	}

	/** Generates the QIESL for a factor. */
	private StringBuffer generateQIESLForFactor(QIESLEvaluation evaluation,
			List<Measure> measures) {
		StringBuffer qiesl = new StringBuffer();
		qiesl.append("result=");
		boolean first = true;
		for (Measure measure : measures) {

			if (measure.getType() != Type.FINDINGS) {
				// ignore it, the error message was already given before
				continue;
			}

			qiesl.append("\n\t");
			if (!first) {
				qiesl.append("+");
			}

			Entity e = measure.getCharacterizes();
			String ranges = "";
			int range = getRange(e);
			switch (range) {
			case 1:
				ranges = "classRange";
				break;
			case 2:
				ranges = "methodRange";
				break;
			default:
				ranges = "fileRange";
			}

			String normMeasure = getNormalizationMeasureWithRanges(evaluation);
			if (normMeasure == null) {
				showMessage("No suitable normalization measure could be found.");
				return null;
			}

			qiesl.append("linearDistribution(\n");
			qiesl.append("\t\textent(\n");
			qiesl.append("\t\t\t" + ranges + "(\n");
			qiesl.append("\t\t\t\t" + MEASURE_NAME_DELIMITER
					+ measure.getQualifiedName() + MEASURE_NAME_DELIMITER
					+ "\n");
			qiesl.append("\t\t\t)\n");
			qiesl.append("\t\t)\n");
			qiesl.append("\t\t/" + MEASURE_NAME_DELIMITER + normMeasure
					+ MEASURE_NAME_DELIMITER + "\n");
			qiesl.append("\t\t,0.00\n");
			qiesl.append("\t\t," + (1.0 / measures.size()) + "\n");
			qiesl.append("\t\t,0.25\n");
			qiesl.append("\t\t,0\n");
			qiesl.append("\t)");
			first = false;
		}
		if (first) {
			qiesl.append("\t0");
		}
		return qiesl;
	}

	/** Generate a standard QIESL formula, not considering ranges. */
	private void generateStandardQIESLWithoutRanges() {
		EObject element = getElement();
		if (element instanceof Evaluation) {
			QIESLEvaluation evaluation = (QIESLEvaluation) element;
			Factor factor = evaluation.getEvaluates();

			if (!factor.getRefinedByDirect().isEmpty()
					|| !factor.getInfluencedByDirect().isEmpty()) {
				String qiesl = "result=mean(allImpactsAndRefinements)";
				evaluation.setSpecification(qiesl.toString());
				this.text.setText(qiesl);
				verifyQIESL(qiesl);
			} else {
				List<Measure> measures = evaluation.getUsableMeasures();

				// The generation is only applicable, if all measures are of
				// type findings.
				for (Measure m : measures) {
					if (m.getType() != Type.FINDINGS) {
						showMessage("This function does only work if all measures have type Findings.\n"
								+ "But ["
								+ m.getQualifiedName()
								+ "] is of type " + m.getType());
					}
				}

				StringBuffer qiesl = generateQIESLForFactorWithoutRanges(
						evaluation, measures);

				// if it succeded with no errors, set it
				if (qiesl != null) {
					Command command = SetCommand.create(getEditingDomain(),
							getElement(), getFeature(), qiesl.toString());
					wrapAndExecute(command);
					verifyQIESL(qiesl.toString());
				}
			}
		} else {
			showMessage("This function is only available for factors.");
		}
	}

	/** Generate the QIESL for a factor without ranges. */
	private StringBuffer generateQIESLForFactorWithoutRanges(
			QIESLEvaluation evaluation, List<Measure> measures) {
		StringBuffer qiesl = new StringBuffer();
		qiesl.append("result=");
		boolean first = true;
		for (Measure measure : measures) {

			if (measure.getType() != Type.FINDINGS) {
				// ignore it, the error message was already given before
				continue;
			}

			qiesl.append("\n\t");
			if (!first) {
				qiesl.append("+");
			}

			Entity e = measure.getCharacterizes();
			String normMeasure = getNormalizationMeasureWithoutRanges(
					evaluation, e);
			if (normMeasure == null) {
				showMessage("No suitable normalization measure could be found.");
				return null;
			}

			qiesl.append("linearDistribution(\n");
			qiesl.append("\t\textent(\n");
			qiesl.append("\t\t\t" + MEASURE_NAME_DELIMITER
					+ measure.getQualifiedName() + MEASURE_NAME_DELIMITER
					+ "\n");
			qiesl.append("\t\t)\n");
			qiesl.append("\t\t/" + MEASURE_NAME_DELIMITER + normMeasure
					+ MEASURE_NAME_DELIMITER + "\n");
			qiesl.append("\t\t,0.00\n");
			qiesl.append("\t\t," + (1.0 / measures.size()) + "\n");
			qiesl.append("\t\t,0.25\n");
			qiesl.append("\t\t,0\n");
			qiesl.append("\t)");
			first = false;
		}
		if (first) {
			qiesl.append("\t0");
		}
		return qiesl;
	}

	/** Show a message in a dialog. */
	private void showMessage(String message) {
		MessageDialog.openInformation(Display.getDefault().getActiveShell(),
				"Message", message);
	}

	/** Gets the right normalization measure for the evaluation, with ranges. */
	private String getNormalizationMeasureWithRanges(QIESLEvaluation evaluation) {
		// If there is a normalization measure called LOC, take it
		for (NormalizationMeasure measure : evaluation
				.getNormalizationMeasures()) {
			if (measure.getQualifiedName().contains("LOC")) {
				return measure.getQualifiedName();
			}
		}

		// If there is none called LOC, try it with "#UI Elements"
		for (NormalizationMeasure measure : evaluation
				.getNormalizationMeasures()) {
			if (measure.getQualifiedName().contains("UI Elements")) {
				return measure.getQualifiedName();
			}
		}

		// If there is no suitable normalization measure, return null
		return null;
	}

	/**
	 * Gets the right normalization measure for the factor, without ranges. It
	 * implements some heuristics for choosing the right one.
	 */
	private String getNormalizationMeasureWithoutRanges(
			QIESLEvaluation evaluation, Entity entity) {

		// get the entity name
		String entityName = entity.getName();
		if (entityName.length() > 3) {
			entityName = entityName.substring(0, entityName.length() - 3);
		}
		if (entityName.startsWith("#")) {
			entityName = entityName.substring(1);
		}

		// Heuristic 1:
		// search for a normalization measure that has the same name as the
		// entity of the factor
		for (NormalizationMeasure measure : evaluation
				.getNormalizationMeasures()) {
			String measureName = (measure).getName();
			if (measureName.startsWith("#")) {
				measureName = measureName.substring(1);
			}
			if (measureName.length() > 3) {
				measureName = measureName
						.substring(0, measureName.length() - 3);
			}
			if (measureName.length() > entityName.length()) {
				measureName = measureName.substring(0, entityName.length());
			}
			if (entityName.length() > measureName.length()) {
				entityName = entityName.substring(0, measureName.length());
			}

			if (entityName.equals(measureName)) {
				return measure.getQualifiedName();
			}
		}

		// Heuristic 2: try it with "#UI Elements"
		for (NormalizationMeasure measure : evaluation
				.getNormalizationMeasures()) {
			if (measure.getQualifiedName().contains("UI Elements")) {
				return measure.getQualifiedName();
			}
		}

		// If there is no suitable normalization measure, return null
		return null;
	}

	/** Get the right range for an entity. */
	private int getRange(Entity entity) {

		if (entity == null) {
			return 0;
		}

		int result = 0;
		if (entity.getName().equalsIgnoreCase("Method")) {
			result = 2;
		}
		if (entity.getName().equalsIgnoreCase("Class")) {
			result = 1;
		}

		// Hack to enable it to work, if the not-allowed part-ofs are removed
		if (entity.getName().equalsIgnoreCase("Local variable")) { // method-ranges
			// for local
			// variables
			result = 2;
		}
		if (entity.getName().equalsIgnoreCase("Statement")) { // method-ranges
			// for
			// statements
			result = 2;
		}

		result = Math.max(result, getRange(entity.getPartOfDirect()));
		for (Entity isADirect : entity.getIsADirect()) {
			result = Math.max(result, getRange(isADirect));
		}
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public void refresh() {
		super.refresh();

		IObservableValue modelObservable = EMFEditObservables.observeValue(
				getEditingDomain(), getElement(), getFeature());
		modelObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				verifyQIESL(null);
			}
		});

		ISWTObservableValue viewObservable = SWTObservables.observeText(text,
				SWT.FocusOut);
		viewObservable.addValueChangeListener(new IValueChangeListener() {

			@Override
			public void handleValueChange(ValueChangeEvent event) {
				verifyQIESL(text.getText());
			}
		});
		getContext().bindValue(viewObservable, modelObservable);

		updateMeasuresAndFactorsTable();
		updateNormalizationMeasuresTable();

		layout();

		verifyQIESL(null);
	}

	/** Update the table for usable measures and factors. */
	private void updateMeasuresAndFactorsTable() {
		List<CharacterizingElement> measuresAndFactors = getMeasuresAndFactors();
		measuresAndFactorsTableViewer.setInput(measuresAndFactors);

		for (int j = 1; j < 3; j++) {
			measuresAndFactorsTableViewer.getTable().getColumn(j).pack();
		}
	}

	/** Get the measures and factors that can be used in the QIESL formula. */
	protected abstract List<CharacterizingElement> getMeasuresAndFactors();

	/** Update the table for usable normalization measures. */
	private void updateNormalizationMeasuresTable() {
		List<NormalizationMeasure> measures = getNormalizationMeasures();
		normalizationMeasuresTableViewer.setInput(measures);
		for (int j = 1; j < 3; j++) {
			normalizationMeasuresTableViewer.getTable().getColumn(j).pack();
		}
	}

	/** Get the normalization measures that can be used in the QIESL formula. */
	private List<NormalizationMeasure> getNormalizationMeasures() {
		QualityModelElement element = (QualityModelElement) getElement();
		return QmModelUtils.getNormalizationMeasures(element);
	}

	/**
	 * Verifies the QIELS given as parameter. If null is given as parameter, the
	 * value is loaded from the model.
	 */
	private void verifyQIESL(String formula) {
		String message = QIESLVerificator.verifyQIESL(getElement(), formula);
		if (message == null) {
			hideProblem();
		} else {
			showProblem(Severity.ERROR, message);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setFocus() {
		text.setFocus();
	}

	/**
	 * {@link ITableLabelProvider} for the tables to display measures and
	 * factors.
	 */
	private static class TableLabelProvider implements ITableLabelProvider {

		/** {@inheritDoc} */
		@Override
		public void removeListener(ILabelProviderListener listener) {
			// not required
		}

		/** {@inheritDoc} */
		@Override
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/** {@inheritDoc} */
		@Override
		public void dispose() {
			// not required
		}

		/** {@inheritDoc} */
		@Override
		public void addListener(ILabelProviderListener listener) {
			// not required
		}

		/** {@inheritDoc} */
		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (element instanceof Factor) {
				Factor factor = (Factor) element;
				switch (columnIndex) {
				case 0:
					return factor.getQualifiedName();
				case 1:
					return "RATING";
				case 2:
					return factor.getQualityModel().getName();
				}
			} else if (element instanceof Measure) {
				Measure measure = (Measure) element;
				switch (columnIndex) {
				case 0:
					return measure.getQualifiedName();
				case 1:
					return measure.getType().getLiteral();
				case 2:
					return measure.getQualityModel().getName();
				}
			}
			return "";
		}

		/** {@inheritDoc} */
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				if (element instanceof Factor) {
					return ExtendedImageRegistry.getInstance().getImage(
							QmEditUtils.getFactorImage((Factor) element));
				} else if (element instanceof Measure) {
					return ExtendedImageRegistry.getInstance().getImage(
							QmEditUtils.getMeasureImage((Measure) element));
				}
			}
			return null;
		}
	}
}