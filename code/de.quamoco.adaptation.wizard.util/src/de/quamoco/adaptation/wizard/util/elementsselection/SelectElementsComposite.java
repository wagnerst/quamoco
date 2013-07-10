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

package de.quamoco.adaptation.wizard.util.elementsselection;

import java.util.Collection;
import java.util.List;

import org.conqat.lib.commons.collections.CollectionUtils;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontDecorator;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

import de.quamoco.adaptation.model.AdaptationElement;
import de.quamoco.adaptation.model.AdaptationModel;
import de.quamoco.adaptation.model.ElementHierarchy;
import de.quamoco.adaptation.util.qm.Activator;
import de.quamoco.adaptation.util.swt.SWTUtil;
import de.quamoco.adaptation.wizard.util.WizardState;
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
 * 
 * @author Franz Becker
 *
 * @ConQAT.Rating YELLOW Hash: 0095840409F4A7DE1B1DCE2FC841B8AC
 */
public class SelectElementsComposite extends Composite {
	
	protected AdaptationElement adaptationElement;
	protected WizardState wizardState;
	
	protected CustomFilteredTree selectElementsFilteredTree;
	protected FilteredTree showSelectionFilteredTree;

	protected CustomTreeViewer selectElementsTreeViewer;
	protected TreeViewer showSelectionTreeViewer;
	
	protected String selectElementGroupCaption = "";
	protected String showSelectionGroupCaption = "";
	protected Collection<AnnotatedElement> defaultCheckedElements = CollectionUtils.emptySet();
	
	/**
	 * Represents the currently selected {@link ElementHierarchy}. It can be set using the
	 * combo box by the user if there is more than one hierarchy available in the {@link AdaptationModel}.
	 * If there is only one hierarchy it will be set to this one.
	 * Read by {@link #getHierarchicalQualityModelContentProvider()}
	 */
	protected ElementHierarchy selectedElementHierarchy = null;

	

	/**
	 * Convenience constructor, calls {@link #SelectElementsComposite(Composite parent, int style)}
	 * with {@link SWT#NONE} as style.
	 * @param parent the parent of this composite
	 * @param adaptationElement the {@link ElementsSelectionPage} of the currently used {@link AdaptationModel}
	 * @param wizardState the state of the wizard
	 */
	public SelectElementsComposite(Composite parent, AdaptationElement adaptationElement, WizardState wizardState) {
		this(parent, SWT.NONE, adaptationElement, wizardState);
	}	
	
	/**
	 * 
	 * @param parent the parent of this composite
	 * @param style the style of this composite
	 * @param adaptationElement the {@link AdaptationElement} of the currently used {@link AdaptationModel}
	 * @param wizardState the state of the wizard
	 */
	public SelectElementsComposite(Composite parent, int style, AdaptationElement adaptationElement, WizardState wizardState) {
		super(parent, style);
		
		this.adaptationElement = adaptationElement;
		this.wizardState = wizardState;
				
		setLayout(new GridLayout(2, true));
		createSelectElementsGroup();
		createShowSelectionGroup();
		createButtonGroup();
		createContextMenus();
//		customReset();
	}

	protected IBaseLabelProvider getQualityModelLabelProvider() {
		AdapterFactoryLabelProvider undecoratedLabelProvider = new AdapterFactoryLabelProvider(new QmItemProviderAdapterFactory());
		PreselectionDecorator preselectionDecorator = new PreselectionDecorator();
		return new DecoratingLabelProvider(undecoratedLabelProvider, preselectionDecorator);		 
	}
	
	protected AdapterFactoryContentProvider getFlatQualityModelContentProvider() {
		return new AdapterFactoryContentProvider(new CustomQmItemProviderAdapterFactory(null));
	}
	
	protected AdapterFactoryContentProvider getHierarchicalQualityModelContentProvider() {
		return new AdapterFactoryContentProvider(new CustomQmItemProviderAdapterFactory(selectedElementHierarchy));
	}
	
