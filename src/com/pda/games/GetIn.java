package com.pda.games;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GetIn {
    static  private Scanner sc = new Scanner(System.in);

    private static int[] randomArray(int size, int minRange, int maxRange) {
        int[] genNumber = new int[size];
        for (int i = 0; i < size ; i++) {
            genNumber[i] = minRange + new Random().nextInt(maxRange + 1 - minRange);
        }return genNumber;
    }

    public static int[] getRandomArray(int size, int minRange, int maxRange) {
        return randomArray(size, minRange, maxRange);
    }


    private static int[] humanArray(int size, int minRange, int maxRange){
        // à faire fonctionner 
        int[] humanNumber= new int[size];
        String tests = sc.nextLine();
        String[] test = tests.split(" ");
        for (int i = 0; i < size; i++) {
            try {
                humanNumber[i] = Integer.parseInt(test[i]); }
            catch (java.lang.NumberFormatException e) {
                System.out.println("One of them is not a digit");
                humanArray(size, minRange, maxRange);
            }
            if (humanNumber[i] > minRange && humanNumber[i] < maxRange) {
                break;
            }
            else {
                System.out.println("One of your digits are not between" + minRange + "/" + maxRange);
                humanArray(size, minRange, maxRange);
            }
        }return humanNumber;
    }

    public static int[] getHumanArray(int size, int minRange, int maxRange) {
        return humanArray(size, minRange, maxRange);
    }

}
