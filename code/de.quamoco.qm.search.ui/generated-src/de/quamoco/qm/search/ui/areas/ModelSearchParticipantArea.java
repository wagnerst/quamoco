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

package de.quamoco.qm.search.ui.areas;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.search.ui.areas.AbstractModelSearchParticipantArea;
import org.eclipse.emf.search.ui.pages.AbstractModelSearchPage;
import org.eclipse.emf.search.ui.providers.AbstractMetaModelParticipantsItemProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.emf.search.utils.ModelSearchImagesUtil;
import java.net.URL;


import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import java.util.Collection;



import de.quamoco.qm.provider.QmItemProviderAdapterFactory;
import de.quamoco.qm.search.ui.Activator;
import de.quamoco.qm.search.ui.providers.MetaModelParticipantsItemProvider;
import de.quamoco.qm.search.util.TextualFeaturesUtils;

import de.quamoco.qm.QmPackage;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import java.util.Arrays;

public final class ModelSearchParticipantArea extends
		AbstractModelSearchParticipantArea {

	private String nsURI;

	public ModelSearchParticipantArea(Composite parent,
			AbstractModelSearchPage page, int style) {
		super(parent, page, style);
		createContent();
	}

	public ModelSearchParticipantArea(Composite parent,
			AbstractModelSearchPage page, int style, String nsURI) {
		super(parent, page, style);
		this.nsURI = nsURI;
		createContent();
	}

	@Override
	protected boolean getDefaultParticpantsControlEnabling() {
		return false;
	}

	@Override
	public List<AdapterFactory> getMetaElementComposeableAdapterFactories(
			String nsURI) {
		return getMetaElementComposeableAdapterFactories();
	}

	@Override
	public List<AdapterFactory> getMetaElementComposeableAdapterFactories() {
		List<AdapterFactory> composeableAdapterFactories = new ArrayList<AdapterFactory>();
		composeableAdapterFactories.add(new EcoreItemProviderAdapterFactory());

		if (QmPackage.eNS_URI.equals(nsURI)) {
			composeableAdapterFactories.add(new QmItemProviderAdapterFactory());
		}

		if (nsURI == null || "".equals(nsURI)) {

			composeableAdapterFactories.add(new QmItemProviderAdapterFactory());

		}
		composeableAdapterFactories
				.add(new ResourceItemProviderAdapterFactory());
		return composeableAdapterFactories;
	}

	@Override
	public AbstractMetaModelParticipantsItemProvider getMetaModelParticipantsItemProvider() {
		return new MetaModelParticipantsItemProvider(getTargetMetaModelIDs());
	}

	@Override
	protected Collection<EPackage> getTargetModelPackages() {

		if (QmPackage.eNS_URI.equals(nsURI)) {
			return Arrays.asList(new EPackage[] { QmPackage.eINSTANCE });
		}

		return Arrays.asList(new EPackage[] {

		QmPackage.eINSTANCE

		});
	}

	public Collection<String> getTargetMetaModelIDs() {

		if (QmPackage.eNS_URI.equals(nsURI)) {
			return Arrays.asList(new String[] { nsURI });
		}

		return Arrays.asList(new String[] {

		QmPackage.eNS_URI

		});
	}

	@Override
	protected String getTargetModelElementText(Object object) {
		return object instanceof ENamedElement ? ((ENamedElement) object)
				.getName() : null;
	}

	@Override
	protected Image getTargetModelElementImage(Object object) {
		try {
			if (object instanceof ENamedElement) {
				String imagePath = "/icons/full/obj16/" + computeElementImageName(((ENamedElement) object).getName()) + ".gif"; //$NON-NLS-1$  //$NON-NLS-2$
				URL url = FileLocator.find(de.quamoco.qm.provider.QmEditPlugin
						.getPlugin().getBundle(), new Path(imagePath), null);
				if (url != null) {
					return ModelSearchImagesUtil.getImage(url);
				}
			}
		} catch (Throwable t) {
			Activator
					.getDefault()
					.getLog()
					.log(
							new Status(
									Status.ERROR,
									Activator.PLUGIN_ID,
									"Error while attempmting to retrieve image from edit" + de.quamoco.qm.provider.QmEditPlugin.getPlugin().getBundle() + " bundle")); //$NON-NLS-1$  //$NON-NLS-2$
		}
		return null;
	}

	private String computeElementImageName(String name) {
		return name;
	}
}
