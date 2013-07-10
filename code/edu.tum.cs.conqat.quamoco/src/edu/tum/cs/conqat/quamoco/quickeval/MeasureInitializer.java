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

package edu.tum.cs.conqat.quamoco.quickeval;

import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;

import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.conqat.quamoco.MetricCollection;
import edu.tum.cs.conqat.quamoco.QMProcessorBase;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: D4EADDF101347ADBB0C26FD67853EED8
 */
@AConQATProcessor(description = "Initializes measures from the model, which do not have a value set, with a default value.")
public class MeasureInitializer extends QMProcessorBase {

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "metric", attribute = "collection", description = "Collection of computed metric values", optional = true)
	public MetricCollection metricCollection;

	/** {@inheritDoc} */
	@Override
	public MetricCollection process() {
		if (metricCollection == null) {
			metricCollection = new MetricCollection();
		}

		QualityModel model = models[0];
		for (Measure measure : model.getMeasures()) {
			if (metricCollection.get(measure.getQualifiedName()) == null) {
				addDummyValue(metricCollection, measure);
			}
		}

		return metricCollection;
	}

	/** Add dummy value to measure */
	private void addDummyValue(MetricCollection metricCollection,
			Measure measure) {
		Object value = TypeUtils.defaultValue(measure.getType());
		metricCollection.put(measure.getQualifiedName(), value);
		getLogger().info(
				"Storing: '" + measure.getQualifiedName() + "': '" + value
						+ "'");
	}
}
