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

package de.quamoco.qm.search.util;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.search.core.scope.IModelSearchScope;
import org.eclipse.emf.search.ecore.common.utils.file.EcoreModelSearchScopeFileSystemVisitor;
import java.io.File;

public class ModelSearchScopeFileSystemVisitor extends
		EcoreModelSearchScopeFileSystemVisitor {

	public ModelSearchScopeFileSystemVisitor(
			IModelSearchScope<Object, Resource> scope) {
		super(scope);
	}

	protected boolean isParticipantCurrentSearchEngineValid(File f) {
		if (f instanceof File && f.canRead() && f.exists() && !f.isHidden()) {

			if (f.getName().endsWith(".qm")) { //$NON-NLS-1$
				return true;
			}

		}
		return false;
	}
}
