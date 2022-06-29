package com.shpp.p2p.cs.abolgov.assignment6.tm;

import java.util.Arrays;

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
        //Variables to keep track of whether the value is greater than 1 or -1
        double maxIntensity = 1, minIntensity = -1;
        //Iterating over the rows of a matrix
        for (int row = 0; row < toneMatrix.length; row++) {
            //If in a row a column is active only then iterate over the columns
            if (toneMatrix[row][column]) {
                for (int g = 0; g < samples[column].length; g++) {
                    //Creating sound
                    result[g] = samples[row][g] + result[g];
                }
            }
        }
        for (double v : result) {
            //Looking for maximum intensity
            maxIntensity = Math.max(maxIntensity, v);
            minIntensity = Math.min(minIntensity, v);
        }


        //Edit the intensity if it is more than -1
        if (Math.abs(minIntensity) > maxIntensity && minIntensity < -1) {
            for (int j = 0; j < result.length; j++) {
                ////We divide the total intensity by the minimum
                result[j] = result[j] / minIntensity;
            }
            //Edit the intensity if it is more than 1
        } else if (maxIntensity > 1) {
            for (int j = 0; j < result.length; j++) {
                //We divide the total intensity by the maximum
                result[j] = result[j] / maxIntensity;
            }
            //If the intensity is 0 we do not edit anything (as it is said in the task, we return silence)
        } else if (minIntensity == 0 && maxIntensity == 0) {
            Arrays.fill(result, 0);
        }
        return result;
    }
}
