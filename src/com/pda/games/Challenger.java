package com.pda.games;

import java.util.Arrays;
import java.util.Scanner;

import static com.pda.games.GetIn.getRandomArray;

public class Challenger extends MasterMind {
    private int[] attackNumber= new int[size];
    private String[] clue = new String[size];


    public Challenger() { super(maxRound(), defenseNumber()); }

    private static int[] defenseNumber() { return getRandomArray(size, minRange, maxRange); }

    @Override
    public boolean hasWon() { return correspondence && round <= maxRound; }

    @Override
    public boolean hasLost() { return !canPlayAgain() && !hasWon(); }

    private static int maxRound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        }catch (java.util.InputMismatchException e){
            sc.next();
        }return maxRound();
    }


    @Override
    public void round() {
        System.out.println("Round "+ (round + 1) +"/"+maxRound);
        System.out.println("choose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        attackNumber = GetIn.getHumanArray(size, minRange, maxRange);
        verifyEnter(attackNumber);
        clue();
        round++;
    }

    @Override
    void clue() {
        for (int i = 0; i < size; i++) {
            if (attackNumber[i] == defenseNumber[i]) {
                clue[i] = "=";
            }
            if (attackNumber[i] > defenseNumber[i]) {
                clue[i] = "-";
            }
            if (attackNumber[i] < defenseNumber[i]) {
                clue[i] = "+";
            }
        }
        System.out.println(Arrays.toString(clue));
    }
}
