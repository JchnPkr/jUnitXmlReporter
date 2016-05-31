package usageDemoClasses;

import jUnitXmlReporter.jUnitElementClasses.FailureTestCase;
import jUnitXmlReporter.observerClasses.AbstractReportSubjectImpl;
import jUnitXmlReporter.observerClasses.CheckedClass;

public class CapsChecker extends AbstractReportSubjectImpl
{
	public CapsChecker(CheckedClass testSubject, String testName)
	{
		super(testSubject, testName);
	}
	
	@Override
	protected void test()
	{
		for(String testedString: ((SomeClassToCheck)  testSubject).getSomeStringArr())
		{
			String lowerCaseString = testedString.toLowerCase();
			
			if(!lowerCaseString.equals(testedString))
			{
				String msg = "someString '"+testedString
				+"' contains capital letters";
	
				addTestCase(new FailureTestCase(testName, msg));
			}
		}
	}
}
