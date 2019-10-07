package com.pda.games.MasterMind.Model;

public abstract class Player {

    private final String playerName;
    protected int[] guess = new int[MasterMind.sizeOfLineToFind];
    protected int[] lineToFind = new int[MasterMind.sizeOfLineToFind];
    protected String[] adversaryClue = new String[MasterMind.sizeOfLineToFind];

    protected Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public abstract void lineToFind();

    public int[] getLineToFind() {
        return lineToFind;
    }

    public String[] getAdversaryClue() {
        return adversaryClue;
    }

    public void setAdversaryClue(String[] adversaryClue) {
        this.adversaryClue = adversaryClue;
    }

    public int[] getGuess() {
        return guess;
    }

    public abstract void guess();

    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue);

}
