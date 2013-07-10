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

package de.quamoco.adaptation.wizard.util.elementsselection;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Builds the context menu for the select elements tree
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 365F79ABF767C3600B85F94DDC55A8D4
 */
public class SelectElementsTreeMenuListener implements IMenuListener {

	/** The {@link CustomFilteredTree} that contains the {@link #selectElementsTreeViewer}. */
	protected CustomFilteredTree selectElementsFilteredTree;
	
	/** The {@link CustomTreeViewer} this listener shall act on. */
	protected CustomTreeViewer selectElementsTreeViewer;
	
	/**
	 * Initializes instance variables
	 * @param selectElementsFilteredTree
	 */
	public SelectElementsTreeMenuListener(CustomFilteredTree selectElementsFilteredTree) {
		this.selectElementsFilteredTree = selectElementsFilteredTree;
		this.selectElementsTreeViewer = selectElementsFilteredTree.getCustomTreeViewer();
	}
	
	/**
	 * Builds the menu from scratch
	 */
	@Override
	public void menuAboutToShow(IMenuManager manager) {
		IStructuredSelection selection = (IStructuredSelection) selectElementsTreeViewer.getSelection();
		if (selection != null && !selection.isEmpty()) {
			
			Action checkAllParents = new Action() {					
				@Override
				public void run() {
					selectElementsTreeViewer.setVisibleParentsChecked(true);		
				}
			};
			Action checkAllChildren = new Action() {		
				@Override
				public void run() {
					selectElementsTreeViewer.setAllVisibleChildrenChecked(true);
				}
			};
			Action uncheckAllParents = new Action() {
				@Override
				public void run() {
					selectElementsTreeViewer.setVisibleParentsChecked(false);
				}
			};
			Action uncheckAllChildren = new Action() {	
				@Override
				public void run() {
					selectElementsTreeViewer.setAllVisibleChildrenChecked(false);
				}
			};

			String labelText = selectElementsFilteredTree.isFiltering() ? "all (visible)" : "all";
			checkAllParents.setText("Select this element and " + labelText + " parents");
			checkAllChildren.setText("Select this element and " + labelText + " children");
			uncheckAllParents.setText("Unselect this element and " + labelText + " parents");
			uncheckAllChildren.setText("Unselect this element and " + labelText + " children");
			manager.add(checkAllParents);
			manager.add(checkAllChildren);
			manager.add(new Separator());
			manager.add(uncheckAllParents);
			manager.add(uncheckAllChildren);			
		}

	}

}
