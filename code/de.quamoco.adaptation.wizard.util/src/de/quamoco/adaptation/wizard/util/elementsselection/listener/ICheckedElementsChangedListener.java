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

import de.quamoco.adaptation.wizard.util.elementsselection.ICustomTreeViewer;

/**
 * A listener that is called each time when the set of checked
 * elements has changed.
 * @author Franz Becker 
 * @levd.rating GREEN Hash: F3EED0A8606F76FBE391DCF4D6F2F05B 
 */
public interface ICheckedElementsChangedListener {

	/** 
	 * Handles the event when the set of checked elements has
	 * changed.
	 * @param customTree the tree of interest
	 */
	void checkedElementsChanged(ICustomTreeViewer customTree);
	
}
