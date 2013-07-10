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

/*-----------------------------------------------------------------------+
 | de.quamoco.qm.guideline
 |                                                                       |
   $Id: QualityModelSelectionWizardPage.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.docbook;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.emf.commons.tooltips.TreeViewerAttributeTooltip;

/**
 * The {@link WizardPage} for choosing the quality models to be exported.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 4CDAE155DA65D584CC1D0071E615838A
 */
public class QualityModelSelectionWizardPage extends WizardPage {

	/**
	 * The {@link ContainerCheckedTreeViewer}.
	 */
	private ContainerCheckedTreeViewer viewer;

	/**
	 * The {@link ComposedAdapterFactory}.
	 */
	private ComposedAdapterFactory adapterFactory;

	/**
	 * The {@link AdapterFactoryLabelProvider}.
	 */
	private AdapterFactoryLabelProvider labelProvider;

	/**
	 * Calls the constructor of the superclass.
	 */
	protected QualityModelSelectionWizardPage() {
		super("Quality Models");
	}

	/** {@inheritDoc} */
	public void createControl(Composite parent) {
		setTitle("Quality Models");
		setDescription("Please choose the quality models to be exported.");
		Composite composite = new Composite(parent, SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		Tree tree = new Tree(composite, SWT.CHECK | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.BORDER);
		createTreeViewer(tree);
		setControl(composite);
	}

	/**
	 * Creates the tree viewer displaying the choices of Quality Models that can
	 * be exported.
	 */
	private void createTreeViewer(Tree tree) {
		viewer = new ContainerCheckedTreeViewer(tree);
		// get AdapterFactoryLabelProvider as LabelProvider for TreeViewer.
		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		viewer.setLabelProvider(labelProvider);
		// add tooltips for quality model descriptions.
		new TreeViewerAttributeTooltip(viewer, QmPackage.eINSTANCE
				.getDescribedElement_Description());
		// set ContentProvider
		viewer.setContentProvider(new QualityModelsTreeViewerContentProvider());
		ResourceSet resourceSet = ((GuidelineWizard) getWizard())
				.getResourceSet();
		viewer.setInput(resourceSet);
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				getWizard().getContainer().updateButtons();
			}

		});
		// Set all checked and expand tree
		viewer.setSubtreeChecked(resourceSet, true);
		viewer.expandAll();
	}

	/** {@inheritDoc} */
	@Override
	public boolean canFlipToNextPage() {
		return (super.canFlipToNextPage() && viewer.getCheckedElements().length != 0);
	}

	/** {@inheritDoc} */
	@Override
	public void setVisible(boolean visible) {
		if (!visible) {
			List<QualityModel> selectedQualityModels = new ArrayList<QualityModel>();
			for (Object o : viewer.getCheckedElements()) {
				if (o instanceof QualityModel) {
					selectedQualityModels.add((QualityModel) o);
				}
			}
			GuidelineWizard wizard = (GuidelineWizard) getWizard();
			wizard.setSelectedQualityModels(selectedQualityModels);
		}
		super.setVisible(visible);
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		adapterFactory.dispose();
		labelProvider.dispose();
		super.dispose();
	}

	/**
	 * Content provider for the {@link ContainerCheckedTreeViewer}.
	 */
	class QualityModelsTreeViewerContentProvider implements
			ITreeContentProvider {

		/** {@inheritDoc} */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof Resource) {
				Resource resource = (Resource) parentElement;
				List<EObject> contents = resource.getContents();
				EObject eObject = contents.get(0);
				QualityModel qualityModel = (QualityModel) eObject;
				return new QualityModel[] { qualityModel };
			}
			return null;
		}

		/** {@inheritDoc} */
		public Object getParent(Object element) {
			// not required
			return null;
		}

		/** {@inheritDoc} */
		public boolean hasChildren(Object element) {
			return (element instanceof Resource);
		}

		/** {@inheritDoc} */
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof ResourceSet) {
				ResourceSet resourceSet = (ResourceSet) inputElement;
				List<Resource> resources = resourceSet.getResources();
				return resources.toArray();
			}
			return null;
		}

		/** {@inheritDoc} */
		public void dispose() {
			// nothing to dispose
		}

		/** {@inheritDoc} */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// not required
		}

	}
}
