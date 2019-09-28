package com.pda.games.MasterMind.Comportment;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends PlayerComportment {

    private int[] lowestRange = {0, 0, 0, 0};
    private int[] highestRange = {9, 9, 9, 9};
    private int[] previousGuess = guess;

    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        String name = "BipBoop" + id;
        System.out.println(name);
        return name;
    }

    @Override
    public void lineToFind() {
        for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
            lineToFind[i] = PlayerComportment.minimalValue + new Random().nextInt(PlayerComportment.maximumValue + 1 - PlayerComportment.minimalValue);
        }
        System.out.println("Secret number initialized");
    }

    @Override
    public int[] guess() {
        if (otherPlayerClue == null) {
            for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
                guess[i] = PlayerComportment.minimalValue + new Random().nextInt(PlayerComportment.maximumValue + 1 - PlayerComportment.minimalValue);
            }        } else {
            for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
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
    public String[] clue(int[] lineToFind, int[] guess) {
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
    public boolean verifyClue(int[] lineToFind, int[] guess, String[] clue) {
        boolean verification = Arrays.equals(this.clue(lineToFind, guess), clue);
        if (!verification)
            System.out.println("Noop");
        return !verification;
    }
}
