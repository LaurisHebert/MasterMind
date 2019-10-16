package com.pda.games.mastermind.model;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * read values in "resources/config.properties"
 */
public class MasterMindConfig {
    private final int sizeOfLineToFind;
    private final int maximumValue;
    private final int minimalValue;
    private final int maximumOfRounds;
    private final boolean devMod;
    private final String playerOneName;
    private final String playerTwoName;


    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public MasterMindConfig() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(MasterMind.class.getClassLoader().getResourceAsStream("config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sizeOfLineToFind = Integer.parseInt(properties.getProperty("sizeOfLineToFind"));
        maximumValue = Integer.parseInt(properties.getProperty("maximumValue"));
        minimalValue = Integer.parseInt(properties.getProperty("minimalValue"));
        maximumOfRounds = Integer.parseInt(properties.getProperty("maximumOfRounds"));
        devMod = properties.containsKey("devMod") && Boolean.parseBoolean(properties.getProperty("devMod"));
        playerOneName = properties.getProperty("playerOneName");
        playerTwoName = properties.getProperty("playerTwoName");
    }

    public int getSizeOfLineToFind() {
        return sizeOfLineToFind;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public int getMinimalValue() {
        return minimalValue;
    }

    public int getMaximumOfRounds() {
        return maximumOfRounds;
    }

    public boolean isDevMod() {
        return devMod;
    }
}
