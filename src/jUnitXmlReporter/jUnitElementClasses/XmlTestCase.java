package jUnitXmlReporter.jUnitElementClasses;

import org.jdom2.Element;

public interface XmlTestCase
{
	String getName();

	void setName(String name);

	String getMessage();

	void setMessage(String message);

	String getType();

	Element getJUnitTestCaseXmlElement();
}