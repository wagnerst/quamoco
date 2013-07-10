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

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.collections.UnmodifiableMap;

import de.quamoco.qm.Factor;
import de.quamoco.qm.QIESLEvaluation;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.qiesl.CalibrationUtils;
import edu.tum.cs.conqat.quamoco.qiesl.IQIESLEvalVariables;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLEngine;
import edu.tum.cs.conqat.quamoco.qiesl.QIESLException;
import edu.tum.cs.conqat.quamoco.qiesl.QPoints;

/** {@ConQAT.Doc} */
@AConQATProcessor(description = "Extended Quamoco ModelEvaluator that writes information for "
		+ "calibration.")
public class ModelEvaluatorWritingCalibrationInfos extends ModelEvaluator {

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(optional = false, parameter = "calibration-dir", attribute = "attr", description = "The directory where the calibration data is stored.")
	public String calibrationDir;

	/** Stores the calibration data */
	private Properties calibrationData;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ConQATException
	 */
	@Override
	public QualityModel[] process() throws ConQATException {

		calibrationData = new Properties();

		try {
			return super.process();
		} finally {
			saveCalibrationData();
		}
	}

	/**
	 * Saves the calibration data
	 */
	private void saveCalibrationData() {
		try {
			BufferedOutputStream out = new BufferedOutputStream(
					new FileOutputStream(calibrationDir + "/calibration_"
							+ projectName + ".txt"));
			try {
				calibrationData
						.store(out, "Calibration data of " + projectName);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			getLogger().warn(
					"Error saving calibration data: " + e.getMessage(), e);
		}
	}

	/**
	 * Calculates the calibration data and stores it.
	 */
	@Override
	protected void extensionPointForCalibration(Factor factor,
			QIESLEvaluation evaluation, IQIESLEvalVariables variables) {
		if (this.calibrationData == null) {
			return;
		}

		List<String> linearDistributions = CalibrationUtils
				.getLinearDistributions(evaluation.getSpecification());

		for (int i = 0; i < linearDistributions.size(); i++) {
			String linearDist = linearDistributions.get(i);

			String[] parts = CalibrationUtils
					.getParseLinearDistribution(linearDist);

			if (parts == null) {
				getLogger().warn(
						"Invalid linearDistribution in QIESL of factor '"
								+ factor.getQualifiedName() + "': "
								+ linearDist);
				return;
			}

			// evaluate using the QIESL engine
			QIESLEngine engine = new QIESLEngine(this.functionRangeResolver,
					this.fileRangeResolver, this.classRangeResolver,
					this.getLogger());

			Object value = null;
			try {
				value = engine.evaluate(parts[0], makeAllOptional(variables),
						Type.NUMBER);

			} catch (QIESLException e) {
				getLogger().warn(
						"Evaluation of linearDistribution in QIESL of factor '"
								+ factor.getQualifiedName() + "': '"
								+ linearDist
								+ "' threw the following exception: " + e);
				return;
			}

			if (value == null) {
				getLogger().warn(
						"Evaluation of linearDistribution in QIESL of factor '"
								+ factor.getQualifiedName() + "': '"
								+ linearDist + "' did return null");
				return;
			}

			if (!(value instanceof Number)) {
				getLogger().warn(
						"Evaluation of linearDistribution in QIESL of factor '"
								+ factor.getQualifiedName() + "': '"
								+ linearDist + "' did return type '"
								+ value.getClass().getName() + "'");
				return;
			}

			if (value instanceof QPoints) {
				getLogger().warn(
						"Evaluation of linearDistribution in QIESL of factor '"
								+ factor.getQualifiedName() + "': '"
								+ linearDist + "' did returned type QPoints: '"
								+ ((QPoints) value).toShortString());
				return;
			}

			this.calibrationData.put(evaluation.getQualifiedName() + "$" + i,
					String.valueOf(((Number) value).doubleValue()));

		}

	}

	/** Makes all variables optional */
	private IQIESLEvalVariables makeAllOptional(
			final IQIESLEvalVariables variables) {
		return new IQIESLEvalVariables() {

			@Override
			public UnmodifiableMap<String, Object> getOptionalVariables() {
				Map<String, Object> result = new HashMap<String, Object>();
				result.putAll(variables.getOptionalVariables());
				result.putAll(variables.getMandatoryVariables());
				return CollectionUtils.asUnmodifiable(result);
			}

			@Override
			public UnmodifiableMap<String, Object> getMandatoryVariables() {
				return CollectionUtils.emptyMap();
			}

			@Override
			public QPoints[] getAllImpactsAndRefinements() {
				return variables.getAllImpactsAndRefinements();
			}
		};
	}
}
