package jUnitTests;

import static org.junit.Assert.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.reportClasses.ReportProperties;

public class ReportPropertiesClassTest
{
    private ReportProperties reportProperties;

    @Before
    public void setUp() throws Exception
    {
	reportProperties = new ReportProperties(null);
    }

    @Test
    public void testReportPropertiesNullInit()
    {
	assertNotNull(reportProperties);
    }

    @Test
    public void testGetFilePathNullInit()
    {
	assertNotNull(reportProperties.getFilePath());
    }

    @Test
    public void testGetFileNameNullInit()
    {
	assertNotNull(reportProperties.getFileName());
    }

    @Test
    public void testGetAppNameNullInit()
    {
	assertNotNull(reportProperties.getAppName());
    }

    @Test
    public void testReportPropertiesNoFile()
    {
	reportProperties = new ReportProperties("noSuchFile");
	assertNotNull(reportProperties);
	assertNotNull(reportProperties.getFilePath());
	assertNotNull(reportProperties.getFileName());
	assertNotNull(reportProperties.getAppName());
    }

    @Test
    public void testReportPropertiesInvalidFile()
    {
	createInvalidPropertiesFile();
	
	reportProperties = new ReportProperties("./src/jUnitTests/invalidTestConfig.properties");
	assertNotNull(reportProperties);
	assertNotNull(reportProperties.getFilePath());
	assertNotNull(reportProperties.getFileName());
	assertNotNull(reportProperties.getAppName());
    }

    private void createInvalidPropertiesFile()
    {
	Properties prop = new Properties();
	OutputStream output = null;

	try
	{
	    output = new FileOutputStream("./src/jUnitTests/invalidTestConfig.properties");

	    prop.setProperty("someInvalidKey", "someInvalidValue");

	    prop.store(output, "created by jUnit test");
	}
	catch (IOException io)
	{
	    io.printStackTrace();
	}
	finally
	{
	    if (output != null)
	    {
		try
		{
		    output.close();
		}
		catch (IOException e)
		{
		    e.printStackTrace();
		}
	    }
	}
    }
}
