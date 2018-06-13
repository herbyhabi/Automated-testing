package com.perficient.test.cat.catinspectweb.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

public class CustomAssertion extends TestCaseBase{
    private Log log = LogFactory.getLog(this.getClass());
//    private WebDriver driver;
//    private ExtentTest test;
    String currentPath = ".\\test-output\\errorImages";
    String returnPath = ".\\errorImages";

    public CustomAssertion() {
//      driver = d;
//      test = extentTest;
  }

  public enum ErrorType {
      ELEMENT_NOTFOUND("Element was not found, "),
      ELEMENT_STALE("Element was no longer located in the DOM and has become stale, "),
      WAIT_TIMEOUT("Wait timeout occured, "),
      ASSERTED("Assertion failed, ");
      public String errorMsg;

      private ErrorType(String errorMsg) {
          this.errorMsg = errorMsg;
      }
  }

  public boolean assertEquals(String actual, String expected, String message) {
      try {
          Assert.assertEquals(actual, expected, message);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(message, path, actual, expected);
          return false;
      }
  }
  
  public boolean assertEquals(List<String> actual, List<String> expected, String message) {
      try {
          Assert.assertEquals(actual, expected, message);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }

  }

  public boolean assertEquals(String actual, String expected) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual, expected);
          return false;
      }
  }
  
  public boolean assertEquals(boolean actual, boolean expected) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual, expected);
          return false;
      }
  }
  
  public boolean assertEquals(double actual, double expected) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual+"", expected+"");
          return false;
      }
  }
  
  public boolean assertEquals(double actual, double expected, String message) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, message, actual+"", expected+"");
          return false;
      }
  }
  
  public boolean assertEquals(int actual, int expected, String message) {
      try {
          Assert.assertEquals(actual, expected, message);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(message, path, actual+"", expected+"");
          return false;
      }

  }
  
  public boolean assertEquals(int actual, int expected) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual+"", expected+"");
          return false;
      }
  }
  
  
  public boolean assertEquals(long actual, long expected, String message) throws IOException {
      try {
          Assert.assertEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, path);
          return false;
      }
  }
  
  public boolean assertNotEquals(Object actual, Object expected, String message) {
      try {
          Assert.assertNotEquals(actual, expected, message);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }

  }
  
  public boolean assertNotEquals(int actual, int expected, String message) {
      try {
          Assert.assertNotEquals(actual, expected, message);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(message, path, actual+"", expected+"");
          return false;
      }

  }
  
  public boolean assertNotEquals(int actual, int expected) {
      try {
          Assert.assertNotEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual+"", expected+"");
          return false;
      }

  }
  
  
  public boolean assertNotEquals(double actual, double expected) {
      try {
          Assert.assertNotEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(path, actual+"", expected+"");
          return false;
      }

  }
  
  
  public boolean assertNotEquals(String actual, String expected) {
      try {
          Assert.assertNotEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, path);
          return false;
      }
  }
  
  
  public boolean assertNotEquals(String actual, String expected, String message) {
      try {
          Assert.assertNotEquals(actual, expected);
          //test.log(LogStatus.PASS, "Actual: " + actual + " Expected: " + expected);
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }
  }

  public boolean assertNull(Object obj, String message) {
      try {
          Assert.assertNull(obj, message);
          //test.log(LogStatus.PASS, "Object " + obj.toString() + " is null.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }

  }

  public boolean assertNull(Object obj) {
      try {
          Assert.assertNull(obj);
          //test.log(LogStatus.PASS, "Object " + obj.toString() + " is null.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e,path);
          return false;
      }

  }
  
  public boolean assertNotNull(Object obj, String message) {
      try {
          Assert.assertNotNull(obj, message);
          //test.log(LogStatus.PASS, "Object " + obj.toString() + " is not null.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }

  }

  public boolean assertNotNull(Object obj) {
      try {
          Assert.assertNotNull(obj);
          //test.log(LogStatus.PASS, "Object " + obj.toString() + " is not null.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e,path);
          return false;
      }

  }
  
  public void assertTrueAndExit(boolean expression, String message) {
      if(expression){
      	test.log(LogStatus.PASS, message + " is true.");
      }else{        	
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(false, message, path);
          Assert.assertTrue(false, message);
      }
  }
  
  public void assertFalseAndExit(boolean expression, String message) {
      if(!expression){
      	test.log(LogStatus.PASS, message + " is true.");
      }else{        	
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(true, message, path);
          Assert.assertFalse(!false, message);
      }
  }

  public boolean assertTrue(boolean expression, String message) {
      try {       	
          Assert.assertTrue(expression, message);
         //test.log(LogStatus.PASS, message + " is true.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }
  }

  public boolean assertTrue(boolean expression) {
      try {
          Assert.assertTrue(expression);
         // test.log(LogStatus.PASS, expression + " is true.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, path);
          return false;
      }
  }


  public boolean assertFalse(boolean expression, String message) {
      try {
          Assert.assertFalse(expression, message);
          //test.log(LogStatus.PASS, message + " is false.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, message, path);
          return false;
      }
  }

  public boolean assertFalse(boolean expression) {
      try {
          Assert.assertFalse(expression);
          //test.log(LogStatus.PASS, expression + " is false.");
          return true;
      } catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, path);
          return false;
      }
  }
  
  //Log Status for SoftAssertion 
  public boolean assertAll(SoftAssert softAssert, String description){
  	try{
  		softAssert.assertAll();
          //test.log(LogStatus.PASS, description + " is true.");
          return true;
  	}catch (AssertionError e) {
          String path = snapshot((TakesScreenshot) driverOriginal);
          printError(e, path);
          return false;
      }
  }
  
 

  public String snapshot(TakesScreenshot drivername) {

      File scrFile = drivername.getScreenshotAs(OutputType.FILE);
      Date date=new Date();
	    DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
	    String timestamp=format.format(date);
      try {
          System.out.println("save snapshot path is:" + currentPath + "\\"
                  + timestamp + ".png");
          FileUtils
                  .copyFile(scrFile, new File(currentPath + "\\" + timestamp + ".png"));
          FileUtils
                  .copyFile(scrFile, new File(returnPath + "\\" + timestamp + ".png"));
      } catch (IOException e) {
          System.out.println("Can't save screenshot");
          return "";
      } finally {
          System.out.println("screen shot finished, it's in " + currentPath
                  + " folder");
          return returnPath + "\\" + timestamp + ".png";
      }
  }

  public String getDatetime() {

      SimpleDateFormat date = new SimpleDateFormat("yyyymmdd_hhmmss");

      return date.format(new Date());

  }

  public void printError(AssertionError e, String message, String path) {
      log.info(path);
      logStackTraceInfo();
      test.log(LogStatus.FAIL, "\n"+ message  +  test.addScreenCapture(path) );
  }
  
  
  public void printError(String message, String path, String actual, String expected) {
      log.info(path);
      logStackTraceInfo();
      test.log(LogStatus.FAIL, "\n"+ message + " Actual = "+actual+" Expected = "+expected + test.addScreenCapture(path));
  }
  
  public void printError(String path, String actual, String expected) {
      log.info(path);
      logStackTraceInfo();
      test.log(LogStatus.FAIL, "\n Actual = "+actual+" Expected = "+expected + test.addScreenCapture(path));
  }
  
  public void printError(String path, boolean actual, boolean expected) {
      log.info(path);
      logStackTraceInfo();
      test.log(LogStatus.FAIL, "\n Actual = "+actual+" Expected = "+expected + test.addScreenCapture(path));
  }

  
  public void printError(String path) {
      log.info(path);
      logStackTraceInfo();
      test.log(LogStatus.FAIL, test.addScreenCapture(path) );
  }
  
  
  public void printError(AssertionError e, String path) {
  	logStackTraceInfo();
      test.log(LogStatus.FAIL, "\n" + e +  test.addScreenCapture(path));
  }
  
  public void printError(boolean isFalse, String message, String path) {
  	logStackTraceInfo();
      test.log(LogStatus.FAIL, message  + isFalse + test.addScreenCapture(path) );
  }
  
  public void logStackTraceInfo(){
  	int stackSize = Thread.currentThread().getStackTrace().length;
  	//Only print 7 stack traces.
  	if(stackSize > 7){
  		stackSize = 7;
  	}
  	for(int i = 0; i < stackSize; i++){
  		log.info("StackTrace " + i + " is: " + Thread.currentThread().getStackTrace()[i].toString());
  		log.info("LineNumber for " + i + " stack trace is: " + Thread.currentThread().getStackTrace()[i].getLineNumber());
  	}
  	
  	log.info("StackTrace for Assert is: " + Thread.currentThread().getStackTrace()[3].toString());
		log.info("Assert LineNumber is: " + Thread.currentThread().getStackTrace()[3].getLineNumber());
  }
}
