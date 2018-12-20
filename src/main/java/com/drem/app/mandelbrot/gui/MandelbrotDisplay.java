/**
 * @author - Drem Darios
 */
package com.drem.app.mandelbrot.gui;


import com.drem.app.mandelbrot.math.ComplexNumber;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.image.*;


public class MandelbrotDisplay implements MouseListener, ActionListener{
//makes a new window, canvas, and color
	
	private JFrame  window;
	private Canvas canvas; 
	private Color [] color;
	//private BufferedImage buffer;
	
	//Initialize the text fields
	JTextField RealTxt = new JTextField(12); //Real Location
	JTextField ImagTxt = new JTextField(12); //Imaginary location
	JTextField ViewTxt = new JTextField(10); //View Size
	JTextField SequTxt = new JTextField(10); //Sequence Length
	
	//Variable we will use
	double RealCenter;
	double ImaginaryCenter;
	double ViewSize; //the zoom
	double SequenceLength; // sequence of the set
	final int width = 500;
	double a;//the x real
	double b;//the y imaginary
	double magZ;
	double xCurrent; //current x point clicked on canvas
	double yCurrent; //current y point clicked on canvas
	int x; //loop x
	int y; //loop y
	
	String RealString;
	double RealNum;
	String SequString;
	String ImagString;
	double ImagNum;
	String ViewString;
	
	JButton reset;
	JButton generate;
	
	public MandelbrotDisplay(){
		//Labels for the text boxes. spaces are used to space out the labels correctly
		JLabel Real= new JLabel("Real center          ");
		JLabel Imag= new JLabel("Imaginary center");
		JLabel View= new JLabel("View size               ");	
		JLabel SequLabel= new JLabel("Sequence Length");
	
		JLabel space = new JLabel("                        " +
				"                                " ); //space is used to center the generate button
		
		generate = new JButton("Generate!");
		reset = new JButton("RESET!");
		
		
		//set the window settings, creates a canvas and adds everything to the window
		window = new JFrame("Mandelbrot Set");
		window.setSize(550, 750);
		
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 20, 10);
		window.setLayout(layout);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		
		canvas = new Canvas();
		canvas.setSize(500,500);
		canvas.setBackground(Color.white);
		canvas.addMouseListener(this);		
		window.add(canvas);
		
		window.add(Real);
		window.add(RealTxt);
		
		window.add(View);
		window.add(ViewTxt);
		
		window.add(Imag);
		window.add(ImagTxt);
		
		window.add(SequLabel);
		window.add(SequTxt);
		window.add(space);
		
		window.add(generate);
		window.add(reset);
		
		generate.addActionListener(this);
		reset.addActionListener(this);
		
		RealTxt.setText("-0.4");
		ViewTxt.setText("1.1");
		ImagTxt.setText("0");
		SequTxt.setText("50");
		
		window.setVisible(true);
	}

