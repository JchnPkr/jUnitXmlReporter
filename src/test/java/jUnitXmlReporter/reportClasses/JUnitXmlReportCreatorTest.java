package jUnitXmlReporter.reportClasses;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jUnitXmlReporter.exceptionClasses.InvalidReportFileFormat;
import jUnitXmlReporter.jUnitElementClasses.testSuite.ReportTestSuite;
import jUnitXmlReporter.reportClasses.JUnitXmlReportCreator;

public class JUnitXmlReportCreatorTest
{
	private static final String testReportFilePath = "./src/jUnitTests";
	private static final String testReportFileName = "testReportTestFile.xml";

	private JUnitXmlReportCreator reportCreator;

	@Before
	public void setUp() throws Exception
	{
		reportCreator = new JUnitXmlReportCreator(testReportFilePath, testReportFileName, "testName");
	}

	@After
	public void cleanUp()
	{
		deleteTestReportFile(testReportFilePath + "/" + testReportFileName);
	}

	private void deleteTestReportFile(String path)
	{
			File file = new File(path);

			if (file.exists())
				file.delete();
	}

	@Test(expected = InvalidReportFileFormat.class)
	public void testJUnitXmlReportCreatorReportPropertiesInvalidFormatException() throws InvalidReportFileFormat
	{
		reportCreator = new JUnitXmlReportCreator(testReportFilePath, "invalidFileFormat.txt", "testName");
	}

	@Test
	public void testJUnitXmlReportCreatorStringStringString()
	{
		assertNotNull(reportCreator);
	}

	@Test
	public void testCreateJUnitReport()
	{
		File file = new File(testReportFilePath + "/" + testReportFileName);

		reportCreator.createJUnitReport(new HashMap<String, ReportTestSuite>());

		assertTrue(file.exists());
	}
}
