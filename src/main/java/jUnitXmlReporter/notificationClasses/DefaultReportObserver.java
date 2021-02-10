package jUnitXmlReporter.notificationClasses;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;



/**
 * Basic {@link ReportObserver} implementation
 */
public class DefaultReportObserver implements ReportObserver
{
	private static final Logger logger = LogManager.getLogger(DefaultReportObserver.class.getName());
	
	private HashMap<String, ReportTestSuite> testSuiteMap;

	public DefaultReportObserver()
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
		if(ts != null)
		{
			upDateTestSuiteMap(ts);  
		}
	}

	private void upDateTestSuiteMap(ReportTestSuite ts)
	{
		if(testSuiteMap.containsKey(ts.getName()))
		{
			appendTestCases(ts);
		}
		else
		{
			addTestsuite(ts);
		}
	}

	private void appendTestCases(ReportTestSuite ts)
	{
		logger.debug("\n\t ReportObserver update: Adding testcases to TestSuite " 
					+ ts.getName());

		testSuiteMap.get(ts.getName()).addAllTestCases(ts.getTestCaseList());
	}

	private void addTestsuite(ReportTestSuite ts)
	{
		logger.debug("\n\t ReportObserver update: Adding ReportTestSuite " 
					+ ts.getName() + "\n");

		this.testSuiteMap.put(ts.getName(), ts);
	}
}