package jUnitXmlReporter.jUnitElementClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;

public class ReportTestSuiteImpl implements ReportTestSuite
{
	private static final Logger logger = LogManager.getLogger(ReportTestSuiteImpl.class.getName());
	
	private String name;
	private HashMap<String, XmlTestCase> testCaseMap;
	
	public ReportTestSuiteImpl(String name)
	{
		this.setName(name);
		this.testCaseMap = new HashMap<>();
	}
	
	@Override
	public void addTestCase(XmlTestCase tc)
	{
		if(containsTestCase(tc))
		{
			logger.debug("\n\t Update of testsuite: " + name + ", \n"
						+ "\t appending message: '" + tc.getMessage() + "' \n"
							+ "\t to testcase: '"+ tc.getName() +"' \n"
							+ "\t of type: '" + tc.getType() + "' \n");
			
			String oldMessage = testCaseMap.get(tc.getName()).getMessage();
			String newMessage = oldMessage + ", \n" + tc.getMessage();
	
			testCaseMap.get(tc.getName()).setMessage(newMessage);
		}
		else
		{
			logger.debug("\n\t Update of testsuite: " + name + ", \n"
						+ "\t adding testcase: '" + tc.getName() + "' \n"
						+ "\t of type: '" + tc.getType() +"' \n"
						+ "\t with message: '" +tc.getMessage()+ "' \n");
			
			testCaseMap.put(tc.getName(), tc);
		}
	}

	private boolean containsTestCase(XmlTestCase tc)
	{	
		if(testCaseMap.containsKey(tc.getName()))
			return	isSameTestCaseType(tc);
				
		return  false;
	}
	
	private boolean isSameTestCaseType(XmlTestCase tc)
	{
		String existingTcType = testCaseMap.get(tc.getName())
				.getType();
		
		return existingTcType.equals(tc.getType());
	}
	
	@Override
	public List<XmlTestCase> getTestCaseList()
	{
		return new ArrayList<XmlTestCase>(testCaseMap.values());
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void addAllTestCases(List<XmlTestCase> testCaseList)
	{
		for(XmlTestCase tc: testCaseList)
		{
			addTestCase(tc);
		}
	}
	
	@Override
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
		for(XmlTestCase testCase: testCaseMap.values())
		{
			Element jUnitTestCaseXmlTag = testCase.getJUnitTestCaseXmlElement();
			suite.addContent(jUnitTestCaseXmlTag);
		}
	}
}
