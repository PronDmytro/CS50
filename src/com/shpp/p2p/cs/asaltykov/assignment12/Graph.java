package com.shpp.p2p.cs.asaltykov.assignment12;

import java.util.*;

/**
 * this class represents a single Graph object (vertex)
 * Each Graph contains a number(name), color, List of connections and information of usage
 *
 * @author - Andrew Saltykov
 */
class Graph {
    int number;
    int color;
    ArrayList<Integer> connectionList;
    boolean used;

    /**
     * Create new Graph object
     *
     * @param number vertex number(name)
     * @param color vertex color
     * @param list vertex list of connections
     */
    Graph(int number, int color, ArrayList<Integer> list) {
        this.number = number;
        this.color = color;
        this.connectionList = list;
        this.used = false;
    }
}