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

package de.quamoco.qm.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import edu.tum.cs.emf.commons.validation.view.ValidationView;

/**
 * RCP's perspective
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 13C201B677991E6EAACD3019FB1722A6
 */
public class QmPerspective implements IPerspectiveFactory {

	/**
	 * Perspective ID
	 */
	public static final String ID = QmPerspective.class.getName();

	/**
	 * {@inheritDoc}
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.addPerspectiveShortcut(ID);

		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT,
				0.25f, layout.getEditorArea());
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);

		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT,
				(float) 0.66, layout.getEditorArea());
		right.addView(IPageLayout.ID_PROP_SHEET);

		IFolderLayout bottomRight = layout.createFolder("bottonRight",
				IPageLayout.BOTTOM, (float) 0.60, "right");
		bottomRight.addView(ValidationView.ID);

		bottomRight.addView("org.eclipse.ui.cheatsheets.views.CheatSheetView");
	}
}