package com.pda.games.MasterMind.Config;

import com.pda.games.MasterMind.Comportment.PlayerComportment;

public abstract class MasterMind {

    public static final int maximumOfRounds = 6;

    private int roundCount = 1;
    protected PlayerComportment playerOne;
    protected PlayerComportment playerTwo;

    protected MasterMind(PlayerComportment playerTwo,PlayerComportment playerOne){
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    protected int getRoundCount() {
        return roundCount;
    }
    protected void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

}
