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

package de.quamoco.adaptation.wizard.util.modelattributes.provider;

import org.eclipse.jface.viewers.LabelProvider;

import de.quamoco.adaptation.model.Purpose;
import de.quamoco.qm.NamedElement;

/**
 * A simple label provider for {@link NamedElement}s.
 * @author Franz Becker
 * @levd.rating YELLOW Hash: F50E778BF80F9B7E8131A2D722AA2D77
 */
public class NamedElementLabelProvider extends LabelProvider {

	/**
	 * If the passed element is an instance of {@link NamedElement}
	 * the name will be returned, {@link LabelProvider#getText(Object)}
	 * otherwise.
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof NamedElement) {
			return ((NamedElement) element).getName();
		} else if (element instanceof Purpose) {
			return ((Purpose) element).getName();
		}
		return super.getText(element);
	}
	
}
