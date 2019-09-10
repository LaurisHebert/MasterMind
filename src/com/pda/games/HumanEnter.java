package com.pda.games;

import java.util.Scanner;

public class HumanEnter {

    protected static int maxRound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        }catch (java.util.InputMismatchException e){
            sc.next();
        }return maxRound();
    }
    private static int[] array(int size, int minRange, int maxRange) {
        Scanner sc = new Scanner(System.in);

        int[] humanNumbers = new int[size];
        String numbers = sc.nextLine();
        String[] number = numbers.split(" ");
        for (int i = 0; i < size; i++) {
            try {
                humanNumbers[i] = Integer.parseInt(number[i]);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Need more digits");
                array(size, minRange, maxRange);
            }catch (NumberFormatException e) {
                System.out.println("One of your \"digits\" is not a digit");
                array(size, minRange, maxRange);
            }
            if (!verify(humanNumbers[i], i, minRange, maxRange, size)) {
                System.out.println("One of your digits are not between" + minRange + "/" + maxRange);
                array(size, minRange, maxRange);
            }
        } return humanNumbers;
    }

    private static boolean verify(int humanNumber, int i,int minRange, int maxRange, int size){
        return minRange >= humanNumber || humanNumber <= maxRange;
    }

    public static int[] getArray(int size, int minRange, int maxRange) {
        return array(size, minRange, maxRange);
    }

    public static String pseudo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pseudo" +
                "\nPseudo:");
        return sc.next();
    }

    protected static String[] Clue(int size, int[] botAttack, int[] humanDefense){
        Scanner sc = new Scanner(System.in);
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

    public static String[] getClue(int size, int[] botAttack, int[] humanDefense){
        return Clue(size, botAttack, humanDefense);
    }

}
