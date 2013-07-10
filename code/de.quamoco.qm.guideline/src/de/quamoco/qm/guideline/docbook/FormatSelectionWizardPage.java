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
   $Id: FormatSelectionWizardPage.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A {@link WizardPage} for choosing the targetFormat for the
 * {@link GuidelineWizard}.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating GREEN Hash: 373CD3A7CDC66A66271A14BA4A11EAA8
 */
public class FormatSelectionWizardPage extends WizardPage {

	/**
	 * Calls the constructor of the superclass.
	 */
	protected FormatSelectionWizardPage() {
		super("Output Format");
	}

	/** {@inheritDoc} */
	public void createControl(Composite parent) {
		setPageComplete(false);
		setTitle("Output Format");
		setDescription("Please choose the format of the generated guideline.");
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		new Label(composite, SWT.NONE).setText("Choose file format:");
		for (EGuidelineTargetFormat format : EGuidelineTargetFormat.values()) {
			createButton(composite, format);
		}
		setControl(composite);
	}

	/**
	 * Creates radio buttons for choosing the target format.
	 * 
	 * @param composite
	 *            the parent.
	 * @param format
	 *            the constant defining the target format.
	 */
	private void createButton(Composite composite,
			final EGuidelineTargetFormat format) {
		Button button = new Button(composite, SWT.RADIO);
		button.setText(format.getLabel());
		button.addSelectionListener(new SelectionAdapter() {

			/** {@inheritDoc} */
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GuidelineWizard) getWizard()).setTargetFormat(format);
				setPageComplete(true);
			}

		});
	}

}
