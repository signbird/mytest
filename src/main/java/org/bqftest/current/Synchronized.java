package org.bqftest.current;

/**
 * javap -v Synchronized.class
 * 
0: ldc           #1                  // class org/bqf
2: dup
3: monitorenter
4: monitorexit
5: invokestatic  #16                 // Method m:()V
8: return

public static synchronized void m();
  descriptor: ()V
  flags: ACC_PUBLIC, ACC_STATIC, ACC_SYNCHRONIZED
 * @author Administrator
 *
 */
public class Synchronized {

	public static void main(String[] args) {
		
		synchronized (Synchronized.class) {
		}
		
		m();
	}
	
	public static synchronized void m(){}
}
