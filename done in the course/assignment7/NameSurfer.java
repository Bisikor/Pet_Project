package com.shpp.p2p.cs.abolgov.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {


    //Global variable that represents the graph
    private NameSurferGraph graph;
    //Global variable that stores data from a file
    private NameSurferDataBase documentData;
    //Global variable that receives user-entered information
    private JTextField nameGetter;

    public static void main(String[] args) {
        new NameSurfer().start(args);
    }

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the bottom of the window.
     */
    public void init() {
        documentData = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        createButtons();
        addActionListeners();
        add(graph);
    }

    /**
     * Creates Interactors
     */
    private void createButtons() {

        add(new JLabel("Name"), NORTH);
        nameGetter = new JTextField(TEXT_FIELD_SIZE);
        nameGetter.addActionListener(this);
        add(nameGetter, NORTH);
        add(new JButton("Graph"), NORTH);
        nameGetter.setActionCommand("Graph");
        add(new JButton("Clear"), NORTH);
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        int repitCheker;
        String event = e.getActionCommand();
        if (event.equals("Graph") && nameGetter.getText().length() > 0) {
            String name = nameGetter.getText().toLowerCase();
            NameSurferEntry entry = documentData.findEntry(name);
            if (entry != null) {
                for (repitCheker = 0; repitCheker < outputData.size(); repitCheker++) {
                    if (entry == outputData.get(repitCheker)) {
                        break;
                    }
                }
                if (outputData.size() <= repitCheker) {
                    graph.addEntry(entry);
                    graph.update();
                }
            }
        } else if (event.equals("Clear")) {
            graph.clear();
        }
        nameGetter.setText("");
    }
}
