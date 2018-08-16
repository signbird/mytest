package org.bqftest.jvm.classloading.init;

public class Main
{

	public static void main(String[] args)
    {
		// 仅初始化rootClass： 对于静态字段的引用，属于主动引用的5种情况的第一种，但仅定义这个字段所在的类会被初始化
//		System.out.println(SuperClass.ROOT);
		
		// 先初始化root，再super： 初始化superClass时发现其父类rootClass没有初始化，所以先要初始化rootClass，属于主动引用的第三种情况
//	    System.out.println(SubClass.AAA);
		
		// 没有触初始化.  final修饰的常量，在编译期会被优化到所引用类的常量池中（如这里的Main.class）,在编译期就和SubClass.CONST没有关系了
		// 另外， 通过接口定义的常量，默认是被final修饰了的，所以即便不显示写final，也同样是常量字段。
		System.out.println(SubClass.CONST);
		
		// 没有触初始化， 书上说P212  触发了 由虚拟机自动生成的类 Lorg.bqftest.jvm.classloading.init.SuperClass 类的初始化， 不明所以
	    // 数组类本身不通过类加载器创建，而是由Java虚拟机直接创建。 不过 数组的元素仍然是靠类加载器创建的。
		SuperClass[] aa = new SuperClass[10];
    }
}
