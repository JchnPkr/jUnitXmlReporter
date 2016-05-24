package jUnitXmlReporter;

import java.util.HashMap;



public class ReportObserverImpl implements ReportObserverInterface
{
	private HashMap<String, TestSuite> testSuiteMap;
	
	public ReportObserverImpl()
	{
	}
	
	private void addTestsuite(TestSuite ts)
	{
		this.testSuiteMap.put(ts.getName(), ts);
	}
	
	private void addTestCase(String testSuiteName, TestCase tc) throws Exception
	{
		if(testSuiteMap.containsKey(testSuiteName))
			testSuiteMap.get(testSuiteName).addTestCase(tc);
		else
		{
			String msg = "Failed to add testcase! Unknown testsuite name: '"
					+ testSuiteName+ "'"; 
			throw new Exception(msg);
		}
	}

	public HashMap<String, TestSuite> getTestSuiteMap()
	{
	    return this.testSuiteMap;
	}
	
	@Override
	public void upDate(String testSuiteName, TestCase tc)
	{
	    try
	    {
		this.addTestCase(testSuiteName, tc);
	    }
	    catch (Exception e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }    
	}

	@Override
	public void upDate(TestSuite ts)
	{
	    this.addTestsuite(ts);	    
	}
}
