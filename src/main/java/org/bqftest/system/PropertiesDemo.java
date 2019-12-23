package org.bqftest.system;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesDemo {

	public static void main(String[] args) {
		Properties p = System.getProperties();
		Enumeration<?> e = p.propertyNames();
		while (e.hasMoreElements()) {
			String eName = e.nextElement().toString();
			System.out.println(eName + ": " + System.getProperty(eName));
		}
	}
}
