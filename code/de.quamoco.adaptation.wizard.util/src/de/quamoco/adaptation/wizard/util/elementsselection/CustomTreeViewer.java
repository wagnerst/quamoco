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
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import de.quamoco.adaptation.wizard.util.WizardState;
import de.quamoco.adaptation.wizard.util.elementsselection.listener.ICheckedElementsChangedListener;
import de.quamoco.adaptation.wizard.util.elementsselection.listener.ICustomTreeRefreshListener;
import de.quamoco.qm.AnnotatedElement;

/**
 * Implementation of {@link ICustomTreeViewer}.<br><br>
 * 
 * The checked and unchecked elements (of type TypeParameter) are maintained in the two
 * lists {@link #checkedElements} and {@link #uncheckedElements} which are only modified
 * by the two methods {@link #doUpdateElements(Collection, boolean) doUpdateElements} and {@link #inputChanged(Object, Object) inputChanged}.<br><br>
 * 
 * The implementation is straight-forward and has the two private classes 
 * {@link CustomTreeViewer.CheckStateProvider CheckStateProvider} and {@link CustomTreeViewer.CheckStateListener CheckStateListener} 
 * which maintain the lists of checked and unchecked elements.
 * 
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 88930359487D179F48435E792055FBB6
 */
public class CustomTreeViewer extends CheckboxTreeViewer implements ICustomTreeViewer {

	/** The state of the wizard */
	private WizardState wizardState;
		
	/**
	 * A list of {@link ICustomTreeRefreshListener} that are notified when
	 * {@link #refresh(Object)} or {@link #refresh(Object, boolean)} are called.
	 */
	private ListenerList refreshListener = new ListenerList();
	
	/**
	 * A list of {@link ICheckedElementsChangedListener} that are notified
	 * when the list of selected elements {@link #checkedElements} has changed.
	 */
	private ListenerList selectedElementsChangedListener = new ListenerList();
	
	/**
	 * Calls the super constructor and calls {@link #initViewer()}.
	 * @param parent the SWT parent
	 * @param style the style of this {@link TreeViewer}
	 * @param checkedElements the set that manages checked elements
	 * @param uncheckedElements the set that manages unchecked elements
	 */
	public CustomTreeViewer(Composite parent, int style, WizardState wizardState) {
		super(parent, style);
		// required to support multiple equal model elements		
		setUseHashlookup(true); 
		this.wizardState = wizardState;
		initViewer();		
	}
	
	/**
	 * Inits the viewer
	 */
	protected void initViewer() {
		setUseHashlookup(true);
		setAutoExpandLevel(ALL_LEVELS);
		
		// Set the ICheckStateProvider
		setCheckStateProvider(new CheckStateProvider());
		
		// Add ICheckStateListener
		addCheckStateListener(new CheckStateListener());
		
	}
	
	/** {@inheritDoc} */
	@Override
	public void checkAllVisible() {
		Set<AnnotatedElement> visibleElements = getAllVisibleChildren(getTree().getItems());
		doUpdateElements(visibleElements, true);
	}
	
	/** {@inheritDoc} */
	@Override
	public void uncheckAllVisible() {
		Set<AnnotatedElement> visibleElements = getAllVisibleChildren(getTree().getItems());
		doUpdateElements(visibleElements, false);
	}
	
	/** {@inheritDoc} */
	@Override
	public void setAllVisibleChildrenChecked(boolean state) {
		Item[] selection = this.getSelection(this.getControl());
		Set<AnnotatedElement> elementsToUpdate = getAllVisibleChildren((TreeItem[]) selection);

		doUpdateElements(elementsToUpdate, state);	
	}

	/** {@inheritDoc} */
	@Override
	public void setVisibleParentsChecked(boolean state) {
		Item[] selection = this.getSelection(this.getControl());
		Set<AnnotatedElement> elementsToUpdate = new HashSet<AnnotatedElement>();
		
		// Iterate over the selected tree items and add all parents
		for (int i = 0; i < selection.length; i++) {
			TreeItem treeItem = (TreeItem) selection[i];
			while (treeItem != null) {
				elementsToUpdate.add((AnnotatedElement) treeItem.getData());
				treeItem = treeItem.getParentItem();
			}			
		}	

		doUpdateElements(elementsToUpdate, state);
	}

	/**
	 * Forwards the information to {@link #wizardState} and then
	 * refreshs itself and calls {@link #fireCheckedElementsChanged()}.
	 * @param element the element which shall be updated
	 * @param checked the new check state
	 */
	private void doUpdateElement(AnnotatedElement element, boolean checked) {
		if (checked) {
			wizardState.select(element);
		} else {
			wizardState.unselect(element);
		}		
		refresh();
		fireCheckedElementsChanged();	
	}
	
