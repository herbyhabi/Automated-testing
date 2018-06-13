package com.perficient.test.cat.catinspectweb.basepages.unit;

import static com.perficient.test.cat.catinspectweb.util.FunctionUtil.isElementExist;
import static com.perficient.test.cat.catinspectweb.util.TestCaseBase.customAssertion;

import org.openqa.selenium.support.PageFactory;

import com.perficient.selenium.support.ElementLocatorFactoryEX;
import com.perficient.selenium.support.FieldDecoratorEX;
import com.perficient.selenium.support.WebDriverXpathProxy;
import com.perficient.selenium.support.XPathFunction;
import com.perficient.test.cat.catinspectweb.util.TestCaseBase;

public abstract class Portlet {
	
	public final String portletName;
	protected String locatorPrefix;
	
	protected Portlet(String portletName, String locatorprefix) {
		this.portletName = portletName;
		this.locatorPrefix = locatorprefix;
		
		PageFactory.initElements(new FieldDecoratorEX(new ElementLocatorFactoryEX(WebDriverXpathProxy.getInstance(new XPathFunction() {
			@Override
			public String apply(String xpathExpression) {
				return locatorPrefix + xpathExpression;
			}
		}, TestCaseBase.driverOriginal))), this);
	}
	
	public void isExist() {
		 customAssertion.assertTrue(isElementExist(locatorPrefix), "The portlet called:" + portletName + " is not exist in current page!");
	}

}
