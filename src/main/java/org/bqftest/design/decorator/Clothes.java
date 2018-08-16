package org.bqftest.design.decorator;

public class Clothes extends Person
{

	public Clothes(String name)
    {
	    super(name);
	    // TODO Auto-generated constructor stub
    }

	private Person component;
	
	
	public void decorate(Person p)
	{
		this.component = p;
	}
	
	@Override
	public void show()
	{
		if (component != null)
		{
			component.show();
		}
	}
}
