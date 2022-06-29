package com.shpp.p2p.cs.abolgov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.text.DecimalFormat;

public class Assignment3Part3 extends TextProgram {


    public void run() {
        String stringBase = readLine("Enter base");
        double doubleBase = inputValidation(replaced(stringBase));
        String stringExponent = readLine("Enter exponent");
        int IntegerExponent = (int) integerChecker(inputValidation(replaced(stringExponent)));
        println(correctOutput(doubleBase) + "^" + correctOutput(IntegerExponent) + " = "
                + correctOutput(raiseToPower(doubleBase, IntegerExponent)));
    }

    /**
     * Exaltation to the degree
     *
     * @param base     of degree
     * @param exponent of degree
     * @return the result of the calculation
     */
    private double raiseToPower(double base, int exponent) {
        if (base == 0)
            return 0;
        if (exponent == 0)
            return 1;
        double result = 1;
        if (exponent > 0) {//If the exponent is greater than 0
            for (int i = 0; i != exponent; i++) {//how many times do you need to multiply
                result = (result * base);
            }
        } else {
            //If the exponent is less than 0
            for (int i = 0; i != exponent; i--) {//how many times do you need to multiply
                result = (result * base);
            }
            result = 1 / result;//This is how you raise it to a negative power.
        }
        return (result);
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
            a = (inputValidation(number));
        }
        return a;
    }

    /**
     * @param number The number entered by the user
     * @return If after the comma there are only zeros, we return it without a comma
     */
    public String correctOutput(double number) {
        if (number % 1 == 0) {
            DecimalFormat decimalFormat = new DecimalFormat("#");
            return decimalFormat.format(number);
        }
        return String.valueOf(number);
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
     * @param line accept some string
     * @return a string with replaced "," -> "."
     */
    public String replaced(String line) {
        return line.replaceAll(",", "\\.");
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

}