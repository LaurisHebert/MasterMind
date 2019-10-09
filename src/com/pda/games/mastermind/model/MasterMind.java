package com.pda.games.mastermind.model;

import com.pda.games.mastermind.enums.WhoWin;

import java.util.Arrays;

public abstract class MasterMind {

    protected final MasterMindConfig config;
    protected final Player playerOne;
    protected final Player playerTwo;
    private int roundCount = 1;
    private boolean corresponding = false;

    public MasterMind(Player playerOne, Player playerTwo, MasterMindConfig config) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.config = config;
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

    public abstract WhoWin winner();

    public boolean canPlay() {
        return !corresponding && roundCount <= config.getMaximumOfRounds();
    }

    protected void correspondence(int[] lineToFind, int[] guess) {
        if (canPlay()) {
            corresponding = Arrays.equals(lineToFind, guess);
            roundCount++;
        }
    }


}
