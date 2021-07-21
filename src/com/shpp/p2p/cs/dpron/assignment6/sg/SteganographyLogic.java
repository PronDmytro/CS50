package com.shpp.p2p.cs.dpron.assignment6.sg;

import acm.graphics.*;

public class SteganographyLogic implements SteganographyConstants {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        //find width and height
        int width = (int) source.getWidth();
        int height = (int) source.getHeight();
        //get the array of picture's pixels
        int[][] pixelsArray = source.getPixelArray();
        //create a new array of arrays
        boolean[][] secretMessage = new boolean[height][width];
        //iterating through the array of pixels
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                //if red pixel of the array is even - false, else - true
                secretMessage[i][k] = GImage.getRed(pixelsArray[i][k]) % 2 != 0;
            }
        }
        return secretMessage;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        //find width and height
        int width = (int) source.getWidth();
        int height = (int) source.getHeight();
        //get the array of picture's pixels
        int[][] pixelsArray = source.getPixelArray();
        // create a new Gimage
        GImage hiddenMessage;
        //iterating through an array of pixels
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                //creating a new value for all the red, green and blue colors in pixels
                creatingNewPixel(i, k, pixelsArray, message);
            }
        }
        //combining the new picture
        hiddenMessage = new GImage(pixelsArray);

        return hiddenMessage;
    }

    /**
     * Creates a new pixel for the picture
     *
     * @param idxHeight   looping through height
     * @param idxWidth    looping through width
     * @param pixelsArray array of pixels
     * @param message     array of booleans
     */
    public static void creatingNewPixel(int idxHeight, int idxWidth, int[][] pixelsArray, boolean[][] message) {
        int redPixel = GImage.getRed(pixelsArray[idxHeight][idxWidth]);
        int greenPixel = GImage.getGreen(pixelsArray[idxHeight][idxWidth]);
        int bluePixel = GImage.getBlue(pixelsArray[idxHeight][idxWidth]);
        //implementing the logic of the program
        //if the red pixel is even and the secret message pixel is true
        if (redPixel % 2 == 0 && message[idxHeight][idxWidth]) {
            //add 1 pixel
            redPixel += 1;
            //if the red pixel is odd and secret message pixel is false
        } else if (redPixel % 2 != 0 && !message[idxHeight][idxWidth]) {
            redPixel -= 1;
        }
        //combining the new pixel
        pixelsArray[idxHeight][idxWidth] = GImage.createRGBPixel(redPixel, greenPixel, bluePixel);
    }
}