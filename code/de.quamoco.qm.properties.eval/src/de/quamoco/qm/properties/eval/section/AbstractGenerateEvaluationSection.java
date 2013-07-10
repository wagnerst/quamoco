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

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.FormBasedEvaluation;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.properties.eval.Activator;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator;
import de.quamoco.qm.properties.eval.generator.EvaluationGeneratorFactory;

/**
 * Abstract super class for sections generating {@link FormBasedEvaluation}s..
 * 
 * @author Franz Becker
 */
public abstract class AbstractGenerateEvaluationSection extends
		AbstractPropertySection {

	/**
	 * The current {@link Evaluation}, is set within
	 * {@link #setInput(IWorkbenchPart, ISelection)}.
	 */
	protected Evaluation evaluation;

	/** The widget factory, used for creating controls */
	protected TabbedPropertySheetWidgetFactory widgetFactory;

	/** The text field that can be used to display a status */
	protected Text status;

	/*
	 * Color attributes
	 */
	protected Display display = Activator.getDefault().getWorkbench()
			.getDisplay();
	protected Color warningRed = new Color(display, 255, 0, 0);
	protected Color hintYellow = new Color(display, 200, 200, 0);
	protected Color okGreen = new Color(display, 0, 200, 0);

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		// Common settings
		parent.setLayout(new GridLayout(1, false));
		widgetFactory = getWidgetFactory();

		// Separator
		Label separator1 = widgetFactory.createSeparator(parent, SWT.HORIZONTAL
				| SWT.FILL);
		separator1.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

		// Status label
		status = widgetFactory.createText(parent, "", SWT.FLAT);
		GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
		data.exclude = true;
		status.setLayoutData(data);

		// Forward to subclasses
		Composite subClassParent = widgetFactory.createComposite(parent);
		createControls(subClassParent);
	}

	protected abstract void createControls(Composite parent);

	/**
	 * Calls the super method and if an {@link Evaluation} is passed within the
	 * selection call {@link #updateStatusText(Evaluation)} and forwards the
	 * call to {@link #setInput(Evaluation)}.
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		IStructuredSelection structuredSelection = (IStructuredSelection) selection;

		if (structuredSelection.getFirstElement() instanceof Evaluation) {

			evaluation = (Evaluation) structuredSelection.getFirstElement();

			status.setVisible(evaluation instanceof QIESLEvaluation);
			updateStatusText(evaluation);

			// Forward call to subclass
			setInput(evaluation);
		}
	}

	/**
	 * Updates the {@link #status} field, ugly but quickest implementation.
	 * 
	 * @param evaluation
	 */
	protected void updateStatusText(Evaluation evaluation) {
		if (evaluation instanceof QIESLEvaluation) {
			// show status field
			GridData data = new GridData(SWT.FILL, SWT.CENTER, true, false);
			data.exclude = true;
			status.setLayoutData(data);

			QIESLEvaluation qieslEvaluation = (QIESLEvaluation) evaluation;
			EvaluationGenerator evaluationGenerator = EvaluationGeneratorFactory
					.createEvaluationGenerator(qieslEvaluation);
			if (evaluationGenerator.isGenerated(qieslEvaluation)) {
				if (this.getClass() == SingleMeasureEvaluationSection.class) {
					if (evaluationGenerator
							.isGeneratedEvaluationFunction(qieslEvaluation)) {
						status.setText("Generated QIESL Evaluation in use");
						status.setForeground(okGreen);
					} else if (evaluationGenerator
							.isGeneratedAggregation(qieslEvaluation)) {
						status
								.setText("Current QIESL specifies a Factor Aggregation (to maintain use associated tab");
						status.setForeground(hintYellow);
					} else {
						status.setText("Generated QIESL detected but invalid!");
						status.setForeground(warningRed);
					}
				} else if (this.getClass() == FactorAggregationSection.class) {
					if (evaluationGenerator
							.isGeneratedAggregation(qieslEvaluation)) {
						status.setText("Generated QIESL Evaluation in use");
						status.setForeground(okGreen);
					} else if (evaluationGenerator
							.isGeneratedEvaluationFunction(qieslEvaluation)) {
						status
								.setText("Current QIESL specifies an Evaluation Function (to maintain use associated tab)");
						status.setForeground(hintYellow);
					} else {
						status.setText("Generated QIESL detected but invalid!");
						status.setForeground(warningRed);
					}
				}
			} else {
				status.setText("No generated QIESL Evaluation available");
				status.setForeground(warningRed);
			}
		} else {
			// do nothing
			status.setText("");
		}
	}

	protected abstract void setInput(Evaluation evaluation);

}
