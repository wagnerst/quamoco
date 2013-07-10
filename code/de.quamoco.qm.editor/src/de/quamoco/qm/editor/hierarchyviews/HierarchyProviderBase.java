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

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider.ViewerRefresh;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * Base class for {@link IHierarchyProvider}s.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @ConQAT.Rating YELLOW Hash: 81523E9AFBAE2B82D4A96ED123202CAB
 */
public abstract class HierarchyProviderBase extends AdapterImpl implements
		IHierarchyProvider {

	/** The {@link TreeViewer} for which this hierarchy provider is used. */
	private TreeViewer treeViewer;

	/** Helper class to cache refreshes of the viewer. */
	private ViewerRefresh viewerRefresh;

	/** {@inheritDoc} */
	@Override
	public void setTreeViewer(TreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	/** Refresh an element in the {@link TreeViewer}. */
	protected void refresh(final Object element) {
		if (viewerRefresh == null) {
			viewerRefresh = new ViewerRefresh(treeViewer);
		}

		IViewerNotification notification = new ViewerNotification(null,
				element, true, true);
		if (viewerRefresh.addNotification(notification)) {
			// only execute the refresh once
			if (!treeViewer.getControl().isDisposed()) {
				treeViewer.getControl().getDisplay().asyncExec(viewerRefresh);
			}
		}
	}
}
