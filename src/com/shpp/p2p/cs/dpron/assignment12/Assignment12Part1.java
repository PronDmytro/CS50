package com.shpp.p2p.cs.dpron.assignment12;

import java.util.*;
import java.util.List;


/**
 * Can count how many shadows in the picture.
 * Can work with jpg/png files.
 */
public class Assignment12Part1 implements Constants {

    private static final Stack<Node> stack = new Stack<>();

    public static void main(String[] args) {
        String filepath;
        if (args.length > 0) {
            filepath = FILE_PATH + args[0];
        } else {
            filepath = DEFAULT_FILE_PATH;
        }

        try {
            System.out.println("Silhouettes count (" + filepath + "): "+ findSilhouette(BinaryImage.binaryImage(filepath)));
        } catch (Exception e) {
            System.out.println("Check the specified file name or(and) its path\n" + e.getMessage());
        }
    }

    /**
     * Counting branches of nodes which matches given integer value.
     * The branch corresponds to an inextricable cluster of pixels in the image (silhouette).
     *
     * @param nodes - Node[][], each element of this array corresponds to pixel on image.
     *              Element value may be:
     *              0 - if pixel has background color
     *              1 - otherwise.
     * @return amount of branches.
     */
    public static int findSilhouette(Node[][] nodes) {
        List<Integer> branches = new ArrayList<>();

        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                if (nodes[x][y].getValue() == COMPARE_VALUE && !nodes[x][y].isVisited()) {
                    branches.add(goOnBranch(nodes, x, y));
                }
            }
        }

        return filter(branches);
    }

    /**
     * Go through an inextricable cluster of nodes with a given integer value using deep search algorithm with using of stack.
     *
     * @param nodes    - array of nodes, Node[x][y]
     * @param currentX - row's position index in Node[][]
     * @param currentY - column's position index in Node[][]
     * @return integer - amount of visited nodes (all nodes from branch)
     */
    private static int goOnBranch(Node[][] nodes, int currentX, int currentY) {
        int square = 0;
        stack.push(nodes[currentX][currentY]);
        while (!stack.empty()) {
            Node current = stack.pop();
            int x = current.getX();
            int y = current.getY();
            square++;

            if (x + 1 < nodes.length) {
                pushNode(nodes, x + 1, y);
            }
            if (y + 1 < nodes[x].length) {
                pushNode(nodes, x, y + 1);
            }
            if (x - 1 > 0) {
                pushNode(nodes, x - 1, y);
            }
            if (y - 1 > 0) {
                pushNode(nodes, x, y - 1);
            }
        }
        return square;
    }

    /**
     * If nodes[x][y] correspond to the given value and is not visited, - add it to stack, then mark them as visited.
     */
    private static void pushNode(Node[][] nodes, int x, int y) {
        if (nodes[x][y].getValue() == COMPARE_VALUE && !nodes[x][y].isVisited()) {
            stack.push(nodes[x][y]);
            nodes[x][y].setVisited(true);
        }
    }

    /**
     * Returns the number of elements from List <Integer> whose value must be greater than the value of the MAX element
     * multiply the threshold
     */
    private static int filter(List<Integer> branches) {
        int amount = 0;
        int thresholdSquare = (int) (Collections.max(branches) * THRESHOLD_SQUARE);

        for (Integer value : branches) {
            if (value > thresholdSquare)
                amount++;
        }

        return amount;
    }
}