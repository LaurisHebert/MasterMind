package com.pda.games.mastermind.entry;

import java.util.Scanner;


public class Inputs {

    private static final Scanner sc = new Scanner(System.in);

    private Inputs() {
    }

    public static String nextLine() {
        return sc.nextLine();
    }

    public static int nextPositiveInt() {
        boolean error;
        int i = -1;
        do {
            error = false;
            try {
                i = sc.nextInt();
                if (i < 0) {
                    Errors.wrongEntry(i);
                    error = true;
                }
            } catch (Exception e) {
                Errors.wrongCharacter();
                error = true;
                sc.nextLine();
            }
        } while (error);
        sc.nextLine(); // use to clean scanner and read the nex line
        return i;
    }

}
