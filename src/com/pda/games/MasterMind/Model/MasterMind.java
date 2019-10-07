package com.pda.games.MasterMind.Model;

import com.pda.games.MasterMind.Enums.WhoWin;

import java.util.Arrays;

public abstract class MasterMind {

    public static final int sizeOfLineToFind = 4;
    public static final int maximumValue = 9;
    public static final int minimalValue = 0;
    private static final int maximumOfRounds = 6;
    protected final Player playerOne;
    protected final Player playerTwo;
    private int roundCount = 1;
    private boolean corresponding = false;

    public MasterMind(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public static int getMaximumOfRounds() {
        return maximumOfRounds;
    }

    public String getPlayerOneName() {
        return playerOne.getPlayerName();
    }

    public String getPlayerTwoName() {
        return playerTwo.getPlayerName();
    }

    public int getRoundCount() {
        return roundCount;
    }

    public boolean isCorresponding() {
        return corresponding;
    }

    public abstract void initialization();

    public abstract void round();

    public abstract WhoWin whoWin();

    public boolean canPlay() {
        return !corresponding && roundCount <= maximumOfRounds;
    }

    protected void correspondence(int[] lineToFind, int[] guess) {
        if (canPlay()) {
            corresponding = Arrays.equals(lineToFind, guess);
            roundCount++;
        }
    }


}
