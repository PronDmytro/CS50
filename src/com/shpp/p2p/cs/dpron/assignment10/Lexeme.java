package com.shpp.p2p.cs.dpron.assignment10;

public class Lexeme {
    public LexemeType type;
    public String value;

    /**
     * Constructor
     *
     * @param type  Lexeme type
     * @param value Lexeme value
     */
    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Constructor
     *
     * @param type  Lexeme type
     * @param value Lexeme value
     */
    public Lexeme(LexemeType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
