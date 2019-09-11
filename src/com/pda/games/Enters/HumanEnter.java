package com.pda.games.Enters;

import java.util.Scanner;

public class HumanEnter {

    private final Scanner sc;

    public HumanEnter(Scanner sc){
        this.sc = sc;
    }

    public int maxRound() {
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        }catch (java.util.InputMismatchException e){
            sc.next();
        }return maxRound();
    }
    private int[] array(int size, int minRange, int maxRange) {
        int[] humanNumbers = new int[size];

        String humanLine = sc.nextLine();
        String[] splitLine = humanLine.split(" ");
        if ( splitLine.length != size){
            System.out.println("You need to enter " + size + " digits.");
            return array(size, minRange, maxRange);
        }
        boolean hasError = false;
        for (int i = 0; i < size; i++) {
            try {
                humanNumbers[i] = Integer.parseInt(splitLine[i]);
            }
            catch (NumberFormatException e) {
                System.out.println((i + 1) + " enter [" + splitLine[i] + "] is not a digit");
                hasError = true;
            }
            if (!verify(humanNumbers[i], i, minRange, maxRange)) {
                System.out.println((i + 1) + " enter [" + humanNumbers[i] + "] is not between" + minRange + "/" + maxRange);
                hasError = true;
            }
        }
        if ( hasError){
            return array(size, minRange, maxRange);
        }
        return humanNumbers;
    }

    private static boolean verify(int humanNumber, int i,int minRange, int maxRange){
        return minRange >= humanNumber || humanNumber <= maxRange;
    }

    public int[] getArray(int size, int minRange, int maxRange) {
        return array(size, minRange, maxRange);
    }

    public String pseudo(){
        System.out.println("Enter your pseudo" +
                "\nPseudo:");
        return sc.next();
    }

    private String[] Clue(int size, int[] botAttack, int[] humanDefense){
        String clues = sc.nextLine();
        String[] clue = clues.split(" ");
        for (int i = 0; i < size; i++) {
            if (!verify(clue[i], i, botAttack, humanDefense)){
                System.out.println("A cake is a lie");
                Clue(size, botAttack, humanDefense);
            }
        }return clue;
    }

    private static boolean verify(String clue, int i, int[] botAttack, int[] humanDefense){
        switch (clue){
            case "+":
                return botAttack[i] < humanDefense[i];
            case "=":
                return botAttack[i] == humanDefense[i];
            case "-":
                return botAttack[i] > humanDefense[i];
            default:
                System.out.println("invalid clue : \"" + clue + "\"");
                return false;
        }
    }

    public String[] getClue(int size, int[] botAttack, int[] humanDefense){
        return Clue(size, botAttack, humanDefense);
    }

}
