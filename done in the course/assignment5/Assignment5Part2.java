package com.shpp.p2p.cs.abolgov.assignment5;


import com.shpp.cs.a.console.TextProgram;

public class Assignment5Part2 extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number: ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param num1 The first number.
     * @param num2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String num1, String num2) {

        //Find out the length of the entered numbers
        int lengthFirsNumber = num1.length(), lengthSecondNumber = num2.length();

        StringBuilder numberOne = new StringBuilder();
        StringBuilder numberTwo = new StringBuilder();


        //We look at which number is greater in the number of digits
        if (lengthFirsNumber >= lengthSecondNumber) {
            numberOne.append(num1);
            numberTwo.append(num2);
        } else {
            //If the second swap the numbers
            numberOne.append(num2);
            numberTwo.append(num1);
            lengthSecondNumber = num1.length();
            lengthFirsNumber = num2.length();
        }
        //Flip over (because the score is in a column from right to left)
        numberOne.reverse();
        numberTwo.reverse();

        return (calculate(lengthFirsNumber, lengthSecondNumber, numberOne, numberTwo));
    }

    private String calculate(int lengthFirsNumber, int lengthSecondNumber, StringBuilder numberOne, StringBuilder numberTwo) {
        StringBuilder answer2 = new StringBuilder();
        int inMind = 0, intermediateResult;

        //We first add the numbers that are under each other (column addition)
        for (int i = 0; i < lengthSecondNumber; i++) {
            intermediateResult = numberOne.charAt(i) - '0' + numberTwo.charAt(i) - '0' + inMind;
            //If it turns out a dozen, leave the unit (in the mind)
            inMind = (intermediateResult / 10);
            //The result of the calculation is added to the end
            answer2.append(intermediateResult % 10);
        }

        //We count the remaining numbers (add what was "in the mind")
        for (int i = lengthSecondNumber; i < lengthFirsNumber; i++) {
            intermediateResult = numberOne.charAt(i) - '0' + inMind;
            inMind = (intermediateResult / 10);
            answer2.append(intermediateResult % 10);
        }
        //If there is something "in the mind" we increase the final number (for example 99+1=100)
        if (inMind != 0) {
            answer2.append(inMind);
        }

        //flip the result
        answer2.reverse();
        return String.valueOf((answer2));
    }

}
