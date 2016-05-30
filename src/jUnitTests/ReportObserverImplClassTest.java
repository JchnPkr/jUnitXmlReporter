package jUnitTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.ErrorTestCase;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportObserverImpl;

public class ReportObserverImplClassTest
{
	private ReportObserver reportObserver;

	@Before
	public void setUp() throws Exception
	{
		this.reportObserver = new ReportObserverImpl();
	}

	@Test
	public void testReportObserverImpl()
	{
		assertNotNull(reportObserver);
	}

	@Test
	public void testGetTestSuiteMap()
	{
		assertNotNull(reportObserver.getTestSuiteMap());
	}

	@Test
	public void testUpDate()
	{
		ReportTestSuite ts = new ReportTestSuite("reportTestSuite");
		ts.addTestCase(new ErrorTestCase("errorTestCaseName", "errorTestCaseMessage"));

		this.reportObserver.upDate(ts);
		
		HashMap<String, ReportTestSuite> testSuiteMap = reportObserver.getTestSuiteMap();
		ReportTestSuite expectedTs = testSuiteMap.get("reportTestSuite");

		assertEquals(ts, expectedTs);
	}
}
