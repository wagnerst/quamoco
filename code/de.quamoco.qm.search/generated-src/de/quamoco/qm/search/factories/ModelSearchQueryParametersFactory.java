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

import org.eclipse.emf.search.core.factories.IModelSearchQueryParametersFactory;
import org.eclipse.emf.search.core.parameters.AbstractModelSearchQueryParameters;
import org.eclipse.emf.search.core.parameters.IModelSearchQueryParameters;

/**
 * Wraps ModelSearchQueryParameters creation.
 */
public class ModelSearchQueryParametersFactory implements
		IModelSearchQueryParametersFactory {
	private static ModelSearchQueryParametersFactory instance;

	public ModelSearchQueryParametersFactory() {
	}

	public static ModelSearchQueryParametersFactory getInstance() {
		return instance == null ? instance = new ModelSearchQueryParametersFactory()
				: instance;
	}

	protected final class ModelSearchQueryParameters extends
			AbstractModelSearchQueryParameters {
		public String getModelSearchEngineID() {
			return "search.QmSearchEngine"; //$NON-NLS-1$
		}
	}

	public IModelSearchQueryParameters createSearchQueryParameters() {
		return new ModelSearchQueryParameters();
	}
}
