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
   $Id: BlockFunctionRangeResolver.java 5030 2012-09-19 12:24:47Z lochmann $            
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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.conqat.engine.commons.ConQATProcessorBase;
import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.AConQATAttribute;
import org.conqat.engine.core.core.AConQATParameter;
import org.conqat.engine.core.core.AConQATProcessor;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.resource.IContentAccessor;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.engine.resource.scope.filesystem.FileContentAccessor;
import org.conqat.engine.sourcecode.analysis.Block;
import org.conqat.engine.sourcecode.analysis.BlockParser;
import org.conqat.engine.sourcecode.analysis.BlockParser.BlockParserException;
import org.conqat.engine.sourcecode.analysis.BlockParser.EMatchStyle;
import org.conqat.engine.sourcecode.resource.TokenElement;
import org.conqat.lib.commons.cache.LRUStraightCacheBase;
import org.conqat.lib.commons.collections.CollectionUtils;
import org.conqat.lib.commons.filesystem.CanonicalFile;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.region.RegionSet;
import org.conqat.lib.scanner.ELanguage;
import org.conqat.lib.scanner.ETokenType;
import org.conqat.lib.scanner.IToken;

/**
 * {@ConQAT.Doc}
 * 
 * @author juergens
 * @author $Author: lochmann $
 * @version $Rev: 5030 $
 * @levd.rating RED Hash: E961EAB19A75DC3336469E4E46E0CAFE
 */
@AConQATProcessor(description = "Resolves function ranges for C-style languages.")
public class BlockFunctionRangeResolver extends ConQATProcessorBase implements
		IFunctionRangeResolver {

	/** Language in which file is in. */
	private ELanguage language;

	/** Keywords that open a new scope */
	private final Set<ETokenType> scopeKeywords = new HashSet<ETokenType>();

	/** Nesting depth at which we expect method blocks */
	private int methodDepth;

	/** ConQAT Parameter */
	@AConQATParameter(name = "language", minOccurrences = 1, maxOccurrences = 1, description = "Define programming language.")
	public void setLanguage(
			@AConQATAttribute(name = "name", description = ""
					+ "A programming language of enum type org.conqat.lib.scanner.ELanguage") ELanguage language) {

		this.language = language;
	}

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

	/** Cache for RegionSets. Maps from UniformPaths to RegionSet */
	private RegionSetCache cache = new RegionSetCache(1024);

	/** Counter for locations without region (debug purposes) */
	private int locationsWithoutRegion = 0;

	/** Counter for locations processed (debug purposes) */
	private int locationsAll = 0;

	/** {@inheritDoc} */
	@Override
	public RegionSetDictionary obtainRegion(TextRegionLocation location)
			throws ConQATException {

		locationsAll++;

		// get the element from the cache
		RegionSet functionRegions = cache.getItem(location.getLocation());

		// if there is no element in the case, then something went wrong
		if (functionRegions == null) {
			locationsWithoutRegion++;
			return null;
		}

		// obtain the region for the line location
		Region region = choose(functionRegions, location.getRawStartLine());

		RegionSetDictionary commonRegionSetForMethod = new RegionSetDictionary();

		if (region == null) {
			getLogger().warn("No method-region found for location: " + location);
		} else {

			RegionSet regionSet = commonRegionSetForMethod.get(location
					.getUniformPath());
			if (regionSet == null) {
				regionSet = new RegionSet(location.getUniformPath());
				commonRegionSetForMethod.add(regionSet);
			}
			regionSet.add(region);
		}

		return commonRegionSetForMethod;
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

		locationsWithoutRegion++;
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public IFunctionRangeResolver process() {
		return this;
	}

	/**
	 * A LRU cache automatically creating RegionSets for ElementLocations
	 * 
	 * @author lochmann
	 * @author $Author: lochmann $
	 * @version $Rev: 5030 $
	 * @levd.rating RED Rev:
	 */
	private class RegionSetCache extends
			LRUStraightCacheBase<String, RegionSet, ConQATException> {
		// ATTENTION: ElementLocation does NOT WORK as key, because it does
		// not implement hashCode()

		/**
		 * @param maxSize
		 */
		public RegionSetCache(int maxSize) {
			super(maxSize);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected RegionSet obtainItem(String arg0) throws ConQATException {
			return calculateRegionSet(arg0);
		}

		/**
		 * Calculates the RegionSet for a location and returns it
		 * 
		 * @param location
		 * @return RegionSet
		 * @throws ConQATException
		 */
		private RegionSet calculateRegionSet(String location)
				throws ConQATException {

			// TODO (LH+FD) This should work on elements and not files

			File file = new File(location);
			if (!file.isFile()) {
				throw new ConQATException("Can't find file: " + location);
			}

			try {
				CanonicalFile canonicalFile = new CanonicalFile(file);
				IContentAccessor accessor = new FileContentAccessor(
						canonicalFile, canonicalFile.getParentFile(), file
								.getParentFile().getName());

				TokenElement tokenElement = new TokenElement(accessor,
						Charset.defaultCharset(), language);
				List<IToken> tokens = tokenElement.getTokens(getLogger());
				RegionSet functionRegions = new RegionSet();
				createRegions(tokens, functionRegions);
				return functionRegions;
			} catch (IOException e) {
				throw new ConQATException(e);
			} catch (BlockParserException e) {
				throw new ConQATException(e);
			}
		}

		/**
		 * Determines whether a token opens or closes a method
		 */
		private void createRegions(List<IToken> tokens, RegionSet regions)
				throws ConQATException, BlockParserException {

			BlockParser parser = new BlockParser(methodDepth,
					EMatchStyle.DECLARATION_AND_BODY, scopeKeywords,
					CollectionUtils.asHashSet(ETokenType.LBRACE),
					CollectionUtils.asHashSet(ETokenType.RBRACE));

			List<Block> methodBlocks = parser
					.createBlocks(new ArrayList<IToken>(tokens));

			for (int i = 0; i < methodBlocks.size(); i++) {
				Block block = methodBlocks.get(i);
				// token line numbers are 0-based so increment
				int startLine = block.getFirst().getLineNumber() + 1;
				int endLine = block.getLast().getLineNumber() + 1;
				regions.add(new Region(startLine, endLine));
			}

		}

	}

	/** {@inheritDoc} */
	@Override
	public int getNumLocationsWithoutRegion() {
		return locationsWithoutRegion;
	}

	/** {@inheritDoc} */
	@Override
	public int getNumLocations() {
		return locationsAll;
	}
}
