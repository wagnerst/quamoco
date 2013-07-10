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
   $Id: JAXBDocbookGeneratorBase.java 4974 2012-02-17 09:34:10Z lochmann $            
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

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;

import de.quamoco.qm.DescribedElement;
import de.quamoco.qm.Impact;
import de.quamoco.qm.NamedElement;
import de.quamoco.qm.QmPackage;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.docbook.Anchor;
import de.quamoco.qm.docbook.ArticleClass;
import de.quamoco.qm.docbook.Articleinfo;
import de.quamoco.qm.docbook.Date;
import de.quamoco.qm.docbook.Itemizedlist;
import de.quamoco.qm.docbook.Listitem;
import de.quamoco.qm.docbook.ObjectFactory;
import de.quamoco.qm.docbook.Para;
import de.quamoco.qm.docbook.SectionClass;
import de.quamoco.qm.docbook.Subtitle;
import de.quamoco.qm.docbook.Title;
import de.quamoco.qm.docbook.Xref;
import de.quamoco.qm.guideline.extension.IGenerator;
import de.quamoco.qm.provider.QmEditPlugin;
import edu.tum.cs.emf.commons.resources.ResourceLocatorUtils;

/**
 * Base class for creating a docbook XML using the JAXB model.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: F685630CD5B8A2BE0825CFFD9D438409
 */
public abstract class JAXBDocbookGeneratorBase implements IGenerator {

	/**
	 * The {@link ObjectFactory}.
	 */
	private final ObjectFactory objectFactory = new ObjectFactory();

	/**
	 * The {@link QualityModel}s to be exported.
	 */
	private List<QualityModel> qualityModels;

	/**
	 * The {@link AdapterFactoryLabelProvider}.
	 */
	private AdapterFactoryLabelProvider labelProvider;

	/** {@inheritDoc} */
	public void generateXML(List<QualityModel> qualityModels, OutputStream out)
			throws IOException {
		this.qualityModels = qualityModels;
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory);

		ArticleClass article = generateArticle(qualityModels);

		adapterFactory.dispose();
		labelProvider.dispose();

