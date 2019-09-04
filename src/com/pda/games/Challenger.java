package com.pda.games;

import java.util.Arrays;
<<<<<<< HEAD
import java.util.Scanner;

import static com.pda.games.GetIn.getRandomArray;

public class Challenger extends MasterMind {
=======
import java.util.Random;
import java.util.Scanner;

public class Challenger extends MasterMind {
    static private Scanner staticScan = new Scanner(System.in);
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
    private Scanner sc = new Scanner(System.in);
    private int[] attackNumber= new int[size];
    private String[] clue = new String[size];


<<<<<<< HEAD
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
        System.out.println(Arrays.toString(defenseNumber));
        System.out.println("Round"+ (round + 1) +"/"+maxRound);
        System.out.println("choose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        attackNumber = GetIn.getHumanArray(size, minRange, maxRange);
        verifyEnter(attackNumber);
=======
    public Challenger() {
        super(maxTurn(), defenseNumber());
    }

    private static int maxTurn() {
        System.out.println("Choose the maximum number of rounds");
        try {
            return staticScan.nextInt();
        }catch (java.util.InputMismatchException e){
            staticScan.next();
        }return maxTurn();
    }

    static private int[] defenseNumber() {
        int[] defenseNumber = new int[size];
        for (int i = 0; i < size ; i++) {
            defenseNumber[i] = new Random().nextInt(10);
        }return defenseNumber;
    }

    @Override
    public void turn() {
        turn++;
        System.out.println("Round"+turn+"/"+maxTurn);
        System.out.println("choose four digits between 0 and 9 (included) separated from a space");
        for (int i = 0; i < size; i++) {
            attackNumber[i] = sc.nextInt(9);

            /* chercher à catcher la création d'attackNumber

            try {
                attackNumber[i] = sc.nextInt(9);
            }catch (java.util.InputMismatchException e){
            ??
            }
             */

        }verifyEnter(attackNumber);
        if (!correspondence) {
            clue();
            System.out.println(Arrays.toString(clue));
        }
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
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
<<<<<<< HEAD
        System.out.println(Arrays.toString(clue));
=======
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
    }
}
