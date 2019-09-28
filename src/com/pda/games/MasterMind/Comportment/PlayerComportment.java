package com.pda.games.MasterMind.Comportment;

public abstract class PlayerComportment {

    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    public int[] guess = new int[sizeOfLineToFind];
    public int[] lineToFind = new int[sizeOfLineToFind];
    public String[] otherPlayerClue = null;

    public abstract void lineToFind();

    public abstract int[] guess();

    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean verifyClue(int[] guess, int[] lineToFind, String[] clue);
}
