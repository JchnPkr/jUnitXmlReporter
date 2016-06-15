package jUnitTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.testCases.ErrorTestCase;
import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;
import jUnitXmlReporter.notificationClasses.DefaultReportObserver;
import jUnitXmlReporter.notificationClasses.ReportObserver;
import jUnitXmlReporter.jUnitElementClasses.testSuite.DefaultReportTestSuite;

public class ReportObserverImplClassTest
{
	private ReportObserver reportObserver;

	@Before
	public void setUp() throws Exception
	{
		this.reportObserver = new DefaultReportObserver();
	}

	@Test
	public void testReportObserverImpl()
	{
		assertNotNull(reportObserver);
		assertNotNull(reportObserver.getTestSuiteMap());
	}

	@Test
	public void testGetTestSuiteMap()
	{
		assertNotNull(reportObserver.getTestSuiteMap());
	}

	@Test
	public void testTestSuiteUpDate()
	{
		ReportTestSuite ts = new DefaultReportTestSuite("reportTestSuite");
		ts.addTestCase(new ErrorTestCase("errorTestCaseName", "errorTestCaseMessage"));

		this.reportObserver.upDate(ts);
		
		HashMap<String, ReportTestSuite> testSuiteMap = reportObserver.getTestSuiteMap();
		ReportTestSuite expectedTs = testSuiteMap.get("reportTestSuite");

		assertEquals(ts, expectedTs);
	}
	
	@Test
	public void testTestCaseAppendUpDate()
	{
		ReportTestSuite ts = new DefaultReportTestSuite("reportTestSuite");
		ts.addTestCase(new ErrorTestCase("errorTestCaseName", "errorTestCaseMessage"));

		this.reportObserver.upDate(ts);
		
		ts.addTestCase(new ErrorTestCase("anotherErrorTestCaseName", "anotherErrorTestCaseMessage"));
		
		this.reportObserver.upDate(ts);
		
		HashMap<String, ReportTestSuite> testSuiteMap = reportObserver.getTestSuiteMap();		
		ReportTestSuite expectedTs = testSuiteMap.get("reportTestSuite");
		
		assertEquals(2, expectedTs.getTestCaseList().size());
	}
}
