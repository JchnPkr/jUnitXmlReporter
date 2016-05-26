package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public class ErrorTestCase extends AbstractTestCase
{
	public ErrorTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element testCase = new Element("testcase").setAttribute("name", this.name);
		Element err = new Element("error").addContent(message);
		testCase.addContent(err);
				
		return testCase;
	}
}
