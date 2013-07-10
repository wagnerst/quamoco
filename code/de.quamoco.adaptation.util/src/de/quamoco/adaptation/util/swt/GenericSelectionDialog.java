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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * Provides a generic, EMF based selection dialog.
 * @author Franz Becker
 */
public class GenericSelectionDialog extends ElementListSelectionDialog {

	/** The generic composed adapter factory */
	static protected AdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	
	/** The generic label provider */
	static protected ILabelProvider labelProvider = new AdapterFactoryLabelProvider(adapterFactory);

	/**
	 * Calls the super constructor and configures the dialog
	 * @param title the title of the dialog
	 * @param message the message shown to the user
	 * @param objects the objects from which the user can choose
	 */
	public GenericSelectionDialog(String title, String message, Object[] objects) {
		super(PlatformUI.getWorkbench().getDisplay().getActiveShell(), labelProvider);
		this.setTitle(title);
		this.setMessage(message);
		this.setElements(objects);
	}
	
	/**
	 * Prevents the text filter from appearing
	 */
	protected Text createFilterText(Composite parent) {
		Text parentText = super.createFilterText(parent);
//		parentText.setVisible(false);
		return parentText;
	}

	/** Create only an OK Button, no Cancel button! */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,	true);
	}
	
}
