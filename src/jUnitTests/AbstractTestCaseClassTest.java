package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.AbstractTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.XmlTestCase;

public class AbstractTestCaseClassTest
{
	private XmlTestCase tc;
	
	@Before
	public void setUp() throws Exception
	{
		tc = new AbstractTestCase("tcName", "tcMessage")
		{		
			@Override
			public Element getJUnitTestCaseXmlElement()
			{
				return new Element("test");
			}
		};
	}

	@Test
	public void testTestCase()
	{
		assertNotNull(tc);
	}

	@Test
	public void testGetName()
	{
		assertEquals("tcName", tc.getName());
	}

	@Test
	public void testSetName()
	{
		tc.setName("newTcName");
		assertEquals("newTcName", tc.getName());
	}

	@Test
	public void testGetMessage()
	{
		assertEquals("tcMessage", tc.getMessage());
	}

	@Test
	public void testSetMessage()
	{
		tc.setMessage("newTcMessage");
		assertEquals("newTcMessage", tc.getMessage());
	}

	@Test
	public void testGetType()
	{
		assertEquals("", tc.getType());
	}
	
	@Test
	public void getJUnitTestCaseXmlElement()
	{
		assertNotNull(tc.getJUnitTestCaseXmlElement());
	}
}
