package jUnitXmlReporter;

import java.util.ArrayList;

public class TestSuite
{
	private String name;
	private ArrayList<TestCase> testCaseList;
	
	public TestSuite(String name)
	{
		this.setName(name);
		this.testCaseList = new ArrayList<>();
	}
	
	public void addTestCase(TestCase tc)
	{
		testCaseList.add(tc);
	}

	public ArrayList<TestCase> getTestCaseList()
	{
		return testCaseList;
	}

	public void setTestCaseList(ArrayList<TestCase> testCaseList)
	{
		this.testCaseList = testCaseList;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
