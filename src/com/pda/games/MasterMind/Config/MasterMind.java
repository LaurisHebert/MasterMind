package com.pda.games.MasterMind.Config;

import java.util.Arrays;

public abstract class MasterMind implements Game {
    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    public static final int maximumOfRounds = 6;
    protected final int[] lineToFind;
    protected boolean correspondence = false;

    protected MasterMind(String playerOneName, String playerTwoName, int[] lineToFind) {
        this.lineToFind = lineToFind;
    }

    protected int getRoundCount() {
        // roundCont static ?
        int roundCount = 0;
        return roundCount + 1;
    }

    protected boolean checkCorresponding(int[] guess) {
        return correspondence = Arrays.equals(lineToFind, guess);
    }

}
