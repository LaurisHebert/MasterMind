package com.pda.games.mastermind.game.config;

import com.pda.games.mastermind.game.MasterMind;

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
    private final int[] botDifficulties;
    private final boolean devMod;
    private final boolean antiCheat;
    private final String playerOneName;
    private final String playerTwoName;
    private final String language;

    public MasterMindConfig() {
        Properties properties = new Properties();
        try {
            properties.load(Objects.requireNonNull(MasterMind.class.getClassLoader().getResourceAsStream("config.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sizeOfLineToFind = defineValue(properties, "sizeOfLineToFind", 4);
        maximumValue = defineValue(properties, "maximumValue", 9);
        minimalValue = defineValue(properties, "minimalValue", 0);
        maximumOfRounds = defineValue(properties, "maximumOfRounds", 6);
        playerOneName = properties.getProperty("playerOneName");
        playerTwoName = properties.getProperty("playerTwoName");
        devMod = properties.containsKey("devMod") && Boolean.parseBoolean(properties.getProperty("devMod"));
        antiCheat = properties.containsKey("antiCheat") && Boolean.parseBoolean(properties.getProperty("antiCheat"));
        if (properties.containsKey("botDifficulties")) {
            String numberOfBot = properties.getProperty("botDifficulties");
            if (numberOfBot.isEmpty()) {
                botDifficulties = new int[]{};
            } else {
                String[] botsDifficulties = numberOfBot.split(",");
                botDifficulties = new int[botsDifficulties.length];
                for (int i = 0; i < botsDifficulties.length; i++) {
                    botDifficulties[i] = Integer.parseInt(botsDifficulties[i]);
                }
            }
        } else {
            botDifficulties = new int[]{1};
        }
        if (properties.containsKey("language")) {
            language = properties.getProperty("language");
        } else {
            language = "EN";
        }
    }

    public String getLanguage() {
        return language;
    }

    public boolean isAntiCheat() {
        return antiCheat;
    }

    public int[] getBotDifficulties() {
        return botDifficulties;
    }

    private int defineValue(Properties properties, String key, int defaultValue) {
        if (properties.containsKey(key)) {
            return Integer.parseInt(properties.getProperty(key));
        } else {
            return defaultValue;
        }
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

    public int getNumberOfBot() {
        return botDifficulties.length;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }
}
