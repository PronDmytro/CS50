package com.shpp.p2p.cs.dpron.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {
    /*default width and height of the window.*/
    public static final int APPLICATION_WIDTH = 700;
    public static final int APPLICATION_HEIGHT = 200;

    //default circle characters
    private static final double CIRCLE_DIAMETER = 100;
    public static final int CIRCLE_COUNT = 10;

    public void run() {
        for (int i = 0; i <= CIRCLE_COUNT/2; i++) {
            downCircle((CIRCLE_DIAMETER) * i);
            upCircle((CIRCLE_DIAMETER) * i);
        }
    }

    //create down circle
    private void downCircle(double z) {
        double y = CIRCLE_DIAMETER / 2;
        drawNewOval(z, y, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    //create up circle
    private void upCircle(double z) {
        double x = CIRCLE_DIAMETER / 2;
        drawNewOval(x + z, 0, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
    }

    //draw new oval
    private void drawNewOval(double x, double y, double w, double h) {
        GOval o = new GOval(x, y, w, h);
        o.setFillColor(Color.GREEN);
        o.setColor(Color.RED);
        o.setFilled(true);
        add(o);
    }
}
