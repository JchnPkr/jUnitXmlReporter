package jUnitXmlReporter.jUnitElementClasses.testSuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jdom2.Element;
import org.jdom2.filter.ElementFilter;

import jUnitXmlReporter.jUnitElementClasses.testCases.ReportTestCase;

/**
 * Implementation giving default testsuite format
 */
public class DefaultReportTestSuite implements ReportTestSuite {
	private static final Logger logger = LogManager.getLogger(DefaultReportTestSuite.class.getName());

	private String name;
	private HashMap<String, ReportTestCase> testCaseMap;

	public DefaultReportTestSuite(String name) {
		this.setName(name);
		this.testCaseMap = new LinkedHashMap<>();
	}

	@Override
	public void addTestCase(ReportTestCase tc) {
		if (containsTestCase(tc)) {
			logger.debug("\n\t Update of testsuite: " + name + ", \n" + "\t appending message: '" + tc.getMessage()
					+ "' \n" + "\t to testcase: '" + tc.getName() + "' \n" + "\t of type: '" + tc.getType() + "' \n");

			String oldMessage = testCaseMap.get(tc.getName()).getMessage();
			String newMessage = oldMessage + ", \n" + tc.getMessage();

			testCaseMap.get(tc.getName()).setMessage(newMessage);
		} else {
			logger.debug("\n\t Update of testsuite: " + name + ", \n" + "\t adding testcase: '" + tc.getName() + "' \n"
					+ "\t of type: '" + tc.getType() + "' \n" + "\t with message: '" + tc.getMessage() + "' \n");

			testCaseMap.put(tc.getName(), tc);
		}
	}

	private boolean containsTestCase(ReportTestCase tc) {
		if (testCaseMap.containsKey(tc.getName()))
			return isSameTestCaseType(tc);

		return false;
	}

	private boolean isSameTestCaseType(ReportTestCase tc) {
		String existingTcType = testCaseMap.get(tc.getName()).getType();

		return existingTcType.equals(tc.getType());
	}

	@Override
	public List<ReportTestCase> getTestCaseList() {
		return new ArrayList<ReportTestCase>(testCaseMap.values());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addAllTestCases(List<ReportTestCase> testCaseList) {
		for (ReportTestCase tc : testCaseList) {
			addTestCase(tc);
		}
	}

	@Override
	public Element getJUnitTestSuiteXmlElement() {
		Element suite = new Element("testsuite");

		addTestCasesToSuiteXmlElement(suite);

		suite.setAttribute("name", this.name);
		suite.setAttribute("errors",
				String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("error"))).size()));
		suite.setAttribute("failures",
				String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("failure"))).size()));
		suite.setAttribute("tests",
				String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("testcase"))).size()));
		suite.setAttribute("skipped",
				String.valueOf(IteratorUtils.toList(suite.getDescendants(new ElementFilter("skipped"))).size()));

		return suite;
	}

	private void addTestCasesToSuiteXmlElement(Element suite) {
		for (ReportTestCase testCase : testCaseMap.values()) {
			Element jUnitTestCaseXmlTag = testCase.getJUnitTestCaseXmlElement();
			suite.addContent(jUnitTestCaseXmlTag);
		}
	}
}
