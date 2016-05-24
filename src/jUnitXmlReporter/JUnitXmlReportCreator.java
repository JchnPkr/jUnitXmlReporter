package jUnitXmlReporter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.spec.InvalidParameterSpecException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



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
		"https://svn.jenkins-ci.org/trunk/hudson/dtkit/dtkit-format/dtkit-junit-model/src/main/resources/com/thalesgroup/dtkit/junit/model/xsd/junit-4.xsd",
		ns));
	this.rootElement.setAttribute("name", appName);
    }

    private boolean validateFileName(String fileName)
    {
	return fileName.endsWith(".xml");
    }

    public void createJUnitReport(HashMap<String, TestSuite> testSuiteMap)
    {
	for (String testSuiteName : testSuiteMap.keySet())
	{
	    TestSuite testSuite = testSuiteMap.get(testSuiteName);
	    List<Element> testCaseXmlTagList = createTestCaseXmlTagList(testSuite);
	    this.addTestSuiteToRoot(testSuiteName, testCaseXmlTagList);
	}

	this.writeJUnitXmlReport();
    }

 
    
    /**
     * TODO:
     * -create testcase tag factory to implement switch for case type
     * -add tag if not exists or only message
     * 
     * @param testSuite
     * @return
     */
    private List<Element> createTestCaseXmlTagList(TestSuite testSuite)
    {
	List<Element> testCaseXmlTagList = new ArrayList<>();
	
//	for (TestCase tc : testSuite.getTestCaseList())
	{
		// TODO: create tag factory to implement switch for case type
		// create tag if not exists
		// Element testCaseTag = JUnitXmlElementCreator.

		// TODO: add message to appropriate tag

		// testCaseXmlTagList.add(testCaseTag);
	}
	
	return testCaseXmlTagList;
    }
    
    private void addTestSuiteToRoot(String testSuiteName, List<Element> testCaseXmlTagList)
    {
	List<NameValuePair> attributes = new ArrayList<>();
	attributes.add(new BasicNameValuePair("name", testSuiteName));
	this.rootElement.addContent(JUnitXmlElementCreator.getTestSuite(testCaseXmlTagList, attributes));
    }
    
    private void writeJUnitXmlReport()
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
