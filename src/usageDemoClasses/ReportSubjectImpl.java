package usageDemoClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;
import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportSubject;

public class ReportSubjectImpl implements ReportSubject
{
	private ReportObserver reportObserver;
	private ReportTestSuite reportTestSuite;
	private CheckedDemoClass testSubject;
	
	public ReportSubjectImpl(CheckedDemoClass testSubject)
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
	
	public void testIsNotPalindrome()
	{
		for(String testedString: testSubject.getSomeStringArr())
		{
			String reversedString = reverseString(testedString);
			
			if(!reversedString.equals(testedString.toLowerCase()))
			{
				String msg = "someString '"+testedString
				+"' is not a palindrome";
	
				reportTestSuite.addTestCase(new FailureTestCase("palindromeTest", msg));
			}
		}
		
		safeNotify();
	}
	
	private String reverseString(String s)
	{
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(s.toLowerCase());
		strBuild=strBuild.reverse(); 
		
		return strBuild.toString();
	}
}
