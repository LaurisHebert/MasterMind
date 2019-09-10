package com.pda.games;

import java.util.Arrays;

public class Challenger extends MasterMind {

    private int[] humanAttack = new int[getSize()];
    private String[] clue = new String[getSize()];

    public Challenger() { super(HumanEnter.maxRound(), null, BotEnter.getArray(getSize(), getMinRange(), getMaxRange())); }

    @Override
    public boolean humanWin() { return isHumanAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean humanLose() { return !isHumanAttackCorrespondence() && !humanCanPlayAgain(); }
    @Override
    protected void humanClue() {}

    @Override
    public boolean botWin(){ return humanLose(); }
    @Override
    public boolean botLose(){ return humanWin(); }
    @Override
    protected void botClue() { System.out.println(Arrays.toString(BotEnter.getClue(getSize(), humanAttack, getBotDefense()))); }

    @Override
    public void round() {
        System.out.println("Round " + (getRound() + 1) + "/" + getMaxRound());
        System.out.println("Attack :");
        humanAttack = HumanEnter.getArray(getSize(), getMinRange(), getMaxRange());
        humanVerifyEnter(humanAttack);
        if (!isHumanAttackCorrespondence()) {
            botClue();
        }
    }

}
