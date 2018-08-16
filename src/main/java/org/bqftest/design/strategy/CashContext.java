package org.bqftest.design.strategy;

import org.bqftest.design.strategy.subcash.CashNormal;
import org.bqftest.design.strategy.subcash.CashRebate;
import org.bqftest.design.strategy.subcash.CashReturn;

/**
 * 
 * 这里包一层给客户端调用， 使策略算法和客户端完全分离， 改策略就不用动客户端代码了
 * 
 * TODO 如何理解这个Context类？
 * 其实客户端完全可以这样调用：
 * ICashSuper cs = CashFactory.createCash(type); //简单工厂
 * result = cs.acceptCash(money);
 * 
 * 和使用CashContext比较：
 * CashContext cc = new CashContext(type);
 * result = cc.getResult(money);
 * 
 * ---简单来说就是解耦。 ICashSuper是收费算法的父类，也属于具体实现的范畴，
 * 从解耦的角度看，客户端是不应该关心具体实现的，只负责调用XX接口，传入参数 获得结果就行了，（领导/用户模式：发出指令，得到结果）
 * 因此需要一个包装（facade） 封装算法、策略之类 从属于如何实现这一范畴的东西，供客户端直接使用。
 * 
 * CashContext就充当这个facade。 so， 这个代理有啥不同？？
 */
public class CashContext
{

	/**
	 * 收费策略
	 */
	private ICashSuper cashSuper;
	
	public CashContext(String cashType)
	{
		switch (cashType)
        {
		case "正常":
			this.cashSuper = new CashNormal();
			break;
		case "9折" :
			this.cashSuper = new CashRebate(0.9d);
		case "满300返100" :
			this.cashSuper = new CashReturn(300d, 100d);
		default:
			break;
		}
	}
	
	public double getResult(double money)
	{
		return cashSuper.acceptCash(money);
	}
}
