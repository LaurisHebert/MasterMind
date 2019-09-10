package com.pda.games;

import java.util.Arrays;

public abstract class MasterMind {

    private final static int size = 4;
    public static int getSize() { return size; }

    private final static int minRange = 0;
    protected static int getMinRange() { return minRange; }

    private final static int maxRange = 9;
    protected static int getMaxRange() { return maxRange; }

    private final int[] humanDefense;
    public int[] getHumanDefense() { return humanDefense; }

    private final int[] botDefense;
    public int[] getBotDefense() { return botDefense; }

    private int maxRound;
    protected int getMaxRound() { return maxRound; }

    private int round = 0;
    protected int getRound() { return round; }
    protected void setRound(int round) { this.round = round; }

    private boolean botAttackCorrespondence = false;
    protected boolean isBotAttackCorrespondence() { return botAttackCorrespondence; }

    private boolean humanAttackCorrespondence = false;
    protected boolean isHumanAttackCorrespondence() { return humanAttackCorrespondence; }


    protected MasterMind(int maxRound, int[] humanDefense, int[] botDefenseNumber){
        this.maxRound = maxRound;
        this.humanDefense = humanDefense;
        this.botDefense = botDefenseNumber;
    }

    public abstract void round();

    protected void botVerifyEnter(int[] botAttack){
        if (botCanPlayAgain()){
            botAttackCorrespondence = Arrays.equals(humanDefense, botAttack);
            round++;
        }
    }
    public boolean botCanPlayAgain() {
        return !botAttackCorrespondence && round < maxRound;
    }
    public abstract boolean botWin();
    public abstract boolean botLose();
    protected abstract void botClue();

    protected void humanVerifyEnter(int[] humanAttack){
        if (humanCanPlayAgain()){
            humanAttackCorrespondence = Arrays.equals(botDefense, humanAttack);
            round++;
        }
    }
    public boolean humanCanPlayAgain() {
        return !humanAttackCorrespondence && round < maxRound;
    }
    public abstract boolean humanWin();
    public abstract boolean humanLose();
    protected abstract void humanClue();

}

