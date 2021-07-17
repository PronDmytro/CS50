package com.shpp.p2p.cs.dpron.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/* TODO: St. Petersburg game
 */
public class Assignment3Part5ext extends TextProgram {

    public String log = "";

    public void run() {
        for (int i = 0; i < 1000000; i++) {
            startGame();
        }
        writeResult(log);
    }

    private void startGame() {
        int prizeSum = 0;
        int gamesCount = 0;
        do {
            gamesCount++;
            prizeSum += roll();
        } while (prizeSum < 20);
        log = log + prizeSum + "\n";
        println("It took " + gamesCount + " games to earn $" + prizeSum);
    }

    //new roll
    private int roll() {
        int sum = 1;
        while (isHeads()) {
            sum *= 2;
        }
        return sum;
    }

    //heads or tails
    private boolean isHeads() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    private void writeResult(String prize) {
        String filePath = "log.txt";
        String text = prize + "\n";

        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        } catch (IOException e) {
            println(e);
        }
    }
}
