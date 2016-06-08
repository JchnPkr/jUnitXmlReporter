package jUnitXmlReporter.exceptionClasses;

import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;

/**
 * Used in {@link JUnitXmlReportCreator}
 */
public class InvalidReportFileFormat extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public InvalidReportFileFormat(String message) 
	{
		super(message);
	}
}
