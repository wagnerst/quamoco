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

package de.quamoco.qm.conqat.generator;

import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.CLASS;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.KEY;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.NAME;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.NAMESPACE;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.REF;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.SPEC;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.TYPE;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.VALUE;
import static de.quamoco.qm.conqat.generator.EConQATBlockAttribute.XMLNS;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.ATTR;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.BLOCK;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.BLOCK_SPEC;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.CONQAT;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.ENTRIES;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.ENTRY;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.META;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.OUT;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.PARAM;
import static de.quamoco.qm.conqat.generator.EConQATBlockElement.PROCESSOR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.conqat.lib.commons.assertion.CCSMPre;
import org.conqat.lib.commons.filesystem.FileSystemUtils;
import org.conqat.lib.commons.xml.LowercaseResolver;
import org.conqat.lib.commons.xml.XMLWriter;

/**
 * This class can be used to write ConQAT blocks. It provides an abstraction
 * slightly above the basic XML writer.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 755AC5664861ECBA3E20AF9905937B2B
 */
public class ConQATBlockWriter {

	/** The writer used by all other methods. */
	private final Writer writer;

	/** Create new block writer. */
	public ConQATBlockWriter(File file) throws IOException {
		try {
			FileSystemUtils.ensureParentDirectoryExists(file);
			writer = new Writer(file);
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError("UTF-8 should be supported!");
		}
	}

	/** Open ConQAT block file. */
	public void openConQAT() {
		writer.addHeader("1.0", "UTF-8");
		writer.openElement(CONQAT, XMLNS, "http://conqat.cs.tum.edu/ns/config");
	}

	/** Close ConQAT block file. */
	public void closeConQAT() {
		writer.closeElement(CONQAT);
	}

	/** Open block spec. */
	public void openBlockSpec(String blockId) {
		writer.openElement(BLOCK_SPEC, NAME, blockId);
	}

	/** Close block spec. */
	public void closeBlockSpec() {
		writer.closeElement(BLOCK_SPEC);
	}

	/** Write a spec parameter. */
	public void addSpecParam(String name, String... attributes) {
		writer.openElement(PARAM, NAME, name);
		for (String attr : attributes) {
			writer.addClosedElement(ATTR, NAME, attr);
		}
		writer.closeElement(PARAM);
	}

	/** Add spec output. */
	public void addSpecOutput(String name, String ref) {
		writer.addClosedElement(OUT, NAME, name, REF, ref);
	}

	/** Open a block declaration. */
	public void openBlockDecl(String blockName, String blockId) {
		writer.openElement(BLOCK, NAME, blockName, SPEC, blockId);
	}

	/** Close block declaration. */
	public void closeBlockDecl() {
		writer.closeElement(BLOCK);
	}

	/** Open a processor declaration. */
	public void openProcessorDecl(String processorName, String processorId) {
		writer.openElement(PROCESSOR, NAME, processorName, CLASS, processorId);
	}

	/** Close processor declaration. */
	public void closeProcessorDecl() {
		writer.closeElement(PROCESSOR);
	}

	/**
	 * Add declaration input.
	 * 
	 * @param attributes
	 *            must be key-value-pairs
	 */
	public void addDeclInput(String paramName, String... attributes) {
		CCSMPre.isTrue(attributes.length % 2 == 0, "Must be pairs");
		writer.addRawString("<" + paramName + " ");
		for (int i = 0; i < attributes.length - 1; i = i + 2) {
			// we need to use raw strings here as attributes are unknown and
			// cannot be defined via enum
			writer.addRawString(attributes[i]);
			writer.addRawString("=\"");
			writer.addRawString(attributes[i + 1]);
			writer.addRawString("\" ");
		}
		writer.addRawString("/>");
	}

	/** Open meta section. */
	public void openMeta(String type) {
		writer.openElement(META, TYPE, type);
	}

	/** Close meta section. */
	public void closeMeta() {
		writer.closeElement(META);
	}

	/** Open meta entries. */
	public void openMetaEntries(String namespace) {
		writer.openElement(ENTRIES, NAMESPACE, namespace);
	}

	/** Close meta entries. */
	public void closeMetaEntries() {
		writer.closeElement(ENTRIES);
	}

	/** Add meta entries with one entry. */
	public void addMetaEntries(String namespace, String key, String value) {
		openMetaEntries(namespace);
		addMetaEntry(key, value);
		closeMetaEntries();
	}

	/** Add a meta entry. */
	public void addMetaEntry(String key, String value) {
		writer.addClosedElement(ENTRY, KEY, key, VALUE, value);
	}

	/** Close this writer. This closes underlying streams. */
	public void close() {
		writer.close();
	}

	/**
	 * Simple wrapper class for {@link XMLWriter}. Wee need this to expose
	 * {@link #addRawString(String)}
	 */
	private class Writer extends
			XMLWriter<EConQATBlockElement, EConQATBlockAttribute> {
		/** Create writer. */
		public Writer(File file) throws FileNotFoundException,
				UnsupportedEncodingException {
			super(
					new PrintStream(file, FileSystemUtils.UTF8_ENCODING),
					new LowercaseResolver<EConQATBlockElement, EConQATBlockAttribute>(
							EConQATBlockAttribute.class));
		}

		/**
		 * {@inheritDoc}
		 * 
		 * Made this public here.
		 */
		@Override
		public void addRawString(String html) {
			super.addRawString(html);
		}

	}
}
