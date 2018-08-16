package org.bqftest.design.decorator.my;

import java.util.ArrayList;
import java.util.List;

public class Person
{

	private String name;
	
	private List<IClothes> clothes;
	
	public Person(String name)
	{
		this.name = name;
		this.clothes = new ArrayList<IClothes>();
	}
	
	public void dress(IClothes cloth)
	{
		for (IClothes c : clothes)
		{
			if (c.type().equals(cloth.type()))
			{
				System.out.println("[warn]穿过了 " + cloth.type());
				return ;
			}
		}
		
		System.out.println(this.name + "穿上了 " + cloth.type());
		clothes.add(cloth);
	}
	
	public void takeOff(IClothes cloth)
	{
		for (IClothes c : clothes)
		{
			if (c.type().equals(cloth.type()))
			{
				System.out.println(this.name + "脱掉了 " + cloth.type());
				clothes.remove(cloth);
				return ;
			}
		}
		
		System.out.println("[warn]没穿过 " + cloth.type());
	}
	
	public void showClothes()
	{
		System.out.print(this.name + "穿了：");
		for (IClothes c : clothes)
		{
			c.show();
		}
		System.out.println();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
}
