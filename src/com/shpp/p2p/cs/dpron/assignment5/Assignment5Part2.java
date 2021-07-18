package com.shpp.p2p.cs.dpron.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    private static final int NUMERAL_SYSTEM = 3; //not more than 10

    /**
     * Given two string representations of non negative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private static String addNumericStrings(String n1, String n2) {
        /* This number represents the rest  when we need to add 1 to the next rank of the number
         * (if sum of 2 numbers give us number more or equal 10). Or 0 if it is not.
         */
        int rest = 0;
        StringBuilder result = new StringBuilder();

        //loop ends when we reach the end of digits in strings n1 or n2
        for (int i = n1.length() - 1, j = n2.length() - 1; j >= 0 && i >= 0; i--, j--) {

            //current digits that we would sum
            int currentDigit1 = n1.charAt(i) - '0';
            int currentDigit2 = n2.charAt(j) - '0';

            if (currentDigit1 >= NUMERAL_SYSTEM && currentDigit2 >= NUMERAL_SYSTEM) {
                return "Error \n What are you need?)\n(╯°□°）╯︵ ┻━┻";
            }
            //sum of 2 digits and rest(if the prev sum was more or equal 10, so we need to add 1 to the next rank)
            int sum = currentDigit1 + currentDigit2 + rest;

            if (sum >= NUMERAL_SYSTEM) {
                //need to add 1 to the next rank
                rest = 1;
                //leave only a single rank for result
                sum -= NUMERAL_SYSTEM;
            } else {
                rest = 0;
            }
            result.append(sum);
        }

        if (n1.length() >= n2.length()) {
            result.append(addRemainingDigitsOfNumber(n1, n2, rest));

        } else {
            result.append(addRemainingDigitsOfNumber(n2, n1, rest));
        }

        return result.reverse().toString();
    }

    /**
     * If the length of the strings is different, then add the remaining digits of the number to the resulting number.
     *
     * @param n1   First string that represents the number that bigger than n2.
     * @param n2   Second string that represents another number that less than n1.
     * @param rest indicates whether 1 needs to be added to the next rank.
     * @return String which consist the number to which the remaining elements of the larger number have been added
     */
    private static String addRemainingDigitsOfNumber(String n1, String n2, int rest) {
        final int INCREASE_RANK_NUMBER = 1;
        StringBuilder result = new StringBuilder();

        //length of the remaining digits of the larger number
        int commonLength = n1.length() - n2.length() - 1;
        for (int i = commonLength; i >= 0; i--) {
            int currentDigit = n1.charAt(i) - '0';

            if (rest == INCREASE_RANK_NUMBER && (currentDigit + rest) == NUMERAL_SYSTEM) {
                result.append(0);
                rest = INCREASE_RANK_NUMBER;
            } else if (rest == INCREASE_RANK_NUMBER) {
                result.append(currentDigit + rest);
                rest = 0;
            } else {
                result.append(currentDigit);
            }
        }

        //if previous sum in loop was bigger that 10 then need to add new rank to the number
        if (rest == INCREASE_RANK_NUMBER) {
            result.append(1);
        }

        return result.toString();
    }

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            try {
                String n1 = readLine("Enter first number:  ");
                String n2 = readLine("Enter second number: ");
                println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
                println();
            } catch (Exception e) {
                println("Error: " + e);
                break;
            }
        }
    }
}