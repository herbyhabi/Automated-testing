package com.perficient.test.cat.catinspectweb.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageManager {
	//protected static WebDriver driver = TestCaseBase.getDriver();
	
	public PageManager(){
		PageFactory.initElements(TestCaseBase.decEx, this);
	}

	protected String getTitle() {
		return TestCaseBase.driver.getTitle();
	}

	public static void waitTillVisible(int seconds, WebElement element){
		 (new WebDriverWait(TestCaseBase.driver,seconds)).until(ExpectedConditions.visibilityOf(element));
			
		}
	// Set the browser default wait time.
	public static void waitFewSeconds(int time) {
		TestCaseBase.driver.manage().timeouts().implicitlyWait(time, TimeUnit.MICROSECONDS);
	}

	public static void maxmizeBrowser() {
		TestCaseBase.driver.manage().window().maximize();

	}

	

}