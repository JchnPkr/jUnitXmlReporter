package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public class SystemOutTestCase extends TestCase
{
	public SystemOutTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element sysOut = new Element("system-out");
		sysOut.addContent(message);
	
		return sysOut;
	}
}
