package org.bqftest.design.strategy.subcash;

import org.bqftest.design.strategy.ICashSuper;

/**
 * 正常计费（无折扣） 
 *
 */
public class CashNormal implements ICashSuper
{

	@Override
    public double acceptCash(double money)
    {
	    // TODO Auto-generated method stub
	    return 0;
    }

}
