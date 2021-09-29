package com.shpp.p2p.cs.asaltykov.assignment12;

import acm.graphics.GImage;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class defines number of object in image
 * <p>
 * Rules:
 * 1. if image has gradient background - set COLOR_SENSITIVITY 3;
 * 2. if image has gradient objects - set COLOR_SENSITIVITY 1-2;
 * 3. if image has gradient objects and background - are you serious?;
 * 4. if image has many small objects - reduce OBJECT_SENSITIVITY;
 * 5. if you have sack overflow error - change AM to "-Xss8m" - 8 mb or more
 *
 * @author - Andrew Saltykov
 */
public class Assignment12Part1 extends WindowProgram {

    /* size for resizing */
    private static final int SET_WIDTH = 300;

    /* Array for saving  color data */
    private static int[][] ar;

    /* Color reduction
     *
     * From 1 to 3:
     * 1 - grayscale
     * 2 - 10 colors (black, white and 8 levels of grey)
     * 3 - 2 colors (black and white) - default
     *  */
    private static final int COLOR_SENSITIVITY = 2;

    /* coefficient for changing Black and White color reduction */
    private static final int BLACK_COLOR_LEVEL = 170;


    /* Sensitivity for objects (garbage filter)  */
    private static final int OBJECT_SENSITIVITY = 1;

    /* ArrayList og graph vertex */
    private static final ArrayList<Graph> graphsList = new ArrayList<>();

    /* Objects counter */
    private static int objCount = 0;

    /* background color */
    private static int background;

    /* Pixels counter (for garbage filter)  */
    private static int pixelCount = 0;

    /**
     * Format image
     * Start DFS algorithm
     * Show results of counting objects in image
     *
     * @param args image filename
     */
    public static void main(String[] args) {

        // check args
        if (args.length > 0) {

            //create array of color data
            createAndFormatImg(args);

            // set background color
            background = findBackgroundColor();

            // start DFS
            dfs(findStar());

            // show results
            System.out.println("\n" + objCount + " objects in this image: " + args[0]);

        } else {
            // OOps, you forgot to set image filename
            System.err.println("You need to enter filename to args!");
        }
    }

    /**
     * Copy pixel data to array
     * Resize image
     * Convert color to grayscale
     * Add data in array of graphs vertex
     *
     * @param args image filename
     */
    private static void createAndFormatImg(String[] args) {

        // copy pixel data to array
        try {
            ar = new GImage(args[0]).getPixelArray();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // if width of image more than SET_WIDTH - resize it
        if (ar[0].length > SET_WIDTH) {
            resize();
        }


        int counter = 0;

        // for each pixel in array
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {

                //find alpha canal color
                double alpha = (double) GImage.getAlpha(ar[i][j]) / 255;

                //convert color to grayscale
                int grey = (int) (
                        (0.299 * ((1 - alpha) * 255 + (alpha * GImage.getRed(ar[i][j])))) +
                                (0.587 * ((1 - alpha) * 255 + (alpha * GImage.getGreen(ar[i][j])))) +
                                (0.114 * ((1 - alpha) * 255 + (alpha * GImage.getBlue(ar[i][j]))))
                );

                //convert color with COLOR_SENSITIVITY level
                grey = changeColor(grey);

                //add vertex data to graph array
                addConnection(i, j, counter, grey);

                //simple counter for vertex
                counter++;
            }
        }
    }

    /**
     * Resize image resize according to SET_WIDTH
     */
    private static void resize() {

        //columns (with size)
        int col = SET_WIDTH;

        //rows (height size)
        int row = col * ar.length / ar[0].length;

        //set size of resizing image
        int[][] resizeAr = new int[row][col];

        // resizing coefficient
        double coef = (double) ar[0].length / SET_WIDTH;

        //set color for each pixel in resizing image with resizing coefficient
        for (int i = 0; i < (row); i++) {
            for (int j = 0; j < (col); j++) {
                resizeAr[i][j] = ar[(int) (coef * i)][(int) (coef * j)];
            }
        }

        //save new pixel data to array
        ar = resizeAr;
    }

    /**
     * convert color with COLOR_SENSITIVITY level (2-3)
     *
     * @param grey color to convert
     * @return converting color
     */
    private static int changeColor(int grey) {

        // for level 3 - change color to Black and White
        if (COLOR_SENSITIVITY == 3) {
            if (grey >= BLACK_COLOR_LEVEL) grey = 255;
            else grey = 0;

            // for level 3 - change color evenly
        } else if (COLOR_SENSITIVITY == 2) {
            if (grey > 225) grey = 255;
            else if (grey > 200 && grey < 225) grey = 225;
            else if (grey > 175 && grey < 200) grey = 200;
            else if (grey > 150 && grey < 175) grey = 175;
            else if (grey > 125 && grey < 150) grey = 150;
            else if (grey > 100 && grey < 125) grey = 125;
            else if (grey > 75 && grey < 100) grey = 100;
            else if (grey > 50 && grey < 75) grey = 75;
            else if (grey > 25 && grey < 50) grey = 50;
            else if (grey < 25) grey = 0;
        }
        return grey;
    }

