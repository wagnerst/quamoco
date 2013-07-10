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

import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.sourcecode.resource.ITokenElement;
import org.conqat.engine.sourcecode.resource.TokenElementProcessorBase;
import org.conqat.lib.commons.collections.UnmodifiableList;
import org.conqat.lib.scanner.ETokenType;
import org.conqat.lib.scanner.IToken;

/** Base class for counting tokens */
public abstract class TokenCounterBase extends TokenElementProcessorBase {

	/** {@inheritDoc} */
	@Override
	protected void processElement(ITokenElement element) throws ConQATException {
		int classCount = 0;
		UnmodifiableList<IToken> tokens = element.getTokens(getLogger());
		for (IToken token : tokens) {
			if (token.getType() == getTokenClass()) {
				classCount++;
			}
		}
		element.setValue(getWriteKey(), classCount);
	}

	/** The write key */
	protected abstract String getWriteKey();

	/** The token class to be counted */
	protected abstract ETokenType getTokenClass();

}
