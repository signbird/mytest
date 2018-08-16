package org.bqftest.jvm.vmmodel;

/**
 * http://www.cnblogs.com/lin-xuan/p/5271354.html#_label4
 * TODO 沒搞明白
 *
 */
public class InternTest
{
	
    public static void main(String[] args)
    {
    	String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
        System.out.println(str1.intern() == str1);
    
    //	System.out.println("中国".intern() == str1);
    //	System.out.println("中国钓鱼".intern() == str1);
    	System.out.println("中国钓鱼岛".intern() == str1);
    	
        String str2 = new String("倚天不屠龙");
        System.out.println(str2.intern() == str2);
        System.out.println("倚天不屠龙".intern() == str2);
    }
}
