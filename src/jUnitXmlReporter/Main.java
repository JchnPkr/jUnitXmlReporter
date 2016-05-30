package jUnitXmlReporter;

import java.security.spec.InvalidParameterSpecException;

import jUnitXmlReporter.observerClasses.ReportObserverImpl;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;
import usageDemoClasses.CheckedDemoClass;
import usageDemoClasses.CheckingDemoClass;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println("---- Starting test run of JUnitXmlRepoter programm ----");

		ReportObserver reportObserver = new ReportObserverImpl();
		CheckingDemoClass testingClass = new CheckingDemoClass();	
		testingClass.registerReportObserver(reportObserver);
		
		CheckedDemoClass testSubject1 = new CheckedDemoClass("firstSubjectToTest", new String[]{"Aibohphobia", "openSource"});
		CheckedDemoClass testSubject2 = new CheckedDemoClass("secondSubjectToTest", new String[]{"foof", "observe"});

		testingClass.testContainsCapital(testSubject1);
		testingClass.testIsNotPalindrome(testSubject1);
		testingClass.testContainsCapital(testSubject2);
		testingClass.testIsNotPalindrome(testSubject2);
		
		try
		{
			JUnitXmlReportCreator reporter = new JUnitXmlReportCreator("./testReportDir", "report.xml", "testApplication");
			reporter.createJUnitReport(reportObserver.getTestSuiteMap());
		}
		catch (InvalidParameterSpecException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("---- Finished test run of JUnitXmlRepoter programm ----");
	}
}
