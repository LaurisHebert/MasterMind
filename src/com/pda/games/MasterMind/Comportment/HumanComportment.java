package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Entry.Sc;
import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Structure.Player;

public class HumanComportment extends Player {


    @Override
    public int[] lineToFind() {
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != Player.sizeOfLineToFind) {
                    Sout.notEnough(Player.sizeOfLineToFind);
                    error = true;
                } else {
                    for (int i = 0; i < Player.sizeOfLineToFind; i++) {
                        lineOfDigits[i] = Integer.parseInt(arrayEntry[i]);
                        if (lineOfDigits[i] < Player.minimalValue || lineOfDigits[i] > Player.maximumValue) {
                            Sout.wrongEntry(i, lineOfDigits[i]);
                            error = true;
                        }
                    }
                }
            } catch (NumberFormatException r) {
                error = true;
                System.out.println("Only digits");
            } catch (Exception e) {
                error = true;
                System.out.println("Error please try again");
            }
        } while (error);
        return lineOfDigits;
    }

    @Override
    public int[] guess() {
        return lineToFind();
    }


    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[Player.sizeOfLineToFind];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != Player.sizeOfLineToFind) {
                    Sout.notEnough(Player.sizeOfLineToFind);
                    error = true;
                } else {
                    for (int i = 0; i < Player.sizeOfLineToFind; i++) {
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
    public boolean notVerifyClue(int[] guess, int[] lineToFind, String[] clue) {
        return false;
    }
}
