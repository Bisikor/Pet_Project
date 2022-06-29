package com.shpp.p2p.cs.abolgov.assignment5;


import com.shpp.cs.a.console.TextProgram;


public class Assignment5Part1 extends TextProgram {
    public void run() {


        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            if (word.equals("")) {
                println("You didn't enter a word");
            } else {
                println("  Syllable count: " + syllablesIn(word));
            }
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int number = 0, numberV = 0;
        //Change everything entered to lowercase
        word = word.toLowerCase();
        //The letters we are going to look for in the word
        char[] vowel = {'e', 'y', 'u', 'i', 'o', 'a'};
        //Loop through all the letters in a word
        for (int i = 0; i < word.length(); i++) {
            //Loop through all the letters we are looking for
            for (int j = vowel.length - 1; j >= 0; j--) {
                //If there is a match, add 1 to the counter
                if (word.charAt(i) == vowel[j]) {
                    number++;
                    numberV++;
                    for (char c : vowel) {
                        if (i + numberV < word.length() && word.charAt(i + numberV) == c) {
                            number--;
                        }
                    }
                }
                numberV = 0;
            }
        }
        return endCheker(vowel, word, number);
    }

    /**
     * checks for the end of a word
     *
     * @param vowel  the letters I'm looking for
     * @param word   the word I'm looking for
     * @param number number of syllables
     * @return Total number of syllables
     */
    private int endCheker(char[] vowel, String word, int number) {
        boolean voil = true;
        if (word.length() - 2 >= 0) {
            for (char c : vowel) {
                if (word.charAt(word.length() - 2) == c) {
                    voil = false;
                    break;
                }
            }
            if (number != 0 && word.charAt(word.length() - 1) == vowel[0] && voil) {
                number--;
            }
        }
        //Checking words like Me
        for (char c : vowel) {
            boolean b = word.charAt(word.length() - 1) == c;
            if (number == 0 && b) {
                number++;
            }
        }
        return number;
    }
}
