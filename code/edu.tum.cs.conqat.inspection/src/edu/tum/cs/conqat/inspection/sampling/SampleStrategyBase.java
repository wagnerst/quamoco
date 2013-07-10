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
   $Id: SampleStrategyBase.java 4974 2012-02-17 09:34:10Z lochmann $            
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
package edu.tum.cs.conqat.inspection.sampling;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.conqat.java.resource.IJavaElement;
import org.conqat.java.resource.IJavaResource;
import org.conqat.java.resource.JavaElementUtils;
import org.eclipse.emf.common.util.EMap;

import de.quamoco.qm.ImplementingMeasure;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureBase;
import edu.tum.cs.conqat.commons.ConQATParamDoc;
import edu.tum.cs.conqat.commons.ConQATProcessorBase;
import edu.tum.cs.conqat.core.AConQATAttribute;
import edu.tum.cs.conqat.core.AConQATParameter;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmFactory;
import edu.tum.cs.conqat.inspection.cm.Documentation;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;

/**
 * The Base Class for all Sample Strategies.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: B03FC9596F89AC1C22D51702660C5F04
 */
public abstract class SampleStrategyBase extends ConQATProcessorBase {

	/** The root {@link IJavaResource}. */
	protected IJavaResource javaRoot;

	/**
	 * Indicates whether measures are distributed among inspectors or each
	 * inspector inspects all measures.
	 */
	boolean distributeMeasures;

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "java", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The Java source code.")
	public void setJavaRoot(
			@AConQATAttribute(name = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_REF_DESC) IJavaResource javaRoot) {
		this.javaRoot = javaRoot;
	}

	/** {@inheritDoc} */
	@Override
	public SampleStrategyBase process() {
		return this;
	}

	/**
	 * Template method that allows deriving classes to implement their own
	 * strategy of finding samples and putting together checklists for the given
	 * inspectors.
	 */
	public abstract List<Checklist> generateChecklists(
			Map<MeasureBase, ManualInstrument> measures, List<String> inspectors)
			throws ConQATException;

	/**
	 * Returns all {@link IJavaElement}s of the root {@link IJavaResource}.
	 */
	protected List<IJavaElement> findJavaClassElements() {
		return JavaElementUtils.listJavaElements(javaRoot);
	}

	/**
	 * Creates one checklist per inspector, distributing and attaching
	 * inspection measures.
	 * 
	 * @param measuresAndInstruments
	 *            the map containing {@link MeasureBase}s and
	 *            {@link ManualInstrument}s.
	 * @param inspectors
	 *            the inspectors given.
	 * @param maxMeasuresPerChecklist
	 *            maximum number of measures per checklist.
	 * 
	 * @throws ConQATException
	 *             if checklists cannot be created due to a too little or large
	 *             ratio between measures and inspectors.
	 */
	protected List<Checklist> createChecklistsWithMeasures(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments,
			List<String> inspectors, int maxMeasuresPerChecklist)
			throws ConQATException {
		List<Checklist> checklists = new ArrayList<Checklist>();
		double ratioMeasuresInspectors = measuresAndInstruments.size()
				/ inspectors.size();
		if (ratioMeasuresInspectors == 0) {
			throw new ConQATException(
					"There are too many inspectors given for too few manual measures");
		} else if (ratioMeasuresInspectors > maxMeasuresPerChecklist) {
			throw new ConQATException(
					"There are too few inspectors given for too many manual measures");
		}
		distributeMeasures = measuresAndInstruments.size() > maxMeasuresPerChecklist;
		for (String inspector : inspectors) {
			Checklist checklist = CmFactory.eINSTANCE.createChecklist();
			checklist.setInspector(inspector);
			if (!distributeMeasures) {
				// This extra call is necessary as the measures will not be kept
				// in the checklist otherwise...
				List<InspectionMeasure> inspMeasures = generateInspectionMeasures(measuresAndInstruments);
				checklist.getInspectionMeasures().addAll(inspMeasures);
			}
			checklists.add(checklist);
		}
		if (distributeMeasures) {
			List<InspectionMeasure> inspectionMeasures = generateInspectionMeasures(measuresAndInstruments);
			distributeMeasuresToChecklists(checklists, inspectionMeasures);
		}
		return checklists;
	}

	/**
	 * Generates {@link InspectionMeasure}s from the given QM-{@link Measure}s.
	 */
	private List<InspectionMeasure> generateInspectionMeasures(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments) {
		List<InspectionMeasure> inspectionMeasures = new ArrayList<InspectionMeasure>();
		for (MeasureBase measure : measuresAndInstruments.keySet()) {
			InspectionMeasure inspectionMeasure = CmFactory.eINSTANCE
					.createInspectionMeasure();
			inspectionMeasure.setMeasureName(measure.getQualifiedName());
			inspectionMeasure.setInstrumentInfo(measuresAndInstruments.get(
					measure).getDescription());
			String measureInfo = "";
			if (measure instanceof Measure) {
				measureInfo = ((Measure) measure).getDescription();
			} else {
				measureInfo = ((ImplementingMeasure) measure).getDescription();
			}
			inspectionMeasure.setMeasureInfo(measureInfo);
			EMap<String, String> annotations = measure.getAnnotations();
			String sampleType = annotations.get("sample");
			if (sampleType != null) {
				inspectionMeasure.setSampleType(sampleType);
			}
			inspectionMeasure.getDocumentations().addAll(
					getDocumentation(annotations));
			inspectionMeasures.add(inspectionMeasure);
		}
		return inspectionMeasures;
	}

	/**
	 * Collects and returns all links to {@link Documentation} in the
	 * annotations.
	 */
	private List<Documentation> getDocumentation(
			EMap<String, String> annotations) {
		List<Documentation> documentations = new ArrayList<Documentation>();
		for (Entry<String, String> annotation : annotations) {
			String value = annotation.getValue();
			if (isValidURL(value) || isValidPath(value)) {
				String key = annotation.getKey();
				Documentation documentation = CmFactory.eINSTANCE
						.createDocumentation();
				documentation.setName(key);
				documentation.setPath(value);
				if (isValidURL(value)) {
					documentation.setType("url");
				}
				if (isValidPath(value)) {
					documentation.setType("file");
				}
				documentations.add(documentation);
			}
		}
		return documentations;
	}

	/**
	 * Checks whether the given String is a valid URL.
	 */
	protected boolean isValidURL(String string) {
		try {
			new URL(string);
		} catch (MalformedURLException e) {
			return false;
		}
		return true;
	}

	/**
	 * Checks whether the given String is a path to an existing file.
	 */
	protected boolean isValidPath(String path) {
		File file = new File(path);
		return file.exists() && !file.isDirectory();
	}

	/**
	 * Distributes all {@link InspectionMeasure}s to checklists in a round robin
	 * way.
	 */
	private void distributeMeasuresToChecklists(List<Checklist> checklists,
			List<InspectionMeasure> inspectionMeasures) {
		int currentChecklist = 0;
		for (InspectionMeasure measure : inspectionMeasures) {
			checklists.get(currentChecklist++).getInspectionMeasures().add(
					measure);
			currentChecklist %= checklists.size();
		}
	}

}
