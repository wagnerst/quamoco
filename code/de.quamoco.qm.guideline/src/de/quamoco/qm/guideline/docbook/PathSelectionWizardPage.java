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

/*-----------------------------------------------------------------------+
 | de.quamoco.qm.guideline.docbook
 |                                                                       |
   $Id: PathSelectionWizardPage.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.docbook;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The {@link WizardPage} for choosing the targetPath for the
 * {@link GuidelineWizard}.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 14BE78AE16BF0E077244BC76E6711038
 */
public class PathSelectionWizardPage extends WizardPage {

	/**
	 * The text field for displaying the currently selected target path.
	 */
	private Text text;

	/**
	 * The {@link FileDialog} for choosing the target path.
	 */
	private FileDialog fileDialog;

	/**
	 * Calls the constructor of the superclass.
	 */
	protected PathSelectionWizardPage() {
		super("Output Path");
	}

	/** {@inheritDoc} */
	public void createControl(Composite parent) {
		setPageComplete(false);
		setTitle("Output Path");
		setDescription("Please choose the target file for your guideline.");
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.makeColumnsEqualWidth = false;
		layout.numColumns = 2;
		composite.setLayout(layout);

		createFileLabel(composite);
		addPathText(composite);
		addFileDialog(composite);

		setControl(composite);
	}

	/**
	 * Creates the file choosing {@link Label}.
	 */
	private void createFileLabel(Composite composite) {
		Label label = new Label(composite, SWT.NONE);
		label.setText("Choose file location:");
		GridData data = new GridData();
		data.horizontalSpan = 2;
		label.setLayoutData(data);
	}

	/**
	 * Adds the {@link Text} for displaying the currently chosen path.
	 */
	private void addPathText(Composite composite) {
		text = new Text(composite, SWT.BORDER);
		text.setEditable(false);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.FILL;
		text.setLayoutData(data);
	}

	/**
	 * Adds the button for opening the {@link FileDialog}.
	 */
	private void addFileDialog(Composite composite) {
		fileDialog = new FileDialog(composite.getShell(), SWT.SAVE);
		fileDialog.setOverwrite(true);
		Button button = new Button(composite, SWT.PUSH | SWT.CENTER);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {

			/** {@inheritDoc} */
			@Override
			public void widgetSelected(SelectionEvent e) {
				String filePath = fileDialog.open();
				if (filePath != null) {
					((GuidelineWizard) getWizard()).setTargetPath(filePath);
					text.setText(filePath);
					setPageComplete(true);
				}
			}
		});
	}

	/** Updates the text field, file dialog and targetPath variable. */
	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			GuidelineWizard guidelineWizard = (GuidelineWizard) getWizard();
			String targetPath = guidelineWizard.getTargetPath();
			String fileExtension = guidelineWizard.getTargetFormat()
					.getFileExtension();
			String fileName = "Guideline";
			// check whether targetPath is set and directory still exists
			if (targetPath != null
					&& (new File(targetPath).getParentFile()).exists()) {
				// check whether targetPath file extension is still valid
				if (!guidelineWizard.canFinish()) {
					fileName = FileUtils.getFileName(targetPath);
					text.setText("");
				} else {
					text.setText(targetPath);
				}
			}
			fileDialog
					.setFilterExtensions(new String[] { "*." + fileExtension });
			fileDialog.setFileName(fileName + "." + fileExtension);
		}
		super.setVisible(visible);
	}
}
