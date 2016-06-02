package jUnitXmlReporter.reportClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ReportProperties
{
	private static final Logger logger = LogManager.getLogger(ReportProperties.class.getName());
	
	private Properties reportProps;
	
	public ReportProperties(String propPath)
	{
		reportProps = setReportProperties(propPath);
	}

	private Properties setReportProperties(String propPath)
	{
		Properties props = new Properties();
		props.setProperty("filePath", "notSet");
		props.setProperty("fileName", "notSet");
		props.setProperty("appName", "notSet");

		InputStream input = null;

		try 
		{
			input = new FileInputStream(propPath);
			props.load(input);
		} 
		catch (IOException ex) 
		{
			logger.error(ex.getMessage());
		} 
		catch (NullPointerException ex) 
		{
			logger.error("\n\tCatched NullpointerException! Message: "
						+ ex.getMessage());
		} 
		finally 
		{
			if (input != null) 
			{
				try
				{
					input.close();
				} 
				catch (IOException e) 
				{
					logger.error(e.getMessage());
				}
			}
		}
		
		return props;
	}

	public String getFilePath()
	{
		return reportProps.getProperty("filePath");
	}

	public String getFileName()
	{
		return reportProps.getProperty("fileName");
	}

	public String getAppName()
	{
		return reportProps.getProperty("appName");
	}
	
	
}
