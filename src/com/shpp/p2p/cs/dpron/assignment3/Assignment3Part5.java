package com.shpp.p2p.cs.dpron.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.concurrent.ThreadLocalRandom;

/* TODO: St. Petersburg game
 */
public class Assignment3Part5 extends TextProgram {

    public void run() {
        startGame();
    }

    //start game
    private void startGame() {
        int prizeSum = 0;
        int gamesCount = 0;
        do {
            gamesCount++;
            prizeSum += roll();
            println("Your total is $" + prizeSum);
        } while (prizeSum < 20);
        println("It took " + gamesCount + " games to earn $" + prizeSum);
    }

    //new roll
    private int roll() {
        int sum = 1;
        while (isHeads()) {
            sum *= 2;
        }
        println("This game, you earned " + sum);
        return sum;
    }

    //heads or tails
    private boolean isHeads() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
