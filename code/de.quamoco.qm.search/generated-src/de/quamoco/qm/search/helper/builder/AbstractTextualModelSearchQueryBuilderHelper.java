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

package de.quamoco.qm.search.helper.builder;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.search.core.engine.IModelSearchQuery;
import org.eclipse.emf.search.core.parameters.IModelSearchQueryParameters;
import org.eclipse.emf.search.core.scope.IModelSearchScope;

import org.eclipse.emf.search.ecore.helper.builder.AbstractEcoreTextualModelSearchQueryBuilderHelper;
import org.eclipse.emf.search.ecore.regex.ModelSearchQueryTextualExpressionEnum;

import de.quamoco.qm.search.evaluators.ModelTextualModelSearchQueryEvaluator;
import de.quamoco.qm.search.factories.ModelSearchQueryFactory;
import de.quamoco.qm.search.factories.ModelSearchQueryParametersFactory;


public abstract class AbstractTextualModelSearchQueryBuilderHelper extends
		AbstractEcoreTextualModelSearchQueryBuilderHelper {

	protected IModelSearchQueryParameters createParameters(
			IModelSearchScope<Object, Resource> scope,
			List<? extends Object> participants,
			ModelSearchQueryTextualExpressionEnum textualExpression) {

		IModelSearchQueryParameters parameters = ModelSearchQueryParametersFactory
				.getInstance().createSearchQueryParameters();

		parameters
				.setEvaluator(new ModelTextualModelSearchQueryEvaluator<IModelSearchQuery, Object>());
		parameters.setParticipantElements(participants);
		parameters.setScope(scope);

		initTextualQueryParametersFromPatternKind(parameters, textualExpression);

		return parameters;
	}

	protected IModelSearchQuery createQuery(String pattern,
			IModelSearchQueryParameters parameters) {

		return ModelSearchQueryFactory.getInstance().createModelSearchQuery(
				pattern, parameters);
	}
}
