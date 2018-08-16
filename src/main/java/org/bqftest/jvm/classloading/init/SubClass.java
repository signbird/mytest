package org.bqftest.jvm.classloading.init;

public class SubClass extends SuperClass
{

	static {
		System.out.println("sub class init.");
	}
	
	public static final String CONST = "i'm const";
}

