package org.bqftest.jvm.classloading.clinit;

/**
 * 多个线程初始化一个类时，jvm会保证clinit方法被正确加锁、同步
 * 如果static块执行时间过长，会导致隐式的线程阻塞问题。
 *
 */
public class DeadLoopClass
{

	static
	{
		if (true) // 如果不加这句，编译器会报错：“Initializer does not complete normally”
		{
			System.out.println(Thread.currentThread() + "init DeadLoopClass.");
			while(true)
			{
			}
			
			
			/**
			 * 也可以让sleep一下， 可以看到:
			 * Thread[Thread-0,5,main]start...
			 * Thread[Thread-1,5,main]start...
			 * Thread[Thread-0,5,main]init DeadLoopClass.
			 * Thread[Thread-0,5,main]end.
			 * Thread[Thread-1,5,main]end.
			 * 
			 * 即：static块（编译后的clinit方法）只会初始化一次。 线程1执行时线程2阻塞，线程1初始化完，线程2发现clinit已执行 就不再执行了。
			 */
//			try
//            {
//	            Thread.sleep(2000);
//            } catch (InterruptedException e)
//            {
//	            e.printStackTrace();
//            }
		}
	}
	
	/**
	 * 从这里启动没法模拟多线程阻塞的场景，原因见Main类。
	 */
//	public static void main(String[] args)
//    {
//	    Runnable script = new Runnable()
//	    {
//			@Override
//            public void run()
//            {
//				System.out.println(Thread.currentThread() + "start...");
//				DeadLoopClass a = new DeadLoopClass();
//				System.out.println(Thread.currentThread() + "end.");
//            }
//	    };
//	    
//	    Thread thread1 = new Thread(script);
//	    Thread thread2 = new Thread(script);
//	    thread1.start();
//	    thread2.start();
//    }
}
