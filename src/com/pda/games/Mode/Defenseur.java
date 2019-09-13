package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Defenseur extends MasterMind {

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};
    private int[] playerTwoAttack = new int[getSize()];
    private int[] previousAttack = new int[getSize()];
    private String[] playerOneClue = new String[getSize()];
    private HumanEnter humanEnter;

    public Defenseur(HumanEnter humanEnter) {
        super(humanEnter.maximumRound(), playerOneDefense(humanEnter), null);
        this.humanEnter = humanEnter;
    }

    private static int[] playerOneDefense(HumanEnter humanEnter) {
        System.out.println("Defense :");
        return humanEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber());
    }

    @Override
    public boolean humanWin() {
        return botLose();
    }

    @Override
    public boolean humanLose() {
        return botWin();
    }

    @Override
    protected void playerOneClue() {
        playerOneClue = humanEnter.getClue(getSize(), playerTwoAttack, getPlayerOneDefense());
    }

    @Override
    public boolean botWin() {
        return isPlayerTwoCorrespondence() && getRoundCount() <= getMaximumRound();
    }

    @Override
    public boolean botLose() {
        return !isPlayerTwoCorrespondence() && !playerTwoCanPlayAgain();
    }

    @Override
    protected void playerTwoClue() {
    }

    @Override
    public void round() {
        System.out.println("Round " + (getRoundCount() + 1) + "/" + getMaximumRound());
        for (int i = 0; i < getSize(); i++) {
            if (getRoundCount() == 0) {
                playerTwoAttack = BotEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber());
            } else {
                previousAttack[i] = playerTwoAttack[i];
                switch (playerOneClue[i]) {
                    case "+":
                        minimum[i] = playerTwoAttack[i];
                        playerTwoAttack[i] = ((maximum[i] - minimum[i]) / 2) + minimum[i];
                        break;
                    case "-":
                        maximum[i] = playerTwoAttack[i];
                        playerTwoAttack[i] = ((maximum[i] - minimum[i]) / 2) + minimum[i];
                        break;
                    case "=":
                        playerTwoAttack[i] = previousAttack[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(playerTwoAttack));
        verifyAttackPlayerTwo(playerTwoAttack);
        if (!isPlayerTwoCorrespondence()) {
            System.out.println("Clue :");
            playerOneClue();
        }
        System.out.println("===========================\n");
    }

}
