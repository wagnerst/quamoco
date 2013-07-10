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

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.conqat.engine.cache.strategy.CachingStrategy;
import org.conqat.engine.commons.logging.IncludeExcludeListLogMessage;
import org.conqat.engine.commons.logging.StructuredLogTags;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IContentAccessor;
import org.conqat.engine.resource.scope.filesystem.FileSystemScope;
import org.conqat.engine.resource.util.ConQATDirectoryScanner;
import org.conqat.engine.resource.util.ConQATFileUtils;
import org.conqat.lib.commons.filesystem.CanonicalFile;

@AConQATProcessor(description = "A scope working on the file system and reading files.")
public class CachingFileSystemScope extends FileSystemScope {
	
	/** The strategy for caching */
	protected CachingStrategy cachingStrategy;

	@AConQATParameter(name = "cachingstrategy", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "The Caching Strategy")
	public void setCachingStrategy(
			@AConQATAttribute(name = "attr", description = "value") CachingStrategy value) {
		this.cachingStrategy = value;
	}
	
	/** {@inheritDoc} */
	@Override
	protected IContentAccessor[] createAccessors() throws ConQATException {
		// create better error message for this case
		if (!new File(rootDirectoryName).isDirectory()) {
			throw new ConQATException("The root directory '"
					+ rootDirectoryName
					+ "' does not exist or is not a directory!");
		}

		String[] filenames = ConQATDirectoryScanner.scan(rootDirectoryName,
				caseSensitive, includePatterns.toArray(new String[0]),
				excludePatterns.toArray(new String[0]));

		IContentAccessor[] result = new IContentAccessor[filenames.length];
		CanonicalFile root = ConQATFileUtils
				.createCanonicalFile(rootDirectoryName);

		Set<String> uniformPaths = new HashSet<String>();
		for (int i = 0; i < filenames.length; ++i) {
			CachingFileContentAccessor accessor = new CachingFileContentAccessor(
					ConQATFileUtils.createCanonicalFile(new File(root,
							filenames[i])), root, projectName, this.cachingStrategy);
			result[i] = accessor;
			uniformPaths.add(accessor.getUniformPath());
		}

		getLogger().info(
				new IncludeExcludeListLogMessage("files", true, uniformPaths,
						StructuredLogTags.SCOPE, StructuredLogTags.FILES));

		return result;
	}
}
