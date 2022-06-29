package com.shpp.p2p.cs.abolgov.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

public class Assignment3Part5 extends TextProgram {
    public static int allCoin = 0;//The global variable counts the number of coins earned
    public static int numberOfGames = 0;//The global variable counts the number of games played

    public void run() {
        allGame();
    }

    /**
     *Main part of the game
     */
    public void partGame() {
        int put = 1;
        Random randomBool = new Random();
        //Simulates a coin toss (true-heads) and (false-tails)
        boolean coin = randomBool.nextBoolean();
        while (coin) {//Cycle until heads come up (true)
            put = put + put;//We count the increase in coins on the table
            coin = randomBool.nextBoolean();//flip a coin
        }
        println("This game, you earned " + put);
        allCoin = allCoin + put;//Calculate the winning amount
        numberOfGames++;//Counting the number of games played
        println("Your total is " + allCoin + "$");
    }

    /**
     *Keep track of when the game is over (20 coins will be collected after the game)
     */
    public void allGame() {
        while (allCoin < 20) {
            partGame();
        }
        println("It took " + numberOfGames + " games to earn $20");
    }
}