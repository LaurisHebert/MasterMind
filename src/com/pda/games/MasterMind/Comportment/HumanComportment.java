package com.pda.games.MasterMind.Comportment;

import java.util.Arrays;
import java.util.Scanner;

public class HumanComportment extends PlayerComportment {

    private static Scanner sc = new Scanner(System.in);

    @Override
    public String playerName() {
        return sc.nextLine();
    }

    @Override
    public void lineToFind() {
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
                        if (lineToFind[i] < PlayerComportment.minimalValue || lineToFind[i] > PlayerComportment.maximumValue) {
                            if (i == 0)
                                System.out.println("Your " + (i + 1) + "st digit \"" + lineToFind[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            else if (i == 1)
                                System.out.println("Your " + (i + 1) + "nd digit \"" + lineToFind[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            else
                                System.out.println("Your " + (i + 1) + "rd digit \"" + lineToFind[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            error = true;
                        }
                    }
                }
            } catch (NumberFormatException r) {
                error = true;
                System.out.println("Only digits");
            } catch (Exception e) {
                error = true;
                System.out.println(e);
                System.out.println("Error please try again");
            }
        } while (error);
    }

    @Override
    public int[] guess() {
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
                        guess[i] = Integer.parseInt(arrayEntry[i]);
                        if (guess[i] < PlayerComportment.minimalValue || guess[i] > PlayerComportment.maximumValue) {
                            if (i == 0)
                                System.out.println("Your " + (i + 1) + "st digit \"" + guess[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            else if (i == 1)
                                System.out.println("Your " + (i + 1) + "nd digit \"" + guess[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            else
                                System.out.println("Your " + (i + 1) + "rd digit \"" + guess[i] + "\" is not between " + PlayerComportment.minimalValue + " and " + PlayerComportment.maximumValue);
                            error = true;
                        }
                    }
                }
            } catch (NumberFormatException r) {
                error = true;
                System.out.println("Only digits");
            } catch (Exception e) {
                error = true;
                System.out.println(e);
                System.out.println("Error please try again");
            }
        } while (error);
        return guess;
    }


    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        System.out.println("Memo* " + Arrays.toString(lineToFind));
        String[] clue = new String[PlayerComportment.sizeOfLineToFind];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != PlayerComportment.sizeOfLineToFind) {
                    System.out.println(PlayerComportment.sizeOfLineToFind + " characters are needed");
                    error = true;
                } else {
                    for (int i = 0; i < PlayerComportment.sizeOfLineToFind; i++) {
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
    public boolean verifyClue(int[] guess, int[] lineToFind, String[] clue) {
        return true;
    }
}
