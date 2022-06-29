package com.shpp.p2p.cs.abolgov.assignment4;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.random.RandomGenerator;

public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 500;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of game board (usually the same)
     */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW =2;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 2;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Width of a brick
     */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;
    /**
     * Radius of the ball in pixels
     */
    private static final double BALL_RADIUS = 30;
    /**
     * Radius of the ball in pixels
     */
    private static final double BALL_SIZE = BALL_RADIUS * 2;
    /**
     * Half radius of the ball in pixels
     */
    private static final double HALF_BALL_RADIUS = BALL_SIZE / 2;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 40;

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;

    /**
     * Game ball
     */
    private final GOval ball = new GOval(0, 0, BALL_SIZE, BALL_SIZE);
    /**
     * Game rocket
     */
    private GRect rocket;
    /**
     * A global variable that is responsible for the speed of the ball along the coordinate X
     * (Changes during the game)
     */
    private static double ballMoveX = 0;
    /**
     * A global variable that is responsible for the speed of the ball along the coordinate Y
     * (Changes during the game)
     */
    private static double ballMoveY = 3;
    /**
     * Global variable that is responsible for the number of remaining lives
     */
    private static int life = NTURNS+1;
    /**
     * 50 fps
     */
    private static final int PAUSE_TIME = 1000 / 50;
    /**
     * Counts the number of blocks on the screen
     */
    private static int numberOfBrick = NBRICK_ROWS * NBRICKS_PER_ROW;
    /**
     * Number of points for 1 brick
     */
    private static final int POINT_OF_ONE_BRICK = 10;


    public void run() {
        //Number of blocks on the screen
        int point = 0;
        addMouseListeners();
        //Draw all the blocks
        addAllBricks();
        //Let's draw a rocket
        addRocket();
        //Ball drawing
        addBall();
        //As long as there are lives and the blocks have not run out
        while (lifeChecker() && numberOfBrick > 0) {
            pause(PAUSE_TIME); //fps
            //Checking if the ball collided with something
            ballChecker();
            //If you encounter a block
            if (brickChecker()) {
                point = point + POINT_OF_ONE_BRICK;
                numberOfBrick--;
            }
        }
        //Remove all remaining objects from the screen
        removeAll();
        //If the blocks are over
        if (numberOfBrick == 0) {
            add(textLabel(("Won"), getHeight() / 3.0));
            add(textLabel(("Your score= " + point), getHeight() / 2.0));
        } else {
            add(textLabel(("Loose"), getHeight() / 3.0));
            add(textLabel(("Your score= " + point), getHeight() / 2.0));
        }
    }

    /**
     * Method for monitoring mouse clicks
     *
     * @return Returns true if PKM has been pressed
     */
    public boolean waiter() {
        waitForClick();
        return true;
    }

    /**
     * Method that adds a rocket to the screen
     */
    public void addRocket() {
        this.rocket = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
        float locationX = (getWidth() - PADDLE_WIDTH);
        rocket.setLocation(locationX / 2, (getHeight() - PADDLE_Y_OFFSET));
        rocket.setFilled(true);
        rocket.setColor(Color.BLACK);
        add(rocket);
    }

    /**
     * Method that makes the rocket follow the mouse
     *
     * @param e Mouse movement
     */
    public void mouseMoved(MouseEvent e) {
        int coordinateX;
        if (e.getX() < PADDLE_WIDTH / 2) {
            coordinateX = PADDLE_WIDTH / 2;
        } else coordinateX = Math.min(e.getX(), (APPLICATION_WIDTH - PADDLE_WIDTH / 2));
        rocket.setLocation((coordinateX - PADDLE_WIDTH / 2.0), getHeight() - PADDLE_Y_OFFSET);
    }

    /**
     * Adds a ball to the screen
     */
    public void addBall() {
        double sizeBall = (BALL_SIZE / 2.0);
        GOval ball = this.ball;
        ball.setSize(sizeBall, sizeBall);
        ball.setLocation((getWidth() - sizeBall) / 2, (getHeight() - sizeBall) / 2);
        ball.setFilled(true);
        ball.setColor(Color.BLACK);
        ball.setFillColor(Color.BLACK);
        add(ball);
    }

    /**
     * Method that checks if the ball has fallen to the bottom of the screen
     *
     * @return if it fell return true
     */
    private boolean ballHitTheDownOfWindow() {
        return (ball.getY() >= (APPLICATION_HEIGHT));
    }

    /**
     * A method that monitors whether the ball has collided with the ceiling
     *
     * @return returns true if the ball collided
     */
    private boolean ballHitTheTopOfWindow() {
        return (ball.getX() <= 0 || ball.getX() > (APPLICATION_WIDTH - (BALL_SIZE / 2)));
    }

    /**
     * Method that checks if the ball hit the window wall
     *
     * @return returns true if the ball collided
     */
    private boolean ballHitTheRightOrLeftOfWindow() {
        return (ball.getY() <= 0 && ballMoveY <= 0);

    }

    /**
     * Random number between 1-3 with a random sign in front of it
     *
     * @return Returns the generated random number
     */
    private double randomPlusMinus() {
        double randomNumber;
        Random randomBool = new Random();
        boolean randomSign = randomBool.nextBoolean();
        RandomGenerator rgen = RandomGenerator.getDefault();
        randomNumber = rgen.nextDouble(1.0, 3);
        if (randomSign) {
            randomNumber = -randomNumber;
        }
        return randomNumber;
    }

    /**
     * Draws 1 row of blocks on the screen
     *
     * @param brickY Y coordinate for block row
     * @param color  Color for block row
     */
    private void addOneLevelsBrick(int brickY, Color color) {
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            GRect brick = new GRect(
                    (i * (BRICK_WIDTH + BRICK_SEP)),
                    brickY,
                    BRICK_WIDTH,
                    BRICK_HEIGHT);
            brick.setFilled(true);
            brick.setFillColor(color);
            add(brick);
        }
    }

    /**
     * Draws all block lines depending on the constant.
     * Each two rows has a new color in the sequence: RED, ORANGE, YELLOW, GREEN, CYAN.
     */
    private void addAllBricks() {
        int numberColor = 1;
        Color brickColor = null;
        for (int i = 0; i < NBRICK_ROWS; i++) {
            if (numberColor <= 2) {
                brickColor = Color.RED;
            } else if (numberColor <= 4) {
                brickColor = Color.ORANGE;
            } else if (numberColor <= 6) {
                brickColor = Color.YELLOW;
            } else if (numberColor <= 8) {
                brickColor = Color.GREEN;
            } else if (numberColor <= 9) {
                brickColor = Color.CYAN;
                numberColor++;
            } else if (numberColor / 11 == 1) {
                numberColor = 0;
            }
            addOneLevelsBrick((BRICK_Y_OFFSET + i * (BRICK_SEP + BRICK_HEIGHT)), brickColor);
            numberColor++;
        }
    }

    /**
     * The method is responsible for checking if the top points of the ball collided with a block
     *
     * @return Returns the block that the ball collided with
     */
    private GObject ballTopChecker() {
        GObject brickChecker;
        GPoint[] ballTouchPoints = new GPoint[3];
        ballTouchPoints[0] = new GPoint(ball.getX(), ball.getY());
        ballTouchPoints[1] = new GPoint(ball.getX() + BALL_SIZE * 2, ball.getY());
        ballTouchPoints[2] = new GPoint(ball.getX() + HALF_BALL_RADIUS, ball.getY());
        for (int i = 0; i < 3; i++) {
            brickChecker = getElementAt(ballTouchPoints[i]);
            if (brickChecker != null && ball.getY() + BALL_SIZE < getHeight() - PADDLE_Y_OFFSET && brickChecker != rocket) {
                return (brickChecker);
            }
        }
        return null;
    }

    /**
     * The method is responsible for checking if the down points of the ball collided with a block
     *
     * @return Returns the block that the ball collided with
     */
    private GObject ballDownChecker() {
        GObject brickChecker;
        GPoint[] ballTouchPoints = new GPoint[3];
        ballTouchPoints[0] = new GPoint(ball.getX(), ball.getY() + BALL_SIZE);
        ballTouchPoints[1] = new GPoint(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE);
        ballTouchPoints[2] = new GPoint(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE);
        for (int i = 0; i < 3; i++) {
            brickChecker = getElementAt(ballTouchPoints[i]);
            if (brickChecker != null && ball.getY() + BALL_SIZE < getHeight() - PADDLE_Y_OFFSET && brickChecker != rocket) {
                return (brickChecker);
            }
        }
        return null;
    }

    /**
     * Method that checks if the ball collided with a block (additional points)
     *
     * @return Returns the block that the ball collided with
     */
    private GObject ballSideChecker() {
        GObject brickChecker;
        GPoint[] ballTouchPoints = new GPoint[2];
        ballTouchPoints[0] = new GPoint(ball.getX(), ball.getY() + HALF_BALL_RADIUS);
        ballTouchPoints[1] = new GPoint(ball.getX() + BALL_SIZE * 2, ball.getY() + HALF_BALL_RADIUS);
        for (int i = 0; i < 2; i++) {
            brickChecker = getElementAt(ballTouchPoints[i]);
            if (brickChecker != null && ball.getY() + HALF_BALL_RADIUS < getHeight() - PADDLE_Y_OFFSET && brickChecker != rocket) {
                return (brickChecker);
            }
        }
        return null;
    }

    /**
     * A method that changes the speed of the ball in X and Y if it collides with a brick
     *
     * @return true if encountered, false if everything is clean
     */
    private boolean brickChecker() {
        if (ballTopChecker() != null) {
            ballMoveY = -ballMoveY;
            remove(ballTopChecker());
            return true;
        } else if (ballDownChecker() != null) {
            ballMoveY = -ballMoveY;
            ballMoveX = -ballMoveX;
            remove(ballDownChecker());
            return true;
        } else if (ballSideChecker() != null) {
            ballMoveY = -ballMoveY;
            ballMoveX = -ballMoveX;
            remove(ballSideChecker());
            return true;
        }
        return false;
    }

    /**
     * A method that changes the speed of the ball in X and Y if it collides with the application window or rocket
     * If the ball falls to the bottom, it will redraw it and display a notification
     */
    private void ballChecker() {
            GObject label = textLabel("Press to Continue", getHeight());
            ball.move(ballMoveX, ballMoveY);

            if (ballTouchRocket() && ballMoveY > 0) {

                ballMoveY = -ballMoveY;


            } else {
                if (ballHitTheTopOfWindow()) {
                    ballMoveX = -ballMoveX;
                }
                if (ballHitTheRightOrLeftOfWindow()) {
                    ballMoveY = -ballMoveY;
                    ballMoveY = -ballMoveY;
                } else if (ballHitTheDownOfWindow() || life == NTURNS + 1) {
                    remove(ball);
                    life--;
                    numberOfBrick++;
                    addBall();
                    add(label);
                    if (waiter()) {
                        remove(label);
                        ballMoveX += randomPlusMinus();
                    }
                }
            }
    }

    /**
     * Method that checks if the ball collided with the rocket
     *
     * @return Returns true if encountered
     */
    private boolean ballTouchRocket() {
        return (
                rocket.getX() <= ball.getX() + BALL_SIZE
                        && rocket.getX() + PADDLE_WIDTH > ball.getX()
                        && ball.getY() + BALL_SIZE / 2 > getHeight() - PADDLE_Y_OFFSET
                        && ball.getY() + BALL_SIZE / 2 < getHeight() - PADDLE_Y_OFFSET + PADDLE_HEIGHT);

    }

    /**
     * Method for displaying notifications
     *
     * @param text        What needs to be displayed
     * @param CoordinateY Label Y Position
     * @return Ready object label
     */
    private GObject textLabel(String text, double CoordinateY) {
        GLabel textLabel = new GLabel(text, 0, 0);
        textLabel.setColor(Color.BLACK);
        textLabel.setFont("Verdana-45");
        textLabel.sendToFront();
        textLabel.setLocation(
                (getWidth() - textLabel.getWidth()) / 2,
                CoordinateY - BALL_SIZE / 3.0);
        return (textLabel);
    }


    /**
     * Checks if lives have ended
     *
     * @return Returns true if a life > 0
     */
    private boolean lifeChecker() {
        return (life > 0);
    }

}