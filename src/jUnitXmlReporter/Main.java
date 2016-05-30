package jUnitXmlReporter;

import jUnitXmlReporter.observerClasses.ReportObserverImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jUnitXmlReporter.exceptionClasses.InvalidReportFileFormat;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;
import usageDemoClasses.AnotherReportSubjectImpl;
import usageDemoClasses.CheckedDemoClass;
import usageDemoClasses.ReportSubjectImpl;

public class Main
{
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args)
	{
		logger.trace("\n\t ---- Starting test run of JUnitXmlRepoter programm ----\n");

		ReportObserver reportObserver = new ReportObserverImpl();
		
		CheckedDemoClass testSubject1 = new CheckedDemoClass("firstSubjectToTest", new String[]{"Aibohphobia", "openSource"});
		runTests(reportObserver, testSubject1);
		
		CheckedDemoClass testSubject2 = new CheckedDemoClass("secondSubjectToTest", new String[]{"subject", "observer"});
		runTests(reportObserver, testSubject2);
		
		try
		{
			JUnitXmlReportCreator reporter = new JUnitXmlReportCreator("./testReportDir", "report.xml", "testApplication");
			reporter.createJUnitReport(reportObserver.getTestSuiteMap());
		}
		catch (InvalidReportFileFormat e)
		{
			e.printStackTrace();
		}
		
		logger.trace("\n\t ---- Finished test run of JUnitXmlRepoter programm ----\n");
	}

	private static void runTests(ReportObserver reportObserver, CheckedDemoClass testSubject)
	{
		ReportSubjectImpl palindromeTest = new ReportSubjectImpl(testSubject);	
		palindromeTest.registerReportObserver(reportObserver);
		palindromeTest.testIsNotPalindrome();
		
		AnotherReportSubjectImpl capitalLettersTest = new AnotherReportSubjectImpl(testSubject);	
		capitalLettersTest.registerReportObserver(reportObserver);
		capitalLettersTest.testContainsCapitals();;
	}
}
