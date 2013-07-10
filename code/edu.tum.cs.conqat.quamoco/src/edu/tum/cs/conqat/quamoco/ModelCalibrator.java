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

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;

import de.quamoco.qm.QualityModel;

/**
 * 
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "Takes a quality model as input and calibrates its QIESL formulas.")
public class ModelCalibrator extends QMProcessorBase {

	/**
	 * Local file name of the calibration file
	 */
	private String calibrationfile;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "calibration-data", description = "Zip-file containing the calibration data", minOccurrences = 1, maxOccurrences = 1)
	public void setScope(
			@AConQATAttribute(name = "file", description = "local file name") String filename) {
		this.calibrationfile = filename;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QualityModel[] process() throws ConQATException {
		edu.tum.cs.conqat.quamoco.qiesl.ModelCalibrator calibrator = new edu.tum.cs.conqat.quamoco.qiesl.ModelCalibrator(
				this.models);

		try {
			ZipFile file = new ZipFile(this.calibrationfile);
			try {

				Enumeration<? extends ZipEntry> e = file.entries();
				while (e.hasMoreElements()) {
					ZipEntry entry = e.nextElement();
					Properties p = new Properties();
					InputStream entryStream = file.getInputStream(entry);
					try {
						p.load(entryStream);
					} finally {
						entryStream.close();
					}
					calibrator.addCalibrationData(p);
				}
			} finally {
				file.close();
			}
		} catch (IOException e1) {
			throw new ConQATException(e1);
		}
		
		List<String> msgs = calibrator.calibrate();
		for(String msg: msgs)		 {
			getLogger().info(msg);
		}

		return this.models;
	}

}
