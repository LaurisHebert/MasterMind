package com.pda.games.MasterMind.Comportment;

import java.util.Arrays;
import java.util.Scanner;

public class HumanComportment implements PlayerComportment {

    private static Scanner sc = new Scanner(System.in);

    public HumanComportment() {
        String playerName = playerName();
    }

    @Override
    public String playerName() {
        System.out.println("Enter your Pseudo");
        return sc.next();
    }

    @Override
    public int[] lineToFind(int sizeOfTheArray, int minimalValue, int maximumValue) {
        System.out.println("Please enter your secret line");
        int[] array = new int[sizeOfTheArray];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();

                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != sizeOfTheArray) {
                    System.out.println(sizeOfTheArray + " digits are needed");
                    error = true;
                } else {
                    for (int i = 0; i < sizeOfTheArray; i++) {
                        array[i] = Integer.parseInt(arrayEntry[i]);
                        if (array[i] < minimalValue || array[i] > maximumValue) {
                            if (i == 0)
                                System.out.println("Your " + (i + 1) + "st digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else if (i == 1)
                                System.out.println("Your " + (i + 1) + "nd digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else
                                System.out.println("Your " + (i + 1) + "rd digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            error = true;
                        }
                    }
                }
            } catch (Exception e) {
                error = true;
                System.out.println("Error please try again");
            }
        } while (error);
        return array;
    }

    @Override
    public int[] guess(int sizeOfTheArray, int minimalValue, int maximumValue, int roundCount, String[] clue) {
        int[] array = new int[sizeOfTheArray];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();

                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != sizeOfTheArray) {
                    System.out.println(sizeOfTheArray + " digits are needed");
                    error = true;
                } else {
                    for (int i = 0; i < sizeOfTheArray; i++) {
                        array[i] = Integer.parseInt(arrayEntry[i]);
                        if (array[i] < minimalValue || array[i] > maximumValue) {
                            if (i == 0)
                                System.out.println("Your " + (i + 1) + "st digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else if (i == 1)
                                System.out.println("Your " + (i + 1) + "nd digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else
                                System.out.println("Your " + (i + 1) + "rd digit \"" + array[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            error = true;
                        }
                    }
                }
            } catch (Exception e) {
                error = true;
                System.out.println("Error please try again");
            }
        } while (error);
        return array;
    }

    @Override
    public String[] clue(int sizeOfTheArray, int[] lineToFind, int[] guess) {
        String[] clue = new String[sizeOfTheArray];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != sizeOfTheArray) {
                    System.out.println(sizeOfTheArray + " characters are needed");
                    error = true;
                } else {
                    for (int i = 0; i < sizeOfTheArray; i++) {
                        if (!arrayEntry[i].equals("=") && !arrayEntry[i].equals("+") && !arrayEntry[i].equals("-")) {
                            System.out.println("Only \"+\" \"-\" or \"=\" are accepted");
                            error = true;
                        } else
                            clue[i] = arrayEntry[i];
                    }
                    if (!trueClue(clue, sizeOfTheArray, lineToFind, guess))
                        error = true;
                }
            } catch (Exception e) {
                System.out.println("error please retry");
                error = true;
            }

        } while (error);
        return clue;
    }

    private boolean trueClue(String[] clue, int sizeOfTheArray, int[] lineToFind, int[] guess) {
        return Arrays.equals(clue, PlayerComportment.verifyClue(sizeOfTheArray, lineToFind, guess));
    }
}
