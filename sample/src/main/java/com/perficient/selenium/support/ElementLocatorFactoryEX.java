package com.perficient.selenium.support;

import java.lang.reflect.Field;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class ElementLocatorFactoryEX implements ElementLocatorFactory {
	  private final SearchContext searchContext;

	  public ElementLocatorFactoryEX(SearchContext searchContext) {
	    this.searchContext = searchContext;
	  }

	  public ElementLocator createLocator(Field field) {
	    return new ElementLocatorEX(searchContext, field);
	  }
}
