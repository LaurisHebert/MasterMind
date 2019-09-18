package com.pda.games.MasterMind.Comportment;

public interface PlayerComportment {
    static String[] verifyClue(int sizeOfLineToFind, int[] lineToFind, int[] guess) {
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

    String playerName();

    int[] lineToFind(int sizeOfTheArray, int minimalValue, int maximumValue);

    int[] guess(int sizeOfTheArray, int minimalValue, int maximumValue, int roundCount, String[] clue);

    String[] clue(int sizeOfTheArray, int[] lineToFind, int[] guess);
}
