/**
 * @author - Drem Darios
 */
package com.drem.app.mandelbrot.gui;

import com.drem.app.mandelbrot.math.ComplexNumber;

import java.awt.*;

public class DisplayCanvas extends Canvas {

    public DisplayCanvas() {
    }

    public void redraw(ComplexNumber currentNumber, double viewSize, int sequenceLength, Color[] colors) {
        Color[][] colorPosition = getColorPositions(currentNumber, viewSize, sequenceLength, colors);
        drawColors(colorPosition);
    }

    private Color[][] getColorPositions(ComplexNumber currentNumber, double viewSize, int sequenceLength, Color[] colors) {
        Color[][] colorPosition = new Color[getWidth()][getHeight()];
        //loops through all the points on the canvas
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                //calculates the real and imaginary location based on the point on the canvas
                double a = (currentNumber.getReal() - viewSize) + 2 * ((viewSize * x) / getWidth());
                double b = (currentNumber.getImaginary() + viewSize) - 2 * ((viewSize * y) / getWidth());

                //a new complex number z is created with the new a and b as its coordinates
                ComplexNumber z = new ComplexNumber(a, b);
                ComplexNumber c = new ComplexNumber(a, b);
                //c is set as the first z in the sequence

                for (int i = 0; i < sequenceLength; i++) {
                    //c is squared and then added to z through the
                    //complex number class made and that becomes the new c
                    c = c.complexAdd((c.complexSquare(c)), z);

                    //this will be the point of magnitude
                    double magZ = c.getPointMag();

                    //if the square root of that point is greater than 2,
                    //break the loop and use that number as in index for colors
                    if (Math.sqrt(magZ) > 2) {
                        colorPosition[x][y] = colors[i];
                        break;
                    }
                }
            }
        }

        return colorPosition;
    }

    private void drawColors(Color[][] colorPosition) {
        Graphics g = getGraphics();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (colorPosition[x][y] == null) {
                    //if we went though the sequence and didn't find a number
                    //outside the Mandelbrot set, we make the color at that point black as well
                    //because it is in the set
                    colorPosition[x][y] = Color.black;
                }
                g.setColor(colorPosition[x][y]);
                g.fillRect(x, y, 1, 1);
            }
        }
    }
}
