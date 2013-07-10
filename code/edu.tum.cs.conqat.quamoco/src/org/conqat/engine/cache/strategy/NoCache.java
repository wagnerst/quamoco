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

package org.conqat.engine.cache.strategy;

import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;

@AConQATProcessor(description = "Does not cache.")
public class NoCache extends CachingStrategy {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getContent(Object key) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setContent(Object key, byte[] content) {
		// do nothing
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CachingStrategy process() throws ConQATException {
		return this;
	}

}
