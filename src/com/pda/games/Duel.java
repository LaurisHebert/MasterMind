package com.pda.games;

import java.util.Arrays;
import java.util.Scanner;

import static com.pda.games.GetIn.getRandomArray;

public class Duel extends MasterMind{
    private Scanner sc = new Scanner(System.in);

    private final int[] humanDefenseNumber = defenseNumber2;
    private int[] humanAttackNumber = new int[size];
    private String[] humanClue = new String[size];

    private final int[] botDefenseNumber = defenseNumber;
    private int[] botAttackNumber = new int[size];
    private String[] botClue = new String[size];
    private int [] previousBotAttackNumber = new int[size];

    private int[] minimum = {-1, -1, -1, -1};
    private int[] maximum = {10, 10, 10, 10};

    public Duel() { super(maxRound(), botDefenseNumber(), humanDefenseNumber()); }


    private static int[] humanDefenseNumber(){
        System.out.println("choose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        return GetIn.getHumanArray(size, minRange, maxRange);
    }

    private static int[] botDefenseNumber() { return getRandomArray(size, minRange, maxRange); }

    private static int maxRound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        }catch (java.util.InputMismatchException e){
            sc.next();
        }return maxRound();
    }

    private void humanTurn(){
        System.out.println("Round " + (round + 1) + "/" + maxRound +
                "\nHuman Turn" +
                "\nChoose four digits between " + minRange + " and " + maxRange + " (included) separated by a space");
        humanAttackNumber = GetIn.getHumanArray(size, minRange, maxRange);
        verifyEnter(humanAttackNumber);
        clue();
    }

    private void botTurn(){
        System.out.println("Bot turn");
        for (int i = 0; i < size ; i++) {
            if(round == 0){
                botAttackNumber = GetIn.getRandomArray(size, minRange, maxRange);
            }
            else{
                previousBotAttackNumber[i]=botAttackNumber[i];
                switch (humanClue[i]) {
                    case "+":
                        minimum[i] = botAttackNumber[i];
                        botAttackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "-":
                        maximum[i] = botAttackNumber[i];
                        botAttackNumber[i] =((maximum[i] - minimum[i])/2)+minimum[i];
                        break;
                    case "=":
                        botAttackNumber[i] = previousBotAttackNumber[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(botAttackNumber));
        verifyEnter2(botAttackNumber);
        System.out.println("Say more, less, or equal with these symbols (+ - =)");
        humanClue();
    }


    @Override
    public boolean hasWon() { return correspondence && round <= maxRound; }

    @Override
    public boolean hasLost() { return correspondence2 && round <= maxRound; }

    @Override
    public void round() {
        botDefenseNumber();
        System.out.println(Arrays.toString(botDefenseNumber));
        humanTurn();
        botTurn();
        round++;
    }

    @Override
    void clue() {
        for (int i = 0; i < size; i++) {
            if (humanAttackNumber[i] == botDefenseNumber[i]) {
                botClue[i] = "=";
            }
            if (humanAttackNumber[i] > botDefenseNumber[i]) {
                botClue[i] = "-";
            }
            if (humanAttackNumber[i] < botDefenseNumber[i]) {
                botClue[i] = "+";
            }
        }
        System.out.println(Arrays.toString(botClue));
    }
    private void humanClue(){
        String clues = sc.nextLine();
        humanClue = clues.split(" ");
        for (int i = 0; i < size; i++) {
            if (!verify(humanClue[i], i)){
                System.out.println("A cake is a lie");
                humanClue();
            }
        }
    }

    private boolean verify(String clue, int i){
        switch (clue){
            case "+":
                return botAttackNumber[i] < humanDefenseNumber[i];
            case "=":
                return botAttackNumber[i] == humanDefenseNumber[i];
            case "-":
                return botAttackNumber[i] > humanDefenseNumber[i];
            default:
                System.out.println("invalid clue : \"" + clue + "\"");
                return false;
        }
    }

}
