package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;

public interface ReportSubject 
{
    public void registerReportObserver(ReportObserver observer);
    public void notifyObserver(ReportTestSuite testSuite);
}
