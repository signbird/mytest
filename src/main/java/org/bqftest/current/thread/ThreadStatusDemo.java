package org.bqftest.current.thread;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo {

	public static void main(String[] args) {
		new Thread(new TimeWaiting(), "MyTimeWaitingThread").start();
		new Thread(new Waiting(), "MyWaitingThread").start();
		
		// 一个获取锁，一个blocked
		new Thread(new Blocked(), "MyBlockedThread-1").start();
		new Thread(new Blocked(), "MyBlockedThread-2").start();
	}
	
	static class TimeWaiting implements Runnable{

		@Override
		public void run() {
			while (true){
				ThreadStatusDemo.second(100);
			}
		}
	}
	
	static class Waiting implements Runnable{

		@Override
		public void run() {
			while (true){
				synchronized (Waiting.class) {
					try{
						Waiting.class.wait();
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	static class Blocked implements Runnable{
		@Override
		public void run() {
			synchronized (Blocked.class) {
				while (true){
					ThreadStatusDemo.second(100);
				}
			}
		}
	}
	
	
	
	public static final void second (long seconds){
		try{
			TimeUnit.SECONDS.sleep(seconds);
		}catch (InterruptedException e){
		}
	}
}
