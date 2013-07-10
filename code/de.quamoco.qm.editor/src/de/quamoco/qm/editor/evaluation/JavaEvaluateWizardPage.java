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

package de.quamoco.qm.editor.evaluation;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.editor.CustomQmEditor;

/**
 * Wizard page to set the parameters for the ConQAT evaluation of a Java system.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class JavaEvaluateWizardPage extends EvaluateWizardPageBase {

	/** Text field to set the binary directory. */
	private Text binText;

	/** Constructor. */
	public JavaEvaluateWizardPage(QualityModel model, CustomQmEditor editor) {
		super(model, editor);
	}

	/** {@inheritDoc} */
	@Override
	protected void createParameterWidgets(Composite composite) {
		super.createParameterWidgets(composite);
		binText = createDirChooser(composite, "Binary Directory:", "binary.dir");
	}

	/** {@inheritDoc} */
	@Override
	public boolean performFinish() {
		getDialogSettings().put("binary.dir", binText.getText());
		runnable = new JavaConQATRunnable(model, projectText.getText(),
				sourceText.getText(), binText.getText(), resultText.getText(),
				manualText.getText(), editor);
		return super.performFinish();
	}
}
