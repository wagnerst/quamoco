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

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.conqat.lib.commons.test.CCSMTestCaseBase;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Type;
import edu.tum.cs.conqat.quamoco.ExcelUtils;
import edu.tum.cs.conqat.quamoco.ModelHarness;

/**
 * Tests for {@link MeasureLister}.
 * 
 * @author heineman
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class MeasureListerTest extends CCSMTestCaseBase {

	/** Basic test */
	public void testSimple() throws Exception {
		MeasureLister ml = new MeasureLister();
		File tmp = getTmpDirectory();
		tmp.mkdirs();
		ml.file = tmp.getAbsolutePath() + "/out.xls";
		QualityModel qm = ModelHarness.createQualityModel("m");
		ManualInstrument instr = ModelHarness.createManualInstrument("i");
		Measure measure = ModelHarness.createMeasure("m", Type.NUMBER);
		instr.setDetermines(measure);
		qm.getMeasures().add(measure);

		ml.setQualityModels(new QualityModel[] { qm });
		ml.process();
		HSSFSheet sheet = ExcelUtils.readFirstSheet(new File(ml.file));
		HSSFRow titleRow = sheet.getRow(0);
		assertEquals("Measure", ExcelUtils.getCellContent(titleRow, 0));
	}

}
