package org.bqftest.common;

public class MathRandom
{

	public static void main(String[] args) {  
        System.out.println("测试随机生成字符：");  
        for (int i = 1; i <= 100; i++) {  
            System.out.print(GetRandom.getRandomChar('A', 'Z') + "  ");  
            if (i % 10 == 0)  
                System.out.println();  
        }  
        System.out.println("测试随机生成自然数：");  
        for (int i = 1; i <= 100; i++) {  
            System.out.print(GetRandom.getRandomInt(0, 9) + "  ");  
            if (i % 10 == 0)  
                System.out.println();  
        }  
    }  
}


/**
 * 获取某个区间的随机数  工具类
 */
class GetRandom
{
	// 返回ch1和ch2之间(包括ch1,ch2)的任意一个字符,如果ch1 > ch2，返回'\0'
	public static char getRandomChar(char ch1, char ch2)
	{
		if (ch1 > ch2)
			return 0;
		// 下面两种形式等价
		// return (char) (ch1 + new Random().nextDouble() * (ch2 - ch1 + 1));
		return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
	}

	// 返回a到b之間(包括a,b)的任意一個自然数,如果a > b || a < 0，返回-1
	public static int getRandomInt(int a, int b)
	{
		if (a > b || a < 0)
			return -1;
		// 下面两种形式等价
		// return a + (int) (new Random().nextDouble() * (b - a + 1));
		return a + (int) (Math.random() * (b - a + 1));
	}
}