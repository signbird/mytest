package org.bqftest.design.decorator.my.subClothes;

import org.bqftest.design.decorator.my.IClothes;

public class Default implements IClothes
{

	@Override
    public void show()
    {
	    System.out.print("默认 ");
    }
	
	@Override
    public String type()
    {
		return "默认";
    }
}
