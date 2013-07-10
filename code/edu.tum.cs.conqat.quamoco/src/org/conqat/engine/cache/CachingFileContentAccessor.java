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

package org.conqat.engine.cache;

import org.conqat.engine.cache.strategy.CachingStrategy;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.scope.filesystem.FileContentAccessor;
import org.conqat.lib.commons.filesystem.CanonicalFile;

public class CachingFileContentAccessor extends FileContentAccessor {
	
	/** The caching strategy */
	protected CachingStrategy cachingStrategy;

	/**
	 * Constructor
	 * @param file
	 * @param root
	 * @param projectName
	 * @param cachingStrategy
	 */
	public CachingFileContentAccessor(CanonicalFile file, CanonicalFile root,
			String projectName, CachingStrategy cachingStrategy) {
		super(file, root, projectName);
		this.cachingStrategy = cachingStrategy;
	}

	/**
	 * Constructor
	 * @param file
	 * @param uniformPath
	 * @param cachingStrategy
	 */
	public CachingFileContentAccessor(CanonicalFile file, String uniformPath, CachingStrategy cachingStrategy) {
		super(file, uniformPath);
		this.cachingStrategy = cachingStrategy;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] getContent() throws ConQATException {
		byte[] result = this.cachingStrategy.getContent(this.getUniformPath());
		if(result == null) {
			result = super.getContent();
			this.cachingStrategy.setContent(this.getUniformPath(), result);
		}
		return result;
	}

}
