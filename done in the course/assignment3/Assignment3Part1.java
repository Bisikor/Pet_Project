package com.shpp.p2p.cs.abolgov.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {

    public void run() {
        dayCounter();
    }


    /**
     *
     * Counts the number of days the user has practiced the required time for each activity
     */
    public void dayCounter() {
        String minuteReader;
        double minutesOneDay;
        int cardiovacularHealth = 0; //Counting days with completed cardio
        int bloodPressure = 0;//Counting days with completed exercises for pressure
        for (int i = 1; i != 8; i++) {
            minuteReader = readLine("How many minutes did you do on day " + i + "?");
            minutesOneDay = moreDayChecker(quadraticEquationReader(inputValidation(replaced(minuteReader))));
            if (minutesOneDay >= 30) {//whether you did the required amount of time for cardio
                cardiovacularHealth++;
                if (minutesOneDay >= 40) {//have you done the required amount of pressure time
                    bloodPressure++;
                }
            }
        }
        trainChecker(cardiovacularHealth, bloodPressure);
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
     * A method that checks if the first coefficient of a quadratic equation is not equal to zero
     *
     * @param a The number entered by the user
     * @return Returns the number entered by the user
     */
    public double quadraticEquationReader(double a) {
        String number;
        while (a < 0) {
            System.out.println("You can't do negative amounts of time.\n" +
                    "Enter data correctly");
            number = readLine("Please enter correct time");
            a = moreDayChecker(inputValidation(replaced(number)));
        }
        return a;
    }

    /**
     *
     * @param a The number entered by the user
     * @return the correct number entered by the user
     */
    public double moreDayChecker(double a) {
        String number;
        while (a > 14400) {
            System.out.println("There are 1440 minutes in a day, you could not do more.\n" +
                    " Enter data correctly");
            number = readLine("Please enter correct time");
            a = quadraticEquationReader(inputValidation(replaced(number)));
        }
        return a;
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

    /**
     *
     * @param line
     * @return
     */
    public double inputValidation(String line) {
        while (!isDigit(line)) {
            System.out.println("Should be digit");
            line = readLine("Please enter a digit");
        }
        return Double.parseDouble(line);
    }


    /**
     * Count of days of completed or not workouts for the entire week
     *
     * @param cardiovacularHealth Counting days with completed cardio
     * @param BloodPressure       Counting days with completed exercises for pressure
     */
    public void trainChecker(int cardiovacularHealth, int BloodPressure) {
        int needDayCardio = 5;//required number of training days cardio
        int needDayPressure = 3;//required number of training days pressure
        int dayC, dayP;
        //Checking if a person was doing the required number of cardio days per week
        if (cardiovacularHealth >= needDayCardio) {
            println("Cardiovacular health:\n" +
                    "  Great job! You've done enough exercise for cardiovacular health.");
        } else {
            dayC = (needDayCardio - cardiovacularHealth);
            println("Cardiovacular health:\n" +
                    "  You needed to train hard for at least " + dayC + " more day(s) a week!");
        }
        //Checking if a person was doing the required number train for pressure days per week
        if (BloodPressure >= needDayPressure) {
            println("Blood pressure:\n" +
                    "  Great job! You've done enough exercise to keep a low blood pressure.");
        } else {
            dayP = (needDayPressure - BloodPressure);
            println("Blood pressure:\n" +
                    "  You needed to train hard for at least " + dayP + " more day(s) a week!");
        }
    }
}
