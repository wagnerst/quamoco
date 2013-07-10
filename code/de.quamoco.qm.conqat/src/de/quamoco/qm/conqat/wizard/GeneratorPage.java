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

package de.quamoco.qm.conqat.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;

import org.conqat.ide.commons.ui.dialog.MessageUtils;
import org.conqat.ide.commons.ui.swt.SWTUtils;
import org.conqat.ide.commons.ui.wizard.FinishableWizardPage;
import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.conqat.ConQATActivator;
import de.quamoco.qm.conqat.generator.ConQATBlockGenerationException;
import de.quamoco.qm.conqat.generator.ConQATBlockGenerator;
import de.quamoco.qm.conqat.generator.GeneratorUtils;

/**
 * Main wizard page for ConQAT block creation.
 * 
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 4018689A58F5A9EA12237E864AAC845E
 */
public class GeneratorPage extends FinishableWizardPage {

	/** Programming languages supported by the generator. */
	private static final String[] LANGUAGES = { "Java", "CPP", "CS" };

	/** Dialog settings key. */
	private static final String BUNDLE_LOCATION_KEY = "bunde location";

	/** Dialog settings key. */
	private static final String BLOCK_NAME_KEY = "block name";

	/** Dialog settings key. */
	private static final String LANGUAGE_INDEX_KEY = "language";

	/** Text field for filename. */
	private Text bundleLocationText;

	/** Text field for block name. */
	private Text blockNameText;

	/** Combo box for programming language. */
	private Combo languageCombo;

	/**
	 * The models the block should generated for.
	 */
	private final List<QualityModel> models;

	/** Legal pattern for block names. */
	private final Pattern blockNamePattern = Pattern
			.compile("[\\-_a-zA-Z0-9][\\-_a-zA-Z0-9.]*");

