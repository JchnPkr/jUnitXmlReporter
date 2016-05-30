package usageDemoClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportSubject;

public class AnotherReportSubjectImpl implements ReportSubject
{
	private ReportObserver reportObserver;
	private ReportTestSuite reportTestSuite;
	private CheckedDemoClass testSubject;
	
	public AnotherReportSubjectImpl(CheckedDemoClass testSubject)
	{
		this.testSubject = testSubject;
		reportTestSuite = new ReportTestSuite(testSubject.getIdString());
	}
	
	@Override
	public void registerReportObserver(ReportObserver observer)
	{
		reportObserver = observer;
	}

	@Override
	public void notifyObserver() throws UnregisteredObserverException
	{
		if(reportObserver != null)
			reportObserver.upDate(reportTestSuite);
		else 
			throw new UnregisteredObserverException("Notification failed!");
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
	
	public void testContainsCapitals()
	{
		for(String testedString: testSubject.getSomeStringArr())
		{
			String lowerCaseString = testedString.toLowerCase();
			
			if(!lowerCaseString.equals(testedString))
			{
				String msg = "someString '"+testedString
				+"' contains capital letters";
	
				reportTestSuite.addTestCase(new FailureTestCase("containsCapitalsTest", msg));
			}
		}
		
		safeNotify();
	}
}
