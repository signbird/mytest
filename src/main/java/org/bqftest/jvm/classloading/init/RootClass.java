package org.bqftest.jvm.classloading.init;

public class RootClass
{

	static {
		System.out.println("root class init.");
	}
	
	public static int ROOT = 111;
}
