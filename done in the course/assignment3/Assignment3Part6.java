package com.shpp.p2p.cs.abolgov.assignment3;

import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.Random;

public class Assignment3Part6 extends WindowProgram {
    double starTime = System.currentTimeMillis();
    //Changes the radius of the ball
    private final int BALL_RADIUS = 80;
    private final GOval starterBall = new GOval(0, 0, BALL_RADIUS / 2.0, BALL_RADIUS / 2.0);
    //The global variable is responsible for moving the ball along the X coordinate
    private static double ballMoveX = 5;
    //The global variable is responsible for moving the ball along the Y coordinate
    private static double ballMoveY = 5;

    public void run() {

        //Calculating when to end an animation
        long endTime = System.currentTimeMillis() + 5000;
        double end = 0;
        addBall();
        while (System.currentTimeMillis() < endTime) {
            pause(1000.0 / 50);//50 fps
            //Remembers when a cycle ends
            end = System.currentTimeMillis();
            starterBall.move(ballMoveX, ballMoveY);
            //Change the direction and speed of movement if the ball touches the bottom of the screen
            if (!down()) {
                ballMoveX = randomPlusMinus();
                ballMoveY = -randomPlus();
            }
            //Change the direction and speed of movement if the ball touches the top of the screen
            if (!up()) {
                ballMoveX = randomPlusMinus();
                ballMoveY = randomPlus();
            }
            //Change the direction and speed of movement if the ball touches the right edge of the screen
            if (!right()) {
                ballMoveX = -randomPlus();
                ballMoveY = randomPlusMinus();
            }
            //Change the direction and speed of movement if the ball touches the left edge of the screen
            if (!left()) {
                ballMoveX = randomPlus();
                ballMoveY = randomPlusMinus();
                end = System.currentTimeMillis();
            }
        }
        //Formula for calculating the difference between starting an animation and stopping it
        timer(String.valueOf((end - starTime) / 1000));
    }

    /**
     * Create a circle
     */

    public void addBall() {
        double sizeBall = (BALL_RADIUS / 2.0);
        GOval ball = starterBall;
        ball.setSize(sizeBall, sizeBall);
        ball.setLocation((getWidth() - sizeBall) / 2, (getHeight() - sizeBall) / 2);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    /**
     * Random number between 5 and 10
     *
     * @return Returns the generated random number
     */

    private double randomPlus() {
        double randomNumber;
        RandomGenerator rgen = RandomGenerator.getInstance();
        randomNumber = rgen.nextDouble(5.0, 10.0);
        return randomNumber;
    }

    /**
     * Random number between 5-10 with a random sign in front of it
     *
     * @return Returns the generated random number
     */

    private double randomPlusMinus() {
        double randomNumber;
        Random randomBool = new Random();
        boolean randomSign = randomBool.nextBoolean();
        RandomGenerator rgen = RandomGenerator.getInstance();
        randomNumber = rgen.nextDouble(5.0, 10.0);
        if (randomSign) {
            randomNumber = -randomNumber;
        }
        return randomNumber;
    }

    /**
     * Creating a label Middle of the screen
     *
     * @param timePassed How long has it been since the app was launched
     */
    public void timer(String timePassed) {
        GLabel timer = new GLabel("Time after start -> " + timePassed, 0, 0);
        timer.setColor(Color.BLACK);
        timer.setFont("Verdana-50");
        timer.sendToFront();
        timer.setLocation(
                (getWidth() - timer.getWidth()) / 2,
                getHeight() / 2.0);
        add(timer);
    }

    /**
     * @return if the ball touches the bottom of the screen
     */
    private boolean down() {
        return starterBall.getY() + starterBall.getHeight() < getHeight();
    }

    /**
     * @return if the ball touches the top of the screen
     */
    private boolean up() {
        return starterBall.getY() + starterBall.getHeight() > BALL_RADIUS / 2.0;
    }

    /**
     * @return if the ball touches the right edge of the screen
     */
    private boolean right() {
        return starterBall.getX() + starterBall.getWidth() < getWidth();
    }

    /**
     * @return if the ball touches the left edge of the screen
     */
    private boolean left() {
        return starterBall.getX() > 0;
    }


}