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
import java.io.IOException;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import de.quamoco.qm.QualityModel;

/**
 * Saves all quality models of the resource set in a new directory. If no
 * directory is provided, nothing is done.
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "This processor saves Quamoco quality models "
		+ "to disk.")
public class ModelSaver extends ConQATProcessorBase {

	/** The quality model. */
	protected QualityModel[] models;

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(optional = true, parameter = "output", attribute = "dir", description = "The directory to which the quality models shall be written")
	public String evaluationResultDir;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "models", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The quality models.")
	public void setQualityModels(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) QualityModel[] models) {
		this.models = models;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QualityModel[] process() {
		writeEvaluationResult();
		return this.models;
	}

	/**
	 * If a result directory was specified, the models are written into that
	 * directory.
	 */
	private void writeEvaluationResult() {
		if (!StringUtils.isEmpty(evaluationResultDir) && models.length > 0) {
			ResourceSet resourceSet = models[0].eResource().getResourceSet();
			URI evaluationResultURI = URI.createFileURI(new File(
					evaluationResultDir).getAbsolutePath());
			for (Resource resource : resourceSet.getResources()) {
				resource.setURI(evaluationResultURI.appendSegment(resource
						.getURI().lastSegment()));
			}

			for (Resource resource : resourceSet.getResources()) {
				try {
					resource.save(null);
				} catch (IOException e) {
					getLogger().error(
							"Error writing evaluation result to "
									+ evaluationResultDir, e);
				}
			}

		}
	}
}
