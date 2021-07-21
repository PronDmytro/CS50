package com.shpp.p2p.cs.dpron.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TODO: CSV parser
 */
public class Assignment5Part4 extends TextProgram {

    public void run() {
        println(extractColumn("assets/test2.csv", 1));
    }

    /**
     * ExtractColumn returns the whole parsed arraylist column
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
        ArrayList<String> result = new ArrayList<>();
        StringBuilder fieldBuilder = new StringBuilder();

        final char COMA = ',';
        final char QUOT = '\"';

        for (int i = 0; i < line.length(); i++) {
            fieldBuilder.append(QUOT);
            //Adding letters to element until find coma
            while (line.charAt(i) != COMA) {

                //If find quote - adding letters to element until find another coma
                if (line.charAt(i) == QUOT) {
                    do {
                        fieldBuilder.append(line.charAt(i));
                        i++;
                    } while (line.charAt(i) != QUOT);
                }
                //Add characters or last quote to element
                fieldBuilder.append(line.charAt(i));
                i++;

                //If element reaches end of line - break
                if (i == line.length())
                    break;
            }
            fieldBuilder.append(QUOT);
            String temp = fieldBuilder.toString();
            if (temp.contains("\"\"")) {
                temp = temp.replace("\"\"", "\"");
            }
            result.add(temp);
            fieldBuilder = new StringBuilder();
        }
        return result;
    }
}