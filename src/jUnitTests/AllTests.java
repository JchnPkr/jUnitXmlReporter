package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ErrorTestCaseClassTest.class, 
	TestCaseClassTest.class })

public class AllTests
{

}
