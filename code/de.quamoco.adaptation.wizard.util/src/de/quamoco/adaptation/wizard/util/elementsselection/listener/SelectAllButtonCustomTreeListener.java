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

package de.quamoco.adaptation.wizard.util.elementsselection.listener;

import org.eclipse.swt.widgets.ToolItem;

import de.quamoco.adaptation.wizard.util.elementsselection.ICustomTreeViewer;

/**
 * Updates the status of the select all button depending on the events
 * thrown by the {@link ICustomTreeViewer}.
 * @author Franz Becker
 * @levd.rating GREEN Hash: ED224C2CCB25E9C7B42E9C1BFBA9915E
 */
public class SelectAllButtonCustomTreeListener implements ICustomTreeRefreshListener, ICheckedElementsChangedListener {

	/** The select button. */
	private ToolItem selectAllButton;

	/**
	 * Initializes the fields.
	 * @param selectAllBtton the select all button
	 */
	public SelectAllButtonCustomTreeListener(ToolItem selectAllButton) {
		this.selectAllButton = selectAllButton;
	}
	
	/**
	 * If the tree is empty disable the select button, enable it otherwise.
	 * Set the check box at the button according to whether all elements
	 * in the tree are selected or not.
	 */
	@Override
	public void treeRefreshed(ICustomTreeViewer customTree) {
		selectAllButton.setEnabled(customTree.hasVisibleElements());
		selectAllButton.setSelection(customTree.areAllVisibleElementsChecked());
	}

	/**
	 * Set the check box at the button according to whether all elements
	 * in the tree are selected or not.
	 */
	@Override
	public void checkedElementsChanged(ICustomTreeViewer customTree) {
		selectAllButton.setSelection(customTree.areAllVisibleElementsChecked());
	}
	
}
