package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Config.MasterMind;

import java.util.Random;

public class BotComportment implements PlayerComportment {

    private int[] lowestRange = {0, 0, 0, 0};
    private int[] highestRange = {9, 9, 9, 9};
    private int[] previousGuess = new int[MasterMind.sizeOfLineToFind];

    public BotComportment() {
        String playerName = playerName();
    }

    @Override
    public String playerName() {
        int id = new Random().nextInt();
        String name = "Bot" + id;
        return name;
    }

    @Override
    public int[] lineToFind(int sizeOfTheArray, int minimalValue, int maximumValue) {
        int[] array = new int[sizeOfTheArray];
        for (int i = 0; i < sizeOfTheArray; i++) {
            array[i] = minimalValue + new Random().nextInt(maximumValue + 1 - minimalValue);
        }
        return array;
    }

    @Override
    public String[] clue(int sizeOfLineToFind, int[] lineToFind, int[] guess) {
        return PlayerComportment.verifyClue(sizeOfLineToFind, lineToFind, guess);
    }

    @Override
    public int[] guess(int sizeOfTheArray, int minimalValue, int maximumValue, int roundCount, String[] clue) {
        int[] guess = new int[sizeOfTheArray];
        if (roundCount == 1) {
            guess = lineToFind(sizeOfTheArray, minimalValue, maximumValue);
            previousGuess = guess;
        } else {
            for (int i = 0; i < sizeOfTheArray; i++) {
                previousGuess[i] = guess[i];
                switch (clue[i]) {
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
}
