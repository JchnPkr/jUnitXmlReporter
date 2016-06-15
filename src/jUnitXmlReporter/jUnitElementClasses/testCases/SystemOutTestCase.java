package jUnitXmlReporter.jUnitElementClasses.testCases;

import org.jdom2.Element;

/**
 * Default testcase implementation
 */
public class SystemOutTestCase extends DefaultTestCase
{
	public SystemOutTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element testCase = new Element("testcase").setAttribute("name", this.name);
		Element sysOut = new Element("system-out").addContent(message);
		testCase.addContent(sysOut);
		
		return testCase;	
	}
}