		save(article, out);
	}

	/**
	 * Generates a docbook {@link ArticleClass}. Subclasses must implement this
	 * method in order to create the docbook xml that will be saved to a file.
	 * 
	 * @param qualityModels
	 *            the {@link QualityModel}s to be exported.
	 */
	protected abstract ArticleClass generateArticle(
			List<QualityModel> qualityModels);

	/**
	 * Creates the ArticleInfo containing a title, date, and subtitle.
	 * 
	 * @param titleString
	 *            the title.
	 * 
	 * @param subtitleString
	 *            the subtitle.
	 */
	protected Articleinfo generateArticleInfo(String titleString,
			String subtitleString) {
		Articleinfo articleInfo = objectFactory.createArticleinfo();
		Title title = objectFactory.createTitle();
		title.getContent().add(titleString);
		articleInfo.getGraphicsAndMediaobjectsAndLegalnotices().add(title);

		Date date = objectFactory.createDate();
		articleInfo.getGraphicsAndMediaobjectsAndLegalnotices().add(date);

		Subtitle subtitle = objectFactory.createSubtitle();
		subtitle.getContent().add(subtitleString);
		articleInfo.getGraphicsAndMediaobjectsAndLegalnotices().add(subtitle);
		return articleInfo;
	}

	/**
	 * Generates a {@link Para} containing the specified text.
	 * 
	 * @param text
	 *            the text to be displayed in the paragraph.
	 * @returns a {@link JAXBElement} that can be added to an
	 *          {@link ArticleClass}, {@link Listitem}, or arbitrary section,
	 *          for example.
	 */
	protected JAXBElement<Para> generatePara(String text) {
		Para para = objectFactory.createPara();
		para.getContent().add(text);
		return objectFactory.createPara(para);
	}

	/**
	 * Generates a section for an {@link EObject} containing the name of the
	 * object as {@link Title} and label for x-references, and a linkID.
	 * 
	 * @returns a {@link SectionClass} that needs to be wrapped by
	 *          objFactory.createSection(...) to be able to be added to an
	 *          {@link ArticleClass}, for example.
	 */
	protected SectionClass generateEObjectSection(EObject object,
			ILabelProvider labelProvider) {
		SectionClass sect = generateSectionWithTitle(labelProvider
				.getText(object));
		sect.setId(getLinkID(object));
		sect.setXreflabel(labelProvider.getText(object));
		return sect;
	}

	/**
	 * Generates a section with the specified title. If the title is null, an
	 * empty String will be displayed.
	 * 
	 * @returns a {@link SectionClass} that needs to be wrapped by
	 *          objFactory.createSection(...) to be able to be added to an
	 *          {@link ArticleClass}, for example.
	 */
	protected SectionClass generateSectionWithTitle(String titleString) {
		SectionClass sect = objectFactory.createSectionClass();
		sect.getTocsAndLotsAndIndices().add(generateTitle(titleString));
		return sect;
	}

	/**
	 * Generates a {@link Title}. If the specified titleString is null, an empty
	 * String as title will be generated.
	 */
	protected Title generateTitle(String titleString) {
		Title title = objectFactory.createTitle();
		if (titleString == null) {
			titleString = "";
		}
		title.getContent().add(titleString);
		return title;
	}

	/**
	 * Generates an {@link Itemizedlist} from a list of {@link Listitem}s with
	 * the specified title.
	 * 
	 * @returns a {@link JAXBElement} that can be added to an
	 *          {@link ArticleClass} or arbitrary section, for example.
	 */
	protected JAXBElement<Itemizedlist> generateItemizedListWithTitle(
			List<Listitem> listitems, String title) {
		Itemizedlist itemizedList = objectFactory.createItemizedlist();
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().add(
				generateTitle(title));
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().addAll(
				listitems);
		return objectFactory.createItemizedlist(itemizedList);
	}

	/**
	 * Generates an {@link Itemizedlist} from a list of {@link Listitem}s with
	 * the specified title as Xref (if possible to be referenced, otherwise as
	 * String) and specified text in a paragraph.
	 * 
	 * @returns a {@link JAXBElement} that can be added to an
	 *          {@link ArticleClass} or arbitrary section, for example.
	 */
	protected JAXBElement<Itemizedlist> generateItemizedListWithTitleAndPara(
			List<Listitem> listitems, String title, String paraText) {
		Itemizedlist itemizedList = objectFactory.createItemizedlist();
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().add(
				generateTitle(title));
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().add(
				generatePara(paraText));
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().addAll(
				listitems);
		return objectFactory.createItemizedlist(itemizedList);
	}

	/**
	 * Generates an {@link Itemizedlist} from a list of {@link Listitem}s.
	 * 
	 * @returns a {@link JAXBElement} that can be added to an
	 *          {@link ArticleClass} or arbitrary section, for example.
	 */
	protected JAXBElement<Itemizedlist> generateItemizedList(List<Listitem> list) {
		Itemizedlist itemizedList = objectFactory.createItemizedlist();
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().addAll(
				list);
		return objectFactory.createItemizedlist(itemizedList);
	}

	/**
	 * Generates an {@link Itemizedlist} from a list of {@link Listitem}s with
	 * specified marks (disc, circle, square).
	 * 
	 * @returns a {@link JAXBElement} that can be added to an
	 *          {@link ArticleClass} or arbitrary section, for example.
	 */
	protected JAXBElement<Itemizedlist> generateItemizedList(
			List<Listitem> list, String mark) {
		Itemizedlist itemizedList = objectFactory.createItemizedlist();
		itemizedList.getAdmonClassAndLinespecificClassAndSynopClass().addAll(
				list);
		itemizedList.setMark(mark);
		return objectFactory.createItemizedlist(itemizedList);
	}

	/**
	 * Wraps all parts of a String containing line breaks into paragraphs in
	 * order to preserve the line breaks.
	 */
	protected List<JAXBElement<Para>> replaceLineBreaks(String text) {
		List<JAXBElement<Para>> paraList = new ArrayList<JAXBElement<Para>>();
		text.replaceAll("\r", "");
		String[] lines = text.split("\n");
		for (String line : lines) {
			paraList.add(generatePara(line));
		}
		return paraList;
	}

	/**
	 * Generates a list item containing text.
	 */
	protected Listitem generateListitem(String text) {
		Para para = objectFactory.createPara();
		para.getContent().add(text);
		Listitem listItem = objectFactory.createListitem();
		listItem.getListClassAndAdmonClassAndLinespecificClass().add(
				objectFactory.createPara(para));
		return listItem;
	}

	/**
	 * Generates a {@link Listitem} containing the description of a
	 * {@link DescribedElement}.
	 */
	protected Listitem generateDescriptionListitem(DescribedElement element) {
		return generateStringListitem(element, QmPackage.eINSTANCE
				.getDescribedElement_Description());
	}

	/**
	 * Generates a {@link Listitem} containing the justification of an
	 * {@link Impact}.
	 */
	protected Listitem generateJustificationListitem(Impact impact) {
		return generateStringListitem(impact, QmPackage.eINSTANCE
				.getImpact_Justification());
	}

	/**
	 * Generates a {@link Listitem} containing the specified {@link EAttribute}
	 * (which needs to be a String) of an {@link EObject}.
	 */
	protected Listitem generateStringListitem(EObject object,
			EAttribute attribute) {
		String attr = (String) object.eGet(attribute);
		String key = "_UI_" + attribute.getEContainingClass().getName() + "_"
				+ attribute.getName() + "_feature";
		if (attr != null) {
			List<Object> list = new ArrayList<Object>();
			list.add(generatePara(QmEditPlugin.INSTANCE.getString(key) + ":"));
			list.addAll(replaceLineBreaks(attr));
			Listitem listItem = objectFactory.createListitem();
			listItem.getListClassAndAdmonClassAndLinespecificClass().addAll(
					list);
			return listItem;
		}
		return generateListitem(QmEditPlugin.INSTANCE.getString(key) + ": none");
	}

	/**
	 * Get the label for a class
	 */
	protected String getLabel(EClass eClass) {
		return ResourceLocatorUtils.getString(eClass);
	}

	/**
	 * Generates a {@link Listitem} containing the objects of the eList as
	 * {@link Xref}s (if possible to be referenced, otherwise as String),
	 * separated by commas. If there are no objects, the label together with
	 * "none" will be displayed.
	 * 
	 * @param label
	 *            the label of the enumerated objects.
	 * @param eList
	 *            the list of objects to be enumerated.
	 */
	protected Listitem generateEnumerationListitem(String label,
			List<? extends EObject> eList) {
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(label + ": ");
		if (!eList.isEmpty()) {
			for (EObject object : eList) {
				JAXBElement<Xref> xrefElement = generateXref(object);
				if (xrefElement == null) {
					objectList.add(labelProvider.getText(object));
				} else {
					objectList.add(xrefElement);
				}
				// check whether object is last element
				if (!eList.get(eList.size() - 1).equals(object)) {
					objectList.add(", ");
				}
			}
		} else {
			objectList.add("none");
		}
		return generateListitem(objectList);
	}

	/**
	 * Generates a {@link Listitem} containing an {@link EObject} as
	 * {@link Xref} (if possible to be referenced, otherwise as String). If
	 * there is no object, the label together with "none" will be displayed.
	 * 
	 * @param label
	 *            the label of the object.
	 * @param eObject
	 *            the object to be displayed.
	 */
	protected Listitem generateEObjectListitem(String label, EObject eObject) {
		List<Object> objectItemList = new ArrayList<Object>();
		objectItemList.add(label + ": ");
		if (eObject != null) {
			JAXBElement<Xref> xrefElement = generateXref(eObject);
			if (xrefElement == null) {
				objectItemList.add(labelProvider.getText(eObject));
			} else {
				objectItemList.add(xrefElement);
			}
		} else {
			objectItemList.add("none");
		}
		return generateListitem(objectItemList);
	}

	/**
	 * Generates a {@link Listitem} containing a {@link Para} which contains the
	 * objects specified in the collection. This way, mixed content can be added
	 * to a listitem.
	 */
	protected Listitem generateListitem(List<Object> list) {
		Para para = objectFactory.createPara();
		para.getContent().addAll(list);
		Listitem listItem = objectFactory.createListitem();
		listItem.getListClassAndAdmonClassAndLinespecificClass().add(
				objectFactory.createPara(para));
		return listItem;
	}

	/**
	 * Gets the link ID from the {@link EObject}.
	 */
	protected String getLinkID(EObject eObject) {
		return eObject.eResource().getURIFragment(eObject);
	}

	/**
	 * Generates a {@link Xref} object linked to the specified
	 * {@link NamedElement}
	 * 
	 * @returns a {@link JAXBElement} that can be added to a Listitem or
	 *          paragraph, for example or null in case the {@link EObject}
	 *          cannot be referenced
	 */
	protected JAXBElement<Xref> generateXref(EObject eObject) {
		Resource containingResource = checkExistent(eObject);
		if (containingResource != null) {
			Xref xref = objectFactory.createXref();
			Anchor anchor = objectFactory.createAnchor();
			anchor.setId(getLinkID(eObject, containingResource));
			xref.setLinkend(anchor);
			return objectFactory.createXref(xref);
		}
		return null;
	}

	/**
	 * Checks whether the {@link EObject} is contained in the quality models to
	 * be exported, if so, the containing {@link Resource} is returned,
	 * otherwise null.
	 */
	private Resource checkExistent(EObject eObject) {
		for (QualityModel model : qualityModels) {
			if (EcoreUtil.isAncestor(model, eObject)) {
				return model.eResource();
			}
		}
		return null;
	}

	/**
	 * Gets the link ID from the {@link EObject} contained in the specified
	 * {@link Resource}.
	 */
	protected String getLinkID(EObject eObject, Resource resource) {
		return resource.getURIFragment(eObject);
	}

	/** Returns objectFactory. */
	protected ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	/** Returns labelProvider. */
	protected AdapterFactoryLabelProvider getLabelProvider() {
		return labelProvider;
	}

	/**
	 * Saves the JAXB Content to the xml file.
	 */
	private void save(ArticleClass article, OutputStream out)
			throws IOException {
		JAXBContext jaxbContext = null;
		try {
			jaxbContext = JAXBContext.newInstance("de.quamoco.qm.docbook");
			JAXBElement<ArticleClass> root = objectFactory
					.createArticle(article);

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(root, out);
		} catch (JAXBException e) {
			throw new IOException("JAXBException: " + e.getMessage());
		} finally {
			out.close();
		}
	}
}
