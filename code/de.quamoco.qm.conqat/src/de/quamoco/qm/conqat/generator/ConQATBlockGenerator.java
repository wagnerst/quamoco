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

import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.BLOCK_ID;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.LATE_PROCESSOR_IDS;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.META_DATA_TYPE;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.META_KEY_EDGES_INVISIBLE;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.META_KEY_POS;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.NAMESPACE_COMMENTS;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.NAMESPACE_COMMENT_BOUNDS;
import static de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.xPos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.collections.IdentityHashSet;
import org.conqat.lib.commons.collections.TwoDimHashMap;
import org.conqat.lib.commons.string.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;

import de.quamoco.qm.Instrument;
import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Tool;
import de.quamoco.qm.ToolBasedInstrument;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.DECL;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.METRIC_COLLECTOR;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.MODEL_EVAL;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.MODEL_LOADER;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.MODEL_SAVER;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.RANGE_RESOLVERS;
import de.quamoco.qm.conqat.generator.ConQATBlockGeneratorConstants.SPEC;
import de.quamoco.qm.util.QmModelUtils;

/**
 * Main class of the ConQAT block generator.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 89B102BC2BB665301DC2CD1257EF388E
 */
public class ConQATBlockGenerator {

	/** The models this generator runs for. */
	private final Set<QualityModel> models;

	/** Id of the target block. */
	private final String blockId;

	/** The writer used for generating the block. */
	private final ConQATBlockWriter writer;

	/** Programming language the model is targeted at. */
	private final String language;

	/**
	 * We assume that each block is used only once. We use this set to check if
	 * this is actually true. If not there's something wrong with the
	 * generation, i.e. this is a not a user problem.
	 */
	private final HashSet<String> blockIds = new HashSet<String>();

	/**
	 * For each name space (1. key), this stores a key-value-map of meta
	 * entries.
	 */
	private final TwoDimHashMap<String, String, String> metaEntries = new TwoDimHashMap<String, String, String>();

	/**
	 * This stack holds the outputs of the processors in the chain.
	 * Theoretically, it would be enough to just store the last output. However,
	 * using the stack we can also calculate the current vertical position from
	 * it and don't need to keep another variable for this purpose.
	 */
	private final Stack<String> unitChain = new Stack<String>();

	/**
	 * Flag indicating if the IL input parameter was already added during block
	 * generation
	 */
	private boolean isILInputParameterAdded = false;

	/** Name of the model loader block. */
	private final String modelLoaderName;

	/**
	 * Create generator.
	 */
	public ConQATBlockGenerator(File bundleLocation, String blockName,
			String language, List<QualityModel> models) throws IOException {
		File blockFile = GeneratorUtils.getBlockLocation(bundleLocation,
				blockName);
		writer = new ConQATBlockWriter(blockFile);
		this.blockId = GeneratorUtils.getBlockId(bundleLocation, blockName);
		this.models = new IdentityHashSet<QualityModel>();
		for (QualityModel model : models) {
			traverseModels(model, this.models);
		}
		this.language = language;
		modelLoaderName = obtainUnitName(MODEL_LOADER.UNIT_ID);
	}

	/** Traverse model to obtain required models. */
	private void traverseModels(QualityModel model, Set<QualityModel> models) {
		models.add(model);

		for (QualityModel requiredModel : model.getRequires()) {
			traverseModels(requiredModel, models);
		}
	}

	/**
	 * Generate block.
	 * 
	 * @throws ConQATBlockGenerationException
	 */
	public void generate(IProgressMonitor monitor)
			throws ConQATBlockGenerationException {
		monitor.beginTask("Generate Frame", IProgressMonitor.UNKNOWN);

		writer.openConQAT();
		writer.openBlockSpec(blockId);

		writeInputInterface();

		monitor.beginTask("Write Blocks", IProgressMonitor.UNKNOWN);
		writeBlocks();

		monitor.beginTask("Write Post Processing", IProgressMonitor.UNKNOWN);

		writer.addSpecOutput(SPEC.O_SCOPE, unitChain.peek());
		metaEntries.putValue(SPEC.O_SCOPE, META_KEY_POS, (xPos(2) + 70) + ","
				+ (yPos(1) + 90));

		writeMetricCollector();

		writeModelEvaluator();

		writeModelSaver();

		writer.addSpecOutput(SPEC.O_RESULT, unitChain.peek());
		writer.closeBlockSpec();

		monitor.beginTask("Write Metadata", IProgressMonitor.UNKNOWN);
		writeMetadata();

		writer.closeConQAT();
		writer.close();
	}

