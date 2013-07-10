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

package de.quamoco.qm.editor.refactoring;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * A conflict between two elements concerning the value of a feature. By
 * default, the conflict is resolved to accept the value of the first element.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 374142E701D8C1F9471C0CD07FE35E7B
 */
public class Conflict {

	/**
	 * The merge
	 */
	private final Merge merge;

	/**
	 * The feature
	 */
	private final EStructuralFeature feature;

	/**
	 * The value of which element is selected for resolution of the conflict (by
	 * default, the first element is selected)
	 */
	private boolean first = true;

	/**
	 * Constructor
	 */
	public Conflict(Merge merge, EStructuralFeature feature) {
		this.feature = feature;
		this.merge = merge;
	}

	/**
	 * Resolve the conflict by choosing the value of a certain element
	 */
	public void resolve(boolean first) {
		this.first = first;
	}

	/**
	 * Return whether the first element is the resolution
	 */
	public boolean getResolution() {
		return first;
	}

	/**
	 * Return the value of the resolution
	 */
	public Object getResolutionValue() {
		return first ? getFirstValue() : getSecondValue();
	}
	
	/**
	 * Return the first element
	 */
	public EObject getFirst() {
		return merge.getFirst();
	}

	/**
	 * Return the value of the feature for the first element
	 */
	public Object getFirstValue() {
		return getFirst().eGet(feature);
	}

	/**
	 * Return the second element
	 */
	public EObject getSecond() {
		return merge.getSecond();
	}

	/**
	 * Return the value of the feature for the second element
	 */
	public Object getSecondValue() {
		return getSecond().eGet(feature);
	}

	/**
	 * Return the feature
	 */
	public EStructuralFeature getFeature() {
		return feature;
	}
}
