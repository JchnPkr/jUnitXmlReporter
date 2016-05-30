package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;

public interface ReportSubject 
{
    public void registerReportObserver(ReportObserver observer);
    public void notifyObserver() throws UnregisteredObserverException;
}
