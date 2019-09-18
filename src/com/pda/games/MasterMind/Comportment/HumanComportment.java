package com.pda.games.MasterMind.Comportment;

import java.util.Scanner;

public class HumanComportment extends PlayerComportment {

    private static Scanner sc = new Scanner(System.in);

    @Override
    public String playerName() {
        System.out.println("Enter your Pseudo");
        return sc.nextLine();
    }

    @Override
    public int[] lineToFind() {
        System.out.println("entrez vous chiffres");
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != PlayerComportment.sizeOfLineToFind) {
                    System.out.println(PlayerComportment.sizeOfLineToFind + " digits are needed");
                    error = true;
                } else {
                    for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
                        lineToFind[i] = Integer.parseInt(arrayEntry[i]);
                        if (lineToFind[i] < minimalValue || lineToFind[i] > maximumValue) {
                            if (i == 0)
                                System.out.println("Your " + (i + 1) + "st digit \"" + lineToFind[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else if (i == 1)
                                System.out.println("Your " + (i + 1) + "nd digit \"" + lineToFind[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            else
                                System.out.println("Your " + (i + 1) + "rd digit \"" + lineToFind[i] + "\" is not between " + minimalValue + " and " + maximumValue);
                            error = true;
                        }
                    }
                }
            } catch (Exception e) {
                error = true;
                System.out.println("Error please try again");
            }
        } while (error);
        return lineToFind;
    }

    @Override
    public int[] guess() {
        return lineToFind();
    }

    @Override
    public String[] clue(int[] guess, int[] lineToFind) {
        String[] clue = new String[sizeOfLineToFind];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != sizeOfLineToFind) {
                    System.out.println(sizeOfLineToFind + " characters are needed");
                    error = true;
                } else {
                    for (int i = 0; i < sizeOfLineToFind; i++) {
                        if (!arrayEntry[i].equals("=") && !arrayEntry[i].equals("+") && !arrayEntry[i].equals("-")) {
                            System.out.println("Only \"+\" \"-\" or \"=\" are accepted");
                            error = true;
                        } else
                            clue[i] = arrayEntry[i];
                    }
                }
            } catch (Exception e) {
                System.out.println("error please retry");
                error = true;
            }
        } while (error);
        return clue;
    }

    @Override
    public boolean verifyClue(int[] guess, int[] lineToFind , String[] clue) {
        return true;
    }
}
