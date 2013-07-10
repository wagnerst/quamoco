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

import de.quamoco.qm.QmPackage;
import de.quamoco.qm.editor.CustomQmEditor;
import de.quamoco.qm.editor.hierarchyviews.HierarchyContentProvider;
import de.quamoco.qm.editor.hierarchyviews.HierarchyMenuProvider;
import de.quamoco.qm.editor.hierarchyviews.QualityModelRootProvider;
import de.quamoco.qm.editor.hierarchyviews.TransientHierarchyProvider;

/**
 * Pane that displays the entity hierarchy.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 02A136B00D02953A08BB6F29E9AF9035
 */
public class EntityHierarchyViewerPane extends HierarchyViewerPaneBase {

	/** Constructor. */
	public EntityHierarchyViewerPane(CustomQmEditor editor) {
		super(editor, "Entity Hierarchy");
	}

	/** {@inheritDoc} */
	@Override
	protected void initTreeViewer(TreeViewer treeViewer) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(treeViewer.getTree(),
				"de.quamoco.qm.help.EntityHierarchy");

		HierarchyContentProvider contentProvider = new HierarchyContentProvider(
				new QualityModelRootProvider(QmPackage.eINSTANCE
						.getQualityModel_Entities(), QmPackage.eINSTANCE
						.getEntity_IsADirect(), QmPackage.eINSTANCE
						.getEntity_PartOfDirect()),
				new TransientHierarchyProvider(null, QmPackage.eINSTANCE
						.getEntity_IsADirect()).setLabel("Specialized By")
						.setImage(getImage("full/obj16/EntityIsa")),
				new TransientHierarchyProvider(null, QmPackage.eINSTANCE
						.getEntity_PartOfDirect()).setLabel("Consists Of")
						.setImage(getImage("full/obj16/EntityPart")));

		treeViewer.setContentProvider(contentProvider);

		HierarchyMenuProvider menuProvider = new HierarchyMenuProvider(contentProvider
				.getProviders());
		menuProvider.createContextMenu(treeViewer, editor.getSite());

		super.initTreeViewer(treeViewer);
	}
}
