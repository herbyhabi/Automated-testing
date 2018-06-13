package com.perficient.test.cat.catinspectweb.util;

import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.MAX_WAIT_FOR_ELEMENT;
import static com.perficient.test.cat.catinspectweb.testdata.CommonTestData.MAX_WAIT_FOR_PAGE;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.csvreader.CsvWriter;
import com.google.common.base.Function;

public class FunctionUtil extends TestCaseBase {

	public static Log log = LogFactory.getLog(FunctionUtil.class);
	public static String DOWNLOAD_DIR = "Download";
	/**
	 * wait until the element visible in max wait time setting if not visible at
	 * last, throw ElementNotVisibleException to the operations
	 * 
	 * @param element
	 *            :the WebElement to be judged
	 * @param timeout
	 *            :timeout setting in seconds
	 */
	static public void waitUtilElementVisible(WebElement element, int timeout) {

		long start = System.currentTimeMillis();
		boolean isDisplayed = false;
		while (!isDisplayed
				&& ((System.currentTimeMillis() - start) < timeout * 1000)) {

			isDisplayed = (element == null) ? false : element.isDisplayed();
		}
		if (!isDisplayed) {
			throw new ElementNotVisibleException(
					"the element is not visible in " + timeout + "seconds!");
		}
	}
	
