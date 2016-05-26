package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public abstract class AbstractTestCase
{
	protected String name;
	protected String message;
	
	public AbstractTestCase(String name, String message)
	{
		this.name = name;
		this.message = message;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String getType()
	{
		return this.getClass().getSimpleName();
	}
	
	public abstract Element getJUnitTestCaseXmlElement();
}
