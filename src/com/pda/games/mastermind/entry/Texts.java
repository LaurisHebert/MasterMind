package com.pda.games.mastermind.entry;


public interface Texts {

    void gameIntroduce();

    void pseudoEntry(int i);

    void explainGameMod();

    void initializationMessage(String playerName);

    void rules(int size);

    void launchPhrase();

    void actualRound(int roundCount, int maxRounds);

    void memo(int[] guess, String[] clue);

    void memo(int[] thing);

    void askGuess(String playerName);

    void askClue(String playerName);

    void printArray(String[] thing);

    void printArray(int[] thing);

    void lineToFind(String playerName, int[] lineToFind);

    void lineToFind(String firstPlayer, int[] firstLineToFind, String secondPlayer, int[] secondLineToFind);

    void tryAgainMenu();

    void end();

    void win(String playerName);

    void Equally(String winOrLose);
}
