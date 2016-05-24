package jUnitXmlReporter;

public interface ReportObserverInterface
{
    void upDate(TestSuite ts);
    void upDate(String testSuiteName, TestCase testCase);
}
