package com.pda.games.MasterMind.Model;

public abstract class Player {

    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    protected int[] getGuess = new int[sizeOfLineToFind];
    protected int[] lineToFind = new int[sizeOfLineToFind];
    protected String[] adversaryClue = null;


    public abstract void lineToFind();

    public int[] getLineToFind() {
        return lineToFind;
    }

    public String[] getAdversaryClue() {
        return adversaryClue;
    }

    public void setAdversaryClue(String[] adversaryClue) {
        this.adversaryClue = adversaryClue;
    }

    public int[] getGuess() {
        return getGuess;
    }

    public abstract void guess();

    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue);

}
