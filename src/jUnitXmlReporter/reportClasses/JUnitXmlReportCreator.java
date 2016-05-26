package jUnitXmlReporter.reportClasses;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.spec.InvalidParameterSpecException;
import java.util.HashMap;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import jUnitXmlReporter.jUnitElementClasses.ReportTestSuite;

public class JUnitXmlReportCreator
{
	private File reportFilePath;
	private XMLOutputter xmlOutput;
	private Document jDoc;
	private Element rootElement;

	public JUnitXmlReportCreator(String filePath, String fileName, String appName) throws InvalidParameterSpecException
	{
		if (validateFileName(fileName))
		{
			this.reportFilePath = new File(filePath);
			
			if(!this.reportFilePath.exists())
				this.reportFilePath.mkdir();
			
			this.reportFilePath = new File(filePath, fileName);
			this.xmlOutput = new XMLOutputter();
			this.jDoc = new Document(new Element("testsuites"));
			this.setRootElement(jDoc, appName);
		}
		else
		{
			String msg = "Failed to create new  JUnitXmlReporterListener! Filename" + "has to end with '.xml'";
			throw new InvalidParameterSpecException(msg);
		}
	}

	private void setRootElement(Document jDoc, String appName)
	{
		this.rootElement = jDoc.getRootElement();
		Namespace ns = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		this.rootElement.addNamespaceDeclaration(ns);
		this.rootElement.setAttribute(new Attribute("noNamespaceSchemaLocation",
				"https://svn.jenkins-ci.org/trunk/hudson/dtkit/dtkit-format/dtkit-junit-model/"
				+ "src/main/resources/com/thalesgroup/dtkit/junit/model/xsd/junit-4.xsd", ns));
		this.rootElement.setAttribute("name", appName);
	}

	private boolean validateFileName(String fileName)
	{
		return fileName.endsWith(".xml");
	}

	public void createJUnitReport(HashMap<String, ReportTestSuite> testSuiteMap)
	{
		this.addTestSuitesToRoot(testSuiteMap);
		this.writeJUnitReportXmlFile();
	}

	private void addTestSuitesToRoot(HashMap<String, ReportTestSuite> testSuiteMap)
	{
		for (ReportTestSuite testSuite : testSuiteMap.values())
			this.rootElement.addContent(testSuite.getJUnitTestSuiteXmlElement());
	}

	private void writeJUnitReportXmlFile()
	{
		this.xmlOutput.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));

		try
		{
			this.xmlOutput.output(this.jDoc,
					new OutputStreamWriter(new FileOutputStream(this.reportFilePath), "UTF-8"));
		}
		catch (IOException io)
		{
			System.out.println(io.getMessage());
		}
	}
}
