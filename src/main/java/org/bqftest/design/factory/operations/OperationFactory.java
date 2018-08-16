package org.bqftest.design.factory.operations;

import org.bqftest.design.factory.IOperate;

public class OperationFactory
{

	public static IOperate createOperate(String operator)
	{
		IOperate oper = null;
		switch (operator)
        {
		case "+":
			oper = new OperationAdd(); 
			break;
		case "-":
			oper = new OperationSub(); 
			break;
		case "*":
			oper = new OperationAdd(); 
			break;
		case "/":
			oper = new OperationDiv(); 
			break;
		default:
			break;
		}
		
		return oper;
	}

}
