package com.pda.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Defenseur extends MasterMind {
    static private Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    private int[] attackNumber = new int[size];
    private int[] previousNumber= new int[size];
    private String[] clue = new String[size];

    public Defenseur() {
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

    private static int[] defenseNumber() {
        int[] defenseNumber = new int[size];
        System.out.println("Enter your 4 numbers separate with space");
        for (int i = 0; i < defenseNumber.length; i++) {
            defenseNumber[i] = staticScan.nextInt(9);

            /* chercher à catcher la création de defenseNumber

            try {
                defenseNumber[i] = sc.nextInt(9);
            }catch (java.util.InputMismatchException e){
            ??
            }
             */

        }
        return defenseNumber;
    }

    @Override
    public void turn() {
        turn++;
        System.out.println("Round"+turn+"/"+maxTurn);
        for (int i = 0; i < size ; i++) {
            if(turn == 1){
                previousNumber[i] = attackNumber[i]= new Random().nextInt(10);
            }
            else{
                previousNumber[i]=attackNumber[i];
                switch (clue[i]) {
                    case "+":
                        minimum[i] = attackNumber[i];
                        attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        break;
                    case "-":
                        maximum[i] = attackNumber[i];
                        attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        break;
                    case "=":
                        attackNumber[i] = previousNumber[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(attackNumber));
        verifyEnter(attackNumber);
        clue();
    }

    @Override
    void clue() {
        System.out.println(" say more, less, or equal with these symbols (+ - =)");
        for (int i = 0; i < size; i++) {
            clue[i] = sc.next();
            while (!clue[i].equals("+") && !clue[i].equals("-") && !clue[i].equals("=")) {
                System.out.println("enter only the requested symbols");
                clue[i] = sc.next();
            }
        }
    }
}
