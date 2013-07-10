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

package de.quamoco.qm.provider.util;

import java.util.List;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.provider.QmEditPlugin;

/**
 * Helper methods for the edit functionality.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 46A649594B644394CFFF8719CBB65C31
 */
public final class QmEditUtils {

	/** Private constructor. */
	private QmEditUtils() {
		// hidden
	}

	/** Get the image for a factor. */
	public static Object getFactorImage(Factor factor) {
		String image = "Factor";

		if (!factor.getEvaluatedBy().isEmpty()) {
			image += "Eval";
		} else {
			image += "X";
		}

		Entity entity = factor.getCharacterizes();
		if (entity != null && !"System".equals(entity.getName())) {
			if (entity.isUseCase()) {
				image += "Use";
			} else if (entity.isStakeholder()) {
				image += "Stake";
			} else {
				image += "Entity";
			}
		} else {
			image += "X";
		}

		return QmEditPlugin.INSTANCE.getImage("full/obj16/" + image);
	}

	/** Get the text for a factor. */
	public static String getFactorText(Factor factor) {
		String text = factor.getQualifiedName();
		QualityModel qm = factor.getQualityModel();
		if (qm != null) {
			text += " [" + qm.getName() + "]";
		}
		return text;
	}

	/** Get the image for a measure. */
	public static Object getMeasureImage(Measure measure) {
		String image = "Measure";

		List<MeasurementMethod> methods = measure.getDeterminedBy();
		if (!methods.isEmpty()) {
			MeasurementMethod instrument = methods.get(0);
			if (instrument instanceof ToolBasedInstrument) {
				image += "Tool";
			} else if (instrument instanceof ManualInstrument) {
				image += "Manual";
			}
		}

		return QmEditPlugin.INSTANCE.getImage("full/obj16/" + image);
	}

	/** Get the text for a measure. */
	public static String getMeasureText(Measure measure) {
		String text = measure.getQualifiedName();
		if (measure.getQualityModel() != null) {
			text += " [" + measure.getQualityModel().getName() + "]";
		}
		return text;
	}
}
