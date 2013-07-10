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
 | de.quamoco.qm.guideline
 |                                                                       |
   $Id: DeveloperGuideline.java 4974 2012-02-17 09:34:10Z lochmann $            
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
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import de.quamoco.qm.Effect;
import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.Impact;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.docbook.ArticleClass;
import de.quamoco.qm.docbook.Listitem;
import de.quamoco.qm.docbook.SectionClass;

/**
 * Class for generating a developer guideline using the JAXB Model. The
 * developer guideline uses the entity tree structure and shows all factors of
 * an entity including their impacts on quality aspects. The developer guideline
 * is supposed to point out the quality requirements.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: D4EF0A3839EFF0209C7D38F1D605C9F1
 */
public class DeveloperGuideline extends JAXBDocbookGeneratorBase {

	/** {@inheritDoc} */
	@Override
	protected ArticleClass generateArticle(List<QualityModel> qualityModels) {
		ArticleClass article = getObjectFactory().createArticleClass();

		article.getLotsAndListClassAndAdmonClass().add(
				generateArticleInfo("Developer Guideline",
						"Exported from Quamoco Modules"));

		for (QualityModel model : qualityModels) {
			article.getLotsAndListClassAndAdmonClass().add(
					generateQualityModel(model));
		}

		return article;
	}

	/**
	 * Generates a quality model.
	 */
	private JAXBElement<SectionClass> generateQualityModel(QualityModel model) {
		SectionClass section = generateSectionWithTitle(getLabelProvider()
				.getText(model));
		for (Factor factor : model.getFactors()) {
			generateFactor(factor, section);
		}
		return getObjectFactory().createSection(section);
	}

	/**
	 * Generates a {@link Factor}.
	 */
	private void generateFactor(Factor factor, SectionClass parentSection) {
		AdapterFactoryLabelProvider labelProvider = getLabelProvider();
		String factorLabel = labelProvider.getText(factor);
		SectionClass sect = generateSectionWithTitle(factorLabel);
		sect.setId(getLinkID(factor));
		sect.setXreflabel(factorLabel);
		List<Listitem> listItems = new ArrayList<Listitem>();

		// Entity description
		Entity entity = factor.getCharacterizes();
		if (entity != null) {
			String entityLabel = labelProvider.getText(entity);
			String entityDescr = factor.getCharacterizes().getDescription();
			if (entityDescr == null) {
				entityDescr = "(Entity not described)";
			}
			listItems.add(generateListitem("Characterized by " + entityLabel
					+ ": " + entityDescr));
		}

		// Factor description
		String factorDescr = factor.getDescription();
		if (factorDescr == null) {
			factorDescr = "(Factor not described)";
		}
		listItems.add(generateListitem(factorLabel + ": " + factorDescr));

		sect.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));
		listItems.clear();

		// Refines
		listItems.add(generateEnumerationListitem("Refines Factors", factor
				.getRefinesDirect()));

		// Refined by
		listItems.add(generateEnumerationListitem("Refined by Factors", factor
				.getRefinedByDirect()));

		sect.getTocsAndLotsAndIndices().add(generateItemizedList(listItems));

		listItems.clear();

		// Influences
		List<Impact> impacts = new ArrayList<Impact>(factor.getInfluences());
		if (!impacts.isEmpty()) {
			sortImpacts(impacts);
			for (Impact impact : impacts) {
				listItems.add(generateInfluencesImpact(impact));
			}
			sect.getTocsAndLotsAndIndices().add(
					generateItemizedListWithTitle(listItems, factorLabel
							+ " influences Factors:"));
			listItems.clear();
		}

		// Influenced by
		impacts = new ArrayList<Impact>(factor.getInfluencedBy());
		if (!impacts.isEmpty()) {
			sortImpacts(impacts);
			for (Impact impact : impacts) {
				listItems.add(generateInfluencedByImpact(impact));
			}
			sect.getTocsAndLotsAndIndices().add(
					generateItemizedListWithTitle(listItems, factorLabel
							+ " influenced by Factors:"));
		}

		parentSection.getTocsAndLotsAndIndices().add(
				getObjectFactory().createSection(sect));
	}

	/**
	 * Generates an {@link Impact} that influences other {@link Factor}s.
	 */
	private Listitem generateInfluencesImpact(Impact impact) {
		String factorLabel = getLabelProvider().getText(impact.getSource());
		return generateImpact(impact, Collections
				.singletonList((Object) ("Influences " + factorLabel)));
	}

	/**
	 * Generates an {@link Impact}.
	 */
	private Listitem generateImpact(Impact impact, List<Object> label) {
		List<Object> list = new ArrayList<Object>();
		list.addAll(label);
		Effect effect = impact.getEffect();
		String effectLiteral;
		if (effect == null) {
			effectLiteral = "(Effect not set)";
		} else {
			effectLiteral = effect.getLiteral();
		}
		String justification = impact.getJustification();
		if (justification == null) {
			justification = "(Impact not justified)";
		}
		list.add(" in a " + effectLiteral + " way. " + justification);
		return generateListitem(list);
	}

	/**
	 * Sorts impacts based on their effect in the following way: POSITIVE
	 * effect, NEGATIVE effect, unset effect
	 */
	private void sortImpacts(List<Impact> impacts) {
		List<Impact> positives = new ArrayList<Impact>();
		List<Impact> negatives = new ArrayList<Impact>();
		List<Impact> unsets = new ArrayList<Impact>();
		for (Impact impact : impacts) {
			Effect effect = impact.getEffect();
			if (effect == null) {
				unsets.add(impact);
			} else if (effect.getLiteral().equals("POSITIVE")) {
				positives.add(impact);
			} else if (effect.getLiteral().equals("NEGATIVE")) {
				negatives.add(impact);
			}
		}
		impacts.clear();
		impacts.addAll(positives);
		impacts.addAll(negatives);
		impacts.addAll(unsets);
	}

	/**
	 * Generates an {@link Impact} that influences the documented {@link Factor}
	 * .
	 */
	private Listitem generateInfluencedByImpact(Impact impact) {
		List<Object> list = new ArrayList<Object>();
		list.add("Influenced by ");
		list.add(generateXref(impact.getSource()));
		return generateImpact(impact, list);
	}

}
