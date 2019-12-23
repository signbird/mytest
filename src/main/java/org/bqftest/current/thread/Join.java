package org.bqftest.current.thread;

import java.util.concurrent.TimeUnit;

/**
 * 每个线程等待前驱线程终止后，才从join()方法返回
 *
 *
//加锁当前线程对象
public final synchronized void join() throws InterruptedException {
	// 条件不满足，继续等待
	while (isAlive()) {
		wait(0);
	}
	// 条件符合，方法返回
}
 */
public class Join {
    
    
    
    public static void main(String[] args) throws InterruptedException {
        Thread period = Thread.currentThread();
        
        for (int i = 0; i < 10; i++){
            Thread t = new Thread(new Domino(period), String.valueOf(i));
            t.start();
            period = t;
        }
        TimeUnit.SECONDS.sleep(3);
        System.out.println("all ready, go...");
    }
    
    static class Domino implements Runnable{

        private Thread period;
        
        public Domino(Thread t){
            this.period = t;
        }
        
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " ready.");
            
            try {
                period.join();
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
            System.out.println(Thread.currentThread().getName() + " down.");
        }
        
    } 
    
    
//    
//    
//    
//    
//    
//	public static void main(String[] args) throws Exception {
//		Thread previous = Thread.currentThread();
//		for (int i = 0; i < 10; i++) {
//			// 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
//			Thread thread = new Thread(new Domino(previous), String.valueOf(i));
//			thread.start();
//			previous = thread;
//		}
//		TimeUnit.SECONDS.sleep(10);
//		System.out.println(Thread.currentThread().getName() + " terminate.");
//	}
//
//	// 多米诺骨牌
//	static class Domino implements Runnable {
//		private Thread thread;
//
//		public Domino(Thread thread) {
//			this.thread = thread;
//		}
//
//		public void run() {
//			try {
//				thread.join();
//				TimeUnit.SECONDS.sleep(2);
//			} catch (InterruptedException e) {
//			}
//			
//			System.out.println(Thread.currentThread().getName() + " terminate.");
//		}
//	}
}