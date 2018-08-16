package org.bqftest.jvm.classloading.loader;

public class SampleB
{
	static
	{
		System.out.println("in static method");
	}
	
	public SampleB()
	{
		System.out.println("SampleB is loaded by " + this.getClass().getClassLoader());
	}
}
