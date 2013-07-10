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
   $Id: RandomCodeSampleStrategy.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.conqat.java.resource.IJavaElement;
import org.eclipse.jdt.internal.compiler.ASTVisitor;
import org.eclipse.jdt.internal.compiler.ast.ASTNode;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
import org.eclipse.jdt.internal.compiler.ast.LocalDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.BlockScope;
import org.eclipse.jdt.internal.compiler.lookup.MethodScope;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doc;
import com.sun.javadoc.ProgramElementDoc;

import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.MeasureBase;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.inspection.cm.Checklist;
import edu.tum.cs.conqat.inspection.cm.CmFactory;
import edu.tum.cs.conqat.inspection.cm.InspectionEvaluation;
import edu.tum.cs.conqat.inspection.cm.InspectionItem;
import edu.tum.cs.conqat.inspection.cm.InspectionMeasure;
import edu.tum.cs.conqat.inspection.cm.Sample;
import edu.tum.cs.conqat.java.library.JavaLibrary;

/**
 * {@ConQAT.Doc}
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: D917E9F1AE0F8C68AFB429C36B358A6F
 */
@AConQATProcessor(description = ""
		+ "A sample strategy that selects random samples for each measure based on given sample type.")
public class RandomCodeSampleStrategy extends SampleStrategyBase {

	/** The maximum number of measures to be inspected on one checklist. */
	private static final int maxMeasuresPerChecklist = 5;

	/** The default number of samples per measure. */
	private static final int defaultSamplesPerMeasure = 20;

	/** Contains all {@link Sample}s for one sample type. */
	private final Map<String, List<Sample>> sampleMap = new HashMap<String, List<Sample>>();

	/**
	 * The {@link ASTNode}s found by the visitor.
	 */
	private final List<ASTNode> foundASTNodes = new ArrayList<ASTNode>();

	/**
	 * Builds {@link InspectionItem}s according to sample types of each
	 * {@link InspectionMeasure} and distributes them to given inspectors.
	 */
	@Override
	public List<Checklist> generateChecklists(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments,
			List<String> inspectors) throws ConQATException {
		measuresAndInstruments = filterMissingSampleTypes(measuresAndInstruments);

		List<Checklist> checklists = createChecklistsWithMeasures(
				measuresAndInstruments, inspectors, maxMeasuresPerChecklist);

		List<IJavaElement> javaClassElements = findJavaClassElements();
		List<Sample> samples;
		for (MeasureBase measureBase : measuresAndInstruments.keySet()) {
			String sampleType = measureBase.getAnnotations().get("sample");

			if (sampleMap.containsKey(sampleType)) {
				samples = sampleMap.get(sampleType);
			} else {
				samples = getSamples(sampleType, javaClassElements);
				Collections.shuffle(samples);
				sampleMap.put(sampleType, samples);
			}
			String measureName = measureBase.getQualifiedName();
			int count = calculateCount(measureName, checklists.size(), samples
					.size());
			createInspectionItems(count, measureName, checklists, samples);
		}
		return checklists;
	}

	/**
	 * Filters all measures that have no sample type defined.
	 */
	private Map<MeasureBase, ManualInstrument> filterMissingSampleTypes(
			Map<MeasureBase, ManualInstrument> measuresAndInstruments) {
		Map<MeasureBase, ManualInstrument> filteredMeasuresInstruments = new HashMap<MeasureBase, ManualInstrument>();
		for (MeasureBase measureBase : measuresAndInstruments.keySet()) {
			String sampleType = measureBase.getAnnotations().get("sample");
			if (sampleType != null) {
				filteredMeasuresInstruments.put(measureBase,
						measuresAndInstruments.get(measureBase));
			}
		}
		return filteredMeasuresInstruments;
	}

	/**
	 * Gets samples for the specified sampleType. If sampleType is unknown, an
	 * exception is thrown.
	 */
	private List<Sample> getSamples(String sampleType,
			List<IJavaElement> javaClassElements) throws ConQATException {
		if (sampleType.equals("Identifier")) {
			return getIdentifierSamples(javaClassElements);
		}
		if (sampleType.equals("Documentation")) {
			return getDocumentationSamples(javaClassElements);
		}
		throw new ConQATException("No sampling method for sample type "
				+ sampleType + " defined");
	}

