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

package edu.tum.cs.conqat.quamoco.java;

import java.io.File;
import java.io.StringReader;

import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.java.library.JavaLibrary;
import org.conqat.engine.java.resource.JavaElementFactory;
import org.conqat.engine.resource.IContentAccessor;
import org.conqat.engine.resource.scope.filesystem.FileContentAccessor;
import org.conqat.engine.resource.text.TextElement;
import org.conqat.engine.sourcecode.resource.ITokenElement;

/**
 * Java Factory
 * 
 * @author lochmann
 * @author $Author: hummelb $
 * @version $Rev: 18709 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "Factory for the creation of Java elements, throwing exception for java files with wrong package statement.")
public class JavaFilteringElementFactory extends JavaElementFactory {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ITokenElement create(IContentAccessor accessor)
			throws ConQATException {

		String className = getFQClassName(accessor);

		if (!(accessor instanceof FileContentAccessor)) {
			throw new ConQATException("Only FileContentAccessor is supported.");
		}

		FileContentAccessor fca = (FileContentAccessor) accessor;
		File javaFile = new File(fca.getLocation());

		String[] parts = className.split("\\.");

		if (!javaFile.getName().equals(parts[parts.length - 1] + ".java")) {
			throw new ConQATException(
					"Filename of .java file not conform to class name.");
		}

		File parentFile = javaFile.getParentFile();
		if (parentFile == null) {
			throw new ConQATException("This should never happen.");
		}

		for (int i = parts.length - 2; i >= 0; i--) {
			String part = parts[i].trim();
			if (!parentFile.getName().equals(part)) {
				throw new ConQATException(
						"Directory of .java file not conform to package name.");
			}
			parentFile = parentFile.getParentFile();
			if (parentFile == null) {
				throw new ConQATException("This should never happen.");
			}

		}

		// all is ok, invoce super
		return super.create(accessor);
	}

	/**
	 * Get full qualified class name of a class. The name is derived by
	 * analyzing the package statement in the file.
	 */
	private String getFQClassName(IContentAccessor accessor)
			throws ConQATException {
		return JavaLibrary.getFQClassName(
				accessor.getLocation(),
				new StringReader(new TextElement(accessor, encoding)
						.getUnfilteredTextContent()));
	}

}