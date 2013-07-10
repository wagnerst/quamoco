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

package de.quamoco.adaptation.wizard.util.elementsselection.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.provider.QmItemProviderAdapterFactory;

/**
 * Provides a hierarchical view of a {@link QualityModel}.
 * @author Franz Becker
 * @levd.rating GREEN Hash: 36046728BD60266D3302E350B73B4097
 */
public class CustomQmItemProviderAdapterFactory extends QmItemProviderAdapterFactory {

	/** The {@link ElementHierarchy} that shall be used by the item providers
	 * created by this factory. */
	protected ElementHierarchy elementHierarchy;

	/**
	 * Calls super constructor, initializes instance variables.
	 * @param elementHierarchy the {@link ElementHierarchy} that shall be used, may be null (for flat view)
	 */
	public CustomQmItemProviderAdapterFactory(ElementHierarchy elementHierarchy) {
		super();
		this.elementHierarchy = elementHierarchy;
	}

	/** {@inheritDoc} */
	@Override
	public Adapter createAdapter(Notifier target) {
		if (target instanceof TransientRoot) {
			return new TransientRootItemProvider(this, elementHierarchy);
		} else {
			return new GenericItemProvider(this, elementHierarchy);
		}
	}
	
}
