package com.pda.games.MasterMind.Model;

import com.pda.games.MasterMind.Enums.WhoWin;

import java.util.Arrays;

public abstract class MasterMind {
    private static final int maximumOfRounds = 6;
    protected final Player playerOne;
    protected final Player playerTwo;
    private final String playerOneName;
    private final String playerTwoName;
    private int roundCount = 1;
    private boolean corresponding = false;

    public MasterMind(Player playerOne, Player playerTwo, String playerOneName, String playerTwoName) {
        this.playerOne = playerOne;
        this.playerOneName = playerOneName;
        this.playerTwo = playerTwo;
        this.playerTwoName = playerTwoName;

    }

    public static int getMaximumOfRounds() {
        return maximumOfRounds;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
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
