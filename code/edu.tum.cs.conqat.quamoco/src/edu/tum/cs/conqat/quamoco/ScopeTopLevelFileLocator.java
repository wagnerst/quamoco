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
$Id: ScopeTopLevelFileLocator.java 4974 2012-02-17 09:34:10Z lochmann $
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

import java.io.File;
import java.util.List;

import org.conqat.engine.commons.ConQATParamDoc;
import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.node.IConQATNode;
import org.conqat.engine.core.core.AConQATFieldParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IElement;
import org.conqat.engine.resource.IResource;
import org.conqat.engine.resource.util.ResourceTraversalUtils;
import org.conqat.lib.commons.assertion.CCSMAssert;
import org.conqat.lib.commons.collections.CollectionUtils;

/** {@ConQAT.Doc} */
@AConQATProcessor(description = "")
public class ScopeTopLevelFileLocator extends ConQATProcessorBase {

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = ConQATParamDoc.INPUT_NAME, attribute = "scope", description = ConQATParamDoc.INPUT_DESC)
	public IResource input;

	/** {@ConQAT.Doc} */
	@AConQATFieldParameter(parameter = "file", attribute = "name", description = "The file name to look for")
	public String fileName;

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ConQATException
	 */
	@Override
	public String process() throws ConQATException {
		List<IElement> elements = ResourceTraversalUtils.listElements(input);
		if (elements.isEmpty()) {
			throw new ConQATException("No elements found!");
		}
		IElement element = CollectionUtils.getAny(elements);
		int depth = getDepth(element);

		String location = element.getLocation();

		for (int i = 0; i < depth - 1; i++) {
			int index = location.lastIndexOf(File.separator);
			CCSMAssert.isTrue(index > 0,
					"Number of separators should be >= depth of element");
			location = location.substring(0, index);
		}

		return location + File.separator + fileName;
	}

	/** Returns the depth of the node */
	private int getDepth(IConQATNode node) {
		if (node.getParent() == null) {
			return 0;
		}
		return 1 + getDepth(node.getParent());
	}

}
