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

package de.quamoco.qm.properties.eval.generator;

//import java.util.Vector;
//
//import org.junit.Test;
//
//import de.quamoco.qm.properties.eval.EvaluationGenerator.MappingFunction;
//import de.quamoco.qm.properties.eval.EvaluationGenerator.Range;
//import de.quamoco.qm.properties.eval.EvaluationGenerator.Scale;

public class QIESLGeneratorImpTest {

	// String stdQIESL1 =
	// "// EVALUATION FUNCTION (#Documented Functions)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 500\n"+
	// "// Measure used for evaluation = #Documented Functions\n"+
	// "// Scale = NUMBER\n"+
	// "// Range = NA\n"+
	// "// Measure used for normalization = #Functions\n"+
	// "// Mapping function = LinearIncreasing\n"+
	// "// Lower bound = 0.4\n"+
	// "// Upper bound = 0.9\n"+
	// "\n"+
	// "result = linearDistribution(%%#Documented Functions%%/%%#Functions%%,0.4,0,0.9,500)";
	//	
	// String stdQIESL2 =
	// "// EVALUATION FUNCTION (Undocumented Functions)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 300\n"+
	// "// Measure used for evaluation = Undocumented Functions\n"+
	// "// Scale = FINDINGS\n"+
	// "// Range = METHOD\n"+
	// "// Measure used for normalization = Java/LOC\n"+
	// "// Mapping function = LinearDecreasing\n"+
	// "// Lower bound = 0.2\n"+
	// "// Upper bound = 0.5\n"+
	// "\n"+
	// "result = linearDistribution(\n"+
	// "extent(methodRange(%%Undocumented Functions%%))\n"+
	// "/%%Java/LOC%%,0.2,300,0.5,0)";
	//	
	// String stdQIESL3 =
	// "// AGGREGATION (FACTOR RANKING)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 500\n"+
	// "// FACTOR Name of Factor 1; 1; 228.33333333333334; 200; positive; default\n"+
	// "// FACTOR Name of Factor 2; 2; 128.33333333333331; 100; refinement; default\n"+
	// "// FACTOR Name of Factor 3; 3; 61.666666666666664; 400; negative; default\n"+
	// "// FACTOR Name of Factor 4; 3; 61.666666666666664; 200; positive; default\n"+
	// "// FACTOR Name of Factor 5; 4; 20.0; 100; refinement; default\n"+
	// "// FACTOR Name of Factor 6; 0; 0.0; 100; refinement; default\n"+
	// "\n"+
	// "result = 0\n"+
	// "  +linearDistribution(228.33333333333334,%%Name of Factor 1%%/200)\n"+
	// "  +linearDistribution(128.33333333333331,%%Name of Factor 2%%/100)\n"+
	// "  +negativeLinearDistribution(61.666666666666664,%%Name of Factor 3%%/400)\n"+
	// "  +linearDistribution(61.666666666666664,%%Name of Factor 4%%/200)\n"+
	// "  +linearDistribution(20.0,%%Name of Factor 5%%/100)\n";
	//
	// String stdQIESL4 =
	// "// AGGREGATION (CONTRIBUTION POINTS)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 500\n"+
	// "// FACTOR Name of Factor 1; -1; 200.0; 200; positive; default\n"+
	// "// FACTOR Name of Factor 2; -1; 50.0; 100; refinement; default\n"+
	// "// FACTOR Name of Factor 3; -1; 0.0; 400; negative; default\n"+
	// "// FACTOR Name of Factor 4; -1; 0.0; 200; positive; default\n"+
	// "// FACTOR Name of Factor 5; -1; 150.0; 100; refinement; default\n"+
	// "// FACTOR Name of Factor 6; -1; 100.0; 100; refinement; default\n"+
	// "\n"+
	// "result = 0\n"+
	// "  +linearDistribution(200.0,%%Name of Factor 1%%/200)\n"+
	// "  +linearDistribution(50.0,%%Name of Factor 2%%/100)\n"+
	// "  +linearDistribution(150.0,%%Name of Factor 5%%/100)\n"+
	// "  +linearDistribution(100.0,%%Name of Factor 6%%/100)\n";
	//	
	// private Vector<InfluencingOrRefiningElement>
	// createVectorWithInfluencingAndRefiningFactors() {
	// InfluencingOrRefiningElement f1 = new InfluencingOrRefiningElement(
	// "Name of Factor 1", 1, 200, 200,
	// InfluencingOrRefiningElement.Type.positive, "default");
	//		
	// InfluencingOrRefiningElement f2 = new InfluencingOrRefiningElement(
	// "Name of Factor 2", 2, 50, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// InfluencingOrRefiningElement f3 = new InfluencingOrRefiningElement(
	// "Name of Factor 3", 3, 0, 400,
	// InfluencingOrRefiningElement.Type.negative, "default");
	//		
	// InfluencingOrRefiningElement f4 = new InfluencingOrRefiningElement(
	// "Name of Factor 4", 3, 0, 200,
	// InfluencingOrRefiningElement.Type.positive, "default");
	//		
	// InfluencingOrRefiningElement f5 = new InfluencingOrRefiningElement(
	// "Name of Factor 5", 4, 150, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// InfluencingOrRefiningElement f6 = new InfluencingOrRefiningElement(
	// "Name of Factor 6", 0, 100, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// Vector<InfluencingOrRefiningElement> factors = new
	// Vector<InfluencingOrRefiningElement>();
	//		
	// factors.addElement(f1);
	// factors.addElement(f2);
	// factors.addElement(f3);
	// factors.addElement(f4);
	// factors.addElement(f5);
	// factors.addElement(f6);
	// return factors;
	// }
	//	
	// private Vector<InfluencingOrRefiningElement>
	// createExpectedVectorWithInfluencingAndRefiningFactors() {
	// InfluencingOrRefiningElement f1 = new InfluencingOrRefiningElement(
	// "Name of Factor 1", 1, 228.33333333333334, 200,
	// InfluencingOrRefiningElement.Type.positive, "default");
	//		
	// InfluencingOrRefiningElement f2 = new InfluencingOrRefiningElement(
	// "Name of Factor 2", 2, 128.33333333333331, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// InfluencingOrRefiningElement f3 = new InfluencingOrRefiningElement(
	// "Name of Factor 3", 3, 61.666666666666664, 400,
	// InfluencingOrRefiningElement.Type.negative, "default");
	//		
	// InfluencingOrRefiningElement f4 = new InfluencingOrRefiningElement(
	// "Name of Factor 4", 3, 61.666666666666664, 200,
	// InfluencingOrRefiningElement.Type.positive, "default");
	//		
	// InfluencingOrRefiningElement f5 = new InfluencingOrRefiningElement(
	// "Name of Factor 5", 4, 20.0, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// InfluencingOrRefiningElement f6 = new InfluencingOrRefiningElement(
	// "Name of Factor 6", 0, 0.0, 100,
	// InfluencingOrRefiningElement.Type.refinement, "default");
	//		
	// Vector<InfluencingOrRefiningElement> factors = new
	// Vector<InfluencingOrRefiningElement>();
	//		
	// factors.addElement(f1);
	// factors.addElement(f2);
	// factors.addElement(f3);
	// factors.addElement(f4);
	// factors.addElement(f5);
	// factors.addElement(f6);
	// return factors;
	// }
	//		
	// @Test
	// public void testGenerateRankingBasedFactorAggregation() {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// int maxPoints = 500;
	// Vector<InfluencingOrRefiningElement> factors =
	// createVectorWithInfluencingAndRefiningFactors();
	//		
	// String actual = gen.generateRankingBasedFactorAggregation(maxPoints,
	// factors);
	//		
	// org.junit.Assert.assertEquals(this.stdQIESL3, actual);
	// }
	//
	// @Test
	// public void testGeneratePointBasedFactorAggregation() {
	//
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// int maxPoints = 500;
	// Vector<InfluencingOrRefiningElement> factors =
	// createVectorWithInfluencingAndRefiningFactors();
	//		
	// String actual = gen.generatePointBasedFactorAggregation(maxPoints,
	// factors);
	//		
	// org.junit.Assert.assertEquals(this.stdQIESL4, actual);
	// }
	//
	// @Test
	// public void testGenerateEvalFunctionNumber() {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// int maxPoints = 500;
	// String measureNameForEval = "#Documented Functions";
	// String measureNameForNorm = "#Functions";
	// Scale scale = Scale.NUMBER;
	// Range range = Range.NA;
	// MappingFunction mappingFunction = MappingFunction.LinearIncreasing;
	// double lowerBound = 0.4;
	// double upperBound = 0.9;
	//		
	// String actual = gen.generateEvalFunction(maxPoints, measureNameForEval,
	// measureNameForNorm, scale,
	// range, mappingFunction, lowerBound, upperBound);
	//		
	// String expected =
	// "// EVALUATION FUNCTION (#Documented Functions)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 500\n"+
	// "// Measure used for evaluation = #Documented Functions\n"+
	// "// Scale = NUMBER\n"+
	// "// Range = NA\n"+
	// "// Measure used for normalization = #Functions\n"+
	// "// Mapping function = LinearIncreasing\n"+
	// "// Lower bound = 0.4\n"+
	// "// Upper bound = 0.9\n"+
	// "\n"+
	// "result = linearDistribution(%%#Documented Functions%%/%%#Functions%%,0.4,0,0.9,500)";
	//		
	// org.junit.Assert.assertEquals(expected, actual);
	// }
	//
	// @Test
	// public void testGenerateEvalFunctionFinding() {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// int maxPoints = 300;
	// String measureNameForEval = "Undocumented Functions";
	// String measureNameForNorm = "Java/LOC";
	// Scale scale = Scale.FINDINGS;
	// Range range = Range.METHOD;
	// MappingFunction mappingFunction = MappingFunction.LinearDecreasing;
	// double lowerBound = 0.2;
	// double upperBound = 0.5;
	//		
	// String actual = gen.generateEvalFunction(maxPoints, measureNameForEval,
	// measureNameForNorm, scale,
	// range, mappingFunction, lowerBound, upperBound);
	//		
	// String expected =
	// "// EVALUATION FUNCTION (Undocumented Functions)\n"+
	// "// GENERATED QIESL – DO NOT MODIFY\n"+
	// "// BASED ON MAXPOINT VALUE = 300\n"+
	// "// Measure used for evaluation = Undocumented Functions\n"+
	// "// Scale = FINDINGS\n"+
	// "// Range = METHOD\n"+
	// "// Measure used for normalization = Java/LOC\n"+
	// "// Mapping function = LinearDecreasing\n"+
	// "// Lower bound = 0.2\n"+
	// "// Upper bound = 0.5\n"+
	// "\n"+
	// "result = linearDistribution(\n"+
	// "extent(methodRange(%%Undocumented Functions%%))\n"+
	// "/%%Java/LOC%%,0.2,300,0.5,0)";
	//		
	// org.junit.Assert.assertEquals(expected, actual);
	// }
	//	
	// @Test
	// public void testIsGeneratedQIESL() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// boolean actual1 = gen.isGeneratedQIESL(stdQIESL1);
	// boolean actual2 = gen.isGeneratedQIESL(stdQIESL2);
	// boolean actual3 = gen.isGeneratedQIESL(stdQIESL3);
	// boolean actual4 = gen.isGeneratedQIESL(stdQIESL4);
	// boolean actual5 = gen.isGeneratedQIESL("// NOT generated");
	// boolean actual6 = gen.isGeneratedQIESL("");
	//	
	// org.junit.Assert.assertEquals(true, actual1);
	// org.junit.Assert.assertEquals(true, actual2);
	// org.junit.Assert.assertEquals(true, actual3);
	// org.junit.Assert.assertEquals(true, actual4);
	// org.junit.Assert.assertEquals(false, actual5);
	// org.junit.Assert.assertEquals(false, actual6);
	// }
	//	
	// @Test
	// public void testIsGeneratedEvaluationFunction() throws
	// ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// boolean actual1 = gen.isGeneratedEvaluationFunction(stdQIESL1);
	// boolean actual2 = gen.isGeneratedEvaluationFunction(stdQIESL2);
	// boolean actual3 = gen.isGeneratedEvaluationFunction(stdQIESL3);
	// boolean actual4 = gen.isGeneratedEvaluationFunction(stdQIESL4);
	// boolean actual5 = gen.isGeneratedEvaluationFunction("// NOT generated");
	// boolean actual6 = gen.isGeneratedEvaluationFunction("");
	//	
	// org.junit.Assert.assertEquals(true, actual1);
	// org.junit.Assert.assertEquals(true, actual2);
	// org.junit.Assert.assertEquals(false, actual3);
	// org.junit.Assert.assertEquals(false, actual4);
	// org.junit.Assert.assertEquals(false, actual5);
	// org.junit.Assert.assertEquals(false, actual6);
	// }
	//	
	// @Test
	// public void testIsGeneratedFactorAggregation() throws ParseQIESLException
	// {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// boolean actual1 = gen.isGeneratedFactorAggregation(stdQIESL1);
	// boolean actual2 = gen.isGeneratedFactorAggregation(stdQIESL2);
	// boolean actual3 = gen.isGeneratedFactorAggregation(stdQIESL3);
	// boolean actual4 = gen.isGeneratedFactorAggregation(stdQIESL4);
	// boolean actual5 = gen.isGeneratedFactorAggregation("// NOT generated");
	// boolean actual6 = gen.isGeneratedFactorAggregation("");
	//		
	// org.junit.Assert.assertEquals(false, actual1);
	// org.junit.Assert.assertEquals(false, actual2);
	// org.junit.Assert.assertEquals(true, actual3);
	// org.junit.Assert.assertEquals(true, actual4);
	// org.junit.Assert.assertEquals(false, actual5);
	// org.junit.Assert.assertEquals(false, actual6);
	// }
	//	
	//	
	// @Test
	// public void testGetMaxPoints() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// int actual1 = gen.getMaxPoints(stdQIESL1);
	// int actual2 = gen.getMaxPoints(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(500, actual1);
	// org.junit.Assert.assertEquals(300, actual2);
	// }
	//	
	// @Test
	// public void testGetLowerBound() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// double actual1 = gen.getLowerBound(stdQIESL1);
	// double actual2 = gen.getLowerBound(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(0.4, actual1, 0.0001);
	// org.junit.Assert.assertEquals(0.2, actual2, 0.0001);
	// }
	//	
	// @Test
	// public void testGetUpperBound() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// double actual1 = gen.getUpperBound(stdQIESL1);
	// double actual2 = gen.getUpperBound(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(0.9, actual1, 0.0001);
	// org.junit.Assert.assertEquals(0.5, actual2, 0.0001);
	// }
	//	
	// @Test
	// public void testGetEvaluationMeasure() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// String actual1 = gen.getEvaluationMeasure(stdQIESL1);
	// String actual2 = gen.getEvaluationMeasure(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals("#Documented Functions", actual1);
	// org.junit.Assert.assertEquals("Undocumented Functions", actual2);
	// }
	//	
	// @Test
	// public void testGetNormalizationMeasure() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// String actual1 = gen.getNormalizationMeasure(stdQIESL1);
	// String actual2 = gen.getNormalizationMeasure(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals("#Functions", actual1);
	// org.junit.Assert.assertEquals("Java/LOC", actual2);
	// }
	//	
	// @Test
	// public void testGetScale() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// Scale actual1 = gen.getScale(stdQIESL1);
	// Scale actual2 = gen.getScale(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(Scale.NUMBER, actual1);
	// org.junit.Assert.assertEquals(Scale.FINDINGS, actual2);
	// }
	//	
	// @Test
	// public void testGetRange() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// Range actual1 = gen.getRange(stdQIESL1);
	// Range actual2 = gen.getRange(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(Range.NA, actual1);
	// org.junit.Assert.assertEquals(Range.METHOD, actual2);
	// }
	//	
	// @Test
	// public void testGetMappingFunction() throws ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// MappingFunction actual1 = gen.getMappingFunction(stdQIESL1);
	// MappingFunction actual2 = gen.getMappingFunction(stdQIESL2);
	//	
	// org.junit.Assert.assertEquals(MappingFunction.LinearIncreasing, actual1);
	// org.junit.Assert.assertEquals(MappingFunction.LinearDecreasing, actual2);
	// }
	//	
	// @Test
	// public void testGetInfluencingOrRefiningFactors() throws
	// ParseQIESLException {
	//		
	// EvaluationGenerator gen = new QIESLGeneratorImp();
	//		
	// Vector<InfluencingOrRefiningElement> actual =
	// gen.getInfluencingOrRefiningFactors(stdQIESL3);
	//		
	// Vector<InfluencingOrRefiningElement> excpeted =
	// this.createExpectedVectorWithInfluencingAndRefiningFactors();
	//		
	// org.junit.Assert.assertEquals(excpeted, actual);
	// }

}
