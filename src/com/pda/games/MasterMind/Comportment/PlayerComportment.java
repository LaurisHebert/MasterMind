package com.pda.games.MasterMind.Comportment;

public abstract class PlayerComportment {

    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    public int[] lineToFind = new int[sizeOfLineToFind];
    public String[] otherPlayerClue = null;


    public abstract String playerName();

    public abstract int[] lineToFind();

    public abstract int[] guess();

    public abstract String[] clue(int[] guess, int[] lineToFind);

    public abstract boolean verifyClue(int[] guess, int[] lineToFind , String[] clue);
}
