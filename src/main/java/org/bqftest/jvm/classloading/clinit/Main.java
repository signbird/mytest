package org.bqftest.jvm.classloading.clinit;

public class Main
{

	/**
	 * 不能通过DeadLoopClass中的main函数启动，
	 * 因为jvm会自动初始化带main函数的类，初始化时会执行static块中的方法，进入死循环，而main函数中的两个线程就没机会执行了。
	 * 
	 * 需要独立出来，才能模拟通过两个thread加载DeadLoopClass，thread1进入static块，而thread2阻塞等待。
	 */
	public static void main(String[] args)
    {
	    Runnable script = new Runnable()
	    {
            public void run()
            {
				System.out.println(Thread.currentThread() + "start...");
				DeadLoopClass a = new DeadLoopClass();
				System.out.println(Thread.currentThread() + "end.");
            }
	    };
	    
	    Thread thread1 = new Thread(script);
	    Thread thread2 = new Thread(script);
	    thread1.start();
	    thread2.start();
    }
}
