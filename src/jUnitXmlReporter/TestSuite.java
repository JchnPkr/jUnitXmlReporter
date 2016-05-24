package jUnitXmlReporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestSuite
{
	private String name;
	private HashMap<String, TestCase> testCaseMap;
	
	public TestSuite(String name)
	{
		this.setName(name);
		this.testCaseMap = new HashMap<>();
	}
	
	public void addTestCase(TestCase tc)
	{
		if(containsTestCase(tc))
		{
			String oldMessage = testCaseMap.get(tc.getName()).getMessage();
			String newMessage = oldMessage + "\n" + tc.getMessage();
			testCaseMap.get(tc.getName()).setMessage(newMessage);
		}
		else
			testCaseMap.put(tc.getName(), tc);
	}

	private boolean containsTestCase(TestCase tc)
	{
		return testCaseMap.containsKey(tc.getName()) &&
				testCaseMap.get(tc.getName()).getType().equals(tc.getType());
	}
	
	public List<TestCase> getTestCaseList()
	{
		return new ArrayList<TestCase>(testCaseMap.values());
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
