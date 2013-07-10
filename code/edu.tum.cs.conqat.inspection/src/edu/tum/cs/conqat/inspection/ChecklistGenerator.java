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

/*-----------------------------------------------------------------------+
 | edu.tum.cs.conqat.quamoco.inspection
 |                                                                       |
   $Id: ChecklistGenerator.java 29845 2010-08-23 19:17:49Z niessner $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package edu.tum.cs.conqat.inspection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import de.quamoco.qm.ImplementingMeasure;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureBase;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.util.QmModelUtils;
import edu.tum.cs.conqat.commons.ConQATParamDoc;
import edu.tum.cs.conqat.commons.ConQATProcessorBase;
import edu.tum.cs.conqat.core.AConQATAttribute;
import edu.tum.cs.conqat.core.AConQATParameter;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.Documentation;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.sampling.SampleStrategyBase;

/**
 * {@ConQAT.Doc}
 * 
 * @author niessner
 * @author $Author: niessner $
 * @version $Rev: 29845 $
 * @levd.rating YELLOW Hash: 14BFBA3D179616B6692766941374203D
 */
@AConQATProcessor(description = "This Processor performs the actual generation of checklists."
		+ "It saves the checklists that have to be completed by the inspectors"
		+ "into the specified output directory.")
public class ChecklistGenerator extends ConQATProcessorBase {

	/** The Quality Model. */
	private QualityModel[] models;

	/** QM model file name. */
	private String qmFilePath;

	/** The Inspectors the checklists should be completed by. */
	private final List<String> inspectors = new ArrayList<String>();

	/** The Sample Strategy to be used. */
	private SampleStrategyBase sampleStrategy;

	/** The output directory the checklists will be saved to. */
	private File outputDir;

	/** The quality models including the required quality models. */
	List<QualityModel> qualityModels;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "models", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The quality models.")
	public void setQualityModels(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) QualityModel[] models) {
		this.models = models;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = ConQATParamDoc.INPUT_NAME, minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Path of the model file.")
	public void addInputFile(
			@AConQATAttribute(name = "file", description = "Qm file path") String qmFilePath) {
		this.qmFilePath = qmFilePath;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "inspector", minOccurrences = 1, description = ""
			+ "An Inspector.")
	public void setInspectors(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) String inspector) {
		inspectors.add(inspector);
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "sampleStrategy", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The sample strategy to be used.")
	public void setSampleStrategy(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) SampleStrategyBase sampleStrategy) {
		this.sampleStrategy = sampleStrategy;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "output", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The output directory the checklists will be saved to.")
	public void setOutputDir(
			@AConQATAttribute(name = "dir", description = ConQATParamDoc.INPUT_REF_DESC) File outputDir) {
		this.outputDir = outputDir;
	}

	/** {@ConQAT.Doc} */
	@Override
	public Object process() throws ConQATException {
		Map<MeasureBase, ManualInstrument> measuresAndInstruments = new HashMap<MeasureBase, ManualInstrument>();
		qualityModels = new ArrayList<QualityModel>();
		for (QualityModel model : models) {
			qualityModels.addAll(QmModelUtils.getRequiredQualityModels(model));
		}
		for (QualityModel model : qualityModels) {
			for (MeasureBase measureBase : model.getMeasures()) {
				List<MeasurementMethod> measurementMethods = measureBase
						.getDeterminedBy();
				for (MeasurementMethod measurementMethod : measurementMethods) {
					if (measurementMethod instanceof ManualInstrument) {
						measuresAndInstruments.put(measureBase,
								(ManualInstrument) measurementMethod);
					}
				}
			}
		}
		filterMeasures(measuresAndInstruments);
		String qmFileName = (new File(qmFilePath)).getName();
		List<Checklist> checklists = sampleStrategy.generateChecklists(
				measuresAndInstruments, inspectors);
		if (checklists != null) {
			for (Checklist checklist : checklists) {
				checklist.setQmFileName(qmFileName);
				saveChecklist(checklist);
			}
		}
		return null;
	}

	/**
	 * Finds measures that are overwritten by {@link ImplementingMeasure}s of
	 * other Quality Models and deletes them.
	 */
	private void filterMeasures(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments) {
		for (MeasureBase measureBase : measuresAndInstruments.keySet()) {
			if (measureBase instanceof Measure) {
				Measure measure = (Measure) measureBase;
				List<ImplementingMeasure> implementingMeasures = QmModelUtils
						.getInverse(QmPackage.eINSTANCE
								.getImplementingMeasure_Implements(), measure,
								ImplementingMeasure.class);
				if (!implementingMeasures.isEmpty()) {
					measuresAndInstruments.remove(measureBase);
				}
			}
		}

	}

	/**
	 * Zips and saves the Checklist in the output directory.
	 */
	private void saveChecklist(Checklist checklist) throws ConQATException {
		ZipOutputStream zipOutput = generateZipArchive(checklist);

		Resource resource = new XMIResourceImpl();
		resource.getContents().add(checklist);
		try {
			zipOutput.putNextEntry(new ZipEntry("Checklist"));
			resource.save(zipOutput, Collections.emptyMap());
			zipOutput.closeEntry();
			zipOutput.close();
		} catch (IOException e) {
			throw new ConQATException(e);
		}
	}

	/**
	 * Generates one ZIP Archive including the attached files and the checklist.
	 */
	private ZipOutputStream generateZipArchive(Checklist checklist)
			throws ConQATException {
		Set<String> pathSet = new HashSet<String>();
		for (InspectionMeasure measure : checklist.getInspectionMeasures()) {
			for (Documentation documentation : measure.getDocumentations()) {
				if (documentation.getType().equals("file")) {
					pathSet.add(documentation.getPath());
				}
			}
		}
		// add .qm files to zip archive
		pathSet.add(qmFilePath);
		for (QualityModel qualityModel : qualityModels) {
			String filePath = qualityModel.eResource().getURI().toFileString();
			pathSet.add(filePath);
		}
		String zipPath = outputDir.getAbsolutePath() + "\\Checklist" + "_"
				+ checklist.getInspector() + ".cm";
		byte[] buffer = new byte[1024];
		ZipOutputStream zipOutput = null;
		try {
			zipOutput = new ZipOutputStream(new FileOutputStream(zipPath));
			for (String path : pathSet) {
				FileInputStream in = new FileInputStream(path);
				File entryFile = new File(path);
				zipOutput.putNextEntry(new ZipEntry(entryFile.getName()));
				int length;
				while ((length = in.read(buffer)) > 0) {
					zipOutput.write(buffer, 0, length);
				}
				zipOutput.closeEntry();
				in.close();
			}
		} catch (IOException e) {
			throw new ConQATException(e.getMessage());
		}
		return zipOutput;
	}

}
