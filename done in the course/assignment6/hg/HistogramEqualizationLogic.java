package com.shpp.p2p.cs.abolgov.assignment6.hg;

public class HistogramEqualizationLogic {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] aggregateHistogram = new int[MAX_LUMINANCE + 1];
        //Loop through all brightness
        for (int k = 0; k != MAX_LUMINANCE + 1; k++) {
            //Loop through all pixels
            for (int[] luminance : luminances) {
                for (int i : luminance) {
                    //If the brightness of the given pixel is equal to k then the counter is +1
                    if (k == i) {
                        aggregateHistogram[k]++;
                    }
                }
            }
        }
        return aggregateHistogram;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {

        int[] cumulativeFrequencyArray = new int[histogram.length];
        //We set an element with a zero id, since in the future we will use id-1
        cumulativeFrequencyArray[0] = histogram[0] + cumulativeFrequencyArray[0];
        for (int i = 1; i < histogram.length; i++) {
            cumulativeFrequencyArray[i] = cumulativeFrequencyArray[i - 1] + histogram[i];
        }
        return cumulativeFrequencyArray;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        int totalPixelCount = 0;
        for (int[] luminance : luminances) {
            for (int j = 0; j < luminance.length; j++) {
                totalPixelCount++;
            }
        }
        return totalPixelCount;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        //In order not to call methods in the loop, variables are created
        int totalPixel = totalPixelsIn(luminances);
        int[] histogramForNew = histogramFor(luminances);
        int[] cumulativeHistogramNew = cumulativeSumFor(histogramForNew);
        //The variable is created in order to follow a specific value in the loop.
        int brightnessOnePixel;
        //Loop through all pixels
        for (int x = 0; x < luminances.length; x++) {
            for (int j = 0; j < luminances[x].length; j++) {
                brightnessOnePixel = luminances[x][j];
                //Enumerating all brightnesses
                for (int LUMINANCE = 0; LUMINANCE < MAX_LUMINANCE + 1; LUMINANCE++) {
                    //Finding the brightness of the current pixel
                    if (brightnessOnePixel == LUMINANCE) {
                        luminances[x][j] = MAX_LUMINANCE * cumulativeHistogramNew[LUMINANCE] / totalPixel;
                    }
                }
            }
        }
        return luminances;
    }
}