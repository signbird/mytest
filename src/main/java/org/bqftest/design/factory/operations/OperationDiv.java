package org.bqftest.design.factory.operations;

import org.bqftest.design.factory.IOperate;

public class OperationDiv implements IOperate
{

	@Override
    public double getResult(double numA, double numB)
    {
		return numB == 0 ? 0 : numA / numB;
    }

}
