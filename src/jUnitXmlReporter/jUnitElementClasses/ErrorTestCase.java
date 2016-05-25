package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public class ErrorTestCase extends TestCase
{
	public ErrorTestCase(String name, String message)
	{
		super(name, message);
	}

	@Override
	public Element getJUnitTestCaseXmlElement()
	{
		Element err = new Element("error");
		err.addContent(message);
		
		return err;
	}
}
