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
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;

/**
 * Helper methods for employing {@link TransientItemProvider}s.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: F9382BE16783865A3956364920650B50
 */
public final class TransientItemProviderUtils {

	/** Get the parent of an element using a number of parent references. */
	@SuppressWarnings("unchecked")
	public static Object getParent(AdapterFactory adapterFactory,
			EObject element, EReference... parentReferences) {
		if (isRoot(element, parentReferences)) {
			return element.eResource() != null ? element.eResource()
					.getResourceSet() : null;
		}
		for (EReference parentReference : parentReferences) {
			Object value = element.eGet(parentReference);
			if (parentReference.isMany()) {
				Collection values = (Collection) value;
				if (!values.isEmpty()) {
					return getTransientItemProvider(adapterFactory, values
							.iterator().next(), parentReference);
				}
			} else {
				if (value != null) {
					return getTransientItemProvider(adapterFactory, value,
							parentReference);
				}
			}
		}
		return null;
	}

	/**
	 * Check whether an element is the root using a number of parent references.
	 */
	public static boolean isRoot(EObject element,
			EReference... parentReferences) {
		for (EReference parentReference : parentReferences) {
			if (element.eIsSet(parentReference)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the {@link TransientItemProvider} corresponding to a certain
	 * reference.
	 */
	private static Object getTransientItemProvider(
			AdapterFactory adapterFactory, Object parent,
			EReference parentReference) {
		ITransientParentItemProvider provider = (ITransientParentItemProvider) adapterFactory
				.adapt(parent, IEditingDomainItemProvider.class);

		return provider != null ? provider.getProvider(parentReference
				.getEOpposite()) : null;
	}

	/**
	 * Wrap each command to modify the affected objects, in case the affected
	 * objects are the providers.
	 */
	public static Command createWrappedCommand(
			ITransientParentItemProvider parentProvider, Command command,
			final EObject owner, final EStructuralFeature feature) {

		final TransientItemProvider provider = parentProvider
				.getProvider(feature);
		if (provider != null) {
			return new CommandWrapper(command) {
				@SuppressWarnings("unchecked")
				@Override
				public Collection getAffectedObjects() {
					Collection affected = super.getAffectedObjects();
					if (affected.contains(owner)) {
						affected = Collections.singleton(provider);
					}
					return affected;
				}
			};
		}
		return command;
	}
}