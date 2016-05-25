package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.jUnitElementClasses.TestSuite;

public interface ReportSubjectInterface 
{
    public void registerReportObserver(ReportObserver observer);
    public void removeReportObserver(ReportObserver observer);
    public void notifyObserver(TestSuite testSuite);
}
