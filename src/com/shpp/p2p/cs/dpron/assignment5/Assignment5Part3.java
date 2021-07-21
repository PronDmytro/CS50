package com.shpp.p2p.cs.dpron.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO: Game in race
 */
public class Assignment5Part3 extends TextProgram {

    public void run() {
        ArrayList<String> dictionary = readDictionary();

        while (true) {
            try {
                String codeWord = readLine("Enter a single word: ").toLowerCase();
                if (codeWord.length() == 3)
                    findWords(codeWord, dictionary);
                else
                    println("Input word does not fit in length");
            } catch (Exception e) {
                println("Error: " + e);
                break;
            }
        }
    }

    /**
     * Find the words which match input 3 characters
     */
    private void findWords(String letters, ArrayList<String> dictionary) {
        int wordsCount = 0;
        for (String word : dictionary) {
            if (containChars(word, letters)) {
                println(word);
                wordsCount++;
            }
        }
        println("\tTotally find " + wordsCount + " match words");
    }

    /**
     * Find characters in word
     *
     * @param word    temp word
     * @param letters 3 characters from user
     * @return "true" if word contains letters in specified order
     */
    private boolean containChars(String word, String letters) {
        int index1 = word.indexOf(letters.charAt(0));
        int index2 = word.indexOf(letters.charAt(1));
        int index3 = word.indexOf(letters.charAt(2));
        //Check only first index, because another indexes must be bigger than first
        if (index1 == -1) {
            return false;
        }

        //Check letters order
        return index1 < index2 && index2 < index3;
    }

    /**
     * Reads whole dictionary once
     *
     * @return the array list of words
     */
    private ArrayList<String> readDictionary() {
        ArrayList<String> dictionary = new ArrayList<>();
        try {
            //New BufferedReader
            BufferedReader buffer = new BufferedReader(new FileReader("assets/en-dictionary.txt"));
            //Add all lines from file to ArrayList.
            while (true) {
                //read line
                String line = buffer.readLine();
                //break if end
                if (line == null)
                    break;
                //add into array
                dictionary.add(line);
            }
            //Close it
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}