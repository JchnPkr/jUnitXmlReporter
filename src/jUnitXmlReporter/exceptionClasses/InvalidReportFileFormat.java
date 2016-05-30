package jUnitXmlReporter.exceptionClasses;

public class InvalidReportFileFormat extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public InvalidReportFileFormat(String message) 
	{
		super(message);
	}
}
