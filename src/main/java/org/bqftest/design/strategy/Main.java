package org.bqftest.design.strategy;

import org.bqftest.design.strategy.client.FacadeForm;


public class Main
{

	public static void main(String[] args)
    {
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new FacadeForm();
			}
		});
    }
	
}