    /**
     * Add vertex data to graph array
     *
     * @param i       column of image pixel grid
     * @param j       row of image pixel grid
     * @param counter number of vertex
     * @param color   color of vertex
     */
    private static void addConnection(int i, int j, int counter, int color) {

        //List of vertex connections
        ArrayList<Integer> list = new ArrayList<>();

        //Add vertex connections
        if (i == 0) {
            if (j == 0) {
                list.add(counter + ar[0].length);
                list.add(counter + 1);
            } else if (j < (ar[0].length - 1)) {
                list.add(counter - 1);
                list.add(counter + 1);
                list.add(counter + ar[0].length);
            } else if (j == (ar[0].length - 1)) {
                list.add(counter - 1);
                list.add(counter + ar[0].length);
            }
        } else if (i < (ar.length - 1)) {
            if (j == 0) {
                list.add(counter - ar[0].length);
                list.add(counter + ar[0].length);
                list.add(counter + 1);
            } else if (j < (ar[0].length - 1)) {
                list.add(counter + ar[0].length);
                list.add(counter - 1);
                list.add(counter + 1);
                list.add(counter - ar[0].length);
            } else if (j == (ar[0].length - 1)) {
                list.add(counter + ar[0].length);
                list.add(counter - ar[0].length);
                list.add(counter - 1);
            }
        } else if (i == (ar.length - 1)) {
            if (j == 0) {
                list.add(counter + 1);
                list.add(counter - ar[0].length);
            } else if (j < (ar[0].length - 1)) {
                list.add(counter + 1);
                list.add(counter - ar[0].length);
                list.add(counter - 1);
            } else if (j == (ar[0].length - 1)) {
                list.add(counter - ar[0].length);
                list.add(counter - 1);
            }
        }

        //add vertex to graph list
        graphsList.add(new Graph(counter, color, list));
    }


    /**
     * find background color
     *
     * @return background color
     */
    private static int findBackgroundColor() {
        int counter = 0;

        //white color as default
        int answer = 255;

        //create HashMap
        HashMap<Integer, Integer> map = new HashMap<>();

        //for width size of image
        for (int i = 0; i < ar[0].length; i++) {

            //for first pixel line
            int colorTop = graphsList.get(i).color;

            //for last pixel line
            int colorBot = graphsList.get(graphsList.size() - ar[0].length + i).color;

            //add colors data in HashMap
            if (map.containsKey(colorTop)) {
                map.put(colorTop, (map.get(colorTop) + 1));
            } else map.put(colorTop, 1);
            if (map.containsKey(colorBot)) {
                map.put(colorBot, (map.get(colorBot) + 1));
            } else map.put(colorBot, 1);
        }

        //find witch color is more
        for (HashMap.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > counter) {
                counter = entry.getValue();
                answer = entry.getKey();
            }
        }

        return answer;
    }


    /**
     * finding star vertex for DFS
     */
    private static int findStar() {
        int start = 0;
        //for each vertex in graphList
        for (Graph g : graphsList) {

            //in vertex color is backgroundColor - set star position
            if (g.color == background) {
                start = g.number;
                break;
            }
        }
        return start;
    }

    /**
     * DFS algorithm for looking in pixel array objects
     * with recursion
     *
     * @param start start vertex with background color
     */
    private static void dfs(int start) {

        //set vertex used (visited)
        graphsList.get(start).used = true;

        //this vertex color
        int color = graphsList.get(start).color;

        int next;

        // if we find object another(not background) color
        if (color != background) {

            //for each vertex neighbor
            for (int i = 0; i < graphsList.get(start).connectionList.size(); i++) {

                //set next vertex
                next = graphsList.get(start).connectionList.get(i);

                //if next vertex still not visited and still not background color
                if (!graphsList.get(next).used && graphsList.get(next).color != background) {

                    //start to count pixel (for garbage filter)
                    pixelCount++;

                    //start DFS for this vertex(next)
                    dfs(next);
                }
            }

            //if vertex hasn`t any not background color neighbor
        } else {

            //check garbage filter
            if (pixelCount > OBJECT_SENSITIVITY) {
                //add object to counter
                objCount++;
            }

            //discard pixelcounter
            pixelCount = 0;

            //for each neighbor of background vertex
            for (int i = 0; i < graphsList.get(start).connectionList.size(); i++) {

                //set next vertex
                next = graphsList.get(start).connectionList.get(i);

                //if next vertex not visited
                if (!graphsList.get(next).used) {

                    //start DFS for this vertex(next)
                    dfs(next);
                }
            }
        }
    }
}
