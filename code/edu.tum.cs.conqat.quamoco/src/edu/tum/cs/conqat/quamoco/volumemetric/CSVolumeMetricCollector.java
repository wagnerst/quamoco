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

/*--------------------------------------------------------------------------+
$Id: CSVolumeMetricCollector.java 4974 2012-02-17 09:34:10Z lochmann $
|                                                                          |
| Copyright 2005-2010 Technische Universitaet Muenchen                     |
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
+--------------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco.volumemetric;

import java.io.IOException;
import java.util.List;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATPipelineProcessorBase;
import org.conqat.engine.commons.node.IConQATNode;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.dotnet.ila.xml.EIlaXmlAttribute;
import org.conqat.engine.dotnet.ila.xml.EIlaXmlElement;
import org.conqat.engine.resource.text.ITextElement;
import org.conqat.engine.resource.text.ITextResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;
import org.conqat.lib.commons.collections.CounterSet;
import org.conqat.lib.commons.error.NeverThrownRuntimeException;
import org.conqat.lib.commons.xml.IXMLElementProcessor;
import org.conqat.lib.commons.xml.XMLReader;
import org.conqat.lib.commons.xml.XMLResolver;
import org.xml.sax.SAXException;

/**
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Hash: 2C9AF32382202C07F02FB324ABBD4844
 */
@AConQATProcessor(description = "")
public class CSVolumeMetricCollector extends
		ConQATPipelineProcessorBase<IConQATNode> {

	private enum EElement {
		CLASS("NumberOfClasses"), METHOD("NumberOfMethods"), VARIABLE(
				"NumberOfVariables"), STATEMENT("NumberOfStatements"), TYPE(
				"NumberOfTypes"), FIELD("NumberOfFields");

		private final String displayName;

		private EElement(String name) {
			displayName = name;
		}
	}

	@AConQATFieldParameter(parameter = "ila-xml", attribute = ConQATParamDoc.INPUT_REF_NAME, description = ConQATParamDoc.INPUT_DESC)
	public ITextResource ilaXMLFiles;

	private final CounterSet<EElement> elementCounter = new CounterSet<EElement>();

	/** {@inheritDoc} */
	@Override
	protected void processInput(IConQATNode input) throws ConQATException {
		List<ITextElement> elements = ResourceTraversalUtils
				.listTextElements(ilaXMLFiles);
		for (ITextElement element : elements) {
			try {
				new IlaReader(element).parse();
			} catch (IOException e) {
				throw new ConQATException(e);
			}
		}

		for (EElement element : EElement.values()) {
			input.setValue(element.displayName, elementCounter
					.getValue(element));
		}

	}

	private class IlaReader
			extends
			XMLReader<EIlaXmlElement, EIlaXmlAttribute, NeverThrownRuntimeException> {
		public IlaReader(ITextElement element) throws IOException,
				ConQATException {
			super(element.getUnfilteredTextContent(), null,
					new XMLResolver<EIlaXmlElement, EIlaXmlAttribute>(
							EIlaXmlAttribute.class));
		}

		/**
		 * Parses the IL-XML file.
		 * 
		 * @throws ConQATException
		 *             if file could not be parsed.
		 */
		public void parse() throws ConQATException {
			try {
				parseFile();
			} catch (SAXException e) {
				throw new ConQATException("Could not parse file: "
						+ e.getMessage(), e);
			} catch (IOException e) {
				throw new ConQATException("Could not read file: "
						+ e.getMessage(), e);
			}

			processDecendantElements(new TypeElementProcessor());
		}

		private class TypeElementProcessor
				implements
				IXMLElementProcessor<EIlaXmlElement, NeverThrownRuntimeException> {

			/** {@inheritDoc} */
			@Override
			public EIlaXmlElement getTargetElement() {
				return EIlaXmlElement.TypeElement;
			}

			/** {@inheritDoc} */
			@Override
			public void process() throws NeverThrownRuntimeException {
				String declType = getStringAttribute(EIlaXmlAttribute.DeclType);
				elementCounter.inc(EElement.TYPE);
				if ("Class".equals(declType)) {
					elementCounter.inc(EElement.CLASS);
				}
				processDecendantElements(new MemberProcessor());

			}

		}

		private class MemberProcessor
				implements
				IXMLElementProcessor<EIlaXmlElement, NeverThrownRuntimeException> {

			/** {@inheritDoc} */
			@Override
			public EIlaXmlElement getTargetElement() {
				return EIlaXmlElement.Member;
			}

			/** {@inheritDoc} */
			@Override
			public void process() throws NeverThrownRuntimeException {
				String memberType = getStringAttribute(EIlaXmlAttribute.MemberType);

				if ("Field".equals(memberType)) {
					elementCounter.inc(EElement.VARIABLE);
					elementCounter.inc(EElement.FIELD);
					return;
				}

				if ("Method".equals(memberType)) {
					elementCounter.inc(EElement.METHOD);
					try {
						int statementCount = getIntAttribute(EIlaXmlAttribute.NumILStmts);
						elementCounter.inc(EElement.STATEMENT, statementCount);
					} catch (NumberFormatException ex) {
						// ignore
					}
				}

			}
		}
	}

}
