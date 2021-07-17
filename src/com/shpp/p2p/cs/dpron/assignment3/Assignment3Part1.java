package com.shpp.p2p.cs.dpron.assignment3;

import com.shpp.cs.a.console.TextProgram;

/* TODO: Find out if enough aerobics training has been done.
 */
public class Assignment3Part1 extends TextProgram {

    /*Constants controlling the basic parameters for checks*/
    private static final int DAYS_IN_WEEK = 7;
    private static final int NECESSARILY_CARDIOVASCULAR_DAYS = 5;
    private static final int MINIMAL_TIME_FOR_CARDIOVASCULAR = 30;
    private static final int NECESSARILY_BLOOD_PRESSURE_DAYS = 3;
    private static final int MINIMAL_TIME_FOR_BLOOD_PRESSURE = 40;

    public void run() {
        int[] week = new int[DAYS_IN_WEEK];
        int cardiovascularDays = 0;
        int bloodPressureDays = 0;

        try {
            for (int i = 0; i < week.length; i++) {
                //count how many minutes were spent
                week[i] = readInt("How many minutes did you do on day " + (i + 1) + " ?");
                //whether the minimum time was spent to confirm the training
                if (week[i] >= MINIMAL_TIME_FOR_CARDIOVASCULAR)
                    cardiovascularDays++;
                //whether the minimum time was spent to confirm the training
                if (week[i] >= MINIMAL_TIME_FOR_BLOOD_PRESSURE)
                    bloodPressureDays++;
            }
        } catch (Exception e) {
            //if have an error, message about it
            println("Error: " + e);
        } finally {
            //print results (❁´◡`❁)
            printResults(cardiovascularDays, bloodPressureDays);
        }
    }

    /**
     * Displays whether enough training days have been spent, if not, how many more need to be spent.
     *
     * @param cardiovascularDays Indicates how much training data has been performed.
     * @param bloodPressureDays  Indicates how much training data has been performed.
     */
    private void printResults(int cardiovascularDays, int bloodPressureDays) {
        println("\nCardiovascular health:");
        if (cardiovascularDays >= NECESSARILY_CARDIOVASCULAR_DAYS)
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        else
            println("\tYou needed to train hard for at least " + (NECESSARILY_CARDIOVASCULAR_DAYS - cardiovascularDays)
                    + " more day(s) a week!:");

        println("\nBlood pressure:");
        if (bloodPressureDays >= NECESSARILY_BLOOD_PRESSURE_DAYS)
            println("\t Great job! You've done enough exercise to keep a low blood pressure.");
        else
            println("\tYou needed to train hard for at least " + (NECESSARILY_BLOOD_PRESSURE_DAYS - bloodPressureDays)
                    + " more day(s) a week!");
    }
}