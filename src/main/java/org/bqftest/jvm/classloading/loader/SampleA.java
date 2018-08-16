package org.bqftest.jvm.classloading.loader;

public class SampleA
{
	public SampleA()
	{
		System.out.println("SampleA is loaded by " + this.getClass().getClassLoader());
		new A();
	}
	
//	public static void main(String[] args)
//    {
//		String aa = "org.abc.bbb.SampleA";
//		System.out.println(aa.substring(aa.lastIndexOf(".") + 1, aa.length()));
//    }
}

class A
{
	public A()
	{
		System.out.println("A is loaded by " + this.getClass().getClassLoader());
	}
}