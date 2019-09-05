package com.pda.games;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GetIn {
    static private Scanner sc = new Scanner(System.in);

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
        int[] humanNumber = new int[size];
        for (int i = 0; i < size; i++) {
            humanNumber[i] = tryCatch();
            while (humanNumber[i] < minRange || humanNumber[i] > maxRange) {
                System.out.println(humanNumber[i] + " are not between" + minRange + "/" + maxRange);
                humanNumber[i] = tryCatch();
            }
        }return humanNumber;
    }

    public static int[] getHumanArray(int size, int minRange, int maxRange) {
        return humanArray(size, minRange, maxRange);
    }

    private static int tryCatch() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("One of them is not a digit");
            sc.next();
            return tryCatch();
        }
    }
}
