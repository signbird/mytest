package org.bqftest.design.decorator.subClothes;

import org.bqftest.design.decorator.Clothes;
import org.bqftest.design.decorator.Person;


public class BigTrouser extends Clothes
{

//	public BigTrouser(Person p)
//    {
//	    super(p);
//    }

	public BigTrouser(String name)
    {
	    super(name);
	    // TODO Auto-generated constructor stub
    }

	@Override
    public void show()
    {
	    System.out.print("垮裤 ");
    }

}
