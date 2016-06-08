package jUnitXmlReporter.jUnitElementClasses.testSuite;

import java.util.List;

import org.jdom2.Element;

import jUnitXmlReporter.jUnitElementClasses.testCases.XmlTestCase;
import jUnitXmlReporter.observerClasses.ReportObserver;

/**
 * Interface used in {@link ReportObserver}
 */
public interface ReportTestSuite
{

	void addTestCase(XmlTestCase tc);

	List<XmlTestCase> getTestCaseList();

	String getName();

	void setName(String name);

	void addAllTestCases(List<XmlTestCase> testCaseList);

	Element getJUnitTestSuiteXmlElement();

}