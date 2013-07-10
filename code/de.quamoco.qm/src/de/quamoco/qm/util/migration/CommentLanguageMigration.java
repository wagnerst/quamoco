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

package de.quamoco.qm.util.migration;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Metamodel;
import org.eclipse.emf.edapt.migration.Model;

/**
 * Flatten different language versions of a migration. If there is a German
 * version, take it. If there is not, take the English version.
 * 
 * @author herrmama
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
public class CommentLanguageMigration extends CustomMigration {

	/** {@inheritDoc} */
	@Override
	public void migrateBefore(Model model, Metamodel metamodel) {
		EAttribute descriptionAttribute = metamodel
				.getEAttribute("qm.Comment.description");
		for (Instance element : model.getAllInstances("qm.CommentedElement")) {
			Instance comment = getComment(element, "GERMAN");
			if (comment == null || comment.get(descriptionAttribute) == null) {
				comment = getComment(element, "ENGLISH");
			}
			if (comment != null) {
				element.set(descriptionAttribute,
						comment.get(descriptionAttribute));
			}
		}
	}

	/** Get the comment in a certain language. */
	private Instance getComment(Instance commentedElement, String language) {
		for (Instance comment : commentedElement.getLinks("comment")) {
			if (language.equals(comment.get("language"))) {
				return comment;
			}
		}
		return null;
	}
}
