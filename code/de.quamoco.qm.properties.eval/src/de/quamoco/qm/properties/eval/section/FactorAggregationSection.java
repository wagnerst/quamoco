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

package de.quamoco.qm.properties.eval.section;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.IStructuredContentProvider;

import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Ranking;
import de.quamoco.qm.WeightedSumFactorAggregation;
import de.quamoco.qm.properties.eval.provider.FactorAggregationContentProvider;

public class FactorAggregationSection extends AbstractAggregationSection {

	@Override
	protected IStructuredContentProvider getContentProvider() {
		return new FactorAggregationContentProvider();
	}

	@Override
	protected String getElementName() {
		return "factor";
	}

	@Override
	protected String getElementPluralName() {
		return "factors";
	}

	@Override
	protected void setInputSubclass(Evaluation evaluation) {
		
		setCurrentQualityModel(evaluation.getQualityModel());
		
		// Workaround, see javadoc for details
		tableViewer.setInput(FactorAggregationContentProvider
				.getElementsStatic(evaluation));

		// automatically set all rankings to 1 if evaluation has no rankings yet
		if (evaluation instanceof WeightedSumFactorAggregation) {
			if (((WeightedSumFactorAggregation) evaluation).getRankings()
					.isEmpty()) {
				setAllElementRankingsTo(1);
			}
		}
	}

	@Override
	protected Collection<? extends Ranking> getRankings() {
		if (evaluation instanceof WeightedSumFactorAggregation) {
			return ((WeightedSumFactorAggregation) evaluation).getRankings();
		}
		return Collections.emptyList();
	}

}
