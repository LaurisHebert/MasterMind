package com.pda.games.mastermind.entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Texts {

    protected static final Logger logger = LogManager.getLogger();

    public abstract void gameIntroduce();

    public abstract void pseudoEntry(int i);

    public void botName(int i) {
        logger.trace("Bot" + i);
    }

    public abstract void explainGameMod();

    public abstract void initializationMessage(String playerName);

    public abstract void rules(int size);

    public abstract void launchPhrase();

    public abstract void actualRound(int roundCount, int maxRounds);

    public abstract void memo(int[] guess, String[] clue);

    public abstract void memo(int[] thing);

    public abstract void askGuess(String playerName);

    public abstract void askClue(String playerName);

    public abstract void printArray(String[] thing);

    public abstract void printArray(int[] thing);

    public abstract void lineToFind(String playerName, int[] lineToFind);

    public abstract void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind);

    public abstract void tryAgainMenu();

    public abstract void end();

    public abstract void win(String playerName);

    public abstract void Equally(String winOrLose);
}
