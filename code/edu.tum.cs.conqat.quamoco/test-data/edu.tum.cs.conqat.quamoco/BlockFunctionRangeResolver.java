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

/*-----------------------------------------------------------------------+
 | edu.tum.cs.conqat.quamoco
 |                                                                       |
   $Id: BlockFunctionRangeResolver.java 4974 2012-02-17 09:34:10Z lochmann $            
 |                                                                       |
 | Copyright (c)  2004-2010 Technische Universitaet Muenchen             |
 |                                                                       |
 | Technische Universitaet Muenchen               #########  ##########  |
 | Institut fuer Informatik - Lehrstuhl IV           ##  ##  ##  ##  ##  |
 | Prof. Dr. Manfred Broy                            ##  ##  ##  ##  ##  |
 | Boltzmannstr. 3                                   ##  ##  ##  ##  ##  |
 | 85748 Garching bei Muenchen                       ##  ##  ##  ##  ##  |
 | Germany                                           ##  ######  ##  ##  |
 +-----------------------------------------------------------------------*/
package edu.tum.cs.conqat.quamoco;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import edu.tum.cs.commons.region.Region;
import edu.tum.cs.commons.region.RegionSet;
import edu.tum.cs.conqat.commons.ConQATProcessorBase;
import edu.tum.cs.conqat.commons.findings.location.CodeLineLocation;
import edu.tum.cs.conqat.core.AConQATAttribute;
import edu.tum.cs.conqat.core.AConQATParameter;
import edu.tum.cs.conqat.core.AConQATProcessor;
import edu.tum.cs.conqat.core.ConQATException;
import edu.tum.cs.conqat.sourcecode.library.SourceCodeLibrary;
import edu.tum.cs.conqat.sourcecode.scope.SourceCodeElement;
import edu.tum.cs.scanner.ELanguage;
import edu.tum.cs.scanner.ETokenType;
import edu.tum.cs.scanner.IToken;

/**
 * {@ConQAT.Doc}
 * 
 * @author juergens
 * @author $Author: lochmann $
 * @version $Rev: 4974 $
 * @levd.rating RED Rev:
 */
@AConQATProcessor(description = "Resolves function ranges for C-style languages.")
public class BlockFunctionRangeResolver extends ConQATProcessorBase implements
		IFunctionRangeResolver {

	/** Language in which file is in. */
	private ELanguage language;

	/** Keywords that open a new scope */
	private final Set<ETokenType> scopeKeywords = new HashSet<ETokenType>();

	/** Counter that keeps track of nesting depth */
	private final Stack<Integer> scopes = new Stack<Integer>();

	/** Nesting depth at which we expect method blocks */
	private int methodDepth;

	
	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "method", minOccurrences = 1, maxOccurrences = 1, description = ""
			+ "Nesting depth of method blocks")
	public void setMethodDepth(
			@AConQATAttribute(name = "depth", description = "Nesting depth of method blocks") int methodDepth) {
		this.methodDepth = methodDepth;
	}

	/** {@ConQAT.Doc} */
	@AConQATParameter(name = "scope", minOccurrences = 0, maxOccurrences = -1, description = ""
			+ "Keyword that opens a scope")
	public void addScopeKeyword(
			@AConQATAttribute(name = "keywords", description = "Examples are class, namespace, ...") ETokenType scopeKeyword) {
		scopeKeywords.add(scopeKeyword);
	}

	/** {@inheritDoc} */
	@Override
	public Region obtainRegion(CodeLineLocation location)
			throws ConQATException {

		SourceCodeElement element = new SourceCodeElement(location.getFile()
				.getCanonicalPath(), Charset.defaultCharset(), language);
		List<IToken> tokens = SourceCodeLibrary.getInstance()
				.getTokens(element);
		RegionSet functionRegions = new RegionSet();
		createRegions(tokens, functionRegions);

		return choose(functionRegions, location.getFirstLine());
	}

	/** Determines whether a token opens or closes a method */
	private void createRegions(List<IToken> tokens, RegionSet regions) {

		int regionStart = -1;
		for (IToken token : tokens) {

			// Creates a new scope, if a scope keyword is seen
			if (scopeKeywords.contains(token.getType())) {
				// push new scope
				scopes.push(-1);
			}

			if (token.getType() == ETokenType.LBRACE) {
				incNestingDepth();
				if (hasMethodDepth()) {
					regionStart = lineNumber(token);
				}
			}

			if (token.getType() == ETokenType.RBRACE) {
				if (hasMethodDepth()) {
					Region functionRegion = new Region(regionStart,
							lineNumber(token));
					regions.add(functionRegion);
				}

				decNestingDepth();
				checkTearDownScope();
			}
		}

	}

	/** Get token line number */
	private int lineNumber(IToken token) {
		return token.getLineNumber() + 1; // token linenumbers are 0 based;
	}

	/** Checks whether a token is in method depth */
	private boolean hasMethodDepth() {
		return getDepth() == methodDepth;
	}

	/** Tears down a scope, if its closing bracket is seen */
	private void checkTearDownScope() {
		// don't delete the last scope
		if (getDepth() == -1 && scopes.size() > 1) {
			scopes.pop();
		}
	}

	/** Returns depth */
	private int getDepth() {
		return scopes.peek();
	}

	/** Increments current depth counter */
	private void incNestingDepth() {
		scopes.push(scopes.pop() + 1);
	}

	/** Decrements current depth counter */
	private void decNestingDepth() {
		scopes.push(scopes.pop() - 1);
	}

	/**
	 * Choose function region that matches this code location. Returns first
	 * region that contains line, or null, if no region contains it.
	 */
	private Region choose(RegionSet functionRegions, int firstLine) {
		List<Region> containers = new ArrayList<Region>();
		for (Region region : functionRegions) {
			if (region.containsPosition(firstLine)) {
				containers.add(region);
			}
		}

		if (containers.size() > 0) {
			return containers.get(0);
		}

		return null;
	}

	/** {@inheritDoc} */
	@Override
	public Object process() {
		return this;
	}

}
