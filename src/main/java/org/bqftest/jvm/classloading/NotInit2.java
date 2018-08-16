package org.bqftest.jvm.classloading;

/**
 * 常量在编译阶段存入了调用类的常量池中（即存入了NotInit2这个类的class文件的常量池中，和ConstClass没有关系了。这一过程称作编译期的常量传播优化）， 
 * 所以引用常量 本质上并没有直接引用到定义类，不会触发定义常量的类的初始化。 
 *
 */
public class NotInit2
{
	public static void main(String[] args)
    {
	    System.out.println(ConstClass.STR_HELLO);
    }
}

class ConstClass
{
	static
	{
		System.out.println("ConstClass init.");
	}
	
	public static final String STR_HELLO= "Hello";
}