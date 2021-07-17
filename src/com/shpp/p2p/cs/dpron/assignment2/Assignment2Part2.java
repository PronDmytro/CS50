package com.shpp.p2p.cs.dpron.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
   //default width and height of the window.
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    //default circle params
    private static final int DIAMETER = 100;
    private static final int RADIUS = DIAMETER/2;

    public void run() {
        drawNewCircle(0, 0, DIAMETER);                                            //draw first circle
        drawNewCircle(getWidth() - DIAMETER, 0, DIAMETER);                        //draw second circle
        drawNewCircle(0, getHeight() - DIAMETER, DIAMETER);                       //draw third circle
        drawNewCircle(getWidth() - DIAMETER, getHeight() - DIAMETER, DIAMETER);   //draw fourth circle

        drawNewRec(RADIUS, RADIUS, getWidth()-DIAMETER, getHeight()-DIAMETER);  //draw rectangle
    }

    /*draw new circle with input params*/
    private void drawNewCircle(int x, int y, int d) {
        GOval o = new GOval(x, y, d, d);
        o.setFilled(true);
        o.setFillColor(Color.BLACK);
        add(o);
    }

    /*draw new rectangle with input params*/
    private void drawNewRec(int x, int y, int w, int h){
        GRect r = new GRect(x, y, w, h);
        r.setFilled(true);
        r.setFillColor(Color.WHITE);
        r.setColor(Color.WHITE);
        add(r);
    }
}
