package jUnitXmlReporter.jUnitElementClasses.testSuite;

import java.util.List;

import org.jdom2.Element;

import jUnitXmlReporter.jUnitElementClasses.testCases.XmlTestCase;

public interface ReportTestSuite
{

	void addTestCase(XmlTestCase tc);

	List<XmlTestCase> getTestCaseList();

	String getName();

	void setName(String name);

	void addAllTestCases(List<XmlTestCase> testCaseList);

	Element getJUnitTestSuiteXmlElement();

}