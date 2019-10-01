package com.pda.games.MasterMind.Structure;

public abstract class MasterMind {

    public static final int maximumOfRounds = 6;
    protected Player playerOne;
    protected Player playerTwo;
    private int roundCount = 1;

    protected MasterMind(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public int getRoundCount() {
        return roundCount;
    }

    protected void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

}