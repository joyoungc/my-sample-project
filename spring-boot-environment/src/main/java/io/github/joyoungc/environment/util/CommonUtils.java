package io.github.joyoungc.environment.util;

import java.util.Map;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValues;

public class CommonUtils {
	
	public static <T> void propertyAccessor(T obj, Map<String, Object> map) {
		/** 1) Accessing properties directly */
		// PropertyAccessor pad = PropertyAccessorFactory.forDirectFieldAccess(obj);
		/** 2) Accessing properties through accessors/mutators (using getters, setters)*/
		PropertyAccessor pab = PropertyAccessorFactory.forBeanPropertyAccess(obj);
		PropertyValues pvs = new MutablePropertyValues(map);
		pab.setPropertyValues(pvs, true, true);
	}

}
