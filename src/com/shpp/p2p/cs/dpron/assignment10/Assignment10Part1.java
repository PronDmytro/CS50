package com.shpp.p2p.cs.dpron.assignment10;

import java.util.*;

/**
 * The program which inputs a mathematical expression and the parameters that it processes and
 * displays the value that came out as a result of solving the expression.
 */
public class Assignment10Part1 {

    /**
     * Initializes the parameter check and displays the result obtained during the calculation.
     *
     * @param args arguments passed by the user.
     *             the first argument should be the formula we are counting,
     *             all others can be variable.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("You have not entered parameters");
        } else {
            String formula = args[0];

            if (formula.isEmpty()) {
                throw new RuntimeException("Formula is empty!");
            } else {
                System.out.println("Formula: " + formula);
                System.out.println("Result: " + calculate(normalizeFormula(formula), getVariables(args)));
            }
        }
    }

    /**
     * The function that enters the calculation formula and the variables
     * that are set by the user and returns the calculated value.
     *
     * @param formula   the formula to be calculated
     * @param variables variables that are set by the user
     * @return the value obtained
     */
    private static double calculate(String formula, HashMap<String, Double> variables) {
        String expr = makeExpression(formula, variables);
        List<Lexeme> lexemes = parseExpression(expr);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);

        return expr(lexemeBuffer);
    }

    /**
     * Get all possible variables
     *
     * @param args param from entries expression
     * @return HashMap with all declared variable
     */
    private static HashMap<String, Double> getVariables(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();

        try {
            for (int i = 1; i < args.length; i++) {
                String var = args[i].replaceAll("\\s", "").split("=")[0];
                if (isNumeric(var)) {
                    throw new RuntimeException("Variable cannot be numeric");
                }
                variables.put(var, Double.parseDouble(args[i].replaceAll("\\s", "").split("=")[1]));
            }
        } catch (Exception e) {
            System.err.println("Expresion #" + variables.size() + 1 + " have some problem: ");
        }

        if (variables.size() == 0) {
            System.out.println("No variables!");
        } else {
            System.out.println("Variables: " + variables);
        }

        return variables;
    }

    /**
     * Normalizes the formula by removing spaces
     *
     * @param formula non-normalized formula
     * @return normalized formula string
     */
    private static String normalizeFormula(String formula) {
        return formula.replaceAll("\\s", "");
    }

    /**
     * Replaces variables in the formula with their values
     *
     * @param formula   formula
     * @param variables variables
     * @return expression
     */
    private static String makeExpression(String formula, HashMap<String, Double> variables) {
        for (String variable : variables.keySet()) {
            formula = formula.replaceAll(variable, variables.get(variable).toString());
        }
        return formula;
    }

    /**
     * Expression parser
     *
     * @param expression Our expression
     * @return List of lexemes
     */
    public static List<Lexeme> parseExpression(String expression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char c = expression.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MUL, c));
                    pos++;
                    continue;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.OP_POW, c));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    continue;
                case ',':
                    lexemes.add(new Lexeme(LexemeType.COMMA, c));
                    pos++;
                    continue;
                default:
                    if (c <= '9' && c >= '0' || c == '.') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(pos);
                        } while (c <= '9' && c >= '0' || c == '.');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                            StringBuilder sb = new StringBuilder();
                            do {
                                sb.append(c);
                                pos++;

                                if (pos >= expression.length()) {
                                    break;
                                }

                                c = expression.charAt(pos);
                            } while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');
                        }
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    /**
     * Calculate expression
     *
     * @return result
     */
    public static double expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();

        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusMinus(lexemes);
        }
    }

    /**
     * Make plus and minus operations
     */
    public static double plusMinus(LexemeBuffer lexemes) {
        double value = multiDiv(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS -> value += multiDiv(lexemes);
                case OP_MINUS -> value -= multiDiv(lexemes);
                case EOF, RIGHT_BRACKET, COMMA -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
            }
        }
    }

    /**
     * Make multiplication and division operations
     */
    public static double multiDiv(LexemeBuffer lexemes) {
        double value = factor(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL -> value *= factor(lexemes);
                case OP_DIV -> value /= factor(lexemes);
                case OP_POW -> value = Math.pow(value, factor(lexemes));
                case EOF, RIGHT_BRACKET, COMMA, OP_PLUS, OP_MINUS -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
            }
        }
    }

    /**
     * Make action in brackets
     */
    public static double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();

        double value;
        switch (lexeme.type) {
            case OP_MINUS:
                value = factor(lexemes);
                return -value;
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case LEFT_BRACKET:
                value = plusMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.value
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.value
                        + " at position: " + lexemes.getPos());
        }
    }

    /**
     * Taken from https://www.baeldung.com/java-check-string-number#plain-java
     *
     * @return whether a string is a number
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}