package jUnitXmlReporter.observerClasses;

import java.util.HashMap;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;

public interface ReportObserver
{

	HashMap<String, ReportTestSuite> getTestSuiteMap();

	void upDate(ReportTestSuite ts);

}