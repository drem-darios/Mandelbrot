/**
 * @author - Drem Darios
 */
package com.drem.app.mandelbrot.math;

public class ComplexNumber 
{
	private double real;
	private double imaginary;
	
	//constructor for a complex number
	public ComplexNumber(double r, double i)
	{
		real = r;
		imaginary = i;
	}

	//adds two complex numbers to each other. this is used to add the z^2 and the original z
	public static ComplexNumber complexAdd(ComplexNumber z, ComplexNumber w)
	{
		double real = z.real + w.real;
		double imaginary = z.imaginary + w.imaginary;
		
		return new ComplexNumber(real, imaginary); //return the new complex number
	}
	
	//squares a complex number for the sequence
	public static ComplexNumber complexSquare(ComplexNumber z)
	{
		//uses the formula given to calculate the real and imaginary locations
		double real = ((z.real*z.real - z.imaginary*z.imaginary));
		double imaginary = (2*z.real*z.imaginary);
		
		//returns the new complex number
		return new ComplexNumber(real, imaginary);
	}
	
	//the point of magnitude. this number will be square rooted to find if a point is in or out of the set
	public double getPointMag(ComplexNumber c)
	{
		return this.real*this.real + this.imaginary*this.imaginary;
	}
}
