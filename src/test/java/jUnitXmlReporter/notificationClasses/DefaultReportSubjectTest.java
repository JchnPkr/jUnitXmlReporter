package jUnitXmlReporter.notificationClasses;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.testCases.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;
import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;
import jUnitXmlReporter.notificationClasses.DefaultReportObserver;
import jUnitXmlReporter.notificationClasses.DefaultReportSubject;
import jUnitXmlReporter.notificationClasses.ReportObserver;
import jUnitXmlReporter.notificationClasses.ReportSubject;
import jUnitXmlReporter.notificationClasses.ReportedClass;



public class DefaultReportSubjectTest
{
	private ReportSubject reportSubject;
	
	@Before
	public void setUp() throws Exception
	{
		ReportedClass classToCheck = Mockito.mock(ReportedClass.class);
		Mockito.when(classToCheck.getId()).thenReturn("testId");
		
		reportSubject = new DefaultReportSubject(classToCheck, "testName")
				{
					@Override
					protected void test()
					{
						addTestCase(new FailureTestCase(testName, "testMethodInvoked"));
					}
				};
		
	}

	@Test
	public void testAbstractReportSubjectImpl()
	{
		assertNotNull(reportSubject);
	}

	@Test
	public void testRegisterReportObserver() throws UnregisteredObserverException
	{
		ReportObserver reportObserver = new DefaultReportObserver();
		
		reportSubject.registerReportObserver(reportObserver);
		reportSubject.notifyObserver();
	}

	@Test
	public void testRunTest()
	{	
		ReportObserver reportObserver = new DefaultReportObserver();
		reportSubject.registerReportObserver(reportObserver);
		
		((DefaultReportSubject) reportSubject).runTest();
		
		ReportTestSuite ts = reportObserver.getTestSuiteMap().get("testId");
	
		assertTrue(isTestCaseFound(ts.getTestCaseList()));
	}

	private boolean isTestCaseFound(List<ReportTestCase> tcList)
	{
		for(ReportTestCase tc: tcList)
			if(tc.getMessage().equals("testMethodInvoked"))
				return true;
		
		return false;
	}
	
	@Test(expected = UnregisteredObserverException.class)
	public void testNotifyObserverException() throws UnregisteredObserverException
	{
		((DefaultReportSubject) reportSubject).notifyObserver();
	}
}
