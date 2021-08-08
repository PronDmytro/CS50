package com.shpp.p2p.cs.dpron.assignment7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
    /* Private instance variables */
    private final Map<String, NameSurferEntry> namesDB = new HashMap<>();

    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */
    public NameSurferDataBase(String filename) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(filename));
            while (true) {
                String line = rd.readLine();
                if (line == null) break;
                NameSurferEntry nameEntry = new NameSurferEntry(line);
                namesDB.put(nameEntry.getName(), nameEntry);
            }
            rd.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        // optimize string to data
        if (!name.isEmpty()) {
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        }
        return namesDB.getOrDefault(name, null);
    }
}

