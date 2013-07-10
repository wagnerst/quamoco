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

package edu.tum.cs.conqat.quamoco.qiesl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.conqat.lib.commons.math.MathUtils;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QualityModel;

/**
 * Calibrates the QIESL formulas of a model
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
public class ModelCalibrator {

	/** The model to calibrate */
	private final QualityModel[] qualityModels;

	/** The list of calibration data */
	private final List<Properties> calibrationData = new LinkedList<Properties>();

	/** Constructor */
	public ModelCalibrator(QualityModel[] qualityModel) {
		this.qualityModels = qualityModel;
	}

	/** Returns the quality models */
	public QualityModel[] getQualityModel() {
		return this.qualityModels;
	}

	/** Adds the calibration data of one project */
	public void addCalibrationData(Properties properties) {
		this.calibrationData.add(properties);
	}

	/** calibrates the model and returns resulting messages */
	public List<String> calibrate() {
		List<String> messages = new LinkedList<String>();

		for (QualityModel qualityModel : this.qualityModels) {
			Iterator<EObject> i = qualityModel.eAllContents();
			while (i.hasNext()) {
				EObject eObject = i.next();
				if (eObject instanceof QIESLEvaluation) {
					calibrate((QIESLEvaluation) eObject, messages);
				}
			}
		}

		return messages;

	}

	/** Decimal format */
	private static final DecimalFormat format = new DecimalFormat("0.00000000");

	/** calibrates an evaluation */
	private void calibrate(QIESLEvaluation evaluation, List<String> messages) {
		String currentQIESL = evaluation.getSpecification();
		List<String> linestDistributions = CalibrationUtils
				.getLinearDistributions(currentQIESL);
		if (linestDistributions.isEmpty()) {
			return;
		}

		HashMap<Integer, String> substitutionList = new HashMap<Integer, String>();

		for (int i = 0; i < linestDistributions.size(); i++) {
			String linearDist = linestDistributions.get(i);
			String[] parts = CalibrationUtils
					.getParseLinearDistribution(linearDist);
			if (parts == null) {
				messages
						.add("WARNING: Invalid linearDistribution(...) in QIESL of '"
								+ evaluation.getQualifiedName() + "'");
				continue;
			}
			String calibName = evaluation.getQualifiedName() + "$" + i;
			List<Double> values = getValues(calibName);
			if (values.isEmpty()) {
				messages.add("HINT: No data for '" + calibName
						+ "'. Values not changed.");
				continue;
			}

			double min = MathUtils.min(values);
			double max = MathUtils.max(values);

			if (min == max) {
				min = 0;
				max = 1;
				messages.add("HINT: min==max for '" + calibName
						+ "'. Values not changed.");
				continue;
			}

			messages.add("HINT: Values changed for '" + calibName + "'. min="
					+ min + " max=" + max);
			substitutionList.put(i, "linearDistribution(" + parts[0] + ","
					+ format.format(min).replaceAll(",", ".") + "," + parts[2]
					+ "," + format.format(max).replaceAll(",", ".") + ","
					+ parts[4] + ")");
		}

		String newQIESL = CalibrationUtils.substitute(currentQIESL,
				substitutionList);

		evaluation.setSpecification(newQIESL);
	}

	/** gets all values of the calibration data */
	private List<Double> getValues(String calibName) {
		List<Double> result = new ArrayList<Double>();
		for (Properties p : calibrationData) {
			String s = p.getProperty(calibName);
			if (s != null && !s.isEmpty() && !"NaN".equals(s)) {
				result.add(Double.valueOf(s));
			}
		}
		return result;
	}

}
