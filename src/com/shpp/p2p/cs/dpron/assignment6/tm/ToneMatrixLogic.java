package com.shpp.p2p.cs.dpron.assignment6.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        // iterating through the array
        for (int i = 0; i < toneMatrix.length; i++) {
            //if this array's column is the column which is playing now
            if (toneMatrix[i][column]) {
                for (int k = 0; k < result.length; k++) {
                    //pass in the sound to the result array
                    result[k] += samples[i][k];
                }
            }
        }
        //finding the ost intensive value for normalizing
        double intensiveValue = mostIntensive(result);
        for (int i = 0; i < result.length; i++) {
            //normalizing the array between -1 and 1
            if (result[i] > 0 || result[i] < 0) {
                result[i] = result[i] / intensiveValue;
            }
        }
        return result;
    }

    /**
     * Goes through the whole array and look for the most intensive value
     *
     * @param array which shall be checked
     * @return the most intensive value
     */
    public static double mostIntensive(double[] array) {
        double intensive = array[0];

        for (double num : array) {
            if (Math.abs(intensive) < Math.abs(num))
                intensive = num;
        }

        return intensive;
    }
}