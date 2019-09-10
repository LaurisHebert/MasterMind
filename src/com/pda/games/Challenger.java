package com.pda.games;

import java.util.Arrays;

import static com.pda.games.GetIn.getRandomArray;

public class Challenger extends MasterMind {
    private int[] humanAttack = new int[getSize()];
    private String[] clue = new String[getSize()];

    public Challenger() { super(GetIn.maxRound(), null, getRandomArray(getSize(), getMinRange(), getMaxRange())); }


    @Override
    public boolean humanWin() { return isHumanAttackCorrespondence() && getRound() <= getMaxRound(); }
    @Override
    public boolean humanLose() { return !isHumanAttackCorrespondence() && !humanCanPlayAgain(); }
    @Override
    public boolean botWin(){ return humanLose(); }
    @Override
    public boolean botLose(){ return humanWin(); }

    @Override
    public void round() {
        System.out.println(Arrays.toString(getBotDefense()));
        System.out.println("Round " + (getRound() + 1) + "/" + getMaxRound());
        System.out.println("Attack :");
        humanAttack = GetIn.getHumanArray(getSize(), getMinRange(), getMaxRange());
        humanVerifyEnter(humanAttack);
        if (!isHumanAttackCorrespondence()) {
            clue();
        }
    }


    @Override
    protected void clue() {
        for (int i = 0; i < getSize(); i++) {
            if (humanAttack[i] == getBotDefense()[i]) {
                clue[i] = "=";
            }
            if (humanAttack[i] > getBotDefense()[i]) {
                clue[i] = "-";
            }
            if (humanAttack[i] < getBotDefense()[i]) {
                clue[i] = "+";
            }
        }
        System.out.println(Arrays.toString(clue));
    }
}
