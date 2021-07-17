package com.shpp.p2p.cs.dpron.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* TODO:animation
 */
public class Assignment3Part6 extends WindowProgram {
    /*default width and height of the window.*/
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;
    public static final int RECT_WIDTH = 100;
    public static final int RECT_HEIGHT = 75;
    /* The amount of time to pause between frames (144fps). */
    private static final double PAUSE_TIME = 1000.0 / 144;

    /* timer start */
    long startTime = System.currentTimeMillis();

    public void run() {
        GRect rect = makeRec();
        add(rect);
        bounceRec(rect);
    }

    /**
     * Creates a rect that can be bounced, placing it in the upper-left corner of the screen.
     *
     * @return A rect that can be bounced.
     */
    private GRect makeRec() {
        GRect rect = new GRect(0, 0, RECT_WIDTH, RECT_HEIGHT);
        rect.setFilled(true);
        rect.setColor(Color.RED);
        return rect;
    }

    /**
     * Simulates a bouncing rect.
     *
     * @param rect The rect to bounce.
     */
    private void bounceRec(GRect rect) {
        //Track the velocity
        double x = 5;
        //Track the rate of increase of speed
        double velX = 0.02;

        //time(in milliseconds) when animation need stop
        int timeToEnd = 5000;

        boolean isMoveRight = true;

        while (isTime(timeToEnd)) {
            if (isMoveRight)
                rect.move(x, x);
            else
                rect.move(-x, -x);
            if ( rect.getX() > getWidth() - RECT_WIDTH)
                isMoveRight = false;
            else if (rect.getX()<0)
                isMoveRight = true;

            x += velX;

            pause(PAUSE_TIME);
        }
    }
    /* Timer and mechanical illusions moves */
    private boolean isTime(int timeToEnd) {
        pause(PAUSE_TIME);

        /* Timer ending */
        long endTime = System.currentTimeMillis();
        return (endTime - startTime) < timeToEnd;
    }
}

