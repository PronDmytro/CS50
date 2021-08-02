package com.shpp.p2p.cs.dpron.assignment7;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implement the viewer for
 * the baby-name database described in the assignment handout.
 */

import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    /* Private instance variables */
    private JTextField Name;
    private NameSurferDataBase namesDB;
    private NameSurferGraph graph;

    /* Method: init() */

    /**
     * This method has the responsibility for reading in the database
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        //Set up initial display with ineractors and canvas
        JLabel label = new JLabel("Name ");
        add(label, SOUTH);

        // add input field
        Name = new JTextField(20);
        add(Name, SOUTH);
        Name.addActionListener(this);

        // add  btn
        JButton graph1 = new JButton("Graph");
        add(graph1, SOUTH);

        // add  btn
        JButton clear = new JButton("Clear");
        add(clear, SOUTH);

        // add graph
        graph = new NameSurferGraph();
        add(graph);

        addActionListeners();

        // reads the file of names and adds to the NameSurferDataBase
        namesDB = new NameSurferDataBase(NAMES_DATA_FILE);
    }

    /* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Clear")) {
            graph.clear();
            graph.update();
        } else if (e.getActionCommand().equals("Graph")) {
            String enteredName = Name.getText();
            Name.setText("");
            NameSurferEntry rankings = namesDB.findEntry(enteredName);
            if (rankings != null) {
                graph.addEntry(rankings);
                graph.update();
            }
        }
    }
}
