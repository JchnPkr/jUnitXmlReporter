package jUnitTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.reportClasses.ReportProperties;

public class ReportPropertiesClassTest
{
	private static final String testPropertiesFilePath = "./src/jUnitTests/invalidTestConfig.properties";

	private ReportProperties reportProperties;

	@Before
	public void setUp() throws Exception
	{
		createInvalidPropertiesFile(testPropertiesFilePath);
	}

	private void createInvalidPropertiesFile(String path)
	{
		Properties prop = new Properties();
		OutputStream output = null;

		try
		{
			output = new FileOutputStream(path);

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

	@After
	public void cleanUp()
	{
		deleteInvalidPropertiesFile(testPropertiesFilePath);
	}

	private void deleteInvalidPropertiesFile(String path)
	{
		try
		{
			File file = new File(path);

			if (file.exists())
				file.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testReportPropertiesNullInit()
	{
		reportProperties = new ReportProperties(null);
		basicClassCheck();
	}

	@Test
	public void testReportPropertiesNoFile()
	{
		reportProperties = new ReportProperties("noSuchFile");
		basicClassCheck();
	}

	@Test
	public void testReportPropertiesInvalidFile()
	{
		reportProperties = new ReportProperties(testPropertiesFilePath);
		basicClassCheck();
	}

	private void basicClassCheck()
	{
		assertNotNull(reportProperties);
		assertNotNull(reportProperties.getFilePath());
		assertNotNull(reportProperties.getFileName());
		assertNotNull(reportProperties.getAppName());
	}
}
