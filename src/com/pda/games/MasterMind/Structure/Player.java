package com.pda.games.MasterMind.Structure;

public abstract class Player {

    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    public int[] guess = new int[sizeOfLineToFind];
    public int[] lineOfDigits = new int[sizeOfLineToFind];
    public String[] otherPlayerClue = null;

    public abstract int[] lineToFind();

    public abstract int[] guess();

    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue);
}
