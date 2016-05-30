package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;



public class ReportObserverImpl implements ReportObserver
{
	private static final Logger logger = LogManager.getLogger(ReportObserverImpl.class.getName());
	
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
			logger.debug("ReportObserver update: Adding testcases to TestSuite " + ts.getName());

			testSuiteMap.get(ts.getName()).addAllTestCases(ts.getTestCaseList());
		}
		else
		{
			logger.debug("ReportObserver update: Adding ReportTestSuite " + ts.getName());

			this.testSuiteMap.put(ts.getName(), ts);
		}   
	}
}