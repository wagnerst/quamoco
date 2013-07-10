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

package de.quamoco.adaptation.wizard.util.preselection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.util.qm.Activator;
import de.quamoco.adaptation.util.swt.SWTUtil;
import de.quamoco.adaptation.wizard.util.WizardState;
import de.quamoco.adaptation.wizard.util.elementsselection.CustomFilteredTree;
import de.quamoco.adaptation.wizard.util.elementsselection.CustomTreeViewer;
import de.quamoco.adaptation.wizard.util.elementsselection.filter.NamedElementPatternFilter;
import de.quamoco.adaptation.wizard.util.elementsselection.listener.SelectAllButtonCustomTreeListener;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.CustomQmItemProviderAdapterFactory;
import de.quamoco.adaptation.wizard.util.elementsselection.provider.TransientRoot;
import de.quamoco.adaptation.wizard.util.modelattributes.provider.AbstractContentProvider;
import de.quamoco.qm.AnnotatedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.provider.QmItemProviderAdapterFactory;
import edu.tum.cs.emf.commons.tooltips.TreeViewerAttributeTooltip;

/**
 * Offers an dialog where the user can select / unselect elements that
 * are affected by another one of his actions.
 * @author Franz Becker
 * @levd.rating GREEN Hash: DF9AF34624AEDFFF22D8864A85471C96
 */
public class PreselectionDialog extends Dialog {

	/** The title of the dialog. */
	private String title = "Edit affected elements";

	/** The message to display. */
	private String message;
	
	/** The OK button, referenc used for setting focus. */
	private Button okButton;
	
	/** The affected elements that shall be shown in the tree viewer. */
	private Set<AnnotatedElement> affectedElements;
	
	/** The state of the wizard. */
	private WizardState wizardState;

	/**
	 * Calls the super constructor and initializes instance variables.
	 * @param parentShell the shell that shall be parent
	 * @param wizardState the state of the wizard
	 * @param editedElements the elements that were edited originally
	 * @param affectedElements the elements that are affected by the editedElements
	 * @param selected true if editedElements were selected, false if they were unselected
	 */
	public PreselectionDialog(Shell parentShell, WizardState wizardState, 
			Collection<AnnotatedElement> editedElements, Set<AnnotatedElement> affectedElements, boolean selected) {
		super(parentShell);		
		this.wizardState = wizardState;
		this.affectedElements = affectedElements;			
		
		// Determine the message that shall be presented to the user.
		String selectedString = selected ? "selected" : "unselected";
		int numberOfEditedElements = editedElements.size();
		this.message = "You " + selectedString + " ";
		if (numberOfEditedElements == 1) {
			AnnotatedElement element = editedElements.iterator().next();
			this.message += "\"" + element.getQualifiedName() + "\"";
		} else { // > 1
			this.message += numberOfEditedElements + " elements";
		}
		this.message += ".\nThe following elements should therefore also be " + selectedString + ":";		
	}

	/**
	 * We want this dialog to be resizable.
	 */
	@Override
	protected boolean isResizable() {
		return true;
	}

