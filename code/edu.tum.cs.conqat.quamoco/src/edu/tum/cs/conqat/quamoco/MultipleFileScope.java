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
$Id: SingleFileScope.java 32087 2010-12-22 21:03:01Z hummelb $
|                                                                          |
| Copyright 2005-2010 by the ConQAT Project                                |
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
package edu.tum.cs.conqat.quamoco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IContentAccessor;
import org.conqat.engine.resource.scope.ScopeBase;
import org.conqat.engine.resource.scope.filesystem.FileContentAccessor;
import org.conqat.engine.resource.util.ConQATFileUtils;
import org.conqat.engine.resource.util.UniformPathUtils;
import org.conqat.lib.commons.filesystem.CanonicalFile;

/**
 * {@ConQAT.Doc}
 * 
 * @author $Author: hummelb $
 * @version $Rev: 32087 $
 * @levd.rating GREEN Hash: AC0141318AD3CDF31AEFB254632E5839
 */
@AConQATProcessor(description = "Scope for a single file.")
public class MultipleFileScope extends ScopeBase {

	/** file to load */
	private List<String> paths = new LinkedList<String>();

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "file", description = "", minOccurrences = 1)
	public void addReport(
			@AConQATAttribute(name = "path", description = "path") String file) {
		paths.add(file);
	}

	/** lenient mode */
	@AConQATFieldParameter(optional = true, parameter = "lenient", attribute = "attr", description = "In lenient mode not existing files are ignored.")
	public boolean lenient = false;

	/** {@inheritDoc} */
	@Override
	protected IContentAccessor[] createAccessors() throws ConQATException {
		List<IContentAccessor> result = new ArrayList<IContentAccessor>();

		for (String path : this.paths) {
			CanonicalFile file = ConQATFileUtils.createCanonicalFile(path);
			if (!file.isReadableFile()) {
				String msg = "Cannot read file at path " + path;
				if (lenient) {
					getLogger().warn(msg);
				} else {
					throw new ConQATException(msg);
				}
			} else {
				result.add(new FileContentAccessor(file, projectName
						+ UniformPathUtils.SEPARATOR + file.getName()));
			}
		}

		IContentAccessor[] resarr = new IContentAccessor[result.size()];
		for (int i = 0; i < resarr.length; i++) {
			resarr[i] = result.get(i);
		}

		return resarr;
	}
}
