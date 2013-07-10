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

import org.conqat.ide.commons.ui.selection.SelectionUtils;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * A {@link IMenuListener} that provides menu items based on
 * {@link IHierarchyProvider}s.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 211CB27226A068C183A76C146B384171
 */
public class HierarchyMenuProvider implements IMenuListener {

	/** Constant for the separator to which action should be added. */
	private static final String ADDITIONS = IWorkbenchActionConstants.MB_ADDITIONS;

	/** The {@link IMenuProvider}s that are composed. */
	private final List<IMenuProvider> menuProviders = new ArrayList<IMenuProvider>();

	/** The {@link TreeViewer} for which menu items are provided. */
	private TreeViewer treeViewer;

	/** Constructor. */
	public HierarchyMenuProvider(IHierarchyProvider... providers) {
		for (IHierarchyProvider provider : providers) {
			if (provider instanceof IMenuProvider) {
				menuProviders.add((IMenuProvider) provider);
			}
		}
	}

	/** Create the context menu for the {@link TreeViewer}. */
	public void createContextMenu(TreeViewer treeViewer, IWorkbenchPartSite site) {
		this.treeViewer = treeViewer;

		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator(ADDITIONS));
		contextMenu.setRemoveAllWhenShown(true);

		Menu menu = contextMenu.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);

		site.registerContextMenu(contextMenu, new UnwrappingSelectionProvider(
				treeViewer));
		contextMenu.addMenuListener(this);
	}

	/** Get the {@link IMenuProvider}s associated to a certain element. */
	private List<IMenuProvider> getMenuProviders(Object element) {
		List<IMenuProvider> providers = new ArrayList<IMenuProvider>();
		for (IMenuProvider provider : menuProviders) {
			if (provider.providesMenuItems(element)) {
				providers.add(provider);
			}
		}
		return providers;
	}

	/** {@inheritDoc} */
	@Override
	public void menuAboutToShow(IMenuManager manager) {
		Object element = SelectionUtils.checkAndPickFirst(treeViewer
				.getSelection(), Object.class);
		List<IMenuProvider> providers = getMenuProviders(element);
		for (IMenuProvider provider : providers) {
			List<Object> items = provider.getMenuItems(element);
			for (Object item : items) {
				if (item instanceof IAction) {
					manager.insertBefore(ADDITIONS, (IAction) item);
				} else if (item instanceof IContributionItem) {
					manager.insertBefore(ADDITIONS, (IContributionItem) item);
				}
			}
		}
	}
}
