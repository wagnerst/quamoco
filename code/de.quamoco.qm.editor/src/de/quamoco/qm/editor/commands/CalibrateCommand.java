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

package de.quamoco.qm.editor.commands;

import java.util.List;

import org.eclipse.emf.edit.command.ChangeCommand;

import edu.tum.cs.conqat.quamoco.qiesl.ModelCalibrator;

/**
 * Command for execution the calibration of the model
 * 
 * @author lochmann
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 053386AB5833083D39640F4863CFBAFE
 */
public class CalibrateCommand extends ChangeCommand {

	/** The source that should be added to all elements. */
	private final ModelCalibrator calibrator;

	/** Constructor. */
	public CalibrateCommand(ModelCalibrator calib) {
		super(calib.getQualityModel()[0].eResource().getResourceSet());

		this.calibrator = calib;
	}

	/** {@inheritDoc} */
	@Override
	protected void doExecute() {
		List<String> msg = this.calibrator.calibrate();
		for(String s: msg) {
			System.out.println("Calib.msg.: " + s);
		}
	}
}