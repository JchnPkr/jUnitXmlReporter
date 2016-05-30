package jUnitXmlReporter.exceptionClasses;

public class UnregisteredObserverException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public UnregisteredObserverException(String message) 
	{
		super(message);
	}
}
