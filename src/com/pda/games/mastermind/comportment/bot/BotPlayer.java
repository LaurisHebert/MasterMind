package com.pda.games.mastermind.comportment.bot;

import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.model.MasterMindConfig;
import com.pda.games.mastermind.model.Player;

import java.util.Arrays;
import java.util.Random;

public class BotPlayer extends Player {

    protected int[] lowestRange = buildLowestRange();
    protected int[] highestRange = buildHighestRange();

    public BotPlayer(Texts texts, MasterMindConfig config, String playerName) {
        super(texts, config, playerName);
    }

    /**
     * used for create bot name
     *
     * @return the bot name
     */
    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        id = id % 10000;
        return "Bot" + id;
    }

    /**
     * used for initialize lowestRange
     *
     * @return an array with the size and the minimal value selected in config file
     */
    private int[] buildLowestRange() {
        int[] lowestRange = new int[config.getSizeOfLineToFind()];
        Arrays.fill(lowestRange, config.getMinimalValue());
        return lowestRange;
    }

    /**
     * used for initialize highestRange
     *
     * @return an array with the size and the maximum value selected in configuration file
     */
    private int[] buildHighestRange() {
        int[] highestRange = new int[config.getSizeOfLineToFind()];
        Arrays.fill(highestRange, config.getMaximumValue() + 1);
        return highestRange;
    }

    /**
     * used to generate random numbers in range
     *
     * @return an array with length and randoms digits between two value selected in configuration file
     */
    protected int[] buildArrayInt() {
        int[] lineOfDigits = new int[config.getSizeOfLineToFind()];
        for (int i = 0; i < lineOfDigits.length; i++) {
            lineOfDigits[i] = config.getMinimalValue() + new Random().nextInt(config.getMaximumValue() + 1 - config.getMinimalValue());
        }
        return lineOfDigits;
    }

    @Override
    public void lineToFind() {
        texts.initializationMessage(getPlayerName());
        lineToFind = buildArrayInt();
    }

    @Override
    public void guess() {
        if (adversaryClue == null) {
            guess = buildArrayInt();
        } else {
            for (int i = 0; i < config.getSizeOfLineToFind(); i++) {
                switch (adversaryClue[i]) {
                    case "+":
                        lowestRange[i] = guess[i] + 1;
                        guess[i] = lowestRange[i] + new Random().nextInt(highestRange[i] - lowestRange[i]);
                        break;
                    case "-":
                        highestRange[i] = guess[i];
                        guess[i] = lowestRange[i] + new Random().nextInt(highestRange[i] - lowestRange[i]);
                        break;
                    case "=":
                        break;
                }
            }
        }
    }

    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[config.getSizeOfLineToFind()];
        for (int i = 0; i < config.getSizeOfLineToFind(); i++) {
            if (lineToFind[i] == guess[i]) {
                clue[i] = "=";
            } else if (lineToFind[i] < guess[i]) {
                clue[i] = "-";
            } else {
                clue[i] = "+";
            }
        }
        return clue;
    }

    @Override
    public boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue) {
        return !Arrays.equals(this.clue(lineToFind, guess), clue);
    }
}