	/** Listener that calls {@link #updateState()}. */
	private final ModifyListener modifyListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			updateState();
		}
	};

	/** Create page. */
	public GeneratorPage(List<QualityModel> models) {
		super("Generator Page");
		this.models = models;
	}

	/** {@inheritDoc} */
	@Override
	public void createControl(Composite parent) {
		setTitle("ConQAT Block Generator");
		setMessage("Choose destination block");

		Composite root = new Composite(parent, SWT.NONE);
		setControl(root);
		root.setLayout(new GridLayout(2, false));

		new Label(root, SWT.NONE).setText("Bundle location:");
		bundleLocationText = new Text(root, SWT.SINGLE | SWT.BORDER);
		GridDataFactory.defaultsFor(bundleLocationText).hint(400, SWT.DEFAULT)
				.applyTo(bundleLocationText);

		bundleLocationText.addModifyListener(modifyListener);

		new Label(root, SWT.NONE).setText("Block name (local):");
		blockNameText = new Text(root, SWT.SINGLE | SWT.BORDER);
		SWTUtils.layout(blockNameText);

		blockNameText.addModifyListener(modifyListener);

		new Label(root, SWT.NONE).setText("Implementation Language:");
		languageCombo = new Combo(root, SWT.READ_ONLY);
		languageCombo.setItems(LANGUAGES);
		languageCombo.addModifyListener(modifyListener);

		String bundleLocation = getDialogSettings().get(BUNDLE_LOCATION_KEY);
		if (!StringUtils.isEmpty(bundleLocation)) {
			bundleLocationText.setText(bundleLocation);
		}

		String blockName = getDialogSettings().get(BLOCK_NAME_KEY);
		if (!StringUtils.isEmpty(blockName)) {
			blockNameText.setText(blockName);
		}

		try {
			int langIndex = getDialogSettings().getInt(LANGUAGE_INDEX_KEY);
			languageCombo.select(langIndex);
		} catch (NumberFormatException ex) {
			// ignore
		}

		updateState();
	}

	/** Updates message and page completion state. */
	public void updateState() {
		setErrorMessage(null);

		checkBundleLocation();
		checkBlockName();
		checkLanguage();

		setPageComplete(getErrorMessage() == null);

		if (getErrorMessage() == null) {
			File bundleLocation = new File(bundleLocationText.getText());
			setMessage("Block name: "
					+ GeneratorUtils.getBlockId(bundleLocation, blockNameText
							.getText())
					+ StringUtils.CR
					+ "Block file: "
					+ GeneratorUtils.getBlockLocation(bundleLocation,
							blockNameText.getText()).getAbsolutePath());
		}

		// this is need for two reasons: (1) the prefered width of the
		// bundleLocationTextField nees to be respected (2) if the wizard
		// message gets very long, the window has to be resized to show the full
		// message.
		getShell().pack(true);
	}

	/** Check if bundle location is valid. */
	private void checkBundleLocation() {
		String bundleLocationPath = bundleLocationText.getText();

		if (StringUtils.isEmpty(bundleLocationPath)) {
			setErrorMessage("Bundle location must be specified!");
			return;
		}

		File bundleLocation = new File(bundleLocationPath);

		if (!bundleLocation.exists()) {
			setErrorMessage("Bundle location does not exist!");
			return;
		}

		if (!bundleLocation.isDirectory()) {
			setErrorMessage("Bundle location is not a directory!");
			return;
		}

		// TODO (FD): Redundancy to ConQAT
		if (!(new File(bundleLocation, "bundle.xml").exists())) {
			setErrorMessage("Bundle location does not contain a ConQAT bundle!");
			return;
		}

	}

	/** Check if bundle name is valid. */
	private void checkBlockName() {
		String blockName = blockNameText.getText();

		if (StringUtils.isEmpty(blockName)) {
			setErrorMessage("Block name must be specified!");
			return;
		}

		// TODO: this a bit of hack
		if (!blockNamePattern.matcher(blockName).matches()
				|| blockName.endsWith(".") || blockName.contains("..")) {
			setErrorMessage("Invalid block name!");
			return;
		}

	}

	/** Check if a language is selected. */
	private void checkLanguage() {
		if (languageCombo.getSelectionIndex() < 0) {
			setErrorMessage("Implementation languages must be selected.");
		}
	}

	/**
	 * Run generator.
	 * 
	 * @return always returns <code>true</code>
	 */
	@Override
	public boolean performFinish() {

		final String bundleLocation = bundleLocationText.getText();
		final String blockName = blockNameText.getText();
		final String language = LANGUAGES[languageCombo.getSelectionIndex()];

		getDialogSettings().put(BUNDLE_LOCATION_KEY, bundleLocation);
		getDialogSettings().put(BLOCK_NAME_KEY, blockName);
		getDialogSettings().put(LANGUAGE_INDEX_KEY,
				languageCombo.getSelectionIndex());

		IRunnableWithProgress op = new IRunnableWithProgress() {
			@Override
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					new ConQATBlockGenerator(new File(bundleLocation),
							blockName, language, models).generate(monitor);
				} catch (IOException e) {
					throw new InvocationTargetException(e);
				} catch (ConQATBlockGenerationException e) {
					throw new InvocationTargetException(e);
				}

			}
		};

		try {
			new ProgressMonitorDialog(getShell()).run(true, true, op);
		} catch (InvocationTargetException e) {
			Throwable targetException = e.getTargetException();
			String message;
			if (targetException instanceof ConQATBlockGenerationException) {
				message = "Block could not be generated: "
						+ targetException.getMessage();
			} else {
				message = "Unexpected error: " + targetException.getMessage()
						+ StringUtils.CR
						+ StringUtils.obtainStackTrace(targetException);
			}
			MessageUtils.showError("Error", message);

		} catch (InterruptedException e) {
			MessageUtils.showError("Error", e.getMessage());
		}
		return true;
	}

	/** Obtain settings. */
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings section = ConQATActivator.getDefault()
				.getDialogSettings().getSection(
						GeneratorPage.class.getSimpleName());
		if (section == null) {
			section = ConQATActivator.getDefault().getDialogSettings()
					.addNewSection(GeneratorPage.class.getSimpleName());
		}
		return section;
	}

}
