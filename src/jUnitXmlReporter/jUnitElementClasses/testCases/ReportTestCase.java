package jUnitXmlReporter.jUnitElementClasses.testCases;

import org.jdom2.Element;

/**
 * Interface of the testcases used in {@link jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite}
 */
public interface ReportTestCase
{
	String getName();

	void setName(String name);

	String getMessage();

	void setMessage(String message);

	String getType();

	Element getJUnitTestCaseXmlElement();
}