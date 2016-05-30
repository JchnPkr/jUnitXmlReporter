package jUnitXmlReporter;

import jUnitXmlReporter.observerClasses.ReportObserverImpl;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jUnitXmlReporter.exceptionClasses.InvalidReportFileFormat;
import jUnitXmlReporter.observerClasses.ReportObserver;
import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;
import jUnitXmlReporter.reportClasses.ReportProperties;
import usageDemoClasses.CapsChecker;
import usageDemoClasses.SomeClassToCheck;
import usageDemoClasses.PalindromeChecker;

public class Main
{
	private static final Logger logger = LogManager.getLogger();
	
	public static void main(String[] args)
	{
		logger.trace("\n\t ---- Starting test run of JUnitXmlRepoter programm ----\n");

		ReportObserver reportObserver = new ReportObserverImpl();
		
		SomeClassToCheck testSubject1 = new SomeClassToCheck("firstSubjectToTest", new String[]{"Aibohphobia", "openSource"});
		runTests(reportObserver, testSubject1);
		
		SomeClassToCheck testSubject2 = new SomeClassToCheck("secondSubjectToTest", new String[]{"subject", "observer"});
		runTests(reportObserver, testSubject2);
		
		Properties reportProps = new ReportProperties("./src/main/resources/report.properties").getReportProperties();		
		writeJUnitXmlReport(reportObserver, reportProps);
		
		logger.trace("\n\t ---- Finished test run of JUnitXmlRepoter programm ----\n");
	}

	private static void runTests(ReportObserver reportObserver, SomeClassToCheck testSubject)
	{
		PalindromeChecker palindromeCheck = new PalindromeChecker(testSubject, "isNotPalindrome");	
		palindromeCheck.registerReportObserver(reportObserver);
		palindromeCheck.runTest();
		
		CapsChecker capitalLettersCheck = new CapsChecker(testSubject, "containsCapitalLetters");	
		capitalLettersCheck.registerReportObserver(reportObserver);
		capitalLettersCheck.runTest();
	}
	
	private static void writeJUnitXmlReport(ReportObserver reportObserver, Properties reportProps)
	{
		try
		{
			JUnitXmlReportCreator reporter = new JUnitXmlReportCreator(reportProps);
			reporter.createJUnitReport(reportObserver.getTestSuiteMap());
		}
		catch (InvalidReportFileFormat e)
		{
			e.printStackTrace();
		}
	}
}
