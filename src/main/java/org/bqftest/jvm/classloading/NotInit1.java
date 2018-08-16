package org.bqftest.jvm.classloading;

/**
 * JVM有且仅有5中情况会触发主动的类加载： 
 * 1、new 2、反射调用 3、初始化子类时主动初始化父类（父类委托机制） 4、main方法的主类 5、MethodHandle
 * 
 * 除此之外， 所有引用类的方式都不会触发类加载。
 * 
 * 比如下面这种： 通过子类引用父类的静态字段，不会导致子类初始化。
 * 
 * ps1： 子类是否要加载虚拟机规范没有明确规定，hotspot虚拟机可以通过 -XX:+TraceClassLoading 强制子类初始化。
 * ps2：初始化子类时主动初始化父类---对于接口来说，并不需要初始化父接口，只有在真正使用到父接口时才初始化。
 */
public class NotInit1
{

	public static void main(String[] args)
    {
	    System.out.println(SubClass.aaa);
    }
}


class SuperClass
{
	static 
	{
		System.out.println("SuperClass init.");
	}
	
	public static int aaa = 111;
}

class SubClass extends SuperClass
{
	static 
	{
		System.out.println("SubClass init.");
	}
}