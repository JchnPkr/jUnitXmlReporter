package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.XmlTestCase;

public class FailureTestCaseClassTest
{
    private XmlTestCase failTc;
	
	@Before
	public void setUp() throws Exception
	{
		failTc = new FailureTestCase("failTcName", "failTcMessage");
	}

	@Test
	public void testGetJUnitTestCaseXmlElement()
	{
		Element testElement = new Element("testcase").setAttribute("name", "failTcName");
		Element fail = new Element("failure").addContent("failTcMessage");
		testElement.addContent(fail);
		
		XMLOutputter outp = new XMLOutputter();
		String expected = outp.outputString(testElement);
		String actual = outp.outputString(failTc.getJUnitTestCaseXmlElement());
	
		assertEquals(expected, actual);
	}

	@Test
	public void testFailureTestCase()
	{
		assertNotNull(failTc);
	}
	
	@Test
	public void testGetType()
	{
		assertEquals("FailureTestCase", failTc.getType());
	}
}