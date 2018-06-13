package com.perficient.selenium.support;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ByAll;

import com.perficient.selenium.annotations.FindAllFormat;
import com.perficient.selenium.annotations.FindFormat;

public class AnnotationsEx extends Annotations {

	private Field field;
	public AnnotationsEx(Field field) {
		super(field);
		this.field = field;
	}

	@SuppressWarnings("all")
	@Override
	public By buildBy() {
		
		FindFormat findFormat = field.getAnnotation(FindFormat.class);
		if (findFormat != null) {
			return By.xpath(String.format(findFormat.format(), findFormat.keys()));
		}
		
		FindAllFormat findFormats = field.getAnnotation(FindAllFormat.class);
		if (findFormats != null) {
			By[] bys = new By[findFormats.formats().length];
			for (int i = 0; i < bys.length; i++) 
				bys[i] = By.xpath(String.format(findFormats.formats()[i], findFormats.keys()));
			
			return new ByAll(bys);

		}
		
		return super.buildBy();
	}
	
	

}