	/** Just creates an OK Button. */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
	}

	/**
	 * Creates the message, the tree viewer and the select all button.
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		// Message
		Label label = new Label(composite, SWT.WRAP);
		label.setText(message);
		label.setFont(parent.getFont());
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		// TreeViewer
		Group treeGroup = SWTUtil.createGroup(composite, ""); 
		treeGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));	
		CustomFilteredTree filteredTree = new CustomFilteredTree(treeGroup, new NamedElementPatternFilter(), wizardState);
		filteredTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		final CustomTreeViewer treeViewer = filteredTree.getCustomTreeViewer();
		treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(new QmItemProviderAdapterFactory()));
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(new CustomQmItemProviderAdapterFactory(null)));
		new TreeViewerAttributeTooltip(treeViewer, QmPackage.eINSTANCE.getDescribedElement_Description());  // tooltip
		treeViewer.setSorter(new ViewerSorter());
		treeViewer.setInput(new TransientRoot(null, null) {
			@Override
			public Collection<AnnotatedElement> getReferencedElements() {
				return new LinkedList<AnnotatedElement>(affectedElements);
			}			
		});		
		
		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		toolBar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		
		// Hierarchies
		List<ElementHierarchy> hierarchies = getHierarchies();
		if (hierarchies.size() > 0) {
			final ToolItem toggleHierarchy = new ToolItem(toolBar, SWT.CHECK);
			final ComboViewer dropDownHierarchy = new ComboViewer(composite);
			toggleHierarchy.setImage(Activator.getImageDescriptor("icons/toggle_hierarchy.gif").createImage());
			toggleHierarchy.setToolTipText("Switch between flat and hierarchical view");
			toggleHierarchy.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (toggleHierarchy.getSelection()) { // => hierarchy view
						ElementHierarchy hierarchy = (ElementHierarchy) ((IStructuredSelection) dropDownHierarchy.getSelection()).getFirstElement();
						hierarchyUpdated(treeViewer, hierarchy);
					} else { // => flat view
						hierarchyUpdated(treeViewer, null);
					}
				}

			});			
			new ToolItem(toolBar, SWT.SEPARATOR);

			/*
			 * Show combo box to allow between the hierarchies and preselect the first one.
			 */			
			
			dropDownHierarchy.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					ElementHierarchy hierarchy = (ElementHierarchy) element;
					String elementName = hierarchy.getAdaptationElement().getElementClass().getName();
					return elementName + " - " + hierarchy.getLabel();
				}					
			});
			dropDownHierarchy.setContentProvider(new AbstractContentProvider() {					
				@Override
				public Object[] getElements(Object inputElement) {
					return ((List<?>) inputElement).toArray();
				}
			});
			dropDownHierarchy.setInput(hierarchies);

			// Show only when button is active
			toggleHierarchy.addSelectionListener(new SelectionAdapter() {					
				@Override
				public void widgetSelected(SelectionEvent e) {
					dropDownHierarchy.getControl().setVisible(toggleHierarchy.getSelection());
				}
			});

			// set selectedElementHierarchy when modified and update view
			dropDownHierarchy.addSelectionChangedListener(new ISelectionChangedListener() {					
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					IStructuredSelection selection = (IStructuredSelection) event.getSelection();
					if (toggleHierarchy.getSelection()) { // update when necessary
						hierarchyUpdated(treeViewer, (ElementHierarchy) selection.getFirstElement());
					}
				}
			});

			// Set default status	
			toggleHierarchy.setSelection(false);
			dropDownHierarchy.setSelection(new StructuredSelection(hierarchies.get(0)));
			dropDownHierarchy.getControl().setVisible(false);
		}	
		
		// Select All Button		
		final ToolItem selectAllButton = new ToolItem(toolBar, SWT.CHECK);
		selectAllButton.setImage(Activator.getImageDescriptor("icons/select_all.gif").createImage());
		selectAllButton.setToolTipText("Select all");
		selectAllButton.setSelection(treeViewer.areAllVisibleElementsChecked());
		SelectAllButtonCustomTreeListener selectAllButtonCustomTreeListener = new SelectAllButtonCustomTreeListener(selectAllButton);
		treeViewer.addCheckedElementsChangedListener(selectAllButtonCustomTreeListener);		
		treeViewer.addRefreshListener(selectAllButtonCustomTreeListener);
		// Selection listener that reacts upon button click
		selectAllButton.addSelectionListener(new SelectionAdapter() { 			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectAllButton.getSelection()) {
					treeViewer.checkAllVisible();
				} else {
					treeViewer.uncheckAllVisible();
				}
				treeViewer.refresh();				
				// set focus to ok button
				okButton.setFocus();
			}			
		});
		// select everything by default
		selectAllButton.setSelection(true);
		treeViewer.checkAllVisible();
		treeViewer.refresh();
				
		applyDialogFont(composite);
		return composite;
	}
	
	/**
	 * Helper method that sets the content provider new and expands the treeViewer
	 * @param treeViewer the tree viewer
	 * @param elementHierarchy the selected hierarchy
	 */
	private void hierarchyUpdated(CustomTreeViewer treeViewer, ElementHierarchy elementHierarchy) {
		treeViewer.setContentProvider(new AdapterFactoryContentProvider(new CustomQmItemProviderAdapterFactory(elementHierarchy)));
		treeViewer.expandAll();
	}
	
	/**
	 * @return all available {@link ElementHierarchy}s defined in the adapatation model.
	 */
	private List<ElementHierarchy> getHierarchies() {
		List<ElementHierarchy> result = new LinkedList<ElementHierarchy>();
		for (AdaptationElement adaptationElement : wizardState.getAdaptationElements()) {
			result.addAll(adaptationElement.getElementHierarchies());
		}
		return result;
	}
	
	/** Sets the title of the dialog. */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (title != null) {
			shell.setText(title);
		}
	}

}
