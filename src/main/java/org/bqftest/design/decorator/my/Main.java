package org.bqftest.design.decorator.my;

import org.bqftest.design.decorator.my.subClothes.BigTrouser;
import org.bqftest.design.decorator.my.subClothes.Shoes;
import org.bqftest.design.decorator.my.subClothes.TShirt;

public class Main
{

	public static void main(String[] args)
    {
	    Person p = new Person("小王");
	    
	    IClothes c1 = new BigTrouser();
	    IClothes c2 = new Shoes();
	    IClothes c3 = new TShirt();
	    
	    p.dress(c1);
	    p.dress(c2);
	    p.dress(c3);
	    p.dress(c1);
	    p.showClothes();

	    p.takeOff(c3);
	    p.takeOff(c2);
	    p.takeOff(c2);
	    p.showClothes();
    }
}
