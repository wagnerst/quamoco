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

/**
 * Collection of constants used by the ConQAT block generator. Instead of plain
 * string constants, we used nested static classes for hierarchical structures
 * like block/parameter/attribute. This allows users of the constants to use the
 * auto-completion feature of the IDE in a more sensbile way. Also, it also
 * automatic refactorings like renaming a parameter so that all attributes of
 * the parameter are automatically renamed, too.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: B1DD0B203792BF0D544670D49A909512
 */
public class ConQATBlockGeneratorConstants {

	/** Block identifier annotation. */
	public final static String BLOCK_ID = "Block-Id";

	/** Key annotation. */
	public final static String KEY = "Key";

	/** Obtain x position for column. */
	public static int xPos(int columnNo) {
		return columnNo * 200;
	}

	/** The region set dictionary that covers the entire system. */
	public static final String SYSTEM_REGION_SET_DICTIONARY = "System";

	/** The meta data type used by cq.edit. */
	public static final String META_DATA_TYPE = "cq.edit";

	/** Meta data key for position. */
	public static final String META_KEY_POS = "pos";

	/** Meta data key for the hide edge feature. */
	public static final String META_KEY_EDGES_INVISIBLE = "edges_invisible";

	/** Name space for comments in the meta data. */
	public static final String NAMESPACE_COMMENTS = "#comments";

	/** Name space for comment bounds in the meta data. */
	public static final String NAMESPACE_COMMENT_BOUNDS = "#comment-bounds";

	/**
	 * For type compatibility regions was must put some units at the end of the
	 * chain. Currently, this array specifies these units.
	 */
	public static final String[] LATE_PROCESSOR_IDS = {
			"edu.tum.cs.conqat.quamoco.model.QJavaCloneDetection",
			"edu.tum.cs.conqat.quamoco.model.QCSCloneDetection",
			"edu.tum.cs.conqat.quamoco.model.QCPPCloneDetection" };

	/** Constants for the interface of generated block. */
	public static class SPEC {
		/** Parameter input. */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_SCOPE = "scope";
			/** Reference to input. */
			public static final String REF = "@" + NAME + "." + A_SCOPE;
		}
		
		/** Parameter project-name. */
		public static class P_PROJECT_NAME {
			/** Parameter project. */
			public final static String PROJECT = "project";
			/** Attribute {@value} */
			public final static String A_NAME = "name";
			/** Reference to input. */
			public static final String REF = "@" + PROJECT + "." + A_NAME;
		}

		/** IL input */
		public static class P_IL_INPUT {
			/** Parameter name. */
			public final static String NAME = "il-input";
			/** Attribute {@value} */
			public final static String A_SCOPE = "scope";
			/** Reference to input. */
			public static final String REF = "@" + NAME + "." + A_SCOPE;
		}

		/** Parameter map. */
		public static class P_MAP {
			/** Parameter name. */
			public final static String NAME = "map";
			/** Attribute {@value} */
			public final static String A_PROJECT = "project";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
			/** Reference to project. */
			public static final String REF_PROJECT = "@" + NAME + "."
					+ A_PROJECT;
			/** Reference to dir. */
			public static final String REF_DIR = "@" + NAME + "." + A_DIR;
		}

		/** Parameter output. */
		public static class P_OUTPUT {
			/** Parameter name. */
			public final static String NAME = "output";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
			/** Reference to output directory. */
			public static final String REF = "@" + NAME + "." + A_DIR;
		}

		/** Parameter metric-collection. */
		public static class P_METRIC_COLLECTION {
			/** Parameter name. */
			public final static String NAME = "metric-collection";
			/** Attribute {@value} */
			public final static String A_COLLECTION = "collection";
			/** Reference */
			public static final String REF = "@" + NAME + "." + A_COLLECTION;
		}

		/** Parameter quality model. */
		public static class P_QUALITY_MODEL {
			/** Parameter name. */
			public final static String NAME = "quality-model";
			/** Attribute {@value} */
			public final static String A_FILE = "file";
			/** Reference to model. */
			public static final String REF = "@" + NAME + "." + A_FILE;
		}

		/** Parameter evaluation result directory. */
		public static class P_EVAL_RESULT_DIR {
			/** Parameter name. */
			public final static String NAME = "evaluation-result";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
			/** Reference to model. */
			public static final String REF = "@" + NAME + "." + A_DIR;
		}

