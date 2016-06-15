package jUnitXmlReporter.jUnitElementClasses.testSuite;

import java.util.List;

import org.jdom2.Element;

import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;
import jUnitXmlReporter.notificationClasses.ReportObserver;

/**
 * Interface used in {@link ReportObserver}
 */
public interface ReportTestSuite
{

	void addTestCase(ReportTestCase tc);

	List<ReportTestCase> getTestCaseList();

	String getName();

	void setName(String name);

	void addAllTestCases(List<ReportTestCase> testCaseList);

	Element getJUnitTestSuiteXmlElement();

}