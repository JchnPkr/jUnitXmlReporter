package jUnitXmlReporter.reportClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReportProperties
{
	private String propPath;
	
	public ReportProperties(String propPath)
	{
		super();
		this.propPath = propPath;
	}

	public Properties getReportProperties()
	{
		Properties reportProps = new Properties();
		InputStream input = null;

		try 
		{
			input = new FileInputStream(propPath);
			reportProps.load(input);

		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
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
					e.printStackTrace();
				}
			}
		}
		
		return reportProps;
	}
}
