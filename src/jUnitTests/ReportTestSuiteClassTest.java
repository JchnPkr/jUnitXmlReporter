package jUnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;
import org.jdom2.output.XMLOutputter;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.jUnitElementClasses.ErrorTestCase;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;
import jUnitXmlReporter.jUnitElementClasses.ReportTestSuiteImpl;
import jUnitXmlReporter.jUnitElementClasses.XmlTestCase;

public class ReportTestSuiteClassTest
{
    private ReportTestSuite ts;
    
    @Before
    public void setUp() throws Exception
    {
	ts = new ReportTestSuiteImpl("reportTestSuiteName");
    }

    @Test
    public void testReportTestSuite()
    {
	assertNotNull(ts);
    }

    @Test
    public void testAddTestCase()
    {
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage1"));
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage2"));
	ts.addTestCase(new ErrorTestCase("differentAddedErrTcName", "differentAddedTcMessage"));
	
	ErrorTestCase expectedDoubleMessageTc = new ErrorTestCase("addedErrTcName", "addedTcMessage1"+"\n"+"addedTcMessage2");
	ErrorTestCase expectedSingleMessageTc  = new ErrorTestCase("differentAddedErrTcName", "differentAddedTcMessage");

	List<XmlTestCase> actualTcList = ts.getTestCaseList();
	
	assertEquals(expectedDoubleMessageTc.getName(), actualTcList.get(0).getName());
	assertEquals(expectedDoubleMessageTc.getMessage(), actualTcList.get(0).getMessage());
	assertEquals(expectedSingleMessageTc.getName(), actualTcList.get(1).getName());
	assertEquals(expectedSingleMessageTc.getMessage(), actualTcList.get(1).getMessage());
    }
    
    @Test
    public void testGetTestCaseList()
    {
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage1"));
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage2"));
	ts.addTestCase(new ErrorTestCase("differentAddedErrTcName", "differentAddedTcMessage"));
	
	List<XmlTestCase> actualTcList = ts.getTestCaseList();
	
	assertNotNull(actualTcList);
	assertEquals(2, actualTcList.size());
    }
    
    @Test
    public void testGetName()
    {
	assertEquals("reportTestSuiteName", ts.getName());
    }

    @Test
    public void testSetName()
    {
	ts.setName("newName");
	assertEquals("newName", ts.getName());
    }

    @Test
    public void testAddAllTestCases()
    {
	List<XmlTestCase> tcList = new ArrayList<>();
	tcList.add(new ErrorTestCase("addedErrTcName", "addedTcMessage1"));
	tcList.add(new ErrorTestCase("addedErrTcName", "addedTcMessage2"));
	tcList.add(new ErrorTestCase("differentAddedErrTcName", "differentAddedTcMessage"));
	
	ts.addAllTestCases(tcList);
	
	assertEquals(2, ts.getTestCaseList().size());
    }

    @Test
    public void testGetJUnitTestSuiteXmlElement()
    {
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage1"));
	ts.addTestCase(new ErrorTestCase("addedErrTcName", "addedTcMessage2"));
	ts.addTestCase(new ErrorTestCase("differentAddedErrTcName", "differentAddedTcMessage"));
	
	String expectedTsXmlString = createExpectedTestSuiteXmlString();
	
	XMLOutputter outp = new XMLOutputter();
	String actualTsXmlString = outp.outputString(ts.getJUnitTestSuiteXmlElement());
	
	assertEquals(expectedTsXmlString, actualTsXmlString);
    }
    
    private String createExpectedTestSuiteXmlString()
    {
	Element suite = new Element("testsuite");	
	
	Element tcElement1 = new Element("testcase").setAttribute("name", "addedErrTcName");
	Element err1 = new Element("error").addContent("addedTcMessage1"+"\n"+"addedTcMessage2");
	tcElement1.addContent(err1);
	
	Element tcElement2 = new Element("testcase").setAttribute("name", "differentAddedErrTcName");
	Element err2 = new Element("error").addContent("differentAddedTcMessage");
	tcElement2.addContent(err2);
	
	suite.addContent(tcElement1);
	suite.addContent(tcElement2);
	
	suite.setAttribute("name", "reportTestSuiteName");	
	suite.setAttribute("errors", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("error"))).size()));
	suite.setAttribute("failures", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("failure"))).size()));
	suite.setAttribute("tests", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("testcase"))).size()));
	suite.setAttribute("skipped", String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("skipped"))).size()));
	
	
	XMLOutputter outp = new XMLOutputter();
	return outp.outputString(suite);
    }
}
