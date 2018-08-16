package org.bqftest.jvm.classloading;

public class SimpleClass
{
	static 
	{
		System.out.println("load SimpleClass.");
	}

	public void aaa()
	{
		System.out.println("used method aaa.");
	}
}
