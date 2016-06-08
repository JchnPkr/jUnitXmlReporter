package jUnitXmlReporter.observerClasses;

import jUnitXmlReporter.exceptionClasses.UnregisteredObserverException;

public interface ReportSubject 
{
    public void registerReportObserver(ReportObserver observer);
    
    /**
     * This method should call {@link ReportObserver#upDate(jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite)}
     * 
     * @throws UnregisteredObserverException
     */
    public void notifyObserver() throws UnregisteredObserverException;
}
