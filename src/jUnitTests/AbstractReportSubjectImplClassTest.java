package jUnitTests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.testCases.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.XmlTestCase;
import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;
import jUnitXmlReporter.observerClasses.AbstractReportSubjectImpl;
import jUnitXmlReporter.observerClasses.CheckedClass;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportObserverImpl;
import jUnitXmlReporter.observerClasses.ReportSubject;



public class AbstractReportSubjectImplClassTest
{
	private ReportSubject reportSubject;
	
	@Before
	public void setUp() throws Exception
	{
		CheckedClass classToCheck = Mockito.mock(CheckedClass.class);
		Mockito.when(classToCheck.getId()).thenReturn("testId");
		
		reportSubject = new AbstractReportSubjectImpl(classToCheck, "testName")
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
		ReportObserver reportObserver = new ReportObserverImpl();
		
		reportSubject.registerReportObserver(reportObserver);
		reportSubject.notifyObserver();
	}

	@Test
	public void testRunTest()
	{	
		ReportObserver reportObserver = new ReportObserverImpl();
		reportSubject.registerReportObserver(reportObserver);
		
		((AbstractReportSubjectImpl) reportSubject).runTest();
		
		ReportTestSuite ts = reportObserver.getTestSuiteMap().get("testId");
	
		assertTrue(isTestCaseFound(ts.getTestCaseList()));
	}

	private boolean isTestCaseFound(List<XmlTestCase> tcList)
	{
		for(XmlTestCase tc: tcList)
			if(tc.getMessage().equals("testMethodInvoked"))
				return true;
		
		return false;
	}
	
	@Test(expected = UnregisteredObserverException.class)
	public void testNotifyObserverException() throws UnregisteredObserverException
	{
		((AbstractReportSubjectImpl) reportSubject).notifyObserver();
	}
}
