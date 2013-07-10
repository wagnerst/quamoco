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

package de.quamoco.adaptation.util.qm.resource;

import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.adaptation.util.resource.AbstractResourceVisitor;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.presentation.QmModelWizard;

/**
 * An {@link IResourceVisitor} that searches for {@link QualityModel}s in
 * the workspace according to their file extension. If a file with this 
 * extension is found, its resource is loaded within the passed editing
 * domain and the {@link QualityModel} object is added to a list which
 * can be accessed via {@link #getElements()}.
 * @author Franz Becker
 * @see QualityModelResourceUtil#getQualityModels(EditingDomain)
 */
public class QualityModelResourceVisitor extends AbstractResourceVisitor<QualityModel> {

	/** 
	 * Initializes the fields
	 * @param editingDomain initialize {@link #editingDomain}
	 */
	public QualityModelResourceVisitor(EditingDomain editingDomain) {
		super(editingDomain, QmModelWizard.FILE_EXTENSIONS);
	}

	/**
	 * Returns true for {@link QualityModel}s.
	 */
	@Override
	protected boolean isTypeInstance(EObject eObject) {
		return eObject instanceof QualityModel;
	}


}
