package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.SkippedTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.XmlTestCase;

public class SkippedTestCaseClassTest
{
    private XmlTestCase skipTc;
	
	@Before
	public void setUp() throws Exception
	{
		skipTc = new SkippedTestCase("skipTcName", "skipTcMessage");
	}

	@Test
	public void testGetJUnitTestCaseXmlElement()
	{
		Element testElement = new Element("testcase").setAttribute("name", "skipTcName");
		Element skip = new Element("skipped").addContent("skipTcMessage");
		testElement.addContent(skip);
		
		XMLOutputter outp = new XMLOutputter();
		String expected = outp.outputString(testElement);
		String actual = outp.outputString(skipTc.getJUnitTestCaseXmlElement());
	
		assertEquals(expected, actual);
	}

	@Test
	public void testSkippedTestCase()
	{
		assertNotNull(skipTc);
	}
	
	@Test
	public void testGetType()
	{
		assertEquals("SkippedTestCase", skipTc.getType());
	}
}