package usageDemoClasses;

import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.observerClasses.ReportSubject;

public class CheckingDemoClass implements ReportSubject
{
	private ReportObserver reportObserver;
	
	@Override
	public void registerReportObserver(ReportObserver observer)
	{
		this.reportObserver = observer;
	}

	@Override
	public void notifyObserver(ReportTestSuite testSuite)
	{
		reportObserver.upDate(testSuite);
	}
	
	public void testContainsCapital(CheckedDemoClass testSubject)
	{
		ReportTestSuite suite = new ReportTestSuite(testSubject.getIdString());
		
		for(String testedString: testSubject.getSomeStringArr())
		{
			String allLower = testedString.toLowerCase();
			
			if(!allLower.equals(testedString))
			{
				String msg = "someString '"+testedString
							+"' contains capital letter(s)";
				
				suite.addTestCase(new FailureTestCase("containsCapitalTest", msg));
			}
		}
		
		notifyObserver(suite);
	}
	
	public void testIsNotPalindrome(CheckedDemoClass testSubject)
	{
		ReportTestSuite suite = new ReportTestSuite(testSubject.getIdString());

		for(String testedString: testSubject.getSomeStringArr())
		{
			String reversedString = reverseString(testedString);
			
			if(!reversedString.equals(testedString.toLowerCase()))
			{
				String msg = "someString '"+testedString
				+"' is not a palindrome";
	
				suite.addTestCase(new FailureTestCase("palindromeTest", msg));
			}
		}
		
		notifyObserver(suite);
	}
	
	private String reverseString(String s)
	{
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(s.toLowerCase());
		strBuild=strBuild.reverse(); 
		
		return strBuild.toString();
	}
}
