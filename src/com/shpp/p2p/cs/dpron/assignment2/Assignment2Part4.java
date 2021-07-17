package com.shpp.p2p.cs.dpron.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 400;

    private static final double FLAG_WIDTH = 120;
    private static final double FLAG_HEIGHT = 300;

    public void run() {
        setBackground(Color.LIGHT_GRAY);    //this background is set because so can be seen flag if it have parts white
        String countryName = "Belgium";     //set country name
        drawFlag(Color.BLACK, Color.YELLOW, Color.RED);    //draw flag with these params

        writeText(countryName); //write text line with country name
    }

    //draw flag with input colors
    private void drawFlag(Color color1, Color color2, Color color3) {
        /* Determine the coordinates of the center of the window. */
        double xCenter = getWidth() / 2.0;
        double yCenter = getHeight() / 2.0;
        drawNewPartFlag(xCenter - FLAG_WIDTH/2 - FLAG_WIDTH, yCenter - FLAG_HEIGHT / 2, color1);    //draw first flag part with these params
        drawNewPartFlag(xCenter - FLAG_WIDTH/2, yCenter - FLAG_HEIGHT / 2, color2);                 //draw second flag part
        drawNewPartFlag(xCenter - FLAG_WIDTH/2 + FLAG_WIDTH, yCenter - FLAG_HEIGHT / 2, color3);    //draw third flag part
    }

    //draw new part flag with input color
    private void drawNewPartFlag(double x, double y, Color color) {
        GRect rect = new GRect(x, y, FLAG_WIDTH, FLAG_HEIGHT);
        rect.setFillColor(color);
        rect.setColor(color);
        rect.setFilled(true);
        add(rect);
    }

    // draw new label with input country name
    private void writeText(String countryName) {
        GLabel label = new GLabel("Flag of " + countryName, getWidth(), getHeight());
        label.setFont("Roboto-28");
        label.setLocation(getWidth() - label.getWidth(), getHeight() - label.getDescent());
        add(label);
    }
}
