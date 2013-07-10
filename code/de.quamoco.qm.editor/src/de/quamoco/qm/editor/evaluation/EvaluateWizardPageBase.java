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

package de.quamoco.qm.editor.evaluation;

import org.conqat.ide.commons.ui.wizard.FinishableWizardPage;
import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;

/**
 * Base class for wizard pages to set the parameters for the ConQAT evaluation.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public abstract class EvaluateWizardPageBase extends FinishableWizardPage {

	/** Text field to set the project name. */
	protected Text projectText;

	/** Text field to set the source directory. */
	protected Text sourceText;

	/** Text field to set the output directory. */
	protected Text resultText;

	/** Text field to set the manual measurement file. */
	protected Text manualText;

	/** The quality model. */
	protected final QualityModel model;

	/** The runnable to perform the evaluation. */
	protected ConQATRunnableBase runnable;

	/** The quality model editor. */
	protected final CustomQmEditor editor;

	/** Constructor. */
	public EvaluateWizardPageBase(QualityModel model, CustomQmEditor editor) {
		super("evaluate");
		this.model = model;
		this.editor = editor;
	}

	/** {@inheritDoc} */
	@Override
	public void createControl(Composite parent) {
		setTitle("ConQAT Evaluation");
		setMessage("Choose system parameters");

		final Composite composite = new Composite(parent, SWT.None);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);

		createParameterWidgets(composite);

		setControl(composite);
	}

	/** Create the widgets to set the parameters. */
	protected void createParameterWidgets(final Composite composite) {
		projectText = createTextSetter(composite, "System Name:",
				"project.name");

		sourceText = createDirChooser(composite, "Source Directory:",
				"source.dir");
		resultText = createDirChooser(composite, "Result Directory:",
				"output.dir");
		manualText = createFileChooser(composite, "Manual Measure File:",
				"manual-measure.file", "*.xls");
	}

	/** Create widgets to be able to set a text. */
	protected Text createTextSetter(final Composite composite, String text,
			String dialogSettingsKey) {
		Label projectLabel = new Label(composite, SWT.None);
		projectLabel.setText(text);
		Text textField = createText(composite, dialogSettingsKey);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		textField.setLayoutData(data);
		return textField;
	}

	/** Create widgets to be able to choose a directory. */
	protected Text createDirChooser(final Composite composite, String text,
			String dialogSettingsKey) {
		Label label = new Label(composite, SWT.None);
		label.setText(text);
		final Text pathText = createText(composite, dialogSettingsKey);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button button = new Button(composite, SWT.PUSH);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(Display
						.getDefault().getActiveShell());
				if (pathText.getText() != null) {
					dialog.setFilterPath(pathText.getText());
				}
				String file = dialog.open();
				if (file != null) {
					pathText.setText(file);
				}
			}
		});
		return pathText;
	}

	/** Create a text field. */
	private Text createText(final Composite composite, String dialogSettingsKey) {
		Text text = new Text(composite, SWT.BORDER);
		String initialValue = getDialogSettings().get(dialogSettingsKey);
		if (!StringUtils.isEmpty(initialValue)) {
			text.setText(initialValue);
		}
		return text;
	}

	/** Create widgets to be able to choose a file. */
	protected Text createFileChooser(final Composite composite, String text,
			String dialogSettingsKey, final String... extensions) {
		Label label = new Label(composite, SWT.None);
		label.setText(text);
		final Text pathText = createText(composite, dialogSettingsKey);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		Button button = new Button(composite, SWT.PUSH);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(Display.getDefault()
						.getActiveShell(), SWT.OPEN);
				if (pathText.getText() != null) {
					dialog.setFileName(pathText.getText());
				}
				dialog.setFilterExtensions(extensions);
				String file = dialog.open();
				if (file != null) {
					pathText.setText(file);
				}
			}
		});
		return pathText;
	}

	/** {@inheritDoc} */
	@Override
	public boolean performFinish() {
		getDialogSettings().put("project.name", projectText.getText());
		getDialogSettings().put("source.dir", sourceText.getText());
		getDialogSettings().put("output.dir", resultText.getText());
		getDialogSettings().put("manual-measure.file", manualText.getText());
		return true;
	}

	/** Get the runnable. */
	public ConQATRunnableBase getRunnable() {
		return runnable;
	}

	/** Obtain the dialog settings. */
	@Override
	protected IDialogSettings getDialogSettings() {
		return QmEditorPlugin.getPlugin().getDialogSettings(
				getClass().getSimpleName());
	}

}
