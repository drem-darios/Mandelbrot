package com.drem.app.mandelbrot.factory;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorFactory {
    private int sequenceLength;
    private static Map<Integer, Color[]> the70sColors = new HashMap<>();
    private static Map<Integer, Color[]> rgbColors = new HashMap<>();

    public ColorFactory(int length) {
        this.sequenceLength = length;
    }

    public Color[] get70sStyle() {
        if (the70sColors.containsKey(sequenceLength)) {
            // Quickly return since same sequence generates same colors
            return the70sColors.get(sequenceLength);
        }

        int green;
        int blue;
        int red;
        Color[] color = new Color[sequenceLength];
        for (int l = 0; l < sequenceLength; l++) {
            //this will make it darker colors
            green = (int) (Math.random() * 255);
            red = (int) (Math.random() * 255);

            blue = (int) (Math.random() * 255);

            color[l] = new Color(red, green, blue);
        }
        the70sColors.put(sequenceLength, color);
        return color;
    }

    public Color[] getBlackAndWhite() {
        int green;
        int blue;
        int red;
        Color[] color = new Color[sequenceLength];
        for (int l = 0; l < sequenceLength; l++) {
            if (l % 2 == 1) {
                green = 255;
                blue = 255;
                red = 255;
            } else {
                green = 0;
                red = 0;
                blue = 0;
            }
            color[l] = new Color(red, green, blue);
        }

        return color;
    }

    public Color[] getRedGreenBlue() {
        if (rgbColors.containsKey(sequenceLength)) {
            // Quickly return since same sequence generates same colors
            return rgbColors.get(sequenceLength);
        }
        int green;
        int blue;
        int red;
        Color[] color = new Color[sequenceLength];
        for (int l = 0; l < sequenceLength; l++) {
            // loops through 3 colors
            if (l % 3 == 1) {
                red = 49;
                green = 73;
                blue = 240;
            } else if (l % 3 == 2) {
                green = 7;
                blue = 7;
                red = 252;
            } else {
                green = 211;
                red = 5;
                blue = 31;
            }
            color[l] = new Color(red, green, blue);
        }
        rgbColors.put(sequenceLength, color);
        return color;
    }
}
