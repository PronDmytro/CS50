package com.shpp.p2p.cs.dpron.assignment11;

import java.util.List;

/**
 * Lexeme buffer
 */
public class LexemeBuffer {
    private int position;

    public List<Lexeme> lexemes;

    /**
     * Constructor
     *
     * @param lexemes all lexemes
     */
    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    /**
     * Go further
     */
    public Lexeme next() {
        return lexemes.get(position++);
    }

    /**
     * Go back
     */
    public void back() {
        position--;
    }

    /**
     * Get position
     */
    public int getPosition() {
        return position;
    }
}
