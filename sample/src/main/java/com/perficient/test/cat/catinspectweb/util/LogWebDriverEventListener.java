package com.perficient.test.cat.catinspectweb.util;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class LogWebDriverEventListener implements WebDriverEventListener {
	private Log log = LogFactory.getLog(this.getClass());
	private By lastFindBy;
	private String originalValue;

	@Override
	public void beforeNavigateTo(String url, WebDriver webdriver) {
		log.info("WebDriver navigating to:'" + url + "'");
	}

	
	public void beforeChangeValueOf(WebElement element, WebDriver webdriver) {
		originalValue = element.getAttribute("value");
	}
	
	public void beforeAlertAccept(WebDriver driver){
		
	}
	
	public void afterAlertAccept(WebDriver driver){
		
	}
	
	public void beforeAlertDismiss(WebDriver driver){
		
	}
	
	public void afterAlertDismiss(WebDriver driver){
		
	}
	
	public void beforeNavigateRefresh(WebDriver driver){
		
	}
	
	public void afterNavigateRefresh(WebDriver driver){
		
	}
	
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend){
		
	}
	
	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend){
		
	}

	
	public void afterChangeValueOf(WebElement element, WebDriver webdriver) {
		log.info("WebDriver changing value in element found " + lastFindBy
				+ " from '" + originalValue + "' to '"
				+ element.getAttribute("value") + "'");
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver webdriver) {
		webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Override
	public void onException(Throwable error, WebDriver webdriver) {
		if (error.getClass().equals(NoSuchElementException.class)) {
			log.info("Element is still not found " + lastFindBy);
		} else {
			log.error("WebDriver error:", error);
		}
	}

	@Override
	public void beforeNavigateBack(WebDriver webdriver) {
		
		log.info("Before page back navigation, the URL is:"+webdriver.getCurrentUrl().trim());
	}

	@Override
	public void beforeNavigateForward(WebDriver webdriver) {
		
		log.info("Before page forward navigation, the URL is:"+webdriver.getCurrentUrl().trim());
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver webdriver) {
		
	}

	@Override
	public void beforeScript(String script, WebDriver webdriver) {
		
		webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver webdriver) {
		log.info("Clicked the element found " + lastFindBy);
		webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver webdriver) {
		lastFindBy = by;
	}

	@Override
	public void afterNavigateBack(WebDriver webdriver) {
		log.info("Page navigate back to URL:"+webdriver.getCurrentUrl().trim());
	}

	@Override
	public void afterNavigateForward(WebDriver webdriver) {
		log.info("Page navigate forward to URL:"+webdriver.getCurrentUrl().trim());
	}

	@Override
	public void afterNavigateTo(String url, WebDriver webdriver) {
		webdriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@Override
	public void afterScript(String script, WebDriver webdriver) {
		
		if(script.contains("arguments[0].click();")){
			log.info("Click on the element find "+ lastFindBy +" by javascript");
		}
	
	}

}