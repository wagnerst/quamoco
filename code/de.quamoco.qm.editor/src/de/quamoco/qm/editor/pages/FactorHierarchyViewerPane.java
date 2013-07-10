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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PlatformUI;

import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.hierarchyviews.HierarchyContentProvider;
import de.quamoco.qm.editor.hierarchyviews.HierarchyMenuProvider;

/**
 * Pane that displays the factor hierarchy.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 2F47D94896E6BBBEC1E4D06657959C61
 */
public class FactorHierarchyViewerPane extends HierarchyViewerPaneBase {

	/** Constructor. */
	public FactorHierarchyViewerPane(CustomQmEditor editor) {
		super(editor, "Factor Hierarchy");
	}

	/** {@inheritDoc} */
	@Override
	protected void initTreeViewer(TreeViewer treeViewer) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(treeViewer.getTree(),
				"de.quamoco.qm.help.FactorHierarchy");

		HierarchyContentProvider contentProvider = new FactorHierarchyContentProvider();
		treeViewer.setContentProvider(contentProvider);

		HierarchyMenuProvider menuProvider = new HierarchyMenuProvider(
				contentProvider.getProviders());
		menuProvider.createContextMenu(treeViewer, editor.getSite());

		super.initTreeViewer(treeViewer);
	}
}
