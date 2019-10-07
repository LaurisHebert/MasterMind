package com.pda.games.MasterMind.Comportment;

import com.pda.games.MasterMind.Entry.Errors;
import com.pda.games.MasterMind.Entry.Sc;
import com.pda.games.MasterMind.Entry.Texts;
import com.pda.games.MasterMind.Model.MasterMind;
import com.pda.games.MasterMind.Model.Player;

public class HumanComportment extends Player {

    public HumanComportment(String playerName) {
        super(playerName);
    }

    public static String playerName() {
        return Sc.nextLine();
    }

    private int[] readArrayInt() {
        int[] lineOfDigits = new int[MasterMind.sizeOfLineToFind];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != MasterMind.sizeOfLineToFind) {
                    Errors.notEnough(MasterMind.sizeOfLineToFind);
                    error = true;
                } else {
                    for (int i = 0; i < MasterMind.sizeOfLineToFind; i++) {
                        lineOfDigits[i] = Integer.parseInt(arrayEntry[i]);
                        if (lineOfDigits[i] < MasterMind.minimalValue || lineOfDigits[i] > MasterMind.maximumValue) {
                            Errors.wrongEntry(i);
                            error = true;
                        }
                    }
                }
            } catch (NumberFormatException r) {
                error = true;
                Errors.onlyDigits();
            } catch (Exception e) {
                error = true;
                Errors.error();
            }
        } while (error);
        return lineOfDigits;
    }

    @Override
    public void lineToFind() {
        Texts.initializationMessage(getPlayerName());
        lineToFind = readArrayInt();
    }

    @Override
    public void guess() {
        guess = readArrayInt();
    }

    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[MasterMind.sizeOfLineToFind];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Sc.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != MasterMind.sizeOfLineToFind) {
                    Errors.notEnough(MasterMind.sizeOfLineToFind);
                    error = true;
                } else {
                    for (int i = 0; i < MasterMind.sizeOfLineToFind; i++) {
                        if (!arrayEntry[i].equals("=") && !arrayEntry[i].equals("+") && !arrayEntry[i].equals("-")) {
                            Errors.onlySymbol();
                            error = true;
                        } else
                            clue[i] = arrayEntry[i];
                    }
                }
            } catch (Exception e) {
                Errors.error();
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
