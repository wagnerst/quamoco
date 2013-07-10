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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.quamoco.qm.QualityModel;
import de.quamoco.qm.Tag;

/**
 * 
 * @author Franz Becker
 * @levd.rating YELLOW Hash: 60C93D79D7D6A185DD80527B4AB4C3DB
 */
public class ContextComboContentProvider extends AbstractContentProvider {

	/** Reference to the current context. */
	private Set<String> context;

	public ContextComboContentProvider(Set<String> context) {
		this.context = context;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		Set<String> objects = new HashSet<String>();
		
		if (inputElement instanceof Collection<?>) {
			for (Object element : (Collection<?>) inputElement) {
				if (element instanceof QualityModel) {
					for (Tag tag : ((QualityModel) element).getTags()) {
						if (isContextTag(tag)) {
							objects.add(tag.getName());
						}						
					}				
				}
			}
		}
		/*
		 * Remove those elements which are already in the context
		 */
		objects.removeAll(context);
		
		return objects.toArray();
	}

	/**
	 * Returns true iff the tag's name contains exactly one "="
	 * @param tag
	 * @return
	 */
	private boolean isContextTag(Tag tag) {
		String tagName = tag.getName();
		if (tagName != null) {
			int firstIndex = tagName.indexOf("=");
			int lastIndex = tagName.lastIndexOf("=");
			if (firstIndex == lastIndex) {
				return firstIndex > 0;
			}
		}	
		return false;
	}

}
