package com.shpp.p2p.cs.abolgov.assignment7;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    //the global variable stores the name of what is plotting
    private String name;
    //a global array that contains the ranks of the name
    private int[] rank = new int[NDECADES];

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        update();
    }

    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        outputData.clear();
        update();
    }

    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        outputData.add(entry);
    }

    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawAll();
    }

    /**
     * a method that draws a table grid and displays a graph on the screen
     */
    private void drawAll() {

        int number;
        int cell = getWidth() / NDECADES;
        double decadeLength = Integer.toString(START_DECADE).length();
        double xPosition = 0;
        double yDecadePosition = (getHeight() - (GRAPH_MARGIN_SIZE / decadeLength));
        drawTable(decadeLength, xPosition, yDecadePosition, cell, START_DECADE);
        for (int i = 0; i < outputData.size(); i++) {
            number = i % 4;
            creater(outputData.get(i));
            drawGraph(xPosition, cell, number);
        }
    }

    /**
     * Displaying the table grid and decades on the screen
     *
     * @param decadeLength    string length
     * @param xPosition       Location on the screen x-coordinate
     * @param yDecadePosition Location on the screen н-coordinate
     * @param cell            The size of the cell in which decade is written
     * @param decade          starting decade variable
     */
    public void drawTable(double decadeLength, double xPosition, double yDecadePosition, int cell, int decade) {

        //top line
        drawLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE, Color.BLACK);
        //down line
        drawLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE, Color.BLACK);
        for (int i = 0; i < NDECADES; i++) {
            //vertical lines that separate one year from another
            drawLine(xPosition, 0, xPosition, getHeight(), Color.BLACK);
            //years at the bottom of the screen
            drawLabel(Integer.toString(decade),
                    xPosition + cell / decadeLength,
                    yDecadePosition);
            xPosition += cell;
            decade += 10;
        }
    }

    /**
     * method that draws a graph
     *
     * @param xPosition x-coordinates of the label and points
     * @param cell      the number by which the x coordinate changes
     * @param number    number corresponding to color (1-4)
     */
    private void drawGraph(double xPosition, int cell, int number) {
        int[] mass = ChangeYPosition();
        for (int i = 0; i < NDECADES; i++) {
            if (i + 1 != NDECADES) {
                drawGraphNameRank(xPosition, xPosition + cell, mass[i], mass[i + 1], colorChenger(number));
            }
            if (rank[i] != 0)
                nameLabel(name.substring(0, 1).toUpperCase() + name.substring(1) + " " + rank[i], xPosition, mass[i] + GRAPH_MARGIN_SIZE / 1.5, colorChenger(number));
            else
                nameLabel(name.substring(0, 1).toUpperCase() + name.substring(1) + "*", xPosition, mass[i] + GRAPH_MARGIN_SIZE / 1.5, colorChenger(number));
            xPosition += cell;
        }

    }

    /**
     * @param x     x position of first point
     * @param y     y position of first point
     * @param x2    x position of second point
     * @param y2    y position of second point
     * @param color line color
     */
    private void drawLine(double x, double y, double x2, double y2, Color color) {
        GLine graphLine = new GLine(x, y, x2, y2);
        graphLine.setColor(color);
        add(graphLine);
    }

    /**
     * @param text      label text
     * @param xPosition xPosition label
     * @param yPosition yPosition label
     */
    private void drawLabel(String text, double xPosition, double yPosition) {
        GLabel label = new GLabel(text, xPosition, yPosition);
        label.setFont(new Font("Verdana-17", Font.BOLD, 12));
        add(label);
    }

    /**
     * Method that sets the values of the global variable name and the array rank
     *
     * @param entry
     */
    public void creater(NameSurferEntry entry) {
        name = entry.getName();
        for (int j = 0; j < NDECADES; j++) {
            rank[j] = entry.getRank(j);
        }
    }

    /**
     * @param xFirst  x position of first point
     * @param xSecond x position of second point
     * @param yFirst  y position of first point
     * @param ySecond y position of second point
     * @param color   line color
     */
    private void drawGraphNameRank(double xFirst, double xSecond, double yFirst, double ySecond, Color color) {
        double yLine = GRAPH_MARGIN_SIZE;
        drawLine(xFirst, yLine + yFirst, xSecond, yLine + ySecond, color);
    }

    /**
     * Calculation of coordinates
     * (depending on the size of the application window)
     * for the points that make up the graph
     *
     * @return array of calculated coordinates
     */
    private int[] ChangeYPosition() {
        int[] finalMass = new int[NDECADES];
        double form = ((getHeight() - (GRAPH_MARGIN_SIZE * 2.0)) / (MAX_RANK));
        if (form == 0) {
            form = 1;
        }
        for (int i = 0; i < NDECADES; i++) {
            if (rank[i] != 0) {
                finalMass[i] = (int) (form * rank[i]);
            } else {
                finalMass[i] = (int) (form * MAX_RANK);
            }
        }
        return finalMass;
    }

    /**
     * @param name  name about which statistics are displayed
     * @param x     label x position
     * @param y     label y position
     * @param color color of label
     */
    private void nameLabel(String name, double x, double y, Color color) {
        GLabel surfLabel = new GLabel(name, x, y);
        surfLabel.setFont("Courrier-10");
        surfLabel.setColor(color);
        add(surfLabel);
    }

    /**
     * @param number gets the color number to be returned
     * @return returns the desired color
     */
    private Color colorChenger(int number) {
        return switch (number) {
            case 0 -> Color.BLUE;
            case 1 -> Color.RED;
            case 2 -> Color.MAGENTA;
            case 3 -> Color.BLACK;
            default -> Color.black;
        };
    }

    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}