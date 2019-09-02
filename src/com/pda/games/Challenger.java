package com.pda.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Challenger extends MasterMind {
    static private Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);
    private int[] attackNumber= new int[size];
    private String[] clue = new String[size];


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
    }
}
