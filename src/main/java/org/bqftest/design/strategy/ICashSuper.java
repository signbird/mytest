package org.bqftest.design.strategy;

public interface ICashSuper
{

	/**
	 * 优惠策略
	 * 
	 * @param money 原始价钱
	 * @return 优惠后价钱
	 */
	double acceptCash(double money);
}
