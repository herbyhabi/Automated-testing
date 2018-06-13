package com.perficient.test.cat.catinspectweb.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.eval.NotImplementedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.beust.jcommander.ParameterException;
import com.perficient.selenium.support.FieldDecoratorEX;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseBase {

	public static final String CHROME = "1";
	public static final String FIREFOX = "2";
	public static final String IE = "3";
	public static final String PRODUCTION = "PROD";
	public static final String QA = "QA";
	public static final String Dev = "DEV";
	public static EventFiringWebDriver driver;
	public static WebDriver driverOriginal;
	protected static ExtentTest test;
	public static String buildNumber;
	public static CustomAssertion customAssertion;
	public static String browserFlag;
	public static String envFlag;
	private String className;
	public static String testName;
	private final String description="The Extent Report of Cat Inspect Web Automated Regression";
	public static LogWebDriverEventListener eventListener;
	public static Log log = LogFactory.getLog(TestCaseBase.class);
	public static FieldDecoratorEX decEx;
	public static String DEALERNAME;
	public final static int daysBeforeExpiry = 2;//defines number of days before the current date 
	
	@BeforeSuite(alwaysRun =true)
	public void generateBuildNumber() throws Exception{

	    Date date=new Date();
	    DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	    String time=format.format(date);
	    log.info(time);
	    buildNumber=time;
	    log.info("Delete screen shots generated "+daysBeforeExpiry+" days ago.");
	    deleteOldScreenshots();
	}

	//@BeforeSuite(alwaysRun = true)
	public void moveOriginreport() throws Exception {

		try {
			File originFile = new File("test-output/emailable-report.html");
			// Clear the file moved before
			FunctionUtil.clearCertainFileUnderFolder("FailedScriptsLastCI/" + originFile.getName());
			Thread.sleep(5000);
			
			//clear extented report folder
			File imageFile = new File("errorImages");
			log.info("Before Deletion");
			FunctionUtil.emptyFolderAll(imageFile);
			
			// Move report file for this CI build
			if (originFile.renameTo(new File("FailedScriptsLastCI/" + originFile.getName())))
				log.info("File is moved successful!");
			else
				log.info("File is failed to move!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void cleanDownloadFolder() throws Exception {
		FunctionUtil.emptyDownloadFolder();
	}
	
	@Parameters({ "browserFlagO", "environmentFlag"})
	@BeforeMethod(alwaysRun = true)
	
	public void setUpBrowser(@Optional(CHROME) String browserFlagO, @Optional(Dev) String environmentFlag) throws Exception {
		browserFlag = browserFlagO;
		envFlag = environmentFlag;

		switch (browserFlagO) {
		case CHROME:
			setUpChrome();
			break;
		case IE:
			setUpIE();
			break;
		default:
			throw new ParameterException(browserFlagO + " is a not a valid browser option.");
		}
	}

	public void initListener() {
		// Add Event Listener into the driver
		driver = new EventFiringWebDriver(driverOriginal);
		eventListener = new LogWebDriverEventListener();
		driver.register(eventListener);
		// Clean cache and cookies
		driver.manage().deleteAllCookies();
		// Refresh the driver after deleting cookies
		driver.navigate().refresh();
		// maximize browser
		FunctionUtil.maximeBrowser();
	}

	private void setUpIE() {
		// TODO: add IE binary dependency
		throw new NotImplementedException("IE is not supported in current framework.");
	}

	private void setUpChrome() throws Exception{
		File fileForDownload = new File("./Download");
		String desiredFilePathForDownload = fileForDownload.getAbsolutePath();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		//cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		File chromeDriver = new File("./lib/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", desiredFilePathForDownload);
		chromePrefs.put("download.prompt_for_download", false);

		ChromeOptions options = new ChromeOptions();
		
		File chromeBinary = new File("lib/Google Chrome/chrome.exe");
		

		// log.info("Chrome in = " + chromeBinary.getAbsolutePath());
		options.setBinary(chromeBinary.getAbsolutePath());
		//setup user data to store chrome options. 
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--test-type");
		options.addArguments("--no-sandbox");
		//options.addArguments("--dns-prefetch-disable");

		/********** Set Proxy **********/
		//cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);

		cap.setCapability(ChromeOptions.CAPABILITY, options);

		//Get Test Name
		String testCaseFullName=this.getClass().getName();
		testName = (testCaseFullName.split("\\.")[testCaseFullName.split("\\.").length-1]);
		className = envFlag;
				
		//Add test name into the Log
		System.out.println("Run in Browser: Chrome");
		System.out.println("Current Test Case Name = " + testName);
		System.out.println("Run for Environment: " + className);
		
		driverOriginal = new ChromeDriver(cap);
		
	    test = ComplexReportFactory.getTest(testName, className, description);
	    customAssertion = new CustomAssertion();
		
		initListener();
	}
	

	/**
	 * Objective: Close the opened browser which was opened by selenium server
	 */

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		//timeoutListener.deadFlag.set(true);
		try {
			driver.manage().deleteAllCookies();
			driver.quit();
			driverOriginal.quit();
		    SystemUtil.driverKiller();
			FunctionUtil.emptyFolder(System.getenv("TEMP"));
		} catch (Exception e) {
			log.error("Driver have quited by other thread or by hand.");
		}
	}

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method caller, ITestResult iTestResult) throws Exception {
        ComplexReportFactory.closeTest(testName);
        if(iTestResult.getThrowable() != null && !iTestResult.getThrowable().toString().contains("java.lang.AssertionError")){
        	test.log(LogStatus.FAIL, "Exception Type: " + iTestResult.getThrowable().getMessage());
        	String path = customAssertion.snapshot((TakesScreenshot) driverOriginal);
        	customAssertion.printError(path);
        }
    }
    
    @AfterSuite(alwaysRun=true)
    public void afterSuite() throws IOException {
        ComplexReportFactory.closeReport();
    }
    
    public void verifyFinalAssert(){
    	test.log(LogStatus.INFO, "");//make an empty row in the extent report
    	Assert.assertEquals(ComplexReportFactory.getTest(testName).getRunStatus().toString(), LogStatus.PASS.toString(),"the script status is :"+ComplexReportFactory.getTest(testName).getRunStatus() );
    }
    
    /**
     * This method is to delete the screenshots (in test-out/errorImages)
     * whose date is before the expire date.
     * The expire date is defined at daysBeforeExpiry in this class.
     * @author matthew.feng
     * @throws Exception 
     */
    private void deleteOldScreenshots() throws Exception{
    	File file = new File("test-output/errorImages");
    	DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
    	Date today = new Date();
    	Date fileDate = null;
    	String fileName = "";
    	File[] contents = file.listFiles();
    		if (contents.length>0){
    			for (File f : contents){
    				fileName = (f.getName().split("\\."))[0];
    				try{
    					fileDate = format.parse(fileName);
    			  	}catch (ParseException pe){
    			  		System.out.println("The screen shot "+fileName+" does NOT match the date format.");
    		    		continue;
    		    	}
    				if (FunctionUtil.getDistanceDays(today, fileDate)> daysBeforeExpiry){
    					f.delete();				
    					System.out.println("Screen Shot "+ fileName +".png is deleted.");
    				}
    			}
    		}
  
    }
}
