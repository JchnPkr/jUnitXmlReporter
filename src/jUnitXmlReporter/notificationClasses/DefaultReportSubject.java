package jUnitXmlReporter.notificationClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.testCases.SystemOutTestCase;
import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;
import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;
import jUnitXmlReporter.jUnitElementClasses.testSuite.DefaultReportTestSuite;

/**
 * Basic implementation to be extended by classes running tests
 */
public abstract class DefaultReportSubject implements ReportSubject
{
	private ReportObserver reportObserver;
	protected ReportTestSuite reportTestSuite;
	protected ReportedClass testSubject;
	protected String testName;

	public DefaultReportSubject(ReportedClass testSubject, String testName)
	{
		this.testSubject = testSubject;
		reportTestSuite = new DefaultReportTestSuite(testSubject.getId());
		this.testName = testName;
	}

	@Override
	public void registerReportObserver(ReportObserver observer)
	{
		reportObserver = observer;
	}

	/**
	 * This method should be overridden with the actual test 
	 * that should be performed by the actual implemementation
	 * when {@link #runTest()} is called.
	 * 
	 * If the test fails use {@link #addTestCase(ReportTestCase)} 
	 * to add an appropriate testcase to {@link #reportTestSuite}
	 */
	protected abstract void test();
	
	/**
	 * This method should be called to run the actual test.
	 */
	public void runTest()
	{
		test();
		safeNotify();
	}

	/**
	 * Convenience method handling exception from {@link #notify()}
	 */
	private void safeNotify()
	{
		try
		{
			notifyObserver();
		}
		catch (UnregisteredObserverException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void notifyObserver() throws UnregisteredObserverException
	{
		if (reportObserver != null)
		{
			isPassed();
			reportObserver.upDate(reportTestSuite);
		}
		else
			throw new UnregisteredObserverException("Notification failed! No Observer registered!");
	}
	
	/**
	 * Convenience method called in {@link #notifyObserver()}
	 */
	private void isPassed()
	{
		if (reportTestSuite.getTestCaseList().size() == 0)
			reportTestSuite.addTestCase(new SystemOutTestCase(testName, "passed successful"));
	}
	
	/**
	 * This method should be used in {@link #test()} if a test fails.
	 * 
	 * @param testCase testCase to add to {@link #reportTestSuite}
	 */
	protected void addTestCase(ReportTestCase testCase)
	{
		reportTestSuite.addTestCase(testCase);
	}
}
