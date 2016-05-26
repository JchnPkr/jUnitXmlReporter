package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;

public interface ReportSubjectInterface 
{
    public void registerReportObserver(ReportObserver observer);
    public void removeReportObserver(ReportObserver observer);
    public void notifyObserver(ReportTestSuite testSuite);
}
