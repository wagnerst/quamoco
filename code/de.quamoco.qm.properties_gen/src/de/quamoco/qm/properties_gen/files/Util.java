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

package de.quamoco.qm.properties_gen.files;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

public class Util {

	public static EStructuralFeature lastFeature(EStructuralFeature feature) {
		EStructuralFeature f = feature;
		EClass c = f.getEContainingClass();
		List<EStructuralFeature> fs = c.getEStructuralFeatures();
		int i = fs.indexOf(f) - 1;
		while (c != null) {
			while (i >= 0) {
				f = fs.get(i);
				if (isProperties(f)) {
					return f;
				}
				i--;
			}
			if (c.getESuperTypes().isEmpty()) {
				c = null;
			} else {
				c = c.getESuperTypes().get(0);
				fs = c.getEAllStructuralFeatures();
				i = fs.size() - 1;
			}
		}
		return feature;
	}

	private static boolean isProperties(EStructuralFeature f) {
		if (f instanceof EReference) {
			EReference r = (EReference) f;
			if (r.isContainer() || r.isContainment()) {
				return false;
			}
		}
		return true;
	}
}
