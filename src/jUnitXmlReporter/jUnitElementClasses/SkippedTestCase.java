package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public class SkippedTestCase extends TestCase
{
	public SkippedTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element skipped = new Element("skipped");
		skipped.addContent(message);
			
		return skipped;
	}
}
