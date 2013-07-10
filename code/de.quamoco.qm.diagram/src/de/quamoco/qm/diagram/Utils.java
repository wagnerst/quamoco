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

package de.quamoco.qm.diagram;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;

import de.quamoco.qm.Entity;
import de.quamoco.qm.Factor;
import de.quamoco.qm.FactorRefinement;
import de.quamoco.qm.util.QmModelUtils;

public class Utils {

	/**
	 * 
	 * @param a
	 * @param e
	 * @return
	 */
	public static Factor searchForFactor(EditingDomain domain, String a, Entity e) {
		for(Resource r: domain.getResourceSet().getResources()) {
			TreeIterator<EObject> i = r.getAllContents();
			while(i.hasNext()) {
				EObject obj = i.next();
				if(obj instanceof Factor) {
					Factor f = (Factor) obj;
					if(f.getName().equals(a) && f.getCharacterizes() == e) {
						return f;
					}
				}
			}
		}
		return null;
	}

	// TODO (KL): This is only used in the diagram plugin. It should thus be
	// moved over. (MH)
	public static class RefinementType {
		private final String name;

		public final static RefinementType REFINE = new RefinementType(
				"refines");
		public final static RefinementType DECOMPOSE = new RefinementType(
				"decompose");
		public final static RefinementType INVALID_REFINE = new RefinementType(
				"invalid");
		public final static RefinementType INVALID_DECOMPOSE = new RefinementType(
				"invalid");

		/**
		 * 
		 * @param name
		 */
		private RefinementType(String name) {
			this.name = name;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return this.name;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			return this.name.hashCode();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof RefinementType) {
				return ((RefinementType) obj).name.equals(this.name);
			}
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	// TODO (KL): This is only used in the diagram plugin. It should thus be
	// moved over. (MH)
	public static RefinementType getRefinementType(FactorRefinement ref) {
		if (ref.getParent() != null && ref.getChild() != null) {
			// Check for refinement
			if (ref.getParent().getName().equals(ref.getChild().getName())) {
				if (ref.getParent().getCharacterizes() != null
						&& ref.getChild().getCharacterizes() != null
						&& QmModelUtils.getAllIsAEntities(
								ref.getChild().getCharacterizes()).contains(
								ref.getParent().getCharacterizes())) {
					// System.out.println(ref.getParent().getCharacterizes() +
					// " in " + ref.getChild().getCharacterizes()
					// .getAllSuperEntities());
					return RefinementType.REFINE;
				}

				// System.out.println(ref.getParent().getCharacterizes() +
				// " in " + ref.getChild().getCharacterizes()
				// .getAllSuperEntities());
				return RefinementType.INVALID_REFINE;
			}

			// check for decomposition
			if (ref.getParent().getCharacterizes() == ref.getChild()
					.getCharacterizes()) {
				return RefinementType.DECOMPOSE;
			}
			return RefinementType.INVALID_DECOMPOSE;

		}
		// System.out.println("1");
		return RefinementType.INVALID_REFINE;

	}
}
