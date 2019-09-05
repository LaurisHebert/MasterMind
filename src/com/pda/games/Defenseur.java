package com.pda.games;

import java.util.Arrays;
import java.util.Scanner;

public class Defenseur extends MasterMind {

    private static Scanner staticScan = new Scanner(System.in);
    private Scanner sc = new Scanner(System.in);


    private int[] minimum = {-1, -1, -1, -1};
    private int[] maximum = {10, 10, 10, 10};

    private int[] attackNumber = new int[size];
    private int[] previousNumber= new int[size];
    private String[] clue = new String[size];

    public Defenseur() { super(maxRound(), defenseNumber()); }

    @Override
    public boolean hasWon() { return !canPlayAgain() && !hasLost(); }

    @Override
    public boolean hasLost() { return correspondence && round <= maxRound; }

    private static int maxRound() {
        System.out.println("Choose the maximum number of rounds");
        try {
            return staticScan.nextInt();
        }catch (java.util.InputMismatchException e){
            staticScan.next();
        }return maxRound();
    }

    private static int[] defenseNumber() {
        System.out.println("choose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        return GetIn.getHumanArray(size, minRange, maxRange);

    }

    @Override
    public void round() {
        System.out.println("Round "+ (round + 1) +"/"+maxRound);
        for (int i = 0; i < size ; i++) {
            if(round == 0){
                attackNumber = GetIn.getRandomArray(size, minRange, maxRange);
            }
            else{
                previousNumber[i]=attackNumber[i];
                switch (clue[i]) {
                    case "+":
                        minimum[i] = attackNumber[i];
                        attackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = attackNumber[i];
                        //attackNumber[i] = minimum[i] + new Random().nextInt(maximum[i] - minimum[i]);
                        attackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "=":
                        attackNumber[i] = previousNumber[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(attackNumber));
        verifyEnter(attackNumber);
        System.out.println("Say more, less, or equal with these symbols (+ - =)");
        clue();
        round++;
    }

    @Override
    void clue() {
        String clues = sc.nextLine();
        clue = clues.split(" ");
        try{
            for (int i = 0; i < size; i++) {
                if (!verify(clue[i], i)){
                    System.out.println("A cake is a lie");
                    clue();
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Need more input");
            clue();
        }
    }

    private boolean verify(String clue, int i){
        switch (clue){
            case "+":
                return attackNumber[i] < defenseNumber[i];
            case "=":
                return attackNumber[i] == defenseNumber[i];
            case "-":
                return attackNumber[i] > defenseNumber[i];
            default:
                System.out.println("invalid clue : \"" + clue + "\"");
                return false;
        }
    }
}