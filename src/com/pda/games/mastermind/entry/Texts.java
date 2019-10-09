package com.pda.games.mastermind.entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Texts {

    protected static final Logger logger = LogManager.getLogger();

    private Texts() {
    }

    public static void gameIntroduce() {
        logger.trace("Welcome in mastermind !" +
                "\n-----------------------");
    }

    public static void askingHowManyPlayer() {
        logger.trace("\nHow many player human we have ?");

    }

    public static void pseudoEntry(int i) {
        switch (i) {
            case 1:
                logger.trace("Player one pseudo :");
                break;
            case 2:
                logger.trace("\nPlayer two pseudo :");
                break;
        }
    }

    public static void bot(int i) {
        logger.trace("Bot" + i);
    }

    public static void explainGameMod() {
        logger.trace("\nPlease select your game mod:" +
                "\n1-Challenger ( player one try to guess the player two hidden number ) " +
                "\n2-Defender ( player two try to guess the player one hidden number )" +
                "\n3-Duel ( both players try to find the opponent's hidden number )");
    }

    public static void initializationMessage(String playerName) {
        logger.trace("\nInitialization of secret digits of " + playerName + ":");
    }

    public static void rules(int size) {
        logger.trace("Rules :\n" +
                "-------\n" +
                "When secret digits are requested you need to enter " + size + " digits separated by the space" +
                "When a guess was requested you need to enter " + size + " digits separated by the space for trying to find the secret digits.\n" +
                "when a clue was requested you need to enter a symbol for each digits  of your adversary guess separated by the space.\n" +
                "\"+\" if the corresponding number in your secret line is bigger\n" +
                "\"-\" if he is lower\n" +
                "\"=\" if he is equal\n");
    }

    public static void launchPhrase() {
        logger.trace("\nSecret numbers are initialized\n" +
                "\nLet's the game begin !\n");
    }

    public static void actualRound(int roundCount, int maxRounds) {
        logger.trace("Round " + roundCount + "/" + maxRounds +
                "\n---------");
    }

    public static void memo(int[] guess, String[] clue) {
        logger.trace("Precedent guess : " + Arrays.toString(guess) +
                "\nPrecedent clue : " + Arrays.toString(clue));
    }

    public static void memo(int[] thing) {
        logger.trace("Memo: " + Arrays.toString(thing));
    }

    public static void askGuess(String playerName) {
        logger.trace(playerName + " guess :");
    }

    public static void askClue(String playerName) {
        logger.trace(playerName + " clue :");
    }

    public static void printArray(String[] thing) {
        logger.trace(Arrays.toString(thing) + "\n");
    }

    public static void printArray(int[] thing) {
        logger.trace(Arrays.toString(thing) + "\n");
    }

    public static void lineToFind(String playerName, int[] lineToFind) {
        logger.trace("The number was : \n" +
                "-" + playerName + ": " + Arrays.toString(lineToFind));
    }

    public static void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind) {
        logger.trace("The number was : \n" +
                "-" + firstPlayer + ": " + Arrays.toString(firstLineToFind) +
                "\n-" + secondPlayer + ": " + Arrays.toString(secondLineToFind));

    }

    public static void tryAgainMenu() {
        logger.trace("---------------------------" +
                "\nIf you want retry \"R\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
    }

    public static void end() {
        logger.trace("Cya");
    }

    public static void win(String playerName) {
        logger.trace(playerName + " win !");
    }

    public static void Equally(String winOrLose) {
        logger.trace("EveryOne " + winOrLose + " !");


    }
}
