package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.jUnitElementClasses.SystemOutTestCase;

public abstract class AbstractReportSubjectImpl implements ReportSubject
{
	private ReportObserver reportObserver;
	protected ReportTestSuite reportTestSuite;
	protected CheckedClass testSubject;
	protected String testName;

	public AbstractReportSubjectImpl(CheckedClass testSubject, String testName)
	{
		this.testSubject = testSubject;
		reportTestSuite = new ReportTestSuite(testSubject.getId());
		this.testName = testName;
	}

	@Override
	public void registerReportObserver(ReportObserver observer)
	{
		reportObserver = observer;
	}

	protected abstract void test();
	
	public void runTest()
	{
		test();
		safeNotify();
	}

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
			throw new UnregisteredObserverException("Notification failed!");
	}
	
	private void isPassed()
	{
		if (reportTestSuite.getTestCaseList().size() == 0)
			reportTestSuite.addTestCase(new SystemOutTestCase(testName, "passed successsful"));
	}
}
