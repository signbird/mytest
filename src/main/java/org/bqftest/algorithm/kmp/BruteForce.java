package org.bqftest.algorithm.kmp;

/**
 * 字符串匹配朴素算法 （ Naive String-Matching，也叫 BruteForce，暴力求解方法）  
 * 可以和KMP算法比较。
 * 
 * 时间复杂度O((n-m+1)m)
 *
 * 参考 http://blog.csdn.net/zlhy_/article/details/8655349
 */
public class BruteForce
{

	public static void main(String[] args)
    {
	    String source = "abbabbbbcab";
	    String pattern = "bbcab";
	    System.out.println(bruteForce(source, pattern));
    }
	

	public static int bruteForce(String source, String pattern)
	{
		int sLen = source.length();
		int pLen = pattern.length();
		
		for (int i = 0; i <= sLen - pLen; i++)
		{
			int j = 0, sNow = i;
			for (j = 0; j < pLen; j++)
			{
				if (pattern.charAt(j) != source.charAt(sNow))
				{
					break;
				}
				sNow++;
			}
			
			// matched
			if (j == pLen)
			{
				return i;
			}
		}
		
		return -1;
	}
	
}