	/** Write spec parameters. */
	private void writeInputInterface() {
		writer.addSpecParam(SPEC.P_INPUT.NAME, SPEC.P_INPUT.A_SCOPE);
		writer.addSpecParam(SPEC.P_OUTPUT.NAME, SPEC.P_OUTPUT.A_DIR);
		writer.addSpecParam(SPEC.P_MAP.NAME, SPEC.P_MAP.A_PROJECT,
				SPEC.P_MAP.A_DIR);
		writer.addSpecParam(SPEC.P_PROJECT_NAME.PROJECT,
				SPEC.P_PROJECT_NAME.A_NAME);
		writer.addSpecParam(SPEC.P_EVAL_RESULT_DIR.NAME,
				SPEC.P_EVAL_RESULT_DIR.A_DIR);
		unitChain.push(SPEC.P_INPUT.REF);
	}

	/**
	 * Write the blocks that are specified through the concrete measures of the
	 * model.
	 * 
	 * @throws ConQATBlockGenerationException
	 */
	private void writeBlocks() throws ConQATBlockGenerationException {
		// we need to collect all blocks first and sort them with the magic
		// comparator to generate a valid order of processors.
		HashSet<String> blocks = new HashSet<String>();

		for (Instrument instrument : QmModelUtils
				.getConcreteMeasures(new ArrayList<QualityModel>(models))) {
			addInstrument(instrument, blocks);
		}

		for (String blockId : CollectionUtils.sort(blocks,
				new MagicComparator())) {
			addConQATBlock(blockId);
		}
	}

	/**
	 * Adds a instrument. This add the block id to the specified set. If the
	 * tool specified by the measure is not known, the measure is ignored and no
	 * block is added.
	 */
	private void addInstrument(Instrument instrument, Set<String> blockIds)
			throws ConQATBlockGenerationException {

		if (!(instrument instanceof ToolBasedInstrument)) {
			return;
		}

		Tool tool = ((ToolBasedInstrument) instrument).getTool();

		String blockId = tool.getAnnotations().get(BLOCK_ID);
		if (blockId == null) {
			throw new ConQATBlockGenerationException(
					"Block id not specified for tool " + tool.getName());
		}

		blockIds.add(blockId);

		if (isILRequiringBlock(blockId) && !isILInputParameterAdded) {
			writer.addSpecParam(SPEC.P_IL_INPUT.NAME, SPEC.P_IL_INPUT.A_SCOPE);
			isILInputParameterAdded = true;
		}

	}

	/** Checks if the given block requires the IL scope */
	private boolean isILRequiringBlock(String blockId) {
		return blockId.endsWith("IL");
	}

	/** Add a ConQAT block to the chain. */
	private void addConQATBlock(String blockId) {
		String blockName = obtainUnitName(blockId);
		// unitChain.add(blockName);
		writer.openBlockDecl(blockName, blockId);
		writer.addDeclInput(DECL.P_INPUT.NAME, DECL.P_INPUT.A_SCOPE, unitChain
				.peek());
		writer.addDeclInput(DECL.P_OUTPUT.NAME, DECL.P_OUTPUT.A_DIR,
				SPEC.P_OUTPUT.REF);
		writer.addDeclInput(DECL.P_MAP.NAME, DECL.P_MAP.A_PROJECT,
				SPEC.P_MAP.REF_PROJECT, DECL.P_MAP.A_DIR, SPEC.P_MAP.REF_DIR);

		if (isILRequiringBlock(blockId)) {
			writer.addDeclInput(DECL.P_IL_INPUT.NAME, DECL.P_IL_INPUT.A_SCOPE,
					SPEC.P_IL_INPUT.REF);
		}

		writer.closeBlockDecl();
		metaEntries.putValue(blockName, META_KEY_POS, xPos(1) + "," + yPos(1));
		unitChain.push("@" + blockName + "." + DECL.O_SCOPE);
	}

