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

import java.util.List;

import org.eclipse.emf.edapt.migration.CustomMigration;
import org.eclipse.emf.edapt.migration.Instance;
import org.eclipse.emf.edapt.migration.Model;

public abstract class TagMigrationBase extends CustomMigration {

	protected Instance getOrCreateTag(Model model, String name) {
		Instance t = findElementWithName(model, "qm.Tag", name);
		if (t == null) {
			t = model.newInstance("qm.Tag");
			Instance m = model.getAllInstances("qm.QualityModel").get(0);
			m.add("tagDefinitions", t);
			t.set("name", name);
		}
		return t;
	}

	protected Instance findElementWithName(Model model, String type, String name) {
		for (Instance tag : model.getAllInstances(type)) {
			if (name.equals(tag.get("name"))) {
				return tag;
			}
		}
		return null;
	}

	protected void addRefinement(Model model, Instance parent, Instance child,
			String tagname) {
		if (parent != null && child != null) {
			Instance r = getRefinement(parent, child);
			if (r == null) {
				r = model.newInstance("qm.Refinement");
				r.set("refinedBy", child);
				r.set("refines", parent);
			}
			Instance tag = getOrCreateTag(model, tagname);
			if (!r.getLinks("tags").contains(tag)) {
				r.add("tags", tag);
			}

		}
	}

	private Instance getRefinement(Instance parent, Instance child) {
		for (Instance r : parent.getLinks("refinements")) {
			if (r.get("refinedBy") == child) {
				return r;
			}
		}
		return null;
	}

	protected String assembleName(List<Instance> namedElements) {
		String name = "";
		for (Instance namedElement : namedElements) {
			if (name.length() > 0) {
				name += " and ";
			}
			name += "(" + namedElement.get("name") + ")";
		}
		return name;
	}

	protected Instance getOrCreateEntity(Model model, String name) {
		Instance e = findElementWithName(model, "qm.Entity", name);
		if (e == null) {
			e = model.newInstance("qm.Entity");
			Instance m = model.getAllInstances("qm.QualityModel").get(0);
			m.add("entities", e);
			e.set("name", name);
		}
		return e;
	}
}