	protected void createSelectElementsGroup() {
		/*
		 * (1) Create a group as container
		 * (2) Create a FilteredContainerCheckedTreeView to display the quality aspects
		 * (3) Configure it
		 * (4) Add a context menu to the tree viewer
		 * (5) Add listeners
		 * (6) Add Drag & Drop support
		 */
		// (1) Create treeGroup
		Group treeGroup = SWTUtil.createGroup(this, selectElementGroupCaption); 
		treeGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));		

		// (2) Create the filtered tree viewer on the left side
		PatternFilter patternFilter = new NamedElementPatternFilter();
		selectElementsFilteredTree = new CustomFilteredTree(treeGroup, patternFilter, wizardState);

		// (3) Configuration
		selectElementsTreeViewer = selectElementsFilteredTree.getCustomTreeViewer();
		selectElementsTreeViewer.setLabelProvider(getQualityModelLabelProvider());
		selectElementsTreeViewer.setContentProvider(getFlatQualityModelContentProvider());
		new TreeViewerAttributeTooltip(selectElementsTreeViewer, QmPackage.eINSTANCE.getDescribedElement_Description());  // tooltip
		selectElementsTreeViewer.setSorter(new ViewerSorter());
	}
	
	protected void createShowSelectionGroup() {
		/* 
		 * (1) Create a group as container
		 * (2) Create a FilteredTree to display the selected quality aspects
		 * (3) Configure it 
		 * (4) Add a context menu to the tree viewer
		 * (5) Add filters
		 * (6) Add Drag & Drop support
		 */
		// (1) create selectionTreeGroup
		Group selectionTreeGroup = SWTUtil.createGroup(this, showSelectionGroupCaption);
		selectionTreeGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// (2) Create the filtered tree viewer on the right side
		PatternFilter patternFilter = new NamedElementPatternFilter();
		showSelectionFilteredTree = new FilteredTree(selectionTreeGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, patternFilter, true);

		// (3)Configuration
		showSelectionTreeViewer = showSelectionFilteredTree.getViewer();
		showSelectionTreeViewer.setLabelProvider(getQualityModelLabelProvider());
		showSelectionTreeViewer.setContentProvider(getFlatQualityModelContentProvider());
		new TreeViewerAttributeTooltip(showSelectionTreeViewer, QmPackage.eINSTANCE.getDescribedElement_Description()); // tooltip
		showSelectionTreeViewer.setSorter(new ViewerSorter());
	}
	
	protected void createButtonGroup() {
		/* [IV]
		 * Buttons hierarchy, check all and reset **********************************************************************
		 */
		Composite buttonComposite = new Composite(this, SWT.NONE);	
		buttonComposite.setLayout(new GridLayout(1, true));		

		final ToolBar toolBar = new ToolBar(buttonComposite, SWT.NONE);
		
		/*
		 * Toggle button flat / hierarchy. Viewed only when there is at least one hierarchy 
		 * defined in the adaptation model for this page!
		 */
		if (adaptationElement.getElementHierarchies().size() > 0) {
			final ToolItem toggleHierarchy = new ToolItem(toolBar, SWT.CHECK);
			toggleHierarchy.setImage(Activator.getImageDescriptor("icons/toggle_hierarchy.gif").createImage());
			toggleHierarchy.setToolTipText("Switch between flat and hierarchical view");
			toggleHierarchy.addSelectionListener(new SelectionAdapter() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					if (toggleHierarchy.getSelection()) { // => hierarchy view
						selectElementsTreeViewer.setContentProvider(getHierarchicalQualityModelContentProvider());
						showSelectionTreeViewer.setContentProvider(getHierarchicalQualityModelContentProvider());
						showSelectionTreeViewer.expandAll();
					} else { // => flat view
						selectElementsTreeViewer.setContentProvider(getFlatQualityModelContentProvider());
						showSelectionTreeViewer.setContentProvider(getFlatQualityModelContentProvider());
						showSelectionTreeViewer.expandAll();
					}
				}

			});			
			new ToolItem(toolBar, SWT.SEPARATOR);

			/*
			 * Show combo box to allow between the hierarchies and preselect the first one.
			 */			
			final ComboViewer dropDownHierarchy = new ComboViewer(buttonComposite);
			dropDownHierarchy.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return ((ElementHierarchy) element).getLabel();
				}					
			});
			dropDownHierarchy.setContentProvider(new AbstractContentProvider() {					
				@Override
				public Object[] getElements(Object inputElement) {
					return ((List<?>) inputElement).toArray();
				}
			});
			dropDownHierarchy.setInput(adaptationElement.getElementHierarchies());

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
					selectedElementHierarchy = (ElementHierarchy) selection.getFirstElement();
					if (toggleHierarchy.getSelection()) { // update when necessary
						selectElementsTreeViewer.setContentProvider(getHierarchicalQualityModelContentProvider());
						showSelectionTreeViewer.setContentProvider(getHierarchicalQualityModelContentProvider());
						showSelectionTreeViewer.expandAll();
					}
				}
			});

			// Toggle status according to the showHierarchyByDefault value in the adaptation model		
			toggleHierarchy.setSelection(adaptationElement.isShowHierarchyByDefault());
			dropDownHierarchy.setSelection(new StructuredSelection(adaptationElement.getElementHierarchies().get(0)));
			dropDownHierarchy.getControl().setVisible(adaptationElement.isShowHierarchyByDefault());
		}		
		
		/*
		 * Select all button, has an selection listener that reacts upon click,
		 * and another lister that unchecks it, if not all elements are selected in the view
		 */
		final ToolItem selectAllButton = new ToolItem(toolBar, SWT.CHECK);
		selectAllButton.setImage(Activator.getImageDescriptor("icons/select_all.gif").createImage());
		selectAllButton.setToolTipText("Select all");

		SelectAllButtonCustomTreeListener selectAllButtonCustomTreeListener = new SelectAllButtonCustomTreeListener(selectAllButton);
		selectElementsTreeViewer.addCheckedElementsChangedListener(selectAllButtonCustomTreeListener);		
		selectElementsTreeViewer.addRefreshListener(selectAllButtonCustomTreeListener);

		// Selection listener that reacts upon button click
		selectAllButton.addSelectionListener(new SelectionAdapter() { 
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (selectAllButton.getSelection()) {
					selectElementsTreeViewer.checkAllVisible();
				} else {
					selectElementsTreeViewer.uncheckAllVisible();
				}
			}
			
		});

		new ToolItem(toolBar, SWT.SEPARATOR);
		
		// Reset button
		// TODO implement with wizard state
