package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Structure.Player;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends Player {

    private int[] lowestRange = {0, 0, 0, 0};
    private int[] highestRange = {10, 10, 10, 10};
    private int[] previousGuess = guess;

    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        String name = "BipBoop" + id%10000;
        System.out.println(name);
        return name;
    }

    @Override
    public int[] lineToFind() {
        for (int i = 0; i < Player.sizeOfLineToFind; i++) {
            lineOfDigits[i] = Player.minimalValue + new Random().nextInt(Player.maximumValue + 1 - Player.minimalValue);
        }return lineOfDigits;
    }

    @Override
    public int[] guess() {
        if (otherPlayerClue == null) {
            for (int i = 0; i < Player.sizeOfLineToFind; i++) {
                guess[i] = Player.minimalValue + new Random().nextInt(Player.maximumValue + 1 - Player.minimalValue);
            }
        } else {
            for (int i = 0; i < Player.sizeOfLineToFind; i++) {
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
        }
        return clue;
    }

    @Override
    public boolean notVerifyClue(int[] lineToFind, int[] guess, String[] clue) {
        boolean verification = Arrays.equals(this.clue(lineToFind, guess), clue);
        if (!verification)
            System.out.println("Noop");
        return !verification;
    }
}
