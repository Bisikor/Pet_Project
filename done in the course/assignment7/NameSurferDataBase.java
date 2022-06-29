package com.shpp.p2p.cs.abolgov.assignment7;

import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {

    //Global variable that stores data from a file
    private final HashMap<String, NameSurferEntry> documentData;
    /* Constructor: NameSurferDataBase(filename) */

    /**
     * Creates a new NameSurferDataBase and initializes it using the
     * data in the specified file.  The constructor throws an error
     * exception if the requested file does not exist or if an error
     * occurs as the file is being read.
     */

    public NameSurferDataBase(String filename) {
        documentData = new HashMap<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                line = line.toLowerCase();
                NameSurferEntry entry = new NameSurferEntry(line);
                documentData.put(entry.getName(), entry);
            }
            bf.close();
        } catch (IOException ex) {
            System.out.println("Invalid file name");
        }
    }

    /* Method: findEntry(name) */

    /**
     * Returns the NameSurferEntry associated with this name, if one
     * exists.  If the name does not appear in the database, this
     * method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return documentData.get(name);
    }

}

