package com.pda.games.mastermind.entry.languages;

import com.pda.games.mastermind.entry.Texts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class TextsFR implements Texts {

    private static final Logger logger = LogManager.getLogger();

    public void gameIntroduce() {
        logger.trace("Bienvenue dans MasterMind !" +
                "\n-----------------------");
    }

    @Override
    public void pseudoEntry(int i) {
        switch (i) {
            case 1:
                logger.trace("Joueur un entrez votre pseudonyme :");
                break;
            case 2:
                logger.trace("\nJoueur deux entrez votre pseudonyme :");
                break;
        }
    }


    @Override
    public void explainGameMod() {
        logger.trace("\nSélectionnez votre mod:" +
                "\n1-Challenger ( le joueur un essaye de deviner la combinaison du joueur deux ) " +
                "\n2-Defender ( le joueur deux essaye de deviner la combinaison du joueur un )" +
                "\n3-Duel ( les deux joueurs essayent de chercher la combinaison de leur adverssaire )");
    }


    @Override
    public void initializationMessage(String playerName) {
        logger.trace("\nInitialisation de la combinaison de " + playerName + ":");
    }


    @Override
    public void rules(int size) {
        logger.trace("Règles :\n" +
                "-------\n" +
                "Quand une combinaison est demandée, vous devez entrer " + size + " chiffres, séparés par un espace.\n" +
                "Quand une \"proposition\" est demandée, vous devez entrer  " + size + " chiffres, séparés par un espace, pour essayer de trouver la combinaison de votre adversaire.\n" +
                "Quand un indice vous est demandé, vous devez entrer un symbole pour chaques chiffres de l'adversaire, séparés par un espace\n" +
                "\"+\" si le chiffre de votre combinaison est plus grand que celui donné par l'adversaire.\n" +
                "\"-\" si il est plus petit\n" +
                "\"=\" si il est égal.\n");
    }


    @Override
    public void launchPhrase() {
        logger.trace("\nCombinaison initialisée\n" +
                "\nQue le jeu commence !\n");
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public void actualRound(int roundCount, int maxRounds) {
        logger.trace("Tour " + roundCount + "/" + maxRounds +
                "\n---------");
    }


    @Override
    public void memo(int[] guess, String[] clue) {
        logger.trace("precedente proposition : " + Arrays.toString(guess) +
                "\nindice précédent : " + Arrays.toString(clue));
    }


    @Override
    public void memo(int[] thing) {
        logger.trace("Memo: " + Arrays.toString(thing));
    }


    @Override
    public void askGuess(String playerName) {
        logger.trace(playerName + " proposition :");
    }


    @Override
    public void askClue(String playerName) {
        logger.trace(playerName + " indice :");
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
        logger.trace("la combinaison était : \n" +
                "-" + playerName + ": " + Arrays.toString(lineToFind));
    }


    @Override
    public void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind) {
        logger.trace("les combinaisons étaient : \n" +
                "-" + firstPlayer + ": " + Arrays.toString(firstLineToFind) +
                "\n-" + secondPlayer + ": " + Arrays.toString(secondLineToFind));

    }


    @Override
    public void tryAgainMenu() {
        logger.trace("---------------------------" +
                "\nSi vous voulez recommencer appuyer sur \"R\"" +
                "\nSi vous voulez retourner au menu appuyer sur \"H\"" +
                "\nIf si vous voulez quitter \"Q\"");
    }


    @Override
    public void end() {
        logger.trace("Au revoir !");
    }


    @Override
    public void win(String playerName) {
        logger.trace(playerName + " a gagné !");
    }


    @Override
    public void Equally(String winOrLose) {
        logger.trace("tout le monde a " + winOrLose + " !");
    }
}
