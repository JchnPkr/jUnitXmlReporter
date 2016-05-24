package jUnitXmlReporter;

public class TestCase
{
	public enum Type
	{
		ERROR, FAILURE, SKIPPED, SYSTEMOUT
	}
	
	private String name;
	private Type type;
	private String message;
	
	public TestCase(String name, Type type, String message)
	{
		this.name = name;
		this.type = type;
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

	public Type getType()
	{
		return type;
	}

	public void setType(Type type)
	{
		this.type = type;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
