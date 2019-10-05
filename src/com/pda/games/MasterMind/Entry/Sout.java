package com.pda.games.MasterMind.Entry;

import com.pda.games.MasterMind.GameMods.Party;
import com.pda.games.MasterMind.Model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Sout {

    protected static final Logger logger = LogManager.getLogger();

    private Sout() {
    }

    public static void gameIntroduce() {
        System.out.println("Welcome in mastermind !" +
                "\n-----------------------");
    }

    public static void askingHowManyPlayer() {
        System.out.println("\nHow many player human we have ?");

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

    public static void bot(int i) {
        System.out.println("Bot" + i);
    }

    public static void explainGameMod() {
        System.out.println("\nPlease select your game mod:" +
                "\n1-Challenger ( player one try to guess the player two hidden number ) " +
                "\n2-Defender ( player two try to guess the player one hidden number )" +
                "\n3-Duel ( both players try to find the opponent's hidden number )");
    }

    public static void initializationMessage(String playerName) {
        System.out.println("\nInitialization of secret digits of " + playerName + ":");
    }

    public static void rules(int size) {
        System.out.println("Rules :\n" +
                "-------\n" +
                "When secret digits are requested you need to enter " + size + " digits separated by the space" +
                "When a guess was requested you need to enter " + size + " digits separated by the space for trying to find the secret digits.\n" +
                "when a clue was requested you need to enter a symbol for each digits  of your adversary guess separated by the space.\n" +
                "\"+\" if the corresponding number in your secret line is bigger\n" +
                "\"-\" if he is lower\n" +
                "\"=\" if he is equal");
    }

    public static void launchPhrase() {
        System.out.println("\nSecret numbers are initialized\n" +
                "\nLet's the game begin !\n");
    }

    public static void actualRound(int roundCount) {
        System.out.println("Round " + roundCount + "/" + Party.getMaximumOfRounds() +
                "\n---------");
    }

    public static void memo(int[] guess, String[] clue) {
        System.out.println("Precedent guess : " + Arrays.toString(guess) +
                "\nPrecedent clue : " + Arrays.toString(clue));
    }

    public static void memo(int[] thing) {
        System.out.println("Memo: " + Arrays.toString(thing));
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

    public static void lineToFind(String playerName, int[] lineToFind) {
        System.out.println("The number was : \n" +
                "-" + playerName + ": " + Arrays.toString(lineToFind));
    }

    public static void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind) {
        System.out.println("The number was : \n" +
                "-" + firstPlayer + ": " + Arrays.toString(firstLineToFind) +
                "\n-" + secondPlayer + ": " + Arrays.toString(secondLineToFind));

    }

    public static void tryAgainMenu() {
        System.out.println("---------------------------" +
                "\nIf you want retry \"R\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
    }


    public static void wrongEntry(int i) {
        System.out.println("Your " + (i + 1) + getNumberSuffix(i + 1) + "entry was wrong");
    }

    private static String getNumberSuffix(int i) {
        if (i >= 11 && i <= 13) {
            return "th";
        }
        switch (i % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static void notEnough(int i) {
        System.out.println(i + " entry are needed");
    }

    public static void onlyBetween(int min, int max) {
        System.out.println("You can choose only between " + min + " and " + max);
    }

    public static void onlyDigits() {
        System.out.println("you have to enter only digits");
    }

    public static void onlySymbol() {
        System.out.println("you only need to enter the required symbols ");
    }

    public static void error() {
        System.out.println("we encountered an error please retry");
    }

}
