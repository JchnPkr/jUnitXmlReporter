package usageDemoClasses;

public class CheckedDemoClass
{
	private String idString;
	private String[] someStringArr;
	
	public CheckedDemoClass(String idString, String[] someStringArr)
	{
		this.setIdString(idString);
		this.setSomeStringArr(someStringArr);
	}

	public String getIdString()
	{
		return idString;
	}

	public void setIdString(String idString)
	{
		this.idString = idString;
	}

	public String[] getSomeStringArr()
	{
		return someStringArr;
	}

	public void setSomeStringArr(String[] someStringArr)
	{
		this.someStringArr = someStringArr;
	}
}
