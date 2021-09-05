package com.shpp.p2p.cs.dpron.assignment11;

import java.util.*;

import static com.shpp.p2p.cs.dpron.assignment11.Function.getFunctionMap;

/**
 * The program which inputs a mathematical expression and the parameters that it processes and
 * displays the value that came out as a result of solving the expression.
 */
public class Assignment11Part1 {
    /**
     * Functions map
     * <p>
     * String   function name<p>
     * Function  function of action
     */
    public static HashMap<String, Function> functionMap;

    /**
     * Initializes the parameter check and displays the result obtained during the calculation.
     *
     * @param args arguments passed by the user.
     *             the first argument should be the formula we are counting,
     *             all others can be variable.
     */
    public static void main(String[] args) {
        functionMap = getFunctionMap();

        if (args.length == 0) {
            System.err.println("You have not entered parameters");
        } else {
            String formula = args[0];

            if (formula.isEmpty()) {
                throw new RuntimeException("Formula is empty!");
            } else {
                System.out.println("Formula: " + formula);
                List<Lexeme> lexemes = parseExpression(normalizeFormula(formula));
                System.out.println("Result: " + calculate(lexemes, getVariables(args)));
            }
        }
    }

    /**
     * The function that enters the calculation formula and the variables
     * that are set by the user and returns the calculated value.
     *
     * @param lexemes   list of lexemes to be calculated
     * @param variables variables that are set by the user
     * @return the value obtained
     */
    private static double calculate(List<Lexeme> lexemes, HashMap<String, Double> variables) {
        LexemeBuffer lexemeBuffer = new LexemeBuffer(makeExpression(lexemes, variables));
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
                if (functionMap.containsKey(var)) {
                    throw new RuntimeException("Functions cannot be assigned values");
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
     * @param lexemes   lexemes
     * @param variables variables
     * @return expression
     */
    private static List<Lexeme> makeExpression(List<Lexeme> lexemes, HashMap<String, Double> variables) {
        List<Lexeme> tempLexemes = cloneLexemesList(lexemes);
        for (Lexeme lexeme : tempLexemes) {
            if (lexeme.type == LexemeType.VARIABLE) {
                lexeme.type = LexemeType.NUMBER;
                lexeme.value = variables.get(lexeme.value).toString();
            }
        }
        return tempLexemes;
    }

    /**
     * Clone all list of lexemes
     *
     * @param basicLexemes basic lexemes
     * @return cloned list
     */
    private static List<Lexeme> cloneLexemesList(List<Lexeme> basicLexemes) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();

        for (Lexeme lexeme : basicLexemes) {
            lexemes.add(lexeme.clone());
        }
        return lexemes;
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

                                if (c == '2' || c == '1') {
                                    sb.append(c);
                                    pos++;

                                    c = expression.charAt(pos);

                                    if (expression.charAt(pos - 1) == '1' && c == '0') {
                                        sb.append(c);
                                        pos++;

                                        c = expression.charAt(pos);
                                    }
                                }
                            } while (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z');

                            if (functionMap.containsKey(sb.toString())) {
                                lexemes.add(new Lexeme(LexemeType.NAME, sb.toString()));
                            } else {
                                lexemes.add(new Lexeme(LexemeType.VARIABLE, sb.toString()));
                            }
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
        double value = exponent(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL -> value *= exponent(lexemes);
                case OP_DIV -> value /= exponent(lexemes);
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
     * Make pow operation
     */
    public static double exponent(LexemeBuffer lexemes) {
        double value = factor(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_POW -> value = Math.pow(value, factor(lexemes));
                case EOF, RIGHT_BRACKET, OP_PLUS, OP_MINUS, OP_MUL, OP_DIV -> {
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
            case NAME:
                lexemes.back();
                return mathFunc(lexemes);
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
     * Make math function
     *
     * @return math function result
     */
    public static double mathFunc(LexemeBuffer lexemeBuffer) {
        String name = lexemeBuffer.next().value;
        Lexeme lexeme = lexemeBuffer.next();

        if (lexeme.type != LexemeType.LEFT_BRACKET) {
            throw new RuntimeException("Wrong function call syntax at " + lexeme.value);
        }

        ArrayList<Double> args = new ArrayList<>();

        lexeme = lexemeBuffer.next();
        if (lexeme.type != LexemeType.RIGHT_BRACKET) {
            lexemeBuffer.back();
            do {
                args.add(expr(lexemeBuffer));
                lexeme = lexemeBuffer.next();

                if (lexeme.type != LexemeType.COMMA && lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Wrong function call syntax at " + lexeme.value);
                }

            } while (lexeme.type == LexemeType.COMMA);
        }
        return functionMap.get(name).apply(args);
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