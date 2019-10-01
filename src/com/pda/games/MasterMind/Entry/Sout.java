package com.pda.games.MasterMind.Entry;

import com.pda.games.MasterMind.Structure.MasterMind;
import com.pda.games.MasterMind.Structure.Player;

import java.util.Arrays;

public class Sout {

    private Sout() {
    }

    public static void initializationMessage(String playerName) {
        System.out.println("\nInitialization of secret digits of " + playerName + ":");
    }

    public static void launchPhrase() {
        System.out.println("\nSecret number initialized\n" +
                "\nLet's the game begin !\n");
    }

    public static void actualRound(int roundCount) {
        System.out.println("Round " + roundCount + "/" + MasterMind.maximumOfRounds +
                "\n---------");
    }

    public static void reminder(int[] guess, String[] clue) {
        System.out.println("Precedent guess : " + Arrays.toString(guess) +
                "\nPrecedent clue : " + Arrays.toString(clue));
    }

    public static void askGuess(String playerName) {
        System.out.println(playerName + " guess :");
    }

    public static void askClue(String playerName) {
        System.out.println(playerName + " clue :");
    }

    public static void printArray(String[] thing) {
        System.out.println(Arrays.toString(thing) + "\n");
    }

    public static void printArray(int[] thing) {
        System.out.println(Arrays.toString(thing) + "\n");
    }


    public static void gameIntroduce() {
        System.out.println("Welcome in mastermind !" +
                "\n-----------------------");
    }

    public static void askingHowManyPlayer() {
        System.out.println("\nHow many player human we have ?");

    }

    public static void onlyBetween(int min, int max) {
        System.out.println("You can choose only between " + min + " and " + max);
    }

    public static void rules() {
        System.out.println("\nPlease select your game mod:" +
                "\n1- Challenger ( player one try to guess the player two hidden number ) " +
                "\n2- Defender ( player two try to guess the player one hidden number )" +
                "\n3- Duel ( both players try to find the opponent's hidden number )");
    }

    public static void again() {
        System.out.println("---------------------------" +
                "\nIf you want retry \"R\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
    }

    public static void lineToFind(String playerName, int[] lineToFind) {
        System.out.println("The number was : \n" +
                "-" + playerName + ": " + Arrays.toString(lineToFind));
    }

    public static void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind) {
        System.out.println("The number was : \n" +
                "-" + firstPlayer + ": " + Arrays.toString(firstLineToFind) +
                "\n-" + secondPlayer + ": " + Arrays.toString(secondLineToFind));

    }

    public static void pseudoEntry(int i) {
        switch (i) {
            case 1:
                System.out.println("Player one pseudo :");
                break;
            case 2:
                System.out.println("\nPlayer two pseudo :");
                break;
        }
    }

    public static void wrongEntry(int i, int r) {
        if (i == 0)
            System.out.println("Your " + (i + 1) + "st digit \"" + r + "\" is not between " + Player.minimalValue + " and " + Player.maximumValue);
        else if (i == 1)
            System.out.println("Your " + (i + 1) + "nd digit \"" + r + "\" is not between " + Player.minimalValue + " and " + Player.maximumValue);
        else
            System.out.println("Your " + (i + 1) + "rd digit \"" + r + "\" is not between " + Player.minimalValue + " and " + Player.maximumValue);
    }

    public static void notEnough(int i){
        System.out.println(i + " entry are needed");
    }
}
