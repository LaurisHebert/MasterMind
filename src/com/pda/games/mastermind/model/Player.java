package com.pda.games.mastermind.model;

public abstract class Player {

    protected final MasterMindConfig config;
    private final String playerName;
    protected int[] guess;
    protected int[] lineToFind;
    protected String[] adversaryClue;

    protected Player(MasterMindConfig config, String playerName) {
        this.config = config;
        this.playerName = playerName;
        this.guess = new int[config.getSizeOfLineToFind()];
        this.lineToFind = new int[config.getSizeOfLineToFind()];
    }

    public String getPlayerName() {
        return playerName;
    }

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

    /**
     * used to initialize "lineToFind"
     */
    public abstract void lineToFind();

    /**
     * used to initialize "guess"
     */
    public abstract void guess();

    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue);

}
