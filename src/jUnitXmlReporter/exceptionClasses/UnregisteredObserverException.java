package jUnitXmlReporter.exceptionClasses;

import jUnitXmlReporter.notificationClasses.ReportSubject;

/**
 * Used in {@link ReportSubject} and it's implementations
 */
public class UnregisteredObserverException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public UnregisteredObserverException(String message) 
	{
		super(message);
	}
}
