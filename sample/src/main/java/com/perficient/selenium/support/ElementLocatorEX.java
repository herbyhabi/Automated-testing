package com.perficient.selenium.support;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

public class ElementLocatorEX extends DefaultElementLocator {

	public ElementLocatorEX(SearchContext searchContext, Field field) {
		super(searchContext, new AnnotationsEx(field));
	}

}
