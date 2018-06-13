package com.perficient.test.cat.catinspectweb.util;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.reporter.ExtentHtmlReporter;
//import com.relevantcodes.extentreports.reporter.ExtentXReporter;
//import com.relevantcodes.extentreports.reporter.configuration.Protocol;

/**
 * Created by eric.guo on 8/8/2016.
 */
public class ComplexReportFactory extends TestCaseBase{
    public static ExtentReports reporter;
//    public static ExtentXReporter extentxReporter;
//    public static ExtentHtmlReporter htmlReporter;
    public static Map<Long, String> threadToExtentTestMap = new HashMap<Long, String>();
    public static Map<String, ExtentTest> nameToTestMap = new HashMap<String, ExtentTest>();

    
    private synchronized static ExtentReports getExtentReport() {
        if (reporter == null) {
            String filePath = "./test-output/ExtentReport_" + buildNumber + ".html";
            reporter = new ExtentReports(filePath, true);
        }
        return reporter;
    }

    public synchronized static ExtentTest getTest(String testName, String categoryName, String testDescription) {

        // if this test has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            Long threadID = Thread.currentThread().getId();
            ExtentTest test = getExtentReport().startTest(testName, testDescription);
            test.assignCategory(categoryName);
            nameToTestMap.put(testName, test);
            threadToExtentTestMap.put(threadID, testName);
        }
        return nameToTestMap.get(testName);
    }

    public synchronized static ExtentTest getTest(String testName) {
        return getTest(testName, "testde", "testede");
    }

    public synchronized static ExtentTest getTest() {
        Long threadID = Thread.currentThread().getId();

        if (threadToExtentTestMap.containsKey(threadID)) {
            String testName = threadToExtentTestMap.get(threadID);
            return nameToTestMap.get(testName);
        }
        //system log, this shouldnt happen but in this crazy times if it did happen log it.
        return null;
    }

    public synchronized static void closeTest(String testName) {

        if (!testName.isEmpty()) {
            ExtentTest test = getTest(testName);
            getExtentReport().endTest(test);
        }
    }

    public synchronized static void closeTest(ExtentTest test) {
        if (test != null) {
              getExtentReport().endTest(test);
             
        }
    }

    public synchronized static void closeTest() {
        ExtentTest test = getTest();
        closeTest(test);
    }

    public synchronized static void closeReport() throws IOException {
        if (reporter != null) {
        //	reporter.endTest(test);
            reporter.flush();
            reporter.close();
            File currentReport = new File("./test-output/ExtentReport_" + buildNumber + ".html");
            FileUtils.copyFile(currentReport, new File("./test-output/ExtentReport.html"));
        }
    }

    public static String getDatetime() {

        SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");

        return date.format(new Date());

    }
}
