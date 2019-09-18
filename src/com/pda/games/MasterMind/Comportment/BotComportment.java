package com.pda.games.MasterMind.Comportment;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends PlayerComportment {

    private int[] lowestRange = {0, 0, 0, 0};
    private int[] highestRange = {9, 9, 9, 9};
    private int[] previousGuess;

    @Override
    public String playerName() {
        int id = new Random().nextInt();
        return "BipBoop:" + id;
    }

    @Override
    public int[] lineToFind() {
        for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
            lineToFind[i] = minimalValue + new Random().nextInt(maximumValue + 1 - minimalValue);
        }
        return lineToFind;
    }

    @Override
    public int[] guess() {
        int[] guess = new int[sizeOfLineToFind];
        if (otherPlayerClue == null) {
            guess = lineToFind();
            previousGuess = guess;
        } else {
            for (int i = 0; i < sizeOfLineToFind; i++) {
                previousGuess[i] = guess[i];
                switch (otherPlayerClue[i]) {
                    case "+":
                        lowestRange[i] = guess[i];
                        guess[i] = ((highestRange[i] - lowestRange[i]) / 2) + lowestRange[i];
                        break;
                    case "-":
                        highestRange[i] = guess[i];
                        guess[i] = ((highestRange[i] - lowestRange[i]) / 2) + lowestRange[i];
                        break;
                    case "=":
                        guess[i] = previousGuess[i];
                        break;
                }
            }
        }
        return guess;
    }

    @Override
    public String[] clue(int[] guess, int[] lineToFind) {
        String[] clue = new String[sizeOfLineToFind];
        for (int i = 0; i < sizeOfLineToFind; i++) {
            if (lineToFind[i] == guess[i]) {
                clue[i] = "=";
            }
            if (lineToFind[i] < guess[i]) {
                clue[i] = "-";
            }
            if (lineToFind[i] > guess[i]) {
                clue[i] = "+";
            }
        }return clue;
    }

    @Override
    public boolean verifyClue(int[] guess, int[] lineToFind , String[] clue) {
        return Arrays.equals(clue(guess, lineToFind), clue);
    }
}
