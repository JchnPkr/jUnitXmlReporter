package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public class FailureTestCase extends TestCase
{
	public FailureTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element testCase = new Element("testcase").setAttribute("name", this.name);
		Element fail = new Element("failure").addContent(message);
		testCase.addContent(fail);
			
		return testCase;
	}
}
