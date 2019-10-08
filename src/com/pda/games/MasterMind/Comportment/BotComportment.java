package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Entry.Texts;
import com.pda.games.MasterMind.Model.MasterMind;
import com.pda.games.MasterMind.Model.Player;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends Player {

    private int[] lowestRange = setLowestRange();
    private int[] highestRange = setHighestRange();

    public BotComportment(String playerName) {
        super(playerName);
    }

    private int[] setLowestRange() {
        int[] bla = new int[MasterMind.sizeOfLineToFind];
        Arrays.fill(bla, MasterMind.minimalValue);
        return bla;
    }

    private int[] setHighestRange() {
        int[] bla = new int[MasterMind.sizeOfLineToFind];
        Arrays.fill(bla, MasterMind.maximumValue +1);
        return bla;
    }

    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        id = id % 10000;
        Texts.bot(id);
        return "Bot" + id;
    }

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
