package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import jUnitXmlReporter.jUnitElementClasses.TestSuite;



public class ReportObserverImpl implements ReportObserverInterface
{
	private HashMap<String, TestSuite> testSuiteMap;

	public HashMap<String, TestSuite> getTestSuiteMap()
	{
	    return this.testSuiteMap;
	}

	@Override
	public void upDate(TestSuite ts)
	{
		if(testSuiteMap.containsKey(ts.getName()))
		{
			testSuiteMap.get(ts.getName()).addAllTestCases(ts.getTestCaseList());
		}
		else
		{
			this.testSuiteMap.put(ts.getName(), ts);
		}   
	}
}