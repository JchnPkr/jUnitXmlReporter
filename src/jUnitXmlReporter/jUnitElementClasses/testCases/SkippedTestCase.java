package jUnitXmlReporter.jUnitElementClasses.testCases;

import org.jdom2.Element;

public class SkippedTestCase extends AbstractTestCase
{
	public SkippedTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element testCase = new Element("testcase").setAttribute("name", this.name);
		Element skipped = new Element("skipped").addContent(message);
		testCase.addContent(skipped);
		
		return testCase;
	}
}
