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

package de.quamoco.qm.search.helper.launcher;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.core.results.IModelSearchResult;
import org.eclipse.emf.search.core.scope.IModelSearchScope;
import org.eclipse.emf.search.ecore.helper.launcher.EcoreModelSearchQueryLauncherHelper;
import org.eclipse.emf.search.ecore.regex.ModelSearchQueryTextualExpressionEnum;

public class ModelSearchQueryLauncherHelper extends
		EcoreModelSearchQueryLauncherHelper

{

	private static ModelSearchQueryLauncherHelper instance;

	// Singleton
	public static ModelSearchQueryLauncherHelper getInstance() {
		return instance == null ? instance = new ModelSearchQueryLauncherHelper()
				: instance;
	}

	//
	// NORMAL TEXT
	//

	public IModelSearchResult launchGlobalTextualqmModelSearchQuery(
			String pattern, IModelSearchScope<Object, Resource> scope) {
		IModelSearchQuery q = buildTextualModelSearchQuery(pattern,
				de.quamoco.qm.QmPackage.eINSTANCE.getEClassifiers(),
				ModelSearchQueryTextualExpressionEnum.NORMAL_TEXT, scope,
				"http://www.quamoco.de/qm/v6" //$NON-NLS-1$
		);
		q.run(new NullProgressMonitor());
		return q.getModelSearchResult();
	}

	//
	// REGEX
	//

	public IModelSearchResult launchGlobalRegexqmModelSearchQuery(
			String pattern, IModelSearchScope<Object, Resource> scope) {
		IModelSearchQuery q = buildTextualModelSearchQuery(pattern,
				de.quamoco.qm.QmPackage.eINSTANCE.getEClassifiers(),
				ModelSearchQueryTextualExpressionEnum.REGULAR_EXPRESSION,
				scope, "http://www.quamoco.de/qm/v6" //$NON-NLS-1$
		);
		q.run(new NullProgressMonitor());
		return q.getModelSearchResult();
	}

	//
	// CASE SENSITIVE
	//

	public IModelSearchResult launchGlobalCaseSensitiveqmModelSearchQuery(
			String pattern, IModelSearchScope<Object, Resource> scope) {
		IModelSearchQuery q = buildTextualModelSearchQuery(pattern,
				de.quamoco.qm.QmPackage.eINSTANCE.getEClassifiers(),
				ModelSearchQueryTextualExpressionEnum.CASE_SENSITIVE, scope,
				"http://www.quamoco.de/qm/v6" //$NON-NLS-1$
		);
		q.run(new NullProgressMonitor());
		return q.getModelSearchResult();
	}

}
