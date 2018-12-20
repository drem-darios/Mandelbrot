package com.drem.app.mandelbrot.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.drem.app.mandelbrot.thread.WorkerThread;

public class Display 
{
	private JButton resetButton;
	private JButton generateButton;
	private InputPanel input = new InputPanel();
	private DisplayCanvas canvas = new DisplayCanvas();
	private int maxThreads = 10000; // number of threads
	public Display()
	{
		JFrame window = new JFrame("Mandelbrot Set");
		window.setSize(550, 650);
		
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		
		setupPanels(window);
		window.setVisible(true);
	}

	private void setupPanels(JFrame window) 
	{
		
		JPanel displayPanel = new JPanel(new BorderLayout());
		JPanel inputPanel = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		
		createButtons();
		addListeners();
		
		buttonPanel.add(generateButton);
		buttonPanel.add(resetButton);
		
		displayPanel.add(new JPanel(), BorderLayout.NORTH);
		displayPanel.add(new JPanel(), BorderLayout.WEST);
		displayPanel.add(new JPanel(), BorderLayout.EAST);
		displayPanel.add(canvas, BorderLayout.CENTER);
		displayPanel.add(new JPanel(), BorderLayout.SOUTH);
		
		inputPanel.add(new JPanel(), BorderLayout.NORTH);
		inputPanel.add(new JPanel(), BorderLayout.WEST);
		inputPanel.add(new JPanel(), BorderLayout.EAST);
		inputPanel.add(input, BorderLayout.CENTER);
		inputPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		window.add(displayPanel, BorderLayout.CENTER);
		window.add(inputPanel, BorderLayout.SOUTH);
	}
	
	private void createButtons() {
		generateButton = new JButton("Generate!");
		resetButton = new JButton("Reset!");
	}

	private void addListeners()
	{
		resetButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				input.setRealText("-0.4");
				input.setImagText("0");		
				input.setViewText("1.1");
				input.setSeqText("50");
			}
		});
		
		generateButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				double real = Double.parseDouble(input.getRealText());
				double imaginary = Double.parseDouble(input.getImagText());
				double view = Double.parseDouble(input.getViewText());
				int sequence = Integer.parseInt(input.getSeqText());
				
				WorkerThread[] threads = new WorkerThread[maxThreads];
				
				for(int i = 0; i < maxThreads; i++)
				{
					threads[i] = new WorkerThread(real, imaginary, view, sequence, i);
					threads[i].start();
				}
				
				boolean wait = true;
				while(wait)
				{
					for(int j = 0; j < maxThreads; j++)
		            {
		                if (threads[j].isAlive()) 
		                {
		                    wait = false;
		                }
		            }
				}
				
				int[] colors = WorkerThread.getColors();
				for(int i = 0; i < colors.length; i++)
					System.out.println(colors[i]);
				canvas.setColor(colors);
				canvas.repaint();
			}
		});
	}
}
