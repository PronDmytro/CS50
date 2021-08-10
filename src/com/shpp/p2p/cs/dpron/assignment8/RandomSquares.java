package com.shpp.p2p.cs.dpron.assignment8;

import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.event.*;

public class RandomSquares extends WindowProgram implements RandomSquaresConstants {
    private Square squares[] = new Square[COUNT_OF_SQUARES];

    public void init() {
        squares = RandomSquaresLogic.makeSquares();
        drawSquares();

        addMouseListeners();
    }

    private void drawSquares() {
        for (int i = 0; i < COUNT_OF_SQUARES; i++) {
            add(squares[i]);
        }
    }

    /**
     * Change squares orientation when mouse is clicked
     * */
    public void mouseClicked(MouseEvent mouse) {
        for (int i = 0; i < COUNT_OF_SQUARES; i++) {
            squares[i].changeOrientation();
        }
    }

    /**
     * Drag squares when mouse is moved
     * */
    public void mouseMoved(MouseEvent mouse) {
        double x, y;
        for (int i = 0; i < COUNT_OF_SQUARES; i++) {
            if (squares[i].isMouseFollow) {
                if (Math.abs(squares[i].getX() - mouse.getX()) > SQUARES_SIZE &&
                        Math.abs(squares[i].getY() - mouse.getY()) > SQUARES_SIZE) {
                    if (mouse.getX() > squares[i].getX()) {
                        x = squares[i].getX() + 1;
                    } else {
                        x = squares[i].getX() - 1;
                    }
                    if (mouse.getY() > squares[i].getY()) {
                        y = squares[i].getY() + 1;
                    } else {
                        y = squares[i].getY() - 1;
                    }

                    x = RandomSquaresLogic.normalizeX(x);
                    y = RandomSquaresLogic.normalizeY(y);
                    squares[i].setLocation(x, y);
                }
            } else {
                if (Math.abs(squares[i].getX() - mouse.getX()) < SQUARES_SIZE * 3 &&
                        Math.abs(squares[i].getY() - mouse.getY()) < SQUARES_SIZE * 3) {
                    if (mouse.getX() < squares[i].getX()) {
                        x = squares[i].getX() - 1;
                    } else {
                        x = squares[i].getX() + 1;
                    }
                    if (mouse.getY() < squares[i].getY()) {
                        y = squares[i].getY() + 1;
                    } else {
                        y = squares[i].getY() - 1;
                    }

                    x = RandomSquaresLogic.normalizeX(x);
                    y = RandomSquaresLogic.normalizeY(y);
                    squares[i].setLocation(x, y);
                }
            }
        }
    }
}
