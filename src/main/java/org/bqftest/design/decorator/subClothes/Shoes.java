package org.bqftest.design.decorator.subClothes;

import org.bqftest.design.decorator.Clothes;
import org.bqftest.design.decorator.Person;

public class Shoes extends Clothes
{

//	public Shoes(Person p)
//    {
//	    super(p);
//    }

	public Shoes(String name)
    {
	    super(name);
	    // TODO Auto-generated constructor stub
    }

	@Override
    public void show()
    {
	    System.out.print("鞋子  ");
    }

}
