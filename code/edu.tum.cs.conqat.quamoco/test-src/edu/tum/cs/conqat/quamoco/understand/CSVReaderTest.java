package edu.tum.cs.conqat.quamoco.understand;


import org.junit.Test;

import junit.framework.TestCase;

public class CSVReaderTest extends TestCase {

	public void test1() {
		String[] r = CSVReader.split("\"hallo,\"\"klaus,lochmann\"\",123\"");
		assertEquals("hallo", r[0]);
		assertEquals("klaus,lochmann", r[1]);
		assertEquals("123", r[2]);
		assertEquals(3, r.length);
	}
	
	public void test2() {
		String[] r = CSVReader.split("\"hallo,klausl,123\"");
		assertEquals("hallo", r[0]);
		assertEquals("klausl", r[1]);
		assertEquals("123", r[2]);
		assertEquals(3, r.length);
	}
	
	public void test3() {
		String[] r = CSVReader.split("hallo,klausl,123");
		assertEquals("hallo", r[0]);
		assertEquals("klausl", r[1]);
		assertEquals("123", r[2]);
		assertEquals(3, r.length);
	}
	
	public void test4() {
		String[] r = CSVReader.split("\"hallo,klausl\",1,123");
		assertEquals("hallo,klausl", r[0]);
		assertEquals("1", r[1]);
		assertEquals("123", r[2]);
		assertEquals(3, r.length);
	}

}
