package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Challenger extends MasterMind {

    private final HumanEnter humanEnter;
    private int[] playerOneAttack = new int[MasterMind.size];

    public Challenger(HumanEnter humanEnter) {
        super(humanEnter.maximumRound(), null, BotEnter.getArray(MasterMind.size, MasterMind.minimumNumber, MasterMind.maximumNumber));
        this.humanEnter = humanEnter;
    }

    @Override
    public boolean humanWin() {
        return isPlayerOneCorrespondence() && getRoundCount() <= getMaximumRound();
    }

    @Override
    public boolean humanLose() {
        return !isPlayerOneCorrespondence() && !playerOneCanPlayAgain();
    }

    @Override
    protected void playerOneClue() {
    }

    @Override
    public boolean botWin() {
        return humanLose();
    }

    @Override
    public boolean botLose() {
        return humanWin();
    }

    @Override
    protected void playerTwoClue() {
        System.out.println(Arrays.toString(BotEnter.getClue(MasterMind.size, playerOneAttack, getPlayerTwoDefense())));
    }

    @Override
    public void round() {
        System.out.println("Round " + (getRoundCount() + 1) + "/" + getMaximumRound());
        System.out.println("Attack :");
        playerOneAttack = humanEnter.getArray(MasterMind.size, MasterMind.minimumNumber, MasterMind.maximumNumber);
        verifyAttackPlayerOne(playerOneAttack);
        if (!isPlayerOneCorrespondence()) {
            playerTwoClue();
        }
        System.out.println("===========================\n");
    }

}