	/**
	 * Forwards the information to {@link #wizardState} and then
	 * refreshs itself and calls {@link #fireCheckedElementsChanged()}.
	 * @param elements the elements which shall be updated
	 * @param checked the new check state
	 */
	private void doUpdateElements(Collection<AnnotatedElement> elements, boolean checked) {		
		if (checked) {
			wizardState.select(elements);
		} else {
			wizardState.unselect(elements);
		}		
		refresh();
		fireCheckedElementsChanged();		
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean hasVisibleElements() {
		return getTree().getItems().length > 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean areAllVisibleElementsChecked() {
		Set<AnnotatedElement> visibleElements = getAllVisibleChildren(getTree().getItems());
		return wizardState.areSelected(visibleElements);
	}
		
	/**
	 * Collects recursively all children which are in 
	 * subtree described by the passed {@link TreeItem}s.
	 * @param items the {@link TreeItem} describing the subtree
	 * @return all instances all children of the subtree
	 */
	private Set<AnnotatedElement> getAllVisibleChildren(TreeItem[] items) {
		Set<AnnotatedElement> visibleChildren = new HashSet<AnnotatedElement>();
		for (TreeItem treeItem : items) {
			if (treeItem.getData() instanceof AnnotatedElement) { 
				visibleChildren.add((AnnotatedElement) treeItem.getData()); 
			}
			visibleChildren.addAll(getAllVisibleChildren(treeItem.getItems()));
		}
		return visibleChildren;
	}
	
	/** 
	 * Reset everything if input has changed. 
	 */
	@Override
	protected void inputChanged(Object input, Object oldInput) {
		super.inputChanged(input, oldInput);	
	}
		
	/*
	 * Refresh handling
	 */
	
	/**
	 * Expands the whole tree when refreshing
	 */
	@Override
	protected void preservingSelection(Runnable updateCode) {
		super.preservingSelection(updateCode);
		expandAll();
	}
	
	/**
	 * Overrides {@link StructuredViewer#refresh(Object)} to implement refresh listeners.
	 */
	@Override
	public void refresh(final Object element) {
		super.refresh(element);
		fireRefresh();
	}
	
	/**
	 * Overrides {@link StructuredViewer#refresh(Object, boolean)} to implement refresh listeners.
	 */
	@Override
	public void refresh(final Object element, final boolean updateLabels) {
		super.refresh(element, updateLabels);
		fireRefresh();
	}
	
	/**
	 * Notifies all registered {@link ICustomTreeRefreshListener} that the tree has been refreshed
	 */
	protected void fireRefresh() {
		// Notify all refresh listener
		for (Object object : refreshListener.getListeners()) {
			final ICustomTreeRefreshListener listener = (ICustomTreeRefreshListener) object;
			SafeRunnable.run(new SafeRunnable() {
                public void run() {
                	listener.treeRefreshed(CustomTreeViewer.this);
                }
            });
		}    
	}
	
	/** {@inheritDoc} */
	public void addRefreshListener(ICustomTreeRefreshListener listener) {		
		refreshListener.add(listener);
	}
	
	/** {@inheritDoc} */
	public void removeRefreshListener(ICustomTreeRefreshListener listener) {
		refreshListener.remove(listener);
	}

	/*
	 * Selected Elements changed handling
	 */
	
	/**
	 * Notifies all registered {@link ICheckedElementsChangedListener} that the set of
	 * checked elements has been changed
	 */
	protected void fireCheckedElementsChanged() {
		// Notify all listener
		for (Object object : selectedElementsChangedListener.getListeners()) {
			final ICheckedElementsChangedListener listener = (ICheckedElementsChangedListener) object;
			SafeRunnable.run(new SafeRunnable() {
				public void run() {
					listener.checkedElementsChanged(CustomTreeViewer.this);
				}
			});
		} 			  
	}
	
	/** {@inheritDoc} */
	public void addCheckedElementsChangedListener(ICheckedElementsChangedListener listener) {
		selectedElementsChangedListener.add(listener);
	}
	
	/** {@inheritDoc} */
	public void removeCheckedElementsChangedListener(ICheckedElementsChangedListener listener) {
		selectedElementsChangedListener.remove(listener);
	}
	
	/**
	 * Has to be overriden here because {@link CheckboxTreeViewer} asummes that a model
	 * element occurs only once in a tree. Here it might happen that the same model 
	 * element occurs n times in the tree.
	 */
	@Override
    public boolean setChecked(Object element, boolean state) {
		boolean result = false;
        Assert.isNotNull(element);
        Widget[] widgets = findItems(element);
        for (int i = 0; i < widgets.length; i++) {
        	if (widgets[i] instanceof TreeItem) {
                ((TreeItem) widgets[i]).setChecked(state);
                result = true;
            }
		}
        return result;
    }
    
	/**
	 * An {@link ICheckStateProvider} for usage with this tree.
	 * @author Franz Becker
	 */
	private class CheckStateProvider implements ICheckStateProvider {

		@Override
		public boolean isChecked(Object element) {
			return wizardState.isSelected(element);
		}

		@Override
		public boolean isGrayed(Object element) {
			return false; // there are no grayed elements by design
		}
		
	}
	
	/**
	 * Handles the update of the lists of checked and unchecked
	 * elements triggered by user interaction. 
	 * @author Franz Becker
	 */
	private class CheckStateListener implements ICheckStateListener {

		@Override
		public void checkStateChanged(CheckStateChangedEvent event) {
			if (event.getElement() instanceof AnnotatedElement) {
				doUpdateElement((AnnotatedElement) event.getElement(), event.getChecked());
			}			
		}
		
	}

}
