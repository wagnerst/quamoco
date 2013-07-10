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

package de.quamoco.adaptation.util.qm.compare;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.quamoco.qm.QualityModel;

/**
 * A {@link Dialog} that uses {@link QualityModelCompareViewer} to compare two
 * passed models.
 * @author Franz Becker
 */
public class QualityModelCompareDialog extends Dialog {

	/** The title of the dialog*/
	protected final String dialogTitle = "Compare";
	
	/** The image of the dialog, currently null */
	protected Image titleImage = null;
	// TODO set image
	
	/** The first model */
	protected QualityModel leftModel;
	
	/** The second model */ 
	protected QualityModel rightModel;

	/** The label that is displayed above the left model */
	protected String leftLabel;

	/** The label that is displayed above the right model*/
	protected String rightLabel;
	
	/**
	 * Calls super constructor and initializes the fields<br>
     * <b>Important: Make sure that the passed models are copied using {@link CompareCopier}!</b><br>
	 * This is necessary cause the comparison is based on the element's IDs that are copied by the
	 * {@link CompareCopier} into their annotations. 
	 * @param parentShell the parent shell
	 * @param leftModel initializes {@link #leftModel}
	 * @param rightModel initializes {@link #rightModel}
	 * @param leftLabel the label that is displayed above the left model
	 * @param rightLabel the label that is displayed above the right model
	 */
	public QualityModelCompareDialog(Shell parentShell, QualityModel leftModel, QualityModel rightModel, String leftLabel, String rightLabel) {
		super(parentShell);
		this.leftModel = leftModel;
		this.rightModel = rightModel;
		this.leftLabel = leftLabel;
		this.rightLabel = rightLabel;
	}

	/**
	 * Calls super method and sets the dialog title and image
	 */
	@Override
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        if (dialogTitle != null) {
			shell.setText(dialogTitle);
		}
        if (titleImage != null) {
			shell.setImage(titleImage);
		}
    }
	
	/**
	 * Creates a single button named "close" that is in fact an OK button.
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButton(parent, OK, "Close", true);
    }	

	/**
	 * Creates the {@link QualityModelCompareViewer} and returns its control.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        QualityModelCompareViewer viewer = new QualityModelCompareViewer(composite, leftLabel, rightLabel);
        viewer.setInput(leftModel, rightModel);
        viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        applyDialogFont(composite);
        return composite;
    }

	/**
	 * Sets the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		Point point = super.getInitialSize();
		point.x = 800;
		point.y = 700;
		return point;
	}
	
	/**
	 * Dialog is supposed to be resizable
	 */
	@Override
	protected boolean isResizable() {
		return true;
	}
	
}