	/** Write metric collection processor. */
	private void writeMetricCollector() {

		// spec param for metric collection
		writer.addSpecParam(SPEC.P_METRIC_COLLECTION.NAME,
				SPEC.P_METRIC_COLLECTION.A_COLLECTION);
		metaEntries.putValue(SPEC.P_METRIC_COLLECTION.NAME, META_KEY_POS,
				xPos(2) + "," + (yPos() + 10));

		String collectorName = obtainUnitName(METRIC_COLLECTOR.UNIT_ID);
		writer.openProcessorDecl(collectorName, METRIC_COLLECTOR.UNIT_ID);

		writer.addDeclInput(METRIC_COLLECTOR.P_INPUT.NAME,
				METRIC_COLLECTOR.P_INPUT.A_REF, unitChain.peek());

		writer.addDeclInput(METRIC_COLLECTOR.P_METRIC_COLLECTION.NAME,
				METRIC_COLLECTOR.P_METRIC_COLLECTION.A_REF,
				SPEC.P_METRIC_COLLECTION.REF);

		writer.addDeclInput(MODEL_EVAL.P_MODELS.NAME,
				MODEL_EVAL.P_MODELS.A_REF, "@" + modelLoaderName);

		writer.closeProcessorDecl();
		unitChain.push("@" + collectorName);

		metaEntries.putValue(collectorName, META_KEY_POS, xPos(1) + ","
				+ yPos());

		writer.addSpecOutput(SPEC.O_METRICS, unitChain.peek());
		metaEntries.putValue(SPEC.O_METRICS, META_KEY_POS, (xPos(0) + 70) + ","
				+ (yPos() + 90));

	}

	/** Write model evaluator processor. */
	private void writeModelEvaluator() {
		writeModelLoader();

		String resolverId = RANGE_RESOLVERS.UNIT_ID(language);
		String resolverName = obtainUnitName(resolverId);
		writer.openBlockDecl(resolverName, resolverId);
		writer.closeBlockDecl();
		metaEntries
				.putValue(resolverName, META_KEY_POS, xPos(3) + "," + yPos());

		String modelEvaluatorName = obtainUnitName(MODEL_EVAL.UNIT_ID);

		writer.openProcessorDecl(modelEvaluatorName, MODEL_EVAL.UNIT_ID);
		writer.addDeclInput(MODEL_EVAL.P_INPUT.NAME, MODEL_EVAL.P_INPUT.A_REF,
				unitChain.peek());
		writer.addDeclInput(MODEL_EVAL.P_MODELS.NAME,
				MODEL_EVAL.P_MODELS.A_REF, "@" + modelLoaderName);
		writer.addDeclInput(MODEL_EVAL.P_PROJECT_NAME.NAME,
				MODEL_EVAL.P_PROJECT_NAME.A_REF, SPEC.P_PROJECT_NAME.REF);

		writer.addDeclInput(MODEL_EVAL.P_FRR.NAME, MODEL_EVAL.P_FRR.A_REF, "@"
				+ resolverName + "." + RANGE_RESOLVERS.O_FRR.NAME);

		writer.closeProcessorDecl();
		metaEntries.putValue(modelEvaluatorName, META_KEY_POS, xPos(1) + ","
				+ yPos(1));
		unitChain.push("@" + modelEvaluatorName);

	}

	/** Write model saver processor. */
	private void writeModelSaver() {
		String processorName = obtainUnitName(MODEL_SAVER.UNIT_ID);

		writer.openProcessorDecl(processorName, MODEL_SAVER.UNIT_ID);
		writer.addDeclInput(MODEL_SAVER.P_MODELS.NAME,
				MODEL_SAVER.P_MODELS.A_REF, unitChain.peek());
		writer.addDeclInput(MODEL_SAVER.P_EVALDIR.NAME,
				MODEL_SAVER.P_EVALDIR.A_DIR, SPEC.P_EVAL_RESULT_DIR.REF);

		writer.closeProcessorDecl();
		metaEntries.putValue(processorName, META_KEY_POS, xPos(1) + ","
				+ yPos(1));
		unitChain.push("@" + processorName);
	}

