package com.shpp.p2p.cs.dpron.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/* TODO: Build pyramid
 */
public class Assignment3Part4 extends WindowProgram {
    /*default width and height of the window.*/
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 400;

    /*pyramid params*/
    private static final int BRICK_HEIGHT = 20;
    private static final int BRICK_WIDTH = 20;
    private static final int BRICKS_IN_BASE = 7;
    private static final Color BRICKS_COLOR = Color.RED;

    public void run() {
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            int bricksInRow = BRICKS_IN_BASE - i; //counter for number of bricks
            for (int j = 0; j < bricksInRow; j++) {
                buildNewBrick(j, i);
            }
        }
    }

    /**
     * Build new brick.
     *
     * @param rowNum   Number to be incremented.
     * @param brickNum Degree to which you want to increase the number.
     */
    private void buildNewBrick(int rowNum, int brickNum) {
        double sqStartX = ((double) getWidth() - (BRICKS_IN_BASE * BRICK_WIDTH)) / 2;
        double x = sqStartX + brickNum * BRICK_WIDTH + (rowNum) * ((double) BRICK_WIDTH / 2); //adjusted for brick and row
        double y = getHeight() - BRICK_HEIGHT * (rowNum + 1);
        drawNewRec(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }

    /**
     * Draw new Rectangle.
     */
    private void drawNewRec(double x, double y, double w, double h) {
        GRect rect = new GRect(x, y, w, h);
        rect.setColor(Color.BLACK);
        rect.setFilled(true);
        rect.setFillColor(BRICKS_COLOR);
        add(rect);
    }
}
