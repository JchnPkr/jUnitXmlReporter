package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public abstract class AbstractTestCase implements XmlTestCase
{
	protected String name;
	protected String message;
	
	public AbstractTestCase(String name, String message)
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
