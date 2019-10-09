package com.pda.games.mastermind.model;

import com.pda.games.mastermind.enums.WhoWin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public abstract class MasterMind {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(MasterMind.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final int sizeOfLineToFind = Integer.parseInt(properties.getProperty("sizeOfLineToFind"));
    public static final int maximumValue = Integer.parseInt(properties.getProperty("maximumValue"));
    public static final int minimalValue = Integer.parseInt(properties.getProperty("minimalValue"));
    private static final int maximumOfRounds = Integer.parseInt(properties.getProperty("maximumOfRounds"));
    public static String difficulty = properties.getProperty("difficulty");
    // difficulty est là car j'hésite entre deux façon de trouver un "guess" pour le bot
    public static boolean devMod = properties.containsKey("devMod") && Boolean.parseBoolean(properties.getProperty("devMod"));
    // && Boolean.parseBoolean(properties.getProperty("admin")); explication avant application
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

    public abstract WhoWin winner();

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
