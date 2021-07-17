package com.shpp.p2p.cs.dpron.assignment4;

import acm.graphics.GOval;

/**
 * New class for ball extends from GOval
 **/
public class Ball extends GOval {
    /**
     * Ball velocity
     */
    public double vx, vy = 0.5;

    /**
     * Constructor
     **/
    public Ball(double x, double y, int w, int h) {
        super(x, y, w, h);
        setFilled(true);
    }
}
