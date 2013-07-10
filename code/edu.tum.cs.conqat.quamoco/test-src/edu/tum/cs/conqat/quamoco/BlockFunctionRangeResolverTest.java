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
 $Id: BlockFunctionRangeResolverTest.java 5030 2012-09-19 12:24:47Z lochmann $            
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


import org.conqat.engine.commons.findings.location.TextRegionLocation;
import org.conqat.engine.core.core.ConQATException;
import org.conqat.engine.core.logging.testutils.ProcessorInfoMock;
import org.conqat.engine.resource.regions.RegionSetDictionary;
import org.conqat.lib.commons.filesystem.CanonicalFile;
import org.conqat.lib.commons.region.Region;
import org.conqat.lib.commons.test.CCSMTestCaseBase;
import org.conqat.lib.scanner.ELanguage;
import org.conqat.lib.scanner.ETokenType;

/**
 * Test case for {@link BlockFunctionRangeResolver}
 * 
 * @author juergens
 * @author $Author: lochmann $
 * @version $Rev: 5030 $
 * @levd.rating RED Hash: A060649DD0CF0D8F0348F216BF1EF1B6
 */
public class BlockFunctionRangeResolverTest extends CCSMTestCaseBase {

	public void testCR3556() throws Exception {
		RegionSetDictionary result = resolveJava("Driver.java", 351);
		assertNotNull(result);
		assertEquals(344, result.regionSets.values().iterator().next()
				.iterator().next().getStart());
		assertEquals(353, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/** Test correct resolution of location in method for java */
	public void testResolveExistingMethodForJava() throws ConQATException {
		int line = 86; // in method from 79-92
		RegionSetDictionary result = resolveJava(
				"BlockFunctionRangeResolver.java", line);

		assertNotNull(result);
		assertEquals(79, result.regionSets.values().iterator().next()
				.iterator().next().getStart());
		assertEquals(92, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/** Test a real example that failed on 08.01.2011 */
	public void testRealExample() throws ConQATException {

		int line = 255;
		RegionSetDictionary result = resolveJava(
				"FunctionRangeResolverExampleOfRealSystem.java", line);

		assertNotNull(result);
		assertEquals(251, result.regionSets.values().iterator().next()
				.iterator().next().getStart());
		assertEquals(261, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/** Tests block resolution for java */
	public void testResolveFieldLocationForJava() throws ConQATException {
		int line = 54; // contains field
		RegionSetDictionary result = resolveJava(
				"BlockFunctionRangeResolver.java", line);
		assertNull(result.regionSets.values().iterator().next().iterator()
				.next());
	}

	/** Test correct resolution of location in method for java */
	public void testResolveExistingMethodForCpp() throws ConQATException {
		int line = 77; // in method from 61-81
		RegionSetDictionary result = resolveCpp("mac.c", line);

		assertNotNull(result);
		assertEquals(61, result.regionSets.values().iterator().next()
				.iterator().next().getStart());
		assertEquals(81, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/** Test correct resolution of location in method for java */
	public void testResolveExistingMethodForCpp2() throws ConQATException {
		int line = 51; // in method from 1-63
		RegionSetDictionary result = resolveCpp("sdbm.c", line);

		assertNotNull(result);
		assertEquals(1, result.regionSets.values().iterator().next().iterator()
				.next().getStart());
		assertEquals(63, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/**
	 * Test correct resolution of location in method for cpp.
	 * 
	 * TODO (EJ) CR#1827: This test fails, since we currently cannot deal
	 * correctly with preprocessor statements that destroy the balancing of
	 * code.
	 */
	// public void testResolveExistingMethodForCpp3() throws ConQATException {
	// int line = 1726; // in method from 1704-1747
	// Region result = resolveCpp("jlibtool.c", line);
	//
	// assertNotNull(result);
	// assertEquals(1704, result.getStart());
	// assertEquals(1747, result.getEnd());
	// }

	/**
	 * Test correct resolution of location in method for cpp.
	 * 
	 */
	public void testResolveExistingMethodForCpp4() throws ConQATException {
		for (int line = 83; line <= 155; line++) {
			RegionSetDictionary result = resolveCpp("apr_thread_pool.c", line);
			assertRegion(result.regionSets.values().iterator().next()
					.iterator().next());
		}
	}

	/** Assert specific region in file */
	private void assertRegion(Region result) {
		assertNotNull(result);
		assertEquals(83, result.getStart());
		assertEquals(155, result.getEnd());
	}

	/** Another bug driven test */
	public void testResolveExistingMethodForCpp5() throws ConQATException {
		int line = 266; // in method from 239-341
		RegionSetDictionary result = resolveCpp("apr_thread_pool.c", line);

		assertNotNull(result);
		assertEquals(239, result.regionSets.values().iterator().next()
				.iterator().next().getStart());
		assertEquals(341, result.regionSets.values().iterator().next()
				.iterator().next().getEnd());
	}

	/** Tests block resolution for java */
	public void testResolveIncludeLocationForCpp() throws ConQATException {
		int line = 48; // contains include
		RegionSetDictionary result = resolveCpp("mac.c", line);
		assertNull(result);
	}

	/** Resolves function for java */
	private RegionSetDictionary resolveJava(String filename, int line)
			throws ConQATException {
		return resolve(filename, line, 1, ELanguage.JAVA, ETokenType.CLASS);
	}

	/** Resolves function for C/C++ */
	private RegionSetDictionary resolveCpp(String filename, int line)
			throws ConQATException {
		return resolve(filename, line, 1, ELanguage.CPP, ETokenType.CLASS,
				ETokenType.NAMESPACE);
	}

	/** Performs language independent resolution */
	private RegionSetDictionary resolve(String filename, int line,
			int methodDepth, ELanguage language, ETokenType... scopeKeywords)
			throws ConQATException {
		// create resolver
		BlockFunctionRangeResolver resolver = new BlockFunctionRangeResolver();
		resolver.init(new ProcessorInfoMock());
		resolver.setMethodDepth(methodDepth);
		resolver.setLanguage(language);
		for (ETokenType scopeKeyword : scopeKeywords) {
			resolver.addScopeKeyword(scopeKeyword);
		}

		// create location
		CanonicalFile file = useCanonicalTestFile(filename);

		TextRegionLocation location = new TextRegionLocation(
				file.getCanonicalPath(), file.getCanonicalPath(), 10 ,100, line, line);

		return resolver.obtainRegion(location);

	}
}
