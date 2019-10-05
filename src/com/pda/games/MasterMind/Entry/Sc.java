package com.pda.games.MasterMind.Entry;

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
                    Sout.wrongEntry(i);
                    error = true;
                }
            } catch (Exception e) {
                Sout.error();
                error = true;
            }
        } while (error);
        sc.nextLine(); // use to clean scanner and read the nex line
        return i;
    }

}
