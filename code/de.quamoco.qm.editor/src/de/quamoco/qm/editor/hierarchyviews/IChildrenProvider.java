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

package de.quamoco.qm.editor.hierarchyviews;

import java.util.List;

/**
 * Interface to determine the children in a hierarchy
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: C9F143E01746A750926C1EAC3A5436C1
 */
public interface IChildrenProvider extends IHierarchyProvider {

	/** Determine whether children are provided for a certain element. */
	boolean providesChildren(Object element);

	/**
	 * Determine whether a certain element has children. This method is only
	 * called if {@link #providesChildren} evaluates to true.
	 */
	boolean hasChildren(Object element);

	/**
	 * Get the children of a certain element. This method is only called if
	 * {@link #hasChildren} evaluates to true.
	 */
	List<Object> getChildren(Object element);

}