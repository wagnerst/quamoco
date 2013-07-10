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

package de.quamoco.qm.incubator;

import java.util.List;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IColorDecorator;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;

/**
 * A generic view to display a hierarchy based on a metamodel reference.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Rev: 21152
 */
public class HierarchyViewBase extends ViewPart {
	
	/**
	 * Metamodel reference to access children from a certain element
	 */
	private EReference childrenReference;
	
	/**
	 * Tree viewer to display the hierarchy
	 */
	private TreeViewer treeViewer;

	/**
	 * Element on which the hierarchy is currently focused
	 */
	private EObject element;

	/**
	 * Attached viewer to which double click are propagated
	 */
	private IViewerProvider viewerProvider;

	/**
	 * Constructor
	 */
	public HierarchyViewBase(EReference childrenReference) {
		this.childrenReference = childrenReference;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		treeViewer = new TreeViewer(parent, SWT.NULL);
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		
	    ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		treeViewer.setContentProvider(new ContentProvider());
		ILabelProvider labelProvider = new DecoratingLabelProvider(new AdapterFactoryLabelProvider(adapterFactory), new ColorDecorator());
		treeViewer.setLabelProvider(labelProvider);
		
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			/**
			 * {@inheritDoc}
			 */
			public void doubleClick(DoubleClickEvent event) {
				if(viewerProvider != null) {
					IStructuredSelection structuredSelection = (IStructuredSelection) event.getSelection();
					viewerProvider.getViewer().setSelection(new StructuredSelection(structuredSelection.getFirstElement()), true);
				}
			}
			
		});
	}
	
	/**
	 * Get the root element for the element on which the hierarchy is focused.
	 */
	private EObject getRoot() {
		EObject root = element;
		while(getParent(root) != null) {
			root = getParent(root);
		}
		return root;
	}
	
	/**
	 * Get the children of an element w.r.t to the hierarchy.
	 */
	@SuppressWarnings("unchecked")
	private List<EObject> getChildren(EObject element) {
		return (List<EObject>) element.eGet(childrenReference);
	}
	
	/**
	 * Get the parent of an element w.r.t. to the hierarchy.
	 */
	private EObject getParent(EObject element) {
		EReference parentReference = childrenReference.getEOpposite();
		return (EObject) element.eGet(parentReference);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}
	
	/**
	 * Set the element based on which the hierarchy should be displayed as well
	 * as the viewer to which double clicks should be propagated.
	 */
	public void setInput(EObject element, IViewerProvider viewerProvider) {
		this.element = element;
		this.viewerProvider = viewerProvider;
		
		treeViewer.setInput(new Object());
		treeViewer.setSelection(new StructuredSelection(element), true);
	}

	/**
	 * Content provider to display the hierarchy
	 */
	public class ContentProvider implements ITreeContentProvider {

		/**
		 * {@inheritDoc}
		 */
		public Object[] getChildren(Object parentElement) {
			EObject eObject = (EObject) parentElement;
			return HierarchyViewBase.this.getChildren(eObject).toArray();
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getParent(Object element) {
			EObject eObject = (EObject) element;
			return HierarchyViewBase.this.getParent(eObject);
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean hasChildren(Object element) {
			EObject eObject = (EObject) element;
			return !HierarchyViewBase.this.getChildren(eObject).isEmpty();
		}

		/**
		 * {@inheritDoc}
		 */
		public Object[] getElements(Object inputElement) {
			return new Object[]{getRoot()};
		}

		/**
		 * {@inheritDoc}
		 */
		public void dispose() {
			// not required
		}

		/**
		 * {@inheritDoc}
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// not required
		}
	}

	/**
	 * Decorator to highlight element on which the hierarchy is focused.
	 */
	public class ColorDecorator implements ILabelDecorator, IColorDecorator {

		/**
		 * Red color
		 */
		private Color red = new Color(Display.getDefault(), new RGB(255, 0, 0));

		/**
		 * {@inheritDoc}
		 */
		public Image decorateImage(Image image, Object element) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public String decorateText(String text, Object element) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public void addListener(ILabelProviderListener listener) {
			// not required			
		}

		/**
		 * {@inheritDoc}
		 */
		public void dispose() {
			red.dispose();
		}

		/**
		 * {@inheritDoc}
		 */
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		public void removeListener(ILabelProviderListener listener) {
			// not required
		}

		/**
		 * {@inheritDoc}
		 */
		public Color decorateBackground(Object element) {
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		public Color decorateForeground(Object element) {
			if(element == HierarchyViewBase.this.element) {
				return red;
			}
			return null;
		}
	}
}
