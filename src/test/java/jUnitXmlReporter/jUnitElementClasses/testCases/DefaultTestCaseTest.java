package jUnitXmlReporter.jUnitElementClasses.testCases;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.DefaultTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;

public class DefaultTestCaseTest
{
	private ReportTestCase tc;
	
	@Before
	public void setUp() throws Exception
	{
		tc = new DefaultTestCase("tcName", "tcMessage")
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