//if the mouse is clicked
	public void mouseClicked(MouseEvent e) {

		//gets the location the mouse was clicked
		xCurrent = e.getX();
		yCurrent = e.getY();	

		//recalculate a and b so it can be put in the text field
		a = (RealNum - ViewSize) + 2*((ViewSize*xCurrent)/width);				
		b = (ImagNum + ViewSize) - 2*((ViewSize*yCurrent)/width);
		
		RealTxt.setText("" + a);
		ImagTxt.setText("" + b);		
		
		//automatically zooms
		ViewString = ViewTxt.getText();
		ViewSize = Double.parseDouble(ViewString);
		ViewTxt.setText(""+ViewSize/2);
		
	}

	public void mouseEntered(MouseEvent arg0){}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}


	//if the generate button was clicked
	public void actionPerformed(ActionEvent e) {
		

		if(e.getSource() == reset)
		{

			RealTxt.setText("-.4");
			ImagTxt.setText("0");		
			ViewTxt.setText("1.1");
			SequTxt.setText("50");
		}
		
		// make graphics for the canvas
		Graphics g = canvas.getGraphics();
		
		//take in the info from the text fields
		RealString = RealTxt.getText();
		RealNum = Double.parseDouble(RealString);
		
		ImagString = ImagTxt.getText();
		ImagNum = Double.parseDouble(ImagString);

		SequString = SequTxt.getText();
		SequenceLength = Double.parseDouble(SequString);		
	
		ViewString = ViewTxt.getText();
		ViewSize = Double.parseDouble(ViewString);
			

		
		//creates the colors that will be use blue and red are random colors and green is a constant
		int green = 7;
		int blue = 7;
		int red = 252;
		int j;
    	color = new Color[500];		
    	
    
    	// for a 70's style color
    	
//    		for(int l=0; l<=SequenceLength; l++)
//	        {
//    			//this will make it darker colors
//	        	green = (int)( Math.random() * 255);
//	        	red =(int)( Math.random() * 255);
//	        	
//	        	blue = (int)(Math.random() * 255);
//	        	
//	            color[l]=new Color(red, green, blue);        
//	        }
    	  
    	  
    	    for(int l=0; l<=SequenceLength; l++)
	        {
    	    	
//	        	red--;
//	        	if(red==0)
//	        		red = 2;
//	        	blue++;
//	        	if (blue == 255)
//	        		blue = 0;
//	        	green--;
//	        	if (green == 0)
//	        		green = 200;
//
//    	    	//Black and white
//	             if(l%2==1)
//		            {
//		        		green = 255;
//		        		blue = 255;
//		        		red = 255;	
//		            }
//		            else
//		            {
//		            	green = 0;
//		            	red = 0;
//		            	blue = 0;
//		            }

//    	    	//loops through 3 colors
	            if(l%3==1)
	            {
	            	red = 49;
	            	green = 73;
	            	blue = 240;
	            }
	            else if(l%3==2)
	            {
	        		green = 7;
	        		blue = 7;
	        		red = 252;	
	            }
	            else
	            {
	            	green = 211;
	            	red = 5;
	            	blue = 31;
	            }
    	    	color[l]=new Color(red, green, blue);        
	        }
    	    //if we went though the seqeunce and didnt find a number 
			//outside the mandebrot set, we make the color at that point black as well
			//because it is in the set
			
			color[(int) SequenceLength]=Color.black;
		        //loops through all the points on the canvas
	
    		
    		for(x = 0; x < 500; x++)
			{
				for(y = 0; y < 500; y++)
				{
					
					//calculates the real and imaginary location based on the point on the canvas
						a = (RealNum - ViewSize) + 2*((ViewSize*x)/width);		
						b = (ImagNum + ViewSize) - 2*((ViewSize*y)/width);
							
						//a new complex number z is created with the new a and b as its coordinates
				
					
						ComplexNumber z = new ComplexNumber(a, b);
						ComplexNumber c = z;
			//c is set as the first z in the sequence
						c = z;
						
						for(j = 0; j <= SequenceLength; j++)
						{
							//c is squared and then added to z through the 
							//complex number class made and that becomes the new c
							c = c.complexAdd((c.complexSquare(c)), z);		
					
							//this will be the point of magnitude
							magZ = c.getPointMag(c);
				
							//if the square root of that point is greater than 2, 
							//break the loop and use that number as in index for colors
							if(Math.sqrt(magZ) > 2)
								break;
							
							//if it is in the mandelbrot set, color it black
								g.setColor(Color.black);
								g.fillRect(x, y, 1, 1);	;
								
						}
						
							//take the point in the sequence 
							//that we found a number outside the set and use it to index the color
							g.setColor(color[j]);
							g.fillRect(x, y, 1, 1);	
						}
					}
			}
			
			
				
	//main for the mandelbrot display
	public static void main (String[] args)
	{
		new MandelbrotDisplay();
	}
}

