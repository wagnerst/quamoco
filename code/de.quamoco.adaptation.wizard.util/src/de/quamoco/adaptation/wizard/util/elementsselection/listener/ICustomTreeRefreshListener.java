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
 * A listener that shall be called when the public method refresh(..)
 * is called on an {@link AbstractCustomTreeViewer}. This listener
 * is required since it's the only possibility to get notified when
 * the text filter changes (then the tree is refreshed).
 * @author Franz Becker
 * @levd.rating GREEN Hash: 590B01457860BB02BAD82A9C39A15FB4
 */
public interface ICustomTreeRefreshListener {

	/**
	 * Handles the refreshment of the passed {@link ICustomTreeViewer}
	 * @param customTree the tree of interest
	 */
	void treeRefreshed(ICustomTreeViewer customTree);
	
}
