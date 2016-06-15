package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.ErrorTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;

public class ErrorTestCaseClassTest
{
	private ReportTestCase errTc;
	
	@Before
	public void setUp() throws Exception
	{
		errTc = new ErrorTestCase("errTcName", "errTcMessage");
	}

	@Test
	public void testGetJUnitTestCaseXmlElement()
	{
		Element testElement = new Element("testcase").setAttribute("name", "errTcName");
		Element err = new Element("error").addContent("errTcMessage");
		testElement.addContent(err);
		
		XMLOutputter outp = new XMLOutputter();
		String expected = outp.outputString(testElement);
		String actual = outp.outputString(errTc.getJUnitTestCaseXmlElement());
	
		assertEquals(expected, actual);
	}

	@Test
	public void testErrorTestCase()
	{
		assertNotNull(errTc);
	}
	
	@Test
	public void testGetType()
	{
		assertEquals("ErrorTestCase", errTc.getType());
	}
}
