package com.pda.games;

import java.util.Arrays;

public abstract class MasterMind {

    final static int size = 4;
    final static int minRange = 0;
    final static int maxRange = 9;
    // static car utiliser pour définire d'autres methodes statics

    public final int[] defenseNumber;
    // public car utiliser dans Main

    int maxRound;
    int round = 0;
    // package-private car utilisé dans Round avec maxRound

    boolean correspondence = false;
    // package-private car utilisé pour définire les win et lose

    MasterMind(int maxRound, int[] defenseNumber){
        this.maxRound = maxRound;
        this.defenseNumber=defenseNumber;
    }

    public abstract void round();

    void verifyEnter(int[] attackNumber){
        if (canPlayAgain()){
            correspondence = Arrays.equals(defenseNumber, attackNumber);
            clue();
        }
        round++;
    }

    public boolean canPlayAgain(){
        return !correspondence && round < maxRound;
    }

    abstract void clue();

    abstract public boolean hasWon();

    abstract public boolean hasLost();
}