		/** Spec output {@value} */
		public static String O_METRICS = "metrics";

		/** Spec output {@value} */
		public static String O_RESULT = "result";

		/** Spec output {@value} */
		public static String O_SCOPE = "scope";
	}

	/**
	 * All blocks used in the generated chain must adhere to the same interface.
	 * This constant class specifies this interface.
	 */
	public static class DECL {
		/** Parameter input. */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_SCOPE = "scope";
		}

		/** Parameter IL input. */
		public static class P_IL_INPUT {
			/** Parameter name. */
			public final static String NAME = "il-input";
			/** Attribute {@value} */
			public final static String A_SCOPE = "scope";
		}

		/** Parameter map. */
		public static class P_MAP {
			/** Parameter name. */
			public final static String NAME = "map";
			/** Attribute {@value} */
			public final static String A_PROJECT = "project";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
		}

		/** Parameter output. */
		public static class P_OUTPUT {
			/** Parameter name. */
			public final static String NAME = "output";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
		}

		/** Decl output {@value} */
		public static String O_SCOPE = "scope";
	}

	/** Constants for metric collector processor. */
	public static class METRIC_COLLECTOR {
		/** Unit id. */
		public final static String UNIT_ID = "edu.tum.cs.conqat.quamoco.AutomaticMetricCollector";

		/** Parameter input. */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}

		/** Parameter {@value #NAME} */
		public static class P_MODELS {
			/** Parameter name. */
			public final static String NAME = "models";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}

		/** Parameter metric-collection. */
		public static class P_METRIC_COLLECTION {
			/** Parameter name. */
			public final static String NAME = "metric-collection";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}
	}

	/** Constants for model loader. */
	public static class MODEL_LOADER {
		/** Unit id. */
		public final static String UNIT_ID = "edu.tum.cs.conqat.quamoco.ModelLoader";

		/** Parameter {@value #NAME} */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_FILE = "file";
		}
	}

	/** Constants for range resolvers block. */
	public static class RANGE_RESOLVERS {
		/** Unit id. */
		public final static String UNIT_ID(String language) {
			return "edu.tum.cs.conqat.quamoco.model.Q" + language
					+ "RangeResolvers";
		}

		/** Parameter {@value #NAME} */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_FILE = "file";
		}

		/** Output{@value #NAME} */
		public static class O_FRR {
			/** Parameter name. */
			public final static String NAME = "function-range-resolver";
		}
	}

	/** Constants for model saver. */
	public static class MODEL_SAVER {
		/** Unit id. */
		public final static String UNIT_ID = "edu.tum.cs.conqat.quamoco.ModelSaver";

		/** Parameter {@value #NAME} */
		public static class P_EVALDIR {
			/** Parameter name. */
			public final static String NAME = "output";
			/** Attribute {@value} */
			public final static String A_DIR = "dir";
		}

		/** Parameter {@value #NAME} */
		public static class P_MODELS {
			/** Parameter name. */
			public final static String NAME = "models";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}
	}

	/** Constants for model evaluator. */
	public static class MODEL_EVAL {
		/** Unit id. */
		public final static String UNIT_ID = "edu.tum.cs.conqat.quamoco.ModelEvaluator";

		/** Parameter {@value #NAME} */
		public static class P_DICT {
			/** Parameter name. */
			public final static String NAME = "region-set-dictionary";
			/** Attribute {@value} */
			public final static String A_NAME = "name";
			/** Attribute {@value} */
			public final static String A_VALUE = "value";
		}

		/** Parameter {@value #NAME} */
		public static class P_INPUT {
			/** Parameter name. */
			public final static String NAME = "input";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}
		
		/** Parameter {@value #NAME} */
		public static class P_PROJECT_NAME {
			/** Parameter name. */
			public final static String NAME = "project-name";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}

		/** Parameter {@value #NAME} */
		public static class P_MODELS {
			/** Parameter name. */
			public final static String NAME = "models";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}

		/** Parameter {@value #NAME} */
		public static class P_FRR {
			/** Parameter name. */
			public final static String NAME = "function-range-resolver";
			/** Attribute {@value} */
			public final static String A_REF = "ref";
		}

	}

}
