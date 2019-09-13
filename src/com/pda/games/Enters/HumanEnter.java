package com.pda.games.Enters;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanEnter {

    private final Scanner sc;

    public HumanEnter(Scanner sc) {
        this.sc = sc;
    }

    private static boolean rangeCheck(int number, int minRange, int maxRange) {
        return minRange >= number || number <= maxRange;
    }

    private static boolean clueCheck(String clue, int i, int[] botAttack, int[] humanDefense) {
        switch (clue) {
            case "+":
                return botAttack[i] < humanDefense[i];
            case "=":
                return botAttack[i] == humanDefense[i];
            case "-":
                return botAttack[i] > humanDefense[i];
            default:
                System.out.println("your " + (i + 1) + " clue \"" + clue + "\" is invalid");
                return false;
        }
    }

    public String pseudo() {
        System.out.println("Enter your pseudo" +
                "\nPseudo:");
        return sc.next();
    }

    public int maximumRound() {
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            sc.next();
        }
        return maximumRound();
    }

    private int[] array(int size, int minRange, int maxRange) {
        String humanLine = sc.nextLine();
        String[] splitLine = humanLine.split(" ");
        if (splitLine.length != size) {
            System.out.println("You need to enter " + size + " digits.");
            return array(size, minRange, maxRange);
        }
        int[] humanNumbers = new int[size];
        boolean hasError = false;
        for (int i = 0; i < size; i++) {
            try {
                humanNumbers[i] = Integer.parseInt(splitLine[i]);
            } catch (NumberFormatException e) {
                System.out.println((i + 1) + " enter [" + splitLine[i] + "] is not a digit");
                hasError = true;
            }
            if (!rangeCheck(humanNumbers[i], minRange, maxRange)) {
                System.out.println((i + 1) + " enter [" + humanNumbers[i] + "] is not between" + minRange + "/" + maxRange);
                hasError = true;
            }
        }
        if (hasError) {
            return array(size, minRange, maxRange);
        }
        return humanNumbers;
    }

    public int[] getArray(int size, int minRange, int maxRange) {
        return array(size, minRange, maxRange);
    }

    private String[] Clue(int size, int[] botAttack, int[] humanDefense) {
        String clues = sc.nextLine();
        String[] clue = clues.split(" ");
        boolean hasError = false;
        for (int i = 0; i < size; i++) {
            if (!clueCheck(clue[i], i, botAttack, humanDefense)) {
                hasError = true;
            }
        }
        if (hasError){
            Clue(size, botAttack, humanDefense);
        }
        return clue;
    }

    public String[] getClue(int size, int[] botAttack, int[] humanDefense) {
        return Clue(size, botAttack, humanDefense);
    }

    public int gameMode() {
        int i = 0;
        do {
            try {
                i = sc.nextInt();
            }catch (InputMismatchException e){
                System.out.println("it's not a digit");
                sc.next();
            }
        }while (1 > i || i > 3);
        return i;
    }
}
