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

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;

/**
 * Helper class to merge to elements. Not only the contents of two elements are
 * merged, but also references targeting them. Multi-valued features are merged
 * by concatenating their values. For single-valued features, conflicts have to
 * be resolved, in case their values contradict each other.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 7CD637686C96EC974F4BBBB1D2396950
 */
public class Merge {

	/**
	 * The first element
	 */
	private final EObject first;

	/**
	 * The second element
	 */
	private final EObject second;

	/**
	 * The conflicts
	 */
	private Map<EStructuralFeature, Conflict> conflicts;

	/**
	 * Constructor
	 */
	public Merge(EObject first, EObject second) {
		if (first.eContainer() != second.eContainer()) {
			throw new IllegalArgumentException(
					"Elements must have the same container.");
		}
		if (first.eClass() != second.eClass()) {
			throw new IllegalArgumentException(
					"Elements must be of the same type.");
		}

		this.first = first;
		this.second = second;
	}

	/**
	 * Obtain the conflicts
	 */
	public Collection<Conflict> getConflicts() {
		if (conflicts == null) {
			conflicts = new IdentityHashMap<EStructuralFeature, Conflict>();
			EClass eClass = first.eClass();

			for (EStructuralFeature feature : eClass
					.getEAllStructuralFeatures()) {
				if (!feature.isMany()) {
					Object value1 = first.eGet(feature);
					Object value2 = second.eGet(feature);

					if (value1 == null || value2 == null) {
						if (value1 != null || value2 != null) {
							conflicts.put(feature, new Conflict(this, feature));
						}
					} else {
						if (!value1.equals(value2)) {
							conflicts.put(feature, new Conflict(this, feature));
						}
					}
				}
			}
		}

		return conflicts.values();
	}

	/**
	 * Apply the merge
	 */
	public void apply() {
		mergeFeatures();
		mergeCrossReferences();

		EcoreUtil.delete(second);
	}

	/**
	 * Merge the contents of the two elements
	 */
	@SuppressWarnings("unchecked")
	private void mergeFeatures() {
		EClass eClass = first.eClass();
		for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
			if (feature.isMany()) {
				List secondValues = (List) second.eGet(feature);
				List firstValues = (List) first.eGet(feature);
				for (Object value : new ArrayList(secondValues)) {
					if (feature.isUnique() && firstValues.contains(value)) {
						continue;
					}
					firstValues.add(value);
				}
				secondValues.clear();
			} else {
				Conflict conflict = conflicts.get(feature);
				if (conflict != null) {
					first.eSet(feature, conflict.getResolutionValue());
				}
			}
		}
	}

	/**
	 * Merge the references targeting the two elements
	 */
	@SuppressWarnings("unchecked")
	private void mergeCrossReferences() {
		Collection<Setting> links = UsageCrossReferencer.find(second,
				second.eResource().getResourceSet());
		for (Setting link : links) {
			EReference reference = (EReference) link.getEStructuralFeature();
			if (reference.getEOpposite() == null) {
				EObject source = link.getEObject();
				if (reference.isMany()) {
					List values = (List) source.eGet(reference);
					values.remove(second);
					values.add(first);
				} else {
					source.eSet(reference, first);
				}
			}
		}
	}

	/**
	 * Return the first element
	 */
	public EObject getFirst() {
		return first;
	}

	/**
	 * Return the second element
	 */
	public EObject getSecond() {
		return second;
	}
}
