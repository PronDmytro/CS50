package com.shpp.p2p.cs.dpron.assignment10;

import java.util.Arrays;

public class Assignment10Tests {
    public static void main(String[] args) {
        checkCase(new String[]{"1+2^2"}, "5");
        checkCase(new String[]{"3+2^2"}, "7");
        checkCase(new String[]{"10+20+30"}, "60.0");
        checkCase(new String[]{"x", "x=2.5"}, "2.5");
        checkCase(new String[]{"x+3", "x=2"}, "5.0");
        checkCase(new String[]{"x+5+x+3+x+1+x", "x=3"}, "21.0");
        checkCase(new String[]{"x", "x=2"}, "2.0");
        checkCase(new String[]{"x+5", "x=2"}, "7.0");
        checkCase(new String[]{"-10", "x=2"}, "-10.0");
        checkCase(new String[]{"-  10", "x=2"}, "-10.0");
        checkCase(new String[]{"-x", "x=2"}, "-2.0");
        checkCase(new String[]{"-  x", "x=2"}, "-2.0");
        checkCase(new String[]{"-10+ -x", "x=2"}, "-12.0");
        checkCase(new String[]{"-20-x", "x=2"}, "-22.0");
        checkCase(new String[]{"x-10", "x=2"}, "-8.0");
        checkCase(new String[]{"-x-10", "x=2"}, "-12.0");
        checkCase(new String[]{"10+10-20+20", "x=2"}, "20.0");
        checkCase(new String[]{"10-10+20-20", "x=2"}, "0.0");
        checkCase(new String[]{"x*x", "x=2"}, "4.0");
        checkCase(new String[]{"x+3*3", "x=2"}, "11.0");
        checkCase(new String[]{"1+x-3*4+5-6", "x=2"}, "-10.0");
        checkCase(new String[]{"x*2*-x*2*x*2  *  2*2 ", "x=2"}, "-256.0");
        checkCase(new String[]{"-x*-x", "x=2"}, "4.0");
        checkCase(new String[]{"4/x", "x=2"}, "2.0");
        checkCase(new String[]{"256/2/x/2", "x=2"}, "32.0");
        checkCase(new String[]{"5*6/x*3", "x=2"}, "45.0");
        checkCase(new String[]{"6/3*5/4", "x=2"}, "2.5");
        checkCase(new String[]{"10/-x", "x=2"}, "-5.0");
        checkCase(new String[]{"1+x-3*4/-2", "x=2"}, "9.0");
        checkCase(new String[]{"2^x", "x=2"}, "4.0");
        checkCase(new String[]{"-x^2", "x=2"}, "4.0");
        checkCase(new String[]{"-x^3", "x=2"}, "-8.0");
        checkCase(new String[]{"2^-x", "x=2"}, "0.25");
        checkCase(new String[]{"x^2^2", "x=2"}, "16.0");
        checkCase(new String[]{"2^2^2^2", "x=2"}, "256 | 65536.0");
        checkCase(new String[]{"x-y-z", "x=10", "y=-20", "z=-30"}, "60.0");
        checkCase(new String[]{"10"}, "10.0");
        checkCase(new String[]{"x", "x=10", "y=20"}, "10.0");
        checkCase(new String[]{"0/0"}, "exception. ");
        checkCase(new String[]{"x", "y=10"}, "exception. ");
        checkCase(new String[]{"", "x=2"}, "exception. ");
        checkCase(new String[]{"5+3+4+6+7", ""}, "25.0");
        checkCase(new String[]{"5+3-4", ""}, "4.0");
        checkCase(new String[]{"5*3+4", ""}, "19.0");
        checkCase(new String[]{"5+3*4", ""}, "17.0");
        checkCase(new String[]{"1+3^2+2^3", ""}, "18.0");
        checkCase(new String[]{"5-10+3^2", ""}, "4.0");
        checkCase(new String[]{"2*2*2^2-4", ""}, "12.0");
        checkCase(new String[]{"10*-3", ""}, "-30.0");
        checkCase(new String[]{"-3*10", ""}, "-30.0");
        checkCase(new String[]{"-5^-5", ""}, "-0.00032 | -3.2E-4");
        checkCase(new String[]{"5^-5", ""}, "0.00032 | 3.2E-4");
        checkCase(new String[]{"5^5", ""}, "3125.0");
        checkCase(new String[]{"-5^5", ""}, "-3125.0");
        checkCase(new String[]{"10*-3^3", ""}, "-270.0");
        checkCase(new String[]{"1+3^2", ""}, "10.0");
        checkCase(new String[]{"3/(4^-2)", ""}, "48.0");
        checkCase(new String[]{"3/(-4^-2)", ""}, "48.0");
        checkCase(new String[]{"-3/(-4^-2)", ""}, "-48.0");
        checkCase(new String[]{"1.0+ 2", ""}, "3.0");
        checkCase(new String[]{"3+2.0", ""}, "5.0");
        checkCase(new String[]{"1+3*2", ""}, "7.0");
        checkCase(new String[]{"-10+3", ""}, "-7.0");
        checkCase(new String[]{"0-5", ""}, "-5.0");
        checkCase(new String[]{"3*4", ""}, "12.0");
        checkCase(new String[]{"3*4*10", ""}, "120.0");
        checkCase(new String[]{"3*4*10/10", ""}, "12.0");
        checkCase(new String[]{"3/4", ""}, "0.75000");
        checkCase(new String[]{"5-3", ""}, "2.0");
        checkCase(new String[]{"2-3", ""}, "-1.0");
        checkCase(new String[]{"-2-3", ""}, "-5.0");
        checkCase(new String[]{"11-10/-2", ""}, "16.0");
        checkCase(new String[]{"2.33*2", ""}, "4.66000");
        checkCase(new String[]{"11-3^2+3^3", ""}, "29.0");
        checkCase(new String[]{"5+a", "a=5",}, "10.0"); // with args below
        checkCase(new String[]{"a+5", "a=5"}, "10.0");
        checkCase(new String[]{"11-10/-a", "a=5"}, "13.0");
        checkCase(new String[]{"11-10/-a", "a=-5"}, "9.0");
        checkCase(new String[]{"11+10/-a", "a=-5"}, "13.0");
        checkCase(new String[]{"5^a", "a = 4"}, "625.0");
        checkCase(new String[]{"5^-a", "a=4"}, "0.00160");
        checkCase(new String[]{"-3*a^2", "a=2"}, "-12.0");
        checkCase(new String[]{"-3*a^2", "a=-2"}, "-12.0");
        checkCase(new String[]{"2,0*a", "a=-2"}, "-4.0");
        checkCase(new String[]{"-a*2.0", "a=-2"}, "4.0");
        checkCase(new String[]{"-a*2*4", "a=-2"}, "16.0");
        checkCase(new String[]{"2-a", "a=-2"}, "4.0");
        checkCase(new String[]{"3*a", "a=-2"}, "-6.0");
        checkCase(new String[]{"3+a", "a=-3"}, "0.0");
        checkCase(new String[]{"3-a", "a=-3"}, "6.0");
        checkCase(new String[]{"2-a*5", "a=-2"}, "12.0");
        checkCase(new String[]{"a+a", "a=-2"}, "-4.0");
        checkCase(new String[]{"a*a", "a=2"}, "4.0");
        checkCase(new String[]{"a*a", " a=4"}, "16.0");
        checkCase(new String[]{"a*a", "a=-4"}, "16.0");
        checkCase(new String[]{"-a*a", "a=-4"}, "-16.0");
        checkCase(new String[]{"-a*a", "a=4"}, "-16.0");
        checkCase(new String[]{"a+5", "a=-4"}, "1.0");
        checkCase(new String[]{"5-a", "a=-4"}, "9.0");
        checkCase(new String[]{"-a+5", "a=-4"}, "9.0");
        checkCase(new String[]{"-a*a", "a=-4"}, "-16.0");
        checkCase(new String[]{"2-a*a", "a=2"}, "-2.0");
        checkCase(new String[]{"5^-a", "a=-4"}, "625.0");
        checkCase(new String[]{"5-a*a", "a=-2"}, "1.0");
        checkCase(new String[]{"a+55*a", "a=10"}, "560.0");
        checkCase(new String[]{"1+a*2", "a=2"}, "5.0");
        checkCase(new String[]{"1+a*2/2", "a=2"}, "3.0");
        checkCase(new String[]{"1*1-a^2", "a=2"}, "-3.0");
        checkCase(new String[]{"1+a*2/2-1", "a=2"}, "2.0");
        checkCase(new String[]{"11*a^3", "a=-2"}, "-88.0");
        checkCase(new String[]{"1-2*a+3", "a=-5"}, "14.0");
        checkCase(new String[]{"-a+5.0", "a=-2.000"}, "7.0");
        checkCase(new String[]{"5-10+a^2-b^c", "a=5", "b=5", "c=5"}, "-3105.0");
        checkCase(new String[]{"d-10+a^2-b^c", "c=-5", "a= 5", "b= -5", "d=33"}, "48.00032");
        checkCase(new String[]{"d-10+a^2-b^c+a", "c=-5", "a= 5", "b= -5", "d=33"}, "53.00032");
        checkCase(new String[]{"(3+3)"}, "6.0");// with parentheses below
        checkCase(new String[]{"3+(3+3)"}, "9.0");
        checkCase(new String[]{"(1+3)*2"}, "8.0");
        checkCase(new String[]{"(1+3)^2"}, "16.0");
        checkCase(new String[]{"24+(1+3)^2"}, "40.0");
        checkCase(new String[]{"2+(3+5)+(1+3)^2"}, "26.0");
        checkCase(new String[]{"2+(3+5)+(1+3^2)^2"}, "110.0");
        checkCase(new String[]{"3+3+(-3)"}, "3.0");
        checkCase(new String[]{"3+3-(-3)"}, "9.0");
        checkCase(new String[]{"3+3-(5-10)"}, "11.0");
        checkCase(new String[]{"3+3-(8-(2+2))"}, "2.0");
        checkCase(new String[]{"3-(8-(2+2))+33"}, "32.0");
        checkCase(new String[]{"3-(8-(2+2)^2)+33"}, "44.0");
        checkCase(new String[]{"3-(8-(2+2+(3^2))^2)+33"}, "197.0");
        checkCase(new String[]{"3-(8-(2+2+(3^2))^2)+(33+53)"}, "250.0");
        checkCase(new String[]{"5/0"}, "Infinity"); // special cases, check for crashes and infinite loops are below
        checkCase(new String[]{"22"}, "22.0");
        checkCase(new String[]{"-(33+53)"}, "-86.0");
        checkCase(new String[]{"-(33-53)"}, "20.0");
        checkCase(new String[]{"2(5+5)"}, "20");
        checkCase(new String[]{"(5+5)2"}, "20");
        checkCase(new String[]{"2(5+5)2"}, "40");
        checkCase(new String[]{"(3+4)(2+2)"}, "28");
        checkCase(new String[]{"-5-10+a^2-b^codi+1.44", "codi =5", "a=5", "b=5"}, "-3113.56"); // not mendatory with args
        checkCase(new String[]{"dodo-10*3", "dodo=33"}, "3.0");
        checkCase(new String[]{"a2", "a=5"}, "10");
        checkCase(new String[]{"2a", "a=5"}, "10");
        checkCase(new String[]{"3a", "a=-3"}, "-9");
        checkCase(new String[]{"3 a", "a=-3",}, "-9");
        checkCase(new String[]{"32+a2", "a=4"}, "40");
        checkCase(new String[]{"33+2a", "a=5"}, "43");
        checkCase(new String[]{"a2+b/2-c", "a=5", "b=6", "c=3"}, "10");
        checkCase(new String[]{"4^2^2"}, "256");
        checkCase(new String[]{"3-(8-a^2)+33", "a=(2+2)"}, "44");// parentheses + args
        checkCase(new String[]{"3-a+33", "a=(8-(2+2)^2)"}, "44");
        checkCase(new String[]{"3-a^2", "a=(8-(2+2)^2)"}, "-61");
        checkCase(new String[]{"3+3)"}, "");
        checkCase(new String[]{"1-2*a+3", "a=a=-5"}, "");
    }

    private static void checkCase(String[] args, String expected) {
        System.out.println("expression and variables: " + Arrays.toString(args));
        System.out.print("   actual: ");
        try {
            Assignment10Part1.main(args);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        System.out.println(" expected: " + expected + "\n");
    }
}
