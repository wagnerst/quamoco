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

package edu.tum.cs.conqat.quamoco;

import java.util.LinkedList;
import java.util.List;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.java.resource.IJavaResource;
import org.conqat.engine.resource.IResource;
import org.conqat.lib.commons.assertion.CCSMAssert;

/**
 * {@ConQAT.Doc}
 * 
 * @author Klaus Lochmann
 * @author $Author: heineman $
 * @version $Rev: 3646 $
 * @levd.rating YELLOW Hash: FB032160031FFA5756800A57F4D25DC5
 */
@AConQATProcessor(description = "Get a list of scopes as input, and returns one. If there are" +
		"more than 1 an exception is thrown.")
public class ScopeSelector extends ConQATProcessorBase {

	/** directory where to store it. */
	private List<IResource> scopes = new LinkedList<IResource>();

	/** Set input filename. */
	@AConQATParameter(name = "scopes", minOccurrences = 0,  description = ""
			+ "scopes")
	public void addScope(
			@AConQATAttribute(name = "attr", description = "value") IResource scope) {
		this.scopes.add(scope);
	}

	/**
	 * Check whether the file exists
	 * @throws ConQATException 
	 * 
	 * @throws ConQATException
	 */
	@Override
	public IJavaResource process() throws ConQATException {
		if(scopes.size() != 1) {
			throw new ConQATException("Other than 1 scope provided.");
		}
		
		Object result = scopes.get(0);
		
		CCSMAssert.isInstanceOf(result, IJavaResource.class);
		
		return (IJavaResource) result;
	}

}
