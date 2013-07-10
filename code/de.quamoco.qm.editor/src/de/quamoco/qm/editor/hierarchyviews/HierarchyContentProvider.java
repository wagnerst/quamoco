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

package de.quamoco.qm.editor.hierarchyviews;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * An {@link ITreeContentProvider} that composes {@link IHierarchyProvider}s to
 * assemble the tree structure.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: AD2FC5C2EF73FB358657699A85B17F88
 */
public class HierarchyContentProvider implements ITreeContentProvider {

	/** The {@link IHierarchyProvider}s that are composed. */
	private final IHierarchyProvider[] providers;

	/** The {@link IParentProvider}s of the {@link IHierarchyProvider}s. */
	private final List<IParentProvider> parentProviders = new ArrayList<IParentProvider>();

	/** The {@link IChildrenProvider} of the {@link IHierarchyProvider} */
	private final List<IChildrenProvider> childrenProviders = new ArrayList<IChildrenProvider>();

	/** Constructor. */
	public HierarchyContentProvider(IHierarchyProvider... providers) {
		this.providers = providers;
		for (IHierarchyProvider provider : providers) {
			add(provider);
		}
	}

	/** Add an {@link IHierarchyProvider}. */
	private void add(IHierarchyProvider provider) {
		if (provider instanceof IParentProvider) {
			parentProviders.add((IParentProvider) provider);
		}
		if (provider instanceof IChildrenProvider) {
			childrenProviders.add((IChildrenProvider) provider);
		}
	}

	/** Get the {@link IParentProvider}s for a certain element. */
	private List<IParentProvider> getParentProviders(Object element) {
		List<IParentProvider> providers = new ArrayList<IParentProvider>();
		for (IParentProvider provider : parentProviders) {
			if (provider.providesParent(element)) {
				providers.add(provider);
			}
		}
		return providers;
	}

	/** Get the {@link IChildrenProvider}s for a certain element. */
	private List<IChildrenProvider> getChildrenProviders(Object element) {
		List<IChildrenProvider> providers = new ArrayList<IChildrenProvider>();
		for (IChildrenProvider provider : childrenProviders) {
			if (provider.providesChildren(element)) {
				providers.add(provider);
			}
		}
		return providers;
	}

	/** {@inheritDoc} */
	@Override
	public Object getParent(Object element) {
		List<IParentProvider> providers = getParentProviders(element);
		for (IParentProvider provider : providers) {
			Object parent = provider.getParent(element);
			if (parent != null) {
				return parent;
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasChildren(Object element) {
		List<IChildrenProvider> providers = getChildrenProviders(element);
		for (IChildrenProvider provider : providers) {
			if (provider.hasChildren(element)) {
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> children = new ArrayList<Object>();
		List<IChildrenProvider> providers = getChildrenProviders(parentElement);
		for (IChildrenProvider provider : providers) {
			children.addAll(provider.getChildren(parentElement));
		}
		return children.toArray();
	}

	/** {@inheritDoc} */
	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/** {@inheritDoc} */
	@Override
	public void dispose() {
		// not required
	}

	/** {@inheritDoc} */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		TreeViewer treeViewer = (TreeViewer) viewer;
		for (IHierarchyProvider provider : providers) {
			provider.setTreeViewer(treeViewer);
		}
	}

	/** Get the providers. */
	public IHierarchyProvider[] getProviders() {
		return providers;
	}
}
