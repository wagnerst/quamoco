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

package edu.tum.cs.conqat.quamoco.volumemetric;

import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.lib.scanner.ETokenType;

/** {@ConQAT.Doc} */
@AConQATProcessor(description = "Counts the number of class tokens")
public class CppClassCounter extends TokenCounterBase {

	/** The key */
	private static final String CLASS_COUNT_KEY = "NumberOfClasses";

	/** {@inheritDoc} */
	@Override
	protected String getWriteKey() {
		return CLASS_COUNT_KEY;
	}

	/** {@inheritDoc} */
	@Override
	protected ETokenType getTokenClass() {
		return ETokenType.CLASS;
	}

}
