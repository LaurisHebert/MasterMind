package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Entry.Sc;
import com.pda.games.MasterMind.Entry.Sout;
import com.pda.games.MasterMind.Structure.Player;

public class HumanComportment extends Player {


    @Override
    public int[] lineToFind() {
        int[] lineOfDigits = new int[sizeOfLineToFind];
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
                            Sout.wrongEntry(i);
                            error = true;
                        }
                    }
                }
            } catch (NumberFormatException r) {
                error = true;
                Sout.onlyDigits();
            } catch (Exception e) {
                error = true;
                Sout.error();
            }
        } while (error);
        return lineOfDigits;
    }

    @Override
    public int[] guess() {
        return guess = lineToFind();
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
                            Sout.onlySymbol();
                            error = true;
                        } else
                            clue[i] = arrayEntry[i];
                    }
                }
            } catch (Exception e) {
                Sout.error();
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
