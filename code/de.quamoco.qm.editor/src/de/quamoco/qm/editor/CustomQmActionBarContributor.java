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

package de.quamoco.qm.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.IEditorPart;

import de.quamoco.qm.presentation.QmActionBarContributor;
import de.quamoco.qm.presentation.QmEditor;

/**
 * Custom {@link QmActionBarContributor} that synchronizes the editing domain
 * when changing the page.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: DED42514BBEA9828A0CCCA868FEA5FA6
 */
public class CustomQmActionBarContributor extends QmActionBarContributor {

	/** {@inheritDoc} */
	@Override
	public void setActivePage(IEditorPart part) {
		super.setActivePage(part);
		if (activeEditor != null) {
			EditingDomain editingDomain = ((QmEditor) activeEditor)
					.getEditingDomain();
			deleteAction.setEditingDomain(editingDomain);
		}
	}
}
