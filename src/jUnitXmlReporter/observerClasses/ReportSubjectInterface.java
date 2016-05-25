package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.jUnitElementClasses.TestCase;
import jUnitXmlReporter.jUnitElementClasses.TestSuite;

public interface ReportSubjectInterface 
{
    public void registerReportObserver(ReportObserverInterface observer);
    public void removeReportObserver(ReportObserverInterface observer);
    public void notifyObserver(TestSuite testSuite);
    public void notifyObserver(String testSuiteName, TestCase testCase);
}
