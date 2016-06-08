package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;

public interface ReportObserver
{
	HashMap<String, ReportTestSuite> getTestSuiteMap();

	/**
	 * @param ts testsuite to add or update it's content
	 */
	void upDate(ReportTestSuite ts);
}