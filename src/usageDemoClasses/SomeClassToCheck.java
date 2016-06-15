package usageDemoClasses;

import jUnitXmlReporter.notificationClasses.ReportedClass;

/**
 * Demo implementation of a class under test
 */
public class SomeClassToCheck implements ReportedClass
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
