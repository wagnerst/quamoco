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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemProvider;

/**
 * An {@link ItemProvider} that is the parent of {@link TransientItemProvider}s.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: B1A854A7726B5DBF28A6714107166A8B
 */
public interface ITransientParentItemProvider {

	/** Get the {@link TransientItemProvider} for a certain feature. */
	TransientItemProvider getProvider(EStructuralFeature feature);

	/** Get the children features of this provider. */
	Collection<? extends EStructuralFeature> getChildrenFeatures(Object object);

	/** Get the {@link AdapterFactory} of this provider. */
	AdapterFactory getAdapterFactory();

	/** Get the element which is represented by this provider. */
	Object getTarget();
}
