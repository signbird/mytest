package org.bqftest.design.strategy.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FacadeForm extends JFrame
{
    private static final long serialVersionUID = 1L;
	private JLabel label_price;
	private JLabel label_count;

	private JTextField txt_price;
	private JTextField txt_count;

	private JButton btn_ok;
	private JButton btn_reset;

	private JTextArea area_log;

	private JLabel label_total;
	private JLabel label_result;

	public FacadeForm()
	{
		JFrame frame = new JFrame("商场收银系统");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);// 窗口居中
		frame.getContentPane().setLayout(null);

		frame.add(this.getLabelPrice(), null);
		frame.add(this.getLabelCount(), null);
		frame.add(this.getLabelTotal(), null);
		frame.add(this.getLabelResult(), null);

		frame.add(this.getTextPrice(), null);
		frame.add(this.getTextCount(), null);
		frame.add(this.getTextAreaLog(), null);

		frame.add(this.getButtonOK(), null);
		frame.add(this.getButtonReset(), null);

	}

	private JLabel getLabelPrice()
	{
		if (label_price == null)
		{
			label_price = new JLabel("单价：");
			label_price.setBounds(50, 50, 100, 20);
		}

		return label_price;
	}

	private JLabel getLabelCount()
	{
		if (label_count == null)
		{
			label_count = new JLabel("数量：");
			label_count.setBounds(50, 80, 100, 20);
		}

		return label_count;
	}

	private JLabel getLabelTotal()
	{
		if (label_total == null)
		{
			label_total = new JLabel("总计：");
			label_total.setBounds(50, 350, 100, 20);
		}

		return label_total;
	}

	private JLabel getLabelResult()
	{
		if (label_result == null)
		{
			label_result = new JLabel("0.00");
			label_result.setBounds(150, 350, 100, 20);

			 Font font = new Font("", Font.BOLD, 25);// 字体
			 label_result.setFont(font);
		}

		return label_result;
	}

	private JTextField getTextPrice()
	{
		if (txt_price == null)
		{
			txt_price = new JTextField("0.00");
			txt_price.setBounds(100, 50, 100, 20);
			txt_price.setHorizontalAlignment(JTextField.RIGHT);
		}

		return txt_price;
	}

	private JTextField getTextCount()
	{
		if (txt_count == null)
		{
			txt_count = new JTextField("1");
			txt_count.setBounds(100, 80, 100, 20);
			txt_count.setHorizontalAlignment(JTextField.RIGHT);
		}

		return txt_count;
	}

	private JTextArea getTextAreaLog()
	{
		if (area_log == null)
		{
			area_log = new JTextArea();
			area_log.setBounds(50, 120, 280, 200);
		}

		return area_log;
	}

	private JButton getButtonOK()
	{
		if (btn_ok == null)
		{
			btn_ok = new JButton("确定");
			btn_ok.setBounds(240, 50, 80, 20);
		}
		
		btn_ok.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		return btn_ok;
	}

	private JButton getButtonReset()
	{
		if (btn_reset == null)
		{
			btn_reset = new JButton("重置");
			btn_reset.setBounds(240, 80, 80, 20);
		}
		btn_reset.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				txt_price.setText("");
				txt_count.setText("");
				area_log.setText("");
				txt_price.hasFocus();
			}
			
		});
		return btn_reset;
	}

	public static void main(String[] args)
	{

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				new FacadeForm();
			}
		});
	}
	
}
