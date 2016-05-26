package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;



public class ReportObserver
{
	private HashMap<String, ReportTestSuite> testSuiteMap;

	public ReportObserver()
	{
		this.testSuiteMap = new HashMap<>();
	}
	
	public HashMap<String, ReportTestSuite> getTestSuiteMap()
	{
	    return this.testSuiteMap;
	}

	public void upDate(ReportTestSuite ts)
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