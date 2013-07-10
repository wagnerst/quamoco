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

/*--------------------------------------------------------------------------+
 $Id: CustomQmEditor.java 4974 2012-02-17 09:34:10Z lochmann $
 |                                                                          |
 | Copyright 2005-2010 Technische Universitaet Muenchen                     |
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
 +--------------------------------------------------------------------------*/
package de.quamoco.qm.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.common.URIUtils;
import org.eclipse.emf.edapt.history.Release;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.migration.ReleaseUtils;
import org.eclipse.emf.edapt.migration.execution.Migrator;
import org.eclipse.emf.edapt.migration.execution.MigratorRegistry;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.INavigationLocationProvider;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModelResult;
import de.quamoco.qm.editor.action.ShowResultTreeMapAction;
import de.quamoco.qm.editor.pages.EntityHierarchyViewerPane;
import de.quamoco.qm.editor.pages.EntityTableViewerPane;
import de.quamoco.qm.editor.pages.FactorHierarchyViewerPane;
import de.quamoco.qm.editor.pages.FactorTableViewerPane;
import de.quamoco.qm.editor.pages.MeasureTableViewerPane;
import de.quamoco.qm.editor.pages.SelectionViewerPane;
import de.quamoco.qm.presentation.QmEditor;
import de.quamoco.qm.presentation.QmEditorPlugin;
import de.quamoco.qm.provider.QmItemProviderAdapterFactory;
import edu.tum.cs.emf.commons.cache.Cache;
import edu.tum.cs.emf.commons.sections.FeaturePropertySectionBase;
import edu.tum.cs.emf.commons.sections.ILocator;
import edu.tum.cs.emf.commons.validation.ResourceSetValidation;
import edu.tum.cs.emf.commons.validation.Validation;
import edu.tum.cs.emf.commons.validation.view.DefaultValidatedEditor;
import edu.tum.cs.emf.commons.validation.view.IValidatedEditor;

/**
 * Customized quality model editor
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 6F3AFB38C97EFCE342DC02A4ED879282
 */
