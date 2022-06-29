package com.shpp.p2p.cs.abolgov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {
    private static final String DICTIONARY_FILE = "en-dictionary.txt";
    public void run() {
        while (true) {
            String letters = readLine("Enter 3 letters");
            while (letters.length() != 3) {
                letters = readLine("You must enter exactly 3 letters");
            }
            try {
                word(letters, DICTIONARY_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reading words from a text file (which came with the assignment)
     *
     * @return Word list
     */
    public ArrayList readFile(String fileName) {
        try {
            BufferedReader newFile = new BufferedReader(new FileReader(fileName));
            String str;
            ArrayList<String> wordList = new ArrayList<>();
            while ((str = newFile.readLine()) != null) {
                if (!str.isEmpty()) {
                    wordList.add(str);
                }
            }
            newFile.close();
            return wordList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void word(String letters, String fileName) throws IOException {
        String[] file = (String[]) readFile(fileName).toArray(new String[0]);
        String word, goodWord = "a";
        int wordCounter = 0;
        //Convert all letters to lowercase
        letters = letters.toLowerCase();
        //Checking every word in a file
        for (String s : file) {
            //Convert all words to lowercase
            word = s.toLowerCase();
            for (int j = 0; j < word.length(); j++) {
                //We are looking for when the first entered letter will occur in the word
                if (letters.charAt(0) == word.charAt(j)) {
                    //When we find a match
                    // we cut off the index of those letters before which we need the one we need and
                    // look for when the second entered letter will meet
                    for (int k = ++j; k < word.length(); k++) {
                        if (letters.charAt(1) == word.charAt(k)) {
                            //When we find a match
                            // we cut off the index of those letters before which we need the one we need and
                            // look for when the third entered letter will meet
                            for (int l = ++k; l < word.length(); l++) {
                                if (letters.charAt(2) == word.charAt(l)) {
                                    //In order not to display the same words several times
                                    if (!goodWord.equals(word)) {
                                        wordCounter++;
                                        print(wordCounter + "->" + word + "  ");
                                        if ((wordCounter % 5) == 0) {
                                            println();
                                        }
                                        goodWord = word;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        println();
        println("Total print " + wordCounter + " word(s)");

    }

}

