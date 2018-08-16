package org.bqftest.swing;

import javax.swing.*;//注意：是javax
import java.awt.*;
import java.awt.event.*;

public class TestSwing extends JFrame
{
    private static final long serialVersionUID = 1L;

	public TestSwing()
	{
		super("An Application using Swing");

		Container contentPane = getContentPane();

		Icon icon = new ImageIcon("swing.gif", "An advanced GIF of Duke on a swing");

		JLabel label = new JLabel("Swing!", icon, SwingConstants.CENTER);

		contentPane.add(label, BorderLayout.CENTER);
	}

	public static void main(String args[])
	{
		final JFrame f = new TestSwing();

		f.setBounds(100, 100, 300, 250);
		f.setVisible(true);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosed(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
}