package com.shpp.p2p.cs.dpron.assignment11;

import java.util.HashMap;
import java.util.List;

/**
 * Functions map
 * <p>
 * String   function name<p>
 * Function  function of action
 */
public class FunctionMap {
    /**
     * Functions map
     * @return function map
     */
    static HashMap<String, Function> getFunctionMap() {
        HashMap<String, Function> functionTable = new HashMap<>();
        functionTable.put("pow", args -> {
            if (args.size() != 2) {
                throw new RuntimeException("Wrong argument count for function pow: " + args.size());
            }
            return Math.pow(args.get(0), args.get(1));
        });
        functionTable.put("sqrt", args -> {
            checkArgs(args);
            return Math.sqrt(args.get(0));
        });
        functionTable.put("sin", args -> {
            checkArgs(args);
            return Math.sin(args.get(0));
        });
        functionTable.put("cos", args -> {
            checkArgs(args);
            return Math.cos(args.get(0));
        });
        functionTable.put("tan", args -> {
            checkArgs(args);
            return Math.tan(args.get(0));
        });
        functionTable.put("atan", args -> {
            checkArgs(args);
            return Math.atan(args.get(0));
        });
        functionTable.put("log10", args -> {
            checkArgs(args);
            return Math.log10(args.get(0));
        });
        functionTable.put("log2", args -> {
            checkArgs(args);
            return (Math.log(args.get(0)) / Math.log(2));
        });
        functionTable.put("exp", args -> {
            checkArgs(args);
            return Math.exp(args.get(0));
        });

        return functionTable;
    }

    /**
     * Check input args
     * @param args input args
     */
    private static void checkArgs(List<Double> args) {
        if (args.isEmpty()) {
            throw new RuntimeException("No arguments for function sqrt");
        } else if (args.size() > 1) {
            throw new RuntimeException("So many arguments");
        }
    }
}
