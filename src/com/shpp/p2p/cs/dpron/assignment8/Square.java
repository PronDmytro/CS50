package com.shpp.p2p.cs.dpron.assignment8;

import acm.graphics.GRect;

import java.awt.*;

public class Square extends GRect {
    boolean isMouseFollow;

    /**
     * Constructor #1
     *
     * @param x             Abscissa square position
     * @param y             Ordinate square position
     * @param w             Square width
     * @param isMouseFollow Square mouse follow
     */
    public Square(double x, double y, int w, boolean isMouseFollow) {
        super(x, y, w, w);
        this.setFilled(true);
        this.isMouseFollow = isMouseFollow;
    }

    /**
     * Constructor #2
     *
     * @param x             Abscissa square position
     * @param y             Ordinate square position
     * @param w             Square width
     * @param isMouseFollow Square mouse follow
     * @param color         Square standard color
     */
    public Square(double x, double y, int w, boolean isMouseFollow, Color color) {
        super(x, y, w, w);
        this.setColor(color);
        this.setFilled(true);
        this.isMouseFollow = isMouseFollow;
    }

    /**
     * Change square orientation
     */
    public void changeOrientation() {
        isMouseFollow = !isMouseFollow;
    }
}
