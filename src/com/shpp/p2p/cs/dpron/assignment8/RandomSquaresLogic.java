package com.shpp.p2p.cs.dpron.assignment8;


import java.util.Random;

public class RandomSquaresLogic implements RandomSquaresConstants {

    /**
     * Make squares
     *
     * @return initialized squares
     */
    public static Square[] makeSquares() {
        Square[] squares = new Square[COUNT_OF_SQUARES];
        for (int i = 0; i < COUNT_OF_SQUARES; i++) {
            squares[i] = new Square(RandomSquaresLogic.randomX(), RandomSquaresLogic.randomY(), SQUARES_SIZE, RandomSquaresLogic.randomFollow());
        }
        return squares;
    }

    /**
     * @return random num in range APPLICATION_WIDTH
     */
    public static int randomX() {
        return getRandomNumber(0, APPLICATION_WIDTH);
    }

    /**
     * @return random num in range APPLICATION_HEIGHT
     */
    public static int randomY() {
        return getRandomNumber(0, APPLICATION_HEIGHT);
    }

    /**
     * @return random boolean for squares mouse follow
     */
    public static boolean randomFollow() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Normalize X
     *
     * @param x x coordinate
     * @return normalized x
     */
    public static double normalizeX(double x) {
        if (x < 0) {
            x = 0;
        } else if (x > APPLICATION_WIDTH - SQUARES_SIZE) {
            x = APPLICATION_WIDTH - SQUARES_SIZE;
        }
        return x;
    }

    /**
     * Normalize Y
     *
     * @param y y coordinate
     * @return normalized y
     */
    public static double normalizeY(double y) {
        if (y < 0) {
            y = 0;
        } else if (y > APPLICATION_HEIGHT - SQUARES_SIZE) {
            y = APPLICATION_HEIGHT - SQUARES_SIZE;
        }
        return y;
    }


    /**
     * @param min minimum number
     * @param max maximum number
     * @return random number
     */
    private static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
