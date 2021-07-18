package com.shpp.p2p.cs.dpron.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    /**
     * This method runs the program
     */
    public void run() {
        println(extractColumn("assets/test1.csv", 1));
    }

    /**
     * extractColumn returns the whole parsed arraylist column
     *
     * @param filename    accepts the name of the file
     * @param columnIndex accepts the index of the column needs to be parsed and returned
     * @return printColumn
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> printColumn = new ArrayList<>();
        try {
            //accepts the file
            BufferedReader buffer = new BufferedReader(new FileReader(filename));
            while (true) {
                //reads the line
                String line = buffer.readLine();
                //stops when read the whole file
                if (line == null) {
                    break;
                }
                //adds the indexColumn to the array
                printColumn.add(fieldsIn(line).get(columnIndex));
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return printColumn;
    }

    /**
     * Method converts the line of code into the converted file
     *
     * @return arraylist of parsed line
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> fieldsIn = new ArrayList<>();

        // collect word by symbols(chars)
        StringBuilder fieldBuilder = new StringBuilder();
        fieldBuilder.append("\"");
        //determines whether the quote is closed or not
        boolean isQuoteClosed = true;
        for (int i = 0; i < line.length(); i++) {
            // each char of word
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                isQuoteClosed = !isQuoteClosed;
            } else if (currentChar == ',') {
                // Add word to the list
                if (isQuoteClosed) {
                    fieldBuilder.append("\"");
                    fieldsIn.add(fieldBuilder.toString());

                    //start new word
                    fieldBuilder = new StringBuilder();
                    fieldBuilder.append("\"");
                } else {
                    fieldBuilder.append(currentChar);
                }
            } else {
                fieldBuilder.append(currentChar);
            }
        }
        fieldBuilder.append("\"");

        fieldsIn.add(fieldBuilder.toString());
        return fieldsIn;
    }
}