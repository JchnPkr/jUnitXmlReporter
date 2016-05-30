package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;



public class ReportObserverImpl implements ReportObserver
{
	private HashMap<String, ReportTestSuite> testSuiteMap;

	public ReportObserverImpl()
	{
		this.testSuiteMap = new HashMap<>();
	}
	
	@Override
	public HashMap<String, ReportTestSuite> getTestSuiteMap()
	{
	    return this.testSuiteMap;
	}

	@Override
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