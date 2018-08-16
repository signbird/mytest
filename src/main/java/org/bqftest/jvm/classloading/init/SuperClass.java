package org.bqftest.jvm.classloading.init;

public class SuperClass extends RootClass
{

	static {
		System.out.println("super class init.");
	}
	
	public static int AAA = 123;
}

