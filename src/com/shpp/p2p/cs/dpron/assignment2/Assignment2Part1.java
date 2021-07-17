package com.shpp.p2p.cs.dpron.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    public void run() {
        //Show an example of what we will calculate
        println("a * (x^2) + b * x + c = 0");
        double a, b, c;

        //Check that there are no errors when entering values
        try {
            a = readDouble("Please enter a:  ");
            if (a == 0) {
                println("a can not equal 0");
                return;
            }
            b = readDouble("Please enter b:  ");
            c = readDouble("Please enter c:  ");

        } catch (Exception e) {
            //Show exception
            println("Error: " + e);
            return;
        }

        //Show what will be calculated
        String example = (a == 1 ? "" : (a < 0 ? "- " + Math.abs(a) : a + " * ")) + "(x^2) " + (b < 0 ? "- " : "+ ") + Math.abs(b) + " (x^2) " + (c < 0 ? "- " : "+ ") + Math.abs(c);
        println("You enter: " + example);
        //Calculate discriminant
        double D = Math.pow(b, 2) - 4 * a * c;

        //If discriminant > 0 calculate two roots
        if (D > 0) {
            double x1 = (-b + Math.sqrt(D)) / 2 * a;
            double x2 = (-b - Math.sqrt(D)) / 2 * a;
            println("There are two roots: " + x1 + " and " + x2);
        } else if ((int) D == 0) {                                  //If discriminant = 0 calculate one root
            double x = -(b / (2 * a));
            println("There is one root: " + x);
        } else {                                                    //If discriminant < 0 - no roots
            println("There are no real roots");
        }
    }
}
