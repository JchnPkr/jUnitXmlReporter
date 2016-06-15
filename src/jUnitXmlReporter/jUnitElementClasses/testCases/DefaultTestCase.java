package jUnitXmlReporter.jUnitElementClasses.testCases;

import org.jdom2.Element;

/**
 * Abstract implementation of default testcase properties
 */
public abstract class DefaultTestCase implements ReportTestCase
{
	protected String name;
	protected String message;
	
	public DefaultTestCase(String name, String message)
	{
		this.name = name;
		this.message = message;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	@Override
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	@Override
	public String getType()
	{
		return this.getClass().getSimpleName();
	}
	
	@Override
	public abstract Element getJUnitTestCaseXmlElement();
}
