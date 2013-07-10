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

import de.quamoco.adaptation.wizard.util.elementsselection.listener.ICheckedElementsChangedListener;
import de.quamoco.adaptation.wizard.util.elementsselection.listener.ICustomTreeRefreshListener;

/**
 * Provides a custom implementation of a tree viewer. This is necessary,
 * since the existing tree viewers are not capable of preserving their
 * checked state correctly when combined with filtering.
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 2E2CD63AF3E85D6A1F263D3C3CFE8C42
 */
public interface ICustomTreeViewer {
	
	/**
	 * @return true if there are currently visible elements in the tree,
	 * false otherwise
	 */
	public boolean hasVisibleElements();

	/**
	 * @return true if all currently visible elements are checked, false otherwise.
	 */
	public boolean areAllVisibleElementsChecked();
	
	/**
	 * Marks all <b>visible</b> elements as checked.
	 */
	void checkAllVisible();

	/**
	 * Marks all <b>visible</b> elements as unchecked.
	 */	
	void uncheckAllVisible();
	
	/**
	 * Sets the checked state of currently selected elements and their parents.<br> 
	 * Only checks the currently visible parents in the current hierarchy.
	 * @param state true for checked, false for unchecked
	 */
	void setVisibleParentsChecked(boolean state);
	
	/**
	 * Sets the checked state of currently selected elements and their children.<br> 
	 * Only checks the currently visible children in the current hierarchy.
	 * @param state true for checked, false for unchecked
	 */
	void setAllVisibleChildrenChecked(boolean state);
	
	/**
	 * Adds a listener that shall be notified every time the viewer is refreshed.
	 * I.e. when it's public {@link #refresh()} is called! The listeners are not 
	 * notified when another method is called.
	 * Has no effect if an identical listener is already registered.
	 * @param listener the refresh listener
	 */
	public void addRefreshListener(ICustomTreeRefreshListener listener);
	
	/**
	 * Removes the given refresh listener from this viewer. 
	 * Has no effect if an identical listener is not registered.
	 * @param listener the refresh listener
	 */
	public void removeRefreshListener(ICustomTreeRefreshListener listener);

	/**
	 * Adds a listener that shall be notified each time the list of checked elements changed.
	 * Has no effect if an identical listener is already registered.
	 * @param listener the {@link ICheckedElementsChangedListener}
	 */
	public void addCheckedElementsChangedListener(ICheckedElementsChangedListener listener);
	
	/**
	 * Removes the given {@link ICheckedElementsChangedListener} from this viewer. 
	 * Has no effect if an identical listener is not registered.
	 * @param listener the {@link ICheckedElementsChangedListener}
	 */
	public void removeCheckedElementsChangedListener(ICheckedElementsChangedListener listener);
		
}
