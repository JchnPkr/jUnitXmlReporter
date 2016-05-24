package jUnitXmlReporter;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.http.NameValuePair;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;



public class JUnitXmlElementCreator
{
	
	
	/**
	 * creates jdom2 element error for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @return XML element error for JUnit report
	 */
	public static Element getError(String message)
	{
		return getError(message,false);
	}
	
	/**
	 * creates jdom2 element error for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @param silent boolean value if message should be printed to System.out
	 * @return XML element error for JUnit report
	 */
	public static Element getError(String message,boolean silent)
	{
		
		Element err = new Element("error");
		err.addContent(message);
		if(!silent)
		{
			System.err.println("error occured in testcase: " + message);
		}
		return err;
	}
	
	
	/**
	 * creates jdom2 element failure for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @return XML element failure for JUnit report
	 */
	public static Element getFailure(String message)
	{
		return getFailure(message,false);
	}
	
	/**
	 * creates jdom2 element failure for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @param silent boolean value if message should be printed to System.out
	 * @return XML element failure for JUnit report
	 */
	public static Element getFailure(String message, boolean silent)
	{
		Element fail = new Element("failure");
		
		fail.addContent(message);
		if(!silent)
		{
			System.out.println("testcase failed: \r\n" + message);
		}
		return fail;
	}
	
	/**
	 * creates jdom2 element system-out for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @return XML element system-out for JUnit report
	 */
	public static Element getSysOut(String message)
	{
		Element sysOut = new Element("system-out");
		sysOut.addContent(message);
		return sysOut;
	}
	
	
	/**
	 * creates jdom2 element testsuite for JUnit XML report
	 * 
	 * @param testcases testcases to add in test suite
	 * @param attributes attributes for testsuite
	 * @return Element testsuite for JUnit report
	 */
	public static Element getTestSuite(List<Element> testcases, List<NameValuePair> attributes)
	{
		Element suite = new Element("testsuite");
		if(attributes != null)
		{
			for(NameValuePair attr:attributes)
			{
				suite.setAttribute(attr.getName(), attr.getValue());
			}
		}
		
		
		for(Element testcase: testcases)
		{
			suite.addContent(testcase);
		}
		
		// calculate sum of error etc.
		suite.setAttribute("errors", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("error"))).size()));
		suite.setAttribute("failures", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("failure"))).size()));
		suite.setAttribute("tests", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("testcase"))).size()));
		suite.setAttribute("skipped", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("skipped"))).size()));
	
		
		
		return suite;
	}
	
	
	/**
	 * checks test result
	 * 
	 * @param testcase testcase element of JUnit XML report
	 * @return true if test is passed, false if test is fail or error
	 */
	public static boolean isPassed(Element testcase)
	{
		boolean result = true;
		int nErr = IteratorUtils.toList(testcase.getDescendants(new ElementFilter("error"))).size();
		int nFail = IteratorUtils.toList(testcase.getDescendants(new ElementFilter("failure"))).size();
		int nSkipped = IteratorUtils.toList(testcase.getDescendants(new ElementFilter("skipped"))).size();
		if((nErr + nFail + nSkipped) > 0)
			result = false;
		return result;
	}
	
	/**
	 * creates jdom2 element skipped for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @return XML element skipped for JUnit report
	 */
	public static Element getSkipped(String message)
	{
		return getSkipped(message,false);
	}
	
	/**
	 * creates jdom2 element skipped for element testcase in JUnit XML report
	 * 
	 * @param message message which is used as content of XML element
	 * @param silent boolean value if message should be printed to System.out
	 * @return XML element skipped for JUnit report
	 */
	public static Element getSkipped(String message, boolean silent)
	{
		Element skipped = new Element("skipped");
		skipped.addContent(message);
		if(!silent)
		{
			System.out.println("teststep skipped: \r\n" + message);
		}
		return skipped;
	}
	
	
}
