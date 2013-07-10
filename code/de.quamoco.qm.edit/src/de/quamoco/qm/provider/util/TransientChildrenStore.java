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

package de.quamoco.qm.provider.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Store to maintain the children of {@link TransientItemProvider}s.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 36C3C26E80A7DDD7C63A5617C52AD166
 */
public class TransientChildrenStore {

	/** Providers that group the value of each children feature. */
	protected Map<EStructuralFeature, TransientItemProvider> providers;

	/** The provider of the parent element. */
	protected final ITransientParentItemProvider parentProvider;

	/**
	 * The children features for which {@link TransientItemProvider}s need to be
	 * maintained.
	 */
	private final Collection<? extends EStructuralFeature> childrenFeatures;

	/** Constructor. */
	public TransientChildrenStore(ITransientParentItemProvider parentProvider) {
		this(parentProvider, parentProvider.getChildrenFeatures(parentProvider
				.getTarget()));
	}

	/** Constructor. */
	public TransientChildrenStore(ITransientParentItemProvider parentProvider,
			Collection<? extends EStructuralFeature> features) {
		this.parentProvider = parentProvider;
		this.childrenFeatures = features;
	}

	/** Get the children of the element to which this store is attached. */
	@SuppressWarnings("unchecked")
	public Collection<?> getChildren(Object object) {
		initProviders(object);

		EObject element = (EObject) object;
		List children = new ArrayList();
		for (EStructuralFeature feature : parentProvider
				.getChildrenFeatures(object)) {
			TransientItemProvider provider = providers.get(feature);
			if (provider != null) {
				if (feature.isMany()) {
					if (!((List) element.eGet(feature)).isEmpty()) {
						children.add(provider);
					}
				} else {
					if (element.eGet(feature) != null) {
						children.add(provider);
					}
				}
			} else {
				if (feature.isMany()) {
					children.addAll((List) element.eGet(feature));
				} else {
					children.add(element.eGet(feature));
				}
			}
		}
		return children;
	}

	/**
	 * Initialize the {@link TransientItemProvider}s for the element to which
	 * this store is attached.
	 */
	private void initProviders(Object object) {
		if (providers == null) {
			providers = new IdentityHashMap<EStructuralFeature, TransientItemProvider>();
			Collection<? extends EStructuralFeature> features = childrenFeatures;
			if (features == null) {
				features = parentProvider.getChildrenFeatures(object);
			}
			for (EStructuralFeature reference : features) {
				TransientItemProvider provider = createTransientItemProvider(
						(EObject) object, (EReference) reference);
				providers.put(reference, provider);
			}
		}
	}

	/** Create the transient item provider for a certain feature. */
	protected TransientItemProvider createTransientItemProvider(
			EObject element, EReference reference) {
		return new TransientItemProvider(parentProvider.getAdapterFactory(),
				element, reference);
	}

	/** Get the provider that groups the values of a certain children feature. */
	public TransientItemProvider getProvider(EStructuralFeature feature) {
		getChildren(parentProvider.getTarget());
		return providers.get(feature);
	}
}