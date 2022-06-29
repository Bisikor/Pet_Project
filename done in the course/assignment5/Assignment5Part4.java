package com.shpp.p2p.cs.abolgov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part4 extends TextProgram {
    private static final String DICTIONARY_FILE = "test.txt";//File name
    private int howLines = 0;//To count the number of rows in the entire list

    public void run() {

        int columIndex = readInt("Please enter colum index");

        try {
            ArrayList<String> oneColum = extractColumn(DICTIONARY_FILE, columIndex);
            //If null is returned, then the file name was entered incorrectly
            if (oneColum != null) {
                println("___Colum " + columIndex + "___");
                //Displaying the column of interest on the screen
                for (int i = 0; i < oneColum.size(); i++) {
                    println("     " + oneColum.get(i));
                }
            } else println("Not correct file name ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param filename    File name
     * @param columnIndex Index of interest column
     * @return Returning the interest column
     * @throws IOException
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) throws IOException {
        try {
            BufferedReader newFile = new BufferedReader(new FileReader(filename));
            String str;
            ArrayList<String> wordList = new ArrayList<>();
            while ((str = newFile.readLine()) != null) {
                if (!str.isEmpty()) {
                    howLines++;
                    wordList.add(str);
                }
            }
            newFile.close();
            return oneColum(String.valueOf(wordList), columnIndex);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Parses the entire file
     *
     * @param line  line...
     * @return wordsList
     */
    private ArrayList<String> pars(String line) {
        String cvs = "\",";//Symbols for parsing
        StringBuilder currField = new StringBuilder();
        ArrayList<String> wordsList = new ArrayList<>();

        //Comma search
        for (int k = 1; k < line.length() - 1; k++) {
            if (line.charAt(k) == cvs.charAt(1)) {
                wordsList.add(currField.toString());
                currField = new StringBuilder();
                continue;
            }

            //Finding the first quote
            if (line.charAt(k) == cvs.charAt(0)) {
                currField = new StringBuilder(line.substring(k + 1, quotesFinder(line, k)));
                wordsList.add(currField.toString());
                currField = new StringBuilder();
                k = quotesFinder(line, k) + 1;
                continue;
            }
            currField.append(line.charAt(k));
        }
        //Checking if end line
        if (!currField.toString().equals("")){
            wordsList.add(currField.toString());
        }

        return wordsList;
    }

    /**
     * @param line  line...
     * @param index Started index
     * @return Quote Index
     */
    private int quotesFinder(String line, int index) {
        for (int i = index + 1; i < line.length(); i++) {
            if (line.charAt(i) == '"')
                return i;
        }
        return 0;
    }

    /**
     * From a list of ready-made words, it forms a list of words necessary for output
     *
     * @param line        line...
     * @param columnIndex The index of the columns to display
     * @return
     */
    private ArrayList oneColum(String line, int columnIndex) {
        ArrayList<String> list = pars(line);
        ArrayList<String> oneColum = new ArrayList<String>();
        int wordsInRows = list.size() / (howLines);
        //Adding the desired column to the ArrayList
        for (int i = 0; i != howLines; i++) {
            oneColum.add(list.get(columnIndex));
            //  We consider the index necessary to add the word
            columnIndex = columnIndex + wordsInRows;
        }
        //Returning the requested column
        return oneColum;
    }

}
