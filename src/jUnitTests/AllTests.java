package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
	{ 
	    AbstractTestCaseClassTest.class,
	    ErrorTestCaseClassTest.class, 
	    FailureTestCaseClassTest.class,
	    SkippedTestCaseClassTest.class,
	    SystemOutTestCaseClassTest.class
	 })

public class AllTests
{

}
