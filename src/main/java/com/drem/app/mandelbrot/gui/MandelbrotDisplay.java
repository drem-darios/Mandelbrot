/**
 * @author - Drem Darios
 */
package com.drem.app.mandelbrot.gui;


import com.drem.app.mandelbrot.factory.ColorFactory;
import com.drem.app.mandelbrot.math.ComplexNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MandelbrotDisplay implements MouseListener, ActionListener {
    public static final String DEFAULT_REAL_VALUE = "-0.4";
    public static final String DEFAULT_VIEW_VALUE = "1.1";
    public static final String DEFAULT_IMAGINARY_VALUE = "0";
    public static final String DEFAULT_SEQUENCE_LENGTH_VALUE = "50";

    //makes a new window, canvas, and color
    private JFrame window;
    private DisplayCanvas canvas;

    //Initialize the text fields
    private JTextField realTxt = new JTextField(12); //Real Location
    private JTextField imaginaryTxt = new JTextField(12); //Imaginary location
    private JTextField viewTxt = new JTextField(10); //View Size
    private JTextField sequenceLengthTxt = new JTextField(10); //Sequence Length

    private double viewSize; //the zoom
    private final int width = 500; // The canvas width
    private final int height = 500; // The canvas width

    private String realString;
    private ComplexNumber currentNumber = new ComplexNumber(0, 0);
    private String sequenceLengthString;
    private String imaginaryString;
    private String viewString;

    private static final String GENERATE_COMMAND_STRING = "Generate";
    private static final String RESET_COMMAND_STRING = "Reset";

    public MandelbrotDisplay() {
        init();
    }

    private void init() {
        JButton reset;
        JButton generate;

        //Labels for the text boxes. spaces are used to space out the labels correctly
        JLabel realLabel = new JLabel("Real center          ");
        JLabel imaginaryLabel = new JLabel("Imaginary center");
        JLabel viewLabel = new JLabel("View size               ");
        JLabel sequenceLengthLabel = new JLabel("Sequence Length");

        JLabel space = new JLabel("                        " +
                "                                "); //space is used to center the generate button

        generate = new JButton(GENERATE_COMMAND_STRING);
        reset = new JButton(RESET_COMMAND_STRING);

        //set the window settings, creates a canvas and adds everything to the window
        window = new JFrame("Mandelbrot Set");
        window.setSize(550, 750);

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 20, 10);
        window.setLayout(layout);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        canvas = new DisplayCanvas();
        canvas.setSize(width, height);
        canvas.addMouseListener(this);
        window.add(canvas);

        window.add(realLabel);
        window.add(realTxt);

        window.add(viewLabel);
        window.add(viewTxt);

        window.add(imaginaryLabel);
        window.add(imaginaryTxt);

        window.add(sequenceLengthLabel);
        window.add(sequenceLengthTxt);
        window.add(space);

        window.add(generate);
        window.add(reset);

        generate.addActionListener(this);
        reset.addActionListener(this);

        realTxt.setText(DEFAULT_REAL_VALUE);
        imaginaryTxt.setText(DEFAULT_IMAGINARY_VALUE);
        viewTxt.setText(DEFAULT_VIEW_VALUE);
        sequenceLengthTxt.setText(DEFAULT_SEQUENCE_LENGTH_VALUE);

        window.setVisible(true);

        redraw();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //gets the current location the mouse was clicked
        double xCurrent = e.getX();
        double yCurrent = e.getY();

        //recalculate a and b so it can be put in the text field
        double a = (currentNumber.getReal() - viewSize) + 2 * ((viewSize * xCurrent) / width);
        double b = (currentNumber.getImaginary() + viewSize) - 2 * ((viewSize * yCurrent) / width);

        realTxt.setText("" + a);
        imaginaryTxt.setText("" + b);

        //automatically zooms
        viewString = viewTxt.getText();
        viewSize = Double.parseDouble(viewString);

        if (e.getButton() == MouseEvent.BUTTON3) // Right click
        {
            viewTxt.setText("" + viewSize * 2);
        } else {
            viewTxt.setText("" + viewSize / 2);
        }

        redraw();

    }

    @Override public void mouseEntered(MouseEvent arg0) { }
    @Override public void mouseExited(MouseEvent arg0) { }
    @Override public void mousePressed(MouseEvent arg0) { }
    @Override public void mouseReleased(MouseEvent arg0) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase(RESET_COMMAND_STRING)) {

            realTxt.setText(DEFAULT_REAL_VALUE);
            imaginaryTxt.setText(DEFAULT_IMAGINARY_VALUE);
            viewTxt.setText(DEFAULT_VIEW_VALUE);
            sequenceLengthTxt.setText(DEFAULT_SEQUENCE_LENGTH_VALUE);
        }

        redraw();
    }

    private void redraw() {
        //take in the info from the text fields
        realString = realTxt.getText();
        double realNum = Double.parseDouble(realString);

        imaginaryString = imaginaryTxt.getText();
        double imaginaryNum = Double.parseDouble(imaginaryString);

        sequenceLengthString = sequenceLengthTxt.getText();
        int sequenceLength = Integer.parseInt(sequenceLengthString);

        viewString = viewTxt.getText();
        viewSize = Double.parseDouble(viewString);

        currentNumber = new ComplexNumber(realNum, imaginaryNum);
        ColorFactory colorFactory = new ColorFactory(sequenceLength);
        Color[] colors = colorFactory.getRedGreenBlue();
        canvas.redraw(currentNumber, viewSize, sequenceLength, colors);

    }

    //main for the mandelbrot display
    public static void main(String[] args) {
        new MandelbrotDisplay();
    }
}

