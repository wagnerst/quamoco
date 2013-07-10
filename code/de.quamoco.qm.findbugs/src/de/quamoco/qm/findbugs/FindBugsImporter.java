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

package de.quamoco.qm.findbugs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.quamoco.qm.ImplementingMeasure;
import de.quamoco.qm.Measure;
import de.quamoco.qm.QmFactory;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.RuleBasedInstrument;
import de.quamoco.qm.util.QmModelUtils;
import de.quamoco.qm.util.QmResourceFactory;
import edu.tum.cs.conqat.quamoco.QuamocoUtils;

/*-----------------------------------------------------------------------+
 | de.quamoco.qm.findbugs
 |                                                                       |
 $Id: FindBugsImporter.java 4974 2012-02-17 09:34:10Z lochmann $            
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

/**
 * This class imports all FindBugs rules as measures from a html page into a
 * quality model.
 * 
 * @author niessner
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class FindBugsImporter {

	/** The url to the html FindBugs descriptions. */
	private static final String urlString = "http://findbugs.sourceforge.net/bugDescriptions.html";

	/** The path to the FindBugs quality model File. */
	private static final String qmPath = "qm/FindBugs.qm";

	/**
	 * All FindBugs Rules are read from a html page and imported as both
	 * {@link Measure}s and {@link ImplementingMeasure}s for further editing.
	 */
	public static void main(String[] args) throws MalformedURLException,
			IOException {
		Map<String, String> findBugsRules = retrieveFindBugs();
		QualityModel model = getQm();
		model.getMeasures().clear();
		for (String head : findBugsRules.keySet()) {
			int lastIndex = head.lastIndexOf("(");
			String measureName = head.substring(0, lastIndex).trim();
			String rule = head.substring(lastIndex + 1, head.length() - 1);
			String description = findBugsRules.get(head);
			Measure measure = createMeasure(measureName, description, rule);
			model.getMeasures().add(measure);
			model.getMeasures().add(
					createImplementingMeasure(measure, description, rule));
		}
		model.eResource().save(Collections.EMPTY_MAP);
	}

	/**
	 * Gets the quality model called "FindBugs" from the resource file.
	 */
	private static QualityModel getQm() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
				.put(QuamocoUtils.QUAMOCO_MODEL_FILE_SUFFIX,
						new QmResourceFactory());
		ResourceSet resourceSet = new ResourceSetImpl();

		resourceSet.getResource(URI.createFileURI(new File(qmPath)
				.getAbsolutePath()), true);

		List<QualityModel> models = QmModelUtils.getQualityModels(resourceSet);
		for (QualityModel model : models) {
			if (model.getName().equals("FindBugs")) {
				return model;
			}
		}
		return null;
	}

	/**
	 * Crawls the html and finds all FindBugs Rules.
	 * 
	 * @returns a map containing the name of the rule as key and its description
	 *          as value.
	 */
	private static Map<String, String> retrieveFindBugs()
			throws MalformedURLException, IOException {
		URL url = new URL(urlString);
		Object content = url.getContent();
		BufferedInputStream input = new BufferedInputStream(
				(InputStream) content);
		StringBuilder builder = new StringBuilder();
		int read;
		while ((read = input.read()) != -1) {
			builder.append((char) read);
		}
		input.close();
		String inputString = builder.toString();
		Pattern contentPattern = Pattern.compile(
				"<h2>Descriptions</h2>(.*)<hr>", Pattern.MULTILINE
						| Pattern.DOTALL);
		Matcher matcher = contentPattern.matcher(inputString);
		matcher.find();
		String descriptionsString = matcher.group(1);
		// avoid empty first one at split
		descriptionsString = descriptionsString.substring(5);
		String[] descriptions = descriptionsString.split("<h3>");

		Map<String, String> findBugsRules = new HashMap<String, String>();
		for (String description : descriptions) {
			String itemParts[] = description.split("</h3>");
			String header = itemParts[0];
			// remove html tags
			header = header.replaceAll("<([^>]*)>", "").trim();
			String descr = itemParts[1];
			// remove html tags
			descr = descr.replaceAll("<([^>]*)>", "").trim();
			findBugsRules.put(header, descr);
		}
		return findBugsRules;
	}

	/**
	 * Creates a measure with given name, description and attaches a
	 * {@link RuleBasedInstrument} with given rule.
	 */
	private static Measure createMeasure(String measureName,
			String description, String rule) {
		Measure measure = QmFactory.eINSTANCE.createMeasure();
		measure.setName(measureName);
		measure.setDescription(description);
		RuleBasedInstrument instrument = QmFactory.eINSTANCE
				.createRuleBasedInstrument();
		instrument.setDetermines(measure);
		instrument.setRule(rule);
		return measure;
	}

	/**
	 * Creates an {@link ImplementingMeasure} with given description and
	 * attaches a {@link RuleBasedInstrument} with given rule. It will implement
	 * the given measure so the name of the FindBugs Rule will be displayed in
	 * the quality model.
	 */
	private static ImplementingMeasure createImplementingMeasure(
			Measure measure, String description, String rule) {
		ImplementingMeasure implMeasure = QmFactory.eINSTANCE
				.createImplementingMeasure();
		implMeasure.setDescription(description);
		implMeasure.setImplements(measure);
		RuleBasedInstrument instrument = QmFactory.eINSTANCE
				.createRuleBasedInstrument();
		instrument.setDetermines(implMeasure);
		instrument.setRule(rule);
		return implMeasure;
	}

}
