package org.bqftest.design.decorator.my.subClothes;

import org.bqftest.design.decorator.my.IClothes;

public class BigTrouser implements IClothes
{

	@Override
    public void show()
    {
	    System.out.print("垮裤 ");
    }

	@Override
    public String type()
    {
		return "垮裤";
    }

}
