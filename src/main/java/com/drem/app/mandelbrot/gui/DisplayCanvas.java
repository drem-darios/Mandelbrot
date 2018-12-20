package com.drem.app.mandelbrot.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.MemoryImageSource;

@SuppressWarnings("serial")
public class DisplayCanvas extends Canvas implements MouseListener
{
	private Image image;
	private int[] color = new int[250000];
	
	public DisplayCanvas()
	{
		setBackground(Color.BLACK);
		addMouseListener(this);
		image = createImage(new MemoryImageSource(500, 500, color, 0, 500));
	}
	
	public void setColor(int[] color)
	{
		this.color = color;
		image = createImage(new MemoryImageSource(500, 500, color, 0, 500));
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
