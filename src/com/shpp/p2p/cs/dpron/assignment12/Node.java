package com.shpp.p2p.cs.dpron.assignment12;

/**
 * Class to describe node.
 */
public class Node {
    private final int value;
    private final int x;
    private final int y;
    private boolean isVisited = false;

    public Node(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
    }

    /**
     * get temp value
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * get x coordinate
     *
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * get y coordinate
     *
     * @return y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * is visited coordinate
     *
     * @return boolean
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * set visited coordinate
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
