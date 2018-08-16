package org.bqftest.jvm.classloading.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *  http://www.cnblogs.com/chenying99/archive/2013/04/02/2994642.html
 *  http://blog.csdn.net/a352193394/article/details/7343385
 *  
 *  通过重写findClass方法，从本地磁盘中读取.class文件， 实现自定义的类加载器。
 *  
 */
public class MyClassLoader extends ClassLoader
{

	private String name; // 类加载器的名字

	private String path = "d://"; // 加载类的路径

	private final String fileType = ".class"; // .class文件扩展名

	public MyClassLoader(String name)
	{
		super();// 让系统加载器成为该类的加载器的父类加载器

		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name)
	{
		super(parent); // 显示指定该类加载器的父加载器
		this.name = name;
	}

	/**
	 * System.out.println("SampleA is loaded by " + this.getClass().getClassLoader());
	 * 这里的this.getClass().getClassLoader() 返回的是 MyClassLoader这个类的toString结果，默认的是这种：“org.bqftest.jvm.classloading.loader.MyClassLoader@51493995”
	 * 故最好重写toString方法。
	 */
	@Override
	public String toString()
	{
		return this.name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	/**
	 * 读取class文件作为二进制流放入到byte数组中去
	 * 
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name)
	{
		InputStream in = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;

		try
		{
//			name = name.replace(".", "\\");
			name = name.substring(name.lastIndexOf(".") + 1, name.length());
			in = new BufferedInputStream(new FileInputStream(new File(path + name + fileType)));
			baos = new ByteArrayOutputStream();
			int ch = 0;
			while (-1 != (ch = in.read()))
			{
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				in.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			} finally
			{
				try
				{
					baos.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * 重写JVM调用的加载器的方法
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

//	public static void main(String[] args) throws Exception
//	{
//		MyClassLoaderFromFile loader1 = new MyClassLoaderFromFile("myLoader1");
//		loader1.setPath("E:\\workspace\\mytest\\target\\classes\\org\\bqftest\\jvm\\classloading");
//		test(loader1);
//	}
//
//	public static void test(ClassLoader loader) throws Exception
//	{
//		// 使用自定义类加载器加载一个类，并获取到其实例
//		Class<?> clazz = loader.loadClass("org.bqftest.jvm.classloading.SimpleClass");
//		Object o = clazz.newInstance();
//		
//		// 反射调用该类的方法
//		Method m = o.getClass().getDeclaredMethod("aaa");
//		m.invoke(o);
//	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        
		//loader0的父加载器为系统类加载器
        MyClassLoader loader0 = new MyClassLoader("loader0");
        loader0.setPath("E:/workspace/mytest/target/classes/org/bqftest/jvm/classloading/loader/");
        
        //loader1的父加载器为根类加载器
        MyClassLoader loader1 = new MyClassLoader(null, "loader1");
        loader1.setPath("E:/workspace/mytest/target/classes/org/bqftest/jvm/classloading/loader/");
        
        //loader2的父加载器为loader1
        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader2.setPath("");


     // 执行loader0.loadClass()时，其父加载器为系统类加载器： loaded by sun.misc.Launcher$AppClassLoader@6fefa3e7
//      Class<?> clazz = loader0.loadClass("org.bqftest.jvm.classloading.loader.SampleA");
//      Object o = clazz.newInstance();
        
        
     // 执行loader1.loadClass()时，先由它上层的所有父加载器尝试加载Sample类。loader1的父加载器为根类加载器，它无法加载Sample类，接着loader1从E:/XXX/目录下成功地加载了Sample类，因此loader1是Sample类的定义类加载器即初始类加载器。
//      Class<?> clazz = loader1.loadClass("org.bqftest.jvm.classloading.loader.SampleA");
//      Object o = clazz.newInstance();
        
        //执行loader2.loaderClass()时，先由它上层的所有父加载器尝试加载Sample类。loader1从D:/lib1/目录下成功的加载了Sample类，因此laoder1是Sample类的定义类加载器，loader1和loader2是Sample类的初始类加载器。
        //在Sample类中主动使用了A类，当执行Sample类的构造方法中的new A()语句时，Java虚拟机需要先加载Dog类，Java虚拟机会勇Sample类的定义类加载器去加载Dog类，加载过程也同样采用父类委托机制
        Class<?> clazz = loader2.loadClass("org.bqftest.jvm.classloading.loader.SampleA");
        Object o = clazz.newInstance();
        
    }
	
}

