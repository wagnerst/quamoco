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

package de.quamoco.qm.util;

import java.util.List;
import java.util.Set;

import org.conqat.lib.commons.collections.CollectionUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * {@link EContentAdapter} that only maintains itself as adapter for all
 * contained elements that are contained by certain references.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 5BD782A0CD8DE2EEBA37208EE0A9EFF4
 */
public class SelectiveContentAdapter extends EContentAdapter {

	/** Set of containment references. */
	private final Set<EReference> references;

	/** Constructor. */
	public SelectiveContentAdapter(EReference... references) {
		this.references = CollectionUtils.asHashSet(references);
	}

	/** {@inheritDoc} */
	@Override
	protected void handleContainment(Notification notification) {
		if (references.contains(notification.getFeature())) {
			super.handleContainment(notification);
		}
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected void setTarget(EObject target) {
		basicSetTarget(target);
		for (EReference reference : references) {
			if (reference.getEContainingClass().isInstance(target)) {
				if (reference.isMany()) {
					for (EObject value : (List<EObject>) target.eGet(reference)) {
						addAdapter(value);
					}
				} else {
					EObject value = (EObject) target.eGet(reference);
					if (value != null) {
						addAdapter(value);
					}
				}
			}
		}
	}
}
