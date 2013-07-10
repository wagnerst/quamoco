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
 | de.quamoco.qm.guideline.generators
 |                                                                       |
   $Id: ModelExport.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2009 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package de.quamoco.qm.guideline.generators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Evaluation;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.Instrument;
import de.quamoco.qm.ManualInstrument;
import de.quamoco.qm.Measure;
import de.quamoco.qm.MeasureAggregation;
import de.quamoco.qm.MeasurementMethod;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Source;
import de.quamoco.qm.Tag;
import de.quamoco.qm.TextAggregation;
import de.quamoco.qm.TextEvaluation;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.docbook.ArticleClass;
import de.quamoco.qm.docbook.Itemizedlist;
import de.quamoco.qm.docbook.Listitem;
import de.quamoco.qm.docbook.SectionClass;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;
import edu.tum.cs.emf.commons.statistics.Statistics;

/**
 * Class for exporting the complete Quality Model using the JAXB Model. The
 * model export includes an entities section, a factors section, a measures
 * section, and a tag definitions section for each quality model.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 7453394D28B5314084E192FE75D5B725
 */
public class ModelExport extends JAXBDocbookGeneratorBase {

	/** {@inheritDoc} */
	@Override
	protected ArticleClass generateArticle(List<QualityModel> qualityModels) {
		ArticleClass article = getObjectFactory().createArticleClass();

		article.getLotsAndListClassAndAdmonClass().add(
				generateArticleInfo("Quality Model Export",
						"Exported from Quamoco Quality Models"));

		for (QualityModel model : qualityModels) {
			article.getLotsAndListClassAndAdmonClass().add(
					generateQualityModel(model));
		}

		return article;
	}

	/**
	 * Generates a module.
	 */
	private JAXBElement<SectionClass> generateQualityModel(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect1 = generateSectionWithTitle(getLabelProvider()
				.getText(model));
		sect1.getTocsAndLotsAndIndices().add(generateStatistics(model));
		sect1.getTocsAndLotsAndIndices().add(generateEntitiesSection(model));
		sect1.getTocsAndLotsAndIndices().add(generateFactorsSection(model));
		sect1.getTocsAndLotsAndIndices().add(generateMeasuresSection(model));
		sect1.getTocsAndLotsAndIndices().add(generateTagsSection(model));
		sect1.getTocsAndLotsAndIndices().add(generateSourcesSection(model));

		return getObjectFactory().createSection(sect1);
	}

	/**
	 * Generates a statistics itemized list.
	 */
	private JAXBElement<Itemizedlist> generateStatistics(EObject eObject) {
		List<Listitem> list = new ArrayList<Listitem>();
		Statistics stats = new Statistics(eObject);
		List<EClass> classes = stats.getEClasses();
		// remove quality model statistics since there's obviously only one
		// quality model
		classes.remove(QmPackage.eINSTANCE.getQualityModel());
		Collections.sort(classes, new Comparator<EClass>() {
			public int compare(EClass e1, EClass e2) {
				return ResourceLocatorUtils.getString(e1).compareTo(
						ResourceLocatorUtils.getString(e2));
			}
		});
		for (EClass eClass : classes) {
			list.add(generateListitem(ResourceLocatorUtils.getString(eClass)
					+ "s: " + stats.getNumberOf(eClass)));
		}
		return generateItemizedListWithTitle(list, "Statistics");
	}

	/**
	 * Generates the section documenting all {@link Entity}s.
	 */
	private JAXBElement<SectionClass> generateEntitiesSection(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect2 = generateSectionWithTitle("Entities");
		List<JAXBElement<SectionClass>> entitySections = new ArrayList<JAXBElement<SectionClass>>();
		for (Entity entity : model.getEntities()) {
			generateEntity(entity, entitySections);
		}
		sect2.getTocsAndLotsAndIndices().addAll(entitySections);
		return getObjectFactory().createSection(sect2);
	}

