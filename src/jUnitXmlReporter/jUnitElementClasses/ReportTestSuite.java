package jUnitXmlReporter.jUnitElementClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;

public class ReportTestSuite
{
	private String name;
	private HashMap<String, AbstractTestCase> testCaseMap;
	
	public ReportTestSuite(String name)
	{
		this.setName(name);
		this.testCaseMap = new HashMap<>();
	}
	
	public void addTestCase(AbstractTestCase tc)
	{
		if(containsTestCase(tc))
		{
			String oldMessage = testCaseMap.get(tc.getName()).getMessage();
			String newMessage = oldMessage + "\n" + tc.getMessage();
			testCaseMap.get(tc.getName()).setMessage(newMessage);
		}
		else
			testCaseMap.put(tc.getName(), tc);
	}

	private boolean containsTestCase(AbstractTestCase tc)
	{	
		if(testCaseMap.containsKey(tc.getName()))
			return	isSameTestCaseType(tc);
				
		return  false;
	}
	
	private boolean isSameTestCaseType(AbstractTestCase tc)
	{
		String existingTcType = testCaseMap.get(tc.getName())
				.getType();
		
		return existingTcType.equals(tc.getType());
	}
	
	public List<AbstractTestCase> getTestCaseList()
	{
		return new ArrayList<AbstractTestCase>(testCaseMap.values());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void addAllTestCases(List<AbstractTestCase> testCaseList)
	{
		for(AbstractTestCase tc: testCaseList)
		{
			addTestCase(tc);
		}
	}
	
	public Element getJUnitTestSuiteXmlElement()
	{
		Element suite = new Element("testsuite");	
		
		addTestCasesToSuiteXmlElement(suite);
		
		suite.setAttribute("name", this.name);	
		suite.setAttribute("errors", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("error"))).size()));
		suite.setAttribute("failures", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("failure"))).size()));
		suite.setAttribute("tests", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("testcase"))).size()));
		suite.setAttribute("skipped", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("skipped"))).size()));
		
		return suite;
	}
	
	private void addTestCasesToSuiteXmlElement(Element suite)
	{
		for(AbstractTestCase testCase: testCaseMap.values())
		{
			Element jUnitTestCaseXmlTag = testCase.getJUnitTestCaseXmlElement();
			suite.addContent(jUnitTestCaseXmlTag);
		}
	}
}
