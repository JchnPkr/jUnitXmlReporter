package testClasses;

import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.TestSuite;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportSubjectInterface;

public class TestingClass implements ReportSubjectInterface
{
	private ReportObserver reportObserver;
	
	@Override
	public void registerReportObserver(ReportObserver observer)
	{
		this.reportObserver = observer;
	}

	@Override
	public void removeReportObserver(ReportObserver observer)
	{
		this.reportObserver = null;
	}

	@Override
	public void notifyObserver(TestSuite testSuite)
	{
		reportObserver.upDate(testSuite);
	}
	
	public void testContainsCapital(TestedClass testSubject)
	{
		TestSuite suite = new TestSuite(testSubject.getIdString());
		
		String allLower = testSubject.getSomeString().toLowerCase();
		
		if(!allLower.equals(testSubject.getSomeString()))
		{
			String msg = "someString '"+testSubject.getSomeString()
						+"' contains capital letter(s)";
			
			suite.addTestCase(new FailureTestCase("containsCapitalTest", msg));
		}
		
		notifyObserver(suite);
	}
	
	public void testIsNotPalindrome(TestedClass testSubject)
	{
		TestSuite suite = new TestSuite(testSubject.getIdString());

		StringBuilder strBuild = new StringBuilder();
		strBuild.append(testSubject.getSomeString().toLowerCase());
		strBuild=strBuild.reverse(); 
		
		if(!strBuild.toString().equals(testSubject.getSomeString().toLowerCase()))
		{
			String msg = "someString '"+testSubject.getSomeString()
			+"' is not a palindrome";

			suite.addTestCase(new FailureTestCase("palindromeTest", msg));
		}

		notifyObserver(suite);
	}
}
