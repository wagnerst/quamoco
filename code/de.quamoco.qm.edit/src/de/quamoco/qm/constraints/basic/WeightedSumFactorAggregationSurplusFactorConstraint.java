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

package de.quamoco.qm.constraints.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRanking;
import de.quamoco.qm.WeightedSumFactorAggregation;
import edu.tum.cs.emf.commons.validation.ElementModelConstraintBase;

public class WeightedSumFactorAggregationSurplusFactorConstraint extends
		ElementModelConstraintBase {

	@Override
	protected IStatus validate(EObject target) {
		WeightedSumFactorAggregation weightedSumFactorAggregation = (WeightedSumFactorAggregation) target;
		
		Set<Factor> usedFactors = new HashSet<Factor>();
		for (FactorRanking ranking : weightedSumFactorAggregation.getRankings()) {
			if (ranking.getFactor() != null) {
				usedFactors.add(ranking.getFactor());
			}
		}

		Set<Factor> allowedFactors = new HashSet<Factor>(
				weightedSumFactorAggregation.getUsableFactors());
		
		List<IStatus> problems = new ArrayList<IStatus>();
		if(!allowedFactors.equals(usedFactors)) {
			if (usedFactors.containsAll(allowedFactors)) {
				usedFactors.removeAll(allowedFactors);
				for(Factor f: allowedFactors) {
					problems.add(createFailureStatus(f.getQualifiedName()));
				}
			}
		}
		return createMultiStatus(problems);
	}
}
