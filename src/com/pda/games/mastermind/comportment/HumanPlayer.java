package com.pda.games.mastermind.comportment;

import com.pda.games.mastermind.entry.Errors;
import com.pda.games.mastermind.entry.Inputs;
import com.pda.games.mastermind.entry.Texts;
import com.pda.games.mastermind.model.MasterMindConfig;

public class HumanPlayer extends Player {

    public HumanPlayer(Texts texts, MasterMindConfig config, String playerName) {
        super(texts, config, playerName);
    }

    /**
     * used for ask at the human player to create an array with selected length and range of digits in configuration file
     *
     * @return an array
     */
    private int[] readArrayInt() {
        int[] lineOfDigits = new int[config.getSizeOfLineToFind()];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Inputs.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != config.getSizeOfLineToFind()) {
                    Errors.notEnough(config.getSizeOfLineToFind());
                    error = true;
                } else {
                    for (int i = 0; i < config.getSizeOfLineToFind(); i++) {
                        lineOfDigits[i] = Integer.parseInt(arrayEntry[i]);
                        if (lineOfDigits[i] < config.getMinimalValue() || lineOfDigits[i] > config.getMaximumValue()) {
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
        texts.initializationMessage(getPlayerName());
        lineToFind = readArrayInt();
    }

    @Override
    public void guess() {
        guess = readArrayInt();
    }

    @Override
    public String[] clue(int[] lineToFind, int[] guess) {
        String[] clue = new String[config.getSizeOfLineToFind()];
        boolean error;
        do {
            error = false;
            try {
                String humanEntry = Inputs.nextLine();
                String[] arrayEntry = humanEntry.split(" ");
                if (arrayEntry.length != config.getSizeOfLineToFind()) {
                    Errors.notEnough(config.getSizeOfLineToFind());
                    error = true;
                } else {
                    for (int i = 0; i < config.getSizeOfLineToFind(); i++) {
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