//		ToolItem resetButton = new ToolItem(toolBar, SWT.PUSH);
//		resetButton.setEnabled(false);
//		resetButton.setImage(Activator.getImageDescriptor("icons/reset.gif").createImage());
//		resetButton.setToolTipText("Reset");
//		resetButton.addSelectionListener(new SelectionAdapter() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent e) {
////				// Clear filter text
////				selectElementsFilteredTree.getFilterControl().setText("");
////				showSelectionFilteredTree.getFilterControl().setText("");
////
////				// Resets the selectElements viewer
////				selectElementsTreeViewer.uncheckAll();
////				
////				// Restore the default checking
////				selectElementsTreeViewer.setChecked(defaultCheckedElements, true);
////				
////				// Refresh showSelectionTreeViewer since check status may have changed
////				showSelectionTreeViewer.refresh();
////				
////				// Expand both viewer
////				selectElementsTreeViewer.expandAll();
////				showSelectionTreeViewer.expandAll();
//			}
//			
//		});

	}	
	
 	/**
	 * Adds a context menu (defined by {@link SelectElementsTreeMenuListener} to 
	 * the {@link #selectElementsFilteredTree}.
	 */
	protected void createContextMenus() {
		MenuManager menuManager = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuManager.setRemoveAllWhenShown(true);

		//register listener to fill the menu when about to be shown
		menuManager.addMenuListener(new SelectElementsTreeMenuListener(selectElementsFilteredTree));

		//create menu
		Menu menu = menuManager.createContextMenu(selectElementsTreeViewer.getTree());
		selectElementsTreeViewer.getTree().setMenu(menu);
	}
	
	public void setLeftInput(TransientRoot leftInput) {
		selectElementsTreeViewer.setInput(leftInput);
		selectElementsTreeViewer.expandAll();
	}
	
	public void setRightInput(TransientRoot rightInput) {
		showSelectionTreeViewer.setInput(rightInput);
		showSelectionTreeViewer.expandAll();
	}
	
	public CustomTreeViewer getSelectElementsTreeViewer() {
		return selectElementsTreeViewer;
	}
	
	public TreeViewer getShowSelectionTreeViewer() {
		return showSelectionTreeViewer;
	}

	/**
	 * A 
	 * @author Franz Becker
	 *
	 */
	// TODO check if good solution
	protected class PreselectionDecorator extends BaseLabelProvider implements ILabelDecorator, IFontDecorator, IColorProvider {

		/**
		 * Defines whether elements that are a "hit" of the search filter shall be decorated
		 * or not.
		 */
		private final boolean DECORATE_SEARCH_ENTRIES = true;
		
		@Override
		public Font decorateFont(Object element) {			
			if (defaultCheckedElements.contains(element)) {
				return JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT);
			}
			return null;
		}
		
		@Override
		public Color getBackground(Object element) {
			// TODO never called
			/*
			 * Instead of whole background one could use IStyledLabelProvider for styling only the matched area
			 */
			if (DECORATE_SEARCH_ENTRIES) {
				String filterText = selectElementsFilteredTree.getFilterControl().getText();
				if (filterText != null && !filterText.isEmpty()) {
					NamedElementPatternFilter patternFilter = (NamedElementPatternFilter) selectElementsFilteredTree.getPatternFilter();
					if (patternFilter.isLeafMatch(selectElementsTreeViewer, element)) {
						return JFaceResources.getColorRegistry().get("YELLOW");
					}
				}
			}	
			
			return null;
		}

		@Override
		public Color getForeground(Object element) {
			return null;
		}		
		
		@Override
		public Image decorateImage(Image image, Object element) {			
			return null;
		}

		@Override
		public String decorateText(String text, Object element) {
			return null;
		}

	}

}
