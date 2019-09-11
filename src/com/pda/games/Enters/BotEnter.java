package com.pda.games.Enters;

import java.util.Random;

public class BotEnter {

    private static int[] array(int size, int minRange, int maxRange) {
        int[] genNumber = new int[size];
        for (int i = 0; i < size; i++) {
            genNumber[i] = minRange + new Random().nextInt(maxRange + 1 - minRange);
        }
        return genNumber;
    }

    public static int[] getArray(int size, int minRange, int maxRange) {
        return array(size, minRange, maxRange);
    }

    private static String[] clue(int size, int[] humanAttack, int[] botDefense) {
        String[] clue = new String[size];
        for (int i = 0; i < size; i++) {
            if (humanAttack[i] == botDefense[i]) {
                clue[i] = "=";
            }
            if (humanAttack[i] > botDefense[i]) {
                clue[i] = "-";
            }
            if (humanAttack[i] < botDefense[i]) {
                clue[i] = "+";
            }
        }return clue;
    }

    public static String[] getClue(int size, int[] humanAttack, int[] botDefense){
        return clue( size, humanAttack, botDefense);
    }

}
