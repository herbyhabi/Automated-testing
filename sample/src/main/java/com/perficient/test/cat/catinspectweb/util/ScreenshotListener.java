package com.perficient.test.cat.catinspectweb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenshotListener extends TestListenerAdapter {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public void onTestFailure(ITestResult tr){

		try{
			
			log.info("The scripts is failed, about to take the screenshot. The method name of this scripts is "+tr.getName());
		
		// New File with desired Name
		String fileFolderName = "Screenshots" + TestCaseBase.envFlag;

		// Get current time mills and convert to desired Format
		SimpleDateFormat dateFormatType = new SimpleDateFormat("MM-dd-yyyy");
		Date resultdate = new Date(System.currentTimeMillis());

		// Verify there is scroll bar exist or not by height of scroll.
		String execScript2 = "return document.documentElement.scrollHeight";
		JavascriptExecutor scrollBarPresent = (JavascriptExecutor) (TestCaseBase.driver);
		Long heightOfScrollBar = (Long) (scrollBarPresent.executeScript(execScript2));

		// Decide how many screenshots need to captured depends on height of the scroll bar
		double scrollCountBefore = Math.round(heightOfScrollBar.doubleValue()/ 450);
		int scrollCount = (int) scrollCountBefore;

		// At least capture one screenshot
		File[] screenshotArray = new File[scrollCount + 1];
		screenshotArray[0] = new File(fileFolderName + File.separator+ dateFormatType.format(resultdate) + "_"+ TestCaseBase.envFlag + "_" + tr.getName() + "Pic" + 0+ ".png");

		// Put other screenshots in the array if need
		for (int i = 1; i < (screenshotArray.length) - 1; i++) {

			screenshotArray[i] = new File(fileFolderName + File.separator+ dateFormatType.format(resultdate) + "_"+ TestCaseBase.envFlag + "_" + tr.getName() + "Pic" + i+ ".png");

		}

		// Delete captured screenshots before on current script
		if (screenshotArray[0].getParentFile().isDirectory()) {
			for (File subFile : screenshotArray[0].getParentFile().listFiles()) {
				Path pathForSubFile = Paths.get(subFile.getAbsolutePath().toString());
				String subFileName = pathForSubFile.getFileName().toString();
				if (subFileName.contains(tr.getName())) {
					subFile.delete();
				}
			}
		}

		// New folder for certain environment if it doesn't exist
		if (!screenshotArray[0].exists()) {
			new File(screenshotArray[0].getParent()).mkdirs();
		}

		// Create other screenshots except the first one
		for (int j = 0; j < (screenshotArray.length) - 1; j++) {

			try {
				screenshotArray[j].createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Capture the screenshot and scroll down before if need
		for (int i = 0; i < (screenshotArray.length) - 1; i++) {
			try {

				if (i > 0) {
					JavascriptExecutor jsx = (JavascriptExecutor) (TestCaseBase.driver);
					jsx.executeScript("window.scrollBy(0,400)", "");

				}
				new FileOutputStream(screenshotArray[i]).write(((TakesScreenshot) TestCaseBase.driver).getScreenshotAs(OutputType.BYTES));

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		}
		catch(UnreachableBrowserException e){
			
			log.info("No screenshot be taken for browser was closed.");
			
		}

	}
}
