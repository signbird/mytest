package org.bqftest.design.factory.operations;

import org.bqftest.design.factory.IOperate;

public class OperationSub implements IOperate
{

	@Override
    public double getResult(double numA, double numB)
    {
	    return numA - numB;
    }
}
