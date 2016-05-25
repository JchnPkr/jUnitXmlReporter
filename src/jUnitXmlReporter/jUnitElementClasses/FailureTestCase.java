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
		Element fail = new Element("failure");
		fail.addContent(message);
			
		return fail;
	}
}
