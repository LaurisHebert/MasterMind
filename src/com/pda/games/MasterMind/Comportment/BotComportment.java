package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Structure.Player;

import java.util.Arrays;
import java.util.Random;

public class BotComportment extends Player {

    private int[] lowestRange = {0, 0, 0, 0};
    private int[] highestRange = {10, 10, 10, 10};

    public static String playerName() {
        int id = new Random().nextInt();
        if (id < 0)
            id = id * -1;
        id = id % 10000;
        Sout.bot(id);
        return "Bot" + id;
    }

    @Override
    public int[] lineToFind() {
        int[] lineOfDigits = new int[sizeOfLineToFind];
        for (int i = 0; i < Player.sizeOfLineToFind; i++) {
            lineOfDigits[i] = Player.minimalValue + new Random().nextInt(Player.maximumValue + 1 - Player.minimalValue);
        }
        return lineOfDigits;
    }

    @Override
    public int[] guess() {
        if (adversaryClue == null) {
            guess = lineToFind();
        } else {
            for (int i = 0; i < Player.sizeOfLineToFind; i++) {
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
        return guess;
    }

    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[Player.sizeOfLineToFind];
        for (int i = 0; i < sizeOfLineToFind; i++) {
            if (lineToFind[i] == guess[i]) {
                clue[i] = "=";
            }
            else if (lineToFind[i] < guess[i]) {
                clue[i] = "-";
            }
            else{
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
