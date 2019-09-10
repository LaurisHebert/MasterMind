package com.pda.games;

import java.util.Random;
import java.util.Scanner;

public class GetIn {

    private static int[] randomArray(int size, int minRange, int maxRange) {
        int[] genNumber = new int[size];
        for (int i = 0; i < size; i++) {
            genNumber[i] = minRange + new Random().nextInt(maxRange + 1 - minRange);
        }
        return genNumber;
    }

    public static int[] getRandomArray(int size, int minRange, int maxRange) {
        return randomArray(size, minRange, maxRange);
    }


    private static int[] humanArray(int size, int minRange, int maxRange) {
        Scanner sc = new Scanner(System.in);

        int[] humanNumbers = new int[size];
        String numbers = sc.nextLine();
        String[] number = numbers.split(" ");
        for (int i = 0; i < size; i++) {
            try {
                humanNumbers[i] = Integer.parseInt(number[i]);
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Need more digits");
                humanArray(size, minRange, maxRange);
            }catch (NumberFormatException e) {
                System.out.println("One of your \"digits\" is not a digit");
                humanArray(size, minRange, maxRange);
            }
            if (!verify(humanNumbers[i], i, minRange, maxRange, size)) {
                System.out.println("One of your digits are not between" + minRange + "/" + maxRange);
                humanArray(size, minRange, maxRange);
            }
        } return humanNumbers;
    }

    public static int[] getHumanArray(int size, int minRange, int maxRange) {
        return humanArray(size, minRange, maxRange);
    }

    private static boolean verify(int humanNumber, int i,int minRange, int maxRange, int size){
        return minRange >= humanNumber || humanNumber <= maxRange;
    }

    public static String pseudo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your pseudo" +
                "\nPseudo:");
        return sc.next();
    }

    protected static int maxRound() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the maximum number of rounds");
        try {
            return sc.nextInt();
        }catch (java.util.InputMismatchException e){
            sc.next();
        }return maxRound();
    }
}
