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

package edu.tum.cs.conqat.quamoco;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.text.ITextElement;
import org.conqat.engine.resource.text.TextElementUtils;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;

import de.quamoco.qm.Factor;
import de.quamoco.qm.FindingsMeasurementResult;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasurementResult;
import de.quamoco.qm.NumberMeasurementResult;
import de.quamoco.qm.QualityModel;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/**
 * Utility methods used in the Quamoco bundle. *
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 1290A79ED6CEE9A0156A7DA5AD6D81BF
 */
public class QuamocoUtils {

	/** Default suffix for Quamoco model files. */
	public static final String QUAMOCO_MODEL_FILE_SUFFIX = "qm";

	/** Default suffix for Quamoco result files. */
	public static final String QUAMOCO_RESULT_FILE_SUFFIX = "qmr";

	/**
	 * Gets the number of positions contained in the RegionSet. This corresponds
	 * to the (non-overlapping) sum of the length of the regions.
	 */
	public static int getPositionCount(RegionSetDictionary dict) {
		int count = 0;

		for (RegionSet set : dict.regionSets.values()) {
			count += set.getPositionCount();
		}
		return count;
	}

	/**
	 * Obtain the region described by {@link ITextElement}. In case any
	 * exception occurs, this returns an empty region set.
	 */
	public static Region obtainFileRegion(ITextElement element) {
		try {
			int endLine = TextElementUtils.getLines(element).length;
			return new Region(0, endLine);
		} catch (ConQATException ex) {
			// QIESL.LOGGER.error("Could not obtain region for file", ex);
			return new Region(0, 0);
		}
	}

	/** Return all factors stored in the models. */
	public static List<Factor> getFactors(Collection<QualityModel> models) {
		ArrayList<Factor> result = new ArrayList<Factor>();
		for (QualityModel model : models) {
			result.addAll(model.getFactors());
		}
		return result;
	}

	/** Return all factors stored in the models. */
	public static List<Measure> getMeasures(Collection<QualityModel> models) {
		ArrayList<Measure> result = new ArrayList<Measure>();
		for (QualityModel model : models) {
			result.addAll(model.getMeasures());
		}
		return result;
	}

	/** Converts a stack trace to a string */
	public static String stackTraceToString(Exception e) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		e.printStackTrace(ps);
		return baos.toString();
	}

	/** Get the value of a {@link MeasurementResult} depending on the type. */
	public static Object getValue(MeasurementResult measurementResult) {
		if (measurementResult instanceof FindingsMeasurementResult) {
			return ((FindingsMeasurementResult) measurementResult)
					.getFindings();
		} else if (measurementResult instanceof NumberMeasurementResult) {
			return QPoints
					.valueOf(((NumberMeasurementResult) measurementResult)
							.getValue());
		}
		return null;
	}

}
