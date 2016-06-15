package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
		DefaultReportSubjectClassTest.class,
	    DefaultTestCaseClassTest.class,
	    ErrorTestCaseClassTest.class, 
	    FailureTestCaseClassTest.class,
	    JUnitXmlReportCreatorClassTest.class,
	    ReportObserverImplClassTest.class,
	    ReportPropertiesClassTest.class,
	    ReportTestSuiteImplClassTest.class,
	    SkippedTestCaseClassTest.class,
	    SystemOutTestCaseClassTest.class
	 })

public class AllTests
{

}
