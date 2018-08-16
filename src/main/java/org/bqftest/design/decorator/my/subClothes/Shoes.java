package org.bqftest.design.decorator.my.subClothes;

import org.bqftest.design.decorator.my.IClothes;

public class Shoes  implements IClothes
{

	@Override
    public void show()
    {
	    System.out.print("鞋子 ");
    }
	
	@Override
    public String type()
    {
		return "鞋子";
    }
}
