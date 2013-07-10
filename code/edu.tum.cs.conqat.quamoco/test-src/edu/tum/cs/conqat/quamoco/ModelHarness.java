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

/*--------------------------------------------------------------------------+
$Id: ModelHarness.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
+--------------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco;

import de.quamoco.qm.Entity;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;

/**
 * Methods for creating model instances in tests.
 * 
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: EB26DA25653CD287325B474682466834
 */
public class ModelHarness {

	/** Creates a quality model with the given name. */
	public static QualityModel createQualityModel(String name) {
		QualityModel qualityModel = QmFactory.eINSTANCE.createQualityModel();
		qualityModel.setName(name);
		return qualityModel;
	}

	/** Creates an entity with the given name */
	public static Entity createEntity(String name) {
		Entity entity = QmFactory.eINSTANCE.createEntity();
		entity.setName(name);
		return entity;
	}

	/** Creates a measure with the given name and type */
	public static Measure createMeasure(String name, Type type) {
		Measure measure = QmFactory.eINSTANCE.createMeasure();
		measure.setName(name);
		measure.setType(type);
		return measure;
	}

	/** Creates a new manual instrument with the given name */
	public static ManualInstrument createManualInstrument(String name) {
		ManualInstrument manualInstrument = QmFactory.eINSTANCE
				.createManualInstrument();
		manualInstrument.setName(name);
		return manualInstrument;
	}

}
