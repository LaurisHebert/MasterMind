package com.pda.games.mastermind.comportment;

import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.model.MasterMind;
import com.pda.games.mastermind.model.Player;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends Player {

    private int[] lowestRange = setLowestRange();
    private int[] highestRange = setHighestRange();

    public BotComportment(String playerName) {
        super(playerName);
    }

    /**
     * used for create bot name
     * @return the bot name
     */
    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        id = id % 10000;
        Texts.bot(id);
        return "Bot" + id;
    }

    /**
     * used for initialize lowestRange
     * @return an array with the size and the minimal value selected in config file
     */
    private int[] setLowestRange() {
        int[] lowestRange = new int[MasterMind.sizeOfLineToFind];
        Arrays.fill(lowestRange, MasterMind.minimalValue);
        return lowestRange;
    }

    /**
     * used for initialize highestRange
     * @return an array with the size and the maximum value selected in configuration file
     */
    private int[] setHighestRange() {
        int[] bla = new int[MasterMind.sizeOfLineToFind];
        Arrays.fill(bla, MasterMind.maximumValue + 1);
        return bla;
    }

    /**
     * used to generate random numbers in range
     * @return an array with length and randoms digits between two value selected in configuration file
     */
    private int[] readArrayInt() {
        int[] lineOfDigits = new int[MasterMind.sizeOfLineToFind];
        for (int i = 0; i < lineOfDigits.length; i++) {
            lineOfDigits[i] = MasterMind.minimalValue + new Random().nextInt(MasterMind.maximumValue + 1 - MasterMind.minimalValue);
        }
        return lineOfDigits;
    }

    @Override
    public void lineToFind() {
        Texts.initializationMessage(getPlayerName());
        lineToFind = readArrayInt();
    }

    @Override
    public void guess() {
        switch (MasterMind.difficulty) {
            case "easy":
                if (adversaryClue == null) {
                    guess = readArrayInt();
                } else {
                    for (int i = 0; i < MasterMind.sizeOfLineToFind; i++) {
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
                break;
            case "medium":
                if (adversaryClue == null) {
                    guess = readArrayInt();
                } else {
                    for (int i = 0; i < MasterMind.sizeOfLineToFind; i++) {
                        switch (adversaryClue[i]) {
                            case "+":
                                lowestRange[i] = guess[i];
                                guess[i] = ((highestRange[i] - lowestRange[i]) / 2) + lowestRange[i];
                                break;
                            case "-":
                                highestRange[i] = guess[i];
                                guess[i] = ((highestRange[i] - lowestRange[i]) / 2) + lowestRange[i];
                                break;
                            case "=":
                                break;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[MasterMind.sizeOfLineToFind];
        for (int i = 0; i < MasterMind.sizeOfLineToFind; i++) {
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
