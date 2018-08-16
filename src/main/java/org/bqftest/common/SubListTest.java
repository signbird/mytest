package org.bqftest.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListTest
{

	public static void main(String[] args)
    {
	    List<String> aaa = new ArrayList<String>();
	    aaa.add("0");
	    aaa.add("1");
	    aaa.add("2");
	    aaa.add("3");
	    aaa.add("4");
//	    aaa.add("5");
//	    aaa.add("6");
//	    aaa.add("7");
//	    aaa.add("8");
//	    aaa.add("9");
	    
	    System.out.println(Arrays.asList(aaa.subList(0, aaa.size() < 10 ? aaa.size() : 9)));
	    
	    int max = 3;
	    
	    for (int i = 0; i < getCeilInt(aaa.size(), max); i++)
		{
	    	if (i == aaa.size() / max)
	    	{
	    		System.out.println(Arrays.asList(aaa.subList(max * i, aaa.size())));
	    	}
	    	else
	    	{
	    		System.out.println(Arrays.asList(aaa.subList(max * i, max * i + max)));
	    	}
		}
    }
	
	/**
	 * 向上取整
	 * @param a 被除数
	 * @param b 除数
	 * @return
	 */
	public static int getCeilInt(int a, int b)
	{
		return (((double) a / (double) b) > (a / b) ? a / b + 1 : a / b);
	}
}
