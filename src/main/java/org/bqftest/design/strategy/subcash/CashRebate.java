package org.bqftest.design.strategy.subcash;

import org.bqftest.design.strategy.ICashSuper;

/**
 * 打折
 */
public class CashRebate implements ICashSuper
{

	/**
	 * 折扣
	 */
	private double rebate = 1.0d;
	
	public CashRebate(double rebate)
	{
		this.rebate = rebate;
	}
	
	@Override
    public double acceptCash(double money)
    {
	    // TODO Auto-generated method stub
	    return 0;
    }

}
