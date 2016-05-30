package usageDemoClasses;

import jUnitXmlReporter.observerClasses.CheckedClass;

public class SomeClassToCheck implements CheckedClass
{
	private String id;
	private String[] someStringArr;
	
	public SomeClassToCheck(String idString, String[] someStringArr)
	{
		this.setIdString(idString);
		this.setSomeStringArr(someStringArr);
	}

	public String getId()
	{
		return id;
	}

	public void setIdString(String idString)
	{
		this.id = idString;
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
