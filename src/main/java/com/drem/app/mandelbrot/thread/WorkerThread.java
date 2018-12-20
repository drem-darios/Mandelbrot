package com.drem.app.mandelbrot.thread;


import com.drem.app.mandelbrot.math.ComplexNumber;

public class WorkerThread extends Thread{

	private double real;
	private double imaginary;
	private double view;
	private int sequence;
	private int id;
	private int width = 500;
	private static int[] pix = new int[250000];
	
	public WorkerThread(double r, double i, double v, int s, int threadId)
	{
		this.real = r;
		this.imaginary = i;
		this.view = v;
		this.sequence = s;
		this.id = threadId;
	}
	
	@Override
	public void run() 
	{
		int red, green, blue;
		for(int i = id; i < width; i+=10)
		{
			for(int j = 0; j < width; j++)
			{
				for(int k = 0; k < sequence; k++)
				{
					double a = (real - view) + 2 * ((view * i) / width);		
					double b = (imaginary + view) - 2 * ((view * j) / width);
					
					ComplexNumber c;
					ComplexNumber z = new ComplexNumber(a, b);
					c = z;
					
					c = ComplexNumber.complexAdd(ComplexNumber.complexSquare(c), z);
					double magZ = c.getPointMag(c);
					
					if (Math.sqrt(magZ) > 2)
					{
						// various color palettes can be created here!
                        red=255-(k%16)*16;
                        green=(16-k%16)*16;
                        blue=(k%16)*16;
                        
                        pix[500*j + i]=(255<<24)|(red<<16)|(green<<8)|blue;
                        break;
					}
					else
					{
						pix[500*j + i] = 0;
					}
				}	
			}	
		}
		
		//setColors(pix);
	}
	
	public static int[] getColors()
	{
		return pix;
	}
	
	private void setColors(int[] colors)
	{
		pix = colors;
	}
}
