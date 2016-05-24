package jUnitXmlReporter;

import java.util.ArrayList;
import java.util.List;

public class TestSuite
{
	private String name;
	private List<TestCase> testCaseList;
	
	public TestSuite(String name)
	{
		this.setName(name);
		this.testCaseList = new ArrayList<>();
	}
	
	public void addTestCase(TestCase tc)
	{
		testCaseList.add(tc);
	}

	public List<TestCase> getTestCaseList()
	{
		return testCaseList;
	}

	public void setTestCaseList(List<TestCase> testCaseList)
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
