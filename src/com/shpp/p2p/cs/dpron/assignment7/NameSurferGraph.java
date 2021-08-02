package com.shpp.p2p.cs.dpron.assignment7;

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

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
    /* Private instance variables */
    private final ArrayList<NameSurferEntry> entriesDisplayed;

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);
        entriesDisplayed = new ArrayList<>();
    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entriesDisplayed.clear();
    }


    /* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entriesDisplayed.add(entry);
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
        drawGraph();
        if (entriesDisplayed.size() > 0) {
            for (int i = 0; i < entriesDisplayed.size(); i++) {
                NameSurferEntry entries = entriesDisplayed.get(i);
                drawEntry(entries, i);
            }
        }
    }

    /**
     * Draw full graph
     */
    private void drawGraph() {
        drawVerticalLines();
        drawHorizontalLines();
        drawDecades();
    }

    /**
     * Draw the vertical lines in the graph
     */
    private void drawVerticalLines() {
        for (int i = 0; i < NDECADES; i++) {
            double x = i * (getWidth() / NDECADES);

            GLine line = new GLine(x, i, x, getHeight());
            add(line);
        }
    }

    /**
     * Draws the horizontal lines in the graph
     */
    private void drawHorizontalLines() {
        double x1 = 0;
        double x2 = getWidth();
        double yLine1 = getHeight() - GRAPH_MARGIN_SIZE;

        GLine line1 = new GLine(x1, yLine1, x2, yLine1);
        add(line1);

        double yLine2 = GRAPH_MARGIN_SIZE;
        GLine line2 = new GLine(x1, yLine2, x2, yLine2);
        add(line2);
    }

    /**
     * Draw the decade markers
     */
    private void drawDecades() {
        for (int i = 0; i < NDECADES; i++) {
            int decade = START_DECADE;
            decade += 10 * i;
            String label = Integer.toString(decade);

            double y = getHeight() - GRAPH_MARGIN_SIZE / 4;
            double x = 2 + i * (getWidth() / NDECADES);

            GLabel displayedDecade = new GLabel(label, x, y);
            add(displayedDecade);
        }
    }

    /**
     * Draw the graph line with the name and rank labels
     */
    private void drawEntry(NameSurferEntry entry, int entryNumber) {
        //draws the graph line
        for (int i = 0; i < NDECADES - 1; i++) {
            int ranking1 = entry.getRank(i);
            int ranking2 = entry.getRank(i + 1);

            GLine line = makeLine(i, ranking1, ranking2);
            setColor(line, entryNumber);
            add(line);
        }

        //adds in the label with the Name and Rank number
        for (int i = 0; i < NDECADES; i++) {
            String name = entry.getName();
            int rank = entry.getRank(i);

            GLabel nameLabel = makeLabel(name, i, rank);
            setColor(nameLabel, entryNumber);
            add(nameLabel);
        }
    }

    /**
     * Make new line for graph
     */
    GLine makeLine(int rankNum, int ranking1, int ranking2) {
        double x1 = rankNum * (getWidth() / NDECADES);
        double x2 = (rankNum + 1) * (getWidth() / NDECADES);
        double y1, y2;

        if (ranking1 != 0 && ranking2 != 0) {
            y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * ranking1 / MAX_RANK;
            y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * ranking2 / MAX_RANK;
        } else if (ranking1 == 0 && ranking2 == 0) {
            y1 = getHeight() - GRAPH_MARGIN_SIZE;
            y2 = getHeight() - GRAPH_MARGIN_SIZE;
        } else if (ranking1 == 0) {
            y1 = getHeight() - GRAPH_MARGIN_SIZE;
            y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * ranking2 / MAX_RANK;
        } else {
            y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * ranking1 / MAX_RANK;
            y2 = getHeight() - GRAPH_MARGIN_SIZE;
        }

        return new GLine(x1, y1, x2, y2);
    }

    /**
     * Make label for each 'dot' in line
     */
    GLabel makeLabel(String name, int rankNum, int rank) {
        int spacing = 5;
        String rankString = Integer.toString(rank);
        String label = name + " " + rankString;
        double x = rankNum * (getWidth() / NDECADES) + spacing, y;

        if (rank != 0) {
            y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE * 2) * rank / MAX_RANK - spacing;
        } else {
            label = name + " *";
            y = getHeight() - GRAPH_MARGIN_SIZE - spacing;
        }
        return new GLabel(label, x, y);
    }

    /**
     * Set color to any GObject by the incoming number
     */
    void setColor(GObject obj, int entryNumber) {
        if (entryNumber % 4 == 1) {
            obj.setColor(Color.RED);
        } else if (entryNumber % 4 == 2) {
            obj.setColor(Color.BLUE);
        } else if (entryNumber % 4 == 3) {
            obj.setColor(Color.MAGENTA);
        }
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
