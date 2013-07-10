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

package de.quamoco.qm.properties;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

import de.quamoco.qm.provider.QmItemProviderAdapterFactory;
import edu.tum.cs.emf.commons.FilterUtils;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;
import edu.tum.cs.emf.commons.validation.IValidationResultListener;
import edu.tum.cs.emf.commons.validation.IgnoreExtension;
import edu.tum.cs.emf.commons.validation.Severity;
import edu.tum.cs.emf.commons.validation.Validation;
import edu.tum.cs.emf.commons.validation.ValidationUtils;
import edu.tum.cs.emf.commons.validation.view.ValidationUIUtils;

/**
 * Label provider for the properties view.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: DDE3C72586BE613574B0AC553BD84BD6
 */
public class PropertyLabelProvider extends BaseLabelProvider implements
		ILabelProvider, INotifyChangedListener,
		// IValidationListener,
		IValidationResultListener {

	/** Underlying label provider. */
	private final AdapterFactoryLabelProvider labelProvider;

	/** Underlying adapter factory. */
	private final QmItemProviderAdapterFactory adapterFactory;

	/** Constructor. */
	public PropertyLabelProvider() {
		adapterFactory = new QmItemProviderAdapterFactory();
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		adapterFactory.addListener(this);
	}

	/** {@inheritDoc} */
	public Image getImage(Object element) {
		Object unwrapped = unwrap(element);
		Image image = labelProvider.getImage(unwrapped);
		if (unwrapped instanceof EObject) {
			EObject eObject = (EObject) unwrapped;
			List<IConstraintStatus> problems = FilterUtils.filter(
					Validation.getProblems(eObject), IgnoreExtension.FILTER);
			Severity severity = ValidationUtils.getSeverity(problems);
			return ValidationUIUtils.decorateImage(image, severity);
		}
		return image;
	}

	/** {@inheritDoc} */
	public String getText(Object element) {
		element = unwrap(element);
		String label = labelProvider.getText(element);
		if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			if (eObject.eResource() != null) {
				Validation.getResult(eObject.eResource().getResourceSet())
						.addListener(this);
			}
			return ResourceLocatorUtils.getString(eObject.eClass()) + ": "
					+ label;
		}
		return label;
	}

	/** Unwrap an element from a selection. */
	private Object unwrap(Object element) {
		if (element instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) element;
			return unwrap(selection.getFirstElement());
		}
		return element;
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		super.dispose();
		adapterFactory.removeListener(this);
		adapterFactory.dispose();
		labelProvider.dispose();
	}

	/** {@inheritDoc} */
	public void notifyChanged(Notification notification) {
		fireLabelProviderChanged(new LabelProviderChangedEvent(this,
				notification.getNotifier()));
	}

	/** {@inheritDoc} */
	@Override
	public void resultChanged(Set<EObject> elements) {
		fireLabelProviderChanged(new LabelProviderChangedEvent(this));
	}
}
