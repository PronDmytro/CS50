package com.shpp.p2p.cs.dpron.assignment11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {
    /**
     * Functions map
     * <p>
     * String   function name<p>
     * Function  function of action
     */
    public static HashMap<String, Function> functionMap = FunctionMap.getFunctionMap();

    private final Pattern alphabeticalPattern = Pattern.compile("[a-zA-Z]");
    private final Pattern doubleNumPattern = Pattern.compile("(\\d|\\.)");
    private int position = 0;
    private char symbol;

    /**
     * Expression parser
     *
     * @param expression input expression
     * @return List of lexemes
     */
    public List<Lexeme> parseExpression(String expression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        while (position < expression.length()) {
            this.symbol = expression.charAt(position);
            switch (symbol) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, symbol));
                    position++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, symbol));
                    position++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, symbol));
                    position++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, symbol));
                    position++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, symbol));
                    position++;
                    continue;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.OP_POW, symbol));
                    position++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, symbol));
                    position++;
                    continue;
                case ',':
                    lexemes.add(new Lexeme(LexemeType.COMMA, symbol));
                    position++;
                    continue;
                default:
                    if (doubleNumPattern.matcher(new StringBuilder(1).append(symbol)).find()) {
                        lexemes.add(parseNumeric(expression));
                    } else if (alphabeticalPattern.matcher(new StringBuilder(1).append(symbol)).find()) {
                        lexemes.add(parseLetters(expression));
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    /**
     * Parse numbers
     * @param expression input expression
     * @return parsed num
     */
    private Lexeme parseNumeric(String expression) {

        StringBuilder sb = new StringBuilder();
        do {
            sb.append(symbol);
            position++;
            if (position >= expression.length()) {
                break;
            }
            symbol = expression.charAt(position);
        } while (doubleNumPattern.matcher(new StringBuilder(1).append(symbol)).find());

        return new Lexeme(LexemeType.NUMBER, sb.toString());
    }

    /**
     * Parse words
     * @param expression input expression
     * @return parsed word
     */
    private Lexeme parseLetters(String expression) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(symbol);
            position++;

            if (position >= expression.length()) {
                break;
            }

            symbol = expression.charAt(position);

            if (symbol == '2' || symbol == '1') {
                sb.append(symbol);
                position++;

                symbol = expression.charAt(position);

                if (expression.charAt(position - 1) == '1' && symbol == '0') {
                    sb.append(symbol);
                    position++;

                    symbol = expression.charAt(position);
                }
            }
        } while (alphabeticalPattern.matcher(new StringBuilder(1).append(symbol)).find());

        if (functionMap.containsKey(sb.toString())) {
            return new Lexeme(LexemeType.NAME, sb.toString());
        } else {
            return new Lexeme(LexemeType.VARIABLE, sb.toString());
        }
    }
}
