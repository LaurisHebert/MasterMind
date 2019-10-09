package com.pda.games.mastermind.entry;

import java.util.Scanner;


public class Sc {

    private static final Scanner sc = new Scanner(System.in);

    private Sc() {
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
                Errors.error();
                error = true;
            }
        } while (error);
        sc.nextLine(); // use to clean scanner and read the nex line
        return i;
    }

}
