package com.pda.games;

import java.util.Arrays;

public abstract class MasterMind {

    static final int size = 4;
    static int maxTurn;
    public final int[] defenseNumber;

    int turn = 0;

    boolean correspondence;

    MasterMind(int maxTurn, int[] defenseNumber){
        MasterMind.maxTurn = maxTurn;
        this.defenseNumber=defenseNumber;
    }

    public abstract void turn();
    abstract void clue();

    void verifyEnter(int[] attackNumber){
        correspondence=Arrays.equals(defenseNumber, attackNumber);
    }

    public boolean canPlayAgain(){
        return !correspondence && turn < maxTurn;
    }
    public boolean win(){
        return correspondence && turn <= maxTurn;
    }
    public boolean lose(){
        return !correspondence && turn >= maxTurn;
    }
}

