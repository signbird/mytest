package org.bqftest.design.decorator.subClothes;

import org.bqftest.design.decorator.Clothes;
import org.bqftest.design.decorator.Person;

public class TShirt extends Clothes
{

//	public TShirt(Person p)
//    {
//	    super(p);
//    }

	public TShirt(String name)
    {
	    super(name);
	    // TODO Auto-generated constructor stub
    }

	@Override
    public void show()
    {
	    System.out.print("T恤  ");
    }

}
