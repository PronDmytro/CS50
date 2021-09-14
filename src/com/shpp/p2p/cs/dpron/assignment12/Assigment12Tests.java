package com.shpp.p2p.cs.dpron.assignment12;

import java.util.Arrays;

public class Assigment12Tests {
    public static void main(String[] args) {
        checkCase(new String[]{}, "4");
        checkCase(new String[]{"assets/silhouettes/test.png"}, "4");
        checkCase(new String[]{"assets/silhouettes/img2.jpg"}, "5");
        checkCase(new String[]{"assets/silhouettes/img3.jpg"}, "4");
        checkCase(new String[]{"assets/silhouettes/img5.jpg"}, "41");
        checkCase(new String[]{"assets/silhouettes/img6.jpg"}, "6");
        checkCase(new String[]{"assets/silhouettes/img7.jpg"}, "6");
        checkCase(new String[]{"assets/silhouettes/img8.jpg"}, "0");
        checkCase(new String[]{"assets/silhouettes/img9.jpg"}, "0");
        checkCase(new String[]{"assets/silhouettes/img10.jpg"}, "4");
        checkCase(new String[]{"assets/silhouettes/img11.jpg"}, "6");
        checkCase(new String[]{"assets/silhouettes/img12.jpg"}, "5");
        checkCase(new String[]{"assets/silhouettes/img13.jpg"}, "3");
        checkCase(new String[]{"assets/silhouettes/img15.jpg"}, "3 | 4");
        checkCase(new String[]{"assets/silhouettes/img16.png"}, "1");
        checkCase(new String[]{"assets/silhouettes/img17.png"}, "1");
        checkCase(new String[]{"assets/silhouettes/img18.jpg"}, "4");
    }

    private static void checkCase(String[] args, String expected) {
        System.out.println("file path: " + Arrays.toString(args));
        System.out.print("   actual: ");
        try {
            Assignment12Part1.main(args);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" expected: " + expected + "\n");
    }
}
