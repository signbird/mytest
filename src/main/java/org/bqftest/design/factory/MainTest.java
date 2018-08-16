package org.bqftest.design.factory;

import java.util.Scanner;

import org.bqftest.design.factory.operations.OperationFactory;


public class MainTest
{

	public static void main(String[] args)
    {
		System.out.println("输入数字A：");
		Scanner scanner = new Scanner(System.in);
		String strA = scanner.nextLine();
		System.out.println("输入运算符号（+、-、*、/）：");
		String oper = scanner.nextLine();
		System.out.println("输入数字B：");
		String strB = scanner.nextLine();
		if (oper.equals("/") || strB.equals("0"))
		{
			System.out.println("被除数不能为0， 请重新输入数字B：");
			strB = scanner.nextLine();
		}
		scanner.close();
		
		IOperate operate = OperationFactory.createOperate(oper);
		double result = operate.getResult(Double.valueOf(strA), Double.valueOf(strB));
		
		System.out.println("结果：" + result);
    }
}