	/**
	 * * Delete all files from download folder
	 * 
	 * @return
	 * @throws Exception
	 */
	static public void emptyDownloadFolder() throws Exception {
		File file = new File(DOWNLOAD_DIR);
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			temp = new File(DOWNLOAD_DIR + File.separator + element);
			if (temp.isFile())
				temp.delete();
		}
		emptyFolder(DOWNLOAD_DIR);
	}
	
	/**
	 * Delete all the files and folder in a specific directory
	 * 
	 * @param folderName
	 * @throws Exception
	 * @author matthew.feng
	 */
	static public void emptyFolderAll(File file) throws Exception {
		File[] contents = file.listFiles();
		if (contents != null)
			for (File f : contents){
				log.info(f.getName());
				emptyFolderAll(f);
			}
		file.delete();
	}
	
	/**
	 * 
	 * @param folderName
	 * @throws Exception
	 * @author tripp.hu
	 */
	static public void emptyFolder(String folderName) throws Exception {
		File file = new File(folderName);
		String[] tempList = file.list();
		File temp = null;
		for (String element : tempList) {
			temp = new File(folderName + File.separator + element);
			if (temp.isFile())
				temp.delete();
		}
	}

	/**
	 * determine if a main string contains a substring
	 * 
	 * @author matthew.feng
	 * @param mainString
	 *            : the main string
	 * @param subString
	 *            : the substring
	 */
	static public boolean containsSubstring(String mainString, String subString) {		
		return mainString.contains(subString);
	}

	/**
	 * determine if a main string contains a substring by ignore case.
	 * 
	 * @author Jenny.sun
	 * @param mainString
	 *            : the main string
	 * @param subString
	 *            : the substring
	 */

	public static boolean ignoreCaseContains(String mainString, String subString) {
		String string1 = mainString.toUpperCase();
		String string2 = subString.toUpperCase();
		return string1.contains(string2);
	}

	/**
	 * Text from element1 contains text from element2 by ignore case.
	 * 
	 * @author Jenny.sun
	 * @param element1
	 *            : main WebElement element1
	 * @param element2
	 *            : main WebElement element2
	 */

	public static boolean ignoreCaseWebElementContains(WebElement element1,
			WebElement element2) {
		String string1 = element1.getText().toUpperCase();
		String string2 = element2.getText().toUpperCase();
		return string1.contains(string2);
	}

	/**
	 * wait until the element visible in max wait time setting which is set for
	 * all test cases if not visible at last, throw ElementNotVisibleException
	 * to the operations
	 * 
	 * @param element
	 *            :the WebElement to be judged
	 * @throws Exception
	 */
	static public void waitUtilElementVisible(WebElement element)
			throws Exception {
		waitUtilElementVisible(element, MAX_WAIT_FOR_ELEMENT);
	}

	/**
	 * Objective: switch to frame
	 * 
	 * @param frameName
	 *            : frameName string
	 * @throws Exception
	 */
	static public void switchToFrame(WebElement frame) throws Exception {
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
		// Catch the exception when Frame not exist
		try {
			TestCaseBase.driver.switchTo().defaultContent();
			TestCaseBase.driver.switchTo().frame(frame);
		} catch (Exception ex) {
			ex.getMessage();
		}
	}

	/**
	 * Objective: fill out input field
	 * 
	 * @param element
	 *            :WebElement object
	 * @param inputValue
	 *            :string value
	 * @throws Exception
	 */
	static public void input(WebElement element, String inputValue)
			throws Exception {
		element.clear();
//		customAssertion.assertFalse(inputValue.isEmpty(),
//				"Desired to enter string is Empty:" + element);
		element.sendKeys(inputValue);
	}

	/**
	 * wait until the element to be clickable
	 * 
	 * @author paris.yu
	 * @param element
	 *            : the element to wait for
	 * @param timeout
	 *            : waiting until the timeout
	 */
	public static void untilElementClickable(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * Objective: Wait for a certain time
	 * 
	 * @param driver
	 *            :WebDriver
	 * @param timeout
	 *            :timeout setting in seconds
	 * @throws Exception
	 */
	static public void waitImplicitly(int timeout) throws Exception {

		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	/**
	 * Objective: wait for a certain time which is set for all test cases
	 * 
	 * @param driver
	 *            :WebDriver
	 * @throws Exception
	 */
	static public void waitImplicitly() throws Exception {
		waitImplicitly(MAX_WAIT_FOR_PAGE);

	}

	/**
	 * Objective: Maximize browser
	 */
	static public void maximeBrowser() {
		TestCaseBase.driver.manage().window().maximize();
	}

	/**
	 * Objective: Click Element in another way
	 */
	static public void click2(WebElement element) throws Exception {
		FunctionUtil.waitForElementExist(element);
		Actions builder = new Actions(driverOriginal);
		builder.moveToElement(element).sendKeys(Keys.DOWN);
		builder.moveToElement(element).sendKeys(Keys.UP);
	}

	/**
	 * Objective: Click Element
	 */
	static public void click(WebElement element) throws Exception {

		FunctionUtil.waitForElementExist(element);
		//untilElementClickable(element, 30);

		// //See if href attribute exist or not
		// boolean isAttribtuePresent =isAttribtuePresent(element,"href");
		//
		// //pause if link will navigate to incorrect qa1parts page
		// if(isAttribtuePresent){
		// String URLOfLink = element.getAttribute("href");
		// customAssertion.assertFalse(URLOfLink.contains("qa1parts.cat.com"),"Page
		// will navigate to qa1parts page, pause now!");
		//
		// }

		if (TestCaseBase.browserFlag.equals("2")) {
			element.sendKeys("\n");
			element.click();

		} else if (TestCaseBase.browserFlag.equals("0")
				|| TestCaseBase.browserFlag.equals("1")) {
			element.click();

		} else {
			throw new Exception(
					"browserFlag is not set as \"0\" or \"1\" in TestCaseBase class");
		}

	}

	/**
	 * Will not throw an Exception, if the element is not exist.
	 * 
	 * @param e
	 * @throws Exception
	 * @author tripp.hu
	 */
	public static boolean ifExistThenClick(WebElement e) throws Exception {
		if (isElementExist(e)) {
			click(e);
			return true;
		} else {
			log.error("Element not found.");
			return false;
		}
	}

	/**
	 * Will not throw an Exception, if the element is not exist.
	 * 
	 * @param e
	 * @param s
	 * @throws Exception
	 * @author tripp.hu
	 */
	public static void ifExistThenInput(WebElement e, String s)
			throws Exception {
		if (isElementExist(e))
			input(e, s);
	}

	/**
	 * If a selector Web Element exists, select a specific option in the
	 * selector Will not throw an Exception, if the element is not exist.
	 * 
	 * @param e
	 * @param s
	 * @throws Exception
	 * @author matthew.feng
	 */
	public static void ifExistThenSelect(WebElement e, String s)
			throws Exception {
		if (isElementExist(e)) {
			List<WebElement> options = e.findElements(By.tagName("option"));
			selectFromDropDown(options, s);
		}
	}

	/**
	 * Objective: Check is the certain attribute is in the element or not
	 */

	static public boolean isAttribtuePresent(WebElement element,
			String attribute) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}

		return result;
	}

	/**
	 * Objective: Select a desired option from a Select Drop down
	 * 
	 * @throws Exception
	 */
	static public void selectFromCheckBox(List<WebElement> group_Name_Options,
			String deisredOptionToBeSelected) throws Exception {

		// Define parameters
		boolean isElementClicked = false;

		// Match expected item to be selected by Loop
		try {
			for (WebElement option : group_Name_Options) {

				// Get each option text
				String optionValue = option.getText();

				// Click the desired option when it is did not be checked
				if (optionValue.contains(deisredOptionToBeSelected)) {
					boolean isDesiredOptionBeSelected = getCheckedBoxCheckedOrNot(option);
					if (!isDesiredOptionBeSelected) {
						option.click();
						isElementClicked = true;
						break;
					} else if (isDesiredOptionBeSelected) {
						isElementClicked = true;
						break;
					}
				}
			}
		} catch (NullPointerException ex) {

			// Assert the error when the desired option did not be displayed in
			// the drop down
			customAssertion.assertTrue(isElementClicked,
					"List Element not exist in the Application right now:"
							+ group_Name_Options);
		}

		// Assert the error when the desired option did not be displayed in the
		// drop down
		customAssertion.assertTrue(isElementClicked, "Option: "
				+ deisredOptionToBeSelected + " is not Exist in the DropDown");
	}

	/**
	 * Objective: Select a desired option from a Select Drop down
	 * 
	 * @throws Exception
	 */
	static public void selectFromDropDown(List<WebElement> group_Name_Options,
			String deisredOptionToBeSelected) throws Exception {
		log.info("deisredOptionToBeSelected :: " + deisredOptionToBeSelected);
		boolean isElementClicked = false;
		// Match expected item to be selected by Loop
		for (WebElement option : group_Name_Options) {
				// Get each option text
				String optionValue = option.getText().trim();
				log.info("optionValue :: " + optionValue);

				// Click the desired option when it is did not be checked
				// optionValue.contains(deisredOptionToBeSelected)
				if (optionValue.contains(deisredOptionToBeSelected.trim())) {
						clickByJavaScripts(option);
						isElementClicked = true;
						break;
					}
		}
		// Assert the error when the desired option did not be displayed in
		// the drop down
		customAssertion.assertTrue(isElementClicked,
				"List Element does not exist in the Application right now:"
							+ group_Name_Options);
	}
	
	
	/**
	 * Objective: Select a desired option from a Select Drop down
	 * 
	 * @throws Exception
	 */
	static public void selectFromDropDownWithClick(List<WebElement> group_Name_Options,
			String deisredOptionToBeSelected) throws Exception {
		boolean isElementClicked = false;
		// Match expected item to be selected by Loop
		for (WebElement option : group_Name_Options) {
				// Get each option text
				String optionValue = option.getText().trim();

				// Click the desired option when it is did not be checked
				// optionValue.contains(deisredOptionToBeSelected)
				if (optionValue.contains(deisredOptionToBeSelected.trim())) {
						click(option);
						isElementClicked = true;
						break;
					}
		}
		
		
		// Assert the error when the desired option did not be displayed in
		// the drop down
		customAssertion.assertTrue(isElementClicked,
				"List Element does not exist in the Application right now:"
							+ group_Name_Options);
	}
	  /**
     * Click on the Drop down and select an specified options. Click is the ONLY meothod to perform click action.
     * @author matthew.feng
     * @param selector
     * @param toSelect
     * @throws Exception
     */
    public static void selectFromSelectorWithClick(WebElement selector, String toSelect) throws Exception {
        click(selector);
        Thread.sleep(2000);
        List<WebElement> optionList = TestCaseBase.driver.findElements(By.tagName("option"));
        selectFromDropDownWithClick(optionList, toSelect);
    }

	/**
	 * Objective: Verify the content of dropdown list is correct.
	 * 
	 * @Parameter: List<WebElement> group_Name_Options: A list which including
	 *             all Options
	 * @Parameter: deisredOptionContent: All the content are matched with
	 *             dropdown list
	 * @throws Exception
	 */
	public static void verifyDropDownContent(
			List<WebElement> group_Name_Options, String deisredOptionContent)
			throws Exception {
		// Split String with ","
		String[] contentEach = deisredOptionContent.split(",");
		String optionValue = "";

		// Verify the size of the dropdown list is correct
		int sizeOfdesiredContent = contentEach.length;
		int sizeOfDropDownList = group_Name_Options.size();
		log.info(sizeOfdesiredContent);
		log.info(sizeOfDropDownList);
		customAssertion.assertTrue(sizeOfdesiredContent == sizeOfDropDownList,
				"The dropdown list is less or more than the desired content.");

		// Verify every content of the dropdown list is correct
		for (int i = 0; i < sizeOfDropDownList; i++) {
			optionValue = group_Name_Options.get(i).getText().trim();
			customAssertion
					.assertTrue(
							optionValue.contains(contentEach[i].trim()),
							"The"
									+ contentEach[i]
									+ " content of dropDownList is not match with the desired content: "
									+ optionValue);
		}
	}

	/**
	 * Objective: Verify some content are not list in dropdown list
	 * 
	 * @Parameter: List<WebElement> group_Name_Options: A list which including
	 *             all Options
	 * @Parameter: deisredOptionContent: All the content are not list in
	 *             dropdown list
	 * @throws Exception
	 */
	public static void verifySomeContentNotInDropDownContent(
			List<WebElement> group_Name_Options, String notInDropDownList)
			throws Exception {
		// Split String with ","
		String[] contentEach = notInDropDownList.split(",");
		String eachValueFromDeopDownList = "";

		// Verify the size of the dropdown list is correct
		int sizeOfContentEach = contentEach.length;
		int sizeOfDropDownList = group_Name_Options.size();

		// Verify every content is not display in the drop down list
		for (int i = 0; i < sizeOfContentEach; i++) {

			for (int j = 0; j < sizeOfDropDownList; j++) {

				eachValueFromDeopDownList = group_Name_Options.get(j).getText()
						.trim();
				customAssertion.assertFalse(eachValueFromDeopDownList
						.contains(contentEach[i].trim()), "The"
						+ contentEach[i]
						+ " should not display in the drop down list.");
			}

		}
	}

	/**
	 * Objective: Transfer String to String[]
	 * 
	 * @Parameter: String deisredOptionContent: The String union with ","
	 * @return: String[]
	 * @throws Exception
	 */
	public static String[] transferStringToArray(String deisredOptionContent)
			throws Exception {
		String[] contentEach = deisredOptionContent.split(",");
		return contentEach;
	}

	/**
	 * Objective: get specific window by title of window
	 * 
	 * @author bella.ding
	 * @param title
	 * @return
	 * @throws InterruptedException
	 */

	public static void switchToWindow(int windowIndexNo)
			throws InterruptedException {
		// Initialize parameters
		boolean switchedWindow = false;
		// Get the total size of windows in current application
		int total_Size = TestCaseBase.driver.getWindowHandles()
				.size();
		// Switch to desired window when assign the Valid Window Index
		if (windowIndexNo < total_Size && windowIndexNo >= 0) {
			// Switch to desired window by Index No.
			Object[] windows = TestCaseBase.driver
					.getWindowHandles().toArray();
			TestCaseBase.driver.switchTo()
					.window((String) windows[windowIndexNo]);
			Thread.sleep(1000);
			switchedWindow = true;
		} else {
			customAssertion.assertTrue(switchedWindow,
					"Assigned Window Index Invalid, should be less than:"
							+ total_Size);
		}
	}

	/**
	 * Objective: Verify if element is exist or not
	 * 
	 * @author bella.ding
	 * @param webElement
	 * @return
	 * @throws Exception
	 */
	public static boolean isElementExist(WebElement webElement) {
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
		try {
			webElement.isDisplayed();
			
			return true;
		} catch (NoSuchElementException ex) {

			return false;
		}

	}

	/**
	 * Objective: Get the Flag of desired Element Exist or not Return: boolean:
	 * true, false
	 * 
	 * @throws Exception
	 */
	public static boolean isElementExsitByLocator(String locator) {
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(10, TimeUnit.SECONDS);
		try {
			TestCaseBase.driver.findElement(By.xpath(locator))
					.isDisplayed();
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	/**
	 * Objective: Wait for the Element disappear after an operation
	 * 
	 * @return: none
	 */
	public static void waitForElementDisappear(WebElement element)
			throws Exception {

		boolean isElementPresent = true;
		int loopCount = 0;
		// Waiting for desired element disappear
		while (isElementPresent && loopCount <= 10) {
			Thread.sleep(3000);
			isElementPresent = FunctionUtil.isElementExist(element);
			loopCount = loopCount + 1;
			if (loopCount == 10) {
				log.info("isElementPresent is" + isElementPresent);
				customAssertion.assertFalse(isElementPresent,
						"Application did not navigate to the desired page after clicked the Element:"
								+ element);
			}
		}
	}

	/**
	 * Objective: If alert window displayed then return true, else return false
	 * 
	 * @author bella.ding
	 * @return
	 */
	public static boolean isAlertExist() {
		try {
			TestCaseBase.driver.switchTo().alert();
			return true;
		} catch (Exception ex) {
			return false;
		}

	}

	/**
	 * Objective: Wait for alert window display
	 * 
	 * @author bella.ding
	 * @throws Exception
	 */
	public static void waitForAlertDisplayed() throws Exception {

		// Define Parameters
		int loopCount = 1;

		// Get Alert exist flag
		boolean isAlertExist = isAlertExist();

		// Wait for Alert displayed by tracking every 2 seconds
		while (!isAlertExist && loopCount <= 30) {

			// Track every 2 seconds
			Thread.sleep(2000);

			// Get Alert exist flag again
			isAlertExist = isAlertExist();

			// Increase loopCount
			loopCount = loopCount + 1;

			// Exit Loop when 60 seconds
			if (loopCount > 30) {
				customAssertion.assertTrue(isAlertExist,
						"Alert did not pop up as expected after 60 Seconds");
			}
		}
	}

	/**
	 * Objective: Wait for one element invisible by its style property
	 * Precondition: The Element has the style property
	 * 
	 * @author bella.ding
	 * @param element
	 * @throws Exception
	 */
	public static void waitForElementInvisible(WebElement element)
			throws Exception {

		// Define Parameters
		int loopCount = 0;

		// Get style property for desired object
		String style_Value = element.getAttribute("style");
		boolean isElementInvisible = style_Value.contains("none");

		while (!isElementInvisible && loopCount <= 40) {
			loopCount = loopCount + 1;
			Thread.sleep(2000);
			style_Value = element.getAttribute("style");
			isElementInvisible = style_Value.contains("none");

			if (loopCount == 40) {
				customAssertion.assertTrue(isElementInvisible, "Element:"
						+ element + "did not disappear after 80 seconds");
			}
		}

	}

	/**
	 * Objective: Wait for one element visible by its style property
	 * Precondition: The Element has the style property
	 * 
	 */
	public static void waitForElementVisible(WebElement element)
			throws Exception {

		// Define Parameters
		int loopCount = 0;

		// Get style property for desired object
		String style_Value = element.getAttribute("style");
		boolean isElementInvisible = style_Value.contains("none");

		while (isElementInvisible && loopCount <= 40) {
			loopCount = loopCount + 1;
			Thread.sleep(2000);
			style_Value = element.getAttribute("style");
			isElementInvisible = style_Value.contains("none");

			if (loopCount == 40) {
				customAssertion.assertTrue(isElementInvisible, "Element:"
						+ element + "did not appear after 80 seconds");
			}
		}

	}

	/**
	 * Objective: Get the numbers from desired String
	 * 
	 * @throws Exception
	 */
	static public String getNumbersFrmDesiredString(String desiredStringContent) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(desiredStringContent);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	/**
	 * Objective: Wait for content displayed for desired object
	 * 
	 * @author bella.ding
	 * @param elements
	 * @param elementNum
	 *            : wait if element number is less than elementNum
	 * @throws Exception
	 */
	public static void waitForElementsBySize(List<WebElement> elements,
			int elementNum) throws Exception {
		int timeout = 0;
		Thread.sleep(1000);
		int divNum = elements.size();
		while (timeout < MAX_WAIT_FOR_ELEMENT) {
			divNum = elements.size();
			if (divNum < elementNum) {
				Thread.sleep(1000);
				timeout++;
			} else if (divNum > elementNum) {
				log.error("Warning!! Size of the element is more than expected.");
				return;
			} else {
				return;
			}

		}

		if (timeout == MAX_WAIT_FOR_ELEMENT && divNum < elementNum) {
			customAssertion.assertTrue(false, "After 30 seconds, size of the "
					+ elements + " is still less than expected. ");
		}

	}

	/**
	 * @Description: Find the selected value in drop down
	 * @author bella.ding
	 * @param elements
	 * @return selected value in drop down
	 */
	public static String getSelectedValueFromDropdown(List<WebElement> elements) {
		String selectedValue = null;
		for (int i = 0; i < elements.size(); i++) {
			boolean isSelected = elements.get(i).isSelected();
			if (isSelected) {
				selectedValue = elements.get(i).getText();
				break;
			}
		}
		return selectedValue.trim();
	}

	/**
	 * Objective:Waiting for the Element be displayed by Loop when it did not
	 * exist first Return:
	 * 
	 * @throws Exception
	 */
	public static void waitForElementExist(WebElement element) throws Exception {
		boolean elementIsExsit = isElementExist(element);
		int loopCount = 0;
		while (!elementIsExsit && loopCount <= 10) {
			Thread.sleep(3000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExist(element);
			if (loopCount == 10) {
				customAssertion.assertTrue(elementIsExsit,
						"Element does not exist after waiting for 2 minutes: Element Text:"
								+ element.getText() + "Element Value:"
								+ element.getAttribute("Value"));
			}
		}
	}

	/**
	 * Objective:Waiting for the Element be displayed by Loop when it did not
	 * exist first Return:
	 * 
	 * @throws Exception
	 */
	public static void waitForElementExist(WebElement element, String remindMsg)
			throws Exception {
		boolean elementIsExsit = isElementExist(element);
		int loopCount = 0;
		while (!elementIsExsit && loopCount <= 10) {
			Thread.sleep(2000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExist(element);
			if (loopCount == 10) {

				log.info(remindMsg);
				customAssertion.assertTrue(elementIsExsit,
						"Element is not exist after waiting for 2 minutes: Element Text:"
								+ element.getText() + "Element Value:"
								+ element.getAttribute("Value"));
			}
		}
	}

	/**
	 * Objective:Waiting for the Element be displayed by WebDriverWait
	 * 
	 * @throws Exception
	 */
	public static void waitForElementExistTest(WebElement element)
			throws Exception {

		WebDriverWait waitforelement = new WebDriverWait(TestCaseBase.driver,
				120);
		waitforelement.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * Objective: Wait for the Element disappear after an operation
	 * 
	 * @return: none
	 */
	public static void waitForElementDisappear(String locator) throws Exception {

		WebDriverWait waitforelement = new WebDriverWait(TestCaseBase.driver,
				60);
		waitforelement.until(ExpectedConditions.invisibilityOfElementLocated(By
				.xpath(locator)));

	}

	/**
	 * Objective:Waiting for the Element be displayed by Loop when it did not
	 * exist first Return:
	 * 
	 * @throws Exception
	 */
	public static void waitForElementExistByLocator(String xpath)
			throws Exception {
		boolean elementIsExsit = isElementExsitByLocator(xpath);
		int loopCount = 0;
		while (!elementIsExsit && loopCount <= 30) {
			Thread.sleep(2000);
			loopCount = loopCount + 1;
			elementIsExsit = isElementExsitByLocator(xpath);
			if (loopCount == 30) {
				customAssertion.assertTrue(elementIsExsit,
						"Element is not exist after waiting for 6 minutes: Element xpath: "
								+ xpath);
			}
		}

	}

	/**
	 * @Description: Get check box has been checked or not
	 * @return: boolean, true: Checked, false: did not checked
	 */
	public static boolean getCheckedBoxCheckedOrNot(WebElement checkBoxElement) {

		boolean isChecked = false;
		String checkedProperty = "";
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(40, TimeUnit.SECONDS);
		try {
			checkedProperty = checkBoxElement.getAttribute("checked").trim();
			if (checkedProperty.equalsIgnoreCase("true")) {
				isChecked = true;
			}
		} catch (NullPointerException ex) {
			isChecked = false;
		}
		return isChecked;
	}

	/**
	 * @Description: Get check box has been checked or not
	 * @return: boolean, true: Checked, false: did not checked
	 */
	public static boolean getSelectedOrNot(WebElement selectBoxElement) {

		boolean isChecked = false;
		String checkedProperty = "";
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(40, TimeUnit.SECONDS);
		try {
			checkedProperty = selectBoxElement.getAttribute("selected").trim();
			if (checkedProperty.equalsIgnoreCase("selected")) {
				isChecked = true;
			}
		} catch (NullPointerException ex) {
			isChecked = false;
		}
		return isChecked;
	}

	/**
	 * @Description: Get desired WebElement can be edited or not
	 * @return: boolean, true: Can be Edited, false: Cannot be edited
	 */
	public static boolean getEditablePropertyForDesiredWebElement(
			WebElement desiredElement) throws Exception {
		waitForElementExist(desiredElement);
		boolean isEditable = false;
		String readOnlyProperty = "";
		TestCaseBase.driver.manage().timeouts()
				.implicitlyWait(40, TimeUnit.SECONDS);
		try {
			readOnlyProperty = desiredElement.getAttribute("readonly").trim();
			if (readOnlyProperty.equalsIgnoreCase("true")) {
				isEditable = false;
			}
		} catch (NullPointerException ex) {
			isEditable = true;
		}
		return isEditable;
	}

	/**
	 * @Description: Get Option's text which has been checked already
	 * @return: Option's Text
	 */
	public static String getCheckedOptionFrmDropDown(
			List<WebElement> dropDownList) {

		String checkedOption = "";
		String checkedProperty = "";
		boolean isChecked = false;

		// Assert the Error when the drop down is Empty
		customAssertion.assertFalse(dropDownList.isEmpty(),
				"Desired Drop down is Empty");

		// Get the Option which has been checked
		for (int i = 0; i < dropDownList.size(); i++) {
			try {
				checkedProperty = dropDownList.get(i).getAttribute("checked")
						.trim();
				if (checkedProperty.equalsIgnoreCase("true")) {
					checkedOption = dropDownList.get(i).getText();
					isChecked = true;
					break;
				}
			} catch (NullPointerException ex) {
				isChecked = false;
			}
		}

		if (isChecked != true) {
			checkedOption = "There is no Optoin has been checked";
		}

		return checkedOption;
	}

	/**
	 * Objective: Clear all files under desired Folder
	 * 
	 * @param desiredFilePath
	 *            : Known folder Path
	 * @throws Exception
	 */
	static public void clearFolderFiles(String desiredFolderPath)
			throws Exception {

		File directory = new File(desiredFolderPath);

		// Get all files in directory
		File[] files = directory.listFiles();
		for (File file : files) {
			// Delete each file
			if (!file.delete()) {

			}
		}

	}

	/**
	 * Objective: Clear certain files under desired Folder
	 * 
	 * @param desiredFilePath
	 *            : Known folder Path
	 * @throws Exception
	 */
	static public void clearCertainFileUnderFolder(String desiredFolderPath)
			throws Exception {

		File downloadFile = new File(desiredFolderPath);
		if (downloadFile.exists()) {
			downloadFile.delete();
		}
	}

	/**
	 * Objective: Wait for certain files completely
	 * 
	 * @param desiredFilePath
	 *            : Known folder Path
	 * @throws Exception
	 */
	static public void waitCertainFileDownloadCompletely(
			String desiredFolderPath) throws Exception {

		File downloadFile = new File(desiredFolderPath);
		boolean isThefileExist = false;
		int loopCount = 0;
		while (!isThefileExist && loopCount < 10) {

			Thread.sleep(5000);
			loopCount++;
			isThefileExist = downloadFile.exists();

		}

		customAssertion.assertTrue(isThefileExist,
				"The file is not download well after 50 seconds.");
	}

	/**
	 * Objective: Read desired CSV File content and save it into ArrayList
	 * 
	 * @param csvFile
	 *            : desired CSV File exact path with file name
	 * @return: ArrayList
	 * @throws Exception
	 */
	static public List<String> readCSVFile(String csvFile) throws Exception {

		// create BufferedReader to read CSV file
		BufferedReader buffereReader = new BufferedReader(new FileReader(
				DOWNLOAD_DIR+csvFile));
		String line = "";
		int lineNumber = 0;
		int tokenNumber = 0;
		StringTokenizer st = null;
		List<String> allCountArrayList = new ArrayList<String>();
		// read comma separated file Row by Row
		while ((line = buffereReader.readLine()) != null) {

			lineNumber = lineNumber + 1;

			// use comma as token separator
			st = new StringTokenizer(line, ",");

			while (st.hasMoreTokens()) {
				tokenNumber = tokenNumber + 1;

				// Save the each cell value:
				allCountArrayList.add(st.nextToken());
			}

			// reset token number
			tokenNumber = 0;
			
		}

		buffereReader.close();
		
		for (int i = 0; i < allCountArrayList.size(); i++) {
			System.out.print(allCountArrayList.get(i)+"  |  ");
			if (i % 14 ==0) System.out.println();
		}
		
		return allCountArrayList;

	}

	/**
	 * Objective: Get the file name by the path
	 * 
	 * @param desiredFilePath
	 *            : Known path
	 * @return Exact File name
	 * @throws Exception
	 */
	static public List<String> getFileName(String desiredFilePath)
			throws Exception {
		List<String> results = new ArrayList<String>();
		File[] files = new File(desiredFilePath).listFiles();

		customAssertion.assertTrue(files.length != 0,
				"There is no File under the desired Folder:" + desiredFilePath);

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	/**
	 * Objective: wait For Page load complete
	 * 
	 * @throws InterruptedException
	 * 
	 * @throws Exception
	 */
	public static void waitForPageLoadComplete() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				200000);
		wait.until(isPageLoaded());

	}

	/**
	 * Objective: Verify Page load completed or not
	 * 
	 * @return Function<WebDriver, Boolean>
	 * @throws Exception
	 */
	public static Function<WebDriver, Boolean> isPageLoaded() {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) TestCaseBase.driver)
						.executeScript("return document.readyState").equals(
								"complete");
			}
		};
	}

	/**
	 * Objective: get the exact Year, Month, Day from a Date
	 * 
	 * @throws Exception
	 */
	public static List<Integer> getExactYearMonthDayFrmDateType()
			throws Exception {

		// Define Parameters
		List<Integer> exactYearMonthDayList = new ArrayList<Integer>();

		// Get current Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();

		// Covert desired date string to Date type
		SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
		Date desiredDate = sd.parse(dateFormat.format(date).toString());

		// Define Calendar
		Calendar cal = Calendar.getInstance();
		cal.setTime(desiredDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		// Save the result
		exactYearMonthDayList.add(year);
		exactYearMonthDayList.add(month);
		exactYearMonthDayList.add(day);

		// return result
		return exactYearMonthDayList;
	}

	// by shaine
	public static String getRequiredDate() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		String requestDateOfToday = sd.format(date);
		return requestDateOfToday;
	}

	public static String getDateInFormate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("MMMMM dd, yyyy", Locale.ENGLISH);
		String time = df.format(date);
		System.out.println(time);
		return time;
	}

	/**
	 * get current time and display in format- September 19, 2016
	 * 
	 * @author jenny.sun
	 **/
	public static String getTimeInFormat() {
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		log.info("System Date1: " + dateFormat.format(cal.getTime()));
		return dateFormat.format(cal.getTime());

	}

	/**
	 * get current time
	 * 
	 * @author abby.zhang
	 **/
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyyHH:mm:ss");
		String currentTime = sd.format(date);
		return currentTime;
	}

	/**
	 * get the date that a few days before today
	 * 
	 * @author abby.zhang
	 * @param day
	 *            : positive number eg. day = 7. If you input in a navigate
	 *            number it will get a few days after today
	 **/

	public static String getDateBeforeToday(int day) {
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -day);
		Date date = calendar.getTime();
		String fewDaysAgo = sd.format(date);
		return fewDaysAgo;
	}

	/**
	 * Objective: Quit the browser
	 * 
	 * @throws Exception
	 */
	public static void quitDriver() throws Exception {
		TestCaseBase.driver.quit();
		SystemUtil.driverKiller();
	}

	/**
	 * Objective: Mouse Over desired Element
	 */
	public static void mouseOverElement(WebElement desiredElement)
			throws Exception {

		// Move Hover to desired Element
		Actions builder = new Actions(TestCaseBase.driver);
		builder.moveToElement(desiredElement).build().perform();

	}

	/**
	 * Objective: Mouse Over desired Element
	 */
	public static void moveToElement(WebElement desiredElement)
			throws Exception {

		// Move Hover to desired Element
		Actions builder = new Actions(TestCaseBase.driver);
		builder.moveToElement(desiredElement).moveByOffset(20, 3).build()
				.perform();

	}

	/**
	 * Objective: Switch to certain window by window title
	 */
	public static boolean switchToWindow(String windowTitle) {
		boolean flag = false;
		try {
			String currentHandle = TestCaseBase.driver.getWindowHandle();
			Set<String> handles = TestCaseBase.driver.getWindowHandles();
			for (String s : handles) {
				if (s.equals(currentHandle)) {
					continue;
				} else {
					TestCaseBase.driver.switchTo().window(s);
					if (TestCaseBase.driver.getTitle().contains(windowTitle)) {
						flag = true;
						log.info("Switch to window: " + windowTitle
								+ " successfully!");
						break;
					} else {
						continue;
					}
				}
			}
		} catch (NoSuchWindowException e) {
			log.error("Window: " + windowTitle + " cound not found!");
			flag = false;
		}
		return flag;
	}

	/**
	 * click the element by JS
	 * 
	 * @author mandy.chen
	 * @param element
	 * @throws Exception
	 */
	public static void clickByJavaScripts(WebElement element) throws Exception {
		FunctionUtil.waitForElementExist(element);
		FunctionUtil.waitForElementExist(element);

		((JavascriptExecutor) TestCaseBase.driver).executeScript(
				"arguments[0].click();", element);
		Thread.sleep(1000);

	}

	/**
	 * Scroll down browser to element.
	 * 
	 * @author Jenny.Sun
	 * @param element
	 * @throws Exception
	 */
	public static void scrollToElement(WebElement element) {
		// ((JavascriptExecutor) driver).executeScript( //when run TC2006 on
		// Firefox with scrollIntoViewIfNeeded
		// "arguments[0].scrollIntoViewIfNeeded(true)", element); //function
		// ,TC2006 will failed, but use scrollIntoView will pass
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView()", element);
	}

	public static void scrollToBottom() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public static void scrollToTop() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,0)", "");
	}

	/**
	 * click the element by Actions
	 * 
	 * @param element
	 * @throws Exception
	 */
	public static void clickByAction(WebElement element) throws Exception {
		Actions actions = new Actions(TestCaseBase.driver);
		mouseOverElement(element);
		Thread.sleep(2000);
		actions.click(element).build().perform();
	}

	/**
	 * Get a random int value from 0 to (randomNumber-1)
	 * 
	 * @param element
	 * @throws Exception
	 */
	public static int getRandomIntValue(int randomNumber) throws Exception {

		int randomInt = (int) (Math.random() * randomNumber);
		return randomInt;

	}

	/**
	 * Verify the order function works , verify the List is in alphabetical
	 * order
	 * 
	 * @param webElement
	 * @param j
	 *            is the first element of the list you want to start
	 *            customAssertion.eg. j=0 it will assert all element in the
	 *            Array list j=1 the array list will start from webElement[1],so
	 *            the first element of the list will be skipped.
	 * @throws Exception
	 * @author abby.zhang
	 */

	public static void verifyOrderCorrectly(List<WebElement> webElement, int j, String listName,
			final Boolean xec) throws Exception {

		List<String> listOfText = new ArrayList<String>();
		List<String> listOfText1 = new ArrayList<String>();

		int sizeOfText = webElement.size();
		customAssertion.assertTrue(sizeOfText != 0,
				"There is nothing to order");

		// store the content of list to ArrayLiat
		for (int i = j; i < sizeOfText; i++) {
			String contentOfText = webElement.get(i).getText().trim();
			listOfText.add(contentOfText);
			listOfText1.add(contentOfText);
		}
		log.info(listOfText1 + "The original array list");
		// Sort the ArrayList
		Collections.sort(listOfText, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (xec) {
					return o1.compareToIgnoreCase(o2);
				} else {
					return o2.compareToIgnoreCase(o1);
				}
			}
		});

		// Save the order ArrayList
		ArrayList<String> listOfTextOrder = new ArrayList<String>(listOfText);
		log.info(listOfTextOrder
				+ "The alphabetical order array list after sort");
		// See if the order function works
		customAssertion.assertTrue(listOfText1.equals(listOfTextOrder),
				"The order for "+ listName +" is incorrect.");

	}
	
    
    /**
     * Verify the order of date is correct.
     * @author herby.he
     * @param webElement
     * @param index (Starting from this value)
     * @param listName
     * @param xec (true means ascending order, false means descending order.)
     */
    
    public static void verifyDateOrdercorrectly(List<WebElement> webElement, int index, String listName, final Boolean xec ) {
    	List<Date> origalList= new ArrayList<Date>();
    	
    	for(int i = 0; i<webElement.size();i++) {
    		Date date = new Date();
    		
    		//Filter out empty component.
    		if(webElement.get(i).getText().trim().length()>4) {
    			date = tryAllConvertStringToDate(webElement.get(i).getText().trim()); 
    			origalList.add(date);
    			
    		}
    	  
    	}
    	
    	for(int j =0;j<origalList.size()-1;j++) {
    		int result = origalList.get(j).compareTo(origalList.get(j+1));
    		if(xec) {
    			if(result==1) {
    				customAssertion.assertTrue(false,"The order for "+listName+" is incorrect." + j);
    				break;
    			}

    		}else {
    			if(result==-1) {
    				customAssertion.assertTrue(false,"The order for "+listName+" is incorrect." + j);
    				break;
    			}
				
			}
    		
    		
    	}
    	
	}
	

	/**
	 * Get the file from desired path
	 * 
	 * @param String
	 *            fileName
	 * @throws Exception
	 */
	public static List<String> getFileData(String fileName) throws Exception {

		ArrayList<String> dataList = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String tempString = null;

		// Get the date from file one line by one line
		while ((tempString = reader.readLine()) != null) {
			dataList.add(tempString);
		}

		// * remove headers on first line
		// */
		dataList.remove(0);
		reader.close();

		for (int i = 0; i < dataList.size(); i++) {
			log.info(dataList.get(i));
		}
		return dataList;

	}

	/**
	 * In this method, headers on first line in the file is not removed *
	 */
	public static List<String> getFileData02(String fileName) throws Exception {

		ArrayList<String> dataList = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String tempString = null;

		// Get the date from file one line by one line
		while ((tempString = reader.readLine()) != null) {
			dataList.add(tempString);
		}
		for (int i = 0; i < dataList.size(); i++) {
			log.info(dataList.get(i));
		}
		return dataList;

	}

	public static List<String> getFileData01(String fileName) throws Exception {

		ArrayList<String> dataList = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String tempString = null;

		// Get the date from file one line by one line
		while ((tempString = reader.readLine()) != null) {
			dataList.add(tempString);
		}

		// reader.close();

		for (int i = 0; i < dataList.size(); i++) {
			log.info(dataList.get(i));
		}
		return dataList;

	}

	/*
	 * Modified by Tripp 2015/08/10 get the value between the from valueFromText
	 * between String A and String B
	 * 
	 * @author Xin.Fang
	 */
	public static String getTheValueBetweenStringAAndB(String valueFromText,
			String a, String b) {
		String regex = String.format("([\\s\\S]*)%s([\\s\\S]*)%s([\\s\\S]*)",
				a, b);
		Matcher m = Pattern.compile(regex).matcher(valueFromText);
		m.find();
		return m.group(2);
	}

	/**
	 * Objective: Write data into the CSV file
	 * 
	 * @throws Exception
	 */
	public static void writerCsv(String csvFilePath, String[][] data) {

		CsvWriter writer = null;
		try {
			writer = new CsvWriter(csvFilePath);

			for (String[] element : data) {
				writer.writeRecord(element);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

	/**
	 * Wrap of find by xpath action
	 * 
	 * @param xpath
	 * @return
	 * @author tripp.hu
	 */
	public static WebElement find(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public static List<WebElement> findAll(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public static WebElement find(String format, Object... args) {
		return find(String.format(format, args));
	}

	public static List<WebElement> findAll(String format, Object... args) {
		return findAll(String.format(format, args));
	}

	/**
	 * Objective: Verify if element is exist or not
	 * 
	 * @author tripp.hu
	 * @param webElement
	 * @return
	 * @throws Exception
	 */
	public static boolean isElementExist(String xpath) {
		try {
			waitImplicitly(10);
			return find(xpath).isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		} catch (StaleElementReferenceException ex) {
			return false;
		} catch (UnhandledAlertException e) {
			Alert alert = TestCaseBase.driver.switchTo().alert();
			// String alertText = alert.getText().trim();
			// log.info("Alert data: "+ e.getMessage());
			alert.accept();
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Verify a keyword existing in every string item of a Webelement list
	 * 
	 * @author matthew.feng
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keyword
	 *            : the keyword that should exist in every item of elementList.
	 */
	public static boolean verifyKeywordExisting(String keyword,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			//log.info("element text is:" + elementList.get(i).getText());
			if (!elementList.get(i).getText().toLowerCase()
					.contains(keyword.toLowerCase())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify items in the array existing in every string item of a Webelement
	 * list
	 * 
	 * @author jenny.sun
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keywords
	 *            : array that should exist in every item of elementList.
	 */

	public static boolean verifyArrayListExisting(String[] keywords,
			List<WebElement> elementList) {
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			if (findAKeywordInList(keyword, elementList) == -1) {
				log.info("keyword" + (i + 1) + "is not in the list.");
				return false;
			}
		}
		return true;

	}

	/**
	 * Verify each string item of WebElement list is included in a word
	 * 
	 * @author matthew.feng
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keyword
	 *            : the word that include in every string item of a web
	 *            elementList.
	 */
	public static boolean verifyWordIncludingEach(String word,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			if (!word.toLowerCase().contains(
					elementList.get(i).getText().toLowerCase())) {
				return false;

			}
		}
		return true;
	}

	/**
	 * Verify the values of a string list are same to the text of a WebElement
	 * list
	 * 
	 * @author matthew.feng
	 * @param list1
	 *            : string list
	 * @param list2
	 *            : WebElement list
	 * @return
	 */
	public static boolean compareTwoStringLists(List<String> list1, List<String> list2) {
		log.info("String: " + list1.size());
		log.info("WebElement: " + list2.size());
		if (list1.size() != list2.size()) {
			log.info("The size of two lists are not same");
			return false;
		}
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).trim().equalsIgnoreCase(list2.get(i).trim())) {
				log.info(i + "th WebElement Text= "+ list2.get(i).trim());
				return false;
			}
		}
		return true;
	}

	/**
	 * Verify the text of a WebElement list contains all the strings in a string
	 * list
	 * 
	 * @author matthew.feng
	 * @param list1
	 *            : string list
	 * @param list2
	 *            : WebElement list
	 * @return
	 */
	public static boolean verifyWebListTextContainsToStringList(
			List<String> list1, List<WebElement> list2) {
		boolean isTrue;
		for (int i = 0; i < list1.size(); i++) {
			isTrue = false;
			for (int j = 0; j < list2.size(); j++) {
				if (list1.get(i).equalsIgnoreCase(list2.get(j).getText())) {
					isTrue = true;
					break;
				}
			}
			if (isTrue = false) {
				return isTrue;
			}
		}
		return true;
	}

	/**
	 * Verify a keyword existing in any one item of a string list
	 * 
	 * @author matthew.feng
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keyword
	 *            : the keyword that should exist in every item of elementList.
	 */
	public static boolean verifyKeywordExistingOnce(String keyword,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			// log.info("Actual String: "
			// + elementList.get(i).getText().toLowerCase());
			// log.info("keyword: " + keyword.toLowerCase());
			if (containsSubstring(elementList.get(i).getText().toLowerCase(),
					keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public static boolean verifyKeywordExistingOnce1(String keyword,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			// log.info("Actual String: "
			// + elementList.get(i).getAttribute("value").trim().toLowerCase());
			// log.info("keyword: " + keyword.toLowerCase());
			if (containsSubstring(elementList.get(i).getAttribute("value")
					.trim().toLowerCase(), keyword.trim().toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Find a keyword existing in a string list
	 * 
	 * @author matthew.feng
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keyword
	 *            : the keyword that should exist in every item of elementList.
	 */
	public static int findAKeywordInList(String keyword,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).getText().toLowerCase().trim()
					.contains(keyword.toLowerCase().trim())) {
				return i;
			}
		}
		return -1;
	}
	

	/**
	 * Find entire keyword existing in a string list
	 * 
	 * @author Shaine.gu
	 * @param elementList
	 *            : the WebElement list to be verified
	 * @param keyword
	 *            entire keyword
	 */
	public static int findEntireKeywordInList(String keyword,
			List<WebElement> elementList) {
		for (int i = 0; i < elementList.size(); i++) {
			if (elementList.get(i).getText().trim().toLowerCase()
					.equalsIgnoreCase(keyword.trim().toLowerCase())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * date transfer to string (flag=0,actual date,flag=1,Aurora date)
	 * 
	 * @author shirley.xia
	 * @param Date
	 * @return flag
	 * @throws Exception
	 */

	public static String transferDate(Date date, int flag) {
		if (flag == 1) {
			Date today = new Date();
			SimpleDateFormat format = new SimpleDateFormat("HH");
			try {
				int hour = Integer.parseInt(format.format(today));
				if (hour - 13 < 0) {
					date = new Date(date.getTime() + (long) 24 * 60 * 60 * 1000);
				}

			} catch (NullPointerException e) {
				log.info("转换有误");
			}

		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * replace all the , with empty ""; replace $ with empty "" once
	 * 
	 * @author matthew.feng
	 * @param string
	 * @return str
	 * @throws Exception
	 */
	public static String replaceCommaAndDollarSign(String str) throws Exception {
		str = str.replaceAll(",", "");
		str = str.replace("$", "");
		return str;

	}

	/**
	 * 
	 * @author shaine.gu
	 */
	public static void verifyElementsAreEnabled(List<WebElement> lists) {
		for (WebElement element : lists) {
			element.isEnabled();
		}

	}

	/**
	 * wait for a list of webElements displayed
	 * 
	 * @author shaine.gu
	 * @throws Exception
	 */
	public static void waitForListOfElementsExsit(final List<WebElement> lists)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				30);
		wait.until(new ExpectedCondition<List<WebElement>>() {
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return lists.size() > 0 ? lists : null;
			}
		});

	}

	/**
	 * wait for a list of webElements displayed
	 * 
	 * @author shaine.gu
	 * @throws Exception
	 */
	public static void waitForListOfElementsVisible(List<WebElement> lists)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				30);
		wait.until(ExpectedConditions.visibilityOfAllElements(lists));

	}

	public static void waitForListOfElementsClickable(WebElement webElement)
			throws Exception {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				30);
		wait.until(ExpectedConditions.elementToBeClickable(webElement));

	}


	/**
	 * @author shaine.gu
	 * @throws Exception
	 */
	public static String selectRandomItemFromDropDownList(List<WebElement> list)
			throws Exception {
		waitForElementsBySize(list, list.size());
		// Get the size of list
		int size = list.size();
		// Get an valid i
		int i = (int) (1 + Math.random() * (size - 1));
		// Select the certain item
		String str = list.get(i).getText();
		click(list.get(i));
		return str;
	}

	/**
	 * get Number in the beginning of a String i is about length
	 * 
	 * @author shaine.gu
	 * @param list
	 */
	public static int getNumInString(List<WebElement> list, int i) {
		List<String> ss = new ArrayList<String>();
		int total = 0;
		for (WebElement element : list) {
			String s = element.getText().substring(0, i);
			for (String sss : s.replaceAll("[^0-9]", ",").split(",")) {
				if (sss.length() > 0) {
					ss.add(sss);
					total += Integer.parseInt(sss);
				}
			}
		}
		return total;
	}

	/**
	 * get value From a WebElement List
	 * 
	 * @author shaine.gu
	 */
	public static List<String> getValuesFromWebElementList(List<WebElement> list) {
		List<String> strList = new ArrayList<String>();
		for (WebElement element : list) {
			strList.add(element.getText());
		}
		return strList;

	}

	/**
	 * get Part Numbers From a WebElement List
	 * 
	 * @author jenny.sun
	 */
	public static List<String> getPartNumbersFromWebElementList(
			List<WebElement> list) {
		List<String> strList = new ArrayList<String>();
		for (WebElement element : list) {
			String temp = element.getText();//.split(": ")[1]
			strList.add(temp);
		}
		return strList;

	}

	/**
	 * get value From a WebElement List
	 * 
	 * @author shaine.gu
	 */
	public static List<String> getValuesFromInputBoxWebElementList(
			List<WebElement> list) {
		List<String> strList = new ArrayList<String>();
		for (WebElement element : list) {
			strList.add(element.getAttribute("value").trim());
		}
		return strList;

	}

	/**
	 * Verify numeric string is in format:XXXX-XXXXX
	 * 
	 * @author Jenny.sun
	 */
	public final static boolean isNumeric(String s) {
		if (s != null && !"".equals(s.trim())) {
			return s.matches("^[0-9]*\\-[0-9]*$");
		} else {
			return false;
		}
	}

	/**
	 * @author shaine.gu getSelectedOption
	 * 
	 *         return the index of selected option
	 */
	public static int getSelectedOption(List<WebElement> list) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAttribute("selected") != null) {
				return i;
			}
		}

		return -1;

	}

	/**
	 * wait util text present in element
	 * 
	 * @author shaine.gu
	 * @param element
	 * @param timeout
	 * @param text
	 */
	public static void untilTextToBePresentInElementValue(WebElement element,
			int timeout, String text) {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				timeout);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element,
				text));
	}

	/**
	 * click one of the item if it contains certain keywords
	 * 
	 * @throws Exception
	 * @author shaine.gu
	 * 
	 */
	public static void clickItemByKeywords(List<WebElement> list, String keyword)
			throws Exception {
		waitForListOfElementsExsit(list);
		Thread.sleep(5000);
		for (WebElement element : list) {
			if (element.getText().trim().contains(keyword)) {
				clickByAction(element);
				break;
			}
		}
		// waitForPageLoadComplete();
	}

	/**
	 * wait for List<WebElement> clickable in 30 seconds
	 * 
	 * @throws Exception
	 * @author breeze.jiao
	 * @param List
	 *            <WebElement>
	 */
	public static void waitForListOfElementsClickable(List<WebElement> lists)
			throws Exception {
		waitForListOfElementsVisible(lists);
		for (WebElement element : lists) {
			untilElementClickable(element, 30);
		}
	}

	/**
	 * wait for List<WebElement> disapear in 30 seconds
	 * 
	 * @throws Exception
	 * @author shaine.gu
	 * @param List
	 *            <WebElement>
	 */
	public static void waitForListOfElementsDisappear(List<WebElement> lists)
			throws Exception {
		waitForListOfElementsExsit(lists);
		for (WebElement element : lists) {
			waitForElementDisappear(element);
		}
	}

	/**
	 * remove repetitive elements in a List(of any kinds)
	 * 
	 * @author shaine.gu
	 */
	public static HashSet<T> removeRepetitiveElementsInAList(List<T> list) {

		HashSet<T> hs = new HashSet<T>(list);
		return hs;

	}

	/**
	 * @author jenny.sun
	 * @throws Exception
	 * @Objective: get text info from list and put them into array
	 * @param list
	 */
	public static String[] getListInfo(List<WebElement> list) {
		String[] temp = new String[64];
		for (int i = 0; i < list.size(); i++) {
			temp[i] = list.get(i).getText();

		}
		return temp;

	}

	/**
	 * wait until Element's part of the attribute value appeared
	 * 
	 * @author shaine.gu
	 */
	public static void waitForAttributeValueAppear(final String attributeName,
			final String attributeValue, final WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {

					final String elementText = element
							.getAttribute(attributeName);
					if (elementText != null) {
						return elementText.contains(attributeValue);
					} else {
						return false;
					}
				} catch (final StaleElementReferenceException e) {
					return null;
				}
			}
		});
	}

	/**
	 * amy.hu delete all of the files or folders under the temp folder
	 * 
	 * @param tempDir
	 *            the folder want to delete
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	static public void deleteDir(File tempDir) {
		if (tempDir.isDirectory()) {
			String[] children = tempDir.list();
			// delete all of the files or folders under the temp folder
			for (int i = 0; i < children.length; i++) {
				deleteDir(new File(tempDir, children[i]));
			}
		}
		tempDir.delete();
	}

	/**
	 * wait for text to be present in element
	 * 
	 * @author shaine.gu
	 */
	public static void utilTextToBePresentInElement(WebElement element,
			String text, int timeout) {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				timeout);
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	/**
	 * 
	 * wait for list of element with text disappear
	 * 
	 * @author shaine.gu
	 */
	public static void waitForListOfElementWithTextDisappear(
			final List<WebElement> list, final String text, int seconds) {
		WebDriverWait wait = new WebDriverWait(TestCaseBase.driver,
				seconds);
		for (final WebElement element : list) {
			wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return !element.getText().equals(text);
					} catch (NoSuchElementException e) {
						// Returns true because the element with text is not
						// present in DOM. The
						// try block checks if the element is present but is
						// invisible.
						return true;
					} catch (StaleElementReferenceException e) {
						// Returns true because stale element reference implies
						// that element
						// is no longer visible.
						return true;
					}
				}

			});
		}
	}

	/**
	 * Objective: Convert the String value to Date type value Parameter:
	 * 
	 * @caseNo: The date format of desiredString should match with one case in
	 *          this function.
	 */
	public static Date convertStringToDateType(String desriedString, int caseNO)
			throws Exception {

		// Define the Date Objects and SimpleDateFormat
		DateFormat formatter1;
		DateFormat formatter2;
		DateFormat formatter3;
		DateFormat formatter4;
		DateFormat formatter5;
		DateFormat formatter6;
		DateFormat formatter7;
		DateFormat formatter8;
		DateFormat formatter9;
		DateFormat formatter10;
		DateFormat formatter11;
		DateFormat formatter12;
		DateFormat formatter13;
		DateFormat formatter14;

		formatter1 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		formatter2 = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy",
				Locale.ENGLISH);
		formatter3 = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
		formatter4 = new SimpleDateFormat("dd-MMM-yy\nHH:mm:ss a",
				Locale.ENGLISH);
		formatter5 = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
		formatter6 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		formatter7 = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		formatter8 = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.ENGLISH);
		formatter9 = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
		formatter10 = new SimpleDateFormat("MM yyyy", Locale.ENGLISH);
		formatter11 = new SimpleDateFormat("EEEEE, MMM dd, yyyy",
				Locale.ENGLISH);
		formatter12 = new SimpleDateFormat("dd-MMM-yy\nHH:mm:ss a",
				Locale.ENGLISH);
		formatter13 = new SimpleDateFormat("MM/dd/yyyy hh:mm aa",Locale.ENGLISH);
		formatter14 = new SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.ENGLISH);

		// Define a Date parameter to date the value which has been converted
		// from String to Date type
		Date desiredDateValue = null;

		// Convert
		switch (caseNO) {
		case 1:
			desiredDateValue = formatter1.parse(desriedString);
			break;
		case 2:
			desiredDateValue = formatter2.parse(desriedString);
			break;
		case 3:
			desiredDateValue = formatter3.parse(desriedString);
			break;
		case 4:
			desiredDateValue = formatter4.parse(desriedString);
			break;
		case 5:
			desiredDateValue = formatter5.parse(desriedString);
			break;
		case 6:
			desiredDateValue = formatter6.parse(desriedString);
			break;
		case 7:
			desiredDateValue = formatter7.parse(desriedString);
			break;
		case 8:
			desiredDateValue = formatter8.parse(desriedString);
			break;
		case 9:
			desiredDateValue = formatter9.parse(desriedString);
			break;
		case 10:
			desiredDateValue = formatter10.parse(desriedString);
			break;
		case 11:
			desiredDateValue = formatter11.parse(desriedString);
			break;
		case 12:
			desiredDateValue = formatter12.parse(desriedString);
			break;
			
		case 13:
			desiredDateValue = formatter13.parse(desriedString);
			break;	
			
		case 14:
			desiredDateValue = formatter14.parse(desriedString);
			break;	
		}

		// Return the value
		return desiredDateValue;

	}

	/**
	 * Validate list of dates all in 2 certain dates
	 * 
	 * @param List
	 *            <WebElement> date list, String start date, String end date
	 *            (NOTE: please follow "MM/dd/yyyy" format when passing param)
	 * @return null
	 * @author breeze.jiao
	 * @throws ParseException
	 */
	public static void verifyListOfDateInCertainPeriod(
			List<WebElement> dateList, String start, String end)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		List<String> list = getValuesFromWebElementList(dateList);

		Date date1 = sdf.parse(start);
		Date date2 = sdf.parse(end);
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		Date date = new Date();
		for (String tempDate : list) {
			date = tryAllConvertStringToDate(tempDate);
			long time = date.getTime();
			customAssertion.assertTrue(time > time1 && time < time2);
		}
	}

	/**
	 * verify list contains certain values
	 * 
	 * @shaine
	 */
	public static boolean verifyContentExsitInList(List<String> list,
			String[] content) {
		for (int j = 0; j < list.size(); j++) {
			for (int k = 0; k < content.length; k++) {
				if (list.get(j).equals(content[k])) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * verify list contains certain value,return this element
	 * 
	 * @shaine
	 */
	public static WebElement verifyCertainContentExsitInList(
			List<WebElement> list, String content) {
		for (int j = 0; j < list.size(); j++) {
			if (list.get(j).getText().trim().toLowerCase()
					.contains(content.toLowerCase())) {
				return list.get(j);
			}

		}
		return null;

	}

	/**
	 * scroll to desired section
	 * 
	 * @author Shaine.Gu
	 */
	public static void scrollToDesiredElement(List<WebElement> list,
			String header) {
		for (WebElement element : list) {
			if (element.getText().trim().contains(header)) {
				scrollToElement(element);
			}
		}
	}

	/**
	 * This method will set any parameter string to the system's
	 * clipboard,writes a string to the system clipboard,otherwise it returns
	 * null.
	 * 
	 * @author paris.yu
	 * @throws Exception
	 */
	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste
		// operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

	/**
	 * @author Shaine.Gu
	 * 
	 *         if a list containing input WebElements,count elements not empty
	 */
	public static int countElementsValueNotEmptyInAInputList(
			List<WebElement> list) {
		int size = 0;
		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).getAttribute("value").isEmpty()) {
				size++;
			}
		}

		return size;
	}

	/**
	 * verify elements in a list get display in alphabetical order or not lower
	 * case before UpperCase remember to exclude the default value in the
	 * parameter list
	 * 
	 * @param list
	 * @author shaine.gu
	 * @return
	 */
	public static boolean IsListDisplayedInAlphabeticalOrder(List<String> list) {
		Object[] a = list.toArray();
		Object[] b = list.toArray();
		List<String> small = new ArrayList<String>();
		List<String> firstSort = new ArrayList<String>();
		List<String> sort = new ArrayList<String>();
		java.util.Arrays.sort(a);
		for (int i = 0; i < a.length; i++) {
			char ch = String.valueOf(a[i]).charAt(0);
			firstSort.add(String.valueOf(a[i]));
			if (Character.isLowerCase(ch)) {
				small.add((String) a[i]);
				firstSort.remove(String.valueOf(a[i]));
			}
		}
		sort.addAll(small);
		sort.addAll(firstSort);
		for (int j = 0; j < b.length; j++) {
			if ((sort.toArray())[j] != b[j]) {
				return false;
			}
		}

		return true;

	}
	

	/**
	 * get the first displayed element's index in the List
	 * 
	 * @author shaine.gu
	 * @param list
	 * @return
	 */
	public static int getFirstDisplayedElementIndexInAList(List<WebElement> list) {
		for (int i = 0; i < list.size(); i++) {
			log.info("style:" + list.get(i).getAttribute("style"));
			if (list.get(i).getAttribute("style").contains("block;")) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * @author matthew.feng To calculate number of days between two dates
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static long getDistanceDays(Date date1, Date date2) throws Exception {
		long days = 0;
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long diff;
		if (time1 < time2) {
			diff = time2 - time1;
		} else {
			diff = time1 - time2;
		}
		days = diff / (1000 * 60 * 60 * 24);
		return days;
	}

	/**
	 * fill input box according to title keywords
	 * 
	 * @author shaine.gu
	 */
	public static void fillInputFieldAccordingToTitleKeyWord(
			List<WebElement> inputFields, List<WebElement> titleList,
			String[] titlekeywords, String[] inputValues) throws Exception {
		waitForListOfElementsExsit(inputFields);
		waitForListOfElementsExsit(titleList);
		Thread.sleep(10000);
		
		for (int i = 0; i < titlekeywords.length; i++) {
			input(inputFields.get(findAKeywordInList(titlekeywords[i],
					titleList)), inputValues[i]);
		}
		

	}

	// verify values displays in the fields according to title keywords
	public static void verifyFieldValuesAccordingToTitleKeyword(
			List<WebElement> inputFields, List<WebElement> titleList,
			String[] titlekeywords, String[] inputValues) throws Exception {
		waitForListOfElementsExsit(inputFields);
		Thread.sleep(10000);
		for (int i = 0; i < titlekeywords.length; i++) {
			log.info("verify value i is:" + i);
			log.info("size is:" + inputFields.size());
			log.info("i is:" + findAKeywordInList(titlekeywords[i], titleList));
			String tempvalue = inputFields.get(
					findAKeywordInList(titlekeywords[i], titleList))
					.getAttribute("value");
			log.info("title value i is:" + titlekeywords[i]);

			log.info("temp value" + i + "is:" + tempvalue);
			customAssertion.assertTrue(containsSubstring(tempvalue,
					inputValues[i]));
		}

	}


	/**
	 * open new tab in same browser and switch to it
	 * 
	 * @throws InterruptedException
	 * @author shaine.gu
	 */

	public static void openNewTabInSameBrowserAndSwitch()
			throws InterruptedException {
		Actions newtab = new Actions(driver);
		newtab.keyDown(Keys.CONTROL).sendKeys("t").keyUp(Keys.CONTROL)
				.perform();
		int total_Size = TestCaseBase.driver.getWindowHandles()
				.size();
		switchToWindow(total_Size - 1);
	}

	/**
	 * open a new tab and close old tab ,then send a url
	 * 
	 * @author shaine
	 * @param url
	 * @throws InterruptedException
	 */
	public static void replaceCurrentTabAndSendUrl(String url)
			throws InterruptedException {
		Actions newtab = new Actions(driver);
		openNewTabInSameBrowserAndSwitch();
		newtab.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL)
				.perform();
		newtab.release();
		newtab.keyDown(Keys.CONTROL).sendKeys("w").keyUp(Keys.CONTROL)
				.perform();
		switchToWindow(0);
		driver.navigate().to(url);

	}

	/*
	 * hover over an element by webdriver action
	 * 
	 * @AUTHOR CATHY ZHANG
	 */
	public static void hoverOver(WebElement element) throws Exception {
		FunctionUtil.waitForElementExist(element);
		Actions action = new Actions(TestCaseBase.driver);
		action.moveToElement(element).perform();
		Thread.sleep(1000);
	}

	/**
	 * To select an options from a selector based on the option index number
	 * 
	 * @author matthew.feng
	 * @param selector
	 * @param index
	 * @throws Exception
	 */
	static public void selectFromSelector(WebElement selector, int index)
			throws Exception {
		click(selector);
		Thread.sleep(2000);
		List<WebElement> options = driver.findElements(By.tagName("option"));
		if (index + 1 > options.size())
			throw new NoSuchElementException(
					"Index number"
							+ index
							+ " is out of the bound of selector options. Actual Option = "
							+ options.size());
		click(options.get(index));
	}

	static public void selectFromSelector(WebElement selector, String toSelect)
			throws Exception {
		List<WebElement> elements = selector.findElements(By.tagName("option"));
		selectFromDropDown(elements, toSelect);
	}

	/**
	 * Try all the formatter above to convert the string to date type
	 * 
	 * @param s
	 * @return
	 * @author tripp.hu
	 */
	public static Date tryAllConvertStringToDate(String s) {
		int formatterIdx = 0;
		Date d = null;
		while (++formatterIdx <= 14)
			try {
				d = convertStringToDateType(s, formatterIdx);
				break;
			} catch (Exception e) {
			}
		
		if(d == null) {
    		   log.info("Convering from string to date is failed.");
    	}
    	   
		return d;
	}

	/**
	 * Add the text of each Web Element into a string list
	 * 
	 * @author matthew.feng
	 * @param elementList
	 * @return
	 */
	public static List<String> addElementTextToStringList(
			List<WebElement> elementList, String attribute) {
		List<String> targetList = new ArrayList<String>();
		if (attribute.isEmpty()) {
			for (WebElement element : elementList) {
				targetList.add(element.getText().trim());
			}
		} else {
			for (WebElement element : elementList) {
				targetList.add(element.getAttribute(attribute).trim());
			}
		}
		return targetList;
	}

	/**
	 * Verify each String of List 1 contains every corresponding string of List
	 * 2
	 * 
	 * @author matthew.feng
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static boolean verifyStringListContainsAnotherStringList(
			List<String> list1, List<String> list2) {
		
//		if(list1.size()!=list2.size()) {
//			System.out.println("List1 size=" + list1.size() + "      List2 size = "+ list2.size());
//			return false;
//		}
		
		for (int i = 1; i < list1.size(); i++) {
			if (!list1.get(i).contains(list2.get(i).trim())) {
				System.out.println("List1 =" + list1.get(i) + "      List2 = "+ list2.get(i));
				return false;
			}
		}
		
		return true;
	}

	public static void pressEnterKey() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
	}

	/**
	 * @Description: Get Option's index which has been checked already
	 * @return: Option's index
	 */
	public static int getCheckedIndexFrmList(List<WebElement> list) {

		int checkedOption=0;

		// Assert the Error when the drop down is Empty
		customAssertion.assertFalse(list.isEmpty(),
				"Desired Drop down is Empty");
		for (int i = 0; i < list.size(); i++) {
			if (isAttribtuePresent(list.get(i), "checked")) {
				checkedOption = i;
				break;
			}
		}
		return checkedOption;

	}
	
	
	
	
	/**
	 * @author shaine.gu
	 * @throws InterruptedException 
	 * make sure certain check box is not checked.
	 */
	
	
	public static void unselectCertainCheckBox(WebElement checkboxElement) throws InterruptedException{
		boolean isDesiredOptionBeSelected = getCheckedBoxCheckedOrNot(checkboxElement);
		if(isDesiredOptionBeSelected){
			checkboxElement.click();
			Thread.sleep(1000);
		}
	}
	
	

	/**
	 * Get the all the (path + file name) of all the files in a specified path
	 *
	 * @author matthew.feng
	 * @return a list of file objects
	 * @throws Exception
	 */
	public static File[] getFilePathNameFrmAPath(String path) throws Exception {
		File f = new File(path);
		File[] paths = f.listFiles();
		return paths;
	}
	
	/**
	 * Get the (path+name) of the first file of all the files in a specified
	 * path
	 *
	 * @author matthew.feng
	 * @return the path+name of the first file
	 * @throws Exception
	 */
	public static String getFirstFileNameInAPath(String path) throws Exception {
		File[] files = getFilePathNameFrmAPath(path);
		return files[0].getName(); 
	}
	
	/**
	 * wait for file download, not check file name
	 * @author mercy.liu
	 * @return
	 * @throws Exception
	 */
	public static boolean waitForFileDownloadComplete() throws Exception {
		int loopCount = 1;
		Thread.sleep(2000);
		String fileName = "";
				
		while (loopCount <= 60) {
			if (getFilePathNameFrmAPath("./Download").length > 0){
				fileName = getFirstFileNameInAPath("./Download");
				//System.out.println("^^^^^^^^^^^^^^^^^^^^^         "+fileName);
				if (!fileName.contains(".crdownload") && !fileName.contains(".part") && 
						!fileName.contains(".tmp")||fileName.substring(fileName.length()-4).contains("jpeg")||
							fileName.substring(fileName.length()-4).contains("pdf")||fileName.substring(fileName.length()-4).contains("png")||
							fileName.substring(fileName.length()-4).contains("jpg")) return true;
				else {	
					loopCount++;
					Thread.sleep(5000);
					System.out.println("Waiting for downloading ");
					continue;}
			}else {
				loopCount++;
				Thread.sleep(5000);
				System.out.println("Waiting for downloading ");
				continue;
			}
		}
		return false;
	}

	/**
	 * Verify a file download is completed or not
	 * @author matthew.feng
	 * @throws Exception
	 */
	public static String verifyDownloadFileSuccessfully() throws Exception {
		File file = new File("Download");
		String desiredFilePath = file.getAbsolutePath();		
		String desiredFileName = FunctionUtil.getFileName(desiredFilePath).get(0);
		String  extentName = desiredFileName.split("\\.")[1]; 		
		//desiredFileName.substring(desiredFileName.length()-6);
		customAssertion.assertTrue(extentName.contains("png")|| 
								   extentName.contains("jpg")||
								   extentName.contains("pdf")||
								   extentName.contains("csv")||
								   extentName.contains("aft"),"Verify the file is downloaded successfully");
		return desiredFileName;
	}
	
	
	/**
     * Objective: Read desired column values from desired Excel file (Excel
     * version: 2007)
     *
     * @param desiredExcelFileName
     *            : desired XLSX file name
     * @param desiredColNo
     *            : The column which you want to get the content
     * @return: ArrayList
     * @throws Exception
     */
    public static List<String> readDesiredColumnValuesFromDesiredExcelFile(
            String desiredExcelFileName, int desiredColNo) throws Exception {

        // Define Parameters
        List<String> allValuesForDesiredColumnNo = new ArrayList<String>();

        // Define Input Stream for desired file
        System.out.println(DOWNLOAD_DIR + desiredExcelFileName);
        InputStream fs = new FileInputStream(DOWNLOAD_DIR + desiredExcelFileName);

        // Define Workbook and Sheet
        XSSFWorkbook wb = new XSSFWorkbook(fs);
        XSSFSheet xssfSheet = wb.getSheetAt(0);


        int rowstart = xssfSheet.getFirstRowNum();
        int rowEnd = xssfSheet.getLastRowNum();

        for (int i = rowstart; i <= rowEnd; i++) {
            XSSFCell headerCell = xssfSheet.getRow(i).getCell(desiredColNo);
            allValuesForDesiredColumnNo.add(headerCell.getStringCellValue());
        }

        // Close the FIle InputStream
        fs.close();
        // return result
        return allValuesForDesiredColumnNo;
    }
}
