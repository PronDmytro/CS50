package com.shpp.p2p.cs.dpron.assignment3;

import com.shpp.cs.a.console.TextProgram;

/* TODO: The Collatz Conjecture or 3x+1 problem can be summarized as follows:
 */
public class Assignment3Part2 extends TextProgram {
    private static final Integer FINAL_NUMBER = 1;

    public void run() {
        int number = 0;
        try {
            number = readInt("Enter a number: ");
        } catch (Exception e) {
            println("Error: " + e);
        } finally {
            // Take any positive integer number
            if (number <= 0) {
                println("Only natural numbers are allowed");
                return;
            }
            collatzStep(number, 0);
        }
    }

    /**
     * Call itself until the condition is true.
     *
     * @param number      The number given.
     * @param currentStep Current step being performed
     */
    private int collatzStep(int number, int currentStep) {
        //The conjecture states that no matter which number you start with, you will always reach 1 eventually
        if (number == FINAL_NUMBER) {
            return currentStep;
        }
        //Repeat the process indefinitely
        return collatzStep(applyCollatzFunction(number), currentStep + 1);
    }

    /**
     * Counts the next number
     *
     * @param number Number that is calculated.
     */
    private int applyCollatzFunction(int number) {
        //If n is even, divide n by 2 to get n / 2
        if ((number % 2) == 0) {
            println(number + " is even so I take half: " + number / 2);
            return number / 2;
        }
        //If n is odd, multiply n by 3 and add 1 to get 3n + 1.
        println(number + " is odd so I make 3n + 1: " + ((number * 3) + 1));
        return (number * 3) + 1;
    }
}
