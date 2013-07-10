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

package edu.tum.cs.conqat.quamoco.lightweightxml;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Document implements XMLItem {
	protected List<XMLItem> children = new LinkedList<XMLItem>();

	public List<XMLItem> getChildren() {
		return children;
	}

	public void addChild(XMLItem child) {
		this.children.add(child);
	}

	public void setChildren(List<XMLItem> children) {
		this.children = children;
	}

	public List<Node> evaluateXPath(String xpath) {
		String name;

		{
			int i = xpath.indexOf('/');
			if (i != -1) {
				name = xpath.substring(0, i);
				xpath = xpath.substring(i + 1);
			} else {
				name = xpath;
				xpath = null;
			}
		}

		int selectedIndex;
		{
			int i = name.indexOf('(');
			if (i == -1) {
				selectedIndex = -1;
			} else {
				String s = name.substring(i);
				name = name.substring(0, i);

				if (s.startsWith("(")) {
					s = s.substring(1);
				}
				if (s.endsWith(")")) {
					s = s.substring(0, s.length() - 1);
				}
				try {
					selectedIndex = Integer.parseInt(s);
				} catch (NumberFormatException e) {
					selectedIndex = -1;
				}
			}
		}

		int counter = -1;
		List<Node> filteredChildren = new LinkedList<Node>();
		Iterator<XMLItem> j = children.iterator();
		while (j.hasNext()) {
			XMLItem o = j.next();
			if (o instanceof Node) {
				Node node = (Node) o;
				if (node.getTagName().equals(name)) {
					counter++;
					if (selectedIndex == -1 || counter == selectedIndex) {
						if (xpath == null) {
							filteredChildren.add(node);
						} else {
							filteredChildren.addAll(node.evaluateXPath(xpath));
						}
					}
				}
			}
		}
		return filteredChildren;
	}

	public String evaluateXPathSingleText(String xpath) {
		Node n = evaluateXPathSingle(xpath);
		if (n == null) {
			return null;
		}
		return n.getText();
	}

	public Node evaluateXPathSingle(String xpath) {
		List<Node> l = evaluateXPath(xpath);
		if (l.size() != 1) {
			return null;
		}
		return l.get(0);
	}

	public Node getFirstChildNode() {
		Iterator<XMLItem> i = children.iterator();
		while (i.hasNext()) {
			XMLItem node = i.next();
			if (node instanceof Node) {
				return (Node) node;
			}
		}
		return null;
	}

	public String toString() {
		return children.toString();
	}

	public void writeToStream(OutputStream out) throws IOException {
		writeToStream(out, null);
	}

	public void writeToStream(OutputStream out, String dtd) throws IOException {
		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				.getBytes("UTF-8"));
		if (dtd != null) {
			out.write(("<!DOCTYPE files SYSTEM \"" + dtd + "\">")
					.getBytes("UTF-8"));
		}
		Iterator<XMLItem> i = this.children.iterator();
		while (i.hasNext()) {
			XMLItem o = i.next();
			if (o instanceof Node) {
				((Node) o).writeToStream(out);
			} else {
				((Text) o).writeToStream(out);
			}
		}
	}
}
