package com.shpp.p2p.cs.dpron.assignment3;

import com.shpp.cs.a.console.TextProgram;


/* TODO: Сalculate degree of number
 */
public class Assignment3Part3 extends TextProgram {

    public void run() {
        println(raiseToPower(2.0, 3));
        println(raiseToPower(0.5, -2));
        println(raiseToPower(0, 0));
    }

    /**
     * Raise entered number to entered power.
     *
     * @param base     Number to be incremented.
     * @param exponent Degree to which you want to increase the number.
     */
    private double raiseToPower(double base, int exponent) {
        //exponent == zero: x**0 = 1
        if (exponent == 0)
            return 1.0;
        //if exponent < 0 invert him
        if (exponent < 0) return raiseToPower(1.0 / base, -exponent);
        //next step
        return base * raiseToPower(base, exponent - 1);
    }
}
