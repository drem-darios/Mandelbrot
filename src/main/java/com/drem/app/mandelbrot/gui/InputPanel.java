package com.drem.app.mandelbrot.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InputPanel extends JPanel
{
	private JTextField realTF = new JTextField(12); //Real Location
	private JTextField imagTF = new JTextField(12); //Imaginary location
	private JTextField viewTF = new JTextField(10); //View Size
	private JTextField seqTF = new JTextField(10); //Sequence Length
	
	private JPanel realPanel;
	private JPanel imagPanel;
	private JPanel viewPanel;
	private JPanel seqPanel;
	
	private Color[] colors = new Color[500];
	
	public InputPanel()
	{
		init();
		addFields();
	}

	public void setRealText(String real)
	{
		this.realTF.setText(real);
	}
	
	public String getRealText()
	{
		return realTF.getText();
	}
	
	public void setImagText(String imag)
	{
		this.imagTF.setText(imag);
	}
	
	public String getImagText()
	{
		return imagTF.getText();
	}
	
	public void setViewText(String view)
	{
		this.viewTF.setText(view);
	}
	
	public String getViewText()
	{
		return viewTF.getText();
	}
	
	public void setSeqText(String seq)
	{
		this.seqTF.setText(seq);
	}
	
	public String getSeqText()
	{
		return seqTF.getText();
	}
	
	private void addFields() 
	{
		this.setLayout(new BorderLayout());
		JPanel textPanel = new JPanel(new GridLayout(2, 4));
		textPanel.add(realPanel);
		textPanel.add(imagPanel);
		textPanel.add(viewPanel);
		textPanel.add(seqPanel);
		
		add(textPanel, BorderLayout.NORTH);
	}

	private void init() 
	{
		initReal();
		initImag();
		initView();
		initSeq();
		initColors();
	}

	private void initColors() 
	{
		int sequence = Integer.parseInt(seqTF.getText());
		int red;
		int green;
		int blue;
		
		for(int i = 0; i < sequence; i++)
		{
			if (i % 3 == 1)
			{
				red = 49;
				green = 73;
				blue = 240; 
			}
			
			else if(i % 3 == 2)
			{
				red = 252;
				green = 7;
				blue = 7;
			}
			
			else
			{
				red = 5; 
				green = 211;
				blue = 31;
			}
			
			colors[i] = new Color(red, green, blue);
		}
		
	}

	
	private void initReal() 
	{
		setRealText("-0.4");
		realPanel = new JPanel(new GridLayout(1, 2));
		realPanel.add(new JLabel("Real Center"));
		realPanel.add(realTF);
	}
	
	private void initImag() 
	{
		setImagText("0");
		imagPanel = new JPanel(new GridLayout(1, 2));
		imagPanel.add(new JLabel("Imaginary Center"));
		imagPanel.add(imagTF);
	}

	private void initView()
	{
		setViewText("1.1");
		viewPanel = new JPanel(new GridLayout(1, 2));
		viewPanel.add(new JLabel("View Size"));
		viewPanel.add(viewTF);
	}
	
	private void initSeq()
	{
		setSeqText("50");
		seqPanel = new JPanel(new GridLayout(1, 2));
		seqPanel.add(new JLabel("Sequence Length"));
		seqPanel.add(seqTF);
	}
}
