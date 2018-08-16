package org.bqftest.design.decorator.my.subClothes;

import org.bqftest.design.decorator.my.IClothes;

public class TShirt implements IClothes
{

	@Override
    public void show()
    {
	    System.out.print("衬衣 ");
    }
	
	@Override
    public String type()
    {
		return "衬衣";
    }
}
