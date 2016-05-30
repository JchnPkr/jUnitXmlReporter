package usageDemoClasses;

import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.observerClasses.AbstractReportSubjectImpl;
import jUnitXmlReporter.observerClasses.CheckedClass;

public class PalindromeChecker extends AbstractReportSubjectImpl
{
	public PalindromeChecker(CheckedClass testSubject, String testName)
	{
		super(testSubject, testName);
	}
	
	@Override
	protected void test()
	{
		for(String testedString: ((SomeClassToCheck) testSubject).getSomeStringArr())
		{
			String reversedString = reverseString(testedString);
			
			if(!reversedString.equals(testedString.toLowerCase()))
			{
				String msg = "someString '"+testedString
				+"' is not a palindrome";
	
				reportTestSuite.addTestCase(new FailureTestCase(testName, msg));
			}
		}
	}
	
	private String reverseString(String s)
	{
		StringBuilder strBuild = new StringBuilder();
		strBuild.append(s.toLowerCase());
		strBuild=strBuild.reverse(); 
		
		return strBuild.toString();
	}
}