	/**
	 * Finds all identifiers by visiting the compiled javaClassElements by an
	 * {@link IdentifierVisitor}.
	 */
	private List<Sample> getIdentifierSamples(
			List<IJavaElement> javaClassElements) throws ConQATException {
		List<Sample> samples = new ArrayList<Sample>();
		for (IJavaElement element : javaClassElements) {
			CompilationUnitDeclaration unitDeclaration = JavaLibrary.getEcjAST(
					element).getCompilationUnitDeclaration();
			ASTVisitor visitor = new IdentifierVisitor();
			unitDeclaration.traverse(visitor, unitDeclaration.scope);
			unitDeclaration.cleanUp();
			String sourcePath = element.getLocation();
			for (ASTNode node : foundASTNodes) {
				StringBuffer buffer = new StringBuffer();
				node.print(0, buffer);
				String name = buffer.toString();
				Sample sample = CmFactory.eINSTANCE.createSample();
				sample.setName(name);
				sample.setSourcePath(sourcePath);
				sample.setSourceStart(node.sourceStart);
				sample.setSourceEnd(node.sourceEnd + 1);
				sample.setPackagePath(element.getId());
				samples.add(sample);
			}
			foundASTNodes.clear();
		}
		return samples;
	}

	/**
	 * Finds all javadoc comments by analyzing the javaClassElements using the
	 * {@link JavaLibrary}. Empty comments are filtered out.
	 */
	private List<Sample> getDocumentationSamples(
			List<IJavaElement> javaClassElements) throws ConQATException {
		List<Sample> samples = new ArrayList<Sample>();
		for (IJavaElement element : javaClassElements) {
			List<Doc> docs = new ArrayList<Doc>();
			JavaLibrary library = JavaLibrary.getInstance();
			ClassDoc classDoc = library.getDoc(element);
			docs.add(classDoc);
			docs.addAll(Arrays.asList(classDoc.fields()));
			docs.addAll(Arrays.asList(classDoc.methods()));
			docs.addAll(Arrays.asList(classDoc.constructors()));
			for (Doc doc : docs) {
				String commentText = doc.getRawCommentText();
				if (commentText.length() == 0) {
					continue;
				}
				Sample sample = CmFactory.eINSTANCE.createSample();
				sample.setName("/** " + commentText + " */");
				String sourcePath = doc.position().toString();
				sample.setSourcePath(sourcePath.substring(0, sourcePath
						.lastIndexOf(":")));
				String lineNumber = sourcePath.substring(sourcePath
						.lastIndexOf(":") + 1);
				sample.setLineNumber(Integer.parseInt(lineNumber));
				if (doc instanceof ProgramElementDoc) {
					sample.setPackagePath(((ProgramElementDoc) doc)
							.qualifiedName());
				} else {
					sample.setPackagePath(doc.position().toString());
				}
				samples.add(sample);
			}
		}
		return samples;
	}

	/**
	 * Creates inspection items from samples for given measure and attaches them
	 * to given checklists.
	 */
	private void createInspectionItems(int count, String measureName,
			List<Checklist> checklists, List<Sample> samples) {
		int offset = 0;
		for (Checklist checklist : checklists) {
			InspectionItem item = CmFactory.eINSTANCE.createInspectionItem();
			InspectionMeasure inspectionMeasure = null;
			for (InspectionMeasure inspMeasure : checklist
					.getInspectionMeasures()) {
				if (inspMeasure.getMeasureName().equals(measureName)) {
					inspectionMeasure = inspMeasure;
				}
			}
			if (inspectionMeasure == null) {
				continue;
			}
			item.getInspectionMeasures().add(inspectionMeasure);

			// samples
			List<Sample> sampleSubList = samples
					.subList(offset, offset + count);
			item.getSamples().addAll(sampleSubList);
			// evaluations
			for (Sample sample : sampleSubList) {
				InspectionEvaluation evaluation = CmFactory.eINSTANCE
						.createInspectionEvaluation();
				evaluation.setInspectionMeasure(inspectionMeasure);
				evaluation.setSample(sample);
				item.getInspectionEvaluations().add(evaluation);
			}
			checklist.getInspectionItems().add(item);
			offset += count;
		}
	}

	/**
	 * Calculates how many samples will be given for each measure to be
	 * inspected.
	 */
	private int calculateCount(String measureName, int checklistSize,
			int sampleSize) throws ConQATException {
		int count = defaultSamplesPerMeasure;
		if (sampleSize < checklistSize * count && !distributeMeasures) {
			count = sampleSize / checklistSize;
			if (count == 0) {
				throw new ConQATException("Not enough samples for measure "
						+ measureName + " found or too many inspectors given");
			}
		} else if (sampleSize < count && distributeMeasures) {
			throw new ConQATException("Not enough samples for measure "
					+ measureName + " found");
		}
		return count;
	}

	/**
	 * The {@link ASTVisitor} that finds all identifiers.
	 */
	class IdentifierVisitor extends ASTVisitor {

		/** {@inheritDoc} */
		@Override
		public boolean visit(LocalDeclaration localDeclaration, BlockScope scope) {
			foundASTNodes.add(localDeclaration);
			return true;
		}

		/** {@inheritDoc} */
		@Override
		public boolean visit(FieldDeclaration fieldDeclaration,
				MethodScope scope) {
			foundASTNodes.add(fieldDeclaration);
			return true;
		}

	}
}
