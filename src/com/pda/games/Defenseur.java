package com.pda.games;

import java.util.Arrays;
<<<<<<< HEAD
import java.util.Scanner;

public class Defenseur extends MasterMind {

    private static Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);


    private int[] minimum = {-1, -1, -1, -1};
    private int[] maximum = {10, 10, 10, 10};
=======
import java.util.Random;
import java.util.Scanner;

public class Defenseur extends MasterMind {
    static private Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa

    private int[] attackNumber = new int[size];
    private int[] previousNumber= new int[size];
    private String[] clue = new String[size];

<<<<<<< HEAD
    public Defenseur() { super(maxRound(), defenseNumber()); }

    @Override
    public boolean hasWon() { return !canPlayAgain() && !hasLost(); }

    @Override
    public boolean hasLost() { return correspondence && round <= maxRound; }

    private static int maxRound() {
=======
    public Defenseur() {
        super(maxTurn(), defenseNumber());
    }

    private static int maxTurn() {
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
        System.out.println("Choose the maximum number of rounds");
        try {
            return staticScan.nextInt();
        }catch (java.util.InputMismatchException e){
            staticScan.next();
<<<<<<< HEAD
        }return maxRound();
    }

    private static int[] defenseNumber() {
        System.out.println("choose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        return GetIn.getHumanArray(size, minRange, maxRange);

    }

    @Override
    public void round() {
        System.out.println("Round"+ (round + 1) +"/"+maxRound);
        for (int i = 0; i < size ; i++) {
            if(round == 0){
                attackNumber = GetIn.getRandomArray(size, minRange, maxRange);
=======
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
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
            }
            else{
                previousNumber[i]=attackNumber[i];
                switch (clue[i]) {
                    case "+":
                        minimum[i] = attackNumber[i];
<<<<<<< HEAD
                        //attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        attackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = attackNumber[i];
                        //attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        attackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
=======
                        attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        break;
                    case "-":
                        maximum[i] = attackNumber[i];
                        attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
                        break;
                    case "=":
                        attackNumber[i] = previousNumber[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(attackNumber));
        verifyEnter(attackNumber);
<<<<<<< HEAD
        System.out.println("Say more, less, or equal with these symbols (+ - =)");
=======
        clue();
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
    }

    @Override
    void clue() {
<<<<<<< HEAD
        boolean[] test = new boolean[size];
        for (int i = 0; i < size; i++) {
            clue[i] = sc.next();
            while (!test[i] && (!clue[i].equals("+") && !clue[i].equals("-") && !clue[i].equals("="))) {
                clue[i] = sc.next();
                if (clue[i].equals("=") && attackNumber[i] == defenseNumber[i]) {
                    test[i]= true;
                }
                if (clue[i].equals("+") && attackNumber[i] < defenseNumber[i]) {
                    test[i]= true;
                }
                if (clue[i].equals("-") && attackNumber[i] > defenseNumber[i]) {
                    test[i]= true;
                }
                else {
                System.out.println("your clues " + i + " are false");
                test[i]= false;
                }
=======
        System.out.println(" say more, less, or equal with these symbols (+ - =)");
        for (int i = 0; i < size; i++) {
            clue[i] = sc.next();
            while (!clue[i].equals("+") && !clue[i].equals("-") && !clue[i].equals("=")) {
                System.out.println("enter only the requested symbols");
                clue[i] = sc.next();
>>>>>>> a4fe4e50d77c137be44fe3f725656c9929ec6faa
            }
        }
    }
}