	/**
	 * Generates an {@link Entity}.
	 */
	private void generateEntity(Entity entity,
			Collection<JAXBElement<SectionClass>> entitySections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect3 = generateEObjectSection(entity, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(entity));

		// parent entities
		listItems.add(generateEnumerationListitem("Parent Entities", entity
				.getIsADirect()));

		// sub entities
		listItems.add(generateEnumerationListitem("Sub Entities", entity
				.getSpecializedByDirect()));

		// part of
		listItems.add(generateEObjectListitem("Part of", entity
				.getPartOfDirect()));

		// parts
		listItems.add(generateEnumerationListitem("Parts", entity
				.getConsistsOfDirect()));

		// factors
		listItems.add(generateEnumerationListitem("Characterized by", entity
				.getCharacterizedBy()));

		// sources
		listItems.add(generateEnumerationListitem("Originates from", entity
				.getOriginatesFrom()));

		// tags
		listItems
				.add(generateEnumerationListitem("Tags", entity.getTaggedBy()));

		sect3.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));
		entitySections.add(getObjectFactory().createSection(sect3));
	}

	/**
	 * Generates the section documenting all {@link Factor}s.
	 */
	private JAXBElement<SectionClass> generateFactorsSection(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect2 = generateSectionWithTitle("Factors");
		List<JAXBElement<SectionClass>> factorSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Factor factor : model.getFactors()) {
			generateFactor(factor, factorSections);
		}
		sect2.getTocsAndLotsAndIndices().addAll(factorSections);
		return getObjectFactory().createSection(sect2);
	}

	/**
	 * Generates a {@link Factor}.
	 */
	private void generateFactor(Factor factor,
			Collection<JAXBElement<SectionClass>> factorSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect3 = generateEObjectSection(factor, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(factor));

		// refines factors
		listItems.add(generateEnumerationListitem("Refines", factor
				.getRefinesDirect()));

		// refined by factors
		listItems.add(generateEnumerationListitem("Refined by", factor
				.getRefinedByDirect()));

		// entity
		listItems.add(generateEObjectListitem("Characterizes", factor
				.getCharacterizes()));

		// measures
		listItems.add(generateEnumerationListitem("Measured by", factor
				.getMeasuredByDirect()));

		// tags
		listItems
				.add(generateEnumerationListitem("Tags", factor.getTaggedBy()));

		// sources
		listItems.add(generateEnumerationListitem("Originates from", factor
				.getOriginatesFrom()));

		sect3.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		// refinements
		sect3.getTocsAndLotsAndIndices().add(
				generateRefinementsForFactorSection(factor));

		// impacts
		sect3.getTocsAndLotsAndIndices().add(
				generateImpactsForFactorSection(factor));

		// evaluations
		sect3.getTocsAndLotsAndIndices().add(
				generateEvaluationsForFactorSection(factor));

		factorSections.add(getObjectFactory().createSection(sect3));
	}

	/**
	 * Generates the section documenting all {@link FactorRefinement}s of a
	 * {@link Factor} .
	 */
	private JAXBElement<SectionClass> generateRefinementsForFactorSection(
			Factor factor) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect4 = generateSectionWithTitle("Refinements");
		List<JAXBElement<SectionClass>> refinementSections = new ArrayList<JAXBElement<SectionClass>>();
		for (FactorRefinement refinement : factor.getRefinedBy()) {
			generateRefinement(refinement, refinementSections);
		}
		sect4.getTocsAndLotsAndIndices().addAll(refinementSections);
		return getObjectFactory().createSection(sect4);
	}

	/**
	 * Generates a {@link FactorRefinement}.
	 */
	private void generateRefinement(FactorRefinement refinement,
			Collection<JAXBElement<SectionClass>> refinementSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect5 = generateEObjectSection(refinement,
				getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// refined by factor
		listItems.add(generateEObjectListitem("Refined by", refinement
				.getChild()));

		// refines factor
		listItems
				.add(generateEObjectListitem("Refines", refinement.getParent()));

		// tags
		listItems.add(generateEnumerationListitem("Tags", refinement
				.getTaggedBy()));

		sect5.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		refinementSections.add(getObjectFactory().createSection(sect5));
	}

	/**
	 * Generates the section documenting all {@link Impact}s of a {@link Factor}
	 * .
	 */
	private JAXBElement<SectionClass> generateImpactsForFactorSection(
			Factor factor) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect4 = generateSectionWithTitle("Impacts");
		List<JAXBElement<SectionClass>> impactSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Impact impact : factor.getInfluences()) {
			generateImpact(impact, impactSections);
		}
		sect4.getTocsAndLotsAndIndices().addAll(impactSections);
		return getObjectFactory().createSection(sect4);
	}

	/**
	 * Generates an {@link Impact}.
	 */
	private void generateImpact(Impact impact,
			Collection<JAXBElement<SectionClass>> impactSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect5 = generateEObjectSection(impact, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// influenced factor
		listItems
				.add(generateEObjectListitem("Influences", impact.getTarget()));

		// effect
		String effect = impact.getEffect().getLiteral();
		listItems.add(generateListitem("Effect: " + effect));

		listItems.add(generateJustificationListitem(impact));

		sect5.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		impactSections.add(getObjectFactory().createSection(sect5));
	}

	/**
	 * Generates the section documenting all {@link Evaluation}s of a
	 * {@link Factor} .
	 */
	private JAXBElement<SectionClass> generateEvaluationsForFactorSection(
			Factor factor) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect4 = generateSectionWithTitle("Evaluations");
		List<JAXBElement<SectionClass>> evaluationSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Evaluation evaluation : factor.getEvaluatedBy()) {
			if (evaluation instanceof TextEvaluation) {
				generateEvaluation((TextEvaluation) evaluation,
						evaluationSections);
			}
		}
		sect4.getTocsAndLotsAndIndices().addAll(evaluationSections);
		return getObjectFactory().createSection(sect4);
	}

	/**
	 * Generates an {@link Evaluation}.
	 */
	private void generateEvaluation(TextEvaluation evaluation,
			Collection<JAXBElement<SectionClass>> evaluationSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect5 = generateEObjectSection(evaluation,
				getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(evaluation));

		// evaluates
		listItems.add(generateEObjectListitem("Evaluates", evaluation
				.getEvaluates()));

		// specification
		listItems.add(generateStringListitem(evaluation, QmPackage.eINSTANCE
				.getTextEvaluation_Specification()));

		// tags
		listItems.add(generateEnumerationListitem("Tags", evaluation
				.getTaggedBy()));

		sect5.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		evaluationSections.add(getObjectFactory().createSection(sect5));
	}

	/**
	 * Generates the section documenting all {@link Measure}s.
	 */
	private JAXBElement<SectionClass> generateMeasuresSection(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect2 = generateSectionWithTitle("Measures");
		List<JAXBElement<SectionClass>> measureSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Measure measure : model.getMeasures()) {
			generateMeasure(measure, measureSections);
		}
		sect2.getTocsAndLotsAndIndices().addAll(measureSections);
		return getObjectFactory().createSection(sect2);
	}

	/**
	 * Generates a {@link Measure}.
	 */
	private void generateMeasure(Measure measure,
			Collection<JAXBElement<SectionClass>> measureSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect3 = generateEObjectSection(measure, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(measure));

		// factors
		listItems.add(generateEnumerationListitem("Measures Factors", measure
				.getMeasuresDirect()));

		// sources
		listItems.add(generateEnumerationListitem("Originates from", measure
				.getOriginatesFrom()));

		sect3.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		// derived measures
		sect3.getTocsAndLotsAndIndices().add(
				generateDerivedMeasuresSection(measure));

		// concrete measures
		sect3.getTocsAndLotsAndIndices().add(
				generateConcreteMeasuresSection(measure));

		measureSections.add(getObjectFactory().createSection(sect3));
	}

	/**
	 * Generates the section documenting all {@link MeasureAggregation}s of a
	 * {@link Measure}.
	 */
	private JAXBElement<SectionClass> generateDerivedMeasuresSection(
			Measure measure) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect4 = generateSectionWithTitle("Derived Measures");
		List<JAXBElement<SectionClass>> derivedMeasureSections = new ArrayList<JAXBElement<SectionClass>>();
		for (MeasurementMethod determination : measure.getDeterminedBy()) {
			if (determination instanceof TextAggregation) {
				generateDerivedMeasure((TextAggregation) determination,
						derivedMeasureSections);
			}
		}
		sect4.getTocsAndLotsAndIndices().addAll(derivedMeasureSections);
		return getObjectFactory().createSection(sect4);
	}

	/**
	 * Generates a {@link TextAggregation}.
	 */
	private void generateDerivedMeasure(TextAggregation derivedMeasure,
			Collection<JAXBElement<SectionClass>> derivedMeasureSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect5 = generateEObjectSection(derivedMeasure,
				getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// specification
		listItems.add(generateStringListitem(derivedMeasure,
				QmPackage.eINSTANCE.getTextAggregation_Specification()));

		// tags
		listItems.add(generateEnumerationListitem("Tags", derivedMeasure
				.getTaggedBy()));

		sect5.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		derivedMeasureSections.add(getObjectFactory().createSection(sect5));
	}

	/**
	 * Generates the section documenting all {@link Instrument}s of a
	 * {@link Measure}.
	 */
	private JAXBElement<SectionClass> generateConcreteMeasuresSection(
			Measure measure) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect4 = generateSectionWithTitle("Concrete Measures");
		List<JAXBElement<SectionClass>> concreteMeasureSections = new ArrayList<JAXBElement<SectionClass>>();
		for (MeasurementMethod determination : measure.getDeterminedBy()) {
			if (determination instanceof Instrument) {
				generateConcreteMeasure((Instrument) determination,
						concreteMeasureSections);
			}
		}
		sect4.getTocsAndLotsAndIndices().addAll(concreteMeasureSections);
		return getObjectFactory().createSection(sect4);
	}

	/**
	 * Generates a {@link Instrument}.
	 */
	private void generateConcreteMeasure(Instrument concreteMeasure,
			Collection<JAXBElement<SectionClass>> concreteMeasureSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect5 = generateEObjectSection(concreteMeasure,
				getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		if (concreteMeasure instanceof ManualInstrument) {
			ManualInstrument manInstr = (ManualInstrument) concreteMeasure;

			// type
			listItems.add(generateListitem("Manual Instrument"));

			// description
			listItems.add(generateDescriptionListitem(manInstr));
		}

		else if (concreteMeasure instanceof ToolBasedInstrument) {
			ToolBasedInstrument toolInstr = (ToolBasedInstrument) concreteMeasure;

			// type
			listItems.add(generateListitem("Tool based Instrument"));

			// tool
			listItems.add(generateEObjectListitem("Tool", toolInstr.getTool()));

			// metric
			String metric = toolInstr.getMetric();
			listItems.add(generateListitem("Metric: " + metric));
		}
		// tags
		listItems.add(generateEnumerationListitem("Tags", concreteMeasure
				.getTaggedBy()));

		// sources
		listItems.add(generateEnumerationListitem("Originates from",
				concreteMeasure.getOriginatesFrom()));

		sect5.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		concreteMeasureSections.add(getObjectFactory().createSection(sect5));
	}

	/**
	 * Generates the section documenting all {@link Tag} definitions.
	 */
	private JAXBElement<SectionClass> generateTagsSection(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect2 = generateSectionWithTitle("Tags");
		List<JAXBElement<SectionClass>> tagSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Tag tag : model.getTags()) {
			generateTag(tag, tagSections);
		}
		sect2.getTocsAndLotsAndIndices().addAll(tagSections);
		return getObjectFactory().createSection(sect2);
	}

	/**
	 * Generates a {@link Tag}.
	 */
	private void generateTag(Tag tag,
			Collection<JAXBElement<SectionClass>> tagSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect3 = generateEObjectSection(tag, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(tag));

		// tagged elements
		listItems.add(generateEnumerationListitem("Originates from", tag
				.getOriginatesFrom()));

		// tags
		listItems.add(generateEnumerationListitem("Tags", tag.getTaggedBy()));

		sect3.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		tagSections.add(getObjectFactory().createSection(sect3));
	}

	/**
	 * Generates the section documenting all {@link Source} definitions.
	 */
	private JAXBElement<SectionClass> generateSourcesSection(QualityModel model) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect2 = generateSectionWithTitle("Sources");
		List<JAXBElement<SectionClass>> sourceSections = new ArrayList<JAXBElement<SectionClass>>();
		for (Source source : model.getSources()) {
			generateSource(source, sourceSections);
		}
		sect2.getTocsAndLotsAndIndices().addAll(sourceSections);
		return getObjectFactory().createSection(sect2);
	}

	/**
	 * Generates a {@link Source}.
	 */
	private void generateSource(Source source,
			Collection<JAXBElement<SectionClass>> sourceSections) {
		// Names of sections (sect1, sect2, etc.) are supposed to show hierarchy
		// in sections.
		SectionClass sect3 = generateEObjectSection(source, getLabelProvider());
		List<Listitem> listItems = new ArrayList<Listitem>();

		// description
		listItems.add(generateDescriptionListitem(source));

		// tags
		listItems
				.add(generateEnumerationListitem("Tags", source.getTaggedBy()));

		sect3.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		sourceSections.add(getObjectFactory().createSection(sect3));
	}
}
