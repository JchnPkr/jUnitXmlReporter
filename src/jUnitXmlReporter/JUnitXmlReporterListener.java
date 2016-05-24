package jUnitXmlReporter;

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

public class JUnitXmlReporterListener
{
	private HashMap<String, TestSuite> testSuiteMap;
	private File reportFilePath;
	private XMLOutputter xmlOutput;
	private Document jDoc;
	private Element rootElement;
	
	public JUnitXmlReporterListener(String filePath, String fileName, String appName) throws InvalidParameterSpecException
	{
		if(validateFileName(fileName))
		{
			this.reportFilePath=new File(filePath,fileName);
			this.xmlOutput = new XMLOutputter();
			this.jDoc = new Document(new Element("testsuites"));
			this.setRootElement(jDoc, appName);
		}
		else
		{
			String msg = "Failed to create new  JUnitXmlReporterListener! Filename"
					+ "has to end with '.xml'";
			throw new InvalidParameterSpecException(msg);
		}
	}
	
	private void setRootElement(Document jDoc, String appName)
	{
		this.rootElement = jDoc.getRootElement();
		Namespace ns = Namespace.getNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
		this.rootElement.addNamespaceDeclaration(ns);
		this.rootElement.setAttribute(new Attribute("noNamespaceSchemaLocation","https://svn.jenkins-ci.org/trunk/hudson/dtkit/dtkit-format/dtkit-junit-model/src/main/resources/com/thalesgroup/dtkit/junit/model/xsd/junit-4.xsd",ns));
		this.rootElement.setAttribute("name", appName);
	}
	
	private boolean validateFileName(String fileName)
	{
		return fileName.endsWith(".xml");
	}
	
	public void addTestsuite(TestSuite ts)
	{
		this.testSuiteMap.put(ts.getName(), ts);
	}
	
	public void addTestCase(String testSuiteName, TestCase tc) throws Exception
	{
		if(testSuiteMap.containsKey(testSuiteName))
			testSuiteMap.get(testSuiteName).addTestCase(tc);
		else
		{
			String msg = "Failed to add testcase! Unknown testsuite name: '"
					+ testSuiteName+ "'"; 
			throw new Exception(msg);
		}
	}
	
	/**
	 * TODO: 
	 * -collect messages from Testsuite->testcases
	 * -generate xml tags via Elementcreator
	 */
	public void writeJUnitReport()
	{					
		//TODO init testcases
		
		
		//TODO fill testcase elements
		
		
		//TODO add testcase suites to root suite of report document
		
		
		//building JUnit report xml file
		xmlOutput.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
		try
		{
			xmlOutput.output(jDoc, new OutputStreamWriter(new FileOutputStream(reportFilePath), "UTF-8"));
		} 
		catch (IOException io)
		{
			System.out.println(io.getMessage());
		}		
	}
}
