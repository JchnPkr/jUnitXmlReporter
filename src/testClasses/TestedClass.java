package testClasses;

public class TestedClass
{
	private String idString;
	private String someString;
	
	public TestedClass(String idString, String someString)
	{
		this.setIdString(idString);
		this.setSomeString(someString);
	}

	public String getSomeString()
	{
		return someString;
	}

	public void setSomeString(String someString)
	{
		this.someString = someString;
	}

	public String getIdString()
	{
		return idString;
	}

	public void setIdString(String idString)
	{
		this.idString = idString;
	}
}
