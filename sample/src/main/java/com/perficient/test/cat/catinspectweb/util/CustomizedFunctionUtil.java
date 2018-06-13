package com.perficient.test.cat.catinspectweb.util;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;


/**
 * This class is customizing the actions from FunctionUtil class.
 * All the actions in this class requires an extra item -- a description of the corresponding action.
 * The description will display in the HTML extent report.
 * @author matthew.feng
 *
 */
public class CustomizedFunctionUtil extends FunctionUtil {
	
	static public void click(WebElement element, String description) throws Exception{
		click(element);
		test.log(LogStatus.INFO, description);
	}
	
	static public void hoverOver(WebElement element, String description) throws Exception{
		hoverOver(element);
		test.log(LogStatus.INFO, description);
	}
	
	static public void input(WebElement element, String inputString, String description) throws Exception{
		input(element, inputString);
		test.log(LogStatus.INFO, description);
	}
	
	static public void selectFromDropDown(List<WebElement> options, String toSelect, String description) throws Exception{
		selectFromDropDown(options, toSelect);
		test.log(LogStatus.INFO, description);
	}
		
	static public void selectFromSelector(WebElement selector, int index, String description) throws Exception{
		selectFromSelector(selector, index);
		test.log(LogStatus.INFO, description);
	}
	
	static public void selectFromSelector(WebElement selector, String toSelect, String description) throws Exception{
		selectFromSelector(selector, toSelect);
		test.log(LogStatus.INFO, description);
	}
	
	static public void clickByJavaScripts(WebElement element, String description) throws Exception{
		clickByJavaScripts(element);
		test.log(LogStatus.INFO, description);
	}
	
	static public void clickByAction(WebElement element, String description) throws Exception{
		clickByAction(element);
		test.log(LogStatus.INFO, description);
	}
	
	static public void ifExistThenClick(WebElement element, String description) throws Exception{
		if (ifExistThenClick(element)==true);
			 test.log(LogStatus.INFO, description);
	}
}
