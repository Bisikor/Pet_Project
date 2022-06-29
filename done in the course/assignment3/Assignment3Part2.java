package com.shpp.p2p.cs.abolgov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Assignment3Part2 extends TextProgram {

    public void run() {
        String nDouble = readLine("Enter a number:");
        calculation((int) positiveChecker(oneChecker(integerChecker(inputValidation(nDouble)))));
    }

    /**
     * Method that checks if a number is an integer
     *
     * @param a The number entered by the user
     * @return integer entered by the user
     */
    public double integerChecker(double a) {
        String number;
        while (a % 1 != 0) {//integer or not
            println(a + " Is not integer");
            number = readLine("Enter an integer:");
            a = positiveChecker(oneChecker(inputValidation(number)));
        }
        return a;
    }

    /**
     * Method that checks if the number is 1
     *
     * @param a The number entered by the user
     * @return a number other than 1 entered by the user
     */
    public double oneChecker(double a) {
        String number;
        while (a == 1) {
            println("The number is already equal to 1 no calculations need to be done");
            number = readLine("Enter a number:");
            a = positiveChecker(integerChecker(inputValidation(number)));
        }
        return a;
    }

    /**
     * Method that checks if a number is positive
     *
     * @param a The number entered by the user
     * @return positive number entered by the user
     */
    public double positiveChecker(double a) {
        String number;
        DecimalFormat decimalFormat = new DecimalFormat("#");
        while (a <= 0) {//positive or not
            println("Number " + decimalFormat.format(a) + " not positive");
            number = readLine("Enter a number:");
            a = oneChecker(integerChecker(inputValidation(number)));
        }
        return a;
    }

    /**
     * Checking if a string is numeric
     *
     * @param line accept some string
     * @return true if the string can be converted to double
     * @throws NumberFormatException this exception is thrown when
     *                               it is not possible to convert a string to a numeric type(return false)
     */
    public boolean isDigit(String line) throws NumberFormatException {
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * A method that asks the user to enter a number and will do so until the user enters it.
     *
     * @param line the string entered by the user
     * @return converted user-entered string to double
     */
    public double inputValidation(String line) {
        while (!isDigit(line)) {
            System.out.println("Should be digit");
            line = readLine("Please enter a digit");
        }
        return Double.parseDouble(line);
    }

    /**
     * We perform the necessary operations with the number
     *
     * @param n Calculation result
     */
    public void calculation(int n) {
        while (n != 1) {
            if (n % 2 != 0) {//If odd
                int forFormulaOdd = ((n * 3) + 1);
                println(n + " Is odd so I make 3 * " + n + " + 1: " + forFormulaOdd);
                n = forFormulaOdd;
            } else {//If even
                int forFormulaEven = (n / 2);
                println(n + " Is even so I take half: " + forFormulaEven);
                n = forFormulaEven;
            }
        }
    }

}