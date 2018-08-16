package org.bqftest.swing;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 * 来自  http://blog.csdn.net/sjf0115/article/details/6991590#
 *  但运行不起来
 */
public class ValidationTestFrame extends JFrame implements DocumentListener
{
    private static final long serialVersionUID = 1L;
    
	JLabel label = new JLabel("I only accept numbers");
	private IntTextField intFiled;

	public ValidationTestFrame()
	{
		setTitle("ValidationTest");
		setSize(300, 200);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

		Container contentPane = getContentPane();

		JPanel p = new JPanel();
		intFiled = new IntTextField(12, 3);
		p.add(intFiled);

		// 增加DocumentListener事件
		intFiled.getDocument().addDocumentListener(this);

		contentPane.add(p, "South");
		contentPane.add(label, "Center");
	}

	public void insertUpdate(DocumentEvent e)
	{
		setLabel();
	}

	public void removeUpdate(DocumentEvent e)
	{
		setLabel();
	}

	public void changedUpdate(DocumentEvent e)
	{
	}

	public void setLabel()
	{
		if (intFiled.isValid())
		{
			int value = intFiled.getValue();
			label.setText(Integer.toString(value));
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new ValidationTestFrame();
		frame.setVisible(true);
	}

}

class IntTextField extends JTextField
{
    private static final long serialVersionUID = 1L;

	public IntTextField(int defval, int size)
	{
		super("" + defval, size);
	}

	protected Document createDefaultModel()
	{
		return new IntTextDocument();
	}

	public boolean isValid()
	{
		try
		{
			Integer.parseInt(getText());
			return true;
		} catch (NumberFormatException e)
		{
			return false;
		}
	}

	public int getValue()
	{
		try
		{
			return Integer.parseInt(getText());
		} catch (NumberFormatException e)
		{
			return 0;
		}
	}

	class IntTextDocument extends PlainDocument
	{
        private static final long serialVersionUID = 1L;

		// 中写方法实现需求
		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException
		{
			if (str == null)
				return;
			String oldString = getText(0, getLength());
			String newString = oldString.substring(0, offs) + str + oldString.substring(offs);
			try
			{
				Integer.parseInt(newString + "0");

				// 向Document中插入文本前判断
				super.insertString(offs, str, a);
			} catch (NumberFormatException e)
			{
			}
		}
	}

}