public class CustomQmEditor extends QmEditor implements
		ITabbedPropertySheetPageContributor, INavigationLocationProvider,
		ILocator {

	/** This is the property sheet page. */
	protected TabbedPropertySheetPage customPropertySheetPage;

	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EContentAdapter problemIndicationAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				switch (notification.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
					showResultTreeMap((Resource) notification.getNotifier());
				case Resource.RESOURCE__ERRORS:
				case Resource.RESOURCE__WARNINGS: {
					Resource resource = (Resource) notification.getNotifier();
					Diagnostic diagnostic = analyzeResourceProblems(resource,
							null);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						resourceToDiagnosticMap.put(resource, diagnostic);
					} else {
						resourceToDiagnosticMap.remove(resource);
					}

					if (updateProblemIndication) {
						getSite().getShell().getDisplay()
								.asyncExec(new Runnable() {
									public void run() {
										updateProblemIndication();
									}
								});
					}
					break;
				}
				}
			} else {
				super.notifyChanged(notification);
			}
		}

		@Override
		protected void setTarget(Resource target) {
			basicSetTarget(target);
		}

		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
		}
	};

	/** {@inheritDoc} */
	@Override
	protected void initializeEditingDomain() {
		// Create an adapter factory that yields item providers.
		//
		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new QmItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		// Create the command stack that will notify this editor as commands are
		// executed.
		//
		BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		//
		commandStack.addCommandStackListener(new CommandStackListener() {
			public void commandStackChanged(final EventObject event) {
				getContainer().getDisplay().asyncExec(new Runnable() {
					public void run() {
						firePropertyChange(IEditorPart.PROP_DIRTY);

						// Try to select the affected objects.
						//
						CommandStack commandStack = (CommandStack) event
								.getSource();
						Command mostRecentCommand = commandStack
								.getMostRecentCommand();
						if (isCreate(mostRecentCommand)
								|| isUndo(commandStack, mostRecentCommand)) {
							setFocus();
							setSelectionToViewer(mostRecentCommand
									.getAffectedObjects());
						}
						if (customPropertySheetPage != null
								&& !customPropertySheetPage.getControl()
										.isDisposed()
								&& customPropertySheetPage.getCurrentTab() != null) {
							customPropertySheetPage.refresh();
						}

						if (isCreate(mostRecentCommand)
								&& !isUndo(commandStack, mostRecentCommand)) {
							enterTextField();
						}
					}

					private boolean isCreate(Command command) {
						if (command == null) {
							return false;
						}
						if (command instanceof CompoundCommand) {
							CompoundCommand compound = (CompoundCommand) command;
							for (Command subCommand : compound.getCommandList()) {
								if (isCreate(subCommand)) {
									return true;
								}
							}
						}
						return command instanceof CreateChildCommand;
					}

					private boolean isUndo(CommandStack commandStack,
							Command command) {
						if (command == null) {
							return false;
						}
						return command == commandStack.getRedoCommand();
					}
				});
			}
		});

		// Create the editing domain with a special command stack.
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,
				commandStack, new HashMap<Resource, Boolean>());
	}

	/** Show the result in a tree map. */
	protected void showResultTreeMap(Resource resource) {
		if (!resource.getContents().isEmpty()
				&& resource.getContents().get(0) instanceof QualityModelResult) {
			EcoreUtil.resolveAll(resource);
			QualityModelResult modelResult = (QualityModelResult) resource
					.getContents().get(0);
			ShowResultTreeMapAction.showResultTreeMap(modelResult, this);
		}
	}

	/** Enter the first text field in the properties view. */
	@SuppressWarnings("unchecked")
	private void enterTextField() {
		Display.getDefault().asyncExec(new Runnable() {

			public void run() {
				if (customPropertySheetPage == null
						|| customPropertySheetPage.getCurrentTab() == null) {
					return;
				}
				for (ISection section : customPropertySheetPage.getCurrentTab()
						.getSections()) {
					if (section instanceof FeaturePropertySectionBase) {
						final FeaturePropertySectionBase featureSection = (FeaturePropertySectionBase) section;
						if (featureSection.getFeature() == QmPackage.Literals.NAMED_ELEMENT__NAME) {
							customPropertySheetPage.setFocus();
							featureSection.setFocus();
						}

						return;
					}
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createModel() {
		final URI resourceURI = EditUIUtil.getURI(getEditorInput());
		Exception exception = null;
		Resource resource = null;

		checkMigration(resourceURI);

		try {
			// Load the resource through the editing domain.
			resource = editingDomain.getResourceSet().getResource(resourceURI,
					true);
		} catch (Exception e) {
			exception = e;
			resource = editingDomain.getResourceSet().getResource(resourceURI,
					false);
		}

		EcoreUtil.resolveAll(getEditingDomain().getResourceSet());

		Diagnostic diagnostic = analyzeResourceProblems(resource, exception);
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			resourceToDiagnosticMap.put(resource,
					analyzeResourceProblems(resource, exception));
		}
		editingDomain.getResourceSet().eAdapters()
				.add(problemIndicationAdapter);
	}

	/** Perform migration of the model if necessary. */
	private void checkMigration(final URI resourceURI) {
		String nsURI = ReleaseUtils.getNamespaceURI(resourceURI);
		final Migrator migrator = MigratorRegistry.getInstance().getMigrator(
				nsURI);
		if (migrator != null) {
			final Release release = migrator.getRelease(resourceURI).iterator()
					.next();
			if (!release.isLatestRelease()) {
				IFile[] files = WorkspaceResourceDialog.openFileSelection(
						Display.getDefault().getActiveShell(), "Select Files",
						"Select the files that need to be migrated together",
						true, new Object[] { URIUtils.getFile(resourceURI) },
						Collections.<ViewerFilter> emptyList());
				if (files.length == 0) {
					return;
				}
				final List<URI> uris = new ArrayList<URI>();
				for (IFile file : files) {
					uris.add(URIUtils.getURI(file));
				}
				IRunnableWithProgress runnable = new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException {
						try {
							ResourceSet resourceSet = migrator.migrateAndLoad(
									uris, release, null, monitor);
							editingDomain.getResourceSet().getResources()
									.addAll(resourceSet.getResources());
						} catch (MigrationException e) {
							throw new InvocationTargetException(e);
						}
					}

				};
				try {
					new ProgressMonitorDialog(Display.getCurrent()
							.getActiveShell()).run(false, false, runnable);
				} catch (InvocationTargetException e) {
					MessageDialog
							.openError(
									Display.getDefault().getActiveShell(),
									"Migration error",
									"An error occured during migration of the model. More information on the error can be found in the error log.");
					QmEditorPlugin.getPlugin().log(e.getCause());
				} catch (InterruptedException e) {
					QmEditorPlugin.getPlugin().log(e);
				}
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void createPages() {
		// Creates the model from the editor input
		createModel();

		// Only creates the other pages if there is something that can be edited
		if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {

			createDefaultTree();

			createFactorHierarchyTree();
			createEntityHierarchyTree();

			createFactorTable();
			createEntityTable();
			createMeasureTable();

			updateProblemIndication();
		}
	}

	private boolean open = true;

	private int selectionPageIndex;

	/** {@inheritDoc} */
	@Override
	protected void handleActivate() {
		super.handleActivate();

		if (open) {
			for (Resource r : getEditingDomain().getResourceSet()
					.getResources()) {
				showResultTreeMap(r);
			}
			open = false;
		}
	}

	/** Create the default tree view. */
	private void createDefaultTree() {
		SelectionViewerPane selectionViewerPane = new SelectionViewerPane(this);
		selectionViewerPane.createControl(getContainer());

		selectionViewer = selectionViewerPane.getViewer();
		createContextMenuFor(selectionViewer);

		selectionPageIndex = addPage(selectionViewerPane.getControl());
		setPageText(selectionPageIndex,
				QmEditorPlugin.INSTANCE.getString("_UI_SelectionPage_label"));
	}

	/** Activates the selection viewer pane */
	public void activateSelectionViewerPane() {
		setActivePage(selectionPageIndex);
	}

	/** Create the tree to show the factor hierarchy. */
	private void createFactorHierarchyTree() {
		FactorHierarchyViewerPane factorHierarchyPane = new FactorHierarchyViewerPane(
				this);

		addViewerPane(factorHierarchyPane, "Factor Hierarchy");
	}

	/** Create the tree to show the entity hierarchy. */
	private void createEntityHierarchyTree() {
		EntityHierarchyViewerPane entityHierarchyPane = new EntityHierarchyViewerPane(
				this);
		addViewerPane(entityHierarchyPane, "Entity Hierarchy");
	}

	/** Create the table to list all factors. */
	private void createFactorTable() {
		FactorTableViewerPane tableViewerPane = new FactorTableViewerPane(this);

		addViewerPane(tableViewerPane, "Factor List");

		createContextMenuFor(tableViewerPane.getViewer().getMainViewer()
				.getQmTableViewer().getDelegateTableViewer());
	}

	/** Create the table to list all entities. */
	private void createEntityTable() {
		EntityTableViewerPane tableViewerPane = new EntityTableViewerPane(this);

		addViewerPane(tableViewerPane, "Entity List");

		createContextMenuFor(tableViewerPane.getViewer().getQmTableViewer()
				.getDelegateTableViewer());
	}

	/** Create the table to list all measures. */
	private void createMeasureTable() {
		MeasureTableViewerPane tableViewerPane = new MeasureTableViewerPane(
				this);

		addViewerPane(tableViewerPane, "Measure List");

		createContextMenuFor(tableViewerPane.getViewer().getQmTableViewer()
				.getDelegateTableViewer());
	}

	/** Adds a viewer pane to the editor view with the specified title. */
	public void addViewerPane(ViewerPane viewerPane, String title) {
		viewerPane.createControl(getContainer());

		int selectionPageIndex = addPage(viewerPane.getControl());
		setPageText(selectionPageIndex, title);
	}

	/** {@inheritDoc} */
	public String getContributorId() {
		return getSite().getId();
	}

	/** {@inheritDoc} */
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		customPropertySheetPage = new TabbedPropertySheetPage(this);
		return customPropertySheetPage;
	}

	/** {@inheritDoc} */
	public INavigationLocation createEmptyNavigationLocation() {
		return createNavigationLocation();
	}

	/** {@inheritDoc} */
	public INavigationLocation createNavigationLocation() {
		return new QmNavigationLocation(CustomQmEditor.this);
	}

	/** {@inheritDoc} */
	@Override
	public void setSelection(ISelection selection) {
		super.setSelection(selection);

		List<String> uris = getSelectedURIs();
		if (!uris.isEmpty()) {
			if (isCreated()) {
				getSite().getPage().getNavigationHistory().markLocation(this);
			}
		}
	}

	/** Checks whether the editor is created. */
	private boolean isCreated() {
		return getPageCount() > 0;
	}

	/** Set selection based on a list of URIs. */
	public void setSelection(List<String> uris) {
		List<Object> elements = new ArrayList<Object>();
		for (String uri : uris) {
			ResourceSet resourceSet = editingDomain.getResourceSet();
			try {
				EObject element = resourceSet.getEObject(URI.createURI(uri),
						true);
				if (element != null) {
					elements.add(element);
				}
			} catch (NullPointerException e) {
				Resource resource = resourceSet.getResource(URI.createURI(uri),
						true);
				if (resource != null) {
					elements.add(resource);
				}
			}
		}

		getViewer().setSelection(new StructuredSelection(elements), true);
	}

	/** Get the identifiers of the selected elements. */
	public List<String> getSelectedURIs() {
		List<String> elements = new ArrayList<String>();
		IStructuredSelection structuredSelection = (IStructuredSelection) getSelection();
		for (Iterator<?> i = structuredSelection.iterator(); i.hasNext();) {
			Object object = i.next();
			if (object instanceof Resource) {
				Resource resource = (Resource) object;
				elements.add(resource.getURI().toString());
			} else if (object instanceof EObject) {
				EObject element = (EObject) object;
				elements.add(EcoreUtil.getURI(element).toString());
			}
		}
		return elements;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public void locate(List values) {
		getViewer().setSelection(new StructuredSelection(values), true);

		int successfullSelections = ((IStructuredSelection) getViewer()
				.getSelection()).size();
		if (successfullSelections < values.size()) {
			if (allOfType(values, Factor.class)) {
				setActivePage(1);
			} else if (allOfType(values, Entity.class)) {
				setActivePage(2);
			} else {
				setActivePage(0);
			}
			getViewer().setSelection(new StructuredSelection(values), true);
		}
	}

	/** Check whether all elements in the list are instances of a certain type. */
	@SuppressWarnings("unchecked")
	private boolean allOfType(List values, Class type) {
		for (Object value : values) {
			if (!type.isInstance(value)) {
				return false;
			}
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	protected void updateProblemIndication() {
		super.updateProblemIndication();

		for (Resource resource : getEditingDomain().getResourceSet()
				.getResources()) {
			new TaskUpdateJob(resource).schedule();
		}

		ResourceSetValidation validation = Validation
				.getValidation(editingDomain.getResourceSet());
		validation.revalidate();
	}

	/** {@inheritDoc} */
	@Override
	public void gotoMarker(IMarker marker) {
		String uri = getURIString(marker);
		if (uri != null) {
			setSelection(Collections.singletonList(uri));
		}
	}

	/** Get the URI of the element to which a marker is referring. */
	private String getURIString(IMarker marker) {
		String uriFragment = marker
				.getAttribute(EValidator.URI_ATTRIBUTE, null);
		IResource resource = marker.getResource();
		return getURIString(resource, uriFragment);
	}

	/** Assemble a URI based on a resource and fragment. */
	private String getURIString(IResource resource, String uriFragment) {
		if (uriFragment == null) {
			return null;
		}
		URI resourceURI = URI.createPlatformResourceURI(resource.getFullPath()
				.toString(), true);
		URI uri = resourceURI.appendFragment(uriFragment);
		return uri.toString();
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		Cache.disposeCaches(getEditingDomain().getResourceSet());
		if (customPropertySheetPage != null) {
			customPropertySheetPage.dispose();
		}
		super.dispose();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class key) {
		if (key == IValidatedEditor.class) {
			return new DefaultValidatedEditor(this, false);
		}
		return super.getAdapter(key);
	}
}