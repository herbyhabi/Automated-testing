package com.perficient.selenium.support;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;

public class WebDriverXpathProxy implements InvocationHandler {

	private final WebDriver w;
	private final XPathFunction x;

	public WebDriverXpathProxy(XPathFunction x, WebDriver webDriver) {
		this.x = x;
		this.w = webDriver;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (method.getName().contains("findElement") && args[0] instanceof ByXPath){
			return method.invoke(w, applyToByXpath(args));
		}
		return method.invoke(w, args);
	}

	private Object[] applyToByXpath(Object[] args) {
		String xpath = x.apply(((ByXPath) args[0]).toString().split(".xpath: ")[1]);
		return new Object[] { By.xpath(xpath) };
	}

	public static WebDriver getInstance(XPathFunction s, WebDriver w) {
		return (WebDriver) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { WebDriver.class },
				new WebDriverXpathProxy(s, w));
	}

}
