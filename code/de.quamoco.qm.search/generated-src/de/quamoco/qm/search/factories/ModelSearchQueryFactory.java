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

package de.quamoco.qm.search.factories;

import org.eclipse.emf.search.core.engine.AbstractModelSearchQuery;
import org.eclipse.emf.search.core.factories.IModelSearchQueryFactory;
import org.eclipse.emf.search.core.parameters.IModelSearchQueryParameters;

import de.quamoco.qm.search.engine.ModelSearchQuery;

/**
 * Wraps ModelSearchQuery creation.
 */
public class ModelSearchQueryFactory implements IModelSearchQueryFactory {
	private static ModelSearchQueryFactory instance;

	public ModelSearchQueryFactory() {
	}

	public static ModelSearchQueryFactory getInstance() {
		return instance == null ? instance = new ModelSearchQueryFactory()
				: instance;
	}

	public AbstractModelSearchQuery createModelSearchQuery(String expr,
			IModelSearchQueryParameters p) {
		return new ModelSearchQuery(expr, p);
	}
}
