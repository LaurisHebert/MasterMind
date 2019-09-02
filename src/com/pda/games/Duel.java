package com.pda.games;

public class Duel extends MasterMind{

    Duel(int maxTurn, int[] defenseNumber) {
        super(maxTurn, defenseNumber);
    }

    @Override
    public void round() {
    }

    @Override
    void clue() {

    }

    @Override
    public boolean hasWon() {
        return false;
    }

    @Override
    public boolean hasLost() {
        return false;
    }
}
