package jUnitXmlReporter;

import jUnitXmlReporter.observerClasses.ReportObserverImpl;
import jUnitXmlReporter.exceptionClasses.InvalidReportFileFormat;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;
import usageDemoClasses.CheckedDemoClass;
import usageDemoClasses.ReportSubjectImpl;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("---- Starting test run of JUnitXmlRepoter programm ----");

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
		
		System.out.println("---- Finished test run of JUnitXmlRepoter programm ----");
	}

	private static void runTests(ReportObserver reportObserver, CheckedDemoClass testSubject)
	{
		ReportSubjectImpl testingClass = new ReportSubjectImpl(testSubject);	
		testingClass.registerReportObserver(reportObserver);
		testingClass.testIsNotPalindrome();
	}
}
