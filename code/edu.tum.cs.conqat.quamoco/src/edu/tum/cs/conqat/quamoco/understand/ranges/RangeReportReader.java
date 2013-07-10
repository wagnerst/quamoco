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

package edu.tum.cs.conqat.quamoco.understand.ranges;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATPipelineProcessorBase;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IElement;
import org.conqat.engine.resource.IResource;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.text.ITextElement;
import org.conqat.engine.resource.text.ITextResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;
import org.conqat.engine.resource.util.TextElementXMLReader;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;
import org.conqat.lib.commons.string.StringUtils;
import org.conqat.lib.commons.xml.IXMLElementProcessor;

/**
 * 
 * @author lochmann
 */
@AConQATProcessor(description = "Read an understand report containing ranges.")
public class RangeReportReader extends ConQATPipelineProcessorBase<IResource> {

	public static final String KEY_FOR_METHOD_RANGE = "UnderstandMethodRange";
	public static final String KEY_FOR_CLASS_RANGE = "UnderstandClassRange";

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "report-files", attribute = ConQATParamDoc.INPUT_REF_NAME, description = "Scope with report files")
	public ITextResource reports;

	/** locations */
	private Map<String, IElement> elementLocationMap;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void processInput(IResource input) throws ConQATException {

		elementLocationMap = ResourceTraversalUtils
				.toLowercase(ResourceTraversalUtils.createLocationToElementMap(
						input, IElement.class));
//
//		for (String s : elementLocationMap.keySet()) {
//			getLogger().warn("Entry in elementLocationMap: " + s);
//		}

		for (ITextElement report : ResourceTraversalUtils
				.listTextElements(reports)) {
			loadReport(report);
		}
	}

	/**
	 * Loads an Understand Range Report
	 * 
	 * @throws ConQATException
	 */
	private void loadReport(ITextElement report) throws ConQATException {
		(new XmlReportReader(report)).load();
	}

	/**
	 * 
	 * 
	 * @author lochmann
	 * @author $Author: hummelb $
	 * @version $Rev: 18709 $
	 * @levd.rating RED Rev:
	 */
	private final class XmlReportReader
			extends
			TextElementXMLReader<EUnderstandXmlElements, EUnderstandXmlAttributes, ConQATException> {

		private long method_all = 0;
		private long method_errors = 0;
		private long class_all = 0;
		private long class_errors = 0;

		/**
		 * Constructor
		 */
		private XmlReportReader(ITextElement report) throws ConQATException {
			super(report, EUnderstandXmlAttributes.class);
		}

		/** Reads the report and loads its contents into the findings report. */
		public void load() throws ConQATException {
			parseAndWrapExceptions();

			processChildElements(new ProjectProcessor());

			getLogger().info(
					"Stats: method_all=" + method_all + " method_errors="
							+ method_errors + " method_success="
							+ (method_all - method_errors));
			getLogger().info(
					"Stats: class_all=" + class_all + "class_ errors="
							+ class_errors + " class_success="
							+ (class_all - class_errors));
		}

		/** Processor for the project element. Delegates to src dirs. */
		private final class ProjectProcessor implements
				IXMLElementProcessor<EUnderstandXmlElements, ConQATException> {

			/** {@inheritDoc} */
			@Override
			public EUnderstandXmlElements getTargetElement() {
				return EUnderstandXmlElements.entity;
			}

			/** {@inheritDoc} */
			@Override
			public void process() throws ConQATException {

				String type = getStringAttribute(EUnderstandXmlAttributes.type);

				if (type.equalsIgnoreCase("Method")
						|| type.equalsIgnoreCase("Function")) {
					createMethodRange();
				} else if (type.equalsIgnoreCase("Class")
						|| type.equalsIgnoreCase("Struct")) {
					createClassRange();
				}

			}

			/**
			 * Create a method range
			 * 
			 * @throws ConQATException
			 */
			private void createMethodRange() throws ConQATException {
				String filename = getStringAttribute(EUnderstandXmlAttributes.file);
				File file = new File(filename);
				String nname = file.getAbsolutePath().replace('\\', '/');

				method_all++;

				IElement element = elementLocationMap.get(nname.toLowerCase());

				if (element == null) {
					method_errors++;
					getLogger().error(
							"Method-XML-Entry: Failed mapping of path '"
									+ nname.toLowerCase() + "'");
					return;
				}

				List<Range> data = (List<Range>) element
						.getValue(KEY_FOR_METHOD_RANGE);
				if (data == null) {
					data = new ArrayList<Range>();
					element.setValue(KEY_FOR_METHOD_RANGE, data);
				}

				final int line = getIntAttribute(EUnderstandXmlAttributes.line);
				final int loc = getIntAttribute(EUnderstandXmlAttributes.tloc);

				RegionSetDictionary commonRegionSetForMethod = new RegionSetDictionary();
				Region region = new Region(line, line + loc);

				RegionSet regionSet = commonRegionSetForMethod.get(element
						.getUniformPath());
				if (regionSet == null) {
					regionSet = new RegionSet(element.getUniformPath());
					commonRegionSetForMethod.add(regionSet);
				}
				regionSet.add(region);

				data.add(new Range(line, line + loc, commonRegionSetForMethod));

				processChildElements(new DeclarationProcessor(
						commonRegionSetForMethod));
			}

			/**
			 * Creates a class range for the currently processed element
			 */
			private void createClassRange() throws ConQATException {
				RegionSetDictionary commonRegionSetForClass = new RegionSetDictionary();

				// read the range, if it is directly encoded in the tag "class"
				readFileAttribute(commonRegionSetForClass);

				// read the ranges, if there are sub-tags "file"
				processChildElements(new ClassRangeProcessor(
						commonRegionSetForClass));
			}

			/**
			 * Reads the attribute "file" of the tag "class" for classes
			 * consisting of only one file
			 */
			private void readFileAttribute(
					RegionSetDictionary commonRegionSetForClass) {
				String filename = getStringAttribute(EUnderstandXmlAttributes.file);
				if (!StringUtils.isEmpty(filename)) {
					int line = getIntAttribute(EUnderstandXmlAttributes.line);
					int tloc = getIntAttribute(EUnderstandXmlAttributes.tloc);
					addClassRange(commonRegionSetForClass, filename, line, tloc);
				}
			}
		}

		/**
		 * Creates a class range entry for the file, line, and tloc
		 */
		private void addClassRange(RegionSetDictionary commonRegionSetForClass,
				String filename, int line, int tloc) {
			class_all++;

			IElement element = getIElementByFilename(filename);
			if (element == null) {
				class_errors++;
				getLogger().error(
						"Class-XML-Entry: Failed mapping of path '" + filename
								+ "'");
				return;
			}

			List<Range> data = (List<Range>) element
					.getValue(KEY_FOR_CLASS_RANGE);
			if (data == null) {
				data = new ArrayList<Range>();
				element.setValue(KEY_FOR_CLASS_RANGE, data);
			}

			Region region = new Region(line, line + tloc);

			RegionSet regionSet = commonRegionSetForClass.get(element
					.getUniformPath());
			if (regionSet == null) {
				regionSet = new RegionSet(element.getUniformPath());
				commonRegionSetForClass.add(regionSet);
			}
			regionSet.add(region);

			data.add(new Range(region.getStart(), region.getEnd(),
					commonRegionSetForClass));
		}

		/**
		 * Gets an IElement for the given location.
		 */
		private IElement getIElementByFilename(String filename) {
			File file = new File(filename);
			String nname = file.getAbsolutePath().replace('\\', '/')
					.toLowerCase();
			return elementLocationMap.get(nname);
		}

		/**
		 * Processes tags "Class"
		 */
		private final class ClassRangeProcessor implements
				IXMLElementProcessor<EUnderstandXmlElements, ConQATException> {

			/** The RegionSet where all parts of the processed class are saved */
			private RegionSetDictionary commonRegionSetForClass;

			/**
			 * Constructor
			 */
			public ClassRangeProcessor(
					RegionSetDictionary commonRegionSetForClass) {
				this.commonRegionSetForClass = commonRegionSetForClass;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public EUnderstandXmlElements getTargetElement() {
				return EUnderstandXmlElements.file;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void process() {
				String filename = getStringAttribute(EUnderstandXmlAttributes.name);
				int line = getIntAttribute(EUnderstandXmlAttributes.line);
				int tloc = getIntAttribute(EUnderstandXmlAttributes.tloc);
				addClassRange(commonRegionSetForClass, filename, line, tloc);
			}

		}

		/**
		 * Processes tags "Declaration"
		 */
		private final class DeclarationProcessor implements
				IXMLElementProcessor<EUnderstandXmlElements, ConQATException> {

			private RegionSetDictionary commonRegionSetForMethod;

			/**
			 * Constructor
			 */
			public DeclarationProcessor(
					RegionSetDictionary commonRegionSetForMethod) {
				this.commonRegionSetForMethod = commonRegionSetForMethod;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public EUnderstandXmlElements getTargetElement() {
				return EUnderstandXmlElements.declaration;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void process() throws ConQATException {

				method_all++;

				String filename = getStringAttribute(EUnderstandXmlAttributes.file);
				IElement element = getIElementByFilename(filename);

				if (element == null) {
					method_errors++;
					getLogger().error(
							"Method-Declaration-XML-Entry: Failed mapping of path '"
									+ filename + "'");
					return;
				}

				List<Range> data = (List<Range>) element
						.getValue(KEY_FOR_METHOD_RANGE);
				if (data == null) {
					data = new ArrayList<Range>();
					element.setValue(KEY_FOR_METHOD_RANGE, data);
				}

				int line;
				try {
					line = getIntAttribute(EUnderstandXmlAttributes.line);
				} catch (NumberFormatException e) {
					throw new ConQATException(getLocation()
							+ "Attribute 'line' of 'declaration' is invalid: "
							+ e.toString());
				}

				data.add(new Range(line, line, commonRegionSetForMethod));

			}

		}

	}

}
