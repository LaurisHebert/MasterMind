package com.pda.games.mastermind.entry.languages;

import com.pda.games.mastermind.entry.Texts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TextsEN implements Texts {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void gameIntroduce() {
        logger.trace("Welcome in mastermind !" +
                "\n-----------------------");
    }

    @Override
    public void pseudoEntry(int i) {
        switch (i) {
            case 1:
                logger.trace("Player one pseudo :");
                break;
            case 2:
                logger.trace("\nPlayer two pseudo :");
                break;
        }
    }

    @Override
    public void explainGameMod() {
        logger.trace("\nPlease select your game mod:" +
                "\n1-Challenger ( player one try to guess the player two hidden number ) " +
                "\n2-Defender ( player two try to guess the player one hidden number )" +
                "\n3-Duel ( both players try to find the opponent's hidden number )");
    }

    @Override
    public void initializationMessage(String playerName) {
        logger.trace("\nInitialization of secret digits of " + playerName + ":");
    }

    @Override
    public void rules(int size) {
        logger.trace("Rules :\n" +
                "-------\n" +
                "When secret digits are requested you need to enter " + size + " digits separated by the space\n" +
                "When a guess was requested you need to enter " + size + " digits separated by the space for trying to find the secret digits.\n" +
                "when a clue was requested you need to enter a symbol for each digits  of your adversary guess separated by the space.\n" +
                "\"+\" if the corresponding number in your secret line is bigger\n" +
                "\"-\" if he is lower\n" +
                "\"=\" if he is equal\n");
    }

    @Override
    public void launchPhrase() {
        logger.trace("\nSecret numbers are initialized\n" +
                "\nLet's the game begin !\n");
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void actualRound(int roundCount, int maxRounds) {
        logger.trace("Round " + roundCount + "/" + maxRounds +
                "\n---------");
    }

    @Override
    public void memo(int[] guess, String[] clue) {
        logger.trace("Precedent guess : " + Arrays.toString(guess) +
                "\nPrecedent clue : " + Arrays.toString(clue));
    }

    @Override
    public void memo(int[] thing) {
        logger.trace("Memo: " + Arrays.toString(thing));
    }

    @Override
    public void askGuess(String playerName) {
        logger.trace(playerName + " guess :");
    }

    @Override
    public void askClue(String playerName) {
        logger.trace(playerName + " clue :");
    }

    @Override
    public void printArray(String[] thing) {
        logger.trace(Arrays.toString(thing) + "\n");
    }

    @Override
    public void printArray(int[] thing) {
        logger.trace(Arrays.toString(thing) + "\n");
    }

    @Override
    public void lineToFind(String playerName, int[] lineToFind) {
        logger.trace("The number was : \n" +
                "-" + playerName + ": " + Arrays.toString(lineToFind));
    }

    @Override
    public void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind) {
        logger.trace("The number was : \n" +
                "-" + firstPlayer + ": " + Arrays.toString(firstLineToFind) +
                "\n-" + secondPlayer + ": " + Arrays.toString(secondLineToFind));

    }

    @Override
    public void tryAgainMenu() {
        logger.trace("---------------------------" +
                "\nIf you want retry \"R\"" +
                "\nIf you want return menu \"H\"" +
                "\nIf you want quite \"Q\"");
    }

    @Override
    public void end() {
        logger.trace("Cya");
    }

    @Override
    public void win(String playerName) {
        logger.trace(playerName + " win !");
    }

    @Override
    public void Equally(String winOrLose) {
        logger.trace("EveryOne " + winOrLose + " !");
    }
}
