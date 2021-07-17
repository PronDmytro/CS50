package com.shpp.p2p.cs.dpron.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {
    /*default width and height of the window.*/
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    public void run() {
        /* Determine the coordinates of the center of the window. */
        double xCenter = getWidth() / 2.0;
        double yCenter = getHeight() / 2.0;

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                drawNewRec(xCenter - (((BOX_SIZE + BOX_SPACING)*NUM_COLS)/2) +(BOX_SIZE + BOX_SPACING) * j, yCenter - (((BOX_SIZE + BOX_SPACING)*NUM_ROWS)/2) +(BOX_SIZE + BOX_SPACING) * i, BOX_SIZE, BOX_SIZE);
            }
        }
    }

    //draw new rectangle
    private void drawNewRec(double x, double y, double w, double h) {
        GRect r = new GRect(x, y, w, h);
        r.setFilled(true);
        r.setFillColor(Color.BLACK);
        add(r);
    }
}
