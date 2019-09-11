package com.pda.games.Mode;

import com.pda.games.Enters.BotEnter;
import com.pda.games.Enters.HumanEnter;
import com.pda.games.MasterMind;

import java.util.Arrays;

public class Challenger extends MasterMind {

    private final HumanEnter human;
    private int[] humanAttack = new int[getSize()];

    public Challenger(HumanEnter human) {
        super(human.maxRound(), null, BotEnter.getArray(getSize(), getMinRange(), getMaxRange()));
        this.human = human;
    }

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
        humanAttack = human.getArray(getSize(), getMinRange(), getMaxRange());
        humanVerifyEnter(humanAttack);
        if (!isHumanAttackCorrespondence()) {
            botClue();
        }
    }

}
