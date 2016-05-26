package jUnitTests;

import static org.junit.Assert.*;

import org.jdom2.Element;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.TestCase;

public class TestCaseClassTest
{
	private TestCase tc;
	
	@Before
	public void setUp() throws Exception
	{
		tc = new TestCase("tcName", "tcMessage")
		{		
			@Override
			public Element getJUnitTestCaseXmlElement()
			{
				// TODO Auto-generated method stub
				return null;
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
}
