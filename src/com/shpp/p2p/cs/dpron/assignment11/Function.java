package com.shpp.p2p.cs.dpron.assignment11;

import java.util.HashMap;
import java.util.List;

/**
 * Function interface
 */
public interface Function {
    double apply(List<Double> args);

    // functions map
    static HashMap<String, Function> getFunctionMap() {
        HashMap<String, Function> functionTable = new HashMap<>();
        functionTable.put("pow", args -> {
            if (args.size() != 2) {
                throw new RuntimeException("Wrong argument count for function pow: " + args.size());
            }
            return Math.pow(args.get(0), args.get(1));
        });
        functionTable.put("sqrt", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.sqrt(args.get(0));
        });
        functionTable.put("sin", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.sin(args.get(0));
        });
        functionTable.put("cos", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.cos(args.get(0));
        });
        functionTable.put("tan", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.tan(args.get(0));
        });
        functionTable.put("atan", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.atan(args.get(0));
        });
        functionTable.put("log10", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return Math.log10(args.get(0));
        });
        functionTable.put("log2", args -> {
            if (args.isEmpty()) {
                throw new RuntimeException("No arguments for function sqrt");
            }
            return (Math.log(args.get(0)) / Math.log(2));
        });
        return functionTable;
    }
}

