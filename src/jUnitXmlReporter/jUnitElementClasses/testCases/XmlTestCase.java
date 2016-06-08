package jUnitXmlReporter.jUnitElementClasses.testCases;

import org.jdom2.Element;

/**
 * Interface of the testcases used in {@link ReportTestSuite}
 */
public interface XmlTestCase
{
	String getName();

	void setName(String name);

	String getMessage();

	void setMessage(String message);

	String getType();

	Element getJUnitTestCaseXmlElement();
}