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

package de.quamoco.adaptation.util.swt;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

/**
 * Utility class for handling SWT standard tasks
 * @author Franz Becker
 */
public class SWTUtil {
	
	/**
	 * Creates a new {@link Group} according with style {@link SWT#NONE}
	 * and 0 margin height and width.
	 * @param parent the parent of the new group
	 * @param title the title of the new group
	 * @return a newly created group
	 */
	public static Group createGroup(Composite parent, String title) {
		Group group = new Group(parent, SWT.NONE);
		group.setText(title);		
		GridLayout groupLayout = new GridLayout();
		groupLayout.marginHeight = 1;
		groupLayout.marginWidth = 1;
		group.setLayout(groupLayout);		
		return group;
	}
	
	/**
	 * Calls {@link #showErrorDialog(Shell, String, String)} with 
	 * a specified title.
	 * @param shell the shell of the dialog
	 * @param message the message of the dialog
	 * @param exeception the exception that was thrown (optional)
	 */
	public static void showErrorDialog(Shell shell, String message, Exception exception) {
		showErrorDialog(shell, "Something went wrong...", message, exception);
	}
	
	/**
	 * Create and opens simple error dialog with an OK button.
	 * @param shell the shell of the dialog
	 * @param title the title of the dialog
	 * @param message the message of the dialog
	 * @param exeception the exception that was thrown (optional)
	 */
	public static void showErrorDialog(Shell shell, String title, String message, Exception exception) {
		String[] buttons = { IDialogConstants.OK_LABEL };
		if (exception != null) {
			message = message + "\n\nException: " + exception.getClass().getSimpleName() + "\nMessage: " + exception.getMessage();
		}
		new MessageDialog(shell, title, null, message, MessageDialog.ERROR, buttons, 0).open();
	}
	
	/**
	 * Creates a {@link GridLayout} with no margin around it.
	 * Useful for composites that are nested within other composites and structure UI elements.
	 * @param numColumns passed to the {@link GridLayout} constructor
	 * @param makeColumnsEqualWidth passed to the {@link GridLayout} constructor
	 * @return the desired {@link GridLayout}
	 */
	public static GridLayout createNoMarginGridLayout(int numColumns, boolean makeColumnsEqualWidth) {
		GridLayout gridLayout = new GridLayout(numColumns, makeColumnsEqualWidth);
		gridLayout.marginWidth = 0;
		return gridLayout;
	}

	
}
