package com.pda.games.MasterMind.Entry;

import java.util.Scanner;

public class Sc {

    private static final Scanner sc = new Scanner(System.in);
    private Sc() { }

    public static String nextLine(){
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
                    System.out.println(i + "is to low");
                    error = true;
                }
            } catch (Exception e) {
                System.out.println("input error");
                error = true;
            }
        } while (error);
        sc.nextLine(); // read the next line for clean next
        return i;
    }

}
