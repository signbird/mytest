package org.bqftest.design.strategy.subcash;

import org.bqftest.design.strategy.ICashSuper;

/**
 * 返现
 *
 */
public class CashReturn implements ICashSuper
{
	
	private double condition;
	private double moneyReturn;

	public CashReturn(double condition, double moneyReturn)
	{
		this.condition = condition;
		this.moneyReturn = moneyReturn;
	}
	
	@Override
    public double acceptCash(double money)
    {
	    // TODO Auto-generated method stub
	    return 0;
    }

}