	/** Write model loader processor. */
	private void writeModelLoader() {

		writer.addSpecParam(SPEC.P_QUALITY_MODEL.NAME,
				SPEC.P_QUALITY_MODEL.A_FILE);
		metaEntries.putValue(SPEC.P_QUALITY_MODEL.NAME, META_KEY_POS, xPos(3)
				+ "," + (yPos(-1) + 10));

		writer.openProcessorDecl(modelLoaderName, MODEL_LOADER.UNIT_ID);

		writer.addDeclInput(MODEL_LOADER.P_INPUT.NAME,
				MODEL_LOADER.P_INPUT.A_FILE, SPEC.P_QUALITY_MODEL.REF);

		writer.closeProcessorDecl();
		metaEntries.putValue(modelLoaderName, META_KEY_POS, xPos(2) + ","
				+ yPos());
	}

	/** Write metadata for the generated units. */
	private void writeMetadata() {
		writer.openMeta(META_DATA_TYPE);
		writer.addMetaEntries(SPEC.P_INPUT.NAME, META_KEY_POS, (xPos(0) + 40)
				+ ",0");

		writer.openMetaEntries(SPEC.P_OUTPUT.NAME);
		writer.addMetaEntry(META_KEY_POS, xPos(1) + ",0");
		writer.addMetaEntry(META_KEY_EDGES_INVISIBLE, "true");
		writer.closeMetaEntries();

		writer.openMetaEntries(SPEC.P_MAP.NAME);
		writer.addMetaEntry(META_KEY_POS, xPos(2) + ",0");
		writer.addMetaEntry(META_KEY_EDGES_INVISIBLE, "true");
		writer.closeMetaEntries();

		writer.openMetaEntries(SPEC.P_EVAL_RESULT_DIR.NAME);
		writer.addMetaEntry(META_KEY_POS, xPos(1) + ",60");
		writer.addMetaEntry(META_KEY_EDGES_INVISIBLE, "true");
		writer.closeMetaEntries();

		if (isILInputParameterAdded) {
			writer.openMetaEntries(SPEC.P_IL_INPUT.NAME);
			writer.addMetaEntry(META_KEY_POS, xPos(2) + ",80");
			writer.closeMetaEntries();
		}

		for (String namespace : metaEntries.getFirstKeys()) {
			for (String key : metaEntries.getSecondKeys(namespace)) {
				writer.addMetaEntries(namespace, key, metaEntries.getValue(
						namespace, key));
			}

		}

		writer.addMetaEntries(SPEC.O_RESULT, META_KEY_POS, xPos(1) + ","
				+ yPos(1));

		addComment();

		writer.closeMeta();
	}

	/** Add comment about generation. */
	private void addComment() {
		String commentId = "comment_1";

		StringBuilder comment = new StringBuilder();

		comment.append("This block was generated by the Quamoco editor at "
				+ new Date() + ". DO NOT CHANGE MANUALLY AS FILE "
				+ "WILL BE OVERWRITTEN BY THE GENERATOR. ");

		comment.append("This block was generated for the following models:");

		Iterator<QualityModel> it = models.iterator();

		while (it.hasNext()) {
			// determine model file location
			Resource resource = it.next().eResource();
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
					new Path(resource.getURI().toPlatformString(true)));

			comment.append(file.getLocation().toOSString());
			if (it.hasNext()) {
				comment.append(", ");
			}
		}

		writer
				.addMetaEntries(NAMESPACE_COMMENTS, commentId, comment
						.toString());
		writer.addMetaEntries(NAMESPACE_COMMENT_BOUNDS, commentId, xPos(3)
				+ ",0,350," + (85 + models.size() * 10));
	}

	/** Obtain unit name. */
	private String obtainUnitName(String blockId) {
		CCSMAssert.isTrue(blockIds.add(blockId),
				"We assume to have only one declaration of each block.");
		return StringUtils.getLastPart(blockId, '.');
	}

	/**
	 * Returns the current vertical position plus any number of additional rows.
	 */
	private int yPos(int additionalRows) {
		return (unitChain.size() + additionalRows) * 80;
	}

	/** Returns the current vertical position. */
	private int yPos() {
		return yPos(0);
	}

	/**
	 * This comparator implements a hack to ensure that certain processors are
	 * added at the end of the chain.
	 */
	private class MagicComparator implements Comparator<String> {

		/** The set of late processors. */
		private final HashSet<String> set = CollectionUtils
				.asHashSet(LATE_PROCESSOR_IDS);

		/** {@inheritDoc} */
		@Override
		public int compare(String s1, String s2) {
			if (set.contains(s1)) {
				return 1;
			}
			if (set.contains(s2)) {
				return -1;
			}
			return s1.compareTo(s2);
		}

	}

}
