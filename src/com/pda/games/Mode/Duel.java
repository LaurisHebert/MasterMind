package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Duel extends MasterMind {

    private int[] humanAttack = new int[getSize()];
    private String[] humanClue = new String[getSize()];

    private int[] botAttack = new int[getSize()];
    private int[] previousBotAttack = new int[getSize()];

    private int[] minimum = {0, 0, 0, 0};
    private int[] maximum = {9, 9, 9, 9};

    private final HumanEnter humanEnter;

    public Duel(HumanEnter humanEnter) {
        super(humanEnter.maximumRound(), humanDefenseNumber(humanEnter), BotEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber()));
        this.humanEnter = humanEnter;
    }

    private static int[] humanDefenseNumber(HumanEnter humanEnter) {
        System.out.println("Defense :");
        return humanEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber());
    }

    @Override
    public boolean humanWin() {
        return isPlayerOneCorrespondence() && getRoundCount() <= getMaximumRound();
    }

    @Override
    public boolean humanLose() {
        return isPlayerTwoCorrespondence() || (!isPlayerOneCorrespondence() && getRoundCount() == getMaximumRound());
    }

    @Override
    protected void playerOneClue() {
        humanClue = humanEnter.getClue(getSize(), botAttack, getPlayerOneDefense());
    }

    private void humanTurn() {
        System.out.println("Round " + (getRoundCount() + 1) + "/" + getMaximumRound() +
                "\nHuman Turn\n" +
                "Attack");
        humanAttack = humanEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber());
        verifyAttackPlayerOne(humanAttack);
        playerTwoClue();
    }

    @Override
    public boolean botWin() {
        return isPlayerTwoCorrespondence() && getRoundCount() <= getMaximumRound();
    }

    @Override
    public boolean botLose() {
        return isPlayerOneCorrespondence() || (isPlayerTwoCorrespondence() && getRoundCount() == getMaximumRound());
    }

    @Override
    protected void playerTwoClue() {
        System.out.println(Arrays.toString(BotEnter.getClue(getSize(), humanAttack, getPlayerTwoDefense())));
    }

    private void botTurn() {
        System.out.println("Bot turn");
        for (int i = 0; i < getSize(); i++) {
            if (getRoundCount() == 0) {
                botAttack = BotEnter.getArray(getSize(), getMinimumNumber(), getMaximumNumber());
            } else {
                previousBotAttack[i] = botAttack[i];
                switch (humanClue[i]) {
                    case "+":
                        minimum[i] = botAttack[i];
                        botAttack[i] = ((maximum[i] - minimum[i]) / 2) + minimum[i];
                        break;
                    case "-":
                        maximum[i] = botAttack[i];
                        botAttack[i] = ((maximum[i] - minimum[i]) / 2) + minimum[i];
                        break;
                    case "=":
                        botAttack[i] = previousBotAttack[i];
                        break;
                }
            }
        }
        System.out.println(Arrays.toString(botAttack));
        verifyAttackPlayerTwo(botAttack);
        if (!isPlayerTwoCorrespondence()) {
            System.out.println("Clue :");
            playerOneClue();
        }
        System.out.println("===========================\n");

    }

    @Override
    public void round() {
        humanTurn();
        setRoundCount(getRoundCount() - 1);
        botTurn();
    }

}
