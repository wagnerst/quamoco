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

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LightweightXMLParser {

	private static Set<String> htmlTags = new HashSet<String>();
	static {
		htmlTags.add("br");
		htmlTags.add("img");
		htmlTags.add("input");
		htmlTags.add("p");
		htmlTags.add("li");
		htmlTags.add("meta");
		htmlTags.add("link");
	}

	public static Document parseXML(byte[] b, boolean ignoreNamespaces)
			throws IOException {
		return parseXML(new ByteArrayInputStream(b), ignoreNamespaces);
	}

	public static Document parseXML(InputStream inst, boolean ignoreNamespaces)
			throws IOException {
		return parseXML(inst, ignoreNamespaces, "US-ASCII");
	}

	public static Document parseHTML(InputStream inst,
			boolean ignoreNamespaces, String defaultCharset) throws IOException {
		return parseXML(inst, ignoreNamespaces, defaultCharset, htmlTags);
	}

	public static Document parseXML(InputStream inst, boolean ignoreNamespaces,
			String defaultCharset) throws IOException {
		return parseXML(inst, ignoreNamespaces, defaultCharset, null);
	}

	public static Document parseXML(InputStream inst, boolean ignoreNamespaces,
			String defaultCharset, Set<String> tagsWithNoClosingOne)
			throws IOException {

		CharStream charStream;
		try {
			PushbackInputStream pin = new PushbackInputStream(inst, 5);
			byte[] fileStart = new byte[5];
			int l = pin.read(fileStart);

			String fileStartStr = new String(fileStart, 0, l, "US-ASCII");
			if (fileStartStr.equals("<?xml")) {
				StringBuffer sb = new StringBuffer();
				sb.append(new String(fileStart, "US-ASCII"));
				int i;
				while ((i = pin.read()) >= 0) {
					sb.append((char) i);
					if (i == '>') {
						break;
					}
				}
				String charset = defaultCharset;
				Matcher m = Pattern.compile(".*encoding=\"(.*?)\".*").matcher(
						sb);
				if (m.matches()) {
					charset = m.group(1);
				}
				// System.out.println("using charset: " + charset);
				charStream = new CharStream(pin, charset);
			} else {
				pin.unread(fileStart);
				charStream = new CharStream(pin, defaultCharset);
			}
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			throw new IOException("No valid XML-File: " + e.getMessage());
		}

		Stack<Document> stack = new Stack<Document>();
		stack.add(new Document());
		boolean slashFound = false;
		int status = 0;
		Text lastText = null;
		StringBuffer sb = new StringBuffer();
		StringBuffer sbAttrName = new StringBuffer();
		HashMap<String, String> attributes = new HashMap<String, String>();
		StringBuffer sbAttrValue = new StringBuffer();
		char attrEndChar = '>';
		boolean ignore = false;
		/*
		 * 0 = Text lesen, auf '>' warten 1 = Tagname lese, auf '>' oder ' '
		 * warten 2 = ' ' lesen, auf AttrName oder '>' warten 3 = AttrName lese,
		 * auf ' ' oder '=' warten, bei '>' aufh�ren 4 = ' ' lesen, auf '='
		 * warten, bei '>' aufh�ren 5 = auf AttrValue warten, ' ' lesen, bei
		 * '>' aufh�ren 6 = AttrValue lesen, auf attrEndChar warten 7 =
		 * AttrValue lesen (html compatibilit�t), auf ' ' warten, bei '>'
		 * aufh�ren
		 */
		char c;
		while ((c = charStream.read()) != Character.MAX_VALUE) {
			//System.out.println("read char: " + c + "   status=" + status);
			if (status == 0) {
				if (c == '<') {
					if (sb.length() > 0) {
						//JOptionPane.showMessageDialog(null, "length=" + sb.length() + " " + sb.toString());
						if (lastText != null) {
							lastText.text += sb.toString();
						} else {
							lastText = new Text(sb.toString());
							stack.peek().children.add(lastText);
						}
					}
					status = 1;
					sb = new StringBuffer();
				} else {
					sb.append(c);
				}
			} else if (status == 1) {
				if (Character.isWhitespace(c) || c == '>') {
					lastText = null;
					attributes = new HashMap<String, String>();
					status = 2;
				} else {
					sb.append(c);
					if (sb.toString().equals("![CDATA[")) {
						if (lastText == null) {
							lastText = new Text("");
							stack.peek().children.add(lastText);
						}
						lastText.text += readCData(charStream);
						status = 0;
						sb = new StringBuffer();
					} else if (sb.toString().equals("!--")) {
						readComment(charStream);
						status = 0;
						sb = new StringBuffer();
					}
				}
			} else if (status == 3) {
				if (Character.isWhitespace(c)) {
					status = 4;
				} else if (c == '=') {
					status = 5;
				} else if (c == '>') {
					status = 2;
				} else {
					sbAttrName.append(c);
				}
			} else if (status == 4) {
				if (c == '=') {
					status = 5;
				} else if (c == '>') {
					status = 2;
				} else if (!Character.isWhitespace(c)) {
					status = 2;
				}
			} else if (status == 5) {
				if (c == '"') {
					sbAttrValue = new StringBuffer();
					attrEndChar = c;
					status = 6;
				} else if (c == '\'') {
					sbAttrValue = new StringBuffer();
					attrEndChar = c;
					status = 6;
				} else if (c == '>') {
					status = 2;
				} else if (!Character.isWhitespace(c)) {
					sbAttrValue = new StringBuffer();
					sbAttrValue.append(c);
					status = 7;
				}
			} else if (status == 6) {
				if (c == attrEndChar) {
					attributes.put(sbAttrName.toString(), sbAttrValue
							.toString());
					status = 2;
					ignore = true;
				} else {
					sbAttrValue.append(c);
				}
			} else if (status == 7) {
				if (c <= ' ' || c == '>') {
					attributes.put(sbAttrName.toString(), sbAttrValue
							.toString());
					status = 2;
					if (c <= ' ') {
						ignore = true;
					}
				} else {
					sbAttrValue.append(c);
				}
			}
			if (!ignore && status == 2) {
				if (c == '>') {
					String tagName = sb.toString();
					char startChar = 0;
					if (tagName.length() > 0) {
						startChar = tagName.charAt(0);
					}
					if (startChar == '/') {
						Document doc = stack.peek();
						if (doc instanceof Node) {
							Node node = (Node) doc;
							if (node.tagName.equals(processNamespace(tagName
									.toLowerCase().substring(1),
									ignoreNamespaces))) {
								stack.pop();
							}
						}
					} else if (Character.isLetter(startChar)) {
						Node node = new Node(processNamespace(sb.toString()
								.toLowerCase(), ignoreNamespaces), attributes);


						stack.peek().children.add(node);
						if (!slashFound
								&& (tagsWithNoClosingOne == null || !tagsWithNoClosingOne
										.contains(node.getTagName()))) {

							stack.push(node);
						}
					}
					status = 0;
					sb = new StringBuffer();
				} else if (!Character.isWhitespace(c)) {
					status = 3;
					sbAttrName = new StringBuffer();
					sbAttrName.append(c);
				}
			}
//			System.out.println("PARSER: status=" + status + " sb=" + sb
//					+ " sbattravl=" + sbAttrValue);
			slashFound = c == '/';
			ignore = false;
		}
		return stack.firstElement();
	}

	protected static String processNamespace(String s, boolean ignoreNamespaces) {
		if (ignoreNamespaces) {
			int i = s.indexOf(':');
			if (i == -1) {
				return s;
			}
			if (s.length() < i + 1) {
				return s;
			}
			return s.substring(i + 1);
		}
		return s;
	}

	protected static String readCData(CharStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		int status = 0;
		char c;
		while ((c = in.read()) != Character.MAX_VALUE) {
			if (status == 0) {
				if (c == ']') {
					status = 1;
				} else {
					sb.append(c);
				}
			} else if (status == 1) {
				if (c == ']') {
					status = 2;
				} else {
					status = 0;
					sb.append(']');
					sb.append(c);
				}
			} else if (status == 2) {
				if (c == '>') {
					return sb.toString();
				} else {
					status = 0;
					sb.append(']');
					sb.append(']');
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	protected static String readComment(CharStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		int status = 0;
		char c;
		while ((c = in.read()) != Character.MAX_VALUE) {
			if (status == 0) {
				if (c == '-') {
					status = 1;
				} else {
					sb.append(c);
				}
			} else if (status == 1) {
				if (c == '-') {
					status = 2;
				} else {
					status = 0;
					sb.append('-');
					sb.append(c);
				}
			} else if (status == 2) {
				if (c == '>') {
					return sb.toString();
				} else {
					status = 0;
					sb.append('-');
					sb.append('-');
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String args[]) throws IOException {
		Document doc = LightweightXMLParser
				.parseHTML(
						new FileInputStream(
								"C:\\Dokumente und Einstellungen\\lochmann\\Desktop\\weather_result.xml"),
						true, "UTF-8");
		// System.out.println(doc);
		Node rootNode = doc.getFirstChildNode();
		if (rootNode == null) {
			return;
		}

		System.out.println(rootNode.toHTML());
	}
}
