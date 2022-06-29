package com.shpp.p2p.cs.abolgov.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment3Part4 extends WindowProgram {

    public static final int BRICK_WIDTH = 27;//The width of the rectangle that makes up the pyramid
    public static final int BRICK_HEIGHT = 25;//The height of the rectangle that makes up the pyramid
    public static final int BRICKS_IN_BASE = 20;//Number of rectangles at the base of the pyramid
    private static final Color COLOR_1 = new Color(244, 164, 56);

    /**
     * Draw a  pyramid
     */
    public void run() {
        applicationSizeSetter();
        drawPyramid();
    }

    /**
     * A method that resizes the application window depending on the size of the rendered pyramid
     */
    public void applicationSizeSetter() {
        double x = 400;
        double y = 400;

        if (x < (BRICKS_IN_BASE * BRICK_WIDTH) + (BRICKS_IN_BASE * BRICK_WIDTH) / 6.0) {
            x = ((BRICKS_IN_BASE * BRICK_WIDTH) + (BRICKS_IN_BASE * BRICK_WIDTH) / 6.0);
        }
        if (y < (BRICKS_IN_BASE * BRICK_HEIGHT) + (BRICKS_IN_BASE * BRICK_HEIGHT) / 6.0) {
            y = ((BRICKS_IN_BASE * BRICK_HEIGHT) + (BRICKS_IN_BASE * BRICK_HEIGHT) / 6.0);
        }
        this.setSize((int) x, (int) y);
    }

    /**
     * Creating the rectangles that make up the pyramid
     *
     * @param coordinateX x-coordinate (width)
     * @param coordinateY y-coordinate (height)
     */
    public void rectAdd(double coordinateX, double coordinateY) {
        GRect rect = new GRect(coordinateX, coordinateY, BRICK_WIDTH, BRICK_HEIGHT);
        rect.setFilled(true);
        rect.setFillColor(COLOR_1);
        rect.setColor(Color.WHITE);
        add(rect);
    }

    /**
     * Calculate the positions of each level of the pyramid in the Y coordinate
     */
    private void drawPyramid() {
        double onFloorY = getHeight();
        for (int i = BRICKS_IN_BASE; i > 0; i--) {
            //We consider the position of each next level of the pyramid along the Y coordinate
            onFloorY = onFloorY - BRICK_HEIGHT;
            drawOnFloor(i, onFloorY);

        }
    }

    /**
     * Draw a pyramid in the middle of the application window on the X coordinate
     * and at the bottom of the window on the Y coordinate
     *
     * @param i        The number of blocks in the current level of the pyramid
     * @param onFloorY The positions of each level of the pyramid in the Y coordinate
     */
    private void drawOnFloor(int i, double onFloorY) {
        //Calculate the middle for drawing along the x coordinate
        double middleX = (getWidth() / 2.0) + BRICK_WIDTH * i / 2.0;
        for (int j = i; j > 0; j--) {
            //We consider the position of each next blocks of the pyramid along the X coordinate
            middleX = middleX - BRICK_WIDTH;
            //Draw blocks according to the calculated coordinates
            rectAdd(middleX, onFloorY);

        }
    }
}
