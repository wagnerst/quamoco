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

package de.quamoco.qm.editor.pages;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.CustomLabelProvider;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.util.LabelProviderViewerSorter;
import de.quamoco.qm.provider.QmEditPlugin;
import de.quamoco.qm.provider.QmItemProviderAdapterFactory;
import edu.tum.cs.emf.commons.validation.view.ProblemLabelDecorator;

/**
 * {@link ViewerPane} to display a hierarchy in a {@link TreeViewer}.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: F69C1B5B9FD21A41FD644ED4677A97A1
 */
public abstract class HierarchyViewerPaneBase extends ViewerPane {

	/** The parent editor. */
	protected final CustomQmEditor editor;

	/** Constructor. */
	public HierarchyViewerPaneBase(CustomQmEditor editor, String title) {
		super(editor.getSite().getPage(), editor);
		this.editor = editor;
		setTitle(title);
	}

	/** {@inheritDoc} */
	@Override
	public Viewer createViewer(Composite composite) {
		TreeViewer viewer = new TreeViewer(composite, SWT.MULTI);

		// enable support for showing one element multiple times in the tree
		viewer.setUseHashlookup(true);

		return viewer;
	}

	/** {@inheritDoc} */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);

		initTreeViewer(getViewer());
	}

	/** Initialize the {@link TreeViewer} displaying the hierarchy. */
	protected void initTreeViewer(final TreeViewer treeViewer) {
		ResourceSet resourceSet = editor.getEditingDomain().getResourceSet();
		AdapterFactory adapterFactory = createAdapterFactory();

		// default label provider
		AdapterFactoryLabelProvider aflp = new AdapterFactoryLabelProvider.ColorProvider(
				adapterFactory, treeViewer);
		aflp.setFireLabelUpdateNotifications(true);
		aflp.addListener(new ILabelProviderListener() {

			@Override
			public void labelProviderChanged(
					final LabelProviderChangedEvent event) {
				if (Display.getDefault().getThread() != Thread.currentThread()) {
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							treeViewer.update(event.getElements(), null);
						}
					});
				} else if (!treeViewer.isBusy()) {
					treeViewer.update(event.getElements(), null);
				}
			}
		});

		// show the validation results
		ILabelProvider dlp = new DecoratingLabelProvider(aflp,
				new ProblemLabelDecorator(false));

		// show the different colors
		CustomLabelProvider clp = new CustomLabelProvider(dlp);
		treeViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(clp));

		treeViewer.setInput(resourceSet);

		treeViewer.setSorter(new LabelProviderViewerSorter(clp, false,
				QmPackage.eINSTANCE.getFactor(), QmPackage.eINSTANCE
						.getMeasure(), QmPackage.eINSTANCE.getEntity()));
	}

	/** Create the special {@link AdapterFactory} of this pane. */
	protected AdapterFactory createAdapterFactory() {
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new QmItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return adapterFactory;
	}

	/** {@inheritDoc} */
	@Override
	public void requestActivation() {
		super.requestActivation();
		editor.setCurrentViewerPane(this);
	}

	/** {@inheritDoc} */
	@Override
	public TreeViewer getViewer() {
		return (TreeViewer) super.getViewer();
	}

	/** Helper method to get an image by its key. */
	protected Image getImage(String imageKey) {
		Object image = QmEditPlugin.INSTANCE.getImage(imageKey);
		return ExtendedImageRegistry.INSTANCE.getImage(image);
	}
}
