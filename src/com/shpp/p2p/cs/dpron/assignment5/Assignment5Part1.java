package com.shpp.p2p.cs.dpron.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * TODO: To count syllables in word
 */
public class Assignment5Part1 extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            try {
                String word = readLine("Enter a single word: ");
                println("  Syllable count: " + syllablesIn(word));
            } catch (Exception e) {
                println("Error: " + e);
                break;
            }
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int count = 0;
        boolean isPrevVowel = false;
        for (int j = 0; j < word.length(); j++) {
            // check if `word` have vowel letters
            if (word.contains("a") || word.contains("e") || word.contains("i")
                    || word.contains("o") || word.contains("u")) {
                // check if last letter in `word` != 'e'
                if (isVowel(word.charAt(j)) && !((word.charAt(j) == 'e') && (j == word.length() - 1))) {
                    if (!isPrevVowel) {
                        count++;
                        isPrevVowel = true;
                    }
                } else {
                    isPrevVowel = false;
                }
            } else {
                count++;
                break;
            }
        }
        if (count != 0)
            return count;
        return 1;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
    }
}