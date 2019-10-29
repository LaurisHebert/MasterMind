package com.pda.games.mastermind.model;

import com.pda.games.mastermind.entry.Texts;

public abstract class Player {

    protected final MasterMindConfig config;
    private final String playerName;
    protected Texts texts;
    protected int[] guess; // line to guess the other player "lineToFind"
    protected int[] lineToFind; // line that the other player has to find
    protected String[] adversaryClue; //

    protected Player(Texts texts, MasterMindConfig config, String playerName) {
        this.texts = texts;
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
     * update {@link Player#getLineToFind()}
     */
    public abstract void lineToFind();

    /**
     * Update the {@link Player#getGuess()}
     */
    public abstract void guess();

    /**
     * @param lineToFind used only bye the bot
     * @param guess
     * @return
     */
    public abstract String[] clue(int[] lineToFind, int[] guess);

    public abstract boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue);

}
