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

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.EFindingKeys;
import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.FindingGroup;
import org.conqat.engine.commons.findings.FindingReport;
import org.conqat.engine.commons.findings.location.ElementLocation;
import org.conqat.engine.commons.findings.location.QualifiedNameLocation;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.lib.commons.assertion.CCSMAssert;

/**
 * {@ConQAT.Doc}
 * 
 * @author Florian Deissenboeck
 * @author $Author: lochmann $
 * @version $Rev: 5044 $
 * @levd.rating RED Hash: 67C71E54105D30567835656D6B4DDAB6
 */
@AConQATProcessor(description = "This class reads manual created findings from an Excel file.")
public class ManualFindingsReader extends ConQATProcessorBase {

	/** Excel file. */
	private File file;

	/** Category name. */
	private String findingCategory = "DUMMY";

	/** Group name. */
	private String findingGroup = "DUMMY";

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "category", minOccurrences = 0, maxOccurrences = 1, description = ""
			+ "Specify names of category and group. If omitted, dumm names are used.")
	public void setGroup(
			@AConQATAttribute(name = "category-name", description = "Category name") String findingCategory,
			@AConQATAttribute(name = "group-name", description = "Group name") String findingGroup) {
		this.findingCategory = findingCategory;
		this.findingGroup = findingGroup;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Name of the findings file.")
	public void setInputFile(
			@AConQATAttribute(name = "file", description = "Filename") File file) {
		this.file = file;
	}

	/** {@inheritDoc} */
	@Override
	public MetricCollection process() throws ConQATException {

		MetricCollection result = new MetricCollection();

		HSSFSheet sheet = ExcelUtils.readFirstSheet(file);

		ExcelUtils.checkName(sheet, 0, "Measure");
		ExcelUtils.checkName(sheet, 1, "Location");
		ExcelUtils.checkName(sheet, 2, "Message");

		FindingGroup group = new FindingReport().getOrCreateCategory(
				findingCategory).getOrCreateFindingGroup(findingGroup);

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			HSSFRow row = sheet.getRow(i);
			String measureName = ExcelUtils.getCellText(row, 0);
			String location = ExcelUtils.getCellText(row, 1);
			String message = ExcelUtils.getCellText(row, 2);
			
			QualifiedNameLocation qnl = new QualifiedNameLocation(location, location, location);
			
			String findingname = getClass().getSimpleName();
			// not used anymore after restrusturing to new findings framwork of 21.12.2012
			
			Finding finding = group.createFinding(qnl);
			finding.setValue(EFindingKeys.MESSAGE.toString(), message);

			add(measureName, result, finding);
		}
		return result;
	}

	/**
	 * Add measure to result.
	 */
	private void add(String measureName, MetricCollection result,
			Finding finding) {
		Object findingCollection = result.get(measureName);
		if (findingCollection == null) {
			findingCollection = new FindingCollection();
			result.put(measureName, findingCollection);
		} else {
			CCSMAssert.isInstanceOf(findingCollection, FindingCollection.class);
		}
		((FindingCollection) findingCollection).add(finding);

	}
}
