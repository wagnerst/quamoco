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

package de.quamoco.qm.properties.eval.model;

import de.quamoco.qm.Measure;
import de.quamoco.qm.NormalizationMeasure;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.MappingFunction;
import de.quamoco.qm.properties.eval.generator.EvaluationGenerator.Range;

public class RefiningMeasure extends InfluencingOrRefiningElement<Measure> {

	private NormalizationMeasure normalizationMeasure;
	private Range range;
	private MappingFunction mappingFunction;
	private double lowerBound;
	private double upperBound;

	public static Type convertType(de.quamoco.qm.Type qmType) {
		if (de.quamoco.qm.Type.FINDINGS.equals(qmType)) {
			return Type.findings;
		} else if (de.quamoco.qm.Type.NUMBER.equals(qmType)) {
			return Type.number;
		}
		return Type.none;
	}

	public RefiningMeasure(Measure element,
			NormalizationMeasure normalizationMeasure, int ranking,
			double contPoints, int maxPoints, String module, Range range,
			MappingFunction mappingFunction, double lowerBound,
			double upperBound) {
		this(element, normalizationMeasure, ranking, contPoints, maxPoints,
				convertType(element.getType()), module, range, mappingFunction,
				lowerBound, upperBound);
	}

	public RefiningMeasure(Measure element,
			NormalizationMeasure normalizationMeasure, int ranking,
			double contPoints, int maxPoints, Type type, String module,
			Range range, MappingFunction mappingFunction, double lowerBound,
			double upperBound) {
		super(element, ranking, contPoints, maxPoints, type, module);
		this.normalizationMeasure = normalizationMeasure;
		this.range = range;
		this.mappingFunction = mappingFunction;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	public NormalizationMeasure getNormalizationMeasure() {
		return normalizationMeasure;
	}

	public void setNormalizationMeasure(
			NormalizationMeasure normalizationMeasure) {
		this.normalizationMeasure = normalizationMeasure;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public MappingFunction getMappingFunction() {
		return mappingFunction;
	}

	public void setMappingFunction(MappingFunction mappingFunction) {
		this.mappingFunction = mappingFunction;
	}

	public double getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}

}
