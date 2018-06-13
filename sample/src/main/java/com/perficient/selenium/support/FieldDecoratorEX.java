package com.perficient.selenium.support;

import java.lang.reflect.Field;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class FieldDecoratorEX extends DefaultFieldDecorator{

	public FieldDecoratorEX(ElementLocatorFactory factory) {
		super(factory);
	}

	@Override
	protected boolean isDecoratableList(Field field) {
		return true;
	}

	
	
	
	
}
