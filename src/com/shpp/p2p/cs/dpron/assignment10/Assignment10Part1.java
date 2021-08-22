package com.shpp.p2p.cs.dpron.assignment10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Assignment10Part1 {
    /**/
    private static final String OP_ADD = "+";
    private static final String OP_SUB = "-";
    private static final String OP_MUL = "*";
    private static final String OP_DIV = "/";
    private static final String OP_EXP = "^";

    /**/
    private static final String OPERATORS = OP_EXP + OP_MUL + OP_DIV + OP_SUB + OP_ADD;
//    private static final String OPERATORS = "*/^+-";


    public static void main(String[] args) {

        /* print expression*/
        System.out.println("Expression: " + args[0]);
        /* perform expression parsing */
        Expressions expressionObj = new Expressions(args[0]);
        if (expressionObj.isExpressionValid()) {
            /* expression parsed successfully*/
            ArrayList<String> expression = expressionObj.getExpression();
            System.out.println(expression);
            /* perform variables  parsing */
            Variables variables = new Variables(args);
            System.out.println(variables.getVariables());
            System.out.println("Result: " + calculate(expression, variables));
        } else {
            System.out.println("Can't make calculation");
        }
    }

    /**
     * Method to perform calculation of given expression
     * with  input arguments
     *
     * @param splittedExpression expression as ArrayList<String>
     * @param vars               object of parsed variables
     * @return String result of calculation
     */
    private static String calculate(ArrayList<String> splittedExpression, Variables vars) {
        if (splittedExpression.size() == 1) {
            return parseValue(splittedExpression.get(0), vars) + "";
        } else
            while (splittedExpression.size() > 1) {
                for (int i = 0; i < OPERATORS.length(); i++) {
                    String op = OPERATORS.charAt(i) + "";
                    int index = getOperatorIndex(op, splittedExpression);
                    while (index >= 0) {
                        Double a = parseValue(splittedExpression.get(index - 1), vars);
                        Double b = parseValue(splittedExpression.get(index + 1), vars);
                        if (a != null && b != null) {
                            switch (op) {
                                case "^" -> splittedExpression.set(index - 1, (Math.pow(a, b)) + "");
                                case "*" -> splittedExpression.set(index - 1, (a * b) + "");
                                case "/" -> {
                                    if (b == 0) return "Division by zero";
                                    splittedExpression.set(index - 1, (a / b) + "");
                                }
                                case "-" -> splittedExpression.set(index - 1, (a - b) + "");
                                case "+" -> splittedExpression.set(index - 1, (a + b) + "");
                            }
                            splittedExpression.remove(index);
                            splittedExpression.remove(index);

                        } else return "Can't make calculation";
                        index = getOperatorIndex(op, splittedExpression);
                    }
                }
            }
        return splittedExpression.get(0);
    }

    /**
     * Method to find index of position of inputted operator
     *
     * @param operator String of one char(represent operator)
     * @param list     ArrayList<String> to search in
     * @return int searched operator index
     */
    static int getOperatorIndex(String operator, ArrayList<String> list) {

        for (int index = 0; index < list.size(); index++) {
            if (list.get(index).equals(operator)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * Method parses current string to Double
     * if it is not possible tries fo find current string as argument in map of variables
     *
     * @param value String content to parse
     * @param vars  object containing map of variables
     * @return Double result of parsing (null, if not parsed and argument not declared)
     */
    static Double parseValue(String value, Variables vars) {
        Double result = null;
        try {
            result = Double.parseDouble(value);
        } catch (Exception e) {
            if (vars.getVariables() != null && vars.getVariables().containsKey(value)) {
                result = vars.getVariables().get(value);
            } else {
                System.out.println("Variable " + value + " not found");
            }
        }
        return result;
    }
}


 class Expressions {
    /* array list of parsed expression*/
    private ArrayList<String> splittedExpression;
    /* true if expression parsed without errors*/
    private static boolean valid = true;

    /* Constructor */
    public Expressions(String expression) {
        splittedExpression = parseExpression(expression);
        valid = splittedExpression != null;
    }

    /**
     * Get parsed expression
     * @return ArrayList<String> of parsed expression
     */
    public ArrayList<String> getExpression() {
        return splittedExpression;
    }

    /**
     * Method to parse input string to array list of operators an operands
     * @param expression String to be parsed
     * @return ArrayList<String> of parsed expression
     */
    private static ArrayList<String> parseExpression(String expression) {

        /*delete spaces*/
        expression = expression.replaceAll(" ", "");

        /* split in lexemes like operator-operand (12+, aa*, a^)*/
        if (expression.charAt(0) == '-') { // if first argument is negative
            expression = "0" + expression;
        }
        Pattern pattern = Pattern.compile("[a-z0-9.\\s]+[/+*^-]");
        Matcher matcher = pattern.matcher(expression);
        int last = 0;
        ArrayList<String> lexems = new ArrayList<>();
        while (matcher.find()) {
            last = matcher.end();
            lexems.add(expression.substring(matcher.start(), last));
        }
        /* add last operand*/
        String a = expression.substring(last);
        lexems.add(a.replaceAll("[^a-z0-9.\\s]", ""));


        /* check expression correctness*/
        StringBuilder tmp = new StringBuilder();
        for (String s : lexems) {
            tmp.append(s);
            if (!expression.contains(tmp)) {
                System.out.println("Error in expression before " + s);
                return null;
            }
        }

        /* generate splited expression by operators and operands*/
        ArrayList<String> temporary = new ArrayList<>();
        for (int i = 0; i < lexems.size() - 1; i++) {
            String tmpString = lexems.get(i).trim();
            temporary.add(tmpString.substring(0, tmpString.length() - 1).trim());
            temporary.add(tmpString.substring(tmpString.length() - 1).trim());
        }
        temporary.add(lexems.get(lexems.size() - 1).trim());
        return temporary;

    }

    /**
     * Get parsed expression status
     * @return boolean parsed expression status (true if expression parse without errors)
     */
    public boolean isExpressionValid() {
        return valid;
    }
}

class Variables {
    /*  Map of variables values*/
    private static HashMap<String, Double> variables;

    /* Constructor */
    public Variables(String[] args) {
        if(args.length > 1) {
            variables = parseVariables(args);
        }
        else {
            variables = null;
            System.out.println("Nothing to parse for variables");
        }
    }

    /*******************************************************************************************************************
     *
     * @return parsed map of variables values
     */
    public HashMap<String, Double> getVariables() {
        return variables;
    }

    /*******************************************************************************************************************
     * Method to parse variables
     * @param args array of strings with expected varibles values
     * @return map of parsed variables
     */
    private static HashMap<String, Double> parseVariables(String[] args) {
        HashMap<String, Double> vars = new HashMap<>();
        /* begin parse from index 1 (0 - expression)*/
        for (int i = 1; i < args.length; i++) {
            try {
                String[] words = args[i].split("=");
                vars.put(words[0].trim(), Double.parseDouble(words[1]));
            } catch (Exception e) {
                /* can't split or parse double*/
                System.out.println("Can't parse argument " + args[i]);
            }
        }
        return vars;
    }
}