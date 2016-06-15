package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.SystemOutTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;

public class SystemOutTestCaseClassTest
{
    private ReportTestCase sysOutTc;
	
	@Before
	public void setUp() throws Exception
	{
		sysOutTc = new SystemOutTestCase("sysOutTcName", "sysOutTcMessage");
	}

	@Test
	public void testGetJUnitTestCaseXmlElement()
	{
		Element testElement = new Element("testcase").setAttribute("name", "sysOutTcName");
		Element sysOut = new Element("system-out").addContent("sysOutTcMessage");
		testElement.addContent(sysOut);
		
		XMLOutputter outp = new XMLOutputter();
		String expected = outp.outputString(testElement);
		String actual = outp.outputString(sysOutTc.getJUnitTestCaseXmlElement());
	
		assertEquals(expected, actual);
	}

	@Test
	public void testSystemOutTestCase()
	{
		assertNotNull(sysOutTc);
	}
	
	@Test
	public void testGetType()
	{
		assertEquals("SystemOutTestCase", sysOutTc.getType());
	}
}