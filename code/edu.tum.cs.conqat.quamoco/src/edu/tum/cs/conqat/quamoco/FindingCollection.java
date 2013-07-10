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

import java.util.HashSet;

import org.conqat.engine.commons.findings.Finding;
import org.conqat.engine.commons.findings.FindingsList;
import org.conqat.engine.commons.node.IConQATNode;

/**
 * Simple collection class for findings that makes findings collections more
 * explicit that the generic <code>HashSet&lt;Finding&gt;</code>. This is
 * different from {@link FindingsList} as the findings are not bound to an
 * {@link IConQATNode}. However, we still need to discuss if this implementation
 * is clever.
 * 
 * @author deissenb
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating YELLOW Hash: 83C83FB94FAA4C33A5F070D1A1A10055
 */
public class FindingCollection extends HashSet<Finding> {
	// no actual implementation
}